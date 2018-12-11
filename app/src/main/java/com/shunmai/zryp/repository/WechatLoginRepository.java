package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/12/10.
 */

public class WechatLoginRepository extends BaseRepository<Object> {
    public void WechatLogin(String unionId, onResponseListener<TResponse<UserInfoBean>> listener){
    sendRequest(RetrofitClient.getInstance().getService(HttpService.class).WechatLogin(unionId), bean -> listener.onSuccess(bean), throwable -> listener.onFailed(throwable));
    }
    public void IsAuthentication(String invitationCode, onResponseListener<TResponse<Integer>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).IsAuthentication(invitationCode), bean -> listener.onSuccess(bean), throwable -> listener.onFailed(throwable));
    }
    public void RegistAuthentication(HashMap<String,Object> map,String verifyCode,String  invitationCode,onResponseListener<TResponse<UserInfoBean>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).RegistAuthentication(map, verifyCode, invitationCode), new Consumer<TResponse<UserInfoBean>>() {
            @Override
            public void accept(TResponse<UserInfoBean> beanTResponse) throws Exception {
                    listener.onSuccess(beanTResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
    public void getCode(int type, String phoneNum, onResponseListener<TResponse<String>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetSmsCode(type, phoneNum), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }
}
