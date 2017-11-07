package juzix.com.web3jdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
//                    ContractLoaderManager manager=new ContractLoaderManager();
//                    manager.registerContract();
                }catch (Exception e){
                    //异常逻辑处理......
                    e.printStackTrace();

                }
            }
        }).start();
    }


}
