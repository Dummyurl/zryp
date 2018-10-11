package com.shunmai.zryp.adapter.goods;

import android.content.Context;
import android.graphics.Paint;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.goods.GoodsListBean;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.zrypapp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/4.
 */

public class RecGoodsListAdapter extends CommonViewAdapter<GoodsBean> {
    public RecGoodsListAdapter(Context context, List<GoodsBean> datas) {
        super(context, datas, R.layout.item_goods_list);
    }

    @Override
    public void convert(ViewHolder holder, GoodsBean item) {
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getGoodsName());
        ((TextView) holder.getView(R.id.tv_price)).setText("¥"+item.getMarketPrice());
        ((TextView) holder.getView(R.id.tv_past_price)).setText("原价"+item.getPrice());
        ((TextView) holder.getView(R.id.tv_past_price)).getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG);  // 设置中划线并加清晰 ;
        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_goods),item.getDefalutPhotourl());
    }
}
