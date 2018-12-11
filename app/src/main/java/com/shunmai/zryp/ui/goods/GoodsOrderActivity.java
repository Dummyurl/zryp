package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.google.gson.Gson;
import com.shunmai.zryp.AppConfig;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.goods.GoodsOrderBean;
import com.shunmai.zryp.bean.goods.OderInfoBean;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.databinding.ActivityGoodsOrderBinding;
import com.shunmai.zryp.eventhandler.goods.GoodsOrderHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.ysy.commonlib.utils.StringUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.Utils;
import com.shunmai.zryp.utils.wechatutils.WechatPayHelper;
import com.shunmai.zryp.viewmodel.GoodsOrderViewModel;

import java.util.HashMap;

public class GoodsOrderActivity extends SwipeBackActivity<ActivityGoodsOrderBinding> implements View.OnClickListener {
    GoodsOrderViewModel viewModel;
    private double postCost;
    private boolean clickable = true;
    private PayBroadcastReceiver mReceiver;

    @Override
    protected boolean isSwipeBackEnable() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order);
        initWindow(this);
        bindingView.setGoods(new Gson().fromJson(getIntent().getStringExtra("data"), GoodsOrderBean.class));
        if (bindingView.getGoods() == null) {
            onBackPressed();
        }
        bindingView.setHandler(new GoodsOrderHandler());
//        price = bindingView.getGoods().getPrice();
        viewModel = ViewModelProviders.of(this).get(GoodsOrderViewModel.class);
        initListener();
    }

    private void initListener() {
        getAdr();
        setCount();
        getPostCost();
        bindingView.btnOrderSubmit.setOnClickListener(this);
        bindingView.tvWechat.setOnClickListener(this);
        bindingView.ivAdd.setOnClickListener(this);
        bindingView.ivSub.setOnClickListener(this);
        mReceiver = new PayBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(AppConfig.PaySuccess);
        intentFilter.addAction(AppConfig.PayFailed);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);
    }

    public void getAdr() {
        if (bindingView.getGoods().getOid() != 0) {
            bindingView.llChangeCount.setVisibility(View.GONE);
            bindingView.btnOrderSubmit.setText("支付订单");
            getAddrById();
        } else {
            getDefaultAddress();
        }
    }

    private void getDefaultAddress() {
        viewModel.GetDefaultAddress(bindingView.getGoods().getIsOutAddress(), new onResponseListener<AddressListBean.DataBean>() {
            @Override
            public void onSuccess(AddressListBean.DataBean dataBean) {
                bindingView.setAddress(dataBean);
                showContentView();
            }

            @Override
            public void onFailed(Throwable throwable) {
                showContentView();
                bindingView.setAddress(null);
            }
        });
    }

    private void getAddrById() {
        viewModel.getAdrById(bindingView.getGoods().getAddrId(), new onResponseListener<AddressListBean.DataBean>() {
            @Override
            public void onSuccess(AddressListBean.DataBean dataBean) {
                bindingView.setAddress(dataBean);
                showContentView();
            }

            @Override
            public void onFailed(Throwable throwable) {
                bindingView.setAddress(null);
                showContentView();
            }
        });
    }

    private void getPostCost() {
        viewModel.GetPostCost(bindingView.getGoods().getGoodsId(), bindingView.getGoods().getSkuId(), bindingView.getGoods().getBuyCount(), new onResponseListener<Double>() {
            @Override
            public void onSuccess(Double price) {
                postCost = price;
                if (price == 0) {
                    bindingView.tvPostFree.setVisibility(View.VISIBLE);
                    bindingView.tvPostPrice.setVisibility(View.GONE);
                } else {
                    bindingView.tvPostFree.setVisibility(View.GONE);
                    bindingView.tvPostPrice.setVisibility(View.VISIBLE);
                    bindingView.tvPostPrice.setText("邮费：￥" + StringUtils.DoubleFormat(price));
                }
                double total_price = bindingView.getGoods().getGoodsPrice() * bindingView.getGoods().getBuyCount() + postCost;
                bindingView.tvPriceTotal.setText("￥" + StringUtils.DoubleFormat(total_price));
                clickable = true;
            }

            @Override
            public void onFailed(Throwable throwable) {
                clickable = true;
                double total_price = bindingView.getGoods().getGoodsPrice() * bindingView.getGoods().getBuyCount() + postCost;
                bindingView.tvPriceTotal.setText("￥" + StringUtils.DoubleFormat(total_price));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            bindingView.setAddress(new Gson().fromJson(data.getStringExtra("address"), AddressListBean.DataBean.class));
        }
    }

    private void setCount() {
        bindingView.tvCount.setText(bindingView.getGoods().getBuyCount() + "");
        bindingView.tvCountTotal.setText(bindingView.getGoods().getBuyCount() + "");
        bindingView.tvBuyCount.setText("x " + bindingView.getGoods().getBuyCount());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAdr();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_submit: {
                if (Utils.checkLogin(this) && bindingView.getAddress() != null) {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("goods_id", bindingView.getGoods().getGoodsId());
                    map.put("sku_id", bindingView.getGoods().getSkuId());
                    map.put("oid", bindingView.getGoods().getOid());
                    map.put("poid", 0);
                    map.put("text", bindingView.etUserNote.getText().toString().trim());
                    map.put("attr", bindingView.getAddress().getAddr());
                    viewModel.OderInfoBean(map, bindingView.getAddress().getId(), bindingView.getGoods().getBuyCount(), 0, new onResponseListener<OderInfoBean>() {
                        @Override
                        public void onSuccess(OderInfoBean oderInfoBean) {
                            new WechatPayHelper(oderInfoBean, GoodsOrderActivity.this).doPay();
                        }

                        @Override
                        public void onFailed(Throwable throwable) {
                            ToastUtils.showToast("提交订单失败,请稍后再试!");
                        }
                    });
                }
                break;
            }
            case R.id.tv_wechat: {
                break;
            }
            case R.id.iv_add: {
                if (clickable) {
                    bindingView.getGoods().setBuyCount(bindingView.getGoods().getBuyCount() + 1);
                    getPostCost();
                    setCount();
                    clickable = false;
                }
                break;
            }
            case R.id.iv_sub: {
                if (clickable) {
                    if (bindingView.getGoods().getBuyCount() <= 1) {
                        return;
                    }
                    bindingView.getGoods().setBuyCount(bindingView.getGoods().getBuyCount() - 1);
                    setCount();
                    getPostCost();
                    clickable = false;
                }
                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    @Override
    public boolean supportRequestWindowFeature(int featureId) {
        return super.supportRequestWindowFeature(featureId);
    }

    private class PayBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppConfig.PaySuccess)) {
                finish();
            } else if (intent.getAction().equals(AppConfig.PayFailed)) {
                finish();
            }
        }
    }
}
