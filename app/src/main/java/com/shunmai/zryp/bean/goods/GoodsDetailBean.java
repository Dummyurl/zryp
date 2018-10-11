package com.shunmai.zryp.bean.goods;

import com.shunmai.zryp.bean.TResponse;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/9/29.
 */

public class GoodsDetailBean extends TResponse<GoodsDetailBean.DataBean>{

    public static class DataBean {
        /**
         * goods : {"goodsId":1,"channelId":0,"platgoodsId":"12345678ghgs","goodsName":"阿达迪斯","goodsNote":"就是牛","goodsType":1,"goodsPropery":1,"catlaogMobileId":11090961178431024,"catalogPcId":0,"brandId":0,"price":25.5,"marketPrice":24.2,"mscore":15,"weight":1.6,"keywords":"阿迪","sortOrder":0,"upcarriageTime":1537222283000,"lastupdateTime":1537222283000,"isFronthidden":false,"createtime":1.537222283E12,"dataStatus":0,"defalutPhotourl":"https://rabc2.iteye.com/4j/s=7bfnpwv67a,grrx3zbce&x8=14?we6icl=xsqxns_9con8_yeg","sysSpaceNo":"B0CD0050CF0BF01B","sysVersionNo":"3","sysIsEnable":"1","sysIdString":10750656398888960,"sysDataFrom":"ios","limit":0,"description":"商品描述1","freightMode":1,"goodsTitle":"商品标题1","appDetails":"app端商品详情1","miniDetails":"小程序商品详情1","addTime":null,"seekGoodsImgsVOS":[{"goodsPhotoId":8,"goodsPhotoName":"测试图片3","isDefaultPhoto":false,"goodsId":10750656398888960,"photoUrl":"http://b0cd0050cf0bf01b.oss-cn-beijing.aliyuncs.com/2018/9/f13cc5a3-e6a8-4a16-8339-f35c90a0b219.gif","sortOrder":0,"createTime":1.537500884E12,"updateTime":null,"photoType":1,"dataStatus":0,"sysCreateTime":1.537500884E12,"sysVersionNo":0,"sysIsEnable":1,"sysIdString":11771250796007424}]}
         * sku : {"sysIdString":0,"seekGoodsSkuVOS":[],"seekGoodsItemVOS":[]}
         * list : [{"price":25.5,"defalutPhotourl":null,"marketPrice":24.2,"sysIdString":14651775802019840,"goodsName":"阿达迪斯1","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":1},{"price":255.8,"defalutPhotourl":null,"marketPrice":236.8,"sysIdString":11552206172065792,"goodsName":"测试商品2","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":2},{"price":25.5,"defalutPhotourl":null,"marketPrice":24.2,"sysIdString":10750656398888960,"goodsName":"阿达迪斯","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":1},{"price":25.5,"defalutPhotourl":null,"marketPrice":24.2,"sysIdString":14651389229797376,"goodsName":"阿达迪斯1","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":1},{"price":35.8,"defalutPhotourl":null,"marketPrice":36.8,"sysIdString":11552102606311424,"goodsName":"测试商品1","goodsTitle":null,"platgoodsId":"12345678","goodsPropery":2}]
         */

        private GoodsBean goods;
        private SkuBean sku;
        private List<com.shunmai.zryp.bean.goods.GoodsBean> list;

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
        }

        public List<com.shunmai.zryp.bean.goods.GoodsBean> getList() {
            return list;
        }

        public void setList(List<com.shunmai.zryp.bean.goods.GoodsBean> list) {
            this.list = list;
        }

