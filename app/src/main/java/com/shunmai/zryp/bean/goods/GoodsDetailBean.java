package com.shunmai.zryp.bean.goods;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.shunmai.zryp.BR;
import com.ysy.commonlib.base.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/29.
 */

public class GoodsDetailBean extends TResponse<GoodsDetailBean.DataBean> {

    public static class DataBean extends BaseObservable {
        /**
         * goods : {"goodsId":1,"channelId":0,"platgoodsId":"12345678ghgs","goodsName":"阿达迪斯","goodsNote":"就是牛","goodsType":1,"goodsPropery":1,"catlaogMobileId":11090961178431024,"catalogPcId":0,"brandId":0,"price":25.5,"marketPrice":24.2,"mscore":15,"weight":1.6,"keywords":"阿迪","sortOrder":0,"upcarriageTime":1537222283000,"lastupdateTime":1537222283000,"isFronthidden":false,"createtime":1.537222283E12,"dataStatus":0,"defalutPhotourl":"https://rabc2.iteye.com/4j/s=7bfnpwv67a,grrx3zbce&x8=14?we6icl=xsqxns_9con8_yeg","sysSpaceNo":"B0CD0050CF0BF01B","sysVersionNo":"3","sysIsEnable":"1","sysIdString":10750656398888960,"sysDataFrom":"ios","limit":0,"description":"商品描述1","freightMode":1,"goodsTitle":"商品标题1","appDetails":"app端商品详情1","miniDetails":"小程序商品详情1","addTime":null,"seekGoodsImgsVOS":[{"goodsPhotoId":8,"goodsPhotoName":"测试图片3","isDefaultPhoto":false,"goodsId":10750656398888960,"photoUrl":"http://b0cd0050cf0bf01b.oss-cn-beijing.aliyuncs.com/2018/9/f13cc5a3-e6a8-4a16-8339-f35c90a0b219.gif","sortOrder":0,"createTime":1.537500884E12,"updateTime":null,"photoType":1,"dataStatus":0,"sysCreateTime":1.537500884E12,"sysVersionNo":0,"sysIsEnable":1,"sysIdString":11771250796007424}]}
         * sku : {"sysIdString":0,"seekGoodsSkuVOS":[],"seekGoodsItemVOS":[]}
         * list : [{"price":25.5,"defalutPhotourl":null,"marketPrice":24.2,"sysIdString":14651775802019840,"goodsName":"阿达迪斯1","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":1},{"price":255.8,"defalutPhotourl":null,"marketPrice":236.8,"sysIdString":11552206172065792,"goodsName":"测试商品2","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":2},{"price":25.5,"defalutPhotourl":null,"marketPrice":24.2,"sysIdString":10750656398888960,"goodsName":"阿达迪斯","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":1},{"price":25.5,"defalutPhotourl":null,"marketPrice":24.2,"sysIdString":14651389229797376,"goodsName":"阿达迪斯1","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":1},{"price":35.8,"defalutPhotourl":null,"marketPrice":36.8,"sysIdString":11552102606311424,"goodsName":"测试商品1","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":2}]
         */

        private GoodsBean goods;
        @Bindable
        private SkuBean sku;
        private List<GoodsBean> list;

        public GoodsBean getGoods() {
            return goods;
        }

        public void setGoods(GoodsBean goods) {
            this.goods = goods;
        }

        public SkuBean getSku() {
            return sku;
        }

        public void setSku(SkuBean sku) {
            this.sku = sku;
            notifyPropertyChanged(BR.sku);
        }

        public List<GoodsBean> getList() {
            return list;
        }

        public void setList(List<GoodsBean> list) {
            this.list = list;
        }


        public static class SkuBean {
            private String collectId;
            private long sysIdString;
            private List<SeekGoodsSkuVOSBean> seekGoodsSkuVOS;
            private List<SeekGoodsItemVOSBean> seekGoodsItemVOS;

            public String getCollectId() {
                return collectId;
            }

            public void setCollectId(String collectId) {
                this.collectId = collectId;
            }

