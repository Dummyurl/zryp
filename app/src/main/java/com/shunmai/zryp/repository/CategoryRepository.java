package com.shunmai.zryp.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.shunmai.zryp.bean.goods.CategoryBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;


/**
 * Created by yushengyang.
 * Date: 2018/9/25.
 */

public class CategoryRepository extends BaseRepository<CategoryBean> {
    private String Key = "category";


    public LiveData<CategoryBean> getCategory(boolean refresh, onResponseFailedListener listener) {
        //先从缓存中取数据，看是否有数据
        MutableLiveData<CategoryBean> data = new MutableLiveData<>();
        if (dataCache.get(Key)!=null&&!refresh) {
                data.setValue(dataCache.get(Key));
                 return data;
        }

        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).CategoryList(), tResponse -> {
            data.setValue(tResponse);
            dataCache.put(Key, tResponse);
        }, throwable -> {
            throwable.printStackTrace();
            listener.onFailed(throwable);
        });
        return data;
    }


}
