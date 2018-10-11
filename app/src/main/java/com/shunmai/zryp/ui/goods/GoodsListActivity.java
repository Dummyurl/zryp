package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shunmai.zryp.repository.GoodsListRepository;
import com.shunmai.zryp.adapter.goods.RecGoodsListAdapter;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.TitleBean;
import com.shunmai.zryp.viewmodel.GoodsListActivityViewModel;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityGoodsListBinding;

import java.util.ArrayList;


public class GoodsListActivity extends SwipeBackActivity<ActivityGoodsListBinding> {
    //从商品分类进入
    private final int CATEGORY = 1;
    //从首页进入
    private final int FIRSTPAGE = 2;
    //从搜索进入
    private final int SEARCH = 3;

    private RecGoodsListAdapter goodsListAdapter;
    private int page = 1;
    private int sortType = 1;
    private SmartRefreshLayout refreshLayout;
    private GoodsListActivityViewModel viewModel;
    private long catlaogMobileId;
    private int type;
    //首页进入界面类型
    private int HomePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        bindingView.setTitle(new TitleBean().setTitle(getIntent().getStringExtra("title")));
        viewModel = ViewModelProviders.of(this).get(GoodsListActivityViewModel.class);
        viewModel.init(new GoodsListRepository());
        type = getIntent().getIntExtra("type", -1);
        catlaogMobileId = getIntent().getLongExtra("catlaogMobileId", -1);
        HomePage = getIntent().getIntExtra("HomePage", -1);
        initRec();
        initRefresh();
        getData(false);
    }


    private void initRefresh() {
        refreshLayout = bindingView.refreshLayout;
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 1;
            getData(true);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.finishLoadMore();
                Toast.makeText(GoodsListActivity.this, "底部添加", Toast.LENGTH_SHORT).show();
            }
        }, 2000));
//        //触发自动刷新
//        refreshLayout.autoRefresh();
//        //点击测试
//        RefreshFooter footer = refreshLayout.getRefreshFooter();
//        if (footer != null) {
//            refreshLayout.getRefreshFooter().getView().findViewById(ClassicsFooter.ID_TEXT_TITLE).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getActivity().getBaseContext(), "点击测试", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
    }

    private void getData(boolean refresh) {
        switch (type) {
            case CATEGORY: {
                viewModel.getCategoryList(page++, catlaogMobileId, sortType).observe(this, goodsListBean -> {
                    if (goodsListBean != null) {
                        if (refresh) {
                                refreshLayout.finishRefresh();
                                goodsListAdapter.setData(goodsListBean.getData());
                        } else {
                            goodsListAdapter.add(goodsListBean.getData());
                        }
                    }
                });
                break;
            }
            case FIRSTPAGE: {
                viewModel.getFistPageData(page++, sortType, HomePage).observe(this, goodsListBean -> {
                    if (goodsListBean != null) {
                        if (refresh) {
                            refreshLayout.finishRefresh();
                            goodsListAdapter.setData(goodsListBean.getData());
                        } else {
                            goodsListAdapter.add(goodsListBean.getData());
                        }
                    }
                });
                break;
            }
            case SEARCH: {
                break;
            }
        }
        if (goodsListAdapter.getItemCount()==0){
            showEmpty();
        }else{
            showContentView();
        }
    }

    private void initRec() {
        RecyclerView rvGoodsList = bindingView.rvGoodsList;
        rvGoodsList.setLayoutManager(new GridLayoutManager(this, 2));
        goodsListAdapter = new RecGoodsListAdapter(this, new ArrayList<>());
        rvGoodsList.setAdapter(goodsListAdapter);
    }

    @Override
    protected void onRefresh() {
        getData(true);
    }
}
