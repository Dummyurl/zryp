package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.underling.UnderlingBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.UnderlingRepository;

/**
 * Created by yushengyang.
 * Date: 2018/10/11.
 */

public class UnderlingViewModel extends ViewModel {
UnderlingRepository repository=new UnderlingRepository();
public void getUnderlingList(int userType, int userId, int pageNum, onResponseListener<UnderlingBean> listener){
    repository.getUnderlingList(userType,userId,pageNum,listener);
}
}
