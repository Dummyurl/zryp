package com.shunmai.zryp.utils.wechatutils;

import android.content.Context;

import com.shunmai.zryp.bean.userinfo.Response_WechatUserInfo;
import com.shunmai.zryp.utils.ToastUtils;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


/**
 * Created by lixingjun on 7/31/16.
 */
public class WechatLoginHelper {
    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static String APP_ID = "wxf7813412fcdf9a26";
    public static String SECRET_KEY = "50789bfa5028e4f12f99fe933b92872b";
    public static IWXAPI weixinAPI = null;
    public static String openid = null;
    public static String nickName = null;
    public static int sexType = 1;//1=男
    public static String headImgUrl = null;
    public static WechatLoginCallBack loginCallBack = null;

    private WechatLoginHelper() {
    }


    public static void doLogin(Context context, WechatLoginCallBack callBack) {
        weixinAPI = WXAPIFactory.createWXAPI(context, APP_ID, true);
        weixinAPI.registerApp(APP_ID);
        if (isWXAppInstalledAndSupported()) {
            loginCallBack = callBack;
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "carjob_wx_login";
            weixinAPI.sendReq(req);//执行完毕这句话之后，会在WXEntryActivity回调}
        } else {
            ToastUtils.showToast("未检测到微信应用，请使用其他登录方式！");
        }
//        helper = null;
    }

    private static boolean isWXAppInstalledAndSupported() {
        boolean sIsWXAppInstalledAndSupported = weixinAPI.isWXAppInstalled()
                ;
        return sIsWXAppInstalledAndSupported;
    }

//    //这个方法会取得accesstoken  和openID
//    public void getToken(String code) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("appid", WechatLoginHelper.APP_ID);
//        map.put("secret", SECRET_KEY);
//        map.put("code", code);
//        map.put("grant_type", "authorization_code");
//        Observable<Respon_Wechat> respon_weiboObservable = RetrofitClient.getInstance().getService(HttpService.class).WeChatInfo(map);
//        activity.sendRequest(respon_weiboObservable, respon_wechat -> {
//            WechatLoginHelper.openid = respon_wechat.getOpenid();
//            String _token = respon_wechat.getAccess_token();
//            getUserInfo(_token, WechatLoginHelper.openid);
//            destory();
//        });
//    }

//    //获取到token和openID之后，调用此接口得到身份信息
//    private void getUserInfo(String token, String openID) {
////        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" +token+"&openid=" +openID;
//        HashMap<String, String> map = new HashMap<>();
//        map.put("access_token", token);
//        map.put("openid", openID);
//        map.put("lang", "zh_CN");
//        Observable<Respon_WechatUserInfo> respon_weiboObservable = RetrofitClient.getInstance().getService(HttpService.class).WeChatUserInfo(map);
//        activity.sendRequest(respon_weiboObservable, respon_wechatUserInfo -> {
//            WechatLoginHelper.nickName = respon_wechatUserInfo.getNickname();
//            WechatLoginHelper.sexType = respon_wechatUserInfo.getSex();
//            WechatLoginHelper.headImgUrl = respon_wechatUserInfo.getHeadimgurl();
//            if (loginCallBack != null) {
//                loginCallBack.onSuccess();
//            }
//        });
//    }

    public interface WechatLoginCallBack {
        void onSuccess(Response_WechatUserInfo info);

        void onFailure();
    }


}
