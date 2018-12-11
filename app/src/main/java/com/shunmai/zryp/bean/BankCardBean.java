package com.shunmai.zryp.bean;

/**
 * Created by yushengyang.
 * Date: 2018/12/6.
 */

public class BankCardBean {


    /**
     * id : 15598
     * cardNumber : 123456
     * cardBank : 光大银行
     * bankCardAccount : 三毛
     */

    private int id;
    private String cardNumber;
    private String cardBank;
    private String bankCardAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardBank() {
        return cardBank;
    }

    public void setCardBank(String cardBank) {
        this.cardBank = cardBank;
    }

    public String getBankCardAccount() {
        return bankCardAccount;
    }

    public void setBankCardAccount(String bankCardAccount) {
        this.bankCardAccount = bankCardAccount;
    }
}
