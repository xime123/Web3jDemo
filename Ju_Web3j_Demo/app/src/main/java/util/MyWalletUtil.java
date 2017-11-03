package util;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.ObjectMapperFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by 徐敏 on 2017/10/31.
 */

public class MyWalletUtil {
    private static Credentials mCredentials;
    private static final String TAG =MyWalletUtil.class.getName() ;
    static ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
    /**
     * 创建钱包文件
     * @param walletFileDir 存放钱包文件的路径，不含文件名
     * @param userName 用户名
     * @return 生成是否成功
     * @throws Exception the exception
     */

    public static boolean createWalletFile(String userName,String walletFileDir,String password) {
        ECKeyPair ecKeyPair = null;
        WalletFile walletFile = null;
        try {
            ecKeyPair = Keys.createEcKeyPair();
            walletFile = Wallet.create(password, ecKeyPair, 16, 1); // WalletUtils. .generateNewWalletFile();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        //用户名作为钱包文件名
        String fileName= getFileName( userName);


        File destination = new File(walletFileDir, fileName);

        //目录不存在则创建目录，创建不了则报错
        if (!createParentDir(destination)) {
            return false;
        }
        try {
            objectMapper.writeValue(destination, walletFile);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        //加入到instance中
        mCredentials=Credentials.create(ecKeyPair);
        return true;
    }

    public static Credentials getmCredentials() {
        return mCredentials;
    }

    /**
     * 校验用户对应的密码是否输入正确
     *
     * @param walletFileDir
     * @param password
     * @param walletFileName        用户id,其实就是本地钱包文件名
     * @return
     */
    public static boolean validataPasswdAndInit(String walletFileDir, String password, String walletFileName) {
        try {
            String fileName = getFileName(walletFileName);
            Credentials credentials = WalletUtils.loadCredentials(password, pathGet(walletFileDir, fileName));
            mCredentials=credentials;
            return true;
        } catch (IOException e) {
            Log.e(TAG,"===IOException===" + e.getMessage());
            return false;
        } catch (CipherException e) {
            //如正确钱包文件会正常解密,否则抛出异常
            Log.e(TAG,"===CipherException===" + e.getMessage());
            return false;
        }catch (Exception e){
            Log.e(TAG,"===CipherException===" + e.getMessage());
            return false;
        }
    }

    private static String pathGet(String dir, String s) {
        return dir + "/" + s;
    }
    /**
     * 根据userId生成文件名， 0x3750bd117e8da50c239d0945b9642ed865fd9213
     *
     * @param userId
     * @return
     */
    public static String getFileName(String userId) {
        String fileName = userId + ".json";
        return fileName;
    }

    private static boolean createParentDir(File file) {
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            System.out.println("目标文件所在目录不存在，准备创建");
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        return true;
    }
}
