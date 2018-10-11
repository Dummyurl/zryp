package com.shunmai.zryp.bean.goods;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shunmai.zryp.zrypapp.BR;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/11.
 */

public class GoodsBean extends BaseObservable {

    /**
     * goodsId : 3
     * channelId : 0
     * platgoodsId : 12345678ghgs
     * goodsName : 踏步
     * goodsNote : 牛皮匹
     * goodsType : 1
     * catlaogMobileId : 11090961178431029
     * brandId : 0
     * price : 0.0
     * marketPrice : 0.0
     * mscore : 0.0
     * weight : 0.0
     * keywords : 关键字
     * isFronthidden : false
     * defalutPhotourl : https://f12.baidu.com/it/u=517802282,3228289198&amp;fm=76
     * sysSpaceNo : null
     * sysIdString : 10754164640583680
     * description : null
     * freightMode : 1
     * goodsTitle : null
     * appDetails : null
     * miniDetails : null
     * seekGoodsImgsVOS : null
     */
    private double price;
    private String defalutPhotourl;
    private double marketPrice;
    private long sysIdString;
    private String goodsName;
    private String goodsTitle;
    private String platgoodsId;
    private int goodsPropery;

    @Bindable
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        notifyPropertyChanged(BR.goodsName);
    }



    @Bindable
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyPropertyChanged(BR.price);
    }

    @Bindable
    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
        notifyPropertyChanged(BR.marketPrice);
    }



    public String getDefalutPhotourl() {
        return defalutPhotourl;
    }

    public void setDefalutPhotourl(String defalutPhotourl) {
        this.defalutPhotourl = defalutPhotourl;
    }

    public long getSysIdString() {
        return sysIdString;
    }

    public void setSysIdString(long sysIdString) {
        this.sysIdString = sysIdString;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getPlatgoodsId() {
        return platgoodsId;
    }

    public void setPlatgoodsId(String platgoodsId) {
        this.platgoodsId = platgoodsId;
    }

    public int getGoodsPropery() {
        return goodsPropery;
    }

    public void setGoodsPropery(int goodsPropery) {
        this.goodsPropery = goodsPropery;
    }
}