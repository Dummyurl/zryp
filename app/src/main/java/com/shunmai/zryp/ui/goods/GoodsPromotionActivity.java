package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.goods.GoodsPromotionAdapter;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.bean.TitleBean;
import com.shunmai.zryp.bean.goods.GoodsPromotionBean;
import com.shunmai.zryp.databinding.ActivityGoodsPromotionBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.GoodsPromotionViewModel;

import java.util.ArrayList;

/**
 * 商品促销活动页面
 */
public class GoodsPromotionActivity extends SwipeBackActivity<ActivityGoodsPromotionBinding> {

    private int prId;
    private GoodsPromotionAdapter adapter;
    GoodsPromotionViewModel viewModel;
    int type;
    int pageNum = 1;
    int pageSize = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_promotion);
        viewModel = ViewModelProviders.of(this).get(GoodsPromotionViewModel.class);
        prId = getIntent().getIntExtra("prId", -1);
        type = getIntent().getIntExtra("type", 1);
        if (type == 1) {
            bindingView.setTitle(new TitleBean().setTitle("爆赚积分"));
        } else if (type == 2) {
            bindingView.setTitle(new TitleBean().setTitle("天天特价"));
            bindingView.nsPro.setBackgroundColor(Color.parseColor("#971D05"));
            bindingView.tvDescription.setText("天天特价");
        }
        showContentView();
        initView();
    }

    private void initView() {
        bindingView.rvPromotion.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new GoodsPromotionAdapter(this, new ArrayList<>(), type);
        bindingView.rvPromotion.setAdapter(adapter);
        getData();
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        getData();
    }

    private void getData() {
        if (prId == -1) {
            ToastUtils.showToast("活动错误！");
            showError();
        } else {
            viewModel.GetScorePromotion(prId, pageNum, pageSize, new onResponseListener<GoodsPromotionBean>() {
                @Override
                public void onSuccess(GoodsPromotionBean goodsPromotionBean) {
                    adapter.add(goodsPromotionBean.getPromotionGoods());
                    bindingView.setBean(goodsPromotionBean);
                }

                @Override
                public void onFailed(Throwable throwable) {
                    ToastUtils.showToast("活动错误！");
                    showError();
                }
            });
        }
    }
}
