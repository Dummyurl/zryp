package com.shunmai.zryp.ui.userinfo.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shunmai.zryp.AppConfig;
import com.shunmai.zryp.R;
import com.ysy.commonlib.base.BaseFragment;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.bean.userinfo.TabEntity;
import com.shunmai.zryp.databinding.ActivityOrderBinding;
import com.shunmai.zryp.ui.userinfo.child.OrderFragment;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends SwipeBackActivity<ActivityOrderBinding>
{
    boolean canBack=false;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private List<String> mTitles = new ArrayList<>();

    private ArrayList<BaseFragment> mFagments = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initWindow(this);
        initView();
        bindingView.tablayout.setCurrentTab(getIntent().getIntExtra("position",0));
        bindingView.viewPager.setCurrentItem(getIntent().getIntExtra("position",0));
        showContentView();
        if (getIntent().getBooleanExtra("paySuccess",false)) {
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(AppConfig.PaySuccess));
        }
        if (getIntent().getBooleanExtra("payFailed",false)){
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(AppConfig.PayFailed));
        }
    }

    @Override
    protected boolean isSwipeBackEnable() {
        return canBack;
    }

    private void initView() {
        mTitles.add("全部");
        mTitles.add("代付款");
        mTitles.add("待发货");
        mTitles.add("已发货");
        mTitles.add("已完成");
        for (String s : mTitles) {
            mTabEntities.add(new TabEntity(s));
            mFagments.add(new OrderFragment().setType(mTitles.indexOf(s)));
        }
        bindingView.tablayout.setTabData(mTabEntities);
        bindingView.viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        bindingView.viewPager.setOffscreenPageLimit(5);
//        orderBinding.tablayout.setViewPager(orderBinding.viewPager);
        bindingView.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i==0){
                    canBack=true;
                    changeBackMode();
                }else {
                    canBack=false;
                    changeBackMode();
                }
                bindingView.tablayout.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        bindingView.tablayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                bindingView.viewPager.setCurrentItem(position,true);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFagments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public  BaseFragment getItem(int position) {
            return mFagments.get(position);
        }
    }
}
