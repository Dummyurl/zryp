package com.shunmai.zryp.eventhandler.userinfo;

import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityUserApproveBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.PatternUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.ApproveViewModel;
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

public class ApproveHandler implements View.OnClickListener{

    private Disposable disposable;
    private ActivityUserApproveBinding bindView;
    private ApproveViewModel viewModel;
    private  onResponseListener<TResponse<String>> listener;
    public ApproveHandler(ActivityUserApproveBinding bindingView, ApproveViewModel viewModel, onResponseListener<TResponse<String>> listener) {
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
        if(bindView.etBankCard.length()<16){
            ToastUtils.showToast("银行卡号码有误，请检查后再提交！");
            return;
        }
        if(bindView.etRealName.getText().toString().trim().length()<2){
            ToastUtils.showToast("真实姓名输入有误，请检查后再提交！");
            return;
        }
        HashMap<String,String> map=new HashMap<>();
        map.put("realname",bindView.etRealName.getText().toString().trim());
        map.put("cardId",bindView.etIdCard.getText().toString().trim());
        map.put("cardNumber",bindView.etBankCard.getText().toString().trim());
        map.put("mobile",bindView.etPhone.getText().toString().trim());
        viewModel.ApproveUser(map,bindView.etPassCode.getText().toString().trim(),listener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_get_code:{
                if (!PatternUtils.isChinaPhoneLegal(bindView.etPhone.getText().toString().trim())) {
                    ToastUtils.showToast("电话号码格式错误，请检查后再输入！");
                    return;
                }
                viewModel.getCode(1, bindView.etPhone.getText().toString().trim(), new onResponseListener<TResponse<String>>() {
                    @Override
                    public void onSuccess(TResponse<String> stringTResponse) {
                        codeTime((TextView) v);
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
    private void codeTime(TextView view) {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        disposable = interval.take(180).map(aLong -> 180 - aLong).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            if (view != null) {
                view.setText(aLong + "秒");
            }
            if (aLong == 1) {
                view.setOnClickListener(this);
                view.setText("获取验证码");
            }
        });
    }
    public void onDestroy(){
        if (disposable != null) {
            disposable.dispose();
            disposable = null;
        }
    }
}
