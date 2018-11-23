package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.goods.PromotionGoodsBean;
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

public class FlashSaleRecAdapter
        extends CommonViewAdapter<PromotionGoodsBean> {
    public FlashSaleRecAdapter(Context context, List<PromotionGoodsBean> datas) {
        super(context, datas, R.layout.item_flash_sale);
    }

    @Override
    public void convert(ViewHolder holder, PromotionGoodsBean item) {
        ((TextView) holder.getView(R.id.tv_goods_title)).setText(item.getGoodsTitle());
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getGoodsName());
        ((TextView) holder.getView(R.id.tv_price)).setText(item.getPrice() + "");
        GlideCacheUtil.LoadImageWithEmpty(mContext, holder.getView(R.id.iv_goods), item.getDefalutPhotoURL());
        ((TextView) holder.getView(R.id.tv_market_price)).setText("￥" + item.getMarketPrice());
        ((TextView) holder.getView(R.id.tv_market_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate(mContext, item.getGoodsId()));
    }
}
