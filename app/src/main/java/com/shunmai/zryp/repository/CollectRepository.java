package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.userinfo.CollectBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.shunmai.zryp.utils.ShareUtils;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/11/10.
 */

public class CollectRepository extends BaseRepository<Object> {
    public void GetCollectGoods(int page, onResponseListener<List<CollectBean>> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetCollectGoods(page, 20, ShareUtils.getUserInfo().getUserId()), listTResponse -> listener.onSuccess(listTResponse.getData()), throwable -> listener.onFailed(throwable));
    }

    public void DeleteCollectItem(int collectId, onResponseListener<Object> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).DeleteCollectItem(collectId), stringTResponse -> listener.onSuccess(stringTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
}
