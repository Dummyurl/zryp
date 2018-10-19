package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/30.
 * 限时购adapter
 */

public class FlashSaleRecAdapter extends CommonViewAdapter<HomePageBean.DataBean.FlashSaleBean.PromotionGoodsListBean>{
    public FlashSaleRecAdapter(Context context, List<HomePageBean.DataBean.FlashSaleBean.PromotionGoodsListBean> datas) {
        super(context, datas, R.layout.item_flash_sale);
    }

    @Override
    public void convert(ViewHolder holder,HomePageBean.DataBean.FlashSaleBean.PromotionGoodsListBean item) {
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getSeekGoodsVO().getGoodsName());
        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_goods), item.getSeekGoodsVO().getDefalutPhotourl());
        ((TextView) holder.getView(R.id.tv_price_post)).setText("¥"+item.getSeekGoodsVO().getMarketPrice());
        ((TextView) holder.getView(R.id.tv_price_post)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰 ;
        ((TextView) holder.getView(R.id.tv_price)).setText("¥"+item.getSeekGoodsVO().getPrice());
        ProgressBar bar = holder.getView(R.id.pb_flash_sale);
        bar.setMax(100);
        bar.setProgress(((int) Double.parseDouble(item.getSellRate().split("%")[0])));
        ((TextView) holder.getView(R.id.tv_sale_count)).setText("已抢"+item.getGoSalenum()+"件");
        ((TextView) holder.getView(R.id.tv_sale_per)).setText(item.getSellRate());
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate((AppCompatActivity) mContext,holder.getView(R.id.iv_goods), item.getSeekGoodsVO().getSysIdString(),item.getSeekGoodsVO().getDefalutPhotourl(),item.getSeekGoodsVO().getPrice(),item.getSeekGoodsVO().getMarketPrice(),item.getSeekGoodsVO().getGoodsTitle()));
    }
}
