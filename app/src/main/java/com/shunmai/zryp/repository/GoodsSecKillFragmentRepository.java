package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.goods.PromotionGoodsBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by yushengyang.
 * Date: 2018/11/14.
 */

public class GoodsSecKillFragmentRepository extends BaseRepository<Object> {
    public void GetActivityGoods(int prId, int isPriceType, int pageNum, int pageSize, int dataStatus, onResponseListener<List<PromotionGoodsBean>> listener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetActivityGoods(prId, isPriceType, pageNum, pageSize, dataStatus), new Consumer<TResponse<List<PromotionGoodsBean>>>() {
            @Override
            public void accept(TResponse<List<PromotionGoodsBean>> listTResponse) throws Exception {
                listener.onSuccess(listTResponse.getData());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                listener.onFailed(throwable);
            }
        });

    }
}
