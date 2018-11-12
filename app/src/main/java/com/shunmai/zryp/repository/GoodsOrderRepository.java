package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.goods.OderInfoBean;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/11/1.
 */

public class GoodsOrderRepository extends BaseRepository<Object> {
    public void GetPostCost(long goods_id, int sku_id, int count, onResponseListener<Double> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetPostCost(goods_id, sku_id, count), doubleTResponse -> listener.onSuccess(doubleTResponse.getData()), throwable -> listener.onFailed(throwable));
    }

    public void GetDefaultAddress(int isOutAddress,onResponseListener<AddressListBean.DataBean> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetDefaultAddress(isOutAddress), dataBeanTResponse -> listener.onSuccess(dataBeanTResponse.getData()), throwable -> listener.onFailed(throwable));
    }

    public void GetOderInfo(HashMap<String, Object> map, int addrId,int num,int type,onResponseListener<OderInfoBean> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetOderInfo(map,addrId,num,type), oderInfoBeanTResponse -> listener.onSuccess(oderInfoBeanTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
    public void getAdrById(int id,onResponseListener<AddressListBean.DataBean> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetAddressById(id), dataBeanTResponse -> listener.onSuccess(dataBeanTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
}
