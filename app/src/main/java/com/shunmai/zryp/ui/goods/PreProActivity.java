package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.goods.GoodsPromotionAdapter;
import com.shunmai.zryp.adapter.home.FlashSaleRecAdapter;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.goods.GoodsPromotionBean;
import com.shunmai.zryp.databinding.ActivityPreProBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.GoodsPromotionViewModel;

import java.util.ArrayList;

public class PreProActivity extends SwipeBackActivity<ActivityPreProBinding> {
    GoodsPromotionViewModel viewModel;
    private int prId;
    private FlashSaleRecAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_pro);
        viewModel = ViewModelProviders.of(this).get(GoodsPromotionViewModel.class);
        prId = getIntent().getIntExtra("prId", -1);
        showContentView();
        initView();
    }

    private void initView() {
        bindingView.rvPromotion.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter= new FlashSaleRecAdapter(this,new ArrayList<>());
        bindingView.rvPromotion.setAdapter(adapter);
        getData();
    }
    private void getData() {
        if (prId == -1) {
            ToastUtils.showToast("活动错误！");
            showError();
        } else {
            viewModel.GetScorePromotion(prId, new onResponseListener<GoodsPromotionBean>() {
                @Override
                public void onSuccess(GoodsPromotionBean goodsPromotionBean) {
                    bindingView.setBean(goodsPromotionBean);
                    adapter.add(goodsPromotionBean.getPromotionGoods());
                }

                @Override
                public void onFailed(Throwable throwable) {

                }
            });
        }
    }
}
