package com.shunmai.zryp.ui.home.child;


import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.userinfo.VipGuideIndexBean;
import com.shunmai.zryp.databinding.FragmentVipGuideBinding;
import com.shunmai.zryp.eventhandler.userinfo.VipGuideIndexHandler;
import com.shunmai.zryp.listener.onResponseListener;
import com.shunmai.zryp.utils.ShareUtils;
import com.shunmai.zryp.utils.ToastUtils;
import com.shunmai.zryp.viewmodel.VipGuideIndexViewModel;
import com.ysy.commonlib.base.BaseFragment;
import com.ysy.commonlib.base.TResponse;
import com.ysy.commonlib.utils.WeightUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class VipGuideFragment extends BaseFragment<FragmentVipGuideBinding> implements VipGuideIndexHandler.VipGuideListener {
    VipGuideIndexViewModel viewModel;
   private int progress=0;
    private boolean canChange=true;
    private boolean canLevelUp=false;
    private AlertDialog alertDialog;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel= ViewModelProviders.of(this).get(VipGuideIndexViewModel.class);
        viewModel.getVipIndex().observe(this, vipGuideIndexBean -> {
                bindingView.setBean(vipGuideIndexBean);
                showContentView();
                if (canLevelUp){
                    canLevelUp=false;
                    //判断是否进度已满
                    if (vipGuideIndexBean.getUpgradeProgress()==1){
                        viewModel.Upgrade(new onResponseListener<TResponse<String>>() {
                            @Override
                            public void onSuccess(TResponse<String> stringTResponse) {
                                //升级成功显示弹窗
                                loadData();
                                alertDialog.dismiss();
                                showSuccessDialog();
                            }

                            @Override
                            public void onFailed(Throwable throwable) {
                                //升级失败
                                ToastUtils.showToast(throwable.getMessage());
                                alertDialog.dismiss();
                            }
                        });
                    }else{
                        ToastUtils.showToast("用户信息错误，如有疑问联系客服！");
                    }
                }
                if (canChange){
//                        bindingView.getRoot().post(runnable);
                    canChange=false;
                }

        });
        bindingView.setUserBean(ShareUtils.getUserInfo());
        bindingView.setHandler(new VipGuideIndexHandler(this));
        loadData();
    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            VipGuideIndexBean value = viewModel.getVipIndex().getValue();
            if (progress==101){
                progress=0;
                if (value.getCurrentUserType()==0){
                    value.setCurrentUserType(3);
                }else if (value.getCurrentUserType()==3){
                    value.setCurrentUserType(2);
                }else if (value.getCurrentUserType()==2){
                    value.setCurrentUserType(1);
                }else if (value.getCurrentUserType()==1){
                    value.setCurrentUserType(0);
                }
            }
            value.setProgress(progress++);
            viewModel.getVipIndex().setValue(value);
            bindingView.getRoot().postDelayed(runnable,100);
        }
    };

    @Override
    protected void loadData() {
        viewModel.GetShoppingGuideIndex(throwable -> {
            showError();
            throwable.printStackTrace();
        });
    }


    @Override
    protected void onRefresh() {
        loadData();
    }

    @Override
    public int setContent() {
        return R.layout.fragment_vip_guide;
    }

    /**
     * 升级到VIP导购
     * @param alertDialog
     */
    @Override
    public void levelToVipGuide(AlertDialog alertDialog) {
        //从新请求用户数据，校验是否进度已满
        loadData();
        canLevelUp=true;
        this.alertDialog=alertDialog;
    }
    //升级成功，显示弹窗
    public void showSuccessDialog(){
        AlertDialog successDialog = WeightUtils.usefulDialog(getActivity(), R.layout.layout_level_up_vip_guide_success, R.style.alert_dialog, R.id.iv_close, R.id.iv_cancel);
        Window win = successDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        win.setAttributes(lp);
    }
}
