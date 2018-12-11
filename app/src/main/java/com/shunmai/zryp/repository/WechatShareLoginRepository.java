package com.shunmai.zryp.repository;

import android.util.Log;

import com.google.gson.Gson;
import com.shunmai.zryp.bean.userinfo.Response_Wechat;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;
import com.shunmai.zryp.utils.wechatutils.WechatLoginHelper;
import com.ysy.commonlib.base.BaseRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yushengyang.
 * Date: 2018/10/16.
 */

public class WechatShareLoginRepository extends BaseRepository<Response_Wechat> {
    public void getUseInfo(Map<String, String> map, onResponseListener listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).WeChatToken(map), data -> {
            Log.i("login_data", new Gson().toJson(data));
            WechatLoginHelper.openid = data.getOpenid();
            HashMap<String, String> toaken_map = new HashMap<>();
            toaken_map.put("access_token", data.getAccess_token());
            toaken_map.put("openid", data.getOpenid());
            toaken_map.put("lang", "zh_CN");
            sendRequest(RetrofitClient.getInstance().getService(HttpService.class).WeChatUserInfo(toaken_map), userInfo -> {
                listener.onSuccess("");
                WechatLoginHelper.loginCallBack.onSuccess(userInfo);
            }
//            , throwable -> {
//                WechatLoginHelper.loginCallBack.onFailure();
//                listener.onFailed(throwable);
//            }
            );

        }
//        , throwable -> {
//            listener.onFailed(throwable);
//            WechatLoginHelper.loginCallBack.onFailure();
//        }
        );
    }
}
