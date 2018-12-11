package com.shunmai.zryp.repository;

import android.arch.lifecycle.MutableLiveData;

import com.shunmai.zryp.bean.goods.RestBean;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class WithdrawRepository extends BaseRepository<RestBean> {
    public void WalletAmount(MutableLiveData<RestBean> liveData, onResponseFailedListener listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).WalletAmount(), restBeanTResponse -> liveData.setValue(restBeanTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
    public void getCode( String phoneNum, onResponseListener<TResponse<String>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetSmsCode(3, phoneNum), stringTResponse -> listener.onSuccess(stringTResponse), throwable -> listener.onFailed(throwable));
    }
    public void UserWithdraw(HashMap<String, Object> map,onResponseListener<String> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).UserWithdraw(map), stringTResponse -> listener.onSuccess(""), throwable -> listener.onFailed(throwable));
    }
}
