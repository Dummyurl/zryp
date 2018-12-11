package com.shunmai.zryp.bean.userinfo;

import android.graphics.Color;

/**
 * Created by yushengyang.
 * Date: 2018/12/4.
 */

public class VipGuideIndexBean  {

    /**
     * dayIncome : 0
     * monthIncome : 0
     * grandTotalIncome : 0
     * canWithdrawMoney : 0
     * myReferrer : 0
     * directReferrer : 0
     * teamVipTotalPeople : 0
     * dayAddVipPeople : 0
     * monthAddVipPeople : 0
     * teamTotalPeople : 0
     * directDirectorPeople : 0
     * teamDirectorPeople : 0
     * monthAddDirectorPeople : 0
     * uId : 20649
     * pic :
     * wxValue : test-1
     * upgradeProgress : 0
     * cashbScore : 0
     * currentUserType : 2
     * currentUserTypeStr : 总监
     * nextUserType : 1
     * nextUserTypeStr : 总经理
     * isBuyPurchasePackage : false
     * isWithdrawalInformation : false
     * vipUpgradeProgress : {"directlyVipCnt":1,"teamVipCount":2,"directorIntegral":500,"directlyVipCntConfig":1,"teamVipCountConfig":2,"directorIntegralConfig":500}
     * directorUpgradeProgress : {"vipCntExceptDirectly":1,"teamManagerCnt":1,"takeoutIntegral":2,"heirIntegral":1,"vipCntExceptDirectlyConfig":1,"teamManagerCntConfig":1,"totalVipNumConfig":1,"takeoutIntegralConfig":2,"heirIntegralConfig":1}
     */

    private double dayIncome;
    private double monthIncome;
    private double grandTotalIncome;
    private double canWithdrawMoney;
    private int myReferrer;
    private int directReferrer;
    private int teamVipTotalPeople;
    private int dayAddVipPeople;
    private int monthAddVipPeople;
    private int teamTotalPeople;
    private int directDirectorPeople;
    private int teamDirectorPeople;
    private int monthAddDirectorPeople;
    private int uId;
    private String pic;
    private String wxValue;
    private double upgradeProgress;
    private int progress;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public boolean isBuyPurchasePackage() {
        return isBuyPurchasePackage;
    }

    public void setBuyPurchasePackage(boolean buyPurchasePackage) {
        isBuyPurchasePackage = buyPurchasePackage;
    }

    public boolean isWithdrawalInformation() {
        return isWithdrawalInformation;
    }

    public void setWithdrawalInformation(boolean withdrawalInformation) {
        isWithdrawalInformation = withdrawalInformation;
    }

    private double cashbScore;
    private int currentUserType;
    private String currentUserTypeStr;
    private int nextUserType;
    private String nextUserTypeStr;
    private boolean isBuyPurchasePackage;
    private boolean isWithdrawalInformation;
    private VipUpgradeProgressBean vipUpgradeProgress;
    private DirectorUpgradeProgressBean directorUpgradeProgress;

    public double getDayIncome() {
        return dayIncome;
    }

    public void setDayIncome(double dayIncome) {
        this.dayIncome = dayIncome;
    }

    public double getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(double monthIncome) {
        this.monthIncome = monthIncome;
    }

    public double getGrandTotalIncome() {
        return grandTotalIncome;
    }

    public void setGrandTotalIncome(double grandTotalIncome) {
        this.grandTotalIncome = grandTotalIncome;
    }

    public double getCanWithdrawMoney() {
        return canWithdrawMoney;
    }

    public void setCanWithdrawMoney(double canWithdrawMoney) {
        this.canWithdrawMoney = canWithdrawMoney;
    }

    public int getMyReferrer() {
        return myReferrer;
    }

    public void setMyReferrer(int myReferrer) {
        this.myReferrer = myReferrer;
    }

    public int getDirectReferrer() {
        return directReferrer;
    }

    public void setDirectReferrer(int directReferrer) {
        this.directReferrer = directReferrer;
    }

    public int getTeamVipTotalPeople() {
        return teamVipTotalPeople;
    }

    public void setTeamVipTotalPeople(int teamVipTotalPeople) {
        this.teamVipTotalPeople = teamVipTotalPeople;
    }

