package com.shunmai.zryp.ui.userinfo.account;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shunmai.zryp.adapter.SpaceItemDecoration;
import com.shunmai.zryp.adapter.account.AddressListAdapter;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.network.onResponseListener;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.viewmodel.AddressListViewModel;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityAddressListBinding;

import java.util.ArrayList;
import java.util.List;

public class AddressListActivity extends SwipeBackActivity<ActivityAddressListBinding> implements AddressListAdapter.addressChangeListener {
    private AddressListViewModel viewModel;
    private AddressListAdapter adapter;
    private SmartRefreshLayout refreshLayout;
    private int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        viewModel = ViewModelProviders.of(this).get(AddressListViewModel.class);
        initRec();
        initRefresh();
        getData(page++);
    }

    private void initRefresh() {
        refreshLayout = bindingView.refreshLayout;
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshListener(refreshLayout ->{
            adapter.checked_position=0;
            page=1;
            adapter.clear();
            getData(page++);
            refreshLayout.setEnableLoadMore(true);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            getData(page++);
        }, 2000));
    }

    private void initRec() {
        RecyclerView rcAddress =bindingView.rcAddress;
        rcAddress.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AddressListAdapter(this,new ArrayList(),this,viewModel);
        rcAddress.setAdapter(adapter);
        rcAddress.addItemDecoration(new SpaceItemDecoration(0, 10));
        bindingView.btnAddAddress.setOnClickListener(v -> startActivityForResult(new Intent(AddressListActivity.this, AddressDetailActivity.class),200));
    }

    private void getData(int page){
        viewModel.getAddressList(new onResponseListener<List<AddressListBean.DataBean>>() {
            @Override
            public void onSuccess(List<AddressListBean.DataBean> dataBeans) {
                refreshLayout.finishLoadMore();
                refreshLayout.finishRefresh();
                adapter.add(dataBeans);
                showContentView();
                if (dataBeans.size()<20){
                    refreshLayout.setEnableLoadMore(false);
                }
            }
            @Override
            public void onFailed(Throwable throwable) {
                showError();
            }
        },0,page);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode==RESULT_OK){
            page=1;
            adapter.clear();
            getData(page++);
            refreshLayout.setEnableLoadMore(true);
        }
    }

    @Override
    public void onChange() {

    }

    @Override
    public void onDelete() {

    }

    @Override
    public void onDefaultChange() {

    }
}
