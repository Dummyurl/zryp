package com.shunmai.zryp.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.shunmai.zryp.utils.DevicesUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkuDialog {

    private CustomDialog customDialog;

    private Context context;

    public SkuDialog(Context context, GoodsDetailBean.DataBean GoodsBean) {
        this.context = context;
        customDialog = new CustomDialog(context);
        customDialog.setSkus(GoodsBean);
        customDialog.init();
    }

    public CustomDialog getCustomDialog() {
        return customDialog;
    }

    public void show() {
        customDialog.show();
    }

    public final class CustomDialog extends Dialog implements View.OnClickListener {
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
            databing.ivAdd.setOnClickListener(this);
            databing.ivSub.setOnClickListener(this);
            databing.ivDismiss.setOnClickListener(this);
            databing.tvSubmit.setOnClickListener(this);
            //遍历SKU添加RadioGroup
            for (int i = 0; DialogGoodsBean.getSku().getSeekGoodsItemVOS() != null && i < DialogGoodsBean.getSku().getSeekGoodsItemVOS().size(); i++) {
                View inflate = ((Activity) context).getLayoutInflater().inflate(R.layout.item_sku_item, null);
                ((TextView) inflate.findViewById(R.id.sku_title)).setText(DialogGoodsBean.getSku().getSeekGoodsItemVOS().get(i).getItemName());
                skuItems.add(DialogGoodsBean.getSku().getSeekGoodsItemVOS().get(i).getItemName());
                RadioGroup rg_sku = inflate.findViewById(R.id.rg_sku);
                radioGroups.add(rg_sku);
                //遍历SKUBean添加radiobutton
                for (int j = 0; j < DialogGoodsBean.getSku().getSeekGoodsItemVOS().get(i).getNorms().size(); j++) {
                    RadioButton rb = (RadioButton) ((Activity) context).getLayoutInflater().inflate(R.layout.item_radio_button_sku, null);
                    rb.setId(DialogGoodsBean.getSku().getSeekGoodsItemVOS().get(i).getNorms().get(j).getNormId());
                    rb.setText(DialogGoodsBean.getSku().getSeekGoodsItemVOS().get(i).getNorms().get(j).getNormName());
                    RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(0, DevicesUtils.dip2px(context, 10), DevicesUtils.dip2px(context, 10), 0);
                    rb.setLayoutParams(layoutParams);
                    rg_sku.addView(rb);
                    if (j == 0) {
                        rb.setChecked(true);
                    }
                    rg_sku.setOnCheckedChangeListener((group, checkedId) -> {
                        databing.setBean(getSkuVos());
                    });

                }
                container.addView(inflate);
            }
            databing.setBean(getSkuVos());
        }

        private GoodsDetailBean.DataBean.SkuBean.SeekGoodsSkuVOSBean getSkuVos() {
            List<String> sysIdStrings = new ArrayList<>();
            StringBuffer buffer = new StringBuffer();
            skuDescription = new StringBuffer();
            for (int i = 0; i < radioGroups.size(); i++) {
                int normId = radioGroups.get(i).getCheckedRadioButtonId();
                for (GoodsDetailBean.DataBean.SkuBean.SeekGoodsItemVOSBean skuBean : DialogGoodsBean.getSku().getSeekGoodsItemVOS()) {
                    for (GoodsDetailBean.DataBean.SkuBean.SeekGoodsItemVOSBean.NormsBean normBean : skuBean.getNorms()) {
                        if (normBean.getNormId() == normId) {
                            sysIdStrings.add(normBean.getSysIdString());
                        }
                    }
                }
                skuDescription.append(skuItems.get(i) + ":" + ((RadioButton) radioGroups.get(i).findViewById(radioGroups.get(i).getCheckedRadioButtonId())).getText().toString().trim() + ",");
                buffer.append("\"" + ((RadioButton) radioGroups.get(i).findViewById(radioGroups.get(i).getCheckedRadioButtonId())).getText().toString().trim() + "\"");
            }
            databing.tvSkuType.setText("已选：" + buffer.toString());
            for (GoodsDetailBean.DataBean.SkuBean.SeekGoodsSkuVOSBean bean : DialogGoodsBean.getSku().getSeekGoodsSkuVOS()) {
                if (CheckStringContain(sysIdStrings, bean.getNormIds())) {
                    if (bean.getStock() < count) {
                        databing.tvSubmit.setBackgroundColor(context.getResources().getColor(R.color.colorLineDeep));
                    } else {
                        databing.tvSubmit.setBackgroundColor(context.getResources().getColor(R.color.fontRed));
                    }
                    return bean;
                }
            }
            return null;
        }

        public boolean CheckStringContain(List<String> stringList, String str) {
            for (String string : stringList) {
                if (!str.contains(string)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_add: {
                    count++;
                    databing.ivCount.setText(count + "");
                    if (count > databing.getBean().getStock()) {
                        databing.tvSubmit.setBackgroundColor(context.getResources().getColor(R.color.colorLineDeep));
                    }
                    break;
                }
                case R.id.iv_sub: {
                    if (count > 1) {
                        count--;
                        databing.ivCount.setText(count + "");
                        if (count <= databing.getBean().getStock()) {
                            databing.tvSubmit.setBackgroundColor(context.getResources().getColor(R.color.fontRed));
                        }
                    }
                    break;
                }
                case R.id.iv_dismiss: {
                    dismiss();
                    break;
                }
                case R.id.tv_submit: {
                    if (Utils.checkLogin(context)) {
                        GoodsDetailBean.DataBean.SkuBean.SeekGoodsSkuVOSBean skuVos = getSkuVos();
                        if (skuVos.getStock() == 0 || skuVos.getStock() < count) {
                            ToastUtils.showToast("库存不足");
                        } else {
                            Intent intent = new Intent(context, GoodsOrderActivity.class);
                            GoodsOrderBean bean = new GoodsOrderBean();
                            bean.setGoodsName(DialogGoodsBean.getGoods().getGoodsName());
                            bean.setBuyCount(count);
                            bean.setGoodsPrice(skuVos.getPrice());
                            bean.setImageUrl(skuVos.getDefaultPhotoPath());
                            bean.setSkuDescription(skuDescription.deleteCharAt(skuDescription.length() - 1).toString());
                            bean.setGoodsId(DialogGoodsBean.getGoods().getGoodsId());
                            bean.setSkuId(skuVos.getSkuId());
                            if (DialogGoodsBean.getGoods().getGoodsPropery() == 2 && DialogGoodsBean.getGoods().getChannelId() == 1) {
                                bean.setIsOutAddress(2);
                            } else {
                                bean.setIsOutAddress(1);
                            }

                            bean.setOid(0);
                            intent.putExtra("data", new Gson().toJson(bean));
                            context.startActivity(intent);
                            dismiss();
                        }
                    }
                    break;
                }
            }
        }


    }


}
