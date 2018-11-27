package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;

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
    public void getGoodsDetail(long goodsId, onResponseListener<GoodsDetailBean> listener){
//        if (dataCache.get(Key+goodsId)!=null){
//            liveData.setValue(dataCache.get(Key+goodsId));
//            Log.e("NetWork","缓存");
//            return;
//        }
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetGoodsDetail(goodsId), goodsDetailBean -> {
            listener.onSuccess(goodsDetailBean);
//            dataCache.put(Key+goodsId,goodsDetailBean);
//            liveData.setValue(goodsDetailBean);
        }, throwable -> listener.onFailed(throwable));
    }
    public void SaveMyFootprint(long goodid){
        HashMap map=new HashMap();
        map.put("sysIdString",goodid);
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).SaveMyFootprint(map), stringTResponse -> {
        }, throwable -> {
        });
    }
    public void DeleteCollectItem(int collectId, onResponseListener<String> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).DeleteCollectItem(collectId), stringTResponse -> listener.onSuccess(stringTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
    public void CollectGoodsItem(int goodsid,int skuid,int userid, onResponseListener<String> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).CollectGoodsItem(goodsid,skuid,userid), stringTResponse -> listener.onSuccess(stringTResponse.getData()), throwable -> listener.onFailed(throwable));
    }
}
