package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.UserInfoRepository;
import com.ysy.commonlib.base.TResponse;

/**
 * Created by yushengyang.
 * Date: 2018/9/26.
 */

public class UserInfoFragmentViewModel extends ViewModel{

    UserInfoRepository repository= new UserInfoRepository();
    public void getUserInfo(onResponseListener<TResponse<UserInfoBean>> listener){
        repository.getUserInfo(listener);
    }
}
