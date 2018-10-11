package com.shunmai.zryp.ui.userinfo.order;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityScoreBinding;

public class ScoreActivity extends SwipeBackActivity {
    ActivityScoreBinding biding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        biding=  DataBindingUtil.setContentView(this,R.layout.activity_score);
        initWindow(this);
        initView();
    }

    private void initView() {
       biding.layoutScoreA.getRoot().findViewById(R.id.tv_expense).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(ScoreActivity.this,UserApproveActivity.class);
               startActivity(intent);
           }
       });

    }

}
