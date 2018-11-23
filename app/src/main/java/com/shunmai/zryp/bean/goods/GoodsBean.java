package com.shunmai.zryp.bean.goods;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shunmai.zryp.BR;

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
    private String priceSection;
    private String mallPriceSection;
    private int goodsId;
    private double price;
    private int channelId;
    private String defalutPhotourl;
    private String defalutPhotoURL;
    private double marketPrice;
    private long sysIdString;
    private String goodsName;
    private String goodsTitle;
    private String miniDetails;
    private String platgoodsId;
    private int goodsPropery;
    private double mscore;
    private String description;
    private List<SeekGoodsImgsVOSBean> seekGoodsImgsVOS;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

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

    public double getMscore() {
        return mscore;
    }

    public void setMscore(double mscore) {
        this.mscore = mscore;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SeekGoodsImgsVOSBean> getSeekGoodsImgsVOS() {
        return seekGoodsImgsVOS;
    }

    public void setSeekGoodsImgsVOS(List<SeekGoodsImgsVOSBean> seekGoodsImgsVOS) {
        this.seekGoodsImgsVOS = seekGoodsImgsVOS;
    }

    public static class SeekGoodsImgsVOSBean {
//                /**
//                 * goodsPhotoId : 8
//                 * goodsPhotoName : 测试图片3
//                 * isDefaultPhoto : false
//                 * goodsId : 10750656398888960
//                 * photoUrl : http://b0cd0050cf0bf01b.oss-cn-beijing.aliyuncs.com/2018/9/f13cc5a3-e6a8-4a16-8339-f35c90a0b219.gif
//                 * sortOrder : 0
//                 * createTime : 1.537500884E12
//                 * updateTime : null
//                 * photoType : 1
//                 * dataStatus : 0
//                 * sysCreateTime : 1.537500884E12
//                 * sysVersionNo : 0
//                 * sysIsEnable : 1
//                 * sysIdString : 11771250796007424
//                 */
//

        private String photoUrl;

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

    }

    public String getDefalutPhotoURL() {
        return defalutPhotoURL;
    }

    public void setDefalutPhotoURL(String defalutPhotoURL) {
        this.defalutPhotoURL = defalutPhotoURL;
    }

    public String getPriceSection() {
        return priceSection;
    }

    public void setPriceSection(String priceSection) {
        this.priceSection = priceSection;
    }

    public String getMallPriceSection() {
        return mallPriceSection;
    }

    public void setMallPriceSection(String mallPriceSection) {
        this.mallPriceSection = mallPriceSection;
    }

    public String getMiniDetails() {
        return miniDetails;
    }

    public void setMiniDetails(String miniDetails) {
        this.miniDetails = miniDetails;
    }
}