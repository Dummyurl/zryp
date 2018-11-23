package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.shunmai.zryp.R;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.GoodsSecKillBean;
import com.shunmai.zryp.databinding.ActivityGoodsSecKillBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.ui.userinfo.order.OrderActivity;
import com.shunmai.zryp.utils.StringUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.GoodsSecKillViewModel;

import java.util.ArrayList;
import java.util.List;

public class GoodsSecKillActivity extends SwipeBackActivity<ActivityGoodsSecKillBinding> {
    private GoodsSecKillViewModel viewModel;
    private ArrayList<BaseFragment> mFagments = new ArrayList<>();
    private boolean canBack=true;
    private RadioButton button;

    @Override
    protected boolean isSwipeBackEnable() {
        return canBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_sec_kill);
        viewModel = ViewModelProviders.of(this).get(GoodsSecKillViewModel.class);
        getData();
    }

    private void getData() {
        viewModel.GetSeckill(new onResponseListener<List<GoodsSecKillBean>>() {
            @Override
            public void onSuccess(List<GoodsSecKillBean> bean) {
                showContentView();
                for (int i = 0; i < bean.size(); i++) {
                    if (i == 0) {
                        bindingView.rb1.setVisibility(View.VISIBLE);
                        bindingView.rb1.setText(getState(bean.get(i),bindingView.rb1));
                    } else if (i == 1) {
                        bindingView.rb2.setVisibility(View.VISIBLE);
                        bindingView.rb2.setText(getState(bean.get(i),bindingView.rb2));
                    } else if (i == 2) {
                        bindingView.rb3.setVisibility(View.VISIBLE);
                        bindingView.rb3.setText(getState(bean.get(i),bindingView.rb3));
                    } else if (i == 3) {
                        bindingView.rb4.setVisibility(View.VISIBLE);
                        bindingView.rb4.setText(getState(bean.get(i),bindingView.rb4));
                    }
                    GoodsSecKillFragment fragment=new GoodsSecKillFragment();
                    mFagments.add(fragment);
                    fragment.setBean(bean.get(i));
                }
                initView();
            }

            @Override
            public void onFailed(Throwable throwable) {
                ToastUtils.showToast("秒杀活动获取失败！");
                throwable.printStackTrace();
//                showError();
            }
        });
    }

    private SpannableString getState(GoodsSecKillBean bean, RadioButton radioButton) {
        String state;
        switch (bean.getDataStatus()) {
            case 0: {
                if ( StringUtils.isToday(bean.getPrStart())){
                    state = "即将开始";
                }else{
                    state = "明日开始";
                }
                break;
            }
            case 200: {
                state = "马上抢";
                 button = radioButton;
                break;
            }
            case 201: {
                state = "已抢光";
                break;
            }
            case 300:
            case 400: {
                state = "已结束";
                break;
            }
            default: {
                state = "已结束";
            }
        }
        String str = bean.getStartTime() + "\n" + state;
        SpannableString msp = new SpannableString(str);
        msp.setSpan(new AbsoluteSizeSpan(12, true), bean.getStartTime().length(), str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return msp;
    }

    private void initView() {
        bindingView.vpSecKill.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        bindingView.vpSecKill.setOffscreenPageLimit(5);
        bindingView.vpSecKill.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                bindingView.rgSecKill.check(bindingView.rgSecKill.getChildAt(i).getId());
                if (i==0){
                    canBack=true;
                    changeBackMode();
                }else {
                    canBack=false;
                    changeBackMode();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        bindingView.rgSecKill.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                bindingView.vpSecKill.setCurrentItem(group.indexOfChild(group.findViewById(checkedId)),true);
            }
        });
        if (button!=null){
            button.setChecked(true);
        }
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
        public BaseFragment getItem(int position) {
            return mFagments.get(position);
        }
    }
}
