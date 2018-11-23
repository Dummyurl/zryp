package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.goods.GoodsPromotionBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.GoodsPromotionRepository;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsPromotionViewModel extends ViewModel {
    GoodsPromotionRepository repository=new GoodsPromotionRepository();
    public void GetScorePromotion(int prId, onResponseListener<GoodsPromotionBean> listener) {
        repository.GetScorePromotion(prId,listener);
    }
    public void GetPrePro(int prId, onResponseListener<GoodsPromotionBean> listener) {
        repository.GetPrePro(prId,listener);
    }

}
