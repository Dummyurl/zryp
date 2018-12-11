package com.shunmai.zryp.adapter.goods;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.view.MyImageSpan;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/31.
 */

public class GuessGoodsDetailAdapter extends CommonViewAdapter<GoodsBean> {
    public GuessGoodsDetailAdapter(Context context, List<GoodsBean> datas) {
        super(context, datas, R.layout.item_guess_goods_detail);
        setCanShowEmpty(false);

    }

    @Override
    public void convert(ViewHolder holder, GoodsBean item) {
        //商品前加上红标
        GlideCacheUtil.LoadImage(mContext, holder.getView(R.id.iv_goods), item.getDefalutPhotourl(), 1, 0);
        MyImageSpan span = new MyImageSpan(mContext, R.mipmap.icon_locals);
        if (item.getGoodsPropery() == 2&&item.getChannelId()==1) {
            span = new MyImageSpan(mContext, R.mipmap.icon_jd);
        }else if (item.getGoodsPropery() == 2&&item.getChannelId()==2){
            span = new MyImageSpan(mContext, R.mipmap.icon_ccj);
        }
        SpannableString spanStr = new SpannableString("   "+item.getGoodsName());
        spanStr.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(spanStr);
        ((TextView) holder.getView(R.id.tv_price)).setText("" + item.getPrice());
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate(mContext, item.getGoodsId()));
        ((TextView) holder.getView(R.id.tv_market_price)).setText(item.getMarketPrice()+"");
        ((TextView) holder.getView(R.id.tv_market_price)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        ((TextView) holder.getView(R.id.tv_market_price_icon)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);


    }
}
