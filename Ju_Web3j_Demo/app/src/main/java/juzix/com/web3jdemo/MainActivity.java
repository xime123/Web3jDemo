package juzix.com.web3jdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import web3j.loader.ContractManager;
import web3j.ukey.manager.UKeyManager;

public class MainActivity extends AppCompatActivity {
    public static String DEFAULT_MOULE_NAME="SystemModuleManager";
    public static String DEFAULT_MOULE_VERSION="0.0.1.0";
    public static String DEFAULT_CONTRACT_VERSION="0.0.1.0";
    public static String DEFAULT_URL="http://112.64.159.162:6789";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configLoader();
        UKeyManager.getInstance().init(this);
    }

    private void  configLoader(){
        ContractManager.getInstance()
            //配置模块名称
            .moudleName(DEFAULT_MOULE_NAME)
            //配置模块版本
            .moudleVersion(DEFAULT_MOULE_VERSION)
            //配置合约版本
            .contractVersion(DEFAULT_CONTRACT_VERSION)
            //配置节点地址
            .rpcUrl(DEFAULT_URL);
    }


    public void toUkey(View view) {
        Intent intent=new Intent(this,UkeyActivity.class);
        startActivity(intent);
    }

    public void toWallet(View view) {
        Intent intent=new Intent(this,WalletFileActivity.class);
        startActivity(intent);
    }


}
