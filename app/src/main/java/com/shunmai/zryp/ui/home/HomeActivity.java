package com.shunmai.zryp.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;

import com.android.update.NotificationInfo;
import com.android.update.UpdateInfo;
import com.android.update.UpdateManager;
import com.shunmai.zryp.AppConfig;
import com.shunmai.zryp.base.BaseActivity;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.home.CheckVersionBean;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.shunmai.zryp.ui.home.child.UserInfoFragment;
import com.shunmai.zryp.ui.home.child.HomePageFragment;
import com.shunmai.zryp.ui.home.child.SearchFragment;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityHomeBinding;
import com.shunmai.zryp.utils.DevicesUtils;

import java.util.ArrayList;

import io.reactivex.functions.Consumer;

public class HomeActivity extends BaseActivity {
    private ActivityHomeBinding databinding;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databinding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initWindow(this);
        initContentFragment();
        initTabView();
        initListener();
//
        checkVersion();

    }

    private void checkVersion(String url, boolean isForce,String msg,String version) {
        //不用害怕 根据英文名称直译就可以
        UpdateInfo updateInfo = new UpdateInfo();
        updateInfo.versionName =version;
        updateInfo.versionCode = 10;
        updateInfo.isForce = isForce;
        updateInfo.size = 1000000;
        updateInfo.updateContent =msg;
        if (isForce) {
            updateInfo.isIgnorable = false;
        }
        NotificationInfo notificationInfo = new NotificationInfo(R.mipmap.ic_launcher, R.mipmap.ic_launcher, getResources().getString(R.string.app_name), "正在下载中", msg);
        new UpdateManager(this, url, "zryp", false, updateInfo, notificationInfo).init();
    }

    private void checkVersion() {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).CheckVersion("android"), new Consumer<TResponse<CheckVersionBean>>() {
            @Override
            public void accept(TResponse<CheckVersionBean> bean) {
//                bean.getData()
                if (!bean.getData().getVversion().equals(DevicesUtils.getLocalVersionName(HomeActivity.this))) {
                    checkVersion(bean.getData().getVdownloadUrl(), bean.getData().isVisenforce(),bean.getData().getVcomment(),bean.getData().getVversion());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {

            }
        });
    }


    private void initTabView() {
        BottomNavigationView navigationView = databinding.navigationHome;
        navigationView.setItemHorizontalTranslationEnabled(false);
        navigationView.setItemIconTintList(null);
        navigationView.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.tab1: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.fl_home, fragments.get(0));
                    transaction.show(fragments.get(0));
                    transaction.hide(fragments.get(1));
                    transaction.hide(fragments.get(2));
                    transaction.commitAllowingStateLoss();
                    return true;
                }
                case R.id.tab2: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.fl_home, fragments.get(1));
                    transaction.hide(fragments.get(0));
                    transaction.show(fragments.get(1));
                    transaction.hide(fragments.get(2));
                    transaction.commitAllowingStateLoss();
                    return true;
                }
//                    case R.id.tab3: {
//                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.fl_home, fragments.get(2));
//                        transaction.commitAllowingStateLoss();
//                        return true;
//                    }
                case R.id.tab4: {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                        transaction.replace(R.id.fl_home, fragments.get(2));
                    transaction.hide(fragments.get(0));
                    transaction.hide(fragments.get(1));
                    transaction.show(fragments.get(2));
                    transaction.commitAllowingStateLoss();
                    return true;
                }
            }
            return false;
        });

    }


    private void initContentFragment() {
        if (fragments.size() == 0) {
            fragments.add(new HomePageFragment());
            fragments.add(new SearchFragment());
            fragments.add(new UserInfoFragment());
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fl_home, fragments.get(0));
            transaction.add(R.id.fl_home, fragments.get(1));
            transaction.add(R.id.fl_home, fragments.get(2));
            transaction.hide(fragments.get(1));
            transaction.hide(fragments.get(2));
            transaction.commitAllowingStateLoss();
        }
    }

    private PayBroadcastReceiver mReceiver;

    private void initListener() {
        mReceiver = new PayBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(AppConfig.PaySuccess);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);
    }

    private class PayBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppConfig.PaySuccess)) {
                databinding.navigationHome.setSelectedItemId(R.id.tab1);
            }
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
