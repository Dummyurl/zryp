package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/11/12.
 */

public class HomePageRepository extends BaseRepository<Object> {
    public void HomePageInfo(onResponseListener<HomePageBean> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).HomePageInfo(), new Consumer<TResponse<HomePageBean>>() {
            @Override
            public void accept(TResponse<HomePageBean> homePageBeanTResponse) throws Exception {
                listener.onSuccess(homePageBeanTResponse.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
}
