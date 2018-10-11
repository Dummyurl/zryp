package com.shunmai.zryp.eventhandler.home;

import android.content.Intent;
import android.view.View;

import com.shunmai.zryp.ui.userinfo.order.OrderActivity;

/**
 * Created by yushengyang.
 * Date: 2018/9/26.
 */

public class UserInfoHandler {
    public void toOerderAcitvity(View view,int type){
       Intent intent = new Intent(view.getContext(), OrderActivity.class);
       intent.putExtra("position", type);
       view.getContext().startActivity(intent);
    }
    public void toAcitvity(View view,String clz){
        Intent intent = null;
        try {
            intent = new Intent(view.getContext(),Class.forName(clz));
        } catch (ClassNotFoundException e) {

        }
        view.getContext().startActivity(intent);
    }
}
