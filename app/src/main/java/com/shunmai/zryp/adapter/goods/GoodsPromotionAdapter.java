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
import com.ysy.commonlib.utils.StringUtils;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsPromotionAdapter extends CommonViewAdapter<PromotionGoodsBean> {
   private int type;
    public GoodsPromotionAdapter(Context context, List<PromotionGoodsBean> datas,int type) {
        super(context, datas, R.layout.item_promotion);
        this.type=type;
    }

    @Override
    public void convert(ViewHolder holder, PromotionGoodsBean item) {
        if (type==1){
            holder.getView(R.id.ll_score).setVisibility(View.VISIBLE);
        }else{
            holder.getView(R.id.ll_score).setVisibility(View.GONE);
        }
        ((TextView) holder.getView(R.id.tv_score_count)).setText(StringUtils.LargeCountFormat(item.getMscore()));
        ((TextView) holder.getView(R.id.tv_goods_price)).setText(item.getPrice()+"");
        ((TextView) holder.getView(R.id.tv_goods_name)) .setText(item.getGoodsName());
        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_goods),item.getDefalutPhotoURL());
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate(mContext,item.getGoodsId()));
    }
}
