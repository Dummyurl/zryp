package com.shunmai.zryp.utils.wechatutils;

import android.app.Activity;

import com.shunmai.zryp.bean.goods.OderInfoBean;
import com.shunmai.zryp.utils.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by ysy on 2016/9/28.
 * 第三方微信支付工具类
 */
public class WechatPayHelper {
    IWXAPI api;
    Activity activity;
    OderInfoBean orderInfo;


    public WechatPayHelper(OderInfoBean orderInfo, Activity activity) {
        this.orderInfo = orderInfo;
        this.activity = activity;
        api = WXAPIFactory.createWXAPI(activity, orderInfo.getAppID(), true);
        api.registerApp(orderInfo.getAppID());

    }

    private boolean isWXAppInstalledAndSupported() {

        boolean sIsWXAppInstalledAndSupported = api.isWXAppInstalled();

        return sIsWXAppInstalledAndSupported;
    }

    public void doPay() {
        if (isWXAppInstalledAndSupported()) {
            wechatPay();
        } else {
            ToastUtils.showToast("未检测到微信应用，请使用其他支付方式！");
        }
    }


    protected void wechatPay() {
        PayReq req = new PayReq();
        req.appId = orderInfo.getAppID();
        req.partnerId = orderInfo.getPartnerId();
        req.prepayId = orderInfo.getPrepayId();
        req.packageValue = orderInfo.getPackAge();
        req.nonceStr = orderInfo.getNonceStr();
        req.timeStamp = orderInfo.getTimeStamp();
        req.sign = orderInfo.getPaySign();
        api.sendReq(req);
        // dialog.dismiss();
    }

}

