package com.shunmai.zryp.adapter.wallet;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.wallet.WalletListBean;
import com.shunmai.zryp.ui.wallet.WithdrawDetailActivity;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/11.
 */

public class WithdrawListAdapter extends CommonViewAdapter<WalletListBean> {
    public WithdrawListAdapter(Context context, List<WalletListBean> datas) {
        super(context, datas, R.layout.item_withdraw);
        setCanShowEmpty(true);
    }

    //  state 状态：0 审核中; -1 审核未通过; 1 审核通过，已打款; 2 打款成功，已到账; -2 打款失败;
    @Override
    public void convert(ViewHolder holder, WalletListBean item) {
        int position = mDatas.indexOf(item);
        if (position % 2 == 1) {
            holder.getView(R.id.ll_item).setBackgroundColor(Color.parseColor("#F4F5F6"));
        } else {
            holder.getView(R.id.ll_item).setBackgroundColor(Color.WHITE);
        }
        ((TextView) holder.getView(R.id.tv_count_num)).setText(item.getAmout()+"元");
        ((TextView) holder.getView(R.id.tv_time)).setText(item.getSysCreateTime());
        ((TextView) holder.getView(R.id.tv_state)).setText(item.getStateDisplay());
        holder.getView(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, WithdrawDetailActivity.class);
                intent.putExtra("data",new Gson().toJson(item));
                mContext.startActivity(intent);
            }
        });
        switch (item.getState()){
            case -2:
            case -1:{
                ((TextView) holder.getView(R.id.tv_state)).setTextColor(mContext.getResources().getColor(R.color.fontBlack));
                break;
            }
            case 1:{
                ((TextView) holder.getView(R.id.tv_state)).setTextColor(mContext.getResources().getColor(R.color.fontGold));
                break;
            }
            case 2:{
                ((TextView) holder.getView(R.id.tv_state)).setTextColor(mContext.getResources().getColor(R.color.fontNormal));
                break;
            }
        }
    }
}
