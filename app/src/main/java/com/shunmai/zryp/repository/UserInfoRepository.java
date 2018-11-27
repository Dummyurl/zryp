package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;


/**
 * Created by yushengyang.
 * Date: 2018/10/11.
 */

public class UserInfoRepository extends BaseRepository<UserInfoBean> {

    public void getUserInfo(onResponseListener<TResponse<UserInfoBean>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetUserInfo(), userInfoBean -> listener.onSuccess(userInfoBean), throwable -> {
        listener.onFailed(throwable);
        });
    }
}