        public static class GoodsBean {
            /**
             * goodsId : 1
             * channelId : 0
             * platgoodsId : 12345678ghgs
             * goodsName : 阿达迪斯
             * goodsNote : 就是牛
             * goodsType : 1
             * goodsPropery : 1
             * catlaogMobileId : 11090961178431024
             * catalogPcId : 0
             * brandId : 0
             * price : 25.5
             * marketPrice : 24.2
             * mscore : 15.0
             * weight : 1.6
             * keywords : 阿迪
             * sortOrder : 0
             * upcarriageTime : 1537222283000
             * lastupdateTime : 1537222283000
             * isFronthidden : false
             * createtime : 1.537222283E12
             * dataStatus : 0
             * defalutPhotourl : https://rabc2.iteye.com/4j/s=7bfnpwv67a,grrx3zbce&x8=14?we6icl=xsqxns_9con8_yeg
             * sysSpaceNo : B0CD0050CF0BF01B
             * sysVersionNo : 3
             * sysIsEnable : 1
             * sysIdString : 10750656398888960
             * sysDataFrom : ios
             * limit : 0
             * description : 商品描述1
             * freightMode : 1
             * goodsTitle : 商品标题1
             * appDetails : app端商品详情1
             * miniDetails : 小程序商品详情1
             * addTime : null
             * seekGoodsImgsVOS : [{"goodsPhotoId":8,"goodsPhotoName":"测试图片3","isDefaultPhoto":false,"goodsId":10750656398888960,"photoUrl":"http://b0cd0050cf0bf01b.oss-cn-beijing.aliyuncs.com/2018/9/f13cc5a3-e6a8-4a16-8339-f35c90a0b219.gif","sortOrder":0,"createTime":1.537500884E12,"updateTime":null,"photoType":1,"dataStatus":0,"sysCreateTime":1.537500884E12,"sysVersionNo":0,"sysIsEnable":1,"sysIdString":11771250796007424}]
             */

            private int goodsId;
            private int channelId;
            private String platgoodsId;
            private String goodsName;
            private String goodsNote;
            private int goodsType;
            private int goodsPropery;
            private long catlaogMobileId;
            private int catalogPcId;
            private int brandId;
            private double price;
            private double marketPrice;
            private double mscore;
            private double weight;
            private String keywords;
            private int sortOrder;
            private long upcarriageTime;
            private long lastupdateTime;
            private boolean isFronthidden;
            private double createtime;
            private int dataStatus;
            private String defalutPhotourl;
            private String sysSpaceNo;
            private String sysVersionNo;
            private String sysIsEnable;
            private long sysIdString;
            private String sysDataFrom;
            private int limit;
            private String description;
            private int freightMode;
            private String goodsTitle;
            private String appDetails;
            private String miniDetails;
            private Object addTime;
            private List<SeekGoodsImgsVOSBean> seekGoodsImgsVOS;

            public int getGoodsId() {
                return goodsId;
            }

            public void setGoodsId(int goodsId) {
                this.goodsId = goodsId;
            }

            public int getChannelId() {
                return channelId;
            }

            public void setChannelId(int channelId) {
                this.channelId = channelId;
            }

            public String getPlatgoodsId() {
                return platgoodsId;
            }

            public void setPlatgoodsId(String platgoodsId) {
                this.platgoodsId = platgoodsId;
            }

            public String getGoodsName() {
                return goodsName;
            }

            public void setGoodsName(String goodsName) {
                this.goodsName = goodsName;
            }

            public String getGoodsNote() {
                return goodsNote;
            }

            public void setGoodsNote(String goodsNote) {
                this.goodsNote = goodsNote;
            }

            public int getGoodsType() {
                return goodsType;
            }

            public void setGoodsType(int goodsType) {
                this.goodsType = goodsType;
            }

            public int getGoodsPropery() {
                return goodsPropery;
            }

            public void setGoodsPropery(int goodsPropery) {
                this.goodsPropery = goodsPropery;
            }

            public long getCatlaogMobileId() {
                return catlaogMobileId;
            }

            public void setCatlaogMobileId(long catlaogMobileId) {
                this.catlaogMobileId = catlaogMobileId;
            }

            public int getCatalogPcId() {
                return catalogPcId;
            }

            public void setCatalogPcId(int catalogPcId) {
                this.catalogPcId = catalogPcId;
            }

            public int getBrandId() {
                return brandId;
            }

