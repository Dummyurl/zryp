package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.userinfo.VipGuideIndexBean;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.VipGuideRepository;
import com.ysy.commonlib.base.TResponse;


/**
 * Created by yushengyang.
 * Date: 2018/12/4.
 */

public class VipGuideIndexViewModel extends ViewModel {

    MutableLiveData<VipGuideIndexBean> liveData = new MutableLiveData<>();
    VipGuideRepository repository = new VipGuideRepository();

    public void GetShoppingGuideIndex(onResponseFailedListener listener) {
        repository.GetShoppingGuideIndex(liveData,listener);
    }
    public void Upgrade(onResponseListener<TResponse<String>> listener){
        repository.Upgrade(listener);
    }

    public MutableLiveData<VipGuideIndexBean> getVipIndex() {
        return liveData;
    }
}
