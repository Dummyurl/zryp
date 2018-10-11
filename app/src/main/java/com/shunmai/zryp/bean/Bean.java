package com.shunmai.zryp.bean;

import java.util.List;

/**
 * Created by zyf on 2017/5/8.
 */

public class Bean {

    private String title;
    private List<GoodsBean> goods;
//    private List<good> goodsImgs;
    public Bean() {
    }

    public Bean(String title, List<GoodsBean> goods) {
        this.title = title;
        this.goods = goods;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "title='" + title + '\'' +
                ", text='" + goods + '\'' +
                '}';
    }
}
