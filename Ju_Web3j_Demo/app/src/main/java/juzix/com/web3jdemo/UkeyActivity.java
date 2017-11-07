package juzix.com.web3jdemo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Hash;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import java.util.List;

import contract.UserManager;
import juzix.com.web3jdemo.bean.UserInfo;
import juzix.com.web3jdemo.bean.UserInfoResult;
import permission.PermisionsConstant;
import permission.PermissionsManager;
import permission.PermissionsResultAction;
import web3j.loader.ContractManager;
import web3j.ukey.manager.UKeyManager;
import web3j.util.GsonUtil;

public class UkeyActivity extends AppCompatActivity implements View.OnClickListener{
    private Button getDevice;

    private static final int REQUSET = 1;
    private String g_sn;
    private TextView testResult;
    private EditText pinEt,emailEt;
    ProgressDialog progressResetCounter = null;
    private UserInfo userInfo;
    @SuppressLint("HandlerLeak")
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String strTMP1 = (String) msg.obj;
            testResult.setText(strTMP1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ukey);
        progressResetCounter = new ProgressDialog( this);

        getDevice = (Button) findViewById(R.id.getDevice);
        testResult=(TextView)findViewById(R.id.result_tv);
        pinEt=findViewById(R.id.ukey_pin_et);
        emailEt=findViewById(R.id.email_et);
        getDevice.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {


        if (arg0.getId() == R.id.getDevice) {
            PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, new String[]{PermisionsConstant.ACCESS_COARSE_LOCATION,PermisionsConstant.BLUETOOTH_ADMIN,PermisionsConstant.FIND_LOCATION}, new PermissionsResultAction() {
                @Override
                public void onGranted() {
                    //先断开连接
                  //  UKeyManager.getInstance().CloseDevice();

                    Intent intent0 = new Intent(UkeyActivity.this, DeviceScanActivity.class);

                    startActivityForResult(intent0, REQUSET);

                }

                @Override
                public void onDenied(String permission) {

                }
            });

            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // User chose not to enable Bluetooth.
        if (requestCode == UkeyActivity.REQUSET && resultCode == RESULT_OK) {
            final String sn = data.getStringExtra("device_info");
            testResult.setText("选择设备:" + sn);
            g_sn = sn;
            //连接设备
            connect();
        }
    }


    /**
     * 连接设备
     */
    private void connect() {
        new Thread(new Runnable() {

            @Override
            public void run() {



                int ret=UKeyManager.getInstance().OpenDevice(g_sn, 10000);
                if(ret==0){
                    try {
                        final String sn=UKeyManager.getInstance().GetSN();
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressResetCounter.dismiss();
                                testResult.setText("成功!sn="+sn);
                            }
                        });
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        runOnUiThread(new Runnable() {
                            public void run() {
                                progressResetCounter.dismiss();
                                testResult.setText("error");
                            }
                        });
                    }
                }
                else
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressResetCounter.dismiss();
                            testResult.setText("error");
                        }
                    });



            }
        }).start();

    }

    public void ukeylogin(View view) {
        String pin=pinEt.getText().toString().trim();
        if(TextUtils.isEmpty(pin)){
            Toast.makeText(this,"pin 不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        try {
            int code=UKeyManager.getInstance().VerifyPin(pin);
            if(code==0){
                //获取Ukey公钥
                String pubkey= Numeric.toHexString(UKeyManager.getInstance().ECCGetPubKey());
                //根据公钥推导出地址
                String address=getAddressByPubkey();
                ContractManager.getInstance().reset();
                ContractManager.getInstance()
                    .buildType(2)
                    .keyDriver(UKeyManager.getInstance())
                    .ukeyPassword(pin)
                    .ukeyUserAddress(address);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    private String getAddressByPubkey(){

        byte[] pubBytes = Hash.sha3(UKeyManager.getInstance().ECCGetPubKey());
        String pubkeyHex= Numeric.toHexString(pubBytes);
        String addr="0x"+pubkeyHex.substring(pubkeyHex.length()-40,pubkeyHex.length());
        return addr;
    }

    public void findByAddress(View view) {
        getUserInfo();
    }

    /**
     * 调用合约方法查找用户信息

     */
    public void getUserInfo() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //加载用户合约
                    UserManager userManager=( UserManager)ContractManager.getInstance().getContractByFullName(UserManager.class.getName());
                    //使用注册时，给用户设置的address
                    Address uuidUtf=new Address(getAddressByPubkey());
                    //调用合约方法，得到用户信息的json数据
                    //新注册的用户需要审核后才能查找到用户信息
                    Utf8String result= userManager.findByAddress(uuidUtf).get();
                    String jsonStr=result.toString();
                    UserInfoResult infoResult= GsonUtil.jsonToObject(jsonStr,UserInfoResult.class);
                    List<UserInfo> infos=infoResult.getData().getItems();
                    Message msg=Message.obtain();
                    msg.obj=jsonStr;
                    if(infos.size()>0){
                        userInfo=infos.get(0);
                    }
                    handler.sendMessage(msg);
                }catch (Exception e){
                    //异常逻辑处理......
                    Message msg=Message.obtain();
                    msg.obj="查询失败";
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void updateInfo(View view) {
       final String newEmailStr=emailEt.getText().toString().trim();
        if(TextUtils.isEmpty(newEmailStr)){
            Toast.makeText(this,"邮箱不能为空",Toast.LENGTH_LONG).show();
        }
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                updateEmail(newEmailStr);
            }
        });
        thread.start();


    }

    private void updateEmail(String newEmailStr){
        //加载用户合约
        UserManager userManager=( UserManager)ContractManager.getInstance().getContractByFullName(UserManager.class.getName());
        if(userInfo!=null){
            try {
                userInfo.setEmail(newEmailStr);
                String jsonStr= GsonUtil.objectToJson(userInfo,UserInfo.class);
                Utf8String utf8String=new Utf8String(jsonStr);
                TransactionReceipt receipt= userManager.update(utf8String).get();
                List<UserManager.NotifyEventResponse> responses = userManager.getNotifyEvents(receipt);
                if (responses.size() > 0) {
                    Uint256 uint256 = responses.get(0)._errno;
                    Utf8String info = responses.get(0)._info;
                    Log.i("UserManager","====ret===" + uint256.getValue().intValue() + "====" + utf8String.getValue());
                    int code=uint256.getValue().intValue();
                    if (0 == code) {
                        System.out.print("修改成功！");
                    } else if(15214==code){
                        System.out.print("邮箱已存在！");

                    }else if(15215==code){
                        System.out.print("手机号已存在！");

                    }else {
                        System.out.print(info.toString());
                    }
                } else {
                    System.out.print("访问服务器失败！");
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
