package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.google.gson.Gson;
import com.shunmai.zryp.AppConfig;
import com.shunmai.zryp.repository.SearchActivityRepository;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.eventhandler.goods.GoodsSearchHandler;
import com.shunmai.zryp.utils.DevicesUtils;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.Utils;
import com.shunmai.zryp.viewmodel.SearchActivityViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityGoodsSearchBinding;

import java.util.ArrayList;

public class GoodsSearchActivity extends SwipeBackActivity<ActivityGoodsSearchBinding> {
    private SearchActivityViewModel viewModel;
    private MutableLiveData<GoodsHotWordBean> hotData = new MutableLiveData<>();
    private MutableLiveData<GoodsHotWordBean> historyData = new MutableLiveData<>();
    private PayBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_search);
        DevicesUtils.setWindowStatusBarColor(this, R.color.white);
        Utils.setStatusTextColor(true, this);
//        hotData.observe(this, goodsHotWordBean -> {
////            bindingView.setBean(goodsHotWordBean);
////            showContentView();
////        });
        showContentView();
        historyData.observe(this, goodsHotWordBean -> bindingView.setHistory(goodsHotWordBean));
        viewModel = ViewModelProviders.of(this).get(SearchActivityViewModel.class);
        viewModel.init(new SearchActivityRepository());
//        viewModel.getKeyWord(hotData, throwable -> throwable.printStackTrace());
        viewModel.getHistory(historyData);
        bindingView.setHandler(new GoodsSearchHandler(viewModel));
        //按下搜索按钮时
        bindingView.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                return doSearch();
            }
            return false;
        });
        initListener();
    }

    private boolean doSearch() {
        if (!bindingView.etSearch.getText().toString().trim().equals("")) {
            GoodsHotWordBean search_history = new Gson().fromJson(ShareUtils.getString("search_history"), GoodsHotWordBean.class);
            if (search_history == null || search_history.getData() == null) {
                search_history = new GoodsHotWordBean(new ArrayList<>());
            }
            if (search_history.getData().indexOf(bindingView.etSearch.getText().toString().trim()) == -1) {
                search_history.getData().add(bindingView.etSearch.getText().toString().trim());
            }
            ShareUtils.putString("search_history", new Gson().toJson(search_history));
            viewModel.getHistory(historyData);
            Intent intent = new Intent(GoodsSearchActivity.this, GoodsListActivity.class);
            intent.putExtra("type", 3);
            intent.putExtra("keyword", bindingView.etSearch.getText().toString().trim());
            intent.putExtra("goodsName", bindingView.etSearch.getText().toString().trim());
            intent.putExtra("title", bindingView.etSearch.getText().toString().trim());
            startActivity(intent);
            bindingView.etSearch.setText("");
            return true;
        } else {

            return false;
        }
    }

    private void initListener() {
        mReceiver = new PayBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(AppConfig.PaySuccess);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);
        bindingView.tvSearch.setOnClickListener(v -> {
            if (!doSearch()) {
                ToastUtils.showToast("请输入搜索内容！");
            }
        });
    }

    private class PayBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppConfig.PaySuccess)) {
                finish();
            }
        }
    }
}
