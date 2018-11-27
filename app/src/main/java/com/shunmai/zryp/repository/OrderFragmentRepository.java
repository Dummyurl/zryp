package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.goods.GoodsOrderListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/6.
 */

public class OrderFragmentRepository extends BaseRepository<Object> {
    public void GetOrderList(int type, int page, onResponseListener<List<GoodsOrderListBean>> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetOrderList(type, page), listTResponse -> listener.onSuccess( listTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
    public void OrdersDelivery(long oid,onResponseListener<String> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).OrdersDelivery(oid), stringTResponse -> listener.onSuccess(stringTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
}
