package com.shunmai.zryp.repository;

import android.arch.lifecycle.MutableLiveData;

import com.shunmai.zryp.bean.BankBean;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/12/6.
 */

public class BlankListRepository extends BaseRepository<List<BankBean>> {
    public void GetBankList(MutableLiveData<List<BankBean>> liveData, onResponseFailedListener listener) {
        if (dataCache.get("bankList") != null) {
            liveData.setValue(dataCache.get("bankList"));
            return;
        }
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetBankList(), listTResponse -> liveData.setValue(listTResponse.getData()), new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
}
