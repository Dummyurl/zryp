package com.shunmai.zryp.ui.userinfo.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;

import com.shunmai.zryp.adapter.home.HomeFavouriteRecAdapter;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.FragmentOrderBinding;

import java.util.ArrayList;

public class OrderFragment extends BaseFragment<FragmentOrderBinding> {
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRec();
        showContentView();
    }

    private void initRec() {
    bindingView.rcOrder.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        ArrayList<String> arrayList=new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        HomeFavouriteRecAdapter<String> adapter=new HomeFavouriteRecAdapter(getActivity(),R.layout.item_order,new ArrayList(arrayList));
        bindingView.rcOrder.setAdapter(adapter);
    }

    @Override
    public int setContent() {
        return R.layout.fragment_order;
    }
}
