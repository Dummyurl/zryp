package com.shunmai.zryp.bean.goods;

/**
 * Created by yushengyang.
 * Date: 2018/11/2.
 */

public class OderInfoBean {


    /**
     * Error :
     * AppID : wx07956442fa24c563
     * TimeStamp : 1540949677444
     * NonceStr : 0c6378d65e70912b58162b91573ee0bd
     * PackAge : prepay_id=wx310934341216763d9ede1b853699457864
     * SignType : MD5
     * PaySign : D5BFA1D1F94363ADD205AD0BC86D7CB6
     * OID : 322534461709
     */

    private String Error;
    private String AppID;
    private String TimeStamp;
    private String NonceStr;
    private String PackAge;
    private String SignType;
    private String PaySign;
    private long OID;
    private String PrepayId;
    private String PartnerId;

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }

    public String getAppID() {
        return AppID;
    }

    public void setAppID(String AppID) {
        this.AppID = AppID;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String TimeStamp) {
        this.TimeStamp = TimeStamp;
    }

    public String getNonceStr() {
        return NonceStr;
    }

    public void setNonceStr(String NonceStr) {
        this.NonceStr = NonceStr;
    }

    public String getPackAge() {
        return PackAge;
    }

    public void setPackAge(String PackAge) {
        this.PackAge = PackAge;
    }

    public String getSignType() {
        return SignType;
    }

    public void setSignType(String SignType) {
        this.SignType = SignType;
    }

    public String getPaySign() {
        return PaySign;
    }

    public void setPaySign(String PaySign) {
        this.PaySign = PaySign;
    }

    public long getOID() {
        return OID;
    }

    public void setOID(long OID) {
        this.OID = OID;
    }

    public String getPrepayId() {
        return PrepayId;
    }

    public void setPrepayId(String prepayId) {
        PrepayId = prepayId;
    }

    public String getPartnerId() {
        return PartnerId;
    }

    public void setPartnerId(String partnerId) {
        PartnerId = partnerId;
    }
}

