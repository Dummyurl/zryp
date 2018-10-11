package com.shunmai.zryp.binding;

import android.app.Activity;
import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.view.AutoFlowLayout;
import com.shunmai.zryp.view.AutoSizeImageView;
import com.shunmai.zryp.zrypapp.R;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/19.
 */

public class BindingView {
    @BindingAdapter({"android:setVisibility"})
    public static void setVisibility(View view, String content) {
        if (content == null || content.equals("")) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter({"android:onBack"})
    public static void onBack(CommonTitleBar titleBar, boolean back) {
        titleBar.setListener((v, action, extra) -> {
            if (action == CommonTitleBar.ACTION_LEFT_BUTTON && back) {
                ((Activity) titleBar.getContext()).onBackPressed();
            }
        });
    }

    @BindingAdapter({"android:displayImg"})
    public static void displayImg(ImageView imageView, String url) {
        GlideCacheUtil.LoadImage(imageView.getContext(), imageView, url);
    }

    @BindingAdapter({"android:setprice", "android:textline"})
    public static void setPrice(TextView textView, double price, boolean textline) {
        if (textline) {
            textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }
        textView.setText("" + price);
    }

    @BindingAdapter({"android:setTitle"})
    public static void setTitle(CommonTitleBar bar, String title) {
        bar.getCenterTextView().setText(title + "");
    }

    @BindingAdapter("android:setFlowContent")
    public static void setFlowContent(AutoFlowLayout flowLayout, List<String> content) {
        flowLayout.removeAllViews();
        for (int i = 0; content != null && i < content.size(); i++) {
            if (content.get(i) != null && !content.get(i).trim().equals("")) {
                View item = LayoutInflater.from(flowLayout.getContext()).inflate(R.layout.sub_item, null);
                TextView tvAttrTag = item.findViewById(R.id.tv_attr_tag);
                tvAttrTag.setText(content.get(i));
                flowLayout.addView(item);
            }
        }
    }

    @BindingAdapter({"android:loadImages"})
    public static void loadImages(LinearLayout linearLayout, List<GoodsDetailBean.DataBean.GoodsBean.SeekGoodsImgsVOSBean> images) {
        for (int i = 0; images != null && i < images.size(); i++) {
//            ((Activity) linearLayout.getContext()).getLayoutInflater().inflate()
            AutoSizeImageView imageView = new AutoSizeImageView(linearLayout.getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(imageView);
            GlideCacheUtil.LoadImage(linearLayout.getContext(), imageView, images.get(i).getPhotoUrl());
        }
    }
}
