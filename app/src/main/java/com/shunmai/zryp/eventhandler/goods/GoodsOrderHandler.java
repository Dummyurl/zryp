package com.shunmai.zryp.eventhandler.goods;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.base.BaseEventHandler;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.ui.userinfo.account.AddressListActivity;

/**
 * Created by yushengyang.
 * Date: 2018/11/1.
 */

public class GoodsOrderHandler extends BaseEventHandler {

    public void toAddressList(View view, int isOutAddress) {
        Intent intent = new Intent(view.getContext(), AddressListActivity.class);
        intent.putExtra("type", 1);
        intent.putExtra("isOutAddress", isOutAddress);
        ((Activity) view.getContext()).startActivityForResult(intent, 100);
    }


}
