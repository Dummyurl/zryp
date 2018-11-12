package com.shunmai.zryp.adapter.account;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsOrderBean;
import com.shunmai.zryp.bean.goods.GoodsOrderListBean;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.ui.goods.GoodsDetailActivity;
import com.shunmai.zryp.ui.goods.GoodsOrderActivity;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.OrderFragmentViewModel;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/11/5.
 */

public class OrderListAdapter extends CommonViewAdapter<GoodsOrderListBean> {
    OrderFragmentViewModel viewMoel;

    public OrderListAdapter(Context context, List<GoodsOrderListBean> datas, OrderFragmentViewModel viewMoel) {
        super(context, datas, R.layout.item_order);
        setCanShowEmpty(true);
        this.viewMoel=viewMoel;
    }

    @Override
    public void convert(ViewHolder holder, GoodsOrderListBean item) {

        ((TextView) holder.getView(R.id.tv_create_time)).setText(item.getSuccesstime());
        ((TextView) holder.getView(R.id.tv_goods_name)).setText(item.getTitle());
        GlideCacheUtil.LoadImage(mContext, holder.getView(R.id.iv_goods_img), item.getMainpic());
        ((TextView) holder.getView(R.id.tv_goods_count)).setText("x  " + item.getGeshu());
        ((TextView) holder.getView(R.id.tv_goods_price)).setText("￥" + item.getCost());
        ((TextView) holder.getView(R.id.tv_goods_total_cost)).setText("￥" + item.getTotalcost());
        ((TextView) holder.getView(R.id.tv_goods_total_count)).setText(item.getGeshu() + "");
        if (item.getPostcost() > 0) {
            ((TextView) holder.getView(R.id.tv_post_price)).setText("(含运费：￥" + item.getPostcost() + ")");
        }
        if (item.getState() == 0) {
            ((TextView) holder.getView(R.id.tv_state)).setTextColor(mContext.getResources().getColor(R.color.fontRed));
        } else {
            ((TextView) holder.getView(R.id.tv_state)).setTextColor(mContext.getResources().getColor(R.color.fontNormal));
        }
        ((TextView) holder.getView(R.id.tv_state)).setText(item.getStatename());
        switch (item.getState()) {
            case 1:
            case 2:
            case 4:
            case 6:
            case 3:
            case 0: {
                View.OnClickListener listener = v -> {
                    GoodsOrderBean bean = new GoodsOrderBean();
                    bean.setBuyCount(item.getGeshu());
                    bean.setImageUrl(item.getMainpic());
                    bean.setGoodsName(item.getTitle());
                    if (item.getState() == 0) {
                        bean.setOid(item.getOid());
                        bean.setAddrId(item.getAddrid());
                    } else {
                        bean.setOid(0);
                    }
                    bean.setGoodsPrice(item.getCost());
                    if (item.getState() == 0) {
                        bean.setGoodsId(item.getBid());
                    }
                    bean.setSkuId(item.getSkuid());
                    int type = 1;
                    if (item.getOrdertype() == 1) {
                        type = 2;
                    }
                    bean.setGoodsPropery(type);
                    Intent intent = new Intent(mContext, GoodsOrderActivity.class);
                    intent.putExtra("data", new Gson().toJson(bean));
                    mContext.startActivity(intent);
                };
                if (item.getState() == 0) {
                    holder.getView(R.id.tv_pay_again).setVisibility(View.VISIBLE);
                    holder.getView(R.id.tv_buy_again).setVisibility(View.GONE);
                    holder.getView(R.id.tv_confirm_goods).setVisibility(View.GONE);
                    holder.getView(R.id.tv_pay_again).setOnClickListener(listener);
                } else {
                    holder.getView(R.id.tv_buy_again).setVisibility(View.VISIBLE);
                    holder.getView(R.id.tv_pay_again).setVisibility(View.GONE);
                    holder.getView(R.id.tv_confirm_goods).setVisibility(View.GONE);
                    holder.getView(R.id.tv_buy_again).setOnClickListener(listener);
                }
                break;
            }
            case 5: {
                holder.getView(R.id.tv_confirm_goods).setVisibility(View.VISIBLE);
                holder.getView(R.id.tv_pay_again).setVisibility(View.GONE);
                holder.getView(R.id.tv_buy_again).setVisibility(View.GONE);
                holder.getView(R.id.tv_confirm_goods).setOnClickListener(v -> viewMoel.OrdersDelivery(item.getOid(), new onResponseListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        ToastUtils.showToast("已确认收货！");
                        viewMoel.Refresh();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast("确认收货失败！");
                    }
                }));
                break;
            }

        }
        holder.getView(R.id.ll_item).setOnClickListener(v -> GoodsDetailActivity.navigate(mContext, item.getBid()));
    }


}
