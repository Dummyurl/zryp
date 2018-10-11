package com.shunmai.zryp.bean.goods;

import com.shunmai.zryp.bean.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/4.
 */

public class GoodsListBean extends TResponse<List<GoodsBean>> {


    private int pageNum;
    private int pageSize;
    private int pageCount;
    private int totalCount;

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
}
