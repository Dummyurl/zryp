package com.shunmai.zryp.bean.goods;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsPromotionBean {

    /**
     * prId : 595
     * bannerImgUrl : https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=e28edf3fc41b9d168ac79d67cbe5d3b2/8601a18b87d6277f0a677fd822381f30e824fcdd.jpg
     * prStart : 2018-11-07 00:00:00
     * prEnd : 2018-11-12 23:59:59
     * isPriceType : 1
     * scoreTime : 3.3
     * countDown : 381418524
     * promotionGoods : [{"goodsId":87,"goodsName":"每日氢元素人参蛹虫草负氢固体饮料","defalutPhotoURL":"http://pic.gzcfe.net/brand/2018/0411/4960590961483527321","price":2580,"mscore":39732,"promotionPrice":2580,"skuId":10}]
     */

    private int prId;
    private String bannerImgUrl;
    private String prStart;
    private String prEnd;
    private int isPriceType;
    private double scoreTime;
    private long countDown;
    private List<PromotionGoodsBean> promotionGoods;

    public int getPrId() {
        return prId;
    }

    public void setPrId(int prId) {
        this.prId = prId;
    }

    public String getBannerImgUrl() {
        return bannerImgUrl;
    }

    public void setBannerImgUrl(String bannerImgUrl) {
        this.bannerImgUrl = bannerImgUrl;
    }

    public String getPrStart() {
        return prStart;
    }

    public void setPrStart(String prStart) {
        this.prStart = prStart;
    }

    public String getPrEnd() {
        return prEnd;
    }

    public void setPrEnd(String prEnd) {
        this.prEnd = prEnd;
    }

    public int getIsPriceType() {
        return isPriceType;
    }

    public void setIsPriceType(int isPriceType) {
        this.isPriceType = isPriceType;
    }

    public double getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(double scoreTime) {
        this.scoreTime = scoreTime;
    }

    public long getCountDown() {
        return countDown;
    }

    public void setCountDown(long countDown) {
        this.countDown = countDown;
    }

    public List<PromotionGoodsBean> getPromotionGoods() {
        return promotionGoods;
    }

    public void setPromotionGoods(List<PromotionGoodsBean> promotionGoods) {
        this.promotionGoods = promotionGoods;
    }


}
