package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.GoodsSecKillBean;
import com.shunmai.zryp.bean.goods.GoodsPromotionBean;
import com.shunmai.zryp.bean.goods.PromotionGoodsBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.GoodsSecKillFragmentRepository;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/14.
 */

public class GoodsSecKillFragmentViewModel extends ViewModel {
    GoodsSecKillFragmentRepository repository=new GoodsSecKillFragmentRepository();
    public void GetActivityGoods(int prId, int isPriceType, int pageNum, int pageSize, int dataStatus, onResponseListener<List<PromotionGoodsBean>> listener) {
        repository.GetActivityGoods(prId, isPriceType, pageNum, pageSize, dataStatus,listener);

    }
}
