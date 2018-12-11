package com.shunmai.zryp.ui.wallet;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityWithdrawBinding;
import com.shunmai.zryp.eventhandler.userinfo.WithdrawHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.PatternUtils;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.StateCheckUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.WithdrawViewModel;
import com.ysy.commonlib.base.MVVMActivity;

import java.util.HashMap;

public class WithdrawActivity extends MVVMActivity<ActivityWithdrawBinding, WithdrawViewModel> implements View.OnClickListener {
    private double count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);
        showContentView();
        mViewModel.getLiveData().observe(this, restBean -> bindingView.setBean(restBean));
        bindingView.setUserInfo(ShareUtils.getUserInfo());
        bindingView.setHandler(new WithdrawHandler());
        bindingView.setViewModel(mViewModel);
        getData();
        bindingView.etCount.addTextChangedListener(watcher);
        bindingView.etPassCode.addTextChangedListener(watcher);
        bindingView.tvAllCount.setOnClickListener(this);
    }

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkClickable();
        }
    };

    public void getData() {
        mViewModel.WalletAmount(throwable -> {
            showError();
            ToastUtils.showToast(throwable.getMessage());
        });
    }

    @Override
    protected void onRefresh() {
        getData();
    }

    public void checkClickable() {
        if (bindingView.etCount.getText().toString().length() > 0 && PatternUtils.isDouble(bindingView.etCount.getText().toString())) {
            count = Double.parseDouble(bindingView.etCount.getText().toString());
        } else {
            count = 0;
        }
        boolean light = count<=bindingView.getBean().getAmount()&&count > bindingView.getBean().getLeastAmount() && PatternUtils.isCode(bindingView.etPassCode.getText().toString());
        StateCheckUtils.checkClickable(light, bindingView.btnSubmit, getResources().getDrawable(R.drawable.shape_rect_gold), WithdrawActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            bindingView.setUserInfo(ShareUtils.getUserInfo());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_all_count:{
                bindingView.etCount.setText(bindingView.getBean().getAmount()+"");
                break;
            }
            case R.id.btn_submit:{
                HashMap<String, Object> map=new HashMap<>();
                map.put("amout",count);
                map.put("bankCard",ShareUtils.getUserInfo().getCardNumber());
                map.put("bankCardAccount",ShareUtils.getUserInfo().getRealname());
                map.put("bankName",ShareUtils.getUserInfo().getCardBank());
                map.put("bankCode",ShareUtils.getUserInfo().getBankCode());
                map.put("phone",ShareUtils.getUserInfo().getMobile());
                map.put("code",bindingView.etPassCode.getText().toString());
                mViewModel.UserWithdraw(map, new onResponseListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        startActivity(new Intent(WithdrawActivity.this, WithdrawListActivity.class));
                        finish();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });
                 break;
            }
        }
    }
}
