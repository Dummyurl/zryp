package com.shunmai.zryp.ui.userinfo.order;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.userinfo.TabEntity;
import com.shunmai.zryp.ui.userinfo.child.OrderFragment;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends SwipeBackActivity<ActivityOrderBinding>
{
    boolean canBack=false;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private String[] mTitles = {"全部", "代付款", "待分享", "待发货", "已发货", "已完成"};
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
    }

    @Override
    protected boolean isSwipeBackEnable() {
        return canBack;
    }

    private void initView() {
        for (String s : mTitles) {
            mTabEntities.add(new TabEntity(s));
            mFagments.add(new OrderFragment());
        }
        bindingView.tablayout.setTabData(mTabEntities);
        bindingView.viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
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
            return mTitles[position];
        }

        @Override
        public  BaseFragment getItem(int position) {
            return mFagments.get(position);
        }
    }
}
