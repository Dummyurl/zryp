package com.shunmai.zryp.bean;

/**
 * Created by yushengyang.
 * Date: 2018/10/18.
 */

public class UserInfoBean {
    private int userId;
    private String mobile;
    private int userType;
    private String sysIdString;
    private String logonAccount;
    private String nickname;
    private String unionId;
    private String pic;
    private double cashbscore;
    private double totalbscore;
    private double frozenBscore;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getSysIdString() {
        return sysIdString;
    }

    public void setSysIdString(String sysIdString) {
        this.sysIdString = sysIdString;
    }

    public String getLogonAccount() {
        return logonAccount;
    }

    public void setLogonAccount(String logonAccount) {
        this.logonAccount = logonAccount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public double getCashbscore() {
        return cashbscore;
    }

    public void setCashbscore(double cashbscore) {
        this.cashbscore = cashbscore;
    }

    public double getTotalbscore() {
        return totalbscore;
    }

    public void setTotalbscore(double totalbscore) {
        this.totalbscore = totalbscore;
    }

    public double getFrozenBscore() {
        return frozenBscore;
    }

    public void setFrozenBscore(double frozenBscore) {
        this.frozenBscore = frozenBscore;
    }
}
