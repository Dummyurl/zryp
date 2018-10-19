package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/31.
 */

public class GuessGoodsAdapter extends CommonViewAdapter<GoodsBean> {
    public GuessGoodsAdapter(Context context, List<GoodsBean> datas) {
        super(context, datas, R.layout.item_guess_goods);
    }

    @Override
    public void convert(ViewHolder holder, GoodsBean item) {
        GlideCacheUtil.LoadImage(mContext, holder.getView(R.id.iv_goods), item.getDefalutPhotourl(),1);
//        if (item.get)
//        ImageSpan imageSpan=new ImageSpan(getContext(),R.menu)
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getGoodsName());
        ((TextView) holder.getView(R.id.tv_price)).setText("¥" + item.getMarketPrice());
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate((AppCompatActivity) mContext,holder.getView(R.id.iv_goods), item.getSysIdString(),item.getDefalutPhotourl(),item.getPrice(),item.getMarketPrice(),item.getGoodsTitle()));
    }
}
