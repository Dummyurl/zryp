package com.shunmai.zryp.ui.userinfo.score;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shunmai.zryp.R;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.databinding.ActivityExchangeScoreBinding;
import com.shunmai.zryp.utils.ShareUtils;

public class ExchangeScoreActivity extends SwipeBackActivity<ActivityExchangeScoreBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_score);
        showContentView();
        bindingView.setUserInfo(ShareUtils.getUserInfo());
    }
}
