package com.shunmai.zryp.repository;

import com.shunmai.zryp.bean.userinfo.FootprintBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;
import com.ysy.commonlib.base.BaseRepository;

/**
 * Created by yushengyang.
 * Date: 2018/10/12.
 */

public class FootprintRepository extends BaseRepository<FootprintBean> {
    public void GetMyFootprintList(onResponseListener<FootprintBean> linstener) {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetMyFootprintList(), footprintBean -> linstener.onSuccess(footprintBean), throwable -> linstener.onFailed(throwable));
    }
}
