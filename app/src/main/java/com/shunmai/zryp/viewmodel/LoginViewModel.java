package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.LoginRepository;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/10/16.
 */

public class LoginViewModel extends ViewModel {
    LoginRepository repository = new LoginRepository();

    public void wechatLogin(HashMap<String, String> map, onResponseListener<TResponse<UserInfoBean>> listener) {
        repository.wechatLogin(map, listener);
    }

    public void Login(HashMap<String, String> map, onResponseListener<TResponse<UserInfoBean>> listener) {
        repository.login(map, listener);
    }
}
