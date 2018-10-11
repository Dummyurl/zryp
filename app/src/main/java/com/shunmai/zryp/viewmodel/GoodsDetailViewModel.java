package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.repository.GoodsDeatilRepsotiry;
import com.shunmai.zryp.network.onResponseFailedListener;

/**
 * Created by yushengyang.
 * Date: 2018/9/29.
 */

public class GoodsDetailViewModel extends ViewModel {
    GoodsDeatilRepsotiry repsotiry;

    public void init(GoodsDeatilRepsotiry repsotiry) {
        if (this.repsotiry == null) {
            this.repsotiry = repsotiry;
        }
    }

    public void getGoodsDetail(MutableLiveData liveData, long goodsId, onResponseFailedListener
            listener) {
        repsotiry.getGoodsDetail(liveData, goodsId, listener);
    }
}
