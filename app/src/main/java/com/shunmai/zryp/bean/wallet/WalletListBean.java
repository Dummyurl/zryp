package com.shunmai.zryp.bean.wallet;

/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class WalletListBean {

    /**
     * stateDisplay : 审核通过，已打款
     * wid : 201811221703103996
     * userId : 134321
     * amout : 0.01
     * overAmount : 8.99
     * serviceAmount : 1
     * state : 1   //状态：0 审核中; -1 审核未通过; 1 审核通过，已打款; 2 打款成功，已到账; -2 打款失败;
     * sysCreateTime : 2018-11-22 17:03:10
     * sysCreateUser : 134321
     * auditAdmin : admin
     * bankCard : 6226220137981568
     * bankCardAccount : 谭光洪
     * bankName : 民生银行
     * bankCode : 1006
     */

    private String stateDisplay;
    private String wid;
    private int userId;
    private double amout;
    private double overAmount;
    private int serviceAmount;
    private int state;
    private String sysCreateTime;
    private String sysCreateUser;
    private String auditAdmin;
    private String bankCard;
    private String bankCardAccount;
    private String bankName;
    private String bankCode;

    public String getStateDisplay() {
        return stateDisplay;
    }

    public void setStateDisplay(String stateDisplay) {
        this.stateDisplay = stateDisplay;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmout() {
        return amout;
    }

    public void setAmout(double amout) {
        this.amout = amout;
    }

    public double getOverAmount() {
        return overAmount;
    }

    public void setOverAmount(double overAmount) {
        this.overAmount = overAmount;
    }

    public int getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(int serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(String sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public String getSysCreateUser() {
        return sysCreateUser;
    }

    public void setSysCreateUser(String sysCreateUser) {
        this.sysCreateUser = sysCreateUser;
    }

    public String getAuditAdmin() {
        return auditAdmin;
    }

    public void setAuditAdmin(String auditAdmin) {
        this.auditAdmin = auditAdmin;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankCardAccount() {
        return bankCardAccount;
    }

    public void setBankCardAccount(String bankCardAccount) {
        this.bankCardAccount = bankCardAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
