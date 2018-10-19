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
 * Date: 2018/10/16.
 */

public class LoginRepository extends BaseRepository<Object> {
    public void wechatLogin(HashMap<String,String> map,onResponseListener<TResponse<UserInfoBean>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).WeChatLogin(map), wechatLoginBean -> listener.onSuccess(wechatLoginBean), throwable -> listener.onFailed(throwable));
    }
    public void login(HashMap<String,String> map,onResponseListener<TResponse<UserInfoBean>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).Login(map), bean -> listener.onSuccess(bean), throwable -> listener.onFailed(throwable));
    }
}
