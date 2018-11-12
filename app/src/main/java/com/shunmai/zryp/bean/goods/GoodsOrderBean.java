package com.shunmai.zryp.bean.goods;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shunmai.zryp.BR;

/**
 * Created by yushengyang.
 * Date: 2018/11/1.
 */

public class GoodsOrderBean extends BaseObservable {
    private String goodsName;
    private double goodsPrice;
    @Bindable
    private int buyCount;
    private String imageUrl;
    private String skuDescription;
    private long goodsId;
    private int skuId;
    private long oid;
    private int addrId;
    private int goodsPropery;
    private int isOutAddress;

    public int getAddrId() {
        return addrId;
    }

    public void setAddrId(int addrId) {
        this.addrId = addrId;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
        notifyPropertyChanged(BR.buyCount);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSkuDescription() {
        return skuDescription;
    }

    public void setSkuDescription(String skuDescription) {
        this.skuDescription = skuDescription;
    }

    public int getGoodsPropery() {
        return goodsPropery;
    }

    public void setGoodsPropery(int goodsPropery) {
        this.goodsPropery = goodsPropery;
    }

    public int getIsOutAddress() {
        return isOutAddress;
    }

    public void setIsOutAddress(int isOutAddress) {
        this.isOutAddress = isOutAddress;
    }
}
