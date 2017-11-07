package juzix.com.web3jdemo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import java.util.List;
import java.util.UUID;

import contract.RegisterApplyManager;
import contract.UserManager;
import juzix.com.web3jdemo.bean.RegisterInfo;
import juzix.com.web3jdemo.bean.RegisterUserWrap;
import permission.PermisionsConstant;
import permission.PermissionsManager;
import permission.PermissionsResultAction;
import util.MyWalletUtil;
import web3j.loader.ContractManager;
import web3j.util.GsonUtil;

/**
 * 文件证书
 */
public class WalletFileActivity extends AppCompatActivity {

    public static final int                  ACCOUNT_REPETITION=   5 ; //用户名重复
    public static final int                  MOBILE_REPETITION=   6 ; //手机号已存在
    public static final int                  EMAIL_REPETITION=   7  ;//email已经存在
    //钱包存放的文件夹名称
    String walletDirName="web3j_wallet";
    //钱包存放的路径（SD卡根目录wallet文件夹内）
    String walletFilePath= getExternalPrivatePath(walletDirName);
    //钱包密码
    String password="12345678";


    private EditText walletNameEt,emailEt,phoneEt;
     TextView testResult;
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
        setContentView(R.layout.activity_wallet_file);
        walletNameEt=findViewById(R.id.wallet_name_et);
        emailEt=findViewById(R.id.wallet_email_et);
        phoneEt=findViewById(R.id.wallet_mobile_et);
        testResult=findViewById(R.id.result);
    }

    /**
     * 注册钱包
     * 第一次使用时，可以注册一个钱包文件用于发交易
     * @param view
     */
    public void register(View view) {
        String []permissions=new String[]{PermisionsConstant.WRITE_EXTERNAL_STORAGE,PermisionsConstant.READ_EXTERNAL_STORAGE};
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permissions, new PermissionsResultAction() {
            @Override
            public void onGranted() {
                startRegist();
            }

            @Override
            public void onDenied(String permission) {

            }
        });

    }

    private void startRegist(){
        final String walletFileName=walletNameEt.getText().toString().trim();
        if(TextUtils.isEmpty(walletFileName)){
            Toast.makeText(this,"钱包文件名不能为空",Toast.LENGTH_LONG).show();
            return;
        }
        boolean success=MyWalletUtil.createWalletFile(walletFileName,walletFilePath,password);
        if(success){
            //本地钱包注册成功后，向服务器后台注册
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ContractManager.getInstance().reset();
                    ContractManager.getInstance().buildType(1)
                        .credentials(MyWalletUtil.getmCredentials());
                    register2Server(walletFileName);
                }
            }).start();
        }else {
            Toast.makeText(this,"本地钱包注册失败",Toast.LENGTH_LONG).show();
        }
    }
    /**
     * 注册到服务
     */
    private void register2Server(String walletFileName){
        RegisterUserWrap userWrap= generateRegisterInfo(walletFileName);
        String json= GsonUtil.objectToJson(userWrap,RegisterUserWrap.class);
        try {
            //加载注册申请合约
            RegisterApplyManager userManager=(RegisterApplyManager) ContractManager.getInstance().getContractByFullName(RegisterApplyManager.class.getName());
            Utf8String utf8String=new Utf8String(json);
            //调用合约的新增用户方法
            TransactionReceipt receipt= userManager.insert(utf8String).get();
            //获取应答
            List<RegisterApplyManager.NotifyEventResponse> responses = userManager.getNotifyEvents(receipt);
            if (responses.size() > 0) {
                Uint256 uint256 = responses.get(0)._errno;
                Utf8String info = responses.get(0)._info;
                Log.i("UserManager","====ret===" + uint256.getValue().intValue() + "====" + utf8String.getValue());
                int code= uint256.getValue().intValue();
                if (0 ==code) {
                    //注册成功后的逻辑
                    //....
                } else if(ACCOUNT_REPETITION==code){
                    //根据错误码进行逻辑处理......
                }else if(MOBILE_REPETITION==code){
                    //根据错误码进行逻辑处理......
                }else if(EMAIL_REPETITION==code){
                    //根据错误码进行逻辑处理......
                }else {
                    //根据错误码进行逻辑处理......
                }
            } else {
                //没有应答逻辑处理......
            }
        }catch (Exception e){
            e.printStackTrace();
            //异常逻辑处理......
        }
    }




    /**
     * 钱包登录
     * 加载钱包文件
     * @param view
     */
    public void login(View view) {
        String walletFileName=walletNameEt.getText().toString().trim();
        boolean success=MyWalletUtil.validataPasswdAndInit(walletFilePath,password,walletFileName);
        if(success){
            //登录成功
            //先重置
            ContractManager.getInstance().reset();
            ContractManager.getInstance().buildType(1)
                .credentials(MyWalletUtil.getmCredentials());

            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this,"登录失败",Toast.LENGTH_LONG).show();
        }
    }


    /**
     * 钱包等数据可以存放到这里
     *
     * @return
     */
    public static String getExternalPrivatePath(String name) {
        String namedir = "/"+name+"/";
        return Environment.getExternalStorageDirectory().getPath() + namedir;
    }


    /**
     * 采集用户信息
     * @return
     */
    private RegisterUserWrap generateRegisterInfo(String walletFileName) {
        String emailStr=emailEt.getText().toString().trim();
        String mobileStr=phoneEt.getText().toString().trim();
        RegisterUserWrap infoWrap=new RegisterUserWrap();
        RegisterInfo info=new RegisterInfo();
        info.setAccount(walletFileName);
        //钱包公钥
        String pubkey= Numeric.toHexStringNoPrefix(MyWalletUtil.getmCredentials().getEcKeyPair().getPublicKey());
        info.setPublicKey(pubkey);
        info.setCertType(1);
        info.setUserAddr(MyWalletUtil.getmCredentials().getAddress());
        info.setUserId(MyWalletUtil.getmCredentials().getAddress());
        info.setUuid(walletFileName);

        info.setName(walletFileName);
        info.setMobile(mobileStr);
        info.setEmail(emailStr);
        info.setOrgId("0xf4a60bfffcefaf01cd560a1c65b83d3859de154d55463de690da92df41949e63");
        info.setOrgName("椭圆组织");

        infoWrap.setApplyId(UUID.randomUUID().toString());
        infoWrap.setRegisterUser(info);
        return infoWrap;

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
                    Address uuidUtf=new Address(MyWalletUtil.getmCredentials().getAddress());
                    //调用合约方法，得到用户信息的json数据
                    //新注册的用户需要审核后才能查找到用户信息
                    Utf8String result= userManager.findByAddress(uuidUtf).get();
                    String jsonStr=result.toString();
                    Message msg=Message.obtain();
                    msg.obj=jsonStr;
                    handler.sendMessage(msg);
                }catch (Exception e){
                    //异常逻辑处理......
                    e.printStackTrace();
                    Message msg=Message.obtain();
                    msg.obj="查询失败";
                    handler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

