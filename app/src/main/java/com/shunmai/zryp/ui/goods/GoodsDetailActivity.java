package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.shunmai.zryp.AppConfig;
import com.shunmai.zryp.R;
import com.shunmai.zryp.adapter.home.GuessGoodsAdapter;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.bean.userinfo.TabEntity;
import com.shunmai.zryp.databinding.ActivityGoodsDetailBinding;
import com.shunmai.zryp.eventhandler.goods.GoodsDetailHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.repository.GoodsDetailRepository;
import com.shunmai.zryp.utils.GoodsDetailActivityManager;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.utils.Utils;
import com.shunmai.zryp.viewmodel.GoodsDetailViewModel;
import com.xiasuhuei321.loadingdialog.view.LoadingDialog;

import java.util.ArrayList;
import java.util.List;


public class GoodsDetailActivity extends SwipeBackActivity<ActivityGoodsDetailBinding> {

    private GoodsDetailViewModel viewModel;
    private GuessGoodsAdapter guessGoodsAdapter;
    GoodsDetailHandler handler;
    private long goodsId;
    private PayBroadcastReceiver mReceiver;
    private LoadingDialog dialog;
    private List<String> titles = new ArrayList<>();
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        initWindow(this);
        viewModel = ViewModelProviders.of(this).get(GoodsDetailViewModel.class);
        viewModel.init(GoodsDetailRepository.getInstance());
        goodsId = getIntent().getLongExtra("goodsId", -1);
//        goodsId= 30904;
        setDefaultData();
        viewModel.SaveMyFootprint(goodsId);
        initRec(bindingView.rvGuessLike);
        handler = new GoodsDetailHandler(goodsId);
        bindingView.setHandler(handler);
        GoodsDetailActivityManager.getInstance().putActivity(this);
        initListener();
        getData();
    }

    private void getData() {
        viewModel.getGoodsDetail(goodsId, new onResponseListener<GoodsDetailBean>() {
            @Override
            public void onSuccess(GoodsDetailBean goodsDetailBean) {
                bindingView.setDetailBean(goodsDetailBean);
                if (goodsDetailBean != null && goodsDetailBean.getData() != null) {
                    if (goodsDetailBean.getData().getList() != null) {
                        guessGoodsAdapter.setData(goodsDetailBean.getData().getList());
                    }
                    handler.setDataBean(goodsDetailBean.getData());
                    if (goodsDetailBean.getData().getSku().getCollectId() != null && !goodsDetailBean.getData().getSku().getCollectId().equals("0")) {
                        bindingView.cbCollect.setChecked(true);
                    }
                    bindingView.cbCollect.setOnCheckedChangeListener((buttonView, isChecked) -> {
                        if (Utils.checkLogin(GoodsDetailActivity.this)) {
                            dialog = new LoadingDialog(GoodsDetailActivity.this);
                            dialog.setInterceptBack(true)
                                    .setLoadingText("加载中...")
                                    .setLoadStyle(LoadingDialog.STYLE_LINE).show();
                            if (isChecked) {
                                viewModel.CollectGoodsItem(bindingView.getDetailBean().getData().getGoods().getGoodsId(), bindingView.getDetailBean().getData().getSku().getSeekGoodsSkuVOS().get(0).getSkuId(), ShareUtils.getUserInfo().getUserId(), new onResponseListener<String>() {
                                    @Override
                                    public void onSuccess(String str) {
                                        dialog.close();
                                        bindingView.getDetailBean().getData().getSku().setCollectId(str);
                                    }

                                    @Override
                                    public void onFailed(Throwable throwable) {
                                        bindingView.cbCollect.setChecked(false);
                                        ToastUtils.showToast(throwable.getMessage());
                                        dialog.close();
                                    }
                                });
                            } else {
                                viewModel.DeleteCollectItem(Integer.parseInt(bindingView.getDetailBean().getData().getSku().getCollectId()), new onResponseListener<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        dialog.close();
                                        bindingView.getDetailBean().getData().getSku().setCollectId("0");
                                    }
                                    @Override
                                    public void onFailed(Throwable throwable) {
                                        dialog.close();
                                        bindingView.cbCollect.setChecked(true);
                                        ToastUtils.showToast(throwable.getMessage());
                                    }
                                });
                            }
                        }else{
                            bindingView.cbCollect.setChecked(false);
                        }
                    });
                    showContentView();
                } else {
                    ToastUtils.showToast("商品信息错误，请稍后再试！");
                }
            }

            @Override
            public void onFailed(Throwable throwable) {
                showError();
                Toast.makeText(GoodsDetailActivity.this, "商品信息错误！", Toast.LENGTH_SHORT).show();
                throwable.printStackTrace();
            }
        });
    }

    private void initListener() {
        mReceiver = new PayBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(AppConfig.PaySuccess);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, intentFilter);

    }

    private void setDefaultData() {
//        GlideCacheUtil.LoadImage(this, bindingView.ivMain, getIntent().getStringExtra("goodsImgUrl"));
//        bindingView.tvPrice.setText(getIntent().getDoubleExtra("goodsPrice", 0) + "");
//        bindingView.tvMarketPrice.setText(getIntent().getDoubleExtra("goodsMarketPrice", 0) + "");
        bindingView.tvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
//        bindingView.tvGoodsTitle.setText(getIntent().getStringExtra("goodsTitle"));

    }

    private void initRec(RecyclerView recHome) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recHome.setLayoutManager(gridLayoutManager);
        guessGoodsAdapter = new GuessGoodsAdapter(this, new ArrayList<>());
        recHome.setAdapter(guessGoodsAdapter);
        recHome.setNestedScrollingEnabled(false);
        titles.add("商品");
        titles.add("详情");
        titles.add("推荐");
        for (String s : titles) {
            mTabEntities.add(new TabEntity(s));
        }
        bindingView.tlGoods.setTabData(mTabEntities);
        bindingView.tlGoods.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 1) {
                    bindingView.svGoodsDetail.smoothScrollTo(0, bindingView.tvGoodsIntroduce.getTop());
                } else if (position == 2) {
                    bindingView.svGoodsDetail.smoothScrollTo(0, bindingView.tvGoodsGuessLike.getTop());
                } else {
                    bindingView.svGoodsDetail.smoothScrollTo(0, 0);
                }

            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        bindingView.svGoodsDetail.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            bindingView.svGoodsDetail.getChildAt(bindingView.svGoodsDetail.getChildCount() - 2);
            Rect scrollBounds = new Rect();
            bindingView.svGoodsDetail.getHitRect(scrollBounds);
            if (scrollY > bindingView.tvGoodsGuessLike.getTop() - 100 || bindingView.tvGoodsGuessLike.getLocalVisibleRect(scrollBounds)) {
                bindingView.tlGoods.setCurrentTab(2);
            } else if (scrollY > bindingView.tvGoodsIntroduce.getTop() - 100) {
                bindingView.tlGoods.setCurrentTab(1);
            } else {
                bindingView.tlGoods.setCurrentTab(0);
            }
        });

    }


    public static void navigate(Context activity, long goodsId) {
        Intent intent = new Intent(activity, GoodsDetailActivity.class);
        intent.putExtra("goodsId", goodsId);
//        intent.putExtra("goodsImgUrl", goodsImgUrl);
//        intent.putExtra("goodsPrice", goodsPrice);
//        intent.putExtra("goodsMarketPrice", goodsMarketPrice);
//        intent.putExtra("goodsTitle", goodsTitle);
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        activity.startActivity(intent);
//        ActivityCompat.startActivity(intent);
    }

    @Override
    protected void onRefresh() {
        getData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GoodsDetailActivityManager.getInstance().removeActivity(this);
        handler.onDestory();
    }

    private class PayBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            finish();
        }
    }
}
