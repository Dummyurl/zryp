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
    private int gender;
    private String pic;
    //可用积分
    private double cashbscore;
    //总积分
    private double totalbscore;
    //冻结积分
    private double frozenBscore;
    //是否可以升级
    private boolean canUpgrade;
    //有效邀请人
    private int validUserAgent;
    //个人消费金额
    private double personalConsumeAgent;
    //团队消费金额
    private double teamConsumeAgent;
    //我推荐的人
    private int meInvitedPeople;
    //是否有认证
    private boolean isAuthenticated;
    //升级百分比
    private double upgradePercent;
    //我的推荐人
    private String inviteMe;
    //我的邀请码
    private String inviteCode;


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

    public boolean isCanUpgrade() {
        return canUpgrade;
    }

    public void setCanUpgrade(boolean canUpgrade) {
        this.canUpgrade = canUpgrade;
    }

    public int getValidUserAgent() {
        return validUserAgent;
    }

    public void setValidUserAgent(int validUserAgent) {
        this.validUserAgent = validUserAgent;
    }

    public double getPersonalConsumeAgent() {
        return personalConsumeAgent;
    }

    public void setPersonalConsumeAgent(double personalConsumeAgent) {
        this.personalConsumeAgent = personalConsumeAgent;
    }

    public double getTeamConsumeAgent() {
        return teamConsumeAgent;
    }

    public void setTeamConsumeAgent(double teamConsumeAgent) {
        this.teamConsumeAgent = teamConsumeAgent;
    }

    public int getMeInvitedPeople() {
        return meInvitedPeople;
    }

    public void setMeInvitedPeople(int meInvitedPeople) {
        this.meInvitedPeople = meInvitedPeople;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public int getUpgradePercent() {
        return (int) upgradePercent;
    }

    public void setUpgradePercent(double upgradePercent) {
        this.upgradePercent = upgradePercent;
    }

    public String getInviteMe() {
        return inviteMe;
    }

    public void setInviteMe(String inviteMe) {
        this.inviteMe = inviteMe;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
