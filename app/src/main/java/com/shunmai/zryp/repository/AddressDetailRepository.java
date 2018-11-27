package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.addrbean.RegionBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by yushengyang.
 * Date: 2018/10/10.
 */

public class AddressDetailRepository extends BaseRepository<TResponse<String>> {
    public void addAddress(Map<String, Object> map, onResponseListener<TResponse<String>> listener
    ) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).AddAddress(map), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }

    public void changeAddress(Map<String, Object> map, onResponseListener<TResponse<String>> listener
    ) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).ChangeAddress(map), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }

    public void GetRegionList(HashMap<String, String> map, onResponseListener<List<RegionBean>> listener
    ) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetRegionList(map), listTResponse -> listener.onSuccess(listTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
}
