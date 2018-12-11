package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.BankCardBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.WithDrawInfoRepository;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/12/6.
 */

public class WithDrawViewInfoModel extends ViewModel {
    WithDrawInfoRepository repository= new WithDrawInfoRepository();
    public void UpdateBankCardInfo(HashMap<String, String> map, onResponseListener<BankCardBean> listener ){
        repository.UpdateBankCardInfo(map,listener);
    }
}
