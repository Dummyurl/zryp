package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.shunmai.zryp.repository.GoodsListRepository;
import com.shunmai.zryp.bean.goods.GoodsListBean;

/**
 * Created by yushengyang.
 * Date: 2018/9/26.
 */

public class GoodsListActivityViewModel extends ViewModel {

    private GoodsListRepository repository;
    public void init(GoodsListRepository repository){
        this.repository=repository;
    }
    public LiveData<GoodsListBean> getCategoryList(int page, long sysId, int sortType){
        return repository.getCategory(page, sysId, sortType);
    }
    public LiveData<GoodsListBean> getFistPageData(int page,  int sortType,int prType){
        return repository.getFirstPageData(page, sortType,prType);
    }
    public LiveData<GoodsListBean> searchGoods(int page,String keyword,String goodsName,int sortType) {
        return  repository.searchGoods(keyword,goodsName,page,sortType);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        repository.onDestory();
    }
}
