package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.ApproveRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/10/18.
 */

public class ApproveViewModel extends ViewModel {
    ApproveRepository repository = new ApproveRepository();

    public void getCode(int type, String phoneNum, onResponseListener<TResponse<String>> listener) {
        repository.getCode(type, phoneNum, listener);
    }

    public void ApproveUser(HashMap<String, String> map, String code, onResponseListener<TResponse<String>> listener) {
        repository.ApproveUser(map,code,listener);
    }
}
