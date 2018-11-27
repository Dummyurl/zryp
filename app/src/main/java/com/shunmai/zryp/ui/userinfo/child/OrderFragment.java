package com.shunmai.zryp.ui.userinfo.child;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.account.OrderListAdapter;
import com.shunmai.zryp.bean.goods.GoodsOrderListBean;
import com.shunmai.zryp.databinding.FragmentOrderBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.viewmodel.OrderFragmentViewModel;
import com.ysy.commonlib.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends BaseFragment<FragmentOrderBinding> implements OrderFragmentViewModel.refreshListener {
    private int type;
    private int page = 1;
    private OrderListAdapter adapter;
    private OrderFragmentViewModel viewModel;
    private boolean canLoad=true;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(OrderFragmentViewModel.class);
        viewModel.setRefreshListener(this);
        showContentView();
        initView();
        if (canLoad){
            getOrderList();
            canLoad=false;
        }
    }



    public void getOrderList() {
        viewModel.GetOrderList(type, page, new onResponseListener<List<GoodsOrderListBean>>() {
            @Override
            public void onSuccess(List<GoodsOrderListBean> goodsOrderListBeans) {
                page++;
                bindingView.refreshLayout.finishRefresh(true);
                bindingView.refreshLayout.finishLoadMore(true);
                adapter.add(goodsOrderListBeans);
                adapter.setCanShowEmpty(true);
                if (goodsOrderListBeans.size() < 10) {
                    bindingView.refreshLayout.setNoMoreData(true);
                    bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
                }
                showContentView();
            }

            @Override
            public void onFailed(Throwable throwable) {
                bindingView.refreshLayout.finishRefresh(false);
                bindingView.refreshLayout.finishLoadMore(false);
                showError();
            }
        });
    }

    private void initView() {
        bindingView.rcOrder.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new OrderListAdapter(getActivity(), new ArrayList<>(), viewModel);
        bindingView.rcOrder.setAdapter(adapter);
        bindingView.refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        bindingView.refreshLayout.setEnableLoadMore(true);
        bindingView.refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                adapter.clear();
                getOrderList();
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getOrderList();
            }
        });
    }

    @Override
    protected void onRefresh() {
        getOrderList();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_order;
    }

    public OrderFragment setType(int type) {
        this.type = type;
        return this;
    }

    @Override
    public void refresh() {
        page = 1;
        adapter.clear();
        getOrderList();
    }
}
