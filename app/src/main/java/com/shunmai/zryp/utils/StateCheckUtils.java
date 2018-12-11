package com.shunmai.zryp.utils;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shunmai.zryp.R;
import com.shunmai.zryp.ui.wallet.WithDrawInfoActivity;

/**
 * Created by yushengyang.
 * Date: 2018/12/7.
 */

public class StateCheckUtils {
    /**
     * 判断用户是否认证
     * @return
     */
    public static boolean checkVerification(){
        if (ShareUtils.getUserInfo()!=null){
            return ShareUtils.getUserInfo().isVerification();
        }
        return false;
    }
    public static void checkClickable(boolean canLight, View view, Drawable drawable, View.OnClickListener listener) {
        if (canLight) {
            view.setClickable(true);
            view.setBackground(drawable);
            view.setOnClickListener(listener);
        } else {
            view.setClickable(false);
            view.setOnClickListener(null);
            view.setBackgroundColor(((AppCompatActivity) view.getContext()).getResources().getColor(R.color.colorLineDeep));
        }
    }

    /**
     *
     * @param canLight
     * @param view
     * @param d1 能点击时drawable
     * @param d2
     * @param listener
     */
    public static void checkClickable(boolean canLight, View view, Drawable d1,Drawable d2, View.OnClickListener listener) {
        if (canLight) {
            view.setClickable(true);
            view.setBackground(d1);
            view.setOnClickListener(listener);
        } else {
            view.setClickable(false);
            view.setOnClickListener(null);
            view.setBackground(d2);
        }
    }
}
