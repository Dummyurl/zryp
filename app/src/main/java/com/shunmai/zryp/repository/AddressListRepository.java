package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/9.
 */

public class AddressListRepository extends BaseRepository<List<AddressListBean.DataBean>> {
    public void getAddressList(onResponseListener<List<AddressListBean.DataBean>> listener,int userId,int page,int isOutAddress){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetAddressList(userId,page,20,isOutAddress), addressListBean -> listener.onSuccess(addressListBean.getData()), throwable -> listener.onFailed(throwable));
    }
    public void deleteAddress(onResponseListener<TResponse<String>> listener, int id){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).DeleteAddress(id), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }
    public void changeDefaultAddress(onResponseListener<TResponse<String>> listener,int id){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("id",id);
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).ChangeDefaultAddress(map), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }
}
