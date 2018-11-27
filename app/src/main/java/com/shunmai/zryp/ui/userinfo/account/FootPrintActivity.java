package com.shunmai.zryp.ui.userinfo.account;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.shunmai.zryp.adapter.account.FootprintAdapter;
import com.shunmai.zryp.adapter.account.FootprintItemDecoration;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.bean.userinfo.FootprintBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.viewmodel.FootprintViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityFootPrintBinding;

import java.util.ArrayList;

public class FootPrintActivity extends SwipeBackActivity<ActivityFootPrintBinding> implements onResponseListener<FootprintBean> {
    private ArrayList<FootprintBean.DataBean> dataList = new ArrayList<>();
    private FootprintViewModel viewModel;
    private FootprintAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_print);
        viewModel = ViewModelProviders.of(this).get(FootprintViewModel.class);
        viewModel.GetMyFootprintList(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        bindingView.rcFootprint.setLayoutManager(gridLayoutManager);
        adapter = new FootprintAdapter(this, dataList);
        bindingView.rcFootprint.setAdapter(adapter);
        bindingView.rcFootprint.addItemDecoration(new FootprintItemDecoration(this, dataList, new FootprintItemDecoration.OnDecorationCallback() {
            @Override
            public String onGroupId(int pos) {
                if (dataList.get(pos).getTime() != null)
                    return dataList.get(pos).getTime();
                return "-1";
            }

            @Override
            public String onGroupFirstStr(int pos) {
                if (dataList.get(pos).getTime() != null)
                    return dataList.get(pos).getTime();
                return "";
            }

            @Override
            public void onGroupFirstStr(String title) {

            }
        }));
    }

    @Override
    public void onSuccess(FootprintBean footprintBean) {
        showContentView();
        dataList.addAll(footprintBean.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(Throwable throwable) {

    }

}
