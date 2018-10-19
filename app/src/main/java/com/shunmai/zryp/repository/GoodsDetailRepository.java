package com.shunmai.zryp.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.network.service.HttpService;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/9/29.
 */

public class GoodsDetailRepository extends BaseRepository<GoodsDetailBean>
{

    private static class GoodsDetailRepositoryHolder {

        private static final GoodsDetailRepository INSTANCE = new GoodsDetailRepository();

    }

    private GoodsDetailRepository(){}

    public static final GoodsDetailRepository getInstance() {

        return GoodsDetailRepositoryHolder.INSTANCE;

    }
    public final String Key="goodsDetail";
    public void getGoodsDetail(MutableLiveData<GoodsDetailBean> liveData, long goodsId, onResponseFailedListener listener){
        if (dataCache.get(Key+goodsId)!=null){
            liveData.setValue(dataCache.get(Key+goodsId));
            Log.e("NetWork","缓存");
            return;
        }
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetGoodsDetail(goodsId), goodsDetailBean -> {
            dataCache.put(Key+goodsId,goodsDetailBean);
            liveData.setValue(goodsDetailBean);
        }, throwable -> listener.onFailed(throwable));
    }
    public void SaveMyFootprint(long goodid){
        HashMap map=new HashMap();
        map.put("sysIdString",goodid);
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).SaveMyFootprint(map), stringTResponse -> {
        }, throwable -> {
        });
    }
}
