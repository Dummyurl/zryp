package com.shunmai.zryp.ui.open;

import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.databinding.ActivityWelcomeBinding;
import com.shunmai.zryp.ui.home.HomeActivity;
import com.shunmai.zryp.utils.Utils;
import com.ysy.commonlib.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WelcomeActivity extends BaseActivity {

    private Disposable disposable;
    ActivityWelcomeBinding bindingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        bindingView = DataBindingUtil.setContentView(this, R.layout.activity_welcome);
        Utils.setStatusBar(this);
        Utils.setStatusTextColor(true, this);
        ToHome();
        bindingView.btnSkip.setOnClickListener(v -> {
            startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
            overridePendingTransition(0,android.R.anim.fade_out);
            finish();
        });
    }

    private void ToHome() {
        Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
        disposable = interval.take(5).map(aLong -> 5 - aLong).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(aLong -> {
            if (aLong == 1) {
                startActivity(new Intent(WelcomeActivity.this, HomeActivity.class));
                overridePendingTransition(0,android.R.anim.fade_out);
                finish();
            } else {
                bindingView.btnSkip.setText("跳过" + (aLong - 1) + "s");
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

    @Override
    public void finish() {
        super.finish();

    }
}
