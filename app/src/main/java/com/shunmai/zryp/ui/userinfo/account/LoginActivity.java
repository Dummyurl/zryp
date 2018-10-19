package com.shunmai.zryp.ui.userinfo.account;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityLoginBinding;
import com.shunmai.zryp.eventhandler.userinfo.LoginHandler;
import com.shunmai.zryp.viewmodel.LoginViewModel;

public class LoginActivity extends SwipeBackActivity<ActivityLoginBinding> {
    private LoginHandler handler;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWindow(this);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        initHandler();
        initView();
        showContentView();
    }

    private void initView() {
        bindingView.cbPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                bindingView.etLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
                bindingView.etLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });
    }

    private void initHandler() {
        handler = new LoginHandler(viewModel,this,bindingView);
        bindingView.setHandler(handler);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            onBackPressed();
        }
    }
}

