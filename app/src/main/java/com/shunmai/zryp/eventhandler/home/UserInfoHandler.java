package com.shunmai.zryp.eventhandler.home;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;

import com.shunmai.zryp.R;
import com.shunmai.zryp.ui.userinfo.account.ChangeRecommendActivity;
import com.shunmai.zryp.ui.userinfo.account.UserApproveActivity;
import com.ysy.commonlib.base.BaseEventHandler;
import com.shunmai.zryp.ui.userinfo.account.AddressListActivity;
import com.shunmai.zryp.ui.userinfo.account.CollectActivity;
import com.shunmai.zryp.ui.userinfo.account.FootPrintActivity;
import com.shunmai.zryp.ui.userinfo.order.OrderActivity;
import com.shunmai.zryp.ui.userinfo.underling.MyUnderlingActivity;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.Utils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Calendar;

import static com.shunmai.zryp.AppConfig.ServiceTel;

/**
 * Created by yushengyang.
 * Date: 2018/9/26.
 */

public class UserInfoHandler extends BaseEventHandler {

    public void toOerderAcitvity(View view, int type) {
        if (Utils.checkLogin(view.getContext())) {
            Intent intent = new Intent(view.getContext(), OrderActivity.class);
            intent.putExtra("position", type);
            view.getContext().startActivity(intent);
        }
    }

    public void toUnderlingActivity(View view) {
        if (Utils.checkLogin(view.getContext())) {
            view.getContext().startActivity(new Intent(view.getContext(), MyUnderlingActivity.class));
        }
    }

    public void toFootprintActivity(View view) {
        if (Utils.checkLogin(view.getContext())) {
            view.getContext().startActivity(new Intent(view.getContext(), FootPrintActivity.class));
        }
    }

    public void toCollectActivity(View view) {
        if (Utils.checkLogin(view.getContext())) {
            view.getContext().startActivity(new Intent(view.getContext(), CollectActivity.class));
        }
    }

    public void toExchangeScoreActivity(View view) {
        ToastUtils.showToast("积分功能暂未开放！");
//        Intent intent = new Intent(view.getContext(), ExchangeScoreActivity.class);
//        view.getContext().startActivity(intent);
    }

    public void showDialog(View view) {
        ToastUtils.showToast("升级功能暂未开放！");
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.AlertDialogStyle);
        View inflate = ((Activity) view.getContext()).getLayoutInflater().inflate(R.layout.layout_not_level_up_dialog, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setContentView(inflate);
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        Window win = alertDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
    }

    public void showLightDialog(View view) {
        ToastUtils.showToast("升级功能暂未开放！");
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.AlertDialogStyle);
        View inflate = ((Activity) view.getContext()).getLayoutInflater().inflate(R.layout.layout_level_up_dialog, null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setContentView(inflate);
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        Window win = alertDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
    }

    public void toAddressListActivity(View view) {
        if (Utils.checkLogin(view.getContext())) {
            view.getContext().startActivity(new Intent(view.getContext(), AddressListActivity.class));
        }
    }

    public void copyInviteCode(View view) {
        ClipboardManager cm = (ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        if (ShareUtils.getUserInfo() != null) {
            ClipData clipData = ClipData.newPlainText("code", ShareUtils.getUserInfo().getInviteCode());
            cm.setPrimaryClip(clipData);
            ToastUtils.showToast("邀请码复制完成，快去分享给小伙伴吧~");
        } else {
            ToastUtils.showLoginFirst();
        }
    }

    public void callPhone(View view) {
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

    public void VerificationUser(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), UserApproveActivity.class));
    }

    public void editRecommend(View view, boolean canChange) {
        if (canChange) {
            view.getContext().startActivity(new Intent(view.getContext(), ChangeRecommendActivity.class));
        }else {
            ToastUtils.showToast("推荐人只能修改一次！");
        }
    }
}
