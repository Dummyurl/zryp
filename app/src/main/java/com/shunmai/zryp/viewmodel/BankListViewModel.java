package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.BankBean;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.repository.BlankListRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yushengyang.
 * Date: 2018/12/6.
 */

public class BankListViewModel extends ViewModel{
   private BlankListRepository repository=new BlankListRepository();

    public MutableLiveData<List<BankBean>> getList() {
        return liveData;
    }

   private MutableLiveData<List<BankBean>> liveData=new MutableLiveData<>();
    public void GetBankList( onResponseFailedListener listener) {
        repository.GetBankList(liveData,listener);
    }

}
