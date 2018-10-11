package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;

import java.util.Map;


/**
 * Created by yushengyang.
 * Date: 2018/10/10.
 */

public class AddressDetailRepository extends BaseRepository<TResponse<String>> {
    public void addAddress(Map<String,Object> map,onResponseListener<TResponse<String>> listener
                           ){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).AddAddress(map), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }
}
