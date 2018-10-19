package com.shunmai.zryp.ui.userinfo.order;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.shunmai.zryp.R;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.databinding.ActivityUserApproveBinding;
import com.shunmai.zryp.eventhandler.userinfo.ApproveHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.ApproveViewModel;

public class UserApproveActivity extends SwipeBackActivity<ActivityUserApproveBinding> implements onResponseListener<TResponse<String>> {
    private ApproveHandler handler;
    private ApproveViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow(this);
        setContentView(R.layout.activity_user_approve);
        showContentView();
        viewModel = ViewModelProviders.of(this).get(ApproveViewModel.class);
        handler = new ApproveHandler(bindingView,viewModel,this);
        bindingView.setHandler(handler);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.onDestroy();
    }

    @Override
    public void onSuccess(TResponse<String> o) {
        ToastUtils.showToast("绑定成功！");
        onBackPressed();
    }

    @Override
    public void onFailed(Throwable throwable) {
        ToastUtils.showToast(throwable.getMessage());
    }
}
