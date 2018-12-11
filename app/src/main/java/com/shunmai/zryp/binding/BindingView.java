package com.shunmai.zryp.binding;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.ImageViewState;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.gson.Gson;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.DividerItemDecoration;
import com.shunmai.zryp.adapter.GlideImageLoader;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.ui.goods.GoodsListActivity;
import com.shunmai.zryp.utils.BigImageUtils;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.view.AutoFlowLayout;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
import com.youth.banner.Banner;
import com.ysy.commonlib.utils.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/19.
 */

public class BindingView {
    private static final int MAX_SIZE = 4096;
    private static final int MAX_SCALE = 8;

    @BindingAdapter({"android:setVisibility"})
    public static void setVisibility(View view, String content) {
        if (content == null || content.equals("")) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

    @BindingAdapter({"android:setBanner", "android:setBannerDefaultImg"})
    public static void setBanner(Banner banner, List<String> content, String defaultImage) {
        if (content != null && content.size() > 0) {
            banner.setImageLoader(new GlideImageLoader()).setImages(content).start();
        } else {
            ArrayList<String> defaultImg = new ArrayList<>();
            defaultImg.add(defaultImage);
            banner.setImageLoader(new GlideImageLoader()).setImages(defaultImg).start();
        }
    }

    @BindingAdapter({"android:isCollect"})
    public static void isCollect(CheckBox box, String content) {
        if (content != null) {
            if (!content.equals("0")) {
                box.setChecked(true);
            } else {
                box.setChecked(false);
            }
        }
    }


    @BindingAdapter({"android:setListVisibility"})
    public static void setVisibility(View view, int size) {
        if (size == 0) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }


    @BindingAdapter({"android:displayImg"})
    public static void displayImg(ImageView imageView, String url) {
        GlideCacheUtil.LoadImage(imageView.getContext(), imageView, url);
    }

    @BindingAdapter({"android:displayImgEmpty"})
    public static void displayImgEmpty(ImageView imageView, String url) {
        GlideCacheUtil.LoadImageWithEmpty(imageView.getContext(), imageView, url);
    }

    @BindingAdapter({"android:displayHeadImg", "android:setGender"})
    public static void displayHeadImg(ImageView imageView, String url, int gender) {
        GlideCacheUtil.LoadImage(imageView.getContext(), imageView, url, 0, gender);
    }

    @BindingAdapter({"android:setprice", "android:textline"})
    public static void setPrice(TextView textView, double price, boolean textline) {
        if (textline) {
            textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }
        textView.setText("￥" + price);
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
        flowLayout.setOnItemClickListener((position, view) -> {
            String data = new Gson().fromJson(ShareUtils.getString("search_history"), GoodsHotWordBean.class).getData().get(position);
            Intent intent = new Intent(view.getContext(), GoodsListActivity.class);
            intent.putExtra("type", 3);
            intent.putExtra("keyword", data);
            intent.putExtra("goodsName", data);
            intent.putExtra("title", data);
            view.getContext().startActivity(intent);
        });
    }

    @BindingAdapter("android:setWeightPercent")
    public static void setWeightPercent(View view, int weight) {

//        view.setLayoutParams(new LinearLayout.LayoutParams(view.getWidth(),
//                view.getHeight(), weight));
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.weight = weight;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter({"android:loadImages"})
    public static void loadImages(LinearLayout linearLayout, List<GoodsBean.SeekGoodsImgsVOSBean> images) {
        for (int i = 0; images != null && i < images.size(); i++) {
//            ((Activity) linearLayout.getContext()).getLayoutInflater().inflate(
            String url = images.get(i).getPhotoUrl();
            SubsamplingScaleImageView scaleImageView = new SubsamplingScaleImageView(linearLayout.getContext());
            scaleImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            scaleImageView.setZoomEnabled(false);
            linearLayout.addView(scaleImageView);
//            GlideCacheUtil.LoadImage(linearLayout.getContext(), imageView, images.get(i).getPhotoUrl());
            scaleImageView.setMaxScale(10.0F);
            RequestManager manager = Glide.with(linearLayout.getContext());
            manager.load(url).into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                    Bitmap bitmap = ((BitmapDrawable) resource).getBitmap();
                    int h = bitmap.getHeight();
                    int w = bitmap.getWidth();
                    if (h >= MAX_SIZE || h / w > MAX_SCALE) {
                        manager.load(url)
                                .downloadOnly(new SimpleTarget<File>() {
                                    @Override
                                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                                        float scale = BigImageUtils.getImageScale(linearLayout.getContext(), resource.getAbsolutePath());
                                        scaleImageView.setImage(ImageSource.uri(resource.getAbsolutePath()),
                                                new ImageViewState(scale, new PointF(0, 0), 0));
                                    }
                                });
                    } else {
                        scaleImageView.setImage(ImageSource.bitmap(bitmap));
                    }

                }
            });

        }
    }

    @BindingAdapter({"android:clickToActivity"})
    public static void clickToActivity(View view, String clz) {
        view.setOnClickListener(v -> {
            Intent intent = null;
            try {
                intent = new Intent(view.getContext(), Class.forName(clz));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            view.getContext().startActivity(intent);
        });
    }

    @BindingAdapter({"android:AlignContent"})
    public static void AlignContent(TextView textView, boolean isAlign) {
        if (isAlign) {
            textView.setText(StringUtils.getAlignContent(textView.getText().toString()));
        }
    }

    @BindingAdapter({"android:RecRowCount", "android:RecDivisionType"})
    public static void setUserType(RecyclerView recycleView, int RowCount, int divisionType) {
        if (RowCount <= 1) {
            recycleView.setLayoutManager(new LinearLayoutManager(recycleView.getContext()));
        } else {
            recycleView.setLayoutManager(new GridLayoutManager(recycleView.getContext(), RowCount));
        }
        if (divisionType == 1) {
            recycleView.addItemDecoration(new DividerItemDecoration(recycleView.getContext(), DividerItemDecoration.VERTICAL));
        }
    }
}
