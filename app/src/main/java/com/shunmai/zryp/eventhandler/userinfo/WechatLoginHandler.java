package com.shunmai.zryp.eventhandler.userinfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.bean.userinfo.Response_WechatUserInfo;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.ui.home.HomeActivity;
import com.shunmai.zryp.ui.userinfo.account.BindPhoneActivity;
import com.shunmai.zryp.ui.userinfo.account.RecommendProvideActivity;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.wechatutils.WechatLoginHelper;
import com.shunmai.zryp.viewmodel.WechatLoginViewModel;
import com.ysy.commonlib.base.BaseEventHandler;
import com.ysy.commonlib.base.TResponse;

/**
 * Created by yushengyang.
 * Date: 2018/12/10.
 */

public class WechatLoginHandler extends BaseEventHandler {
    WechatLoginViewModel viewModel;
    Context mContext;

    /**
     * 微信登录
     */
    public void doWechatLogin(View view, WechatLoginViewModel viewModel) {
        this.viewModel = viewModel;
        mContext = view.getContext();
        WechatLoginHelper.doLogin(view.getContext(), wechatLoginCallBack);
    }

    private WechatLoginHelper.WechatLoginCallBack wechatLoginCallBack = new WechatLoginHelper.WechatLoginCallBack() {
        @Override
        public void onSuccess(Response_WechatUserInfo info) {
            viewModel.WechatLogin(info.getUnionid(), new onResponseListener<TResponse<UserInfoBean>>() {
                @Override
                public void onSuccess(TResponse<UserInfoBean> beanTResponse) {
                    if (beanTResponse.getStatus() == 1) {
                        mContext.startActivity(new Intent(mContext, HomeActivity.class));
                        ShareUtils.putUserInfo(beanTResponse.getData());
                        ((Activity) mContext).finish();
                    }else if(beanTResponse.getStatus() == -1){
                        Intent intent = new Intent(mContext, RecommendProvideActivity.class);
                        intent.putExtra("wechat_info",new Gson().toJson(info));
                        mContext.startActivity(intent);
                        ((Activity) mContext).finish();
                    }else if (beanTResponse.getStatus() == 0){
                        Intent intent = new Intent(mContext, BindPhoneActivity.class);
                        intent.putExtra("wechat_info",new Gson().toJson(info));
                        mContext.startActivity(intent);
                        ((Activity) mContext).finish();
                    }
                }

                @Override
                public void onFailed(Throwable throwable) {
                    ToastUtils.showToast(throwable.getMessage());
                }
            });
        }

        @Override
        public void onFailure() {
            ToastUtils.showToast("微信登录失败，请稍后再试！");
        }
    };
}
