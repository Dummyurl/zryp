package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.userinfo.CollectBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.CollectRepository;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/10.
 */

public class CollectViewModel extends ViewModel{
    CollectRepository repository=new CollectRepository();
    public void GetCollectGoods(int page, onResponseListener<List<CollectBean>> listener) {
        repository.GetCollectGoods(page,listener);
    }
    public void DeleteCollectItem(int collectId, onResponseListener<Object> listener) {
        repository.DeleteCollectItem(collectId,listener);
    }
}
