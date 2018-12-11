package com.shunmai.zryp.ui.userinfo.score;

import android.os.Bundle;

import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.wallet.ScoreDataBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.viewmodel.ScoreViewModel;
import com.ysy.commonlib.base.MVVMActivity;
import com.shunmai.zryp.databinding.ActivityExchangeScoreBinding;
import com.shunmai.zryp.utils.ShareUtils;

public class ExchangeScoreActivity extends MVVMActivity<ActivityExchangeScoreBinding,ScoreViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchange_score);
        showContentView();
        bindingView.setUserInfo(ShareUtils.getUserInfo());
        getData();
    }
    public void getData(){
        mViewModel.GetExchangeData(new onResponseListener<ScoreDataBean>() {
            @Override
            public void onSuccess(ScoreDataBean scoreDataBean) {

            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }
}
