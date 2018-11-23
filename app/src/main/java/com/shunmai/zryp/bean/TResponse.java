package com.shunmai.zryp.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shunmai.zryp.BR;

import java.io.Serializable;
import java.util.List;


public class TResponse<Data> extends BaseObservable implements Serializable {

    private List<MainadBean> mainad;

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

    private int pageNum;
    private int pageSize;
    private int pageCount;
    private int totalCount;
    @Bindable
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<MainadBean> getMainad() {
        return mainad;
    }

    public void setMainad(List<MainadBean> mainad) {
        this.mainad = mainad;
    }

    public static class MainadBean {
        /**
         * pic : http://pic0.gzcfe.net/article/2018/1012/4774429120548128628.jpg
         * url : https://mp.weixin.qq.com/s/kW4b5LhOp6xrxmNqNrJLsw
         * link_type : 2
         */

        private String pic;
        private String url;
        private String link_type;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getLink_type() {
            return link_type;
        }

        public void setLink_type(String link_type) {
            this.link_type = link_type;
        }
    }
}
