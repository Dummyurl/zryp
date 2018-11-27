package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.goods.GoodsPromotionBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsPromotionRepository extends BaseRepository<Object> {
    public void GetScorePromotion(int prId, onResponseListener<GoodsPromotionBean> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetScorePromotion(prId), goodsPromotionBeanTResponse -> listener.onSuccess(goodsPromotionBeanTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
    public void GetPrePro(int prId, onResponseListener<GoodsPromotionBean> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetPrePro(prId), goodsPromotionBeanTResponse -> listener.onSuccess(goodsPromotionBeanTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
}
