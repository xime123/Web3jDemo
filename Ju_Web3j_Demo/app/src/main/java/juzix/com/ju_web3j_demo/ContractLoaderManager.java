package juzix.com.ju_web3j_demo;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

import contract.RegisterManager;
import contract.UserManager;
import util.MyWalletUtil;
import web3j.PrivateTransactionManager;
import web3j.protocol.CustomerWeb3j;
import web3j.protocol.CustomerWeb3jFactory;



/**
 * Created by 徐敏 on 2017/8/23.
 * 合约加载器
 */

public class ContractLoaderManager {

    private BigInteger gasPrice = new BigInteger("21000000000");
    private BigInteger gasLimitBig = new BigInteger("999999999999");
    private Web3j web3j;
    private TransactionManager transactionManager;
    private RegisterManager registerManager;
    private String contractAddr;


    /** 构造用于获取合约地址的registerManager
     *

     */
    public ContractLoaderManager() {
        //注册中心合约地址
        final String RegisterAddr = "0x0000000000000000000000000000000000000011";
        //构造一个用于发送json-rpc请求的HttpService
       // String url="http://112.64.159.162:6710";//演示环境
        String url="http://192.168.7.150:6789";//taoge
        HttpService httpService = new HttpService(url);
        //创建web3j
        web3j = CustomerWeb3jFactory.buildweb3j(httpService);
        //获取钱包信用对象
        Credentials credentials= MyWalletUtil.getmCredentials();
        //加载注册中心合约
        this.registerManager = RegisterManager.load(RegisterAddr, web3j, credentials, gasPrice, gasLimitBig);
        //构造一个用于打包签名的交易管理对象（Nonce改造后的TransactionManager），并设置发送json-rpc请求的重试次数和间隔时间
        this.transactionManager = new PrivateTransactionManager((CustomerWeb3j) web3j, credentials,100, 100);

    }

    public Object loadContract(String name, String moduleName, Class c) throws ExecutionException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //通过合约名和模块名获取合约地址
        Address contractAddr = (Address)registerManager.getContractAddress(new Utf8String(moduleName),new Utf8String("0.0.1.0"),new Utf8String(name), new Utf8String("0.0.1.0")).get();
        //通过反射执行加载合约方法 得到合约的实力对象并返回
        Method setAttemptsAndSleepDuration = c.getDeclaredMethod("load", new Class[]{String.class, Web3j.class, TransactionManager.class, BigInteger.class, BigInteger.class});
        Object contractManager = setAttemptsAndSleepDuration.invoke((Object)null, new Object[]{contractAddr.toString(), this.web3j, this.transactionManager, gasPrice, gasLimitBig});
        return contractManager;
    }

    public UserManager loadUserManagerContract(String name, String moduleName, Class c) throws ExecutionException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //通过合约名和模块名获取合约地址
        Address contractAddr = (Address)registerManager.getContractAddress(new Utf8String(moduleName),new Utf8String("0.0.1.0"),new Utf8String(name), new Utf8String("0.0.1.0")).get();
        UserManager userManager=UserManager.load(contractAddr.toString(),this.web3j,this.transactionManager,gasPrice,gasLimitBig);
        return userManager;
    }

    public void registerContract(){
        try {
            TransactionReceipt receipt= registerManager.register(new Utf8String("SystemModuleManager"),new Utf8String("0.0.1.0"),new Utf8String("UserManager"),new Utf8String("0.0.1.0")).get();
            //获取应答
            String blockHash=receipt.getBlockHash();
            System.out.print("blockHash="+blockHash);
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
