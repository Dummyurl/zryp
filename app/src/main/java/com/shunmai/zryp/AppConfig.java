package com.shunmai.zryp;

import android.os.Debug;

/**
 * Created by ysy on 2018/9/2.
 */

public class AppConfig {
    /**
     * 其他版本
     */
    public static final int OTHER = 0;

    /**
     * 测试版本
     */
    public static final int DEBUG = 1;

    /**
     * 正式版本
     */
    public static final int RELEASE = 2;

    /**
     * 是否RELEASE版本不开启日志
     */
    public static boolean DEBUG_LOG = true;

    /**
     * 现在状态
     */
    public static final int ONLINE_SERVER = DEBUG;

    /**
     * 支付成功广播
     */
    public static final String PaySuccess = "com.shunmai.zryp.PAYSUCCESS";

    /**
     * 支付失败广播
     */

    public static final String PayFailed = "com.shunmai.zryp.PAYFAILED";

    /**
     * 测试环境小程序URL
     */
    public static final String miniProAddress = "https://api.gzcfe.net/shop/";

    /**
     * 正式环境小程序URL
     */
    public static final String miniProTestAddress = "http://testapi.gzcfe.net/testshop/";


    /**
     * 客服电话
     */
    public static final String ServiceTel = "01053673568";
}
