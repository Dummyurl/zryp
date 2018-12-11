package com.shunmai.zryp.eventhandler.userinfo;

import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityUserApproveBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.PatternUtils;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.ApproveViewModel;
import com.ysy.commonlib.base.BaseEventHandler;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yushengyang.
 * Date: 2018/10/18.
 */



public class ApproveHandler extends BaseEventHandler implements View.OnClickListener{

    private ActivityUserApproveBinding bindView;
    private ApproveViewModel viewModel;
    private  onResponseListener<TResponse<Object>> listener;
    public ApproveHandler(ActivityUserApproveBinding bindingView, ApproveViewModel viewModel, onResponseListener<TResponse<Object>> listener) {
        this.bindView=bindingView;
        this.viewModel=viewModel;
        this.listener=listener;
    }

    public void submitApprove(View view){
        if (!PatternUtils.isChinaPhoneLegal(bindView.etPhone.getText().toString().trim())) {
            ToastUtils.showToast("电话号码格式错误，请检查后再提交！");
            return;
        }
        if (!PatternUtils.isCode(bindView.etPassCode.getText().toString().trim())){
            ToastUtils.showToast("验证码格式错误，请检查后再提交！");
            return;
        }
        if(!PatternUtils.isIDNumber(bindView.etIdCard.getText().toString().trim())){
            ToastUtils.showToast("身份证号码有误，请检查后再提交！");
            return;
        }
        if(bindView.etRealName.getText().toString().trim().length()<2){
            ToastUtils.showToast("真实姓名输入有误，请检查后再提交！");
            return;
        }
        HashMap<String,Object> map=new HashMap<>();
        map.put("realname",bindView.etRealName.getText().toString().trim());
        map.put("cardId",bindView.etIdCard.getText().toString().trim());
        map.put("mobile",bindView.etPhone.getText().toString().trim());
        map.put("id", ShareUtils.getUserInfo().getUserId());
        map.put("verificationCode",bindView.etPassCode.getText().toString().trim());
        viewModel.ApproveUser(map,listener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_get_code:{
                if (!PatternUtils.isChinaPhoneLegal(bindView.etPhone.getText().toString().trim())) {
                    ToastUtils.showToast("电话号码格式错误，请检查后再输入！");
                    return;
                }
                viewModel.getCode(3, bindView.etPhone.getText().toString().trim(), new onResponseListener<TResponse<String>>() {
                    @Override
                    public void onSuccess(TResponse<String> stringTResponse) {
                        codeTime((TextView) v,ApproveHandler.this);
                        v.setOnClickListener(null);
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
