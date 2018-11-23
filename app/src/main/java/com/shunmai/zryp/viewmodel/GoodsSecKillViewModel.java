package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.GoodsSecKillBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.GoodsSecKillRepository;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsSecKillViewModel extends ViewModel {
    GoodsSecKillRepository repository = new GoodsSecKillRepository();

    public void GetSeckill(onResponseListener<List<GoodsSecKillBean>> listener) {
        repository.GetSeckill(listener);
    }
}
