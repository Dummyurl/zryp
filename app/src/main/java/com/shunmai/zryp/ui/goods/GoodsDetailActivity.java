package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.shunmai.zryp.eventhandler.goods.GoodsDetailHandler;
import com.shunmai.zryp.repository.GoodsDetailRepository;
import com.shunmai.zryp.adapter.home.GuessGoodsAdapter;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.utils.GlideCacheUtil;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.GoodsDetailViewModel;
import com.shunmai.zryp.R;
import com.shunmai.zryp.databinding.ActivityGoodsDetailBinding;

import java.util.ArrayList;


public class GoodsDetailActivity extends SwipeBackActivity<ActivityGoodsDetailBinding> {
    public static final String EXTRA_IMAGE = "extraImage";
    private GoodsDetailViewModel viewModel;
    private GuessGoodsAdapter guessGoodsAdapter;
    private RecyclerView rvGuessLike;
    MutableLiveData<GoodsDetailBean> liveData;
    private long goodsId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);

        liveData = new MutableLiveData<>();
        initWindow(this);
        liveData.observe(this, goodsDetailBean -> {
            bindingView.setDetailBean(goodsDetailBean);
            if (goodsDetailBean != null && goodsDetailBean.getData() != null) {
                guessGoodsAdapter.setData(goodsDetailBean.getData().getList());
                showContentView();
            } else {
                ToastUtils.showToast("商品信息错误，请稍后再试！");
            }
        });
        viewModel = ViewModelProviders.of(this).get(GoodsDetailViewModel.class);
        viewModel.init(GoodsDetailRepository.getInstance());
        goodsId = getIntent().getLongExtra("goodsId", -1);
        viewModel.getGoodsDetail(liveData, goodsId, throwable -> {
            showError();
            Toast.makeText(GoodsDetailActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
            throwable.printStackTrace();
        });
        setDefaultData();
        viewModel.SaveMyFootprint(goodsId);
        rvGuessLike = bindingView.rvGuessLike;
        initRec(rvGuessLike);
        bindingView.setHandler(new GoodsDetailHandler());
    }

    private void setDefaultData() {
        GlideCacheUtil.LoadImage(this,bindingView.ivMain,getIntent().getStringExtra("goodsImgUrl"));
        bindingView.tvPrice.setText(getIntent().getDoubleExtra("goodsPrice",0)+"");
        bindingView.tvMarketPrice.setText(getIntent().getDoubleExtra("goodsMarketPrice",0)+"");
        bindingView.tvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        bindingView.tvGoodsTitle.setText(getIntent().getStringExtra("goodsTitle"));
    }

    private void initRec(RecyclerView recHome) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recHome.setLayoutManager(gridLayoutManager);
        guessGoodsAdapter = new GuessGoodsAdapter(this, new ArrayList<>());
        recHome.setAdapter(guessGoodsAdapter);
        recHome.setNestedScrollingEnabled(false);
    }


    public static void navigate(AppCompatActivity activity, View transitionImage, long goodsId,String goodsImgUrl,double goodsPrice,double goodsMarketPrice,String goodsTitle) {
        Intent intent = new Intent(activity, GoodsDetailActivity.class);
        intent.putExtra("goodsId", goodsId);
        intent.putExtra("goodsImgUrl",goodsImgUrl);
        intent.putExtra("goodsPrice",goodsPrice);
        intent.putExtra("goodsMarketPrice",goodsMarketPrice);
        intent.putExtra("goodsTitle",goodsTitle);
//        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        activity.startActivity(intent);
//        ActivityCompat.startActivity(intent);
    }

    @Override
    protected void onRefresh() {
        viewModel.getGoodsDetail(liveData, goodsId, throwable -> {
            showError();
            Toast.makeText(GoodsDetailActivity.this, "网络错误", Toast.LENGTH_SHORT).show();
        });
    }
}
