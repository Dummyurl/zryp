package com.shunmai.zryp.ui.home.child;


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
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.eventhandler.home.FirstPageHandler;
import com.shunmai.zryp.network.RetrofitClient;
import com.shunmai.zryp.network.service.HttpService;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.FragmentHomePageBinding;
import com.shunmai.zryp.databinding.HeaderFirstpageBinding;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRefresh();
        initHeader();
        getData();
    }


    protected void getData() {
        Observable<HomePageBean> observable = RetrofitClient.getInstance().getService(HttpService.class).HomePageInfo();
        sendRequest(observable, homePageBean -> {
                    refreshLayout.getLayout().postDelayed(() -> refreshLayout.finishRefresh(), 1000);
                    topImages.clear();
                    centerImages.clear();
                    if (homePageBean.getData() == null) {
                        showError();
                        return;
                    }
                    if (homePageBean.getData().getRecommendGoods() != null && homePageBean.getData().getRecommendGoods().size() != 0) {
                        recommendRecAdapter.setData(homePageBean.getData().getRecommendGoods());
                    } else {
                        firstpageBinding.rlRecommend.setVisibility(View.GONE);
                        firstpageBinding.devRecommend.setVisibility(View.GONE);
                    }

                    if (homePageBean.getData().getTheNewGoods() != null && homePageBean.getData().getTheNewGoods().size() != 0) {
                        newGoodsRecAdapter.setData(homePageBean.getData().getTheNewGoods());
                    } else {
                        firstpageBinding.rlNewGoods.setVisibility(View.GONE);
                    }
                    if (homePageBean.getData().getGroupBuying() != null && homePageBean.getData().getGroupBuying().getPromotionGoodsList().size() != 0) {
                        groupBuyAdapter.setData(homePageBean.getData().getGroupBuying().getPromotionGoodsList(), homePageBean.getData().getGroupBuying().getPrGroupnum());
                    } else {
                        firstpageBinding.rlGroupBuy.setVisibility(View.GONE);
                        firstpageBinding.devGroupBuy.setVisibility(View.GONE);
                    }
                    if (homePageBean.getData().getFlashSale() != null && homePageBean.getData().getFlashSale().getPromotionGoodsList() != null) {
                        flashSaleRecAdapter.setData(homePageBean.getData().getFlashSale().getPromotionGoodsList());
                        firstpageBinding.ctXsg.mSeconds = (homePageBean.getData().getFlashSale().getPrEnd() - System.currentTimeMillis()) / 1000;
                    } else {
                        firstpageBinding.rlFlashSale.setVisibility(View.GONE);
                        firstpageBinding.devFlashSale.setVisibility(View.GONE);
                    }

                    guessGoodsAdapter.add(homePageBean.getData().getGuessGoods());
                    topImages.clear();
                    if (homePageBean.getData().getTopPostion() != null) {
                        for (int i = 0; i < homePageBean.getData().getTopPostion().getImgsList().size(); i++) {
                            topImages.add(homePageBean.getData().getTopPostion().getImgsList().get(i).getImgSrc());
                        }
                    }
                    centerImages.clear();
                    if (homePageBean.getData().getCenterPostion() != null) {
                        for (HomePageBean.DataBean.CenterPostionBean.ImgsListBean bean : homePageBean.getData().getCenterPostion().getImgsList()) {
                            centerImages.add(bean.getImgSrc());
                            firstpageBinding.banner1.setVisibility(View.VISIBLE);
                        }
                    } else {
                        firstpageBinding.banner1.setVisibility(View.GONE);
                    }
                    firstpageBinding.banner.setImages(topImages).setImageLoader(new GlideImageLoader()).start();
                    firstpageBinding.banner1.setBannerStyle(BannerConfig.NOT_INDICATOR);
                    firstpageBinding.banner1.setImages(centerImages).setImageLoader(new CIrImageLoader()).start();
                    firstpageBinding.banner1.MyOption();
                    if (homePageBean.getData().getSpecial() != null) {
                        firstpageBinding.ivBanner.setVisibility(View.VISIBLE);
                        GlideCacheUtil.LoadImage(getActivity(), firstpageBinding.ivBanner, homePageBean.getData().getSpecial().getImgUrl());
                    } else {
                        firstpageBinding.ivBanner.setVisibility(View.GONE);
                    }
                    showContentView();
                },
                throwable -> {
                    showError();
                    throwable.printStackTrace();
                    Log.i("NetWork", throwable.getStackTrace().toString());
                });
    }

    @Override
    protected void onRefresh() {
        getData();
    }

    private void initHeader() {
        firstpageBinding = DataBindingUtil.inflate(getActivity().getLayoutInflater(), R.layout.header_firstpage, null, false);
        recommendRecAdapter = new RecommendRecAdapter(getActivity(), new ArrayList<>(), R.layout.item_recommend);
        newGoodsRecAdapter = new NewGoodsRecAdapter(getActivity(), new ArrayList<>(), R.layout.item_new_goods);
        groupBuyAdapter = new GroupBuyAdapter(getActivity(), new ArrayList<>());
        flashSaleRecAdapter = new FlashSaleRecAdapter(getActivity(), new ArrayList<>());
        initRec(firstpageBinding.rvAct, true, 30, 1, recommendRecAdapter);
        initRec(firstpageBinding.rvNewGoods, true, 3, 1, newGoodsRecAdapter);
        initRec(firstpageBinding.rvGroupBuy, false, 130, 1, groupBuyAdapter);
        initRec(firstpageBinding.rvFlashSale, true, 25, 1, flashSaleRecAdapter);
        firstpageBinding.ctXsg.init("%s", (3600));
        firstpageBinding.ctXsg.start(0);
        intiBottomRec(bindingView.recHome);
        firstpageBinding.setHandler(new FirstPageHandler());
    }

    private void intiBottomRec(RecyclerView recHome) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recHome.setLayoutManager(gridLayoutManager);
        guessGoodsAdapter = new GuessGoodsAdapter(getActivity(), new ArrayList<>());
        WrapAdapter<GuessGoodsAdapter> wrapAdapter = new WrapAdapter<>(guessGoodsAdapter);
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
//        if (data == null) {
//            data = new LinkedList<>();
//        }
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
//        HomeFavouriteRecAdapter adapter = new HomeFavouriteRecAdapter(getActivity(), id, data);
        rec.setAdapter(adapter);
    }

    private void initRefresh() {
        refreshLayout = bindingView.refreshLayout;
        refreshLayout.setEnableAutoLoadMore(true);//开启自动加载功能（非必须）
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setOnRefreshListener(refreshLayout -> getData());
        refreshLayout.setOnLoadMoreListener(refreshLayout -> refreshLayout.getLayout().postDelayed(() -> {
            refreshLayout.finishLoadMore();
            Toast.makeText(getActivity(), "底部添加", Toast.LENGTH_SHORT).show();

        }, 2000));
    }

    @Override
    public int setContent() {
        return R.layout.fragment_home_page;
    }


}
