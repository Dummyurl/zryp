package com.shunmai.zryp.bean;

import com.shunmai.zryp.bean.goods.PromotionGoodsBean;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsSecKillBean {
    /**
     * prId : 712
     * prStart : 2018-11-13 11:00:00
     * prEnd : 2018-11-13 12:00:00
     * isPriceType : 1
     * countDown : 0
     * dataStatus : 300
     * promotionGoods : [{"progress":0,"status":400,"goodsId":85,"goodsName":"每日氢元素活力派益生菌负氢固体饮品","defalutPhotoURL":"http://pic.gzcfe.net/brand/2018/0411/5391113442081734555","price":1588,"mscore":24455,"promotionPrice":1588,"skuId":8,"goSalenum":0,"salenumMode":1,"manualSalenum":0,"goLimit":1,"goStock":1000},{"progress":0,"status":400,"goodsId":86,"goodsName":"氢素护肤清透水嫩面膜","defalutPhotoURL":"http://pic.gzcfe.net/brand/2018/0411/5611250059646941693","price":298,"mscore":4589,"promotionPrice":298,"skuId":9,"goSalenum":0,"salenumMode":1,"manualSalenum":0,"goLimit":1,"goStock":500},{"progress":0,"status":400,"goodsId":87,"goodsName":"每日氢元素人参蛹虫草负氢固体饮料","defalutPhotoURL":"http://pic.gzcfe.net/brand/2018/0411/4960590961483527321","price":2580,"mscore":39732,"promotionPrice":2580,"skuId":10,"goSalenum":0,"salenumMode":1,"manualSalenum":0,"goLimit":1,"goStock":1000},{"progress":0,"status":400,"goodsId":88,"goodsName":"氢亮舒缓护眼贴","defalutPhotoURL":"http://pic.gzcfe.net/brand/2018/0411/5365603471378238007","price":188,"mscore":2895,"promotionPrice":0,"skuId":11,"goSalenum":0,"salenumMode":1,"manualSalenum":0,"goLimit":1,"goStock":1000}]
     * startTime : 11:00
     */

    private int prId;
    private String prStart;
    private String prEnd;
    private int isPriceType;
    private long countDown;
    private int dataStatus;
    private String startTime;
    private List<PromotionGoodsBean> promotionGoods;

    public int getPrId() {
        return prId;
    }

    public void setPrId(int prId) {
        this.prId = prId;
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

    public long getCountDown() {
        return countDown;
    }

    public void setCountDown(long countDown) {
        this.countDown = countDown;
    }

    public int getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<PromotionGoodsBean> getPromotionGoods() {
        return promotionGoods;
    }

    public void setPromotionGoods(List<PromotionGoodsBean> promotionGoods) {
        this.promotionGoods = promotionGoods;
    }

}

