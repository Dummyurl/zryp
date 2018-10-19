package com.shunmai.zryp.ui.userinfo.underling;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.shunmai.zryp.adapter.home.UnderlingAdapter;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.TitleBean;
import com.shunmai.zryp.bean.underling.UnderlingBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.viewmodel.UnderlingViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityMyUnderlingBinding;

import java.util.ArrayList;

public class MyUnderlingActivity extends SwipeBackActivity<ActivityMyUnderlingBinding> implements onResponseListener<UnderlingBean> {
    //普通用户
    private final int NormalUser=0;
    //店长
    private final int ShopManager = 1;
    //总监
    private final int Majordomo = 2;
    //经理
    private final int Manager = 3;

    private int userType;
    private int userId;
    private UnderlingViewModel viewmodel;
    private int page=1;
    private UnderlingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_underling);
        userType = getIntent().getIntExtra("userType", 0);
        userId = getIntent().getIntExtra("userId", 0);
        boolean isMy=getIntent().getBooleanExtra("isMy",false);
        if (isMy){
            bindingView.setTitle(new TitleBean().setTitle("我推荐的人"));
        }else if (userType==1){
            bindingView.setTitle(new TitleBean().setTitle("下属总监"));
        }else if (userType==2){
            bindingView.setTitle(new TitleBean().setTitle("下属经理"));
        }else if(userType==3){
            bindingView.setTitle(new TitleBean().setTitle("下属用户"));
        }
        initView();
        viewmodel = ViewModelProviders.of(this).get(UnderlingViewModel.class);
        getData(userType,userId,page++);
    }

    private void getData(int userType, int userId, int page) {
        viewmodel.getUnderlingList(userType,userId,page,this);
    }

    private void initView() {
        switch (userType) {
            case NormalUser:{
                bindingView.llUnderling.setVisibility(View.GONE);
                bindingView.rvUnderling.setVisibility(View.GONE);
                break;
            }
            case ShopManager: {
                break;
            }
            case Majordomo: {
                bindingView.tvUnderlingName.setText("经理人数");
                break;
            }
            case Manager: {
                bindingView.llUnderling.setVisibility(View.GONE);
                break;
            }
    }
        bindingView.rvUnderling.setLayoutManager(new LinearLayoutManager(this));
        bindingView.rvUnderling.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adapter=new UnderlingAdapter(this,new ArrayList<>());
        bindingView.rvUnderling.setAdapter(adapter);
        bindingView.refreshLayout.setEnableRefresh(false);
        bindingView.refreshLayout.setEnableLoadMore(true);
        bindingView.refreshLayout.setEnableAutoLoadMore(true);
        bindingView.refreshLayout.setOnLoadMoreListener(refreshLayout -> getData(userType,userId,page++));

    }

    @Override
    public void onSuccess(UnderlingBean underlingBean) {
        showContentView();
        bindingView.tvTotalCount.setText(underlingBean.getTotalCount()+"");
        bindingView.tvUnderlingCount.setText(underlingBean.getCount()+"");
        adapter.add(underlingBean.getData());
        if (underlingBean.getData().size()<15){
            bindingView.refreshLayout.finishLoadMoreWithNoMoreData();
        }
        bindingView.refreshLayout.finishLoadMore();
    }

    @Override
    public void onFailed(Throwable throwable) {
        showError();
    }

    @Override
    protected void onRefresh() {
        page=1;
        getData(userType,userId,page);
        super.onRefresh();
    }
}
