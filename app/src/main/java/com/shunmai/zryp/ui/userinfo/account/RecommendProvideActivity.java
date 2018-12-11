package com.shunmai.zryp.ui.userinfo.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityRecommendProvideBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.StateCheckUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.WechatLoginViewModel;
import com.ysy.commonlib.base.MVVMActivity;
import com.ysy.commonlib.base.TResponse;

public class RecommendProvideActivity extends MVVMActivity<ActivityRecommendProvideBinding, WechatLoginViewModel> implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_provide);
        showContentView();
        bindingView.etInvitationCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean isLight = false;
                if (s.toString().length() == 8) {
                    isLight = true;
                }
                StateCheckUtils.checkClickable(isLight, bindingView.btnSubmit, getResources().getDrawable(R.drawable.shape_rect_deep_red_gradient), getResources().getDrawable(R.drawable.shape_rect_red_gradient), RecommendProvideActivity.this);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit: {
                String invitationCode = bindingView.etInvitationCode.getText().toString().trim();
                mViewModel.IsAuthentication(bindingView.etInvitationCode.getText().toString().trim(), new onResponseListener<TResponse<Integer>>() {
                    @Override
                    public void onSuccess(TResponse<Integer> data) {
                        if (data.getData() > 0) {
                            Intent intent=new Intent(RecommendProvideActivity.this,BindPhoneActivity.class);
                            intent.putExtra("wechat_info",getIntent().getStringExtra("wechat_info"));
                            intent.putExtra("invitationCode",invitationCode);
                            startActivity(intent);
                            finish();
                        } else {
                            bindingView.tvAlert.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                        bindingView.tvAlert.setVisibility(View.VISIBLE);
                    }
                });
                break;
            }
        }

    }
}
