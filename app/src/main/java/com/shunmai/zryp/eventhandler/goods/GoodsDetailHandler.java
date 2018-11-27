package com.shunmai.zryp.eventhandler.goods;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.google.gson.Gson;
import com.ysy.commonlib.base.BaseEventHandler;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.bean.goods.GoodsOrderBean;
import com.shunmai.zryp.ui.goods.GoodsOrderActivity;
import com.shunmai.zryp.ui.home.HomeActivity;
import com.shunmai.zryp.utils.FileUtils;
import com.shunmai.zryp.utils.StringUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.Utils;
import com.shunmai.zryp.utils.wechatutils.WechatLoginHelper;
import com.shunmai.zryp.view.BottomDialog;
import com.shunmai.zryp.R;
import com.shunmai.zryp.view.SkuDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.concurrent.ExecutionException;

import static com.shunmai.zryp.AppConfig.ServiceTel;

/**
 * Created by yushengyang.
 * Date: 2018/10/15.
 */

public class GoodsDetailHandler extends BaseEventHandler {
    GoodsDetailBean.DataBean dataBean;
    SkuDialog dialog;
    long goodsId;
    private String miniProgramPath = "pages/shop/content/content?id="; //小程序路径
    private String miniProgramId = "gh_165c574ee23e";

    public GoodsDetailHandler(long goodsId) {
        this.goodsId = goodsId;
    }

    public void popupdialog(View view) {
        BottomDialog dialog = new BottomDialog(view.getContext())
                .title("分享到")
                .orientation(BottomDialog.HORIZONTAL)
                .inflateMenu(R.menu.menu_grid, (item, dialog1) -> {
                    WechatMiniShare(view.getContext(), dataBean.getGoods().getDefalutPhotourl(), true);
                    dialog1.dismiss();
                });
        dialog.show();

    }

    public void ToHomeActivity(View view) {
        Intent intent = new Intent(view.getContext(), HomeActivity.class);
        view.getContext().startActivity(intent);
    }

    public void CallHelp(View view){
        RxPermissions rxPermissions = new RxPermissions((Activity) view.getContext());
        rxPermissions.request(
                Manifest.permission.CALL_PHONE
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.ACCESS_COARSE_LOCATION
        ).subscribe(granted -> {
            if (!granted) {
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + ServiceTel);
                intent.setData(data);
                view.getContext().startActivity(intent);
            }
        });
    }
    public void ShowSkuDialog(View view) {
        if (Utils.checkLogin(view.getContext())) {
            if (dataBean!=null&&dataBean.getSku()!=null&&dataBean.getSku().getSeekGoodsItemVOS() != null && dataBean.getSku().getSeekGoodsItemVOS().size() != 0) {
                if (dialog == null) {
                    dialog = new SkuDialog(view.getContext(), dataBean);
                }
                dialog.show();
            } else if (dataBean.getSku()!=null&&dataBean.getSku().getSeekGoodsSkuVOS() != null && dataBean.getSku().getSeekGoodsSkuVOS().size() != 0) {
                if (dataBean.getSku().getSeekGoodsSkuVOS().get(0).getStock() == 0) {
                    ToastUtils.showToast("库存不足！");
                    return;
                }
                GoodsDetailBean.DataBean.SkuBean.SeekGoodsSkuVOSBean skuVos = dataBean.getSku().getSeekGoodsSkuVOS().get(0);
                Intent intent = new Intent(view.getContext(), GoodsOrderActivity.class);
                GoodsOrderBean bean = new GoodsOrderBean();
                bean.setGoodsName(dataBean.getGoods().getGoodsName());
                bean.setBuyCount(1);
                bean.setGoodsPrice(skuVos.getPrice());
                bean.setImageUrl(skuVos.getDefaultPhotoPath());
                bean.setSkuDescription("");
                bean.setGoodsId(dataBean.getGoods().getGoodsId());
                bean.setSkuId(skuVos.getSkuId());
                bean.setOid(0);
                if (dataBean.getGoods().getGoodsPropery() == 2 && dataBean.getGoods().getChannelId() == 1) {
                    bean.setIsOutAddress(2);
                } else {
                    bean.setIsOutAddress(1);
                }
                intent.putExtra("data", new Gson().toJson(bean));
                view.getContext().startActivity(intent);
            } else {
                ToastUtils.showToast("商品信息错误，请联系客服！");
            }
        }
    }

    private void WechatShare(Context context, String url, boolean isFriend) {
        WechatLoginHelper.weixinAPI = WXAPIFactory.createWXAPI(context, null);

        WechatLoginHelper.weixinAPI.registerApp(WechatLoginHelper.APP_ID);

        if (!WechatLoginHelper.weixinAPI.isWXAppInstalled()) {
            ToastUtils.showToast("您还未安装微信客户端，无法进行微信分享！");
            return;
        }

        WXWebpageObject webpage = new WXWebpageObject();

        webpage.webpageUrl = url;

        WXMediaMessage msg = new WXMediaMessage(webpage);

        msg.title = context.getResources().getString(R.string.app_name);

        msg.description = context.getResources().getString(R.string.app_name);
        getShareBitmap(context, url, bitmap -> ((Activity) context).runOnUiThread(() -> {
            msg.thumbData = FileUtils.bitmap2Bytes(bitmap, 32);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = StringUtils.buildTransaction("webpage");
            req.message = msg;
            if (isFriend) {
                req.scene = SendMessageToWX.Req.WXSceneSession;
            } else {
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
            }
            WechatLoginHelper.weixinAPI.sendReq(req);
        }));
    }

    private void WechatMiniShare(Context context, String url, boolean isFriend) {
        WechatLoginHelper.weixinAPI = WXAPIFactory.createWXAPI(context, null);
        WechatLoginHelper.weixinAPI.registerApp(WechatLoginHelper.APP_ID);
        if (!WechatLoginHelper.weixinAPI.isWXAppInstalled()) {
            ToastUtils.showToast("您还未安装微信客户端，无法进行微信分享！");
            return;
        }
        WXMiniProgramObject webpage = new WXMiniProgramObject();
        webpage.webpageUrl = url;
        webpage.userName = miniProgramId;
        webpage.path = miniProgramPath + goodsId;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = dataBean.getGoods().getGoodsName();
        msg.description = dataBean.getGoods().getDescription();
        getShareBitmap(context, url, bitmap -> ((Activity) context).runOnUiThread(() -> {
            msg.thumbData = FileUtils.bitmap2Bytes(bitmap, 32);
            SendMessageToWX.Req req = new SendMessageToWX.Req();
            req.transaction = StringUtils.buildTransaction("webpage");
            req.message = msg;
            if (isFriend) {
                req.scene = SendMessageToWX.Req.WXSceneSession;
            } else {
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
            }
            WechatLoginHelper.weixinAPI.sendReq(req);
        }));
    }

    private void getShareBitmap(Context context, String url, SaveResultCallback saveResultCallback) {
        new Thread(() -> {
            try {
                Bitmap bitmap = Glide.with(context).asBitmap().load(url).into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).get();
                saveResultCallback.onSavedSuccess(bitmap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void setDataBean(GoodsDetailBean.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public void onDestory() {
//        dialog.getCustomDialog().cancel();
    }

    public interface SaveResultCallback {
        void onSavedSuccess(Bitmap bitmap);
    }

}
