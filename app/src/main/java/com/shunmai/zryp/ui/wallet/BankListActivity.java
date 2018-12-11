package com.shunmai.zryp.ui.wallet;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.BankAdapter;
import com.shunmai.zryp.bean.BankBean;
import com.shunmai.zryp.databinding.ActivityBankListBinding;
import com.shunmai.zryp.listener.onResponseFailedListener;
import com.shunmai.zryp.viewmodel.BankListViewModel;
import com.ysy.commonlib.base.MVVMActivity;

import java.util.ArrayList;
import java.util.List;

public class BankListActivity extends MVVMActivity<ActivityBankListBinding,BankListViewModel> implements BankAdapter.bankCheckedListener {
    BankAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);
        mViewModel.getList().observe(this, bankBeans -> {
            showContentView();
            adapter.add(bankBeans);
        });
        adapter=new BankAdapter(this,new ArrayList<>(),getIntent().getIntExtra("position",0),this);
        bindingView.rv.setAdapter(adapter);
        getData();
    }
    public void getData(){
        mViewModel.GetBankList(throwable -> showError());
    }

    @Override
    protected void onRefresh() {
        getData();
    }

    @Override
    public void onChecked(BankBean item) {
        Intent intent=new Intent();
        intent.putExtra("bankInfo",new Gson().toJson(item));
        setResult(RESULT_OK,intent);
        onBackPressed();
    }
}
