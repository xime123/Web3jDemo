package juzix.com.ju_web3j_demo;

import org.web3j.tx.Contract;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 徐敏 on 2017/8/24.
 */

public class ContractManager {
    private static final  ContractManager instance =new ContractManager();
    private static Map<String,Contract> _contarctMap=new HashMap<>();

    private ContractLoaderManager contractLoader;


    private ContractManager (){

    }
    public static ContractManager getInstance(){
        return instance;
    }

    /**
     * 初始化一个合约加载器
     */
    public void initLoader(){
        if(contractLoader==null) {
            contractLoader = new ContractLoaderManager();
        }
    }
    /**
     * 1,切换链
     * 2，退出登录
     * 切换链和退出登录切换账户了一定要重置
     */
    public void  reset(){
        contractLoader=null;
        _contarctMap.clear();
    }



    /**
     * 通过合约名获取合约
     * @param contractFullName 合约类全名
     * @return
     */
    public Contract getContractByFullName(String contractFullName){
        initLoader();
        Class clazz = null;
        Contract manager=null;
        try {
            clazz = Class.forName(contractFullName);
            manager= _contarctMap.get(contractFullName);
            if(manager==null){
                manager= (Contract)contractLoader.loadContract(clazz.getSimpleName(),"SystemModuleManager",clazz);
                _contarctMap.put(contractFullName,manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return manager;
    }
}
