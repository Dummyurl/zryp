package com.shunmai.zryp.ui.userinfo.account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.shunmai.zryp.databinding.ActivityWechatLoginBinding;
import com.shunmai.zryp.R;
import com.shunmai.zryp.eventhandler.userinfo.WechatLoginHandler;
import com.shunmai.zryp.utils.Utils;
import com.shunmai.zryp.viewmodel.WechatLoginViewModel;
import com.ysy.commonlib.base.MVVMActivity;

public class WechatLoginActivity extends MVVMActivity<ActivityWechatLoginBinding,WechatLoginViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_login);
        Utils.setStatusBar(this);
        Utils.setStatusTextColor(true, this);
        showContentView();
        bindingView.setHandler(new WechatLoginHandler());
        bindingView.setViewModel(mViewModel);
    }
}
