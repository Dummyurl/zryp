package com.shunmai.zryp.eventhandler.goods;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.viewmodel.SearchActivityViewModel;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

/**
 * Created by yushengyang.
 * Date: 2018/9/28.
 */

public class GoodsSearchHandler {
    SearchActivityViewModel viewModel;
    public GoodsSearchHandler(SearchActivityViewModel viewModel){
        this.viewModel=viewModel;
    }
    public void clickClear(){
        ShareUtils.putString("search_history",new Gson().toJson(new GoodsHotWordBean()));
        viewModel.getHistory();
    }

    public void close(View view){
        ((Activity) view.getContext()).onBackPressed();
    }
}
