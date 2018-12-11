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

import com.google.gson.Gson;
import com.kevin.wraprecyclerview.WrapAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.CommonViewAdapter;
import com.shunmai.zryp.adapter.GlideImageLoader;
import com.shunmai.zryp.adapter.SpaceItemDecoration;
import com.shunmai.zryp.adapter.home.FlashSaleRecAdapter;
import com.shunmai.zryp.adapter.home.GuessGoodsAdapter;
import com.shunmai.zryp.adapter.home.NewGoodsRecAdapter;
import com.shunmai.zryp.adapter.home.RecommendRecAdapter;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.databinding.FragmentHomePageBinding;
import com.shunmai.zryp.databinding.HeaderFirstpageBinding;
import com.shunmai.zryp.eventhandler.home.FirstPageHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.HomePageViewModel;
import com.youth.banner.listener.OnBannerListener;
import com.ysy.commonlib.activity.WebViewActivity;
import com.ysy.commonlib.base.BaseFragment;
import com.ysy.commonlib.base.TResponse;
import com.ysy.commonlib.network.retrofiturlmanager.RetrofitUrlManager;
import com.ysy.commonlib.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页Fragment
 */
public class HomePageFragment extends BaseFragment<FragmentHomePageBinding> {


    private HeaderFirstpageBinding firstpageBinding;
    private RecommendRecAdapter recommendRecAdapter;
    private NewGoodsRecAdapter newGoodsRecAdapter;
    private List<String> topImages = new ArrayList<>();
    private List<String> centerImages = new ArrayList<>();
    private FlashSaleRecAdapter flashSaleRecAdapter;
    private GuessGoodsAdapter guessGoodsAdapter;
    private SmartRefreshLayout refreshLayout;
    private int start = 1;
    private int limit = 20;
    private WrapAdapter<GuessGoodsAdapter> wrapAdapter;
    private HomePageViewModel viewModel;
    private boolean canRequest = true;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRefresh();
        initHeader();
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel.class);
        getData();
        getMainAd();
    }


    protected void getData() {
        viewModel.HomePageInfo(new onResponseListener<HomePageBean>() {
            @Override
            public void onSuccess(HomePageBean bean) {
                showContentView();
                firstpageBinding.setBean(bean);
                guessGoodsAdapter.add(bean.getGuessYouLike());
                {
                    refreshLayout.finishRefresh(true);
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                refreshLayout.finishRefresh(false);
                showError();
            }
        });
    }

    public void getBottomData() {
        start += limit;
        sendRequest(RetrofitClient.getInstance().getService(HttpService.class).GuessYouLike(start, limit), listTResponse -> {
            guessGoodsAdapter.add(listTResponse.getData());
            wrapAdapter.notifyDataSetChanged();
            if (listTResponse.getData().size() < limit) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            } else {
                refreshLayout.finishLoadMore(true);
            }
            refreshLayout.finishRefresh(true);

        }, throwable -> {
            refreshLayout.finishLoadMore(false);
            refreshLayout.finishRefresh(false);
        });
    }

    @Override
    protected void onRefresh() {
        getData();
    }

    private void initHeader() {
        firstpageBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.header_firstpage, null, false);
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
//                Log.i("setAlpha", "Alpha:" + -(float) viewByPosition.getTop() / (float) firstpageBinding.banner.getHeight() + "\n" + -(float) viewByPosition.getTop() + "\n" + (float) firstpageBinding.banner.getHeight());
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
            start = 1;
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


    public void getMainAd() {
        RetrofitUrlManager.getInstance().putDomain("miniProgram", RetrofitClient.MiNi_Program_URL);
        viewModel.GetMainAd(new onResponseListener<TResponse<String>>() {
            @Override
            public void onSuccess(TResponse<String> response) {
                Log.i("okhttp", new Gson().toJson(response));
                ArrayList<String> topImages = new ArrayList<>();
                for (TResponse.MainadBean ad : response.getMainad()) {
                    topImages.add(ad.getPic());
                }
                firstpageBinding.banner.setImageLoader(new GlideImageLoader()).setImages(topImages).start();
                firstpageBinding.banner.setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        if (!StringUtils.isEmptyString(response.getMainad().get(position).getUrl())){
                            WebViewActivity.toWebView(getActivity(),"首页活动",response.getMainad().get(position).getUrl());
                        }else{
                            WebViewActivity.toWebView(getActivity(),"首页活动","http://www.baidu.com");
                        }
                    }
                });
            }

            @Override
            public void onFailed(Throwable throwable) {
                if (canRequest) {
                    getMainAd();
                    canRequest = false;
                }
                throwable.printStackTrace();
            }
        });
    }
}
