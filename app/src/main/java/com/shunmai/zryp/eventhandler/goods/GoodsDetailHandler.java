package com.shunmai.zryp.eventhandler.goods;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.shunmai.zryp.ui.goods.GoodsOrderActivity;
import com.shunmai.zryp.utils.FileUtils;
import com.shunmai.zryp.utils.StringUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.wechatutils.WechatLoginHelper;
import com.shunmai.zryp.view.BottomDialog;
import com.shunmai.zryp.R;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.concurrent.ExecutionException;

/**
 * Created by yushengyang.
 * Date: 2018/10/15.
 */

public class GoodsDetailHandler {
    public void popupdialog(View view) {
        BottomDialog dialog = new BottomDialog(view.getContext())
                .title("分享到")
                .orientation(BottomDialog.HORIZONTAL)
                .inflateMenu(R.menu.menu_grid, (item, dialog1) -> {
                    if (item.getTitle().equals("朋友圈")){
                        WechatShare(view.getContext(),"http://img.mp.sohu.com/upload/20170706/ca35c33af4be462fb11c50ccff0977cf.png",false);
                    }else if (item.getTitle().equals("微信好友")){
                        WechatShare(view.getContext(),"http://img.mp.sohu.com/upload/20170706/ca35c33af4be462fb11c50ccff0977cf.png",true);
                    }
//                    Toast.makeText(view.getContext(), "分享到" + item.getTitle(), Toast.LENGTH_SHORT).show();
                    dialog1.dismiss();
                });
        dialog.show();

    }

    public void ToOrderActivity(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), GoodsOrderActivity.class));
    }
    private void WechatShare(Context context,String url,boolean isFriend) {
        WechatLoginHelper.weixinAPI = WXAPIFactory.createWXAPI(context, null);
        WechatLoginHelper.weixinAPI.registerApp(WechatLoginHelper.APP_ID);
        if (!WechatLoginHelper.weixinAPI.isWXAppInstalled()) {
            ToastUtils.showToast("您还未安装微信客户端，无法进行微信分享！");
            return;
        }
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = url;
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = context.getResources().getString(R.string.app_name);;
        msg.description = context.getResources().getString(R.string.app_name);
        getShareBitmap(context,url, bitmap -> ((Activity) context).runOnUiThread(() -> {
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


    private void getShareBitmap(Context context,String url,SaveResultCallback saveResultCallback) {
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

    public interface SaveResultCallback {
        void onSavedSuccess(Bitmap bitmap);
    }
}
