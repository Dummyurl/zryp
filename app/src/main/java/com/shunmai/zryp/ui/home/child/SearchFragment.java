package com.shunmai.zryp.ui.home.child;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.shunmai.zryp.adapter.home.CheckListener;
import com.shunmai.zryp.adapter.home.SortAdapter;
import com.shunmai.zryp.bean.GoodsCategoryBean;
import com.shunmai.zryp.repository.CategoryRepository;
import com.shunmai.zryp.adapter.ItemDecoration;
import com.shunmai.zryp.adapter.search.AdapterLeft;
import com.shunmai.zryp.adapter.search.AdapterRight;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.bean.GoodsBean;
import com.shunmai.zryp.bean.goods.CategoryBean;
import com.shunmai.zryp.ui.goods.GoodsSearchActivity;
import com.shunmai.zryp.viewmodel.SearchFragmentViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.FragmentSearchBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/27.
 */

public class SearchFragment extends BaseFragment<FragmentSearchBinding> implements View.OnClickListener ,CheckListener {
    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<GoodsCategoryBean> dataList = new ArrayList<>();
    private ArrayList<Integer> titlePosList = new ArrayList<>();
    private String mCurTitle = "";
    private RecyclerView recyclerView;
    private AdapterLeft mAdapterLeft;
    private AdapterRight mAdapterRight;
    private SearchFragmentViewModel searchViewModel;
    private LinearLayoutManager mLinearLayoutManager;
    private SortAdapter mSortAdapter;
    private boolean isMoved;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initLv();
        initView();
        initNewView();
        initNewData();
        searchViewModel = ViewModelProviders.of(this).get(SearchFragmentViewModel.class);
        searchViewModel.init(new CategoryRepository());
        searchViewModel.getCategoryBean(throwable -> showError()).observe(this, bean -> {
            initData(bean.getData());
                showContentView();
        });
    }

    private void initNewData() {
        mSortAdapter=new SortAdapter(getActivity(), titleList, (id, position) -> {
            isMoved = true;
            setChecked(position, true);
        });
        bindingView.rvSort.setAdapter(mSortAdapter);
    }

    private void initNewView() {
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        bindingView.rvSort.setLayoutManager(mLinearLayoutManager);
        bindingView.rvSort.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }
    //将当前选中的item居中
    private void moveToCenter(int position) {
        //将点击的position转换为当前屏幕上可见的item的位置以便于计算距离顶部的高度，从而进行移动居中
        View childAt = bindingView.rvSort.getChildAt(position - mLinearLayoutManager.findFirstVisibleItemPosition());
        if (childAt != null) {
            int y = (childAt.getTop() - bindingView.rvSort.getHeight() / 2);
            bindingView.rvSort.smoothScrollBy(0, y);
        }
    }
    private void setChecked(int position, boolean isLeft) {
        Log.d("p-------->", String.valueOf(position));
        if (isLeft) {
            mSortAdapter.setCheckedPosition(position);
            //此处的位置需要根据每个分类的集合来进行计算
            mAdapterRight.setSelection(position);
            if (null != titleList && titleList.size() > position){
                mAdapterRight.setSelection(position);}

        } else {
            if (isMoved) {
                isMoved = false;
            } else
                mSortAdapter.setCheckedPosition(position);
//            ItemHeaderDecoration.setCurrentTag(String.valueOf(position));//如果是滑动右边联动左边，则按照右边传过来的位置作为tag

        }
        moveToCenter(position);
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
//                        setChecked(i,isScroll);
                        mSortAdapter.setCheckedPosition(i);
                        moveToCenter(i);
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
            GoodsCategoryBean goodsCategoryBean = new GoodsCategoryBean();
            goodsCategoryBean.setTitle(data.get(i).getName());
            goodsCategoryBean.setTag(i+"");
            ArrayList<GoodsBean> goodsBeans = new ArrayList<>();
            for (int j = 0; j < data.get(i).getSonItem().size(); j++) {
                goodsBeans.add(new GoodsBean(data.get(i).getSonItem().get(j).getName(), data.get(i).getSonItem().get(j).getPic(),data.get(i).getSonItem().get(j).getSysId()));
            }
            goodsCategoryBean.setGoods(goodsBeans);
            dataList.add(goodsCategoryBean);
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

    @Override
    protected void onRefresh() {
        super.onRefresh();
        searchViewModel.getCategoryBean(throwable -> showError()).observe(this, bean -> {
            initData(bean.getData());
            showContentView();
        });
    }

    @Override
    public void check(int position, boolean isScroll) {
        setChecked(position,isScroll);
    }
}
