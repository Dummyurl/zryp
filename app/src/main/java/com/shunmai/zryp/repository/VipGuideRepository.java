package com.shunmai.zryp.repository;

import android.arch.lifecycle.MutableLiveData;

import com.shunmai.zryp.bean.userinfo.VipGuideIndexBean;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseFragment;
import com.ysy.commonlib.base.BaseRepository;
import com.ysy.commonlib.base.TResponse;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/12/4.
 */

public class VipGuideRepository extends BaseRepository<Object> {
    public void GetShoppingGuideIndex(MutableLiveData<VipGuideIndexBean> liveData, onResponseFailedListener listener) {

        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetShoppingGuideIndex(), new Consumer<TResponse<VipGuideIndexBean>>() {
            @Override
            public void accept(TResponse<VipGuideIndexBean> data) throws Exception {
                data.getData().setProgress((int) (data.getData().getUpgradeProgress() * 100));
                liveData.setValue(data.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }
    public void Upgrade(onResponseListener<TResponse<String>> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).Upgrade(), new Consumer<TResponse<String>>() {
            @Override
            public void accept(TResponse<String> stringTResponse) throws Exception {
                listener.onSuccess(stringTResponse);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });
    }


}
