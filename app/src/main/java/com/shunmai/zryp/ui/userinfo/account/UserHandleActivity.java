package com.shunmai.zryp.ui.userinfo.account;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.bean.userinfo.Response_WechatUserInfo;
import com.shunmai.zryp.databinding.LayoutBindPhoneBinding;
import com.shunmai.zryp.databinding.LayoutRegisterBinding;
import com.shunmai.zryp.databinding.LayoutSetPasswordBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityUserHandleBinding;
import com.shunmai.zryp.utils.PatternUtils;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.UserHandleViewModel;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 操作账户的activity 用于修改密码，设置密码，注册，三方登录后绑定等操作
 */
public class UserHandleActivity extends SwipeBackActivity<ActivityUserHandleBinding> implements View.OnClickListener {
    /**
     * 0：注册 1：找回密码 2：设置密码 3：绑定手机
     */
    private int Type = 0;
    private LayoutBindPhoneBinding bindPhoneBinding;
    private Disposable disposable;
    private UserHandleViewModel viewModel;
    private String logonAccount;
    private int userId;
    private LayoutSetPasswordBinding setPasswordBinding;
    private LayoutRegisterBinding regBinding;
    private Response_WechatUserInfo wechatUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_handle);
        initWindow(this);
        Type = getIntent().getIntExtra("type", 0);
        showContentView();
        viewModel = ViewModelProviders.of(this).get(UserHandleViewModel.class);
        switch (Type) {
            case 0: {
                bindingView.titleBar.getCenterTextView().setText("注册");
                bindingView.vsRegister.getViewStub().inflate().setVisibility(View.VISIBLE);
                regBinding = DataBindingUtil.bind(bindingView.vsRegister.getRoot());
                regBinding.tvRegisterGetCode.setOnClickListener(this);
                regBinding.btnRegister.setOnClickListener(this);
                regBinding.tvLogin.setOnClickListener(this);
                break;
            }
            case 1: {
                bindingView.titleBar.getCenterTextView().setText("找回密码");
                View inflate = bindingView.vsFindPassword.getViewStub().inflate();
                inflate.setVisibility(View.VISIBLE);
                inflate.findViewById(R.id.btn_next).setOnClickListener(UserHandleActivity.this);
                break;
            }
            case 2: {
                bindingView.titleBar.getCenterTextView().setText("设置新密码");
                View inflate = bindingView.vsSetPassword.getViewStub().inflate();
                inflate.setVisibility(View.VISIBLE);
                logonAccount = getIntent().getStringExtra("logonAccount");
                userId = getIntent().getIntExtra("userId", 0);
                setPasswordBinding = DataBindingUtil.bind(bindingView.vsSetPassword.getRoot());
                setPasswordBinding.btnSetPasswordSubmit.setOnClickListener(this);
                break;
            }
            case 3: {
                bindingView.titleBar.getCenterTextView().setText("绑定手机");
                bindingView.vsBind.getViewStub().inflate().setVisibility(View.VISIBLE);
                userId = getIntent().getIntExtra("userId", 0);
                bindPhoneBinding = DataBindingUtil.bind(bindingView.vsBind.getRoot());
                wechatUserInfo = new Gson().fromJson(getIntent().getStringExtra("data"), Response_WechatUserInfo.class);
                bindPhoneBinding.tvWechat.setText("您的微信账号" + wechatUserInfo.getNickname() + "通过验证");
                bindPhoneBinding.btnBindGetcode.setOnClickListener(this);
                bindPhoneBinding.btnBind.setOnClickListener(this);
                break;
            }

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next: {
                Intent intent = new Intent(this, UserHandleActivity.class);
                intent.putExtra("type", 2);
                startActivity(intent);
                break;
            }
            //
            case R.id.btn_set_password_submit: {
                if (!PatternUtils.isPassword(setPasswordBinding.etSetPassword.getText().toString().trim())) {
                    ToastUtils.showToast("密码由6-20位字符组成，使用数字、字母的组合！");
                    return;
                }
                if (!setPasswordBinding.etSetPassword.getText().toString().trim().equals(setPasswordBinding.etVerifySetPassword.getText().toString().trim())) {
                    ToastUtils.showToast("两次密码不一致！");
                    return;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("logonPassword", setPasswordBinding.etVerifySetPassword.getText().toString().trim());
                map.put("logonAccount", logonAccount);
                map.put("userId", userId);
                viewModel.setPassword(map, new onResponseListener<TResponse<UserInfoBean>>() {
                    @Override
                    public void onSuccess(TResponse<UserInfoBean> data) {
                        ShareUtils.putUserInfo(data.getData());
                        onBackPressed();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });

                break;
            }
            case R.id.btn_bind_getcode: {
                if (!PatternUtils.isChinaPhoneLegal(bindPhoneBinding.etBindPhone.getText().toString().trim())) {
                    ToastUtils.showToast("电话号码格式错误，请检查后再输入！");
                    return;
                }
                viewModel.getCode(1, bindPhoneBinding.etBindPhone.getText().toString().trim(), new onResponseListener<TResponse<String>>() {
                    @Override
                    public void onSuccess(TResponse<String> stringTResponse) {
                        codeTime((TextView) v);
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });
                break;
            }
            case R.id.btn_bind: {
                //验证输入数据格式
                if (!PatternUtils.isChinaPhoneLegal(bindPhoneBinding.etBindPhone.getText().toString().trim())) {
                    ToastUtils.showToast("电话号码格式错误，请检查后再输入！");
                    return;
                }
                if (!PatternUtils.isCode(bindPhoneBinding.etBindCode.getText().toString().trim())) {
                    ToastUtils.showToast("验证码格式错误！");
                    return;
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("mobile", bindPhoneBinding.etBindPhone.getText().toString().trim());
                map.put("unionId", wechatUserInfo.getUnionid());
                map.put("pic", wechatUserInfo.getHeadimgurl());
                map.put("nickname", wechatUserInfo.getNickname());
                map.put("gopenId", wechatUserInfo.getOpenid());
                map.put("gender", wechatUserInfo.getSex());
                map.put("userId", userId);
                viewModel.bindPhone(map, bindPhoneBinding.etBindCode.getText().toString().trim(), new onResponseListener<TResponse<UserInfoBean>>() {
                    @Override
                    public void onSuccess(TResponse<UserInfoBean> bean) {
                        if (bean.getCode() == 201 || bean.getCode() == 202) {
                            //微信绑定成功，但是绑定账号未设置密码！
                            ToastUtils.showToast("该手机未注册，请设置密码！");
                            Intent intent = new Intent(UserHandleActivity.this, UserHandleActivity.class);
                            intent.putExtra("type", 2);
//                            intent.putExtra("logonAccount", bean.getData().getLogonAccount());
                            intent.putExtra("userId", bean.getData().getUserId());
                            startActivity(intent);
                        } else if (bean.getCode() == 200) {
                            //如果为200则绑定账号成功，而且绑定账号已设置密码，直接退出
                            ShareUtils.putUserInfo(bean.getData());
                            ToastUtils.showToast("微信绑定成功！");
                        }
                        finish();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });
                break;
            }
            case R.id.tv_register_get_code: {
                if (!PatternUtils.isChinaPhoneLegal(regBinding.etRegisterPhone.getText().toString().trim())) {
                    ToastUtils.showToast("电话号码格式错误，请检查后再输入！");
                    return;
                }
                viewModel.getCode(1, regBinding.etRegisterPhone.getText().toString().trim(), new onResponseListener<TResponse<String>>() {

                    @Override
                    public void onSuccess(TResponse<String> stringTResponse) {
                        codeTime((TextView) v);
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });
                break;
            }
            case R.id.btn_register: {
                if (!regBinding.cbUserRule.isChecked()) {
                    ToastUtils.showToast("请先阅读《智融优品用户协议》");
                    return;
                }
                if (!PatternUtils.isChinaPhoneLegal(regBinding.etRegisterPhone.getText().toString().trim())) {
                    ToastUtils.showToast("电话号码格式错误，请检查后再注册！");
                    return;
                }
                if (!PatternUtils.isCode(regBinding.etRegisterCode.getText().toString().trim())) {
                    ToastUtils.showToast("验证码格式错误！");
                    return;
                }
                if (!PatternUtils.isPassword(regBinding.etRegisterPassword.getText().toString().trim())) {
                    ToastUtils.showToast("密码由6-20位字符组成，使用数字、字母的组合！");
                    return;
                }
                HashMap<String, String> map = new HashMap<>();
                map.put("mobile", regBinding.etRegisterPhone.getText().toString().trim());
                map.put("logonPassword", regBinding.etRegisterPassword.getText().toString().trim());
                viewModel.register(map, regBinding.etRegisterCode.getText().toString().trim(), new onResponseListener<TResponse<UserInfoBean>>() {
                    @Override
                    public void onSuccess(TResponse<UserInfoBean> data) {
                        if (data.getCode() == 200) {
                            ShareUtils.putUserInfo(data.getData());
                            setResult(RESULT_OK);
                            finish();
                        }else{
                            ToastUtils.showToast(data.getMsg());
                        }
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });

                break;
            }
            case R.id.tv_login: {
                onBackPressed();
                break;
            }

        }
    }

    private void codeTime(TextView tvGetCode) {
        tvGetCode.setOnClickListener(null);
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        disposable = interval.take(180).map(aLong -> 180 - aLong).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            if (tvGetCode != null) {
                tvGetCode.setText(aLong + "秒");
            }
            if (aLong == 1) {
                tvGetCode.setOnClickListener(this);
                tvGetCode.setText("获取验证码");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
