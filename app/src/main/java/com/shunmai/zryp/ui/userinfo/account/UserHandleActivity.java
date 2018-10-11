package com.shunmai.zryp.ui.userinfo.account;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.ui.home.HomeActivity;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityUserHandleBinding;

/**
 * 操作账户的activity 用于修改密码，设置密码，注册，三方登录后绑定等操作
 */
public class UserHandleActivity extends SwipeBackActivity implements View.OnClickListener{
    /**
     * 0：注册 1：找回密码 2：设置密码 3：绑定手机
     */
    private int Type=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityUserHandleBinding databinding=DataBindingUtil.setContentView(this,R.layout.activity_user_handle);
        initWindow(this);
        Type=getIntent().getIntExtra("type",0);
        switch (Type){
            case 0:{
                databinding.titleBar.getCenterTextView().setText("注册");
                databinding.vsRegister.getViewStub().inflate().setVisibility(View.VISIBLE);
                break;
            }
            case 1:{
                databinding.titleBar.getCenterTextView().setText("找回密码");
                View inflate = databinding.vsFindPassword.getViewStub().inflate();
                inflate. setVisibility(View.VISIBLE);
                inflate.findViewById(R.id.btn_next).setOnClickListener(UserHandleActivity.this);

                break;
            }
            case 2:{
                databinding.titleBar.getCenterTextView().setText("设置新密码");
                View inflate = databinding.vsSetPassword.getViewStub().inflate();
                inflate.setVisibility(View.VISIBLE);
                inflate.findViewById(R.id.btn_sure).setOnClickListener(UserHandleActivity.this);
                break;
            }
            case 3:{
                databinding.titleBar.getCenterTextView().setText("绑定手机");
                databinding.vsBind.getViewStub().inflate().setVisibility(View.VISIBLE);
                break;
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:{
                Intent intent = new Intent(this, UserHandleActivity.class);
                intent.putExtra("type",2);
                startActivity(intent);
                break;
            }
            case R.id.btn_sure:{
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
