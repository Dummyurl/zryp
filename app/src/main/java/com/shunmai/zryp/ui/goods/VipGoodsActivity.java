package com.shunmai.zryp.ui.goods;

import android.os.Bundle;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.goods.VipGoodsAdapter;
import com.shunmai.zryp.databinding.ActivityVipGoodsBinding;
import com.shunmai.zryp.viewmodel.VipGoodsViewModel;
import com.ysy.commonlib.base.MVVMActivity;

import java.util.ArrayList;

public class VipGoodsActivity extends MVVMActivity<ActivityVipGoodsBinding, VipGoodsViewModel> {
    private VipGoodsAdapter adapter;
    private int pageNum = 1;
    private int pageSize = 5;
    private boolean canLoad = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_goods);
        mViewModel.getLiveData().observe(this, goodsPromotionBean -> {
            canLoad=true;
            if (goodsPromotionBean.getPromotionGoods().size() < 5) {
                bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
                bindingView.refreshLayout.setNoMoreData(true);
            } else {
                bindingView.refreshLayout.setNoMoreData(false);
                bindingView.refreshLayout.finishLoadMore(true);
            }
            bindingView.refreshLayout.finishRefresh(true);
            adapter.add(goodsPromotionBean.getPromotionGoods());
            showContentView();
        });
        adapter = new VipGoodsAdapter(this, new ArrayList<>());
        bindingView.vipGoodsRec.setAdapter(adapter);
        bindingView.refreshLayout.setEnableLoadMore(true);
        bindingView.refreshLayout.setOnLoadMoreListener(refreshLayout -> getData());
        bindingView.refreshLayout.setOnRefreshListener(refreshLayout -> {
            pageNum = 1;
            adapter.clear();
            getData();
        });
        getData();

    }

    private void getData() {
        if (canLoad) {
            mViewModel.getVipGoods(pageNum++, pageSize, throwable -> {
                bindingView.refreshLayout.finishRefresh(false);
                showError();
                canLoad=true;
            });
            canLoad=false;
        }
    }

    @Override
    protected void onRefresh() {
        getData();
    }
}
