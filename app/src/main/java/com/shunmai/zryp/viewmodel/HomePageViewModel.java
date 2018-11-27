package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.HomePageRepository;
import com.ysy.commonlib.base.TResponse;

/**
 * Created by yushengyang.
 * Date: 2018/11/12.
 */

public class HomePageViewModel extends ViewModel {
    HomePageRepository repository=new HomePageRepository();
    public void HomePageInfo(onResponseListener<HomePageBean> listener){
        repository.HomePageInfo(listener);
    }
    public void GetMainAd(onResponseListener<TResponse<String>> listener) {
        repository.GetMainAd(listener);
    }
}
