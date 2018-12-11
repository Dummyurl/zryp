package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.wallet.ScoreDataBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.ScoreRepository;

/**
 * Created by yushengyang.
 * Date: 2018/12/11.
 */

public class ScoreViewModel extends ViewModel {
    ScoreRepository repository=new ScoreRepository();
    public void GetExchangeData(onResponseListener<ScoreDataBean> listener){
        repository.GetExchangeData(listener);
    }
}
