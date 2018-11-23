package com.shunmai.zryp.ui.goods;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;

import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.home.GroupBuyAdapter;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.databinding.FragmentGoodsItemBinding;

import java.util.ArrayList;


public class GoodsItemFragment extends BaseFragment<FragmentGoodsItemBinding> {
    private GroupBuyAdapter adapter;

    @Override
    public int setContent() {
        return R.layout.fragment_goods_item;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        bindingView.rlGoodsItem.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        adapter = new GroupBuyAdapter(getActivity(), new ArrayList<>());
        bindingView.rlGoodsItem.setAdapter(adapter);
    }
}
