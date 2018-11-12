package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.underling.UnderlingBean;
import com.shunmai.zryp.ui.userinfo.underling.MyUnderlingActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/11.
 */

public class UnderlingAdapter extends CommonViewAdapter<UnderlingBean.DataBean> {
    public UnderlingAdapter(Context context, List<UnderlingBean.DataBean> datas) {
        super(context, datas, R.layout.item_underling);
        setCanShowEmpty(true);
    }

    @Override
    public void convert(ViewHolder holder, UnderlingBean.DataBean item) {
//    if (item.getUserType()==0){
//        holder.getView(R.id.tv_user_type).setVisibility(View.GONE);
//        holder.getView(R.id.tv_user_count).setVisibility(View.GONE);
//        holder.getView(R.id.iv_arrow).setVisibility(View.GONE);
//    }
//    if (item.getCount()>0&&item.getUserType()!=0){
//        holder.getView(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(mContext, MyUnderlingActivity.class);
//                intent.putExtra("userType", item.getUserType());
//                intent.putExtra("userId", item.getUserId());
//                mContext.startActivity(intent);
//
//            }
//        });
//    }
//    if (item.getUserType()==2){
//        ((TextView) holder.getView(R.id.tv_user_type)).setText("下属经理");
//    }else if (item.getUserType()==2){
//        ((TextView) holder.getView(R.id.tv_user_type)).setText("下属人数");
//    }
        int position=mDatas.indexOf(item);
        if (position%2==1){
            holder.getView(R.id.ll_item).setBackgroundColor(Color.parseColor("#F4F5F6"));
        }else{
            holder.getView(R.id.ll_item).setBackgroundColor(Color.WHITE);
        }
        ((TextView) holder.getView(R.id.tv_position)).setText(position+1+".");
        GlideCacheUtil.LoadImage(mContext, holder.getView(R.id.iv_user_icon),item.getPic());
        ((TextView) holder.getView(R.id.tv_user_name)).setText(item.getNickname());
    }
}
