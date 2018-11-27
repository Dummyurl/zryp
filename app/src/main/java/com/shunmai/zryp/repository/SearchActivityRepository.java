package com.shunmai.zryp.repository;

import android.arch.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.network.service.HttpService;
import com.shunmai.zryp.utils.ShareUtils;
import com.ysy.commonlib.base.BaseRepository;

import java.util.ArrayList;

/**
 * Created by yushengyang.
 * Date: 2018/9/28.
 */

public class SearchActivityRepository extends BaseRepository<GoodsHotWordBean> {
    private String Key = "keyword";

    public void getKeyWord(MutableLiveData<GoodsHotWordBean> liveData,onResponseFailedListener listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetSearchKeyWord(), goodsHotWordBean -> liveData.setValue(goodsHotWordBean), throwable -> {
            listener.onFailed(throwable);
            liveData.setValue(new GoodsHotWordBean(new ArrayList<>()));
            throwable.printStackTrace();
        });
    }
    public void getHistory(MutableLiveData<GoodsHotWordBean> liveData){
        String search_history = ShareUtils.getString("search_history");
        if (search_history!=null&&!search_history.equals("")){
           liveData.setValue(new Gson().fromJson(search_history, GoodsHotWordBean.class));
        }else{
            liveData.setValue(new GoodsHotWordBean(new ArrayList<>()));
        }

    }
}
