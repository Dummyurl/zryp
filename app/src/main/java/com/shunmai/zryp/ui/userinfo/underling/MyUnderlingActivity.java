package com.shunmai.zryp.ui.userinfo.underling;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.shunmai.zryp.adapter.home.UnderlingAdapter;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.bean.TitleBean;
import com.shunmai.zryp.bean.underling.UnderlingBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.viewmodel.UnderlingViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityMyUnderlingBinding;

import java.util.ArrayList;

public class MyUnderlingActivity extends SwipeBackActivity<ActivityMyUnderlingBinding> implements onResponseListener<UnderlingBean> {

    private int userType;
    private int userId;
    private UnderlingViewModel viewmodel;
    private int page=1;
    private UnderlingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_underling);
        userType = ShareUtils.getUserInfo().getUserType();
        userId =ShareUtils.getUserInfo().getUserId();
        bindingView.setTitle(new TitleBean().setTitle("我推荐的人"));
        initView();
        viewmodel = ViewModelProviders.of(this).get(UnderlingViewModel.class);
        getData(userType,userId,page++);
    }

    private void getData(int userType, int userId, int page) {
        viewmodel.getUnderlingList(userType,userId,page,this);
    }

    private void initView() {

        bindingView.rvUnderling.setLayoutManager(new LinearLayoutManager(this));
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
