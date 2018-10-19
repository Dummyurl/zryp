package com.shunmai.zryp.ui.home.child;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.eventhandler.home.UserInfoHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.viewmodel.UserInfoFragmentViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.FragmentUserinfoBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends BaseFragment<FragmentUserinfoBinding> implements onResponseListener<TResponse<UserInfoBean>>, OnRefreshListener {


    private UserInfoFragmentViewModel viewModel;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(UserInfoFragmentViewModel.class);
        bindingView.setHandler(new UserInfoHandler());
        initRefresh();
        showContentView();

    }

    private void initRefresh() {
        bindingView.refreshLayout.setOnRefreshListener(this);
    }


    @Override
    public int setContent() {
        return R.layout.fragment_userinfo;
    }

    @Override
    protected void loadData() {
        viewModel.getUserInfo(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public void onSuccess(TResponse<UserInfoBean> userInfoBean) {
        bindingView.refreshLayout.finishRefresh();
       bindingView.setBean(userInfoBean.getData());
    }

    @Override
    public void onFailed(Throwable throwable) {
        bindingView.refreshLayout.finishRefresh(false);
    }


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        loadData();
    }
}
