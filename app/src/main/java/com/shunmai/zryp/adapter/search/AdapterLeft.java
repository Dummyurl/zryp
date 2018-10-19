package com.shunmai.zryp.adapter.search;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.shunmai.zryp.R;

import java.util.ArrayList;

/**
 * Created by zyf on 2017/5/8.
 * 左边ListView适配器
 */

public class AdapterLeft extends BaseAdapter {

    private static final String TAG = AdapterLeft.class.getSimpleName();
    private Context mContext;
    private ArrayList<String> mDataList = new ArrayList<>();
    private int mSelection = 0;
    public AdapterLeft(Context mContext, ArrayList<String> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        if (null != mDataList)
            return mDataList.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        if (null != mDataList)
            return mDataList.get(i);
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (null == view){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_rg_classify, null);
            viewHolder.textContent = view.findViewById(R.id.tv_classify_title);
            viewHolder.view_red=view.findViewById(R.id.view_red);
            viewHolder.ll_search=view.findViewById(R.id.ll_search);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (null != viewHolder.textContent && mSelection == position){
            viewHolder.view_red.setVisibility(View.VISIBLE);
            viewHolder.textContent.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));//加粗
            viewHolder.ll_search.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        }else {
            viewHolder.view_red.setVisibility(View.INVISIBLE);
            viewHolder.textContent.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));//普通
            viewHolder.ll_search.setBackgroundColor(mContext.getResources().getColor(R.color.colorHomeLine));
        }
        if (null != viewHolder.textContent && null != mDataList && mDataList.size()>0){
            viewHolder.textContent.setText(mDataList.get(position));
        }else {
            Log.i(TAG, "getView: null == mDataList");
        }
        return view;
    }

    public int getSelection() {
        return mSelection;
    }

    public void setSelection(int selection) {
        mSelection = selection;
        notifyDataSetChanged();
    }

    class ViewHolder{
        View view_red;
        TextView textContent;
        View ll_search;
    }
}
