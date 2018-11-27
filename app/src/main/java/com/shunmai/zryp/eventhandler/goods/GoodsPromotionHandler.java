package com.shunmai.zryp.eventhandler.goods;

import android.databinding.BindingAdapter;

import com.ysy.commonlib.base.BaseEventHandler;
import com.shunmai.zryp.view.SnapUpCountDownTimerView;

/**
 * Created by yushengyang.
 * Date: 2018/11/13.
 */

public class GoodsPromotionHandler extends BaseEventHandler {
    @BindingAdapter({"android:setTimer"})
    public static void setTimer(SnapUpCountDownTimerView view, long timer) {
        if (timer<0){
            timer=-timer;
        }
        timer/=1000;
        int hour= (int) (timer/3600);
        int minute= (int) ((timer%3600)%60);
        int second= (int) (timer%60);
        view.setTime(hour,minute,second);
        view.start();
    }
}
