package com.shunmai.zryp.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.bean.goods.GoodsOrderBean;
import com.shunmai.zryp.databinding.LayoutDialogSkuBinding;
import com.shunmai.zryp.ui.goods.GoodsOrderActivity;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddrDialog {

    private CustomDialog customDialog;

    private Context context;

    public AddrDialog(Context context, GoodsDetailBean.DataBean GoodsBean) {
        this.context = context;
        customDialog = new CustomDialog(context);
        customDialog.setSkus(GoodsBean);
        customDialog.init();
    }


    public void show() {
        customDialog.show();
    }

    private final class CustomDialog extends Dialog {
        //商品详情Item
        GoodsDetailBean.DataBean DialogGoodsBean;
        private LinearLayout container;
        private List<RadioGroup> radioGroups = new ArrayList<>();
        private List<String> skuItems = new ArrayList<>();
        private LayoutDialogSkuBinding databing;
        private StringBuffer skuDescription;
        //购买数量
        private int count = 1;

        CustomDialog(Context context) {
            super(context, R.style.BottomDialog);
        }

        private void setSkus(GoodsDetailBean.DataBean Skus) {
            DialogGoodsBean = Skus;
        }

        private void init() {
            databing = DataBindingUtil.inflate(((Activity) context).getLayoutInflater(), R.layout.layout_dialog_sku, null, false);
            setContentView(databing.getRoot());
            setCancelable(true);
            setCanceledOnTouchOutside(true);
            getWindow().setGravity(Gravity.BOTTOM);
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            container = databing.container;

        }




    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
