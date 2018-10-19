package com.shunmai.zryp.network;

import com.shunmai.zryp.utils.ShareUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xianglanzuo on 2018/1/2.
 */

public class PublicParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String userInfo;

        if (ShareUtils.getUserInfo()==null) {
            userInfo="0";
        }else{
            userInfo=ShareUtils.getUserInfo().getUserId()+"";
        }
        Request request = chain.request()
                .newBuilder()
                .addHeader("APP-Space", "B0CD0050CF0BF01B")
                .addHeader("App-Type", "2")
                .addHeader("App-Uid", userInfo)
                .build();
        return chain.proceed(request);
    }
}
