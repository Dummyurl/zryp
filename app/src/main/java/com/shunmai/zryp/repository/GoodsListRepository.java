package com.shunmai.zryp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.shunmai.zryp.bean.goods.GoodsListBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;

/**
 * Created by yushengyang.
 * Date: 2018/9/26.
 */

public class GoodsListRepository extends BaseRepository<GoodsListBean> {
    private String Key = "GoodsList";

    public LiveData<GoodsListBean> getCategory(int page,long sysId,int sortType) {
        MutableLiveData<GoodsListBean>  data = new MutableLiveData<>();
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GoodsList(page, sysId, sortType), goodsListBean -> {data.setValue(goodsListBean);
        }, throwable ->  {data.setValue(null);
        throwable.printStackTrace();
        });
        return data;
    }
    public LiveData<GoodsListBean> getFirstPageData(int page,int sortType,int prType) {
        MutableLiveData<GoodsListBean>  data = new MutableLiveData<>();
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GoodsList(page, sortType,prType), goodsListBean -> data.setValue(goodsListBean), throwable -> data.setValue(null));
        return data;
    }
    public LiveData<GoodsListBean> searchGoods(String goodsName,int page,int sortType) {
        MutableLiveData<GoodsListBean>  data = new MutableLiveData<>();
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).SearchGoods(page,goodsName,sortType,20), goodsListBean -> data.setValue(goodsListBean), throwable -> data.setValue(null));
        return data;
    }
}
