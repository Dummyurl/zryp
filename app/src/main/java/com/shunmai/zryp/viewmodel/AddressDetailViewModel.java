package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.addrbean.RegionBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.AddressDetailRepository;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yushengyang.
 * Date: 2018/9/20.
 */

public class AddressDetailViewModel extends ViewModel{
    AddressDetailRepository repository=new AddressDetailRepository();

    public void addAddress( Map<String ,Object> map, onResponseListener<TResponse<String>> listener){
        repository.addAddress(map,listener);
    }
    public void changeAddress(Map<String ,Object> map, onResponseListener<TResponse<String>> listener){
        repository.changeAddress(map,listener);
    }
    public void GetRegionList(int parentId, int regionType, onResponseListener<List<RegionBean>> listener){
        HashMap<String,String> map=new HashMap();
        map.put("parentId",parentId+"");
        map.put("regionType",regionType+"");
        repository.GetRegionList(map,listener);
    }
}
