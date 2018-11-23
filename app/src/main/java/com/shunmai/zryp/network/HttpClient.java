package com.shunmai.zryp.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.shunmai.zryp.network.retrofiturlmanager.RetrofitUrlManager;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by ysy on 2018/1/2.
 */

public final class HttpClient {

    private final OkHttpClient mOkHttpClient;

    private HttpClient() {

        mOkHttpClient = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder()
                        .readTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .addInterceptor(new PublicParamsInterceptor())
                        .addNetworkInterceptor(HttpLogFactory.BodyInterceptor(true))
                        .addNetworkInterceptor(new StethoInterceptor())
                        .retryOnConnectionFailure(true))
                        .build();
    }

    private static final class HttpClientHolder {
        private static final HttpClient HTTP_CLIENT = new HttpClient();
    }

    public static HttpClient getInstance() {
        return HttpClientHolder.HTTP_CLIENT;
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }
}
