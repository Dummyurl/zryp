package com.shunmai.zryp.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.shunmai.zryp.AppConfig;
import com.shunmai.zryp.ui.userinfo.order.OrderActivity;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.wechatutils.WechatLoginHelper;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.activity_main2);
        api = WXAPIFactory.createWXAPI(this, WechatLoginHelper.APP_ID);
        api.handleIntent(getIntent(), this);
        api.registerApp(WechatLoginHelper.APP_ID);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq req) {

    }


    @Override
    public void onResp(BaseResp resp) {
        int errCode = resp.errCode;
        if (errCode == 0) {
            ToastUtils.showToast("支付成功");
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("paySuccess",true);
            intent.putExtra("position", 0);
            startActivity(intent);
        } else if (errCode == -1) {
            ToastUtils.showToast("支付错误");
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("payFailed",true);
            intent.putExtra("position", 1);
            startActivity(intent);
        } else if (errCode == -2) {
            ToastUtils.showToast("取消支付");
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("payFailed",true);
            intent.putExtra("position", 1);
            startActivity(intent);
        }
        finish();
    }
}