package com.shunmai.zryp.bean;


import com.google.gson.annotations.SerializedName;

/**
 * Created by yushengyang.
 * Date: 2018/10/18.
 */

public class UserInfoBean {

    //    private int userid;



    /**
     * usertype : 3
     * pic : https://wx.qlogo.cn/mmopen/vi_32/eCbwpicNicpkBRvCiaCQ4LRdLpalArlH7XLPiayaI1fXoSKG0heuecicnAZzF9SiashaCwqZpvHrYhCeia3a1WLSCiaiaEA/132
     * realname :
     * nickname : 李
     * “cardBank” :
     * cardNumber :
     * bankCardAccount :
     * bankCode :
     * inviteCode : FJFwUKMJ
     * inviteMe : null
     * canUpgrade : false
     * upgradePercent : 0
     * validUserAgentPercent : 0
     * personalConsumeAgentPercent : 0
     * teamConsumeAgentPercent : 0
     * personalConsumeAgent : 72.5
     * teamConsumeAgent : 0
     * validUserAgent : 0
     * meInvitedPeople : 0
     * ruleValidUserAgent : 0
     * rulePersonalConsumeAgent : 0
     * ruleTeamConsumeAgent : 0
     * cashScore : 0
     * totalScore : 0
     * frozenScore : 0
     */
//    @SerializedName("userid")
    private int userId;
    @SerializedName("usertype")
    private int userType;
    private String pic;
    private String realname;
    private String nickname;
    private String cardBank;
    private String cardNumber;
    private String bankCardAccount;
    private String bankCode;
    private String inviteCode;
    private String usertypeName;
    private String cardId;
    private String inviteMe;
    private boolean canUpgrade;
    private int upgradePercent;
    private int validUserAgentPercent;
    private int personalConsumeAgentPercent;
    private int teamConsumeAgentPercent;
    private double personalConsumeAgent;
    private int teamConsumeAgent;
    private int validUserAgent;
    private int meInvitedPeople;
    private int ruleValidUserAgent;
    private int rulePersonalConsumeAgent;
    private int ruleTeamConsumeAgent;
    private int cashScore;
    private int totalScore;
    private int frozenScore;
    private boolean isVerification;
    private String mobile;
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankCardAccount() {
        return bankCardAccount;
    }

    public void setBankCardAccount(String bankCardAccount) {
        this.bankCardAccount = bankCardAccount;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }


    public boolean isCanUpgrade() {
        return canUpgrade;
    }

    public void setCanUpgrade(boolean canUpgrade) {
        this.canUpgrade = canUpgrade;
    }

    public int getUpgradePercent() {
        return upgradePercent;
    }

    public void setUpgradePercent(int upgradePercent) {
        this.upgradePercent = upgradePercent;
    }

    public int getValidUserAgentPercent() {
        return validUserAgentPercent;
    }

    public void setValidUserAgentPercent(int validUserAgentPercent) {
        this.validUserAgentPercent = validUserAgentPercent;
    }

    public int getPersonalConsumeAgentPercent() {
        return personalConsumeAgentPercent;
    }

    public void setPersonalConsumeAgentPercent(int personalConsumeAgentPercent) {
        this.personalConsumeAgentPercent = personalConsumeAgentPercent;
    }

    public int getTeamConsumeAgentPercent() {
        return teamConsumeAgentPercent;
    }

    public void setTeamConsumeAgentPercent(int teamConsumeAgentPercent) {
        this.teamConsumeAgentPercent = teamConsumeAgentPercent;
    }

    public double getPersonalConsumeAgent() {
        return personalConsumeAgent;
    }

    public void setPersonalConsumeAgent(double personalConsumeAgent) {
        this.personalConsumeAgent = personalConsumeAgent;
    }

    public int getTeamConsumeAgent() {
        return teamConsumeAgent;
    }

    public void setTeamConsumeAgent(int teamConsumeAgent) {
        this.teamConsumeAgent = teamConsumeAgent;
    }

    public int getValidUserAgent() {
        return validUserAgent;
    }

    public void setValidUserAgent(int validUserAgent) {
        this.validUserAgent = validUserAgent;
    }

    public int getMeInvitedPeople() {
        return meInvitedPeople;
    }

    public void setMeInvitedPeople(int meInvitedPeople) {
        this.meInvitedPeople = meInvitedPeople;
    }

    public int getRuleValidUserAgent() {
        return ruleValidUserAgent;
    }

    public void setRuleValidUserAgent(int ruleValidUserAgent) {
        this.ruleValidUserAgent = ruleValidUserAgent;
    }

    public int getRulePersonalConsumeAgent() {
        return rulePersonalConsumeAgent;
    }

    public void setRulePersonalConsumeAgent(int rulePersonalConsumeAgent) {
        this.rulePersonalConsumeAgent = rulePersonalConsumeAgent;
    }

    public int getRuleTeamConsumeAgent() {
        return ruleTeamConsumeAgent;
    }

    public void setRuleTeamConsumeAgent(int ruleTeamConsumeAgent) {
        this.ruleTeamConsumeAgent = ruleTeamConsumeAgent;
    }

    public int getCashScore() {
        return cashScore;
    }

    public void setCashScore(int cashScore) {
        this.cashScore = cashScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getFrozenScore() {
        return frozenScore;
    }

    public void setFrozenScore(int frozenScore) {
        this.frozenScore = frozenScore;
    }

    public int getUserId() {
        return userId;
    }

    public String getInviteMe() {
        return inviteMe;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public String getUsertypeName() {
        return usertypeName;
    }

    public void setUsertypeName(String usertypeName) {
        this.usertypeName = usertypeName;
    }

    public boolean isVerification() {
        return isVerification;
    }

    public void setVerification(boolean verification) {
        isVerification = verification;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setInviteMe(String inviteMe) {
        this.inviteMe = inviteMe;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountInfo(){
        return cardBank+" ("+cardNumber.substring(cardNumber.length()-4,cardNumber.length())+") "+realname;
    }
}
