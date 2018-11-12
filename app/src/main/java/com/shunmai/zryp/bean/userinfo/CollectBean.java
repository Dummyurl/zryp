package com.shunmai.zryp.bean.userinfo;

/**
 * Created by yushengyang.
 * Date: 2018/11/10.
 */

public class CollectBean {

    /**
     * collectId : 3
     * goodsId : 105
     * skuId : 0
     * userId : 0
     * sys_create_time : 0001-01-01 00:00:00
     * isEnable : true
     * starNum : 2
     * goodsName : 一叶子天才面膜30片
     * keywords : 一叶子天才面膜30片
     * defalutPhotoURL : http://pic.gzcfe.net/brand/2018/0420/5021997715361457625.jpg
     * price : 152.9
     */

    private int collectId;
    private long goodsId;
    private int skuId;
    private int userId;
    private String sys_create_time;
    private boolean isEnable;
    private int starNum;
    private String goodsName;
    private String keywords;
    private String defalutPhotoURL;
    private double price;

    public int getCollectId() {
        return collectId;
    }

    public void setCollectId(int collectId) {
        this.collectId = collectId;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public int getSkuId() {
        return skuId;
    }

    public void setSkuId(int skuId) {
        this.skuId = skuId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSys_create_time() {
        return sys_create_time;
    }

    public void setSys_create_time(String sys_create_time) {
        this.sys_create_time = sys_create_time;
    }

    public boolean isIsEnable() {
        return isEnable;
    }

    public void setIsEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDefalutPhotoURL() {
        return defalutPhotoURL;
    }

    public void setDefalutPhotoURL(String defalutPhotoURL) {
        this.defalutPhotoURL = defalutPhotoURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
