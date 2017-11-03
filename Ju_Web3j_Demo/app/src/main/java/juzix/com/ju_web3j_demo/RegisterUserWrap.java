package juzix.com.ju_web3j_demo;

/**
 * Created by 徐敏 on 2017/8/23.
 * 注册信息的封装类
 */

public class RegisterUserWrap {

    private RegisterInfo registerUser;
    private String applyId;
    private int auditStatus;
    public RegisterInfo getRegisterUser() {
        return registerUser;
    }

    public void setRegisterUser(RegisterInfo registerUser) {
        this.registerUser = registerUser;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }
}
