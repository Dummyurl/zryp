package com.shunmai.zryp.ui.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.gson.Gson;
import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.BankBean;
import com.shunmai.zryp.bean.BankCardBean;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.databinding.ActivityWithDrawInfoBinding;
import com.shunmai.zryp.eventhandler.userinfo.WithDrawInfoHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.PatternUtils;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.StateCheckUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.WithDrawViewInfoModel;
import com.ysy.commonlib.base.MVVMActivity;

import java.util.HashMap;

public class WithDrawInfoActivity extends MVVMActivity<ActivityWithDrawInfoBinding, WithDrawViewInfoModel> implements View.OnClickListener {

    private BankBean bankInfo;
    private boolean isFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_draw_info);
        bindingView.setHandler(new WithDrawInfoHandler());
        showContentView();
        bindingView.setUserInfo(ShareUtils.getUserInfo());
        isFirst = getIntent().getBooleanExtra("isFirst", true);
        if (!isFirst) {
            bindingView.btnSubmit.setText("保存");
            bankInfo=new BankBean();
            bankInfo.setBankcode(bindingView.getUserInfo().getBankCode());
            bankInfo.setBankname(bindingView.getUserInfo().getCardBank());
            bindingView.tvBankName.setText(bankInfo.getBankname());
            bindingView.etBankCard.setText(bindingView.getUserInfo().getCardNumber());
            checkClickable( bindingView.etBankCard.getText().toString());
        } else {
            bindingView.btnSubmit.setText("下一步");
        }
        bindingView.etBankCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                checkClickable(s.toString());
            }
        });
    }

    public void checkClickable(String str) {
        StateCheckUtils.checkClickable(PatternUtils.isBankCard(str) && bankInfo != null,bindingView.btnSubmit,getResources().getDrawable(R.drawable.shape_rect_gold),WithDrawInfoActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            bankInfo = new Gson().fromJson(data.getStringExtra("bankInfo"), BankBean.class);
            bindingView.tvBankName.setText(bankInfo.getBankname());
            checkClickable(bindingView.etBankCard.getText().toString());
        }
    }

    @Override
    public void onClick(View v) {
        HashMap<String, String> map = new HashMap<>();
        map.put("cardNumber", bindingView.etBankCard.getText().toString());
        map.put("cardBank", bankInfo.getBankname());
        map.put("bankCardAccount", ShareUtils.getUserInfo().getRealname());
        map.put("bankCode", bankInfo.getBankcode());
        mViewModel.UpdateBankCardInfo(map, new onResponseListener<BankCardBean>() {
            @Override
            public void onSuccess(BankCardBean bankCardBean) {
                UserInfoBean userInfo = ShareUtils.getUserInfo();
                userInfo.setCardBank(bankCardBean.getCardBank());
                userInfo.setCardNumber(bankCardBean.getCardNumber());
                ShareUtils.putUserInfo(userInfo);
                if (isFirst) {
                    startActivity(new Intent(WithDrawInfoActivity.this,WithdrawActivity.class));
                }else{
                    setResult(RESULT_OK);
                }
                onBackPressed();
            }

            @Override
            public void onFailed(Throwable throwable) {
                ToastUtils.showToast(throwable.getMessage());
            }
        });

    }
}
