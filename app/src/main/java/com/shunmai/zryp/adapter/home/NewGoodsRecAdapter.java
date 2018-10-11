package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.zrypapp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/30.
 * 新品首发adapter
 */

public class NewGoodsRecAdapter extends CommonViewAdapter<GoodsBean>{

    public NewGoodsRecAdapter(Context context, List<GoodsBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, GoodsBean item) {
        ((TextView) holder.getView(R.id.tv_title)).setText(item.getGoodsName());
//        ((TextView) holder.getView(R.id.tv_second_title)).setText(item.getGoodsNote());
        ((TextView) holder.getView(R.id.tv_price)).setText("¥"+item.getMarketPrice());
        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_goods),item.getDefalutPhotourl(),false);
    }
}