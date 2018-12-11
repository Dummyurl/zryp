package com.shunmai.zryp.ui.wallet;

import android.os.Bundle;

import com.google.gson.Gson;
import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.wallet.WalletListBean;
import com.shunmai.zryp.databinding.ActivityWithdrawDetailBinding;
import com.ysy.commonlib.base.SwipeBackActivity;

public class WithdrawDetailActivity extends SwipeBackActivity<ActivityWithdrawDetailBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_detail);
        showContentView();
        bindingView.setBean(new Gson().fromJson(getIntent().getStringExtra("data"), WalletListBean.class));
    }
}
