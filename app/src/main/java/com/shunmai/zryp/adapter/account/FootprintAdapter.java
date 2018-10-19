package com.shunmai.zryp.adapter.account;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shunmai.zryp.adapter.DividerItemDecoration;
import com.shunmai.zryp.adapter.search.FootprintContentAdapter;
import com.shunmai.zryp.bean.userinfo.FootprintBean;
import com.shunmai.zryp.R;

import java.util.ArrayList;

/**
 * Created by zyf on 2017/5/8.
 * 右边RecyclerView适配器
 */

public class FootprintAdapter extends RecyclerView.Adapter {

    private static final String TAG = FootprintAdapter.class.getSimpleName();
    private Context mContext;
    private ArrayList<FootprintBean.DataBean> mDataList;

    public FootprintAdapter(Context context, ArrayList<FootprintBean.DataBean> dataList) {
        mContext = context;
        mDataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_search, null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       ViewHolder viewHolder = (ViewHolder) holder;
        if (null != mDataList && mDataList.size() > 0) {
            RecyclerView rec_content = viewHolder.rc_item;
            FootprintContentAdapter adapter=new FootprintContentAdapter(mContext,new ArrayList<>());
            rec_content.setAdapter(adapter);
            rec_content.setLayoutManager(new GridLayoutManager(mContext,1));
            rec_content.addItemDecoration(new DividerItemDecoration( mContext, DividerItemDecoration.VERTICAL));
            adapter.add(mDataList.get(position).getList());
//            viewHolder.textContent.setText(mDataList.get(position).getText());
        } else {
            Log.i(TAG, "getView: null == mDataList");
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rc_item;
//        ImageView imageContent;
        ViewHolder(View itemView) {
            super(itemView);
            rc_item= itemView.findViewById(R.id.rv_classify_content);
//            imageContent=itemView.findViewById(R.id.iv_item_search);
        }
    }
}
