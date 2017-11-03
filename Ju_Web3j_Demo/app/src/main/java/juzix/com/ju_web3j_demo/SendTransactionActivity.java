package juzix.com.ju_web3j_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SendTransactionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_transaction);
    }


    private void testPrivate(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ContractLoaderManager manager=new ContractLoaderManager();
                    manager.registerContract();
                }catch (Exception e){
                    //异常逻辑处理......
                    e.printStackTrace();

                }
            }
        }).start();
    }

    /**
     * 调用合约方法查找用户信息
     * @param view
     */
    public void getUserInfo(View view) {
        testPrivate();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    //加载用户合约
//                   UserManager userManager=( UserManager)ContractManager.getInstance().getContractByFullName(UserManager.class.getName());
//                   //使用注册时，给用户设置的uuid
//                    Utf8String uuidUtf=new Utf8String("myWallet");
//                    //调用合约方法，得到用户信息的json数据
//                    //新注册的用户需要审核后才能查找到用户信息
//                    Utf8String result= userManager.findByUuid(uuidUtf).get();
//                    String jsonStr=result.toString();
//                }catch (Exception e){
//                    //异常逻辑处理......
//                    e.printStackTrace();
//
//                }
//            }
//        }).start();
    }
}
