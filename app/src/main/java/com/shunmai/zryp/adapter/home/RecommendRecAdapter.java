package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/30.
 * 超值推荐adapter
 */

public class RecommendRecAdapter extends CommonViewAdapter<GoodsBean> {


    public RecommendRecAdapter(Context context, List<GoodsBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder,GoodsBean item) {
        ((TextView)holder.getView(R.id.tv_goods)).setText(item.getGoodsName());
        ((TextView)holder.getView(R.id.tv_price)).setText("¥"+item.getMarketPrice());
        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_goods),item.getDefalutPhotourl(),2,0);

    }
}
