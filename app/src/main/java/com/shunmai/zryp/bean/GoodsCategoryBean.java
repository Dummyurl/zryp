package com.shunmai.zryp.bean;

import java.util.List;

/**
 * Created by zyf on 2017/5/8.
 */

public class GoodsCategoryBean {

    private String title;
    private List<GoodsBean> goods;
    private String tag;
//    private List<good> goodsImgs;
    public GoodsCategoryBean() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
        return "GoodsCategoryBean{" +
                "title='" + title + '\'' +
                ", text='" + goods + '\'' +
                '}';
    }
}
