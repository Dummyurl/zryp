package com.shunmai.zryp.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shunmai.zryp.view.RoundAngleImageView;
import com.youth.banner.loader.ImageLoader;


public class CIrImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        //圆角
        return new RoundAngleImageView(context);
    }
}
