package com.shunmai.zryp.ui.wallet;

import android.os.Bundle;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.wallet.WithdrawListAdapter;
import com.shunmai.zryp.bean.wallet.WalletListBean;
import com.shunmai.zryp.databinding.ActivityWithdrawListBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.viewmodel.WithdrawListViewModel;
import com.ysy.commonlib.base.MVVMActivity;
import com.ysy.commonlib.base.TResponse;

import java.util.ArrayList;
import java.util.List;

public class WithdrawListActivity extends MVVMActivity<ActivityWithdrawListBinding, WithdrawListViewModel> {
    private int pageNum = 1;
    private WithdrawListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_list);
        bindingView.refreshLayout.setEnableLoadMore(true);
        bindingView.refreshLayout.setOnLoadMoreListener(refreshLayout -> getData());
        bindingView.refreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNum = 1;
            getData();
            adapter.clear();
        });
        adapter = new WithdrawListAdapter(this, new ArrayList<>());
        bindingView.rvWithList.setAdapter(adapter);
        getData();
    }



    public void getData() {
        mViewModel.GetWithdrawList(pageNum++, 20, new onResponseListener<TResponse<List<WalletListBean>>>() {
            @Override
            public void onSuccess(TResponse<List<WalletListBean>> bean) {
                showContentView();
                if (bean.getTotalCount() <= adapter.getDataSize()||bean.getData().size()<20) {
                    bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
                } else {
                    bindingView.refreshLayout.finishLoadMore(true);
                }
                bindingView.refreshLayout.finishRefresh(true);
                adapter.add(bean.getData());
                bindingView.tvTotalCount.setText(bean.getTotalAmount()+"");
            }

            @Override
            public void onFailed(Throwable throwable) {
                bindingView.refreshLayout.finishLoadMore(false);
                bindingView.refreshLayout.finishRefresh(false);
            }
        });
    }
}
