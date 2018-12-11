package com.shunmai.zryp.bean.wallet;

/**
 * Created by yushengyang.
 * Date: 2018/12/11.
 */

public class ScoreDataBean {

    /**
     * total : 110247.64
     * minExchangeNum : 60
     * rateNum : 11
     */

    private double total;
    private int minExchangeNum;
    private int rateNum;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getMinExchangeNum() {
        return minExchangeNum;
    }

    public void setMinExchangeNum(int minExchangeNum) {
        this.minExchangeNum = minExchangeNum;
    }

    public int getRateNum() {
        return rateNum;
    }

    public void setRateNum(int rateNum) {
        this.rateNum = rateNum;
    }
}
