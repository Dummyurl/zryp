package com.shunmai.zryp.eventhandler.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.shunmai.zryp.ui.wallet.BankListActivity;

/**
 * Created by yushengyang.
 * Date: 2018/12/6.
 */

public class WithDrawInfoHandler {
    public void toBankList(View view){
        Intent intent=new Intent(view.getContext(), BankListActivity.class);
        ((Activity) view.getContext()).startActivityForResult(intent,100);
    }
}
