package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;
import com.shunmai.zryp.view.MyImageSpan;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/31.
 */

public class GuessGoodsAdapter extends CommonViewAdapter<GoodsBean> {
    public GuessGoodsAdapter(Context context, List<GoodsBean> datas) {
        super(context, datas, R.layout.item_guess_goods);
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
        ((TextView) holder.getView(R.id.tv_price)).setText("¥" + item.getPrice());
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate(mContext, item.getGoodsId()));


    }
}
