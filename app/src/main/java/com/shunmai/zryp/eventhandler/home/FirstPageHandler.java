package com.shunmai.zryp.eventhandler.home;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.CIrImageLoader;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.ui.goods.GoodsListActivity;
import com.shunmai.zryp.ui.goods.GoodsPromotionActivity;
import com.shunmai.zryp.ui.goods.GoodsSecKillActivity;
import com.shunmai.zryp.ui.goods.PreProActivity;
import com.shunmai.zryp.utils.DevicesUtils;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.view.CountdownTextView;
import com.shunmai.zryp.view.MyBanner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/27.
 */

public class FirstPageHandler {
    public void toGoodsListAcitvity(View view, int type, String title) {
        Intent intent = new Intent(view.getContext(), GoodsListActivity.class);
        intent.putExtra("HomePage", type);
        intent.putExtra("type", 2);
        intent.putExtra("title", title);
        view.getContext().startActivity(intent);
    }
    public void toPreproAcitvity(View view, int prId) {
        Intent intent = new Intent(view.getContext(), PreProActivity.class);
        intent.putExtra("prId", prId);
        view.getContext().startActivity(intent);
    }
    public void toGoodsPromotionActivity(View view, HomePageBean bean,int type) {
        if (type==1&&bean.getGoodInfo().getSecKillAndScorePro().size() > 1) {
            Intent intent = new Intent(view.getContext(), GoodsPromotionActivity.class);
            intent.putExtra("prId", bean.getGoodInfo().getSecKillAndScorePro().get(1).getPrId());
            intent.putExtra("type",type);
            view.getContext().startActivity(intent);
        }else if (type==2&&bean.getGoodInfo().getTopicPro()!=null){
            Intent intent = new Intent(view.getContext(), GoodsPromotionActivity.class);
            intent.putExtra("prId",bean.getGoodInfo().getTopicPro().getPrId());
            intent.putExtra("type",type);
            view.getContext().startActivity(intent);
        }
    }
    @BindingAdapter({"android:setSecTime"})
    public static void setSecTime(TextView textView,HomePageBean bean){
        if ( bean.getGoodInfo().getSecKillAndScorePro().size()!=0&&bean.getGoodInfo().getSecKillAndScorePro().get(0)!=null&&bean.getGoodInfo().getSecKillAndScorePro().get(0).getPoints()!=null){
            textView.setText(bean.getGoodInfo().getSecKillAndScorePro().get(0).getPoints()+"点场");
        }else{
            textView.setText("暂无秒杀");
        }
    }
    public  void toGoodsSecKillActivity(View view) {
        Intent intent = new Intent(view.getContext(), GoodsSecKillActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindingAdapter({"android:HomeBean", "android:beanIndex", "android:isMarket"})
    public static void setPromotion(View view, HomePageBean bean, int index, boolean isMarket) {
        if (bean.getGoodInfo().getRecPro() == null || bean.getGoodInfo().getRecPro().getPromotionGoods().size() <= index) {
            if (bean.getGoodInfo().getRecPro().getPromotionGoods().size() == 0) {
                view.setVisibility(View.GONE);
            } else {
                view.setVisibility(View.INVISIBLE);
            }
            return;
        }
        view.setOnClickListener(v -> GoodsDetailActivity.navigate(view.getContext(), bean.getGoodInfo().getRecPro().getPromotionGoods().get(index).getGoodsId()));
        if (view instanceof ImageView) {
            GlideCacheUtil.LoadImage(view.getContext(), (ImageView) view, bean.getGoodInfo().getRecPro().getPromotionGoods().get(index).getDefalutPhotoURL());
        } else if (view instanceof TextView) {
            if (isMarket) {
                ((TextView) view).setText("￥" + bean.getGoodInfo().getRecPro().getPromotionGoods().get(index).getMarketPrice());
                ((TextView) view).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            } else {
                ((TextView) view).setText("￥" + bean.getGoodInfo().getRecPro().getPromotionGoods().get(index).getPrice());
            }
        }
    }

    @BindingAdapter({"android:setTime"})
    public static void setTime(CountdownTextView countdownTextView, long second) {
        countdownTextView.init("%s", second / 1000);
        countdownTextView.start(0);
    }

    @BindingAdapter({"android:setCenterBanner"})
    public static void setCenterBanner(MyBanner banner, HomePageBean bean) {
        if (bean.getCenterPostion() != null) {
            List<String> images = new ArrayList<>();
            for (HomePageBean.CenterPostionBean.ImgsListBean image : bean.getCenterPostion().getImgsList()) {
                images.add(image.getImgSrc());
            }
            //简单使用
            banner.setBannerStyle(BannerConfig.NOT_INDICATOR);
            banner.setImages(images)
                    .setImageLoader(new CIrImageLoader())
                    .start();
            if (images.size() > 1) {
                banner.MyOption();
            }
        }
    }

    @BindingAdapter({"android:setPrePro"})
    public static void setPrePro(LinearLayout container, HomePageBean bean) {
        container.removeAllViews();
        for (GoodsBean goodsBean : bean.getGoodInfo().getPrePro().getPromotionGoods()) {
            View inflate = ((Activity) container.getContext()).getLayoutInflater().inflate(R.layout.item_flash_sale, null);
            ((TextView) inflate.findViewById(R.id.tv_goods_title)).setText(goodsBean.getGoodsTitle());
            ((TextView) inflate.findViewById(R.id.tv_goods_name)).setText(goodsBean.getGoodsName());
            ((TextView) inflate.findViewById(R.id.tv_price)).setText(goodsBean.getPrice() + "");
            GlideCacheUtil.LoadImage(container.getContext(), inflate.findViewById(R.id.iv_goods), goodsBean.getDefalutPhotoURL());
            ((TextView) inflate.findViewById(R.id.tv_market_price)).setText("￥" + goodsBean.getMarketPrice());
            ((TextView) inflate.findViewById(R.id.tv_market_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            inflate.setOnClickListener(v -> GoodsDetailActivity.navigate(container.getContext(),goodsBean.getGoodsId()));
            container.addView(inflate);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) inflate.getLayoutParams();
            layoutParams.setMargins(0,0,0, DevicesUtils.dip2px(container.getContext(),5));
            inflate.setLayoutParams(layoutParams);

        }
    }

}