            public List<SeekGoodsSkuVOSBean> getSeekGoodsSkuVOS() {
                return seekGoodsSkuVOS;
            }

            public void setSeekGoodsSkuVOS(List<SeekGoodsSkuVOSBean> seekGoodsSkuVOS) {
                this.seekGoodsSkuVOS = seekGoodsSkuVOS;
            }

            public List<SeekGoodsItemVOSBean> getSeekGoodsItemVOS() {
                return seekGoodsItemVOS;
            }

            public void setSeekGoodsItemVOS(List<SeekGoodsItemVOSBean> seekGoodsItemVOS) {
                this.seekGoodsItemVOS = seekGoodsItemVOS;
            }

            public static class SeekGoodsSkuVOSBean {
                /**
                 * skuId : 43936
                 * goodsId : 30904
                 * barCode : 0
                 * mallPrice : 0
                 * defaultPhotoId : 0
                 * defaultPhotoPath : http://pic0.gzcfe.net/brand/2018/1015/5321501927926584729.jpg
                 * dataStatus : 0
                 * normText : [{"itemSystemId":"2784","normId":9353,"itemName":"大小","normName":"中","sysIdString":25962197897318400,"normSort":4},{"itemSystemId":"2785","normId":9342,"itemName":"颜色","normName":"红","sysIdString":25954880623611904,"normSort":1}]
                 * normIds : 25962197897318400-25954880623611904
                 * stock : 0
                 * sysIsEnable : 1
                 * sysIdString : 25962198224474112
                 * price : 0
                 */

                private int skuId;
                private int goodsId;
                private String barCode;
                private double mallPrice;
                private long defaultPhotoId;
                private String defaultPhotoPath;
                private int dataStatus;
                private String normText;
                private String normIds;
                private int stock;
                private int sysIsEnable;
                private String sysIdString;
                private double price;

                public int getSkuId() {
                    return skuId;
                }

                public void setSkuId(int skuId) {
                    this.skuId = skuId;
                }

                public int getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(int goodsId) {
                    this.goodsId = goodsId;
                }

                public String getBarCode() {
                    return barCode;
                }

                public void setBarCode(String barCode) {
                    this.barCode = barCode;
                }

                public double getMallPrice() {
                    return mallPrice;
                }

                public void setMallPrice(double mallPrice) {
                    this.mallPrice = mallPrice;
                }

                public long getDefaultPhotoId() {
                    return defaultPhotoId;
                }

                public void setDefaultPhotoId(long defaultPhotoId) {
                    this.defaultPhotoId = defaultPhotoId;
                }

                public String getDefaultPhotoPath() {
                    return defaultPhotoPath;
                }

                public void setDefaultPhotoPath(String defaultPhotoPath) {
                    this.defaultPhotoPath = defaultPhotoPath;
                }

                public int getDataStatus() {
                    return dataStatus;
                }

                public void setDataStatus(int dataStatus) {
                    this.dataStatus = dataStatus;
                }

                public String getNormText() {
                    return normText;
                }

                public void setNormText(String normText) {
                    this.normText = normText;
                }

                public String getNormIds() {
                    return normIds;
                }

                public void setNormIds(String normIds) {
                    this.normIds = normIds;
                }

                public int getStock() {
                    return stock;
                }

                public void setStock(int stock) {
                    this.stock = stock;
                }

                public int getSysIsEnable() {
                    return sysIsEnable;
                }

                public void setSysIsEnable(int sysIsEnable) {
                    this.sysIsEnable = sysIsEnable;
                }

                public String getSysIdString() {
                    return sysIdString;
                }

                public void setSysIdString(String sysIdString) {
                    this.sysIdString = sysIdString;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }
            }

