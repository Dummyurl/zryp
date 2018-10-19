package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.WechatShareLoginRepository;

import java.util.Map;

/**
 * Created by yushengyang.
 * Date: 2018/10/16.
 */

public class WechatShareLoginViewModel extends ViewModel {
    WechatShareLoginRepository repository=new WechatShareLoginRepository();
    public void getUserInfo(Map<String,String> map, onResponseListener listener){
        Log.i("login_data","request2");
        repository.getUseInfo(map,listener);
    }
}
