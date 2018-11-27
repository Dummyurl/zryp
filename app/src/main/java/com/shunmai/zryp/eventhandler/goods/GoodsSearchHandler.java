package com.shunmai.zryp.eventhandler.goods;

import android.app.Activity;
import android.view.View;

import com.google.gson.Gson;
import com.ysy.commonlib.base.BaseEventHandler;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.view.MyAlertDialog;
import com.shunmai.zryp.viewmodel.SearchActivityViewModel;

/**
 * Created by yushengyang.
 * Date: 2018/9/28.
 */

public class GoodsSearchHandler extends BaseEventHandler{
    SearchActivityViewModel viewModel;
    public GoodsSearchHandler(SearchActivityViewModel viewModel){
        this.viewModel=viewModel;
    }
    public void clickClear(View view){
        MyAlertDialog dialog=new MyAlertDialog(view.getContext(),"确定清空历史搜索吗？");
        dialog.setPositiveListener(v -> {
            ShareUtils.putString("search_history",new Gson().toJson(new GoodsHotWordBean()));
            viewModel.getHistory();
            dialog.dismiss();
        });
        dialog.show();
    }

    public void close(View view){
        ((Activity) view.getContext()).onBackPressed();
    }

}
