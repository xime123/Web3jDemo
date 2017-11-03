package juzix.com.ju_web3j_demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.utils.Numeric;

import java.util.List;
import java.util.UUID;

import contract.RegisterApplyManager;
import permission.PermisionsConstant;
import permission.PermissionsManager;
import permission.PermissionsResultAction;
import util.MyWalletUtil;
import web3j.util.GsonUtil;

public class MainActivity extends AppCompatActivity {

    public static final int                  ACCOUNT_REPETITION=   5 ; //用户名重复
    public static final int                  MOBILE_REPETITION=   6 ; //手机号已存在
    public static final int                  EMAIL_REPETITION=   7  ;//email已经存在
    //钱包存放的文件夹名称
    String walletDirName="web3j_wallet";
    //钱包存放的路径（SD卡根目录wallet文件夹内）
    String walletFilePath= getExternalPrivatePath(walletDirName);
    //钱包密码
    String password="12345678";

    //钱包文件名称
    String walletFileName="myWallet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        boolean success=MyWalletUtil.createWalletFile(walletFileName,walletFilePath,password);
        if(success){
            //本地钱包注册成功后，向服务器后台注册
            new Thread(new Runnable() {
                @Override
                public void run() {
                    register2Server();
                }
            }).start();
        }else {
            Toast.makeText(this,"本地钱包注册失败",Toast.LENGTH_LONG).show();
        }
    }
    /**
     * 注册到服务
     */
    private void register2Server(){
        RegisterUserWrap userWrap= generateRegisterInfo();
        String json= GsonUtil.objectToJson(userWrap,RegisterUserWrap.class);
        try {
            //加载注册申请合约
            RegisterApplyManager userManager=(RegisterApplyManager)ContractManager.getInstance().getContractByFullName(RegisterApplyManager.class.getName());
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
        boolean success=MyWalletUtil.validataPasswdAndInit(walletFilePath,password,walletFileName);
        if(success){
            //登录成功
            Toast.makeText(this,"登录成功",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this,SendTransactionActivity.class);
            startActivity(intent);
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
    private RegisterUserWrap generateRegisterInfo() {
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

        info.setName("zhangsan");
        info.setMobile("1365985665");
        info.setEmail("juzix@163.com");
        info.setOrgId("0xf4a60bfffcefaf01cd560a1c65b83d3859de154d55463de690da92df41949e63");
        info.setOrgName("椭圆组织");

        infoWrap.setApplyId(UUID.randomUUID().toString());
        infoWrap.setRegisterUser(info);
        return infoWrap;

    }
}
