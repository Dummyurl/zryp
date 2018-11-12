package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.repository.AddressListRepository;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.listener.onResponseListener;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/9.
 */

public class AddressListViewModel extends ViewModel {
    AddressListRepository repository=new AddressListRepository();
    public void getAddressList(onResponseListener<List<AddressListBean.DataBean>> listener,int userId,int page,int isOutAddress){
        repository.getAddressList(listener,userId,page,isOutAddress);
    }
    public void deleteAddress(onResponseListener<TResponse<String>> listener,int id){
        repository.deleteAddress(listener,id);
    }
    public void changeDefaultAddress(onResponseListener<TResponse<String>> listener,int id){
        repository.changeDefaultAddress(listener,id);
    }
}
