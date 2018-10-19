package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.repository.GoodsDetailRepository;
import com.shunmai.zryp.listener.onResponseFailedListener;

/**
 * Created by yushengyang.
 * Date: 2018/9/29.
 */

public class GoodsDetailViewModel extends ViewModel {
    GoodsDetailRepository repsotiry;

    public void init(GoodsDetailRepository repsotiry) {
        if (this.repsotiry == null) {
            this.repsotiry = repsotiry;
        }
    }

    public void getGoodsDetail(MutableLiveData liveData, long goodsId, onResponseFailedListener
            listener) {
        repsotiry.getGoodsDetail(liveData, goodsId, listener);
    }
    public void SaveMyFootprint(long goodsId){
        repsotiry.SaveMyFootprint(goodsId);
    }
}
