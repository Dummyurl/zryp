package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.BankCardBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/12/6.
 */

public class WithDrawInfoRepository extends BaseRepository<Object> {
    public void UpdateBankCardInfo(HashMap<String, String> map, onResponseListener<BankCardBean> listener ){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).UpdateBankCardInfo(map), new Consumer<TResponse<BankCardBean>>() {
            @Override
            public void accept(TResponse<BankCardBean>  bean) throws Exception {
                listener.onSuccess(bean.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
}
