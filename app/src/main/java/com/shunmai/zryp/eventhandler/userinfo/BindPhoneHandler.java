package com.shunmai.zryp.eventhandler.userinfo;

import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityBindPhoneBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.PatternUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.WechatLoginViewModel;
import com.ysy.commonlib.base.BaseEventHandler;
import com.ysy.commonlib.base.TResponse;

/**
 * Created by yushengyang.
 * Date: 2018/12/10.
 */

public class BindPhoneHandler extends BaseEventHandler implements View.OnClickListener{
    private ActivityBindPhoneBinding binding;
    private WechatLoginViewModel viewModel;
    public BindPhoneHandler(ActivityBindPhoneBinding binding, WechatLoginViewModel viewModel){
        this.binding=binding;
        this.viewModel=viewModel;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_get_code:{
                if (!PatternUtils.isChinaPhoneLegal(binding.etPhone.getText().toString().trim())) {
                    ToastUtils.showToast("电话号码格式错误，请检查后再输入！");
                    return;
                }
                viewModel.getCode(1, binding.etPhone.getText().toString().trim(), new onResponseListener<TResponse<String>>() {
                    @Override
                    public void onSuccess(TResponse<String> stringTResponse) {
                        codeTime((TextView) v,BindPhoneHandler.this);
                        v.setOnClickListener(null);
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                        binding.tvGetCode.setOnClickListener(BindPhoneHandler.this);
                    }
                });
                binding.tvGetCode.setOnClickListener(null);
                break;
            }
        }
    }
}
