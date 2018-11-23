package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.UserHandleRepository;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/10/17.
 */

public class UserHandleViewModel extends ViewModel {
    UserHandleRepository repository=new UserHandleRepository();
    public void getCode(int type, String phoneNum, onResponseListener<TResponse<String>> listener){
        repository.getCode(type,phoneNum,listener);
    }
    public void bindPhone(HashMap<String,Object> map, String code, onResponseListener<TResponse<UserInfoBean>> listener){
        repository.bindPhone(map,code,listener);
    }
    public void setPassword(HashMap<String,Object> map,onResponseListener<TResponse<UserInfoBean>> listener){
        repository.setPassword(map,listener);
    }
    public void register(HashMap<String,String> map,String code,onResponseListener<TResponse<UserInfoBean>> listener){
        repository.register(map,code,listener);
    }
}
