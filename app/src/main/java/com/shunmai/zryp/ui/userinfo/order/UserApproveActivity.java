package com.shunmai.zryp.ui.userinfo.order;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityUserApproveBinding;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserApproveActivity extends SwipeBackActivity implements View.OnClickListener {
    private Disposable disposable;
    private int mTime = 120;
    private TextView tvGetCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow(this);
        ActivityUserApproveBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_user_approve);
//        setContentView(R.layout.activity_user_approve);
        tvGetCode = binding.tvGetCode;
        tvGetCode.setOnClickListener(this);
    }

    private void codeTime() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        disposable = interval.take(120).map(aLong -> mTime - aLong).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            if (tvGetCode != null) {
                tvGetCode.setText(aLong + "秒");
            }
            if (aLong == 1) {
                tvGetCode.setOnClickListener(UserApproveActivity.this);
                tvGetCode.setText("获取验证码");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_get_code:{
                codeTime();
                tvGetCode.setOnClickListener(null);
                break;
            }
        }
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
