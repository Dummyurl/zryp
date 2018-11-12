package com.shunmai.zryp.compeonent;

import com.shunmai.zryp.ui.userinfo.account.LoginActivity;

import dagger.Component;

/**
 * Created by yushengyang.
 * Date: 2018/10/19.
 */

@Component
public interface LoginActivityComponent{
    void inject(LoginActivity loginActivity);
}