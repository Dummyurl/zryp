package com.shunmai.zryp.ui.home.child;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.databinding.FragmentUserinfoBinding;
import com.shunmai.zryp.eventhandler.home.UserInfoHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.Dev;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.viewmodel.UserInfoFragmentViewModel;
import com.ysy.commonlib.base.BaseFragment;
import com.ysy.commonlib.base.TResponse;

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
        if (ShareUtils.getUserInfo()!=null){
            bindingView.setBean(ShareUtils.getUserInfo());
        }
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
        if (ShareUtils.getUserInfo() != null) {
            loadData();
        } else {
            shrinkBac(0.86);
        }
    }

    @Override
    public void onSuccess(TResponse<UserInfoBean> userInfoBean) {
        bindingView.refreshLayout.finishRefresh();
        if (userInfoBean.getData() != null) {
            ShareUtils.putUserInfo(userInfoBean.getData());
            bindingView.setBean(userInfoBean.getData());
            ViewGroup.LayoutParams v_layout = bindingView.vBac.getLayoutParams();
//            v_layout.height = dp2px(getActivity(), 300);
            bindingView.vBac.setLayoutParams(v_layout);
            shrinkBac(1);
        }
    }

    @Override
    public void onFailed(Throwable throwable) {
        bindingView.refreshLayout.finishRefresh(false);
        if (ShareUtils.getUserInfo() != null) {
            bindingView.setBean(ShareUtils.getUserInfo());
            shrinkBac(1);
        }
//        shrinkBac();
    }

    private void shrinkBac(double size) {
        ViewGroup.LayoutParams layoutParams = bindingView.rlBackground.getLayoutParams();
        layoutParams.height = (int) (Dev.dp2px(getActivity(), 300) * size);
        bindingView.rlBackground.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams v_layout = bindingView.vBac.getLayoutParams();
        v_layout.height = (int) (Dev.dp2px(getActivity(), 240) * size);
        bindingView.vBac.setLayoutParams(v_layout);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        loadData();
    }

}
