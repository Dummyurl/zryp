package com.shunmai.zryp.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.shunmai.zryp.zrypapp.BR;
import java.io.Serializable;


public class TResponse<Data> extends BaseObservable implements Serializable {
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMessage(String message) {
        this.msg = message;
    }

    private int code;

    private String msg;

    @Bindable
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }

}