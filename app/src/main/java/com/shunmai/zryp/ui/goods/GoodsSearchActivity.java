package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;

import com.google.gson.Gson;
import com.shunmai.zryp.repository.SearchActivityRepository;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.eventhandler.goods.GoodsSearchHandler;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.viewmodel.SearchActivityViewModel;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityGoodsSearchBinding;

import java.util.ArrayList;

public class GoodsSearchActivity extends SwipeBackActivity<ActivityGoodsSearchBinding> {
    private SearchActivityViewModel viewModel;
    private MutableLiveData<GoodsHotWordBean> hotData = new MutableLiveData<>();
    private MutableLiveData<GoodsHotWordBean> historyData = new MutableLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_search);
        initWindow(this);
        hotData.observe(this, goodsHotWordBean -> {
            bindingView.setBean(goodsHotWordBean);
            showContentView();
        });
        historyData.observe(this, goodsHotWordBean -> bindingView.setHistory(goodsHotWordBean));
        viewModel = ViewModelProviders.of(this).get(SearchActivityViewModel.class);
        viewModel.init(new SearchActivityRepository());
        viewModel.getKeyWord(hotData, throwable -> throwable.printStackTrace());
        viewModel.getHistory(historyData);
        bindingView.setHandler(new GoodsSearchHandler(viewModel));
//        setContentView(R.layout.activity_goods_search);
        bindingView.etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (!v.getText().toString().trim().equals("")) {
                if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    GoodsHotWordBean search_history = new Gson().fromJson(ShareUtils.getString("search_history"), GoodsHotWordBean.class);
                    if (search_history == null || search_history.getData() == null) {
                        search_history = new GoodsHotWordBean(new ArrayList<>());
                    }
                    search_history.getData().add(v.getText().toString().trim());
                    ShareUtils.putString("search_history", new Gson().toJson(search_history));
                    bindingView.etSearch.setText("");
                    viewModel.getHistory(historyData);
                    return true;
                }
            }
            return false;
        });
    }
}
