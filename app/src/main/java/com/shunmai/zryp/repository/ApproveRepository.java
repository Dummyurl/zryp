package com.shunmai.zryp.repository;

import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/10/17.
 */

public class ApproveRepository extends BaseRepository<Object> {
    public void getCode(int type, String phoneNum, onResponseListener<TResponse<String>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetSmsCode(type, phoneNum), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }
    public void ApproveUser(HashMap<String,String> map,String code,onResponseListener<TResponse<String>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).ApproveUser(map, code), data -> listener.onSuccess(data), throwable -> listener.onFailed(throwable));
    }
}

