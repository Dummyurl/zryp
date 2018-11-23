package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/22.
 */

public class SortAdapter extends CommonViewAdapter<String> {
    RvListener itemClickListener;

    public SortAdapter(Context context, List<String> datas, RvListener listener) {
        super(context, datas, R.layout.item_rg_classify);
        itemClickListener = listener;
    }

    private int checkedPosition;

    public void setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        notifyDataSetChanged();
    }

    @Override
    public void convert(ViewHolder holder, String item) {
        String content;
        if (item.length()>2){
            String start = item.substring(0, 2);
            String end = item.substring(2, item.length());
            content=start+"\n"+end;
        }else{
            content=item;
        }
        ((TextView) holder.getView(R.id.tv_classify_title)).setText(content);
        if (mDatas.indexOf(item) == checkedPosition) {
            holder.getView(R.id.view_red).setVisibility(View.VISIBLE);
            ((TextView) holder.getView(R.id.tv_classify_title)).setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
            ((TextView) holder.getView(R.id.tv_classify_title)).setTextColor(mContext.getResources().getColor(R.color.fontBlack));
            holder.getView(R.id.ll_search).setBackgroundColor(mContext.getResources().getColor(R.color.white));
        } else {
            ((TextView) holder.getView(R.id.tv_classify_title)).setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
            holder.getView(R.id.view_red).setVisibility(View.INVISIBLE);
            ((TextView) holder.getView(R.id.tv_classify_title)).setTextColor(mContext.getResources().getColor(R.color.fontNormal));
            holder.getView(R.id.ll_search).setBackgroundColor(mContext.getResources().getColor(R.color.colorHomeLine));
        }
        holder.getView(R.id.ll_search).setOnClickListener(v -> itemClickListener.onItemClick(v.getId(), mDatas.indexOf(item)));
    }

    //RecyclerView的item点击事件
    public interface RvListener {

        void onItemClick(int id, int position);
    }
}
