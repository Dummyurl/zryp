package com.shunmai.zryp.ui.userinfo.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.google.gson.Gson;
import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.bean.userinfo.Response_WechatUserInfo;
import com.shunmai.zryp.databinding.ActivityBindPhoneBinding;
import com.shunmai.zryp.eventhandler.userinfo.BindPhoneHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.ui.home.HomeActivity;
import com.shunmai.zryp.utils.PatternUtils;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.StateCheckUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.WechatLoginViewModel;
import com.ysy.commonlib.base.MVVMActivity;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

public class BindPhoneActivity extends MVVMActivity<ActivityBindPhoneBinding, WechatLoginViewModel> implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        showContentView();
        bindingView.setHandler(new BindPhoneHandler(bindingView, mViewModel));
        bindingView.etPhone.addTextChangedListener(watcher);
        bindingView.etPassCode.addTextChangedListener(watcher);
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
            checkButton();
        }
    };

    public void checkButton() {
        boolean isLight = PatternUtils.isChinaPhoneLegal(bindingView.etPhone.getText().toString().trim()) && PatternUtils.isCode(bindingView.etPassCode.getText().toString().trim());
        StateCheckUtils.checkClickable(isLight, bindingView.btnSubmit, getResources().getDrawable(R.drawable.shape_rect_deep_red_gradient), getResources().getDrawable(R.drawable.shape_rect_red_gradient), BindPhoneActivity.this);
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_submit:{
                    HashMap<String,Object>  map=new HashMap<>();
                    Response_WechatUserInfo wechat_info = new Gson().fromJson(getIntent().getStringExtra("wechat_info"), Response_WechatUserInfo.class);
                    map.put("unionId",wechat_info.getUnionid());
                    map.put("nickname",wechat_info.getNickname());
                    map.put("xopenId",wechat_info.getOpenid());
                    map.put("pic",wechat_info.getHeadimgurl());
                    map.put("mobile",bindingView.etPhone.getText().toString().trim());
                    String invitationCode = getIntent().getStringExtra("invitationCode");
                    if (invitationCode==null||invitationCode.equals("")){
                        invitationCode="";
                    }
                    mViewModel.RegistAuthentication(map, bindingView.etPassCode.getText().toString().trim(), invitationCode, new onResponseListener<TResponse<UserInfoBean>>() {
                        @Override
                        public void onSuccess(TResponse<UserInfoBean> beanTResponse) {
                            if (beanTResponse.getStatus()==401){
                                ToastUtils.showToast(beanTResponse.getMsg());
                                return;
                            }
                            ShareUtils.putUserInfo(beanTResponse.getData());
                            startActivity(new Intent(BindPhoneActivity.this, HomeActivity.class));
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
