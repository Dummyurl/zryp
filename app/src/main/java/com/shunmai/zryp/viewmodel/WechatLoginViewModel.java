package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.WechatLoginRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/12/10.
 */

public class WechatLoginViewModel extends ViewModel{
    WechatLoginRepository repository=new WechatLoginRepository();
    public void WechatLogin(String unionId, onResponseListener<TResponse<UserInfoBean>> listener){
        repository.WechatLogin(unionId,listener);
    }
    public void IsAuthentication(String invitationCode, onResponseListener<TResponse<Integer>> listener){
        repository.IsAuthentication(invitationCode,listener);
    }

    public void getCode(int type, String phoneNum, onResponseListener<TResponse<String>> listener) {
        repository.getCode(type, phoneNum, listener);
    }
    public void RegistAuthentication(HashMap<String,Object> map, String verifyCode, String  invitationCode, onResponseListener<TResponse<UserInfoBean>> listener){
        repository.RegistAuthentication(map,verifyCode,invitationCode,listener);
    }
}
