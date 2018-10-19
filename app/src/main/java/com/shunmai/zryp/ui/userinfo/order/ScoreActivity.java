package com.shunmai.zryp.ui.userinfo.order;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityScoreBinding;

public class ScoreActivity extends SwipeBackActivity<ActivityScoreBinding> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        initWindow(this);
        initView();
        showContentView();
    }

    private void initView() {
       bindingView.layoutScoreA.getRoot().findViewById(R.id.tv_expense).setOnClickListener(v -> {
           Intent intent=new Intent(ScoreActivity.this,UserApproveActivity.class);
           startActivity(intent);
       });

    }

}
