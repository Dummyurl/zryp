package com.shunmai.zryp.adapter.search;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.userinfo.FootprintBean;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/12.
 */

public class FootprintContentAdapter extends CommonViewAdapter<FootprintBean.DataBean.ListBean> {
    public FootprintContentAdapter(Context context, List<FootprintBean.DataBean.ListBean> datas) {
        super(context, datas, R.layout.item_footprint);
    }

    @Override
    public void convert(ViewHolder holder, FootprintBean.DataBean.ListBean item) {
        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_goods),item.getDefalutPhotourl());
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getGoodsName());
        ((TextView) holder.getView(R.id.tv_goods_price)).setText("￥"+item.getPrice());
        ((TextView) holder.getView(R.id.tv_goods_price_post)).setText("￥"+item.getMarketPrice());
        ((TextView) holder.getView(R.id.tv_goods_price_post)).getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate(mContext,item.getSysIdString()));
         }
}
