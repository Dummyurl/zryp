package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.wallet.WalletListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class WithdrawListRepository extends BaseRepository<Object> {
    public void GetWithdrawList( int pageNum,int pageSize,onResponseListener<TResponse<List<WalletListBean>>> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class)
                .GetWithdrawList(pageNum, pageSize), new Consumer<TResponse<List<WalletListBean>>>() {
            @Override
            public void accept(TResponse<List<WalletListBean>> listTResponse) throws Exception {
                listener.onSuccess(listTResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
}
