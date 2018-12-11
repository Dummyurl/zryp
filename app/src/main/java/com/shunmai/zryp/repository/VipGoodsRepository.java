package com.shunmai.zryp.repository;

import android.arch.lifecycle.MutableLiveData;

import com.shunmai.zryp.bean.goods.GoodsPromotionBean;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/12/5.
 */

public class VipGoodsRepository extends BaseRepository<Object> {
    public void getVipGoods(MutableLiveData<GoodsPromotionBean> liveData, int pageNum, int pageSize, onResponseFailedListener listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetVipGoods(pageNum, pageSize), new Consumer<TResponse<GoodsPromotionBean>>() {
            @Override
            public void accept(TResponse<GoodsPromotionBean> bean) throws Exception {
                liveData.setValue(bean.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
}
