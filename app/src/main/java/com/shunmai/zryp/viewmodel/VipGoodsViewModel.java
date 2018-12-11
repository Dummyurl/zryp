package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.goods.GoodsPromotionBean;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.repository.VipGoodsRepository;

/**
 * Created by yushengyang.
 * Date: 2018/12/5.
 */

public class VipGoodsViewModel extends ViewModel {
    private MutableLiveData<GoodsPromotionBean> liveData = new MutableLiveData();
    private VipGoodsRepository repository = new VipGoodsRepository();
    public void getVipGoods( int pageNum, int pageSize, onResponseFailedListener listener){
        repository.getVipGoods(liveData,pageNum,pageSize,listener);
    }

    public MutableLiveData<GoodsPromotionBean> getLiveData() {
        return liveData;
    }
}
