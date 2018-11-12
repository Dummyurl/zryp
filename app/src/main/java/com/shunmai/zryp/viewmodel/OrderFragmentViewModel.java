package com.shunmai.zryp.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.shunmai.zryp.bean.goods.GoodsOrderListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.OrderFragmentRepository;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/6.
 */

public class OrderFragmentViewModel extends ViewModel{
    private refreshListener listener;
    private OrderFragmentRepository repository=new OrderFragmentRepository();
    public void GetOrderList(int type, int page, onResponseListener<List<GoodsOrderListBean>> listener) {
        repository.GetOrderList(type,page,listener);
    }
    public void OrdersDelivery(long oid,onResponseListener<String> listener){
        repository.OrdersDelivery(oid,listener);
    }

    public void setRefreshListener(refreshListener listener) {
        this.listener = listener;
    }
    public void Refresh(){
        listener.refresh();
    }
    public interface refreshListener{
        void refresh();
    }
}
