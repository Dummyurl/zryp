package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.userinfo.ReferrerNameBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.ChangeRecommendRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class ChangeRecommendViewModel extends ViewModel {
    ChangeRecommendRepository repository=new ChangeRecommendRepository();
    public void ReferrerName(int userId,int type,onResponseListener<ReferrerNameBean> listener){
        repository.ReferrerName(userId,type,listener);
    }
    public void UpdateReferrer(HashMap<String, Integer> map, onResponseListener<TResponse<String>> listener){
        repository.UpdateReferrer(map,listener);
    }
}
