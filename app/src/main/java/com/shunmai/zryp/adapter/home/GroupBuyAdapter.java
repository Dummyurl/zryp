package com.shunmai.zryp.adapter.home;

import android.content.Context;
import android.widget.TextView;

import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.ViewHolder;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/30.
 */

public class GroupBuyAdapter extends CommonViewAdapter<HomePageBean> {

    public GroupBuyAdapter(Context context, List<HomePageBean> datas) {
        super(context, datas, R.layout.item_goods_fragment_item);
    }

    @Override
    public int getDataSize() {
        if (mDatas != null) {
            if (mDatas.size() > 6) {
                return 6;
            } else {
                return mDatas.size();
            }
        } else {
            return 0;
        }
    }

    @Override
    public void convert(ViewHolder holder, HomePageBean item) {
//        ((TextView) holder.getView(R.id.tv_price)).setText(item.getSeekGoodsVO().getMarketPrice()+"");
//        GlideCacheUtil.LoadImage(mContext,holder.getView(R.id.iv_goods),item.getSeekGoodsVO().getDefalutPhotourl());
//        ((TextView) holder.getView(R.id.tv_group_num)).setText(groupNum+"人拼团");
//    }
//
//    public void setData(List<HomePageBean.DataBean.GroupBuyingBean.PromotionGoodsListBean> list,int groupNum) {
//        super.setData(list);
//        this.groupNum=groupNum;
    }
}
