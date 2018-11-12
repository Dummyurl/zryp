package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
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

    public void getGoodsDetail( long goodsId,  onResponseListener<GoodsDetailBean>
            listener) {
        repsotiry.getGoodsDetail( goodsId, listener);
    }

    public void SaveMyFootprint(long goodsId) {
        repsotiry.SaveMyFootprint(goodsId);
    }

    public void DeleteCollectItem(int collectId, onResponseListener<String> listener) {
        repsotiry.DeleteCollectItem(collectId, listener);
    }

    public void CollectGoodsItem(int goodsid, int skuid, int userid, onResponseListener<String> listener) {
        repsotiry.CollectGoodsItem(goodsid, skuid, userid, listener);
    }
}
