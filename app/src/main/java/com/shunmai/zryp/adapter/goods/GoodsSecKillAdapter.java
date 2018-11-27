package com.shunmai.zryp.adapter.goods;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.PromotionGoodsBean;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/14.
 */

public class GoodsSecKillAdapter extends CommonViewAdapter<PromotionGoodsBean> {
    public GoodsSecKillAdapter(Context context, List<PromotionGoodsBean> datas) {
        super(context, datas, R.layout.item_sec_kill);
        setCanShowEmpty(true);
    }

    @Override
    public void convert(ViewHolder holder, PromotionGoodsBean item) {
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getGoodsName());
        GlideCacheUtil.LoadImage(mContext, holder.getView(R.id.iv_goods), item.getDefalutPhotoURL());
        ((TextView) holder.getView(R.id.tv_goods_price)).setText(item.getPrice() + "");
        ((TextView) holder.getView(R.id.tv_goods_market_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        ((TextView) holder.getView(R.id.tv_goods_market_price)).setText("￥" + item.getMarketPrice());
        ((TextView) holder.getView(R.id.tv_per)).setText(item.getProgress() + "");
        ((ProgressBar) holder.getView(R.id.pb_sec_kill)).setProgress(item.getProgress());
        if (item.getStatus() == 0) {
            ((TextView) holder.getView(R.id.tv_buy)).setText("即将开抢");
            holder.getView(R.id.ll_bac).setBackgroundResource(R.mipmap.btn_sec_kill_green);
        } else if (item.getStatus() == 200) {
            holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate(mContext,item.getGoodsId()));
        } else {
            ((TextView) holder.getView(R.id.tv_buy)).setText("已结束");
            holder.getView(R.id.ll_bac).setBackgroundResource(R.mipmap.btn_sec_kill_grey);
            ((ProgressBar) holder.getView(R.id.pb_sec_kill)).setProgressDrawable(mContext.getDrawable(R.drawable.progress_horizontal_sec_kill_grey));
        }

    }
}
