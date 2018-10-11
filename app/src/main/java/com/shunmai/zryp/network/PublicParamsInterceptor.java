package com.shunmai.zryp.network;

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
        Request request = chain.request()
                .newBuilder()
                .addHeader("APP-Space", "B0CD0050CF0BF01B")
                .addHeader("App-Type", "2")
                .addHeader("App-Uid", "18720")
                .build();
        return chain.proceed(request);
    }
}
