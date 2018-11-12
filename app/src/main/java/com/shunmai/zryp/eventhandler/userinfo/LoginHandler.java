package com.shunmai.zryp.eventhandler.userinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.google.gson.Gson;
import com.shunmai.zryp.base.BaseEventHandler;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.bean.userinfo.Response_WechatUserInfo;
import com.shunmai.zryp.databinding.ActivityLoginBinding;
import com.shunmai.zryp.exception.TaskException;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.ui.userinfo.account.UserHandleActivity;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.wechatutils.WechatLoginHelper;
import com.shunmai.zryp.viewmodel.LoginViewModel;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by yushengyang.
 * Date: 2018/10/16.
 */

public class LoginHandler extends BaseEventHandler {
//    @Inject
//    public LoginHandler(){
//
//    }

    private LoginViewModel viewModel;
    private Context mContext;
    private ActivityLoginBinding binding;

    public LoginHandler(LoginViewModel viewModel, Activity activity, ActivityLoginBinding binding) {
        this.viewModel = viewModel;
        mContext = activity;
        this.binding = binding;
    }

    /**
     * 微信登录
     */
    public void doWechatLogin(View view) {
        WechatLoginHelper.doLogin(mContext, wechatLoginCallBack);
    }

    /**
     * QQ登录
     */
    public void doQQLogin(View view) {
        ToastUtils.showToast("QQ登录功能暂未开放！");
    }

    /**
     * 微博登录
     */
    public void doWeiBoLogin(View view) {
        ToastUtils.showToast("微博登录功能暂未开放！");
    }

    /**
     * 手机号登录
     */
    public void doPhoneLogin() {
        if (binding.etLoginUsername.getText().toString().trim().equals("")) {
            ToastUtils.showToast("请输入用户名!");
            return;
        }
        if (binding.etLoginPassword.getText().toString().trim().equals("")) {
            ToastUtils.showToast("请输入密码!");
            return;
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("mobile", binding.etLoginUsername.getText().toString().trim());
        map.put("logonPassword", binding.etLoginPassword.getText().toString().trim());
        viewModel.Login(map, new onResponseListener<TResponse<UserInfoBean>>() {
            @Override
            public void onSuccess(TResponse<UserInfoBean> bean) {
                ShareUtils.putUserInfo(bean.getData());
                ToastUtils.showToast("登录成功");
                ((Activity) mContext).onBackPressed();
            }

            @Override
            public void onFailed(Throwable throwable) {
                ToastUtils.showToast(throwable.getMessage());

            }
        });
    }

    /**
     * 去用户账户操作界面
     */
    public void toUserHandler(int type) {
        Intent intent = new Intent(mContext, UserHandleActivity.class);
        intent.putExtra("type", type);
        ((Activity) mContext).startActivityForResult(intent, Activity.RESULT_OK);
    }

    WechatLoginHelper.WechatLoginCallBack wechatLoginCallBack = new WechatLoginHelper.WechatLoginCallBack() {
        @Override
        public void onSuccess(Response_WechatUserInfo data) {
            HashMap<String, String> map = new HashMap<>();
            map.put("unionId", data.getUnionid());
            viewModel.wechatLogin(map, new onResponseListener<TResponse<UserInfoBean>>() {
                @Override
                public void onSuccess(TResponse<UserInfoBean> bean) {
                    if (bean.getCode() == 201) {
                        //以绑定手机号，但未设置密码！
                        ToastUtils.showToast("手机号未设置密码，请先设置密码！");
                        Intent intent = new Intent(mContext, UserHandleActivity.class);
                        intent.putExtra("type", 2);
                        intent.putExtra("logonAccount", bean.getData().getLogonAccount());
                        intent.putExtra("userId",bean.getData().getUserId()+"");
                        mContext.startActivity(intent);
                    } else if (bean.getCode() == 200) {
                        ShareUtils.putUserInfo(bean.getData());
                        ToastUtils.showToast("登录成功！");
                    }
                    ((Activity) mContext).finish();
                }

                @Override
                public void onFailed(Throwable throwable) {
                    if (throwable != null && throwable instanceof TaskException) {
                        if (((TaskException) throwable).getErrorCode() == 401) {
                            ToastUtils.showToast("请先绑定手机号！");
                            Intent intent = new Intent(mContext, UserHandleActivity.class);
                            intent.putExtra("type", 3);
                            intent.putExtra("data", new Gson().toJson(data));
                            mContext.startActivity(intent);
                            ((Activity) mContext).finish();
                        } else {
                            ToastUtils.showToast(throwable.getMessage());
                        }
                    }
                }
            });
        }

        @Override
        public void onFailure() {
            ToastUtils.showToast("操作失败，请重试");
            WechatLoginHelper.weixinAPI = null;
        }
    };


}
