package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.goods.OderInfoBean;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.GoodsOrderRepository;

import java.util.HashMap;

/**
 * Created by yushengyang.
 * Date: 2018/11/1.
 */

public class GoodsOrderViewModel extends ViewModel {
    GoodsOrderRepository repository=new GoodsOrderRepository();
    public void GetPostCost(long goods_id, int sku_id, int count, onResponseListener<Double> listener ){
        repository.GetPostCost(goods_id,sku_id,count,listener);
    }
    public void GetDefaultAddress( int isOutAddress,onResponseListener<AddressListBean.DataBean> listener ){
        repository.GetDefaultAddress(isOutAddress,listener);
    }
    public void OderInfoBean(HashMap<String, Object> map,  int addrId,int num,int type,onResponseListener<OderInfoBean> listener) {
        repository.GetOderInfo(map,addrId,num,type,listener);
    }
    public void getAdrById(int id,onResponseListener<AddressListBean.DataBean> listener){
        repository.getAdrById(id,listener);
    }
}

