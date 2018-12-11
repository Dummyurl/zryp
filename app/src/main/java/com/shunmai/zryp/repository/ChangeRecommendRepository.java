package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.userinfo.ReferrerNameBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

import io.reactivex.functions.Consumer;


/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class ChangeRecommendRepository extends BaseRepository<Object> {
    public void ReferrerName(int userId,int type,onResponseListener<ReferrerNameBean> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).ReferrerName(userId,type), bean -> listener.onSuccess(bean.getData()), throwable -> listener.onFailed(throwable));
    }
    public void UpdateReferrer(HashMap<String, Integer> map,onResponseListener<TResponse<String>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).UpdateReferrer(map), new Consumer<TResponse<String>>() {
            @Override
            public void accept(TResponse<String> stringTResponse) throws Exception {
                listener.onSuccess(stringTResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
}
