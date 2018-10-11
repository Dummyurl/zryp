package com.shunmai.zryp.ui.goods;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.shunmai.zryp.repository.GoodsDeatilRepsotiry;
import com.shunmai.zryp.adapter.home.GuessGoodsAdapter;
import com.shunmai.zryp.base.SwipeBackActivity;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.GoodsDetailViewModel;
import com.shunmai.zryp.zrypapp.R;
import com.shunmai.zryp.zrypapp.databinding.ActivityGoodsDetailBinding;

import java.util.ArrayList;


public class GoodsDetailActivity extends SwipeBackActivity<ActivityGoodsDetailBinding> implements View.OnClickListener {
    public static final String EXTRA_IMAGE = "extraImage";
    private GoodsDetailViewModel viewModel;
    private GuessGoodsAdapter guessGoodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initActivityTransitions();
        MutableLiveData<GoodsDetailBean> liveData=new MutableLiveData<>();
        initWindow(this);
        liveData.observe(this, goodsDetailBean -> {bindingView.setDetailBean(goodsDetailBean);
            if (goodsDetailBean!=null&&goodsDetailBean.getData()!=null){
            guessGoodsAdapter.setData(goodsDetailBean.getData().getList());}
            else{
                ToastUtils.showToast("商品信息错误，请稍后再试！");
//                onBackPressed();
                finish();
            }
        });
         viewModel = ViewModelProviders.of(this).get(GoodsDetailViewModel.class);
        viewModel.init(GoodsDeatilRepsotiry.getInstance());
        Long goodsId = getIntent().getLongExtra("goodsId",-1);
        viewModel.getGoodsDetail(liveData, goodsId, throwable -> Toast.makeText(GoodsDetailActivity.this,"网络错误",Toast.LENGTH_SHORT).show());
//        List<String> seekGoodsImgsVOS=new ArrayList<>();
//        for (int i = 0; i < seekGoodsImgsVOS.size(); i++) {
//            GlideCacheUtil.LoadImage(this, findViewById(imageviews[i]), seekGoodsImgsVOS.get(i));
//        }
        RecyclerView rvGuessLike = bindingView.rvGuessLike;
        initRec(rvGuessLike);
        bindingView.tvSingleBuy.setOnClickListener(this);
        bindingView.tvService.setOnClickListener(this);
    }
    private void initRec(RecyclerView recHome) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recHome.setLayoutManager(gridLayoutManager);
        guessGoodsAdapter = new GuessGoodsAdapter(this, new ArrayList<>());
        recHome.setAdapter(guessGoodsAdapter);
        recHome.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_single_buy: {
                startActivity(new Intent(this, GoodsOrderActivity.class));
                break;
            }
            case R.id.tv_service:{

            }
        }
    }
    public static void navigate(AppCompatActivity activity, View transitionImage,long goodsId) {
        Intent intent = new Intent(activity, GoodsDetailActivity.class);
        intent.putExtra("goodsId", goodsId);
        //intent.putExtra(EXTRA_TITLE, viewModel.getText());
        //Log.e("DetailActivity:",viewModel.getImage()+"");
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
