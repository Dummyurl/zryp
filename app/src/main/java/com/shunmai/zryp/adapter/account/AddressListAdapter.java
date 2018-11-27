package com.shunmai.zryp.adapter.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.ui.userinfo.account.AddressDetailActivity;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.view.MyAlertDialog;
import com.shunmai.zryp.viewmodel.AddressListViewModel;
import com.shunmai.zryp.R;
import com.ysy.commonlib.base.TResponse;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/20.
 */

public class AddressListAdapter extends CommonViewAdapter<AddressListBean.DataBean> {
    private final ZLoadingDialog dialog;
    private final Handler handler;
    private AddressListViewModel viewModel;
    public int checked_position = 0;
    private int type;

    public AddressListAdapter(Context context, List<AddressListBean.DataBean> datas, AddressListViewModel viewModel, int type) {
        super(context, datas, R.layout.item_address_list);
        this.viewModel = viewModel;
        dialog = new ZLoadingDialog(mContext);
        dialog.setLoadingBuilder(Z_TYPE.SINGLE_CIRCLE)//设置类型
                .setLoadingColor(Color.RED)//颜色
                .setHintText("Loading...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#aaffffff")) // 设置背景色，默认白色;
                .setCanceledOnTouchOutside(false)
        ;
        handler = new Handler();
        setCanShowEmpty(true);
        this.type = type;
    }

    @Override
    public void convert(ViewHolder holder, AddressListBean.DataBean item) {
        String[] split = item.getAddr().split(",");
        StringBuffer addressDetail = new StringBuffer();
        for (String str : split) {
            addressDetail.append(str);
        }
        addressDetail.append(item.getDetailAddress());
        ((TextView) holder.getView(R.id.tv_user_name)).setText(item.getUsername());
        ((TextView) holder.getView(R.id.tv_user_phone)).setText(item.getMobile());
        ((TextView) holder.getView(R.id.tv_user_address)).setText(addressDetail.toString());
        setCheck(holder.getView(R.id.cb_address), mDatas.indexOf(item));
        if ((int) holder.getView(R.id.cb_address).getTag() == checked_position) {
            ((CheckBox) holder.getView(R.id.cb_address)).setChecked(true);
        } else {
            if (((CheckBox) holder.getView(R.id.cb_address)).isChecked()) {
                ((CheckBox) holder.getView(R.id.cb_address)).setChecked(false);
            }
        }
        holder.getView(R.id.tv_address_delete).setOnClickListener(v -> {
            MyAlertDialog alertDialog = new MyAlertDialog(mContext, "是否删除\"" + item.getUsername() + "\"收货地址？");
            alertDialog.setPositiveListener(v1 -> {
                v1.removeCallbacks(runnable);
                viewModel.deleteAddress(new onResponseListener<TResponse<String>>() {
                    @Override
                    public void onSuccess(TResponse<String> stringTResponse) {
                        mDatas.remove(item);
                        v1.postDelayed(runnable, 0);
                        dialog.dismiss();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                        dialog.dismiss();
                        throwable.printStackTrace();
                    }
                }, item.getId());
                alertDialog.dismiss();
                dialog.show();
            });
            alertDialog.show();
        });
        holder.getView(R.id.tv_address_edit).setOnClickListener(v -> {
            Intent intent = new Intent(mContext, AddressDetailActivity.class);
            intent.putExtra("data", new Gson().toJson(item));
            intent.putExtra("regionType", item.getIsOutAddress());
            ((Activity) mContext).startActivityForResult(intent, 200);
        });
        if (type == 1) {
            holder.getView(R.id.ll_item).setOnClickListener(v -> {
                Intent intent = new Intent();
                intent.putExtra("address", new Gson().toJson(item));
                ((Activity) mContext).setResult(200, intent);
                ((Activity) mContext).finish();
            });
        }
    }

    final Runnable r = () -> notifyDataSetChanged();

    private void specialUpdate() {
        handler.removeCallbacks(r);
        handler.post(r);
    }

    private void setCheck(CheckBox box, int position) {
        box.setTag(position);
        box.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if ((int) box.getTag() != checked_position) {
                    dialog.show();
                    checked_position = position;
                    specialUpdate();
                }
                viewModel.changeDefaultAddress(new onResponseListener<TResponse<String>>() {
                    @Override
                    public void onSuccess(TResponse<String> stringTResponse) {
                        box.removeCallbacks(runnable);
                        box.postDelayed(runnable, 0);
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        dialog.dismiss();
                    }
                }, mDatas.get(position).getId());

            } else if (((int) box.getTag()) == checked_position) {
                box.setChecked(true);
            }
        });
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dialog.cancel();
        }
    };


}
