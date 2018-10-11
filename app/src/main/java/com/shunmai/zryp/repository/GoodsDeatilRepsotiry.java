package com.shunmai.zryp.repository;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.onResponseFailedListener;
import com.shunmai.zryp.network.service.HttpService;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/9/29.
 */

public class GoodsDeatilRepsotiry extends BaseRepository<GoodsDetailBean>
{

    private static class GoodsDeatilRepsotiryHolder {

        private static final GoodsDeatilRepsotiry INSTANCE = new GoodsDeatilRepsotiry();

    }

    private GoodsDeatilRepsotiry (){}

    public static final GoodsDeatilRepsotiry getInstance() {

        return GoodsDeatilRepsotiryHolder.INSTANCE;

    }
    public final String Key="goodsDetail";
    public void getGoodsDetail(MutableLiveData<GoodsDetailBean> liveData, long goodsId, onResponseFailedListener listener){
        if (dataCache.get(Key+goodsId)!=null){
            liveData.setValue(dataCache.get(Key+goodsId));
            Log.e("NetWork","缓存");
            return;
        }
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetGoodsDetail(goodsId), new Consumer<GoodsDetailBean>() {
            @Override
            public void accept(GoodsDetailBean goodsDetailBean) {
                dataCache.put(Key+goodsId,goodsDetailBean);
                liveData.setValue(goodsDetailBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                listener.onFailed(throwable);
            }
        });
    }
}
