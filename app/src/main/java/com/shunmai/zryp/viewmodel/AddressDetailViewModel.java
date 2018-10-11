package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.network.onResponseListener;
import com.shunmai.zryp.repository.AddressDetailRepository;

import java.util.Map;

/**
 * Created by yushengyang.
 * Date: 2018/9/20.
 */

public class AddressDetailViewModel extends ViewModel{
    AddressDetailRepository repository=new AddressDetailRepository();

    public void addAddress(Map<String ,Object> map, onResponseListener<TResponse<String>> listener){
        repository.addAddress(map,listener);
    }
}
