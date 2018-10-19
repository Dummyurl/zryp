package com.shunmai.zryp.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shunmai.zryp.BR;

/**
 * Created by yushengyang.
 * Date: 2018/9/26.
 */

public class TitleBean extends BaseObservable {
    private String title;
    @Bindable
    public String getTitle() {
        return title;
    }

    public TitleBean setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
        return this;
    }
}
