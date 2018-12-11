package com.shunmai.zryp.eventhandler.userinfo;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.ui.wallet.WithDrawInfoActivity;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.WithdrawViewModel;
import com.ysy.commonlib.base.BaseEventHandler;
import com.ysy.commonlib.base.TResponse;

/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class WithdrawHandler extends BaseEventHandler implements View.OnClickListener{
    public void toChangeInfo(View view){
        Intent intent=new Intent(view.getContext(), WithDrawInfoActivity.class);
        intent.putExtra("isFirst",false);
        ((Activity) view.getContext()).startActivityForResult(intent,100);
    }
    public void getCode(View view, WithdrawViewModel viewModel,String phoneNum){
        viewModel.getCode( phoneNum, new onResponseListener<TResponse<String>>() {
            @Override
            public void onSuccess(TResponse<String> stringTResponse) {
                codeTime((TextView) view, v -> getCode(view,viewModel,phoneNum));
                view.setOnClickListener(null);
            }

            @Override
            public void onFailed(Throwable throwable) {
                ToastUtils.showToast(throwable.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
