package com.shunmai.zryp.eventhandler.home;

import android.content.Intent;
import android.view.View;

import com.shunmai.zryp.ui.userinfo.account.FootPrintActivity;
import com.shunmai.zryp.ui.userinfo.order.OrderActivity;
import com.shunmai.zryp.ui.userinfo.underling.MyUnderlingActivity;

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
    public void toUnderlingActivity(View view,int userType,int userId){
        Intent intent = new Intent(view.getContext(), MyUnderlingActivity.class);
        intent.putExtra("userType", userType);
        intent.putExtra("userId", userId);
        intent.putExtra("isMy",true);
        view.getContext().startActivity(intent);
    }
    public void toFootprintActivity(View view){
        Intent intent = new Intent(view.getContext(), FootPrintActivity.class);
        view.getContext().startActivity(intent);
    }
}
