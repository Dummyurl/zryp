package com.shunmai.zryp.adapter.search;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.GoodsBean;
import com.shunmai.zryp.ui.goods.GoodsListActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.zrypapp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/3.
 */

public class SearchContentRecAdapter extends CommonViewAdapter<GoodsBean> {
    public SearchContentRecAdapter(Context context, List<GoodsBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, GoodsBean item) {
        ((TextView) holder.getView(R.id.tv_item_search)).setText(item.getGoodsName());
        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_item_search),item.getGoodsImg());
        holder.getView(R.id.ll_search).setOnClickListener(v -> {
            Intent intent = new Intent(mContext, GoodsListActivity.class);
            intent.putExtra("catlaogMobileId",item.getSysId());
            intent.putExtra("title",item.getGoodsName());
            intent.putExtra("type",1);
            mContext.startActivity(intent);
        });
    }
}
