package com.shunmai.zryp.bean.userinfo;

import com.google.gson.annotations.SerializedName;
import com.shunmai.zryp.bean.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/10/12.
 */

public class FootprintBean extends TResponse<List<FootprintBean.DataBean>> {

    public static class DataBean {
        /**
         * time : 2018-10-11
         * list : [{"goodsName":"欧莱雅男士舒润强肤水凝露110ml补水保湿舒缓肌肤男士爽肤水","sysIdString":10090961176431046,"price":132,"marketPrice":120,"defalutPhotourl":"http://pic.gzcfe.net/brand/2018/0420/5486401156024100313.jpg"}]
         */

        private String time;
        private List<ListBean> list;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * goodsName : 欧莱雅男士舒润强肤水凝露110ml补水保湿舒缓肌肤男士爽肤水
             * sysIdString : 10090961176431046
             * price : 132.0
             * marketPrice : 120.0
             * defalutPhotourl : http://pic.gzcfe.net/brand/2018/0420/5486401156024100313.jpg
             */

            private String goodsName;
            private long sysIdString;
            private double price;
            private double marketPrice;
            private String defalutPhotourl;

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public long getSysIdString() {
                return sysIdString;
            }

            public void setSysIdString(long sysIdString) {
                this.sysIdString = sysIdString;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(double marketPrice) {
                this.marketPrice = marketPrice;
            }

            public String getDefalutPhotourl() {
                return defalutPhotourl;
            }

            public void setDefalutPhotourl(String defalutPhotourl) {
                this.defalutPhotourl = defalutPhotourl;
            }
        }
    }
}
