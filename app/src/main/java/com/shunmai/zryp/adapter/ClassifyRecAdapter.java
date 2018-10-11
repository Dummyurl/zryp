package com.shunmai.zryp.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.shunmai.zryp.adapter.home.HomeFavouriteRecAdapter;
import com.shunmai.zryp.zrypapp.R;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by yushengyang.
 * Date: 2018/8/29.
 */

public class ClassifyRecAdapter<T> extends CommonViewAdapter<T>{

    public ClassifyRecAdapter(Context context, List<T> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, T item) {
        RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.rv_classify_content);
        initRec(recyclerView,R.layout.item_search_content,null,false,15,4);
    }
    private void initRec(RecyclerView rec, int id, LinkedList<String> data, boolean isVertical, int dividerSize, int spanCount) {
        rec.setFocusableInTouchMode(false);
        if (data == null) {
            data = new LinkedList<>();
        }
        rec.setItemAnimator(new DefaultItemAnimator());
        if (spanCount > 1) {
            rec.setLayoutManager(new GridLayoutManager(mContext, spanCount));
        } else {
            if (!isVertical) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                rec.setLayoutManager(linearLayoutManager);
            } else {
                rec.addItemDecoration(new SpaceItemDecoration(0, dividerSize));
                rec.setLayoutManager(new LinearLayoutManager(mContext));
            }
        }
        HomeFavouriteRecAdapter adapter = new HomeFavouriteRecAdapter(mContext, id, data);
        rec.setAdapter(adapter);
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
        adapter.addData("");
    }

}
