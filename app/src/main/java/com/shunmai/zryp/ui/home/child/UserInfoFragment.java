package com.shunmai.zryp.ui.home.child;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.shunmai.zryp.adapter.account.AddressListAdapter;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.eventhandler.home.UserInfoHandler;
import com.shunmai.zryp.ui.userinfo.account.AddressListActivity;
import com.shunmai.zryp.ui.userinfo.account.LoginActivity;
import com.shunmai.zryp.ui.userinfo.order.OrderActivity;
import com.shunmai.zryp.ui.userinfo.order.ScoreActivity;
import com.shunmai.zryp.viewmodel.UserInfoFragmentViewModel;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.FragmentUserinfoBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoFragment extends BaseFragment<FragmentUserinfoBinding>{


    private UserInfoFragmentViewModel viewModel;

    public UserInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(UserInfoFragmentViewModel.class);
        bindingView.setHandler(new UserInfoHandler());
        showContentView();


    }


    @Override
    public int setContent() {
        return R.layout.fragment_userinfo;
    }


}
