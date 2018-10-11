package com.shunmai.zryp.eventhandler.home;

import android.content.Intent;
import android.view.View;

import com.shunmai.zryp.ui.goods.GoodsListActivity;
import com.shunmai.zryp.ui.userinfo.order.OrderActivity;

/**
 * Created by yushengyang.
 * Date: 2018/9/27.
 */

public class FirstPageHandler {
    public void toGoodsListAcitvity(View view, int type,String title){
        Intent intent = new Intent(view.getContext(), GoodsListActivity.class);
        intent.putExtra("HomePage", type);
        intent.putExtra("type",2);
        intent.putExtra("title",title);
        view.getContext().startActivity(intent);
    }
}