    public int getDayAddVipPeople() {
        return dayAddVipPeople;
    }

    public void setDayAddVipPeople(int dayAddVipPeople) {
        this.dayAddVipPeople = dayAddVipPeople;
    }

    public int getMonthAddVipPeople() {
        return monthAddVipPeople;
    }

    public void setMonthAddVipPeople(int monthAddVipPeople) {
        this.monthAddVipPeople = monthAddVipPeople;
    }

    public int getTeamTotalPeople() {
        return teamTotalPeople;
    }

    public void setTeamTotalPeople(int teamTotalPeople) {
        this.teamTotalPeople = teamTotalPeople;
    }

    public int getDirectDirectorPeople() {
        return directDirectorPeople;
    }

    public void setDirectDirectorPeople(int directDirectorPeople) {
        this.directDirectorPeople = directDirectorPeople;
    }

    public int getTeamDirectorPeople() {
        return teamDirectorPeople;
    }

    public void setTeamDirectorPeople(int teamDirectorPeople) {
        this.teamDirectorPeople = teamDirectorPeople;
    }

    public int getMonthAddDirectorPeople() {
        return monthAddDirectorPeople;
    }

    public void setMonthAddDirectorPeople(int monthAddDirectorPeople) {
        this.monthAddDirectorPeople = monthAddDirectorPeople;
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getWxValue() {
        return wxValue;
    }

    public void setWxValue(String wxValue) {
        this.wxValue = wxValue;
    }

    public double getUpgradeProgress() {
        return upgradeProgress;
    }

    public void setUpgradeProgress(double upgradeProgress) {
        this.upgradeProgress = upgradeProgress;
    }

    public double getCashbScore() {
        return cashbScore;
    }

    public void setCashbScore(double cashbScore) {
        this.cashbScore = cashbScore;
    }

    public int getCurrentUserType() {
        return currentUserType;
    }

    public void setCurrentUserType(int currentUserType) {
        this.currentUserType = currentUserType;
    }

    public String getCurrentUserTypeStr() {
        return currentUserTypeStr;
    }

    public void setCurrentUserTypeStr(String currentUserTypeStr) {
        this.currentUserTypeStr = currentUserTypeStr;
    }

    public int getNextUserType() {
        return nextUserType;
    }

    public void setNextUserType(int nextUserType) {
        this.nextUserType = nextUserType;
    }

    public String getNextUserTypeStr() {
        return nextUserTypeStr;
    }

    public void setNextUserTypeStr(String nextUserTypeStr) {
        this.nextUserTypeStr = nextUserTypeStr;
    }

    public boolean isIsBuyPurchasePackage() {
        return isBuyPurchasePackage;
    }

    public void setIsBuyPurchasePackage(boolean isBuyPurchasePackage) {
        this.isBuyPurchasePackage = isBuyPurchasePackage;
    }

    public boolean isIsWithdrawalInformation() {
        return isWithdrawalInformation;
    }

    public void setIsWithdrawalInformation(boolean isWithdrawalInformation) {
        this.isWithdrawalInformation = isWithdrawalInformation;
    }

    public VipUpgradeProgressBean getVipUpgradeProgress() {
        return vipUpgradeProgress;
    }

    public void setVipUpgradeProgress(VipUpgradeProgressBean vipUpgradeProgress) {
        this.vipUpgradeProgress = vipUpgradeProgress;
    }

    public DirectorUpgradeProgressBean getDirectorUpgradeProgress() {
        return directorUpgradeProgress;
    }

    public void setDirectorUpgradeProgress(DirectorUpgradeProgressBean directorUpgradeProgress) {
        this.directorUpgradeProgress = directorUpgradeProgress;
    }

    public static class VipUpgradeProgressBean {
        /**
         * directlyVipCnt : 1
         * teamVipCount : 2
         * directorIntegral : 500.0
         * directlyVipCntConfig : 1
         * teamVipCountConfig : 2
         * directorIntegralConfig : 500.0
         */

        private double directlyVipCnt;
        private double teamVipCount;
        private double directorIntegral;
        private int directlyVipCntConfig;
        private int teamVipCountConfig;
        private int directorIntegralConfig;

        public int getDirectlyVipCnt() {
            return (int) (directlyVipCnt*100);
        }

        public void setDirectlyVipCnt(double directlyVipCnt) {
            this.directlyVipCnt = directlyVipCnt;
        }

        public int getTeamVipCount() {
            return (int) (teamVipCount*100);
        }

        public void setTeamVipCount(double teamVipCount) {
            this.teamVipCount = teamVipCount;
        }

        public int getDirectorIntegral() {
            return (int) (directorIntegral*100);
        }

        public void setDirectorIntegral(double directorIntegral) {
            this.directorIntegral = directorIntegral;
        }

        public int getDirectlyVipCntConfig() {
            return directlyVipCntConfig;
        }

        public void setDirectlyVipCntConfig(int directlyVipCntConfig) {
            this.directlyVipCntConfig = directlyVipCntConfig;
        }

        public int getTeamVipCountConfig() {
            return teamVipCountConfig;
        }

        public void setTeamVipCountConfig(int teamVipCountConfig) {
            this.teamVipCountConfig = teamVipCountConfig;
        }

        public int getDirectorIntegralConfig() {
            return directorIntegralConfig;
        }

        public void setDirectorIntegralConfig(int directorIntegralConfig) {
            this.directorIntegralConfig = directorIntegralConfig;
        }
    }

    public static class DirectorUpgradeProgressBean {
        /**
         * vipCntExceptDirectly : 1
         * teamManagerCnt : 1
         * takeoutIntegral : 2
         * heirIntegral : 1
         * vipCntExceptDirectlyConfig : 1
         * teamManagerCntConfig : 1
         * totalVipNumConfig : 1
         * takeoutIntegralConfig : 2
         * heirIntegralConfig : 1
         */

        private double vipCntExceptDirectly;
        private double teamManagerCnt;
        private double takeoutIntegral;
        private double heirIntegral;

        private int vipCntExceptDirectlyConfig;
        private int teamManagerCntConfig;
        private int totalVipNumConfig;
        private int takeoutIntegralConfig;
        private int heirIntegralConfig;

        public int getVipCntExceptDirectly() {
            return (int) (vipCntExceptDirectly*100);
        }

        public void setVipCntExceptDirectly(double vipCntExceptDirectly) {
            this.vipCntExceptDirectly = vipCntExceptDirectly;
        }

        public int getTeamManagerCnt() {
            return (int) (teamManagerCnt*100);
        }

        public void setTeamManagerCnt(double teamManagerCnt) {
            this.teamManagerCnt = teamManagerCnt;
        }

        public int getTakeoutIntegral() {
            return (int) (takeoutIntegral*100);
        }

        public void setTakeoutIntegral(double takeoutIntegral) {
            this.takeoutIntegral = takeoutIntegral;
        }

        public int getHeirIntegral() {
            return (int) (heirIntegral*100);
        }

        public void setHeirIntegral(double heirIntegral) {
            this.heirIntegral = heirIntegral;
        }

        public int getVipCntExceptDirectlyConfig() {
            return vipCntExceptDirectlyConfig;
        }

        public void setVipCntExceptDirectlyConfig(int vipCntExceptDirectlyConfig) {
            this.vipCntExceptDirectlyConfig = vipCntExceptDirectlyConfig;
        }

        public int getTeamManagerCntConfig() {
            return teamManagerCntConfig;
        }

        public void setTeamManagerCntConfig(int teamManagerCntConfig) {
            this.teamManagerCntConfig = teamManagerCntConfig;
        }

        public int getTotalVipNumConfig() {
            return totalVipNumConfig;
        }

        public void setTotalVipNumConfig(int totalVipNumConfig) {
            this.totalVipNumConfig = totalVipNumConfig;
        }

        public int getTakeoutIntegralConfig() {
            return takeoutIntegralConfig;
        }

        public void setTakeoutIntegralConfig(int takeoutIntegralConfig) {
            this.takeoutIntegralConfig = takeoutIntegralConfig;
        }

        public int getHeirIntegralConfig() {
            return heirIntegralConfig;
        }

        public void setHeirIntegralConfig(int heirIntegralConfig) {
            this.heirIntegralConfig = heirIntegralConfig;
        }
    }
}
