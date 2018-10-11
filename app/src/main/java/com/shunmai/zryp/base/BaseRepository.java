package com.shunmai.zryp.base;

import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.rx.RxHelper;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/9/25.
 */

public class BaseRepository<T>  {

    public Map<String ,T> dataCache=new HashMap<>();

    private RxHelper mSubscribeHelper;

    protected void cancelTask() {
        if (mSubscribeHelper != null) {
            mSubscribeHelper.disposeTask();
        }
    }

    protected void cancelEvent() {
        if (mSubscribeHelper != null) {
            mSubscribeHelper.disposeEvent();
        }
    }

    protected RxHelper getSubscribeHelper() {
        if (mSubscribeHelper == null) {
            mSubscribeHelper = new RxHelper();
        }

        return mSubscribeHelper;
    }
    public <T extends TResponse> void sendRequest(Observable<T> observable, Consumer<T> onSuccess, Consumer<Throwable> onFailure) {
        getSubscribeHelper().execute(observable, onSuccess, onFailure == null ? this::defaultFailure : onFailure);
    }
    private void defaultFailure(Throwable throwable) {
        // TODO: 2018/1/2
    }

    public void onDestory() {
        cancelTask();
        cancelEvent();
    }
}
