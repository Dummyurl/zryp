package com.shunmai.zryp.adapter;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shunmai.zryp.R;

import java.util.List;


/**
 * Created by yushengyang.
 * Date: 2018/8/23.
 */

public abstract class CommonViewAdapter<T> extends BaseRecyclerAdapter<T, ViewHolder> {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mDatas;
    private int mLayoutId;
    private boolean canShowEmpty = false;
    private final int EMPTY=0;
    private final int NO_EMPTY=1;
    public CommonViewAdapter(Context context, List<T> datas, int layoutId) {
        super(context, datas);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mDatas = datas;
        mLayoutId = layoutId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // 先inflate数据
        View itemView = mInflater.inflate(getLayoutId(i), viewGroup, false);
        // 返回ViewHolder
        ViewHolder holder = new ViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (mDatas != null && mDatas.size() != 0) {
            convert(viewHolder, mDatas.get(i));
        }
    }

    /**
     * 利用抽象方法回传出去，每个不一样的Adapter去设置
     *
     * @param item 当前的数据
     */
    public abstract void convert(ViewHolder holder, T item);

    @Override
    public int getItemCount() {
        if (mDatas.size() == 0&&canShowEmpty) {
            return 1;
        }
        return this.mDatas.size();
    }

    public void addData(T t) {
        mDatas.add(t);
        notifyDataSetChanged();
    }

    public void clear() {
        mDatas.clear();
    }

    public void addDatas(List<T> list) {
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public void setData(List<T> list) {
        mDatas.clear();
        mDatas.addAll(list);
        notifyDataSetChanged();
    }

    public int getLayoutId(int type) {
        if (type == 0) {
            return R.layout.item_empty;
        }
        return mLayoutId;
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas.size() == 0 && canShowEmpty) {
            return EMPTY;
        }
        return NO_EMPTY;
//        return super.getItemViewType(position);
    }

    public void setCanShowEmpty(boolean canShowEmpty) {
        this.canShowEmpty = canShowEmpty;
    }

    public int getDataSize() {
        if (mDatas != null) {
            return mDatas.size();
        } else {
            return 0;
        }
    }
}
