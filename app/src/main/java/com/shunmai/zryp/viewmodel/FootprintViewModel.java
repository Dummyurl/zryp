package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.userinfo.FootprintBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.FootprintRepository;

/**
 * Created by yushengyang.
 * Date: 2018/10/12.
 */

public class FootprintViewModel extends ViewModel {
    FootprintRepository repository = new FootprintRepository();

    public void GetMyFootprintList(onResponseListener<FootprintBean> listener) {
        repository.GetMyFootprintList(listener);
    }
}
