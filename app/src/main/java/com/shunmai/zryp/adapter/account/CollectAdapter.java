package com.shunmai.zryp.adapter.account;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.userinfo.CollectBean;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/10.
 */

public class CollectAdapter extends CommonViewAdapter<CollectBean> {
    private onDeleteClickListener linstener;

    public CollectAdapter(Context context, List<CollectBean> datas, onDeleteClickListener linstener) {
        super(context, datas, R.layout.item_collect);
        this.linstener = linstener;
        setCanShowEmpty(true);
    }

    @Override
    public void convert(ViewHolder holder, CollectBean item) {
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getGoodsName());
        ((TextView) holder.getView(R.id.tv_goods_price)).setText("￥" + item.getPrice());
        ((TextView) holder.getView(R.id.tv_collect_count)).setText(item.getStarNum() + "人收藏");
        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_goods_img),item.getDefalutPhotoURL());
        if (!item.isIsEnable()) {
            holder.getView(R.id.iv_collect_past_due).setVisibility(View.VISIBLE);
            ((TextView) holder.getView(R.id.tv_goods_name)).setTextColor(Color.parseColor("#999999"));
        } else {
            ((TextView) holder.getView(R.id.tv_goods_name)).setTextColor(Color.parseColor("#222222"));
            holder.getView(R.id.iv_collect_past_due).setVisibility(View.GONE);
            holder.getView(R.id.sm_item).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsDetailActivity.navigate(mContext,item.getGoodsId());
                }
            });
        }
        holder.getView(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linstener.onDelete(mDatas.indexOf(item), item.getCollectId());
            }
        });
    }

    public interface onDeleteClickListener {
        void onDelete(int position,int collectId);
    }
}