            public static class SeekGoodsItemVOSBean {
                /**
                 * itemName : 大小
                 * itemSort : 1
                 * isShow : true
                 * norms : [{"normId":9341,"goodsId":30904,"sysGoodsId":"23458564135849984","itemSystemId":"25954880455839744","itemName":"大小","normName":"大","normValue":"大","isShow":true,"normSort":1,"sysIdString":"25954880594251776"},{"normId":9352,"goodsId":30904,"sysGoodsId":"23458564135849984","itemSystemId":"25954880455839744","itemName":"大小","normName":"贼小","normValue":"贼小","isShow":true,"normSort":3,"sysIdString":"25962197872152576"},{"normId":9353,"goodsId":30904,"sysGoodsId":"23458564135849984","itemSystemId":"25954880455839744","itemName":"大小","normName":"中","normValue":"中","isShow":true,"normSort":4,"sysIdString":"25962197897318400"},{"normId":9354,"goodsId":30904,"sysGoodsId":"23458564135849984","itemSystemId":"25954880455839744","itemName":"大小","normName":"小","normValue":"小","isShow":true,"normSort":5,"sysIdString":"25962197926678528"}]
                 * itemSystemId : 25954880455839744
                 */

                private String itemName;
                private int itemSort;
                private boolean isShow;
                private long itemSystemId;
                private List<NormsBean> norms;

                public String getItemName() {
                    return itemName;
                }

                public void setItemName(String itemName) {
                    this.itemName = itemName;
                }

                public int getItemSort() {
                    return itemSort;
                }

                public void setItemSort(int itemSort) {
                    this.itemSort = itemSort;
                }

                public boolean isIsShow() {
                    return isShow;
                }

                public void setIsShow(boolean isShow) {
                    this.isShow = isShow;
                }

                public long getItemSystemId() {
                    return itemSystemId;
                }

                public void setItemSystemId(long itemSystemId) {
                    this.itemSystemId = itemSystemId;
                }

                public List<NormsBean> getNorms() {
                    return norms;
                }

                public void setNorms(List<NormsBean> norms) {
                    this.norms = norms;
                }

                public static class NormsBean {
                    /**
                     * normId : 9341
                     * goodsId : 30904
                     * sysGoodsId : 23458564135849984
                     * itemSystemId : 25954880455839744
                     * itemName : 大小
                     * normName : 大
                     * normValue : 大
                     * isShow : true
                     * normSort : 1
                     * sysIdString : 25954880594251776
                     */

                    private int normId;
                    private int goodsId;
                    private String sysGoodsId;
                    private String itemSystemId;
                    private String itemName;
                    private String normName;
                    private String normValue;
                    private boolean isShow;
                    private int normSort;
                    private String sysIdString;

                    public int getNormId() {
                        return normId;
                    }

                    public void setNormId(int normId) {
                        this.normId = normId;
                    }

                    public int getGoodsId() {
                        return goodsId;
                    }

                    public void setGoodsId(int goodsId) {
                        this.goodsId = goodsId;
                    }

                    public String getSysGoodsId() {
                        return sysGoodsId;
                    }

                    public void setSysGoodsId(String sysGoodsId) {
                        this.sysGoodsId = sysGoodsId;
                    }

                    public String getItemSystemId() {
                        return itemSystemId;
                    }

                    public void setItemSystemId(String itemSystemId) {
                        this.itemSystemId = itemSystemId;
                    }

                    public String getItemName() {
                        return itemName;
                    }

                    public void setItemName(String itemName) {
                        this.itemName = itemName;
                    }

                    public String getNormName() {
                        return normName;
                    }

                    public void setNormName(String normName) {
                        this.normName = normName;
                    }

                    public String getNormValue() {
                        return normValue;
                    }

                    public void setNormValue(String normValue) {
                        this.normValue = normValue;
                    }

                    public boolean isIsShow() {
                        return isShow;
                    }

                    public void setIsShow(boolean isShow) {
                        this.isShow = isShow;
                    }

                    public int getNormSort() {
                        return normSort;
                    }

                    public void setNormSort(int normSort) {
                        this.normSort = normSort;
                    }

                    public String getSysIdString() {
                        return sysIdString;
                    }

                    public void setSysIdString(String sysIdString) {
                        this.sysIdString = sysIdString;
                    }
                }
            }

            public long getSysIdString() {
                return sysIdString;
            }

            public void setSysIdString(long sysIdString) {
                this.sysIdString = sysIdString;
            }
        }


    }
}
