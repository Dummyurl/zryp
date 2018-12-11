package com.shunmai.zryp.adapter.goods;

import android.content.Context;
import android.view.View;
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
 * Date: 2018/12/5.
 */

public class VipGoodsAdapter extends CommonViewAdapter<PromotionGoodsBean> {
    public VipGoodsAdapter(Context context, List<PromotionGoodsBean> datas) {
        super(context, datas, R.layout.item_vip_goods);
    }

    @Override
    public void convert(ViewHolder holder, PromotionGoodsBean item) {
        GlideCacheUtil.LoadImage(mContext, holder.getView(R.id.iv_goods),item.getDefalutPhotoURL());
        ((TextView) holder.getView(R.id.tv_goods_price)).setText(item.getPrice()+"");
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getGoodsName());
        ((TextView) holder.getView(R.id.tv_goods_score)).setText("赚"+item.getProScore()+"积分");
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate(mContext,item.getGoodsId()));
    }
}
