package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;


/**
 * Created by yushengyang.
 * Date: 2018/10/11.
 */

public class UserInfoRepository extends BaseRepository<UserInfoBean>{

    public void getUserInfo(onResponseListener<TResponse<UserInfoBean>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetUserInfo(), userInfoBean -> listener.onSuccess(userInfoBean), throwable -> {
        listener.onFailed(throwable);
        });
    }
}
