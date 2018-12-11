package com.shunmai.zryp.eventhandler.userinfo;

import android.app.AlertDialog;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shunmai.zryp.R;
import com.shunmai.zryp.bean.userinfo.VipGuideIndexBean;
import com.shunmai.zryp.databinding.LayoutLevelUpDirectorConditionDialogBinding;
import com.shunmai.zryp.databinding.LayoutLevelUpManagerConditionDialogBinding;
import com.shunmai.zryp.ui.goods.VipGoodsActivity;
import com.shunmai.zryp.ui.userinfo.account.UserApproveActivity;
import com.shunmai.zryp.ui.wallet.WithDrawInfoActivity;
import com.shunmai.zryp.ui.wallet.WithdrawActivity;
import com.shunmai.zryp.ui.wallet.WithdrawListActivity;
import com.shunmai.zryp.utils.Dev;
import com.shunmai.zryp.utils.StateCheckUtils;
import com.ysy.commonlib.base.BaseEventHandler;
import com.ysy.commonlib.utils.WeightUtils;

/**
 * Created by yushengyang.
 * Date: 2018/12/4.
 */

public class VipGuideIndexHandler extends BaseEventHandler {
    private VipGuideListener listener;

    public VipGuideIndexHandler(VipGuideListener listener) {
        this.listener = listener;
    }

    @BindingAdapter({"android:vipType", "android:vipIsFull", "android:currentType", "android:isAlways"})
    public static void setVipVisibility(LinearLayout linearLayout, int vipType, boolean isFull, VipGuideIndexBean bean, boolean isAlways) {
        if (bean == null) {
            return;
        }
        if (vipType != bean.getCurrentUserType()) {
            linearLayout.setVisibility(View.GONE);
            return;
        }
        if (isAlways) {
            linearLayout.setVisibility(View.VISIBLE);
            return;
        }
        if (bean.getProgress() == 100 && isFull) {
            linearLayout.setVisibility(View.VISIBLE);
            return;
        }
        if (bean.getProgress() != 100 && !isFull) {
            linearLayout.setVisibility(View.VISIBLE);
            return;
        }
        linearLayout.setVisibility(View.GONE);
    }

    public void toVipGoods(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), VipGoodsActivity.class));
    }

    public void showVipGuideDialog(View view, int type) {
        AlertDialog dialog = WeightUtils.usefulDialog(view.getContext(), R.layout.layout_level_up_vip_guide_dialog, R.style.alert_dialog, R.id.iv_close, R.id.iv_cancel);
        dialog.findViewById(R.id.iv_level_up).setOnClickListener(v -> listener.levelToVipGuide(dialog));
        if (type == 1) {
            ((TextView) dialog.findViewById(R.id.tv_1)).setText("是否升级为");
            ((TextView) dialog.findViewById(R.id.tv_2)).setText("总监");
            ((TextView) dialog.findViewById(R.id.tv_3)).setText("？");
        } else if (type == 2) {
            ((TextView) dialog.findViewById(R.id.tv_1)).setText("是否升级为");
            ((TextView) dialog.findViewById(R.id.tv_2)).setText("总经理");
            ((TextView) dialog.findViewById(R.id.tv_3)).setText("？");
        }
    }

    public void directorPowerDialog(View view, int type) {
        AlertDialog alertDialog = WeightUtils.usefulDialog(view.getContext(), R.layout.layout_director_power, R.style.alert_dialog, R.id.iv_close, R.id.iv_cancel);
        if (type == 1) {
            ((TextView) alertDialog.findViewById(R.id.tv_content)).setText("1.直属总监10人以上；\n2.团队总监人数大于60人；\n3.团队VIP导购赚销售积分超过100分达1000人以上；");
            ((TextView) alertDialog.findViewById(R.id.tv_identity)).setText("总经理特权");
            ((TextView) alertDialog.findViewById(R.id.tv_power)).setText("1.自购商品返积分；\n2.推荐注册奖励积分；\n3.团队购买返积分；\n4.招募VIP享培训奖励；");
        }
    }

    public void directorProgressDialog(View view, VipGuideIndexBean.VipUpgradeProgressBean bean) {
        LayoutLevelUpDirectorConditionDialogBinding binding = null;
        binding = WeightUtils.usefulDialogDatabinding(view.getContext(), R.layout.layout_level_up_director_condition_dialog, R.style.alert_dialog, R.id.iv_close, R.id.iv_cancel, binding);
        binding.setBean(bean);
    }

    public void managerProgressDialog(View view, VipGuideIndexBean.DirectorUpgradeProgressBean bean) {
        LayoutLevelUpManagerConditionDialogBinding binding = null;
        binding = WeightUtils.usefulDialogDatabinding(view.getContext(), R.layout.layout_level_up_manager_condition_dialog, R.style.alert_dialog, R.id.iv_close, R.id.iv_cancel, binding);
        int condition = bean.getHeirIntegralConfig() * bean.getTotalVipNumConfig();
        ViewGroup.LayoutParams layoutParams = binding.rlDialog.getLayoutParams();
        if (condition == 0 && bean.getTakeoutIntegralConfig() == 0) {
            layoutParams.height = Dev.dp2px(view.getContext(), 320);
        } else if (condition == 0 || bean.getTakeoutIntegralConfig() == 0) {
            layoutParams.height = Dev.dp2px(view.getContext(), 365);
        }
        binding.rlDialog.setLayoutParams(layoutParams);
        binding.setBean(bean);
    }

    public void toWallet(View view, boolean withInfo) {
        Intent intent;
        if (StateCheckUtils.checkVerification()) {
            if (withInfo) {
                intent = new Intent(view.getContext(), WithdrawActivity.class);
            } else {
                intent = new Intent(view.getContext(), WithDrawInfoActivity.class);
            }
        }else{
            intent = new Intent(view.getContext(), UserApproveActivity.class);
        }
        view.getContext().startActivity(intent);
    }
    public void toListWithDraw(View view, boolean withInfo) {
        Intent intent;
        if (StateCheckUtils.checkVerification()) {
            if (withInfo) {
                intent = new Intent(view.getContext(), WithdrawListActivity.class);
            } else {
                intent = new Intent(view.getContext(), WithDrawInfoActivity.class);
            }
        }else{
            intent = new Intent(view.getContext(), UserApproveActivity.class);
        }
        view.getContext().startActivity(intent);
    }

    public interface VipGuideListener {
        void levelToVipGuide(AlertDialog alertDialog);
    }
}
