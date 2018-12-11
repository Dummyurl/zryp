package com.shunmai.zryp.ui.userinfo.account;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.userinfo.ReferrerNameBean;
import com.shunmai.zryp.databinding.ActivityChangeRecommendBinding;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.StateCheckUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.ChangeRecommendViewModel;
import com.ysy.commonlib.base.BaseEventHandler;
import com.ysy.commonlib.base.MVVMActivity;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;

public class ChangeRecommendActivity extends MVVMActivity<ActivityChangeRecommendBinding, ChangeRecommendViewModel> implements View.OnClickListener {
    private final int queryMyself = 1;
    private final int queryRecommend = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_recommend);

        bindingView.setHandler(new BaseEventHandler());
        mViewModel.ReferrerName(ShareUtils.getUserInfo().getUserId(), queryMyself, new onResponseListener<ReferrerNameBean>() {
            @Override
            public void onSuccess(ReferrerNameBean bean) {
                bindingView.setReBean(bean);
                showContentView();
            }

            @Override
            public void onFailed(Throwable throwable) {
                showError();
            }
        });
        bindingView.setQueryBean(new ReferrerNameBean());
        bindingView.tvQuery.setOnClickListener(this);
        bindingView.etQueryNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean canLight = false;
                if (s.toString().trim().length() > 0) {
                    canLight = true;
                }
                StateCheckUtils.checkClickable(canLight, bindingView.tvQuery, getResources().getDrawable(R.drawable.shape_rect_gold_stroke), ChangeRecommendActivity.this);
            }
        });
    }

    @Override
    protected void onRefresh() {
        mViewModel.ReferrerName(ShareUtils.getUserInfo().getUserId(), queryMyself, new onResponseListener<ReferrerNameBean>() {
            @Override
            public void onSuccess(ReferrerNameBean bean) {
                bindingView.setReBean(bean);
                showContentView();
            }

            @Override
            public void onFailed(Throwable throwable) {
                showError();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_query: {
                mViewModel.ReferrerName(Integer.parseInt(bindingView.etQueryNum.getText().toString()), queryRecommend, new onResponseListener<ReferrerNameBean>() {
                    @Override
                    public void onSuccess(ReferrerNameBean bean) {
                        bindingView.setQueryBean(bean);
                        StateCheckUtils.checkClickable(true, bindingView.btnChange, getResources().getDrawable(R.drawable.shape_rect_red), ChangeRecommendActivity.this);
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        bindingView.setQueryBean(null);
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });
                break;
            }
            case R.id.btn_change:{
                HashMap<String,Integer> map=new HashMap<>();
                 map.put("userId",ShareUtils.getUserInfo().getUserId());
                 map.put("inviterId",bindingView.getQueryBean().getId());
                mViewModel.UpdateReferrer(map, new onResponseListener<TResponse<String>>() {
                    @Override
                    public void onSuccess(TResponse<String> stringTResponse) {
                        ToastUtils.showToast("推荐人修改成功！");
                        onBackPressed();
                    }

                    @Override
                    public void onFailed(Throwable throwable) {
                        ToastUtils.showToast(throwable.getMessage());
                    }
                });
                break;
            }
        }
    }
}
