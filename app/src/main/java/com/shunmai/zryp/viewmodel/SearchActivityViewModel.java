package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.repository.SearchActivityRepository;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.network.onResponseFailedListener;

/**
 * Created by yushengyang.
 * Date: 2018/9/28.
 */

public class SearchActivityViewModel  extends ViewModel{
    SearchActivityRepository repository;
    MutableLiveData<GoodsHotWordBean> history;
    public void init(SearchActivityRepository repository){
        this.repository=repository;
    }
    public void getKeyWord( MutableLiveData<GoodsHotWordBean> liveData,onResponseFailedListener listener){
        repository.getKeyWord(liveData,listener);
    }
    public void getHistory( MutableLiveData<GoodsHotWordBean> liveData){
        if (history==null){
        history=liveData;}
        repository.getHistory(history);
    }
    public void getHistory(){
        repository.getHistory(history);
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        repository.onDestory();
    }
}
