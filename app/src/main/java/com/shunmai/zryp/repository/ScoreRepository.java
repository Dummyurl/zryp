package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.wallet.ScoreDataBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/12/11.
 */

public class ScoreRepository extends BaseRepository<Object>{
    public void GetExchangeData(onResponseListener<ScoreDataBean> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetExchangeData(), new Consumer<TResponse<ScoreDataBean>>() {
            @Override
            public void accept(TResponse<ScoreDataBean> scoreDataBeanTResponse) throws Exception {
                listener.onSuccess(scoreDataBeanTResponse.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
}
