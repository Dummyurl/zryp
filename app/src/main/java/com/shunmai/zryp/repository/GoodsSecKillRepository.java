package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.GoodsSecKillBean;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsSecKillRepository extends BaseRepository<Object> {
    public void GetSeckill(onResponseListener<List<GoodsSecKillBean>> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetSeckill(), new Consumer<TResponse<List<GoodsSecKillBean>>>() {
            @Override
            public void accept(TResponse<List<GoodsSecKillBean>> goodsSecKillBeanTResponse) throws Exception {
                listener.onSuccess(goodsSecKillBeanTResponse.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
}
