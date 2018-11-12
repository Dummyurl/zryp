package com.shunmai.zryp.ui.home.child;


import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kevin.wraprecyclerview.WrapAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shunmai.zryp.adapter.CIrImageLoader;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.GlideImageLoader;
import com.shunmai.zryp.adapter.SpaceItemDecoration;
import com.shunmai.zryp.adapter.home.FlashSaleRecAdapter;
import com.shunmai.zryp.adapter.home.GroupBuyAdapter;
import com.shunmai.zryp.adapter.home.GuessGoodsAdapter;
import com.shunmai.zryp.adapter.home.NewGoodsRecAdapter;
import com.shunmai.zryp.adapter.home.RecommendRecAdapter;
import com.shunmai.zryp.base.BaseFragment;
import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.eventhandler.home.FirstPageHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.FragmentHomePageBinding;
import com.shunmai.zryp.databinding.HeaderFirstpageBinding;
import com.shunmai.zryp.viewmodel.HomePageViewModel;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * 首页Fragment
 */
public class HomePageFragment extends BaseFragment<FragmentHomePageBinding> {


    private HeaderFirstpageBinding firstpageBinding;
    private RecommendRecAdapter recommendRecAdapter;
    private NewGoodsRecAdapter newGoodsRecAdapter;
    private List<String> topImages = new ArrayList<>();
    private List<String> centerImages = new ArrayList<>();
    private GroupBuyAdapter groupBuyAdapter;
    private FlashSaleRecAdapter flashSaleRecAdapter;
    private GuessGoodsAdapter guessGoodsAdapter;
    private SmartRefreshLayout refreshLayout;
    private int page = 1;
    private WrapAdapter<GuessGoodsAdapter> wrapAdapter;
    private HomePageViewModel viewModel;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRefresh();
        initHeader();
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);

        getData();
        getBottomData();
    }


    protected void getData() {
        viewModel.HomePageInfo(new onResponseListener<HomePageBean>() {
            @Override
            public void onSuccess(HomePageBean homePageBean) {
                showContentView();
                firstpageBinding.setBean(homePageBean);
                {
                    refreshLayout.finishRefresh(true);
                }
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });
    }

    public void getBottomData() {
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GuessYouLike(page++), listTResponse -> {
            guessGoodsAdapter.add(listTResponse.getData());
            wrapAdapter.notifyDataSetChanged();
//                if (listTResponse.getPageSize()==listTResponse.getTotalCount()){
//                    refreshLayout.setNoMoreData(true);
//                    refreshLayout.finishLoadMoreWithNoMoreData();
//                }else
            {
                refreshLayout.finishLoadMore(true);
                refreshLayout.finishRefresh(true);
            }
        }, throwable -> {
            refreshLayout.finishLoadMore(false);
            refreshLayout.finishRefresh(false);
        });
    }

    @Override
    protected void onRefresh() {
//        getData();
    }

    private void initHeader() {
        firstpageBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.header_firstpage, null, false);
//        recommendRecAdapter = new RecommendRecAdapter(getActivity(), new ArrayList<>(), R.layout.item_recommend);
//        newGoodsRecAdapter = new NewGoodsRecAdapter(getActivity(), new ArrayList<>(), R.layout.item_new_goods);
//        groupBuyAdapter = new GroupBuyAdapter(getActivity(), new ArrayList<>());
//        flashSaleRecAdapter = new FlashSaleRecAdapter(getActivity(), new ArrayList<>());
//        initRec(firstpageBinding.rvAct, true, 30, 1, recommendRecAdapter);
//        initRec(firstpageBinding.rvNewGoods, true, 3, 1, newGoodsRecAdapter);
//        initRec(firstpageBinding.rvGroupBuy, false, 130, 1, groupBuyAdapter);
//        initRec(firstpageBinding.rvFlashSale, true, 25, 1, flashSaleRecAdapter);
//        firstpageBinding.ctXsg.init("%s", (3600));
//        firstpageBinding.ctXsg.start(0);
        intiBottomRec(bindingView.recHome);
        firstpageBinding.setHandler(new FirstPageHandler());
    }

    private void intiBottomRec(RecyclerView recHome) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recHome.setLayoutManager(gridLayoutManager);
        guessGoodsAdapter = new GuessGoodsAdapter(getActivity(), new ArrayList<>());
        wrapAdapter = new WrapAdapter<>(guessGoodsAdapter);
        wrapAdapter.adjustSpanSize(recHome);

        recHome.setAdapter(wrapAdapter);
        wrapAdapter.addHeaderView(firstpageBinding.getRoot());
        recHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View viewByPosition = gridLayoutManager.findViewByPosition(0);
                if (viewByPosition != null) {
                    bindingView.viewBackground.setAlpha(-(float) viewByPosition.getTop() / (float) firstpageBinding.banner.getHeight());
                }
            }


        });
    }

    private void initRec(RecyclerView rec, boolean isVertical, int dividerSize, int spanCount, CommonViewAdapter adapter) {
        rec.setFocusableInTouchMode(false);
        rec.setItemAnimator(new DefaultItemAnimator());
        if (spanCount > 1) {
            rec.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        } else {
            if (!isVertical) {
                rec.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            } else {
                rec.addItemDecoration(new SpaceItemDecoration(0, dividerSize));
                rec.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        }
        rec.setAdapter(adapter);
    }

    private void initRefresh() {
        refreshLayout = bindingView.refreshLayout;
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            page = 1;
            guessGoodsAdapter.clear();
            getData();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            getBottomData();
        });
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home_page;
    }


}
