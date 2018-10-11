package com.shunmai.zryp.adapter.account;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.network.onResponseListener;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.AddressListViewModel;
import com.shunmai.zryp.zrypapp.R;
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

    public AddressListAdapter(Context context, List<AddressListBean.DataBean> datas, addressChangeListener listener, AddressListViewModel viewModel) {
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
    }

    @Override
    public void convert(ViewHolder holder, AddressListBean.DataBean item) {
        ((TextView) holder.getView(R.id.tv_user_name)).setText(item.getUsername());
        ((TextView) holder.getView(R.id.tv_user_phone)).setText(item.getMobile());
        ((TextView) holder.getView(R.id.tv_user_address)).setText(item.getAddr());
        setCheck(holder.getView(R.id.cb_address), mDatas.indexOf(item));
        if ((int) holder.getView(R.id.cb_address).getTag() == checked_position) {
            ((CheckBox) holder.getView(R.id.cb_address)).setChecked(true);
        } else {
            if (((CheckBox) holder.getView(R.id.cb_address)).isChecked()) {
                ((CheckBox) holder.getView(R.id.cb_address)).setChecked(false);
            }
        }
        holder.getView(R.id.tv_address_delete).setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("删除地址").setMessage("是否删除" + item.getUsername() + "收货地址？").setNegativeButton("确定", (itemDialog, which) -> {
                v.removeCallbacks(runnable);
                dialog.show();
                viewModel.deleteAddress(new onResponseListener<TResponse<String>>() {
                @Override
                public void onSuccess(TResponse<String> stringTResponse) {
                    mDatas.remove(item);
                    v.postDelayed(runnable, 1000);
                    notifyDataSetChanged();
                }

                @Override
                public void onFailed(Throwable throwable) {
                    ToastUtils.showToast(throwable.getMessage());
                    dialog.dismiss();
                    throwable.printStackTrace();
                }
            }, item.getId());
        }).setPositiveButton("取消", (dialog, which) -> dialog.dismiss());
            builder.show();

        });
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
                        box.postDelayed(runnable, 1000);
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

//    private void setCheck(CheckBox box, int position) {
//        box.setTag(position);
//        box.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            if (isChecked) {
//                if ((int) box.getTag() != checked_position){
//                    dialog.show();
//                    checked_position = position;
//                    specialUpdate();
//                }
//                box.removeCallbacks(runnable);
//                box.postDelayed(runnable, 1000);
//            } else if (((int) box.getTag()) == checked_position) {
//                box.setChecked(true);
//            }
//        });
//    }

    public interface addressChangeListener {
        void onChange();

        void onDelete();

        void onDefaultChange();
    }
}
