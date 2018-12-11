package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.wallet.WalletListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.WithdrawListRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class WithdrawListViewModel extends ViewModel {
    WithdrawListRepository repository = new WithdrawListRepository();

    public void GetWithdrawList(int pageNum, int pageSize, onResponseListener<TResponse<List<WalletListBean>>> listener) {
        repository.GetWithdrawList(pageNum, pageSize, listener);
    }
}
