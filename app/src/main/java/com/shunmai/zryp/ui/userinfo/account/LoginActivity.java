package com.shunmai.zryp.ui.userinfo.account;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityLoginBinding;

public class LoginActivity extends SwipeBackActivity<ActivityLoginBinding> implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWindow(this);
        bindingView.tvRegister.setOnClickListener(this);
        bindingView.tvForgetPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_register:{
                Intent intent = new Intent(LoginActivity.this,UserHandleActivity.class);
                intent.putExtra("type",0);
                startActivity(intent);
                break;
            }
            case R.id.tv_forget_password:{
                Intent intent = new Intent(LoginActivity.this,UserHandleActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            }
        }
    }
}

