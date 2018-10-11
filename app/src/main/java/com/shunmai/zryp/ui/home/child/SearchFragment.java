package com.shunmai.zryp.ui.home.child;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.shunmai.zryp.repository.CategoryRepository;
import com.shunmai.zryp.adapter.ItemDecoration;
import com.shunmai.zryp.adapter.search.AdapterLeft;
import com.shunmai.zryp.adapter.search.AdapterRight;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.bean.Bean;
import com.shunmai.zryp.bean.GoodsBean;
import com.shunmai.zryp.bean.goods.CategoryBean;
import com.shunmai.zryp.ui.goods.GoodsSearchActivity;
import com.shunmai.zryp.viewmodel.SearchFragmentViewModel;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/27.
 */

public class SearchFragment extends BaseFragment<FragmentSearchBinding> implements View.OnClickListener {
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<Bean> dataList = new ArrayList<>();
    private ArrayList<Integer> titlePosList = new ArrayList<>();
    private String mCurTitle = "";
    private RecyclerView recyclerView;
    private AdapterLeft mAdapterLeft;
    private AdapterRight mAdapterRight;
    private SearchFragmentViewModel searchViewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLv();
        initView();
        searchViewModel = ViewModelProviders.of(this).get(SearchFragmentViewModel.class);
        searchViewModel.init(new CategoryRepository());
        searchViewModel.getCategoryBean(throwable -> showError()).observe(this, bean -> {
                initData(bean.getData());
                showContentView();
        });
    }



    private void initView() {
        bindingView.tvSearch.setOnClickListener(this);
    }


    private void initLv() {
        ListView lvClassify = bindingView.lvClassify;
        recyclerView = bindingView.rvClassify;
        mAdapterLeft = new AdapterLeft(getActivity(), titleList);
        lvClassify.setAdapter(mAdapterLeft);
        lvClassify.setOnItemClickListener((adapterView, view, pos, l) -> {
            mAdapterLeft.setSelection(pos);
            if (null != titleList && titleList.size() > pos)
                mAdapterRight.setSelection(pos);
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapterRight = new AdapterRight(getActivity(), dataList, titlePosList, recyclerView);
        recyclerView.addItemDecoration(new ItemDecoration(getActivity(), dataList, new ItemDecoration.OnDecorationCallback() {
            @Override
            public String onGroupId(int pos) {
                if (dataList.get(pos).getTitle() != null)
                    return dataList.get(pos).getTitle();
                return "-1";
            }

            @Override
            public String onGroupFirstStr(int pos) {
                if (dataList.get(pos).getTitle() != null)
                    return dataList.get(pos).getTitle();
                return "";
            }

            @Override
            public void onGroupFirstStr(String title) {
                for (int i = 0; i < titleList.size(); i++) {
                    if (!mCurTitle.equals(title) && title.equals(titleList.get(i))) {
                        mCurTitle = title;
                        mAdapterLeft.setSelection(i);
                        lvClassify.setSelection(i);
                    }
                }
            }
        }));
        recyclerView.setAdapter(mAdapterRight);

    }

    /**
     * 数据
     */
    private void initData(List<CategoryBean.DataBean> data) {
        titlePosList.add(0);
        for (int i = 0; i < data.size(); i++) {
            Bean bean = new Bean();
            bean.setTitle(data.get(i).getName());
            ArrayList<GoodsBean> goodsBeans = new ArrayList<>();
            for (int j = 0; j < data.get(i).getSonItem().size(); j++) {
                goodsBeans.add(new GoodsBean(data.get(i).getSonItem().get(j).getName(), data.get(i).getSonItem().get(j).getPic(),data.get(i).getSonItem().get(j).getSysId()));
            }
            bean.setGoods(goodsBeans);
            dataList.add(bean);
            titleList.add(dataList.get(dataList.size() - 1).getTitle());
            titlePosList.add(dataList.size());
        }
        mAdapterLeft.notifyDataSetChanged();
        mAdapterRight.notifyDataSetChanged();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_search;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search: {
                startActivity(new Intent(getActivity(), GoodsSearchActivity.class));
                break;
            }
        }
    }
}
