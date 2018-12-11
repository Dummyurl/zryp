package com.shunmai.zryp.bean.goods;

/**
 * Created by yushengyang.
 * Date: 2018/12/6.
 */

public class RestBean {

    /**
     * amount : 0.92
     * leastAmount : 5
     * serviceFee : 1
     */

    private double amount;
    private double leastAmount;
    private double serviceFee;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getLeastAmount() {
        return leastAmount;
    }

    public void setLeastAmount(int leastAmount) {
        this.leastAmount = leastAmount;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(int serviceFee) {
        this.serviceFee = serviceFee;
    }
}
