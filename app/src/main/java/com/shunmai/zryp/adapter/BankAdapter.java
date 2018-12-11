package com.shunmai.zryp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.BankBean;
import com.shunmai.zryp.utils.ToastUtils;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/12/6.
 */

public class BankAdapter extends CommonViewAdapter<BankBean> {
    int checkPosition;
   private bankCheckedListener listener;
    public BankAdapter(Context context, List<BankBean> datas,int CheckPosition,bankCheckedListener listener) {
        super(context, datas, R.layout.item_bank_list);
        this.checkPosition=CheckPosition;
        this.listener=listener;
    }

    @Override
    public void convert(ViewHolder holder, BankBean item) {
        if (mDatas.indexOf(item)==checkPosition){
            holder.getView(R.id.iv_checked).setVisibility(View.VISIBLE);
        }else {
            holder.getView(R.id.iv_checked).setVisibility(View.GONE);
        }
        ((TextView) holder.getView(R.id.tv_bank_name)).setText(item.getBankname());
        holder.getView(R.id.ll_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onChecked(item);
            }
        });
    }
    public interface bankCheckedListener{
        void onChecked(BankBean item);
    }
}
