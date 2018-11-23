package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.goods.GoodsSecKillAdapter;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.bean.GoodsSecKillBean;
import com.shunmai.zryp.bean.goods.PromotionGoodsBean;
import com.shunmai.zryp.databinding.FragmentSecondKillBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.viewmodel.GoodsSecKillFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsSecKillFragment extends BaseFragment<FragmentSecondKillBinding> {
    private GoodsSecKillBean bean;
    private GoodsSecKillFragmentViewModel viewModel;
    private int page = 1;
    private GoodsSecKillAdapter adapter;
    private int page_size=10;


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(GoodsSecKillFragmentViewModel.class);
        initView();
        initData();

    }

    private void initView() {
        bindingView.rvSecKill.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new GoodsSecKillAdapter(getActivity(), new ArrayList<>());
        bindingView.rvSecKill.setAdapter(adapter);
        bindingView.rvSecKill.setNestedScrollingEnabled(false);
        bindingView.srSecKill.setOnRefreshListener(refreshLayout -> {
            page = 1;
            getData();
            adapter.clear();
            bindingView.srSecKill.setEnableLoadMore(true);
            bindingView.srSecKill.setEnableAutoLoadMore(true);
        });
        bindingView.srSecKill.setEnableLoadMore(true);
        bindingView.srSecKill.setEnableAutoLoadMore(true);
        bindingView.srSecKill.setOnLoadMoreListener(refreshLayout -> getData());
//        RefreshHeader refreshHeader = bindingView.srSecKill.getRefreshHeader();
//        refreshHeader.getView().setBackgroundColor(getResources().getColor(R.color.backgroundColor));
    }

    public void setBean(GoodsSecKillBean bean) {
        this.bean = bean;
    }

    public void initData() {
        if (bean.getDataStatus() == 0) {
            bindingView.tvState.setText("距本场开始");
            bindingView.timer.setTime((int) bean.getCountDown() / 1000 / 3600, (int) bean.getCountDown() / 1000 % 3600 / 60, (int) bean.getCountDown() / 1000 % 60);
            bindingView.timer.start();
        } else if (bean.getDataStatus() == 200) {
            bindingView.tvState.setText("距本场结束");
            bindingView.timer.setTime((int) bean.getCountDown() / 1000 / 3600, (int) bean.getCountDown() / 1000 / 60 % 60, (int) bean.getCountDown() / 1000 % 60);
            bindingView.timer.start();
        } else {
            bindingView.tvState.setText("本场已结束");
            bindingView.timer.setVisibility(View.GONE);
        }
        getData();
        showContentView();
    }

    public void getData() {
        viewModel.GetActivityGoods(bean.getPrId(), bean.getIsPriceType(), page++, page_size, bean.getDataStatus(), new onResponseListener<List<PromotionGoodsBean>>() {
            @Override
            public void onSuccess(List<PromotionGoodsBean> promotionGoodsBeans) {
                if (promotionGoodsBeans.size() < page_size) {
                    bindingView.srSecKill.finishLoadMoreWithNoMoreData();
                } else {
                    bindingView.srSecKill.finishLoadMore(true);
                }
                bindingView.srSecKill.finishRefresh(true);
                adapter.add(promotionGoodsBeans);
            }

            @Override
            public void onFailed(Throwable throwable) {
                bindingView.srSecKill.finishLoadMore(false);
                bindingView.srSecKill.finishRefresh(false);
            }
        });
    }

    @Override
    public int setContent() {
        return R.layout.fragment_second_kill;
    }
}
