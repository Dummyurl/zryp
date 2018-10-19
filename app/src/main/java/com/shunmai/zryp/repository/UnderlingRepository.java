package com.shunmai.zryp.repository;

import com.shunmai.zryp.base.BaseRepository;
import com.shunmai.zryp.bean.underling.UnderlingBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.service.HttpService;

/**
 * Created by yushengyang.
 * Date: 2018/10/11.
 */

public class UnderlingRepository extends BaseRepository<UnderlingBean>{
    public void getUnderlingList(int userType,int userId,int pageNum,onResponseListener<UnderlingBean> listener){
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GetUnderlingList(userType, userId, pageNum, 15), underlingBean -> listener.onSuccess(underlingBean), throwable -> listener.onFailed(throwable));
    }
}
