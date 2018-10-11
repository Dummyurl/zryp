package com.shunmai.zryp.bean;

/**
 * Created by yushengyang.
 * Date: 2018/9/3.
 */

public class GoodsBean {
    private String goodsName;
    private String goodsImg;
    private long sysId;

    public GoodsBean(String goodsName, String goodsImg, long sysId) {
        this.goodsName = goodsName;
        this.goodsImg = goodsImg;
        this.sysId = sysId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public long getSysId() {
        return sysId;
    }

    public void setSysId(long sysId) {
        this.sysId = sysId;
    }
}
