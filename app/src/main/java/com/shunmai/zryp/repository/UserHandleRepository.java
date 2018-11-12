package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/10/17.
 */

public class UserHandleRepository extends BaseRepository<Object> {
    public void getCode(int type, String phoneNum, onResponseListener<TResponse<String>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetSmsCode(type, phoneNum), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }
    public void bindPhone(HashMap<String,Object> map,String code,onResponseListener<TResponse<UserInfoBean>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).bindPhone(map, code), wechatLoginBean -> listener.onSuccess(wechatLoginBean), throwable -> listener.onFailed(throwable));
    }
    public void setPassword(HashMap<String,String> map,onResponseListener<TResponse<UserInfoBean>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).SetNewPassword(map), setPasswordBean -> listener.onSuccess(setPasswordBean), throwable -> listener.onFailed(throwable));
    }
    public void register(HashMap<String,String> map,String code,onResponseListener<TResponse<UserInfoBean>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).Register(map,code), setPasswordBean -> listener.onSuccess(setPasswordBean), throwable -> listener.onFailed(throwable));
    }
}

