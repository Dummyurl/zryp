package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.shunmai.zryp.AppConfig;
import com.shunmai.zryp.repository.GoodsListRepository;
import com.shunmai.zryp.adapter.goods.RecGoodsListAdapter;
import com.ysy.commonlib.base.SwipeBackActivity;
import com.shunmai.zryp.bean.TitleBean;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.GoodsListActivityViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityGoodsListBinding;

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
    private String goodsName;
    private PayBroadcastReceiver mReceiver;

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
        goodsName = getIntent().getStringExtra("goodsName");
        goodsListAdapter = new RecGoodsListAdapter(this, new ArrayList<>());
        bindingView.rvGoodsList.setAdapter(goodsListAdapter);
        initRefresh();
        initRadio();
        getData(true);
        initListener();
    }

    private void initListener() {
        mReceiver = new PayBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(AppConfig.PaySuccess);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);
    }

    private void initRadio() {
        bindingView.rgList.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_1: {
                    sortType = 1;
                    break;
                }
                case R.id.rb_2: {
                    sortType = 2;
                    break;
                }
                case R.id.rb_3: {
                    sortType = 3;
                    break;
                }
            }
            getData(true);
        });
    }


    private void initRefresh() {
        refreshLayout = bindingView.refreshLayout;
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            getData(true);
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> getData(false));
    }

    private void getData(boolean refresh) {
        if (refresh) {
            page = 1;
            refreshLayout.setNoMoreData(false);
        }
        switch (type) {
            case CATEGORY: {
                viewModel.getCategoryList(page++, catlaogMobileId, sortType).observe(this, goodsListBean -> {
                    refreshLayout.finishRefresh();
                    if (goodsListBean != null) {
                        if (goodsListBean.getPageNum() == goodsListBean.getPageCount()) {
                            refreshLayout.setNoMoreData(true);
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        } else {
                            refreshLayout.finishLoadMore();
                        }
                        if (refresh) {
                            goodsListAdapter.setData(goodsListBean.getData());
                        } else {
                            goodsListAdapter.add(goodsListBean.getData());
                        }
                    }
                    if (refresh) {
                        checkEmpty();
                    }
                });
                break;
            }
            case FIRSTPAGE: {
                viewModel.getFistPageData(page++, sortType, HomePage).observe(this, goodsListBean -> {
                    refreshLayout.finishRefresh();
                    refreshLayout.finishLoadMore();
                    if (goodsListBean != null) {
                        if (goodsListBean.getPageNum() == goodsListBean.getPageCount()) {
                            refreshLayout.setNoMoreData(true);
                        }
                        if (refresh) {
                            goodsListAdapter.setData(goodsListBean.getData());
                        } else {
                            goodsListAdapter.add(goodsListBean.getData());
                        }
                    }
                    if (refresh) {
                        checkEmpty();
                    }
                });
                break;
            }
            case SEARCH: {
                viewModel.searchGoods(page++, goodsName, sortType).observe(this, goodsListBean -> {
                    refreshLayout.finishRefresh();
                    if (goodsListBean != null) {
                        if (goodsListBean.getPageNum() == goodsListBean.getPageCount()) {
                            refreshLayout.setNoMoreData(true);
                            refreshLayout.finishLoadMoreWithNoMoreData();
                        } else {
                            refreshLayout.finishLoadMore();
                        }
                        if (refresh) {
                            goodsListAdapter.setData(goodsListBean.getData());
                        } else {
                            goodsListAdapter.add(goodsListBean.getData());
                        }
                    }
                    if (refresh) {
                        checkEmpty();
                    }
                });
                break;
            }
        }
    }

    private void checkEmpty() {
        if (goodsListAdapter.getDataSize() == 0) {
            initRec(true);
            bindingView.rvGoodsList.setLayoutManager(new LinearLayoutManager(this));
            showContentView();
        } else {
            initRec(false);
            showContentView();
        }
    }

    private void initRec(boolean isEmpty) {
        if (isEmpty) {
            bindingView.rvGoodsList.setLayoutManager(new LinearLayoutManager(this));
        } else {
            bindingView.rvGoodsList.setLayoutManager(new GridLayoutManager(this, 2));
        }

    }

    @Override
    protected void onRefresh() {
        getData(true);
    }

    private class PayBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppConfig.PaySuccess)) {
                finish();
            }
        }
    }
}
