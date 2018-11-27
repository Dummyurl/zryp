package com.shunmai.zryp.ui.userinfo.account;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.ItemDecoration;
import com.shunmai.zryp.adapter.account.CollectAdapter;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.bean.userinfo.CollectBean;
import com.shunmai.zryp.databinding.ActivityCollectBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.CollectViewModel;

import java.util.ArrayList;
import java.util.List;

public class CollectActivity extends SwipeBackActivity<ActivityCollectBinding> implements CollectAdapter.onDeleteClickListener {

    private CollectAdapter adapter;
    private CollectViewModel viewModel;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        viewModel = ViewModelProviders.of(this).get(CollectViewModel.class);
        initView();
    }


    private void initView() {
        bindingView.rvCollect.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        bindingView.rvCollect.addItemDecoration(new DividerItemDecoration(this, android.support.v7.widget.DividerItemDecoration.VERTICAL));
        adapter = new CollectAdapter(this, new ArrayList<>(), this);
        bindingView.rvCollect.setAdapter(adapter);
        bindingView.refreshLayout.setEnableLoadMore(true);
        bindingView.refreshLayout.setEnableAutoLoadMore(true);
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                adapter.clear();
                getData();
            }
        });
        bindingView.refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        getData();
    }

    private void getData() {
        viewModel.GetCollectGoods(page++, new onResponseListener<List<CollectBean>>() {
            @Override
            public void onSuccess(List<CollectBean> collectBeans) {
                adapter.add(collectBeans);
                bindingView.refreshLayout.finishRefresh(true);
                if (collectBeans.size() < 20) {
                    bindingView.refreshLayout.setNoMoreData(true);
                    bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    bindingView.refreshLayout.finishLoadMore(true);
                }
                showContentView();
            }

            @Override
            public void onFailed(Throwable throwable) {
                showError();
                bindingView.refreshLayout.finishRefresh(false);
                bindingView.refreshLayout.finishLoadMore(false);
            }
        });
    }

    @Override
    protected void onRefresh() {
        getData();
    }

    @Override
    public void onDelete(int position, int collectId) {
        viewModel.DeleteCollectItem(collectId, new onResponseListener<Object>() {
            @Override
            public void onSuccess(Object o) {
                adapter.deleteForIndex(position);
            }

            @Override
            public void onFailed(Throwable throwable) {
                ToastUtils.showToast(throwable.getMessage());
            }
        });
    }
}
