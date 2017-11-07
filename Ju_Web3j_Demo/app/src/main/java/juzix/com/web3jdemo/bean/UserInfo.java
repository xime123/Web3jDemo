package juzix.com.web3jdemo.bean;

/**
 * Created by 徐敏 on 2017/8/18.
 * address    userAddr;
 string     name;
 string     account;
 string     email;
 string     mobile;
 string     departmentId;
 uint       accountStatus;
 uint       passwordStatus;          // -
 uint       deleteStatus;            // -
 string     uuid;
 string     publicKey;
 string     cipherGroupKey;
 uint       createTime;
 uint       updateTime;
 uint       loginTime;                // -
 string[]   roleIdList;
 UserState  state;                  // 标记用户数据是否有效
 uint       status;                 // 是否禁用用户：0 禁用 1 激活
 address    ownerAddr;              // 用户真实地址
 uint       certType;               // 证书类型 1 文件证书 2 U-KEY证书
 string     remark;
 string     icon;                   // icon base64编码
 */

public class UserInfo {
    private String userAddr;
    private String name;
    private String account;
    private String email;
    private String mobile;
    private String departmentId;
    private int accountStatus;
    private int passwordStatus;
    private int  deleteStatus;
    private int status;
    private String uuid;
    private String publicKey;
    private String ownerAddr;
    private int certType;
    private String remark;
    private String icon;
    /**自定义字段*/
    private String privateKey;

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getPasswordStatus() {
        return passwordStatus;
    }

    public void setPasswordStatus(int passwordStatus) {
        this.passwordStatus = passwordStatus;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getOwnerAddr() {
        return ownerAddr;
    }

    public void setOwnerAddr(String ownerAddr) {
        this.ownerAddr = ownerAddr;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
