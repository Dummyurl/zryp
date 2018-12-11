package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.goods.RestBean;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.WithdrawRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class WithdrawViewModel extends ViewModel {
    WithdrawRepository repository=new WithdrawRepository();
    MutableLiveData<RestBean> liveData=new MutableLiveData<>();
    public void WalletAmount(onResponseFailedListener lisener) {
        repository.WalletAmount(liveData,lisener);
    }
    public void getCode( String phoneNum, onResponseListener<TResponse<String>> listener){
        repository.getCode(phoneNum,listener);
    }
    public void UserWithdraw(HashMap<String, Object> map, onResponseListener<String> listener){
        repository.UserWithdraw(map,listener);
    }
    public MutableLiveData<RestBean> getLiveData() {
        return liveData;
    }
}