            public void setBrandId(int brandId) {
                this.brandId = brandId;
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

            public double getMscore() {
                return mscore;
            }

            public void setMscore(double mscore) {
                this.mscore = mscore;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public String getKeywords() {
                return keywords;
            }

            public void setKeywords(String keywords) {
                this.keywords = keywords;
            }

            public int getSortOrder() {
                return sortOrder;
            }

            public void setSortOrder(int sortOrder) {
                this.sortOrder = sortOrder;
            }

            public long getUpcarriageTime() {
                return upcarriageTime;
            }

            public void setUpcarriageTime(long upcarriageTime) {
                this.upcarriageTime = upcarriageTime;
            }

            public long getLastupdateTime() {
                return lastupdateTime;
            }

            public void setLastupdateTime(long lastupdateTime) {
                this.lastupdateTime = lastupdateTime;
            }

            public boolean isIsFronthidden() {
                return isFronthidden;
            }

            public void setIsFronthidden(boolean isFronthidden) {
                this.isFronthidden = isFronthidden;
            }

            public double getCreatetime() {
                return createtime;
            }

            public void setCreatetime(double createtime) {
                this.createtime = createtime;
            }

            public int getDataStatus() {
                return dataStatus;
            }

            public void setDataStatus(int dataStatus) {
                this.dataStatus = dataStatus;
            }

            public String getDefalutPhotourl() {
                return defalutPhotourl;
            }

            public void setDefalutPhotourl(String defalutPhotourl) {
                this.defalutPhotourl = defalutPhotourl;
            }

            public String getSysSpaceNo() {
                return sysSpaceNo;
            }

            public void setSysSpaceNo(String sysSpaceNo) {
                this.sysSpaceNo = sysSpaceNo;
            }

            public String getSysVersionNo() {
                return sysVersionNo;
            }

            public void setSysVersionNo(String sysVersionNo) {
                this.sysVersionNo = sysVersionNo;
            }

            public String getSysIsEnable() {
                return sysIsEnable;
            }

            public void setSysIsEnable(String sysIsEnable) {
                this.sysIsEnable = sysIsEnable;
            }

            public long getSysIdString() {
                return sysIdString;
            }

            public void setSysIdString(long sysIdString) {
                this.sysIdString = sysIdString;
            }

            public String getSysDataFrom() {
                return sysDataFrom;
            }

            public void setSysDataFrom(String sysDataFrom) {
                this.sysDataFrom = sysDataFrom;
            }

            public int getLimit() {
                return limit;
            }

            public void setLimit(int limit) {
                this.limit = limit;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public int getFreightMode() {
                return freightMode;
            }

            public void setFreightMode(int freightMode) {
                this.freightMode = freightMode;
            }

            public String getGoodsTitle() {
                return goodsTitle;
            }

            public void setGoodsTitle(String goodsTitle) {
                this.goodsTitle = goodsTitle;
            }

            public String getAppDetails() {
                return appDetails;
            }

            public void setAppDetails(String appDetails) {
                this.appDetails = appDetails;
            }

            public String getMiniDetails() {
                return miniDetails;
            }

            public void setMiniDetails(String miniDetails) {
                this.miniDetails = miniDetails;
            }

            public Object getAddTime() {
                return addTime;
            }

            public void setAddTime(Object addTime) {
                this.addTime = addTime;
            }

            public List<SeekGoodsImgsVOSBean> getSeekGoodsImgsVOS() {
                return seekGoodsImgsVOS;
            }

            public void setSeekGoodsImgsVOS(List<SeekGoodsImgsVOSBean> seekGoodsImgsVOS) {
                this.seekGoodsImgsVOS = seekGoodsImgsVOS;
            }

            public static class SeekGoodsImgsVOSBean {
                /**
                 * goodsPhotoId : 8
                 * goodsPhotoName : 测试图片3
                 * isDefaultPhoto : false
                 * goodsId : 10750656398888960
                 * photoUrl : http://b0cd0050cf0bf01b.oss-cn-beijing.aliyuncs.com/2018/9/f13cc5a3-e6a8-4a16-8339-f35c90a0b219.gif
                 * sortOrder : 0
                 * createTime : 1.537500884E12
                 * updateTime : null
                 * photoType : 1
                 * dataStatus : 0
                 * sysCreateTime : 1.537500884E12
                 * sysVersionNo : 0
                 * sysIsEnable : 1
                 * sysIdString : 11771250796007424
                 */

                private int goodsPhotoId;
                private String goodsPhotoName;
                private boolean isDefaultPhoto;
                private long goodsId;
                private String photoUrl;
                private int sortOrder;
                private double createTime;
                private Object updateTime;
                private int photoType;
                private int dataStatus;
                private double sysCreateTime;
                private int sysVersionNo;
                private int sysIsEnable;
                private long sysIdString;

                public int getGoodsPhotoId() {
                    return goodsPhotoId;
                }

                public void setGoodsPhotoId(int goodsPhotoId) {
                    this.goodsPhotoId = goodsPhotoId;
                }

                public String getGoodsPhotoName() {
                    return goodsPhotoName;
                }

                public void setGoodsPhotoName(String goodsPhotoName) {
                    this.goodsPhotoName = goodsPhotoName;
                }

                public boolean isIsDefaultPhoto() {
                    return isDefaultPhoto;
                }

                public void setIsDefaultPhoto(boolean isDefaultPhoto) {
                    this.isDefaultPhoto = isDefaultPhoto;
                }

                public long getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(long goodsId) {
                    this.goodsId = goodsId;
                }

                public String getPhotoUrl() {
                    return photoUrl;
                }

                public void setPhotoUrl(String photoUrl) {
                    this.photoUrl = photoUrl;
                }

                public int getSortOrder() {
                    return sortOrder;
                }

                public void setSortOrder(int sortOrder) {
                    this.sortOrder = sortOrder;
                }

                public double getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(double createTime) {
                    this.createTime = createTime;
                }

                public Object getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(Object updateTime) {
                    this.updateTime = updateTime;
                }

                public int getPhotoType() {
                    return photoType;
                }

                public void setPhotoType(int photoType) {
                    this.photoType = photoType;
                }

                public int getDataStatus() {
                    return dataStatus;
                }

                public void setDataStatus(int dataStatus) {
                    this.dataStatus = dataStatus;
                }

                public double getSysCreateTime() {
                    return sysCreateTime;
                }

                public void setSysCreateTime(double sysCreateTime) {
                    this.sysCreateTime = sysCreateTime;
                }

                public int getSysVersionNo() {
                    return sysVersionNo;
                }

                public void setSysVersionNo(int sysVersionNo) {
                    this.sysVersionNo = sysVersionNo;
                }

                public int getSysIsEnable() {
                    return sysIsEnable;
                }

                public void setSysIsEnable(int sysIsEnable) {
                    this.sysIsEnable = sysIsEnable;
                }

                public long getSysIdString() {
                    return sysIdString;
                }

                public void setSysIdString(long sysIdString) {
                    this.sysIdString = sysIdString;
                }
            }
        }

        public static class SkuBean {
            /**
             * sysIdString : 0
             * seekGoodsSkuVOS : []
             * seekGoodsItemVOS : []
             */

            private int sysIdString;
            private List<?> seekGoodsSkuVOS;
            private List<?> seekGoodsItemVOS;

            public int getSysIdString() {
                return sysIdString;
            }

            public void setSysIdString(int sysIdString) {
                this.sysIdString = sysIdString;
            }

            public List<?> getSeekGoodsSkuVOS() {
                return seekGoodsSkuVOS;
            }

            public void setSeekGoodsSkuVOS(List<?> seekGoodsSkuVOS) {
                this.seekGoodsSkuVOS = seekGoodsSkuVOS;
            }

            public List<?> getSeekGoodsItemVOS() {
                return seekGoodsItemVOS;
            }

            public void setSeekGoodsItemVOS(List<?> seekGoodsItemVOS) {
                this.seekGoodsItemVOS = seekGoodsItemVOS;
            }
        }


    }
}
