package com.shunmai.zryp.bean.home;

import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.goods.GoodsBean;

import java.util.List;

/**
 * Created by yushengyang.
 * Date: 2018/8/29.
 */

public class HomePageBean extends TResponse<HomePageBean.DataBean>{




    public static class DataBean {

        private FlashSaleBean flashSale;
        private GroupBuyingBean groupBuying;
        private SpecialBean special;
        private TopPostionBean topPostion;
        private CenterPostionBean centerPostion;
        private List<GoodsBean> theNewGoods;
        private List<GoodsBean> recommendGoods;
        private List<GoodsBean> guessGoods;

        public FlashSaleBean getFlashSale() {
            return flashSale;
        }

        public void setFlashSale(FlashSaleBean flashSale) {
            this.flashSale = flashSale;
        }

        public GroupBuyingBean getGroupBuying() {
            return groupBuying;
        }

        public void setGroupBuying(GroupBuyingBean groupBuying) {
            this.groupBuying = groupBuying;
        }

        public SpecialBean getSpecial() {
            return special;
        }

        public void setSpecial(SpecialBean special) {
            this.special = special;
        }

        public TopPostionBean getTopPostion() {
            return topPostion;
        }

        public void setTopPostion(TopPostionBean topPostion) {
            this.topPostion = topPostion;
        }

        public CenterPostionBean getCenterPostion() {
            return centerPostion;
        }

        public void setCenterPostion(CenterPostionBean centerPostion) {
            this.centerPostion = centerPostion;
        }

        public List<GoodsBean> getTheNewGoods() {
            return theNewGoods;
        }

        public void setTheNewGoods(List<GoodsBean> theNewGoods) {
            this.theNewGoods = theNewGoods;
        }

        public List<GoodsBean> getRecommendGoods() {
            return recommendGoods;
        }

        public void setRecommendGoods(List<GoodsBean> recommendGoods) {
            this.recommendGoods = recommendGoods;
        }

        public List<GoodsBean> getGuessGoods() {
            return guessGoods;
        }

        public void setGuessGoods(List<GoodsBean> guessGoods) {
            this.guessGoods = guessGoods;
        }

        public static class FlashSaleBean {

            /**
             * prStart : 1537511135000
             * prEnd : 1538288737000
             * promotionGoodsList : [{"goSalenum":9999,"sellRate":"100%","seekGoodsVO":{"goodsId":1,"channelId":0,"platgoodsId":"12345678ghgs","goodsName":"阿达迪斯","goodsNote":"就是牛","goodsType":1,"catlaogMobileId":11090961178431024,"brandId":0,"price":25.5,"marketPrice":24.2,"mscore":14,"weight":1.6,"keywords":"关键字","isFronthidden":false,"defalutPhotourl":"https://rabc2.iteye.com/4j/s=7bfnpwv67a,grrx3zbce&x8=14?we6icl=xsqxns_9con8_yeg","sysSpaceNo":"B0CD0050CF0BF01B","sysIdString":10750656398888960,"description":"商品描述1","freightMode":1,"goodsTitle":"商品标题1","appDetails":"app端商品详情1","miniDetails":"小程序商品详情1","seekGoodsImgsVOS":null},"sysCreateTime":1537511139000}]
             */

            private long prStart;
            private long prEnd;
            private List<PromotionGoodsListBean> promotionGoodsList;

            public long getPrStart() {
                return prStart;
            }

            public void setPrStart(long prStart) {
                this.prStart = prStart;
            }

            public long getPrEnd() {
                return prEnd;
            }

            public void setPrEnd(long prEnd) {
                this.prEnd = prEnd;
            }

            public List<PromotionGoodsListBean> getPromotionGoodsList() {
                return promotionGoodsList;
            }

            public void setPromotionGoodsList(List<PromotionGoodsListBean> promotionGoodsList) {
                this.promotionGoodsList = promotionGoodsList;
            }

            public static class PromotionGoodsListBean {
                /**
                 * goSalenum : 9999
                 * sellRate : 100%
                 * seekGoodsVO : {"goodsId":1,"channelId":0,"platgoodsId":"12345678ghgs","goodsName":"阿达迪斯","goodsNote":"就是牛","goodsType":1,"catlaogMobileId":11090961178431024,"brandId":0,"price":25.5,"marketPrice":24.2,"mscore":14,"weight":1.6,"keywords":"关键字","isFronthidden":false,"defalutPhotourl":"https://rabc2.iteye.com/4j/s=7bfnpwv67a,grrx3zbce&x8=14?we6icl=xsqxns_9con8_yeg","sysSpaceNo":"B0CD0050CF0BF01B","sysIdString":10750656398888960,"description":"商品描述1","freightMode":1,"goodsTitle":"商品标题1","appDetails":"app端商品详情1","miniDetails":"小程序商品详情1","seekGoodsImgsVOS":null}
                 * sysCreateTime : 1537511139000
                 */

                private int goSalenum;
                private String sellRate;
                private GoodsBean seekGoodsVO;
                private long sysCreateTime;

                public int getGoSalenum() {
                    return goSalenum;
                }

                public void setGoSalenum(int goSalenum) {
                    this.goSalenum = goSalenum;
                }

                public String getSellRate() {
                    return sellRate;
                }

                public void setSellRate(String sellRate) {
                    this.sellRate = sellRate;
                }

                public GoodsBean getSeekGoodsVO() {
                    return seekGoodsVO;
                }

                public void setSeekGoodsVO(GoodsBean seekGoodsVO) {
                    this.seekGoodsVO = seekGoodsVO;
                }

                public long getSysCreateTime() {
                    return sysCreateTime;
                }

                public void setSysCreateTime(long sysCreateTime) {
                    this.sysCreateTime = sysCreateTime;
                }
            }
        }

        public static class GroupBuyingBean {
            /**
             * prGroupnum : 3
             * promotionGoodsList : [{"seekGoodsVO":{"goodsId":1,"channelId":0,"platgoodsId":"12345678ghgs","goodsName":"阿达迪斯","goodsNote":"就是牛","goodsType":1,"catlaogMobileId":11090961178431024,"brandId":0,"price":25.5,"marketPrice":24.2,"mscore":14,"weight":1.6,"keywords":"关键字","isFronthidden":false,"defalutPhotourl":"https://rabc2.iteye.com/4j/s=7bfnpwv67a,grrx3zbce&x8=14?we6icl=xsqxns_9con8_yeg","sysSpaceNo":"B0CD0050CF0BF01B","sysIdString":10750656398888960,"description":"商品描述1","freightMode":1,"goodsTitle":"商品标题1","appDetails":"app端商品详情1","miniDetails":"小程序商品详情1","seekGoodsImgsVOS":null},"sysCreateTime":1537929680000}]
             */

            private int prGroupnum;
            private List<PromotionGoodsListBean> promotionGoodsList;

            public int getPrGroupnum() {
                return prGroupnum;
            }

            public void setPrGroupnum(int prGroupnum) {
                this.prGroupnum = prGroupnum;
            }

            public List<PromotionGoodsListBean> getPromotionGoodsList() {
                return promotionGoodsList;
            }

            public void setPromotionGoodsList(List<PromotionGoodsListBean> promotionGoodsList) {
                this.promotionGoodsList = promotionGoodsList;
            }

            public static class PromotionGoodsListBean {
                private GoodsBean seekGoodsVO;

                public GoodsBean getSeekGoodsVO() {
                    return seekGoodsVO;
                }

                public void setSeekGoodsVO(GoodsBean seekGoodsVO) {
                    this.seekGoodsVO = seekGoodsVO;
                }
            }
        }

        public static class SpecialBean {


            private String prTimeStateDesc;
            private int prTimeState;
            private String timePrEnd;
            private String timePrStart;
            private String timeCreateStart;
            private String timeCreateEnd;
            private long sysCreateTimeStart;
            private long sysCreateTimeEnd;
            private String imgUrl;
            private int prId;
            private String prName;
            private long prStart;
            private long prEnd;
            private String prConent;
            private int prType;
            private int prGroupnum;
            private int scoreTime;
            private int isEnableAll;
            private String scoreType;
            private Object promotionGoodsList;
            private long sysCreateTime;
            private long sysEditTime;
            private String sysCreateUser;
            private String sysEditUser;
            private String sysSpaceNo;
            private int sysVersionNo;
            private int sysIsEnable;
            private int sysIdString;
            private int sysDataFrom;
            private String attr1;
            private String attr2;
            private String attr3;
            private String attr4;
            private String attr5;
            private List<PromotionTopicListBean> promotionTopicList;

            public String getPrTimeStateDesc() {
                return prTimeStateDesc;
            }

            public void setPrTimeStateDesc(String prTimeStateDesc) {
                this.prTimeStateDesc = prTimeStateDesc;
            }

            public int getPrTimeState() {
                return prTimeState;
            }

            public void setPrTimeState(int prTimeState) {
                this.prTimeState = prTimeState;
            }

            public String getTimePrEnd() {
                return timePrEnd;
            }

            public void setTimePrEnd(String timePrEnd) {
                this.timePrEnd = timePrEnd;
            }

            public String getTimePrStart() {
                return timePrStart;
            }

            public void setTimePrStart(String timePrStart) {
                this.timePrStart = timePrStart;
            }

            public String getTimeCreateStart() {
                return timeCreateStart;
            }

            public void setTimeCreateStart(String timeCreateStart) {
                this.timeCreateStart = timeCreateStart;
            }

            public String getTimeCreateEnd() {
                return timeCreateEnd;
            }

            public void setTimeCreateEnd(String timeCreateEnd) {
                this.timeCreateEnd = timeCreateEnd;
            }

            public long getSysCreateTimeStart() {
                return sysCreateTimeStart;
            }

            public void setSysCreateTimeStart(long sysCreateTimeStart) {
                this.sysCreateTimeStart = sysCreateTimeStart;
            }

            public long getSysCreateTimeEnd() {
                return sysCreateTimeEnd;
            }

            public void setSysCreateTimeEnd(long sysCreateTimeEnd) {
                this.sysCreateTimeEnd = sysCreateTimeEnd;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public int getPrId() {
                return prId;
            }

            public void setPrId(int prId) {
                this.prId = prId;
            }

            public String getPrName() {
                return prName;
            }

            public void setPrName(String prName) {
                this.prName = prName;
            }

            public long getPrStart() {
                return prStart;
            }

            public void setPrStart(long prStart) {
                this.prStart = prStart;
            }

            public long getPrEnd() {
                return prEnd;
            }

            public void setPrEnd(long prEnd) {
                this.prEnd = prEnd;
            }

            public String getPrConent() {
                return prConent;
            }

            public void setPrConent(String prConent) {
                this.prConent = prConent;
            }

            public int getPrType() {
                return prType;
            }

            public void setPrType(int prType) {
                this.prType = prType;
            }

            public int getPrGroupnum() {
                return prGroupnum;
            }

            public void setPrGroupnum(int prGroupnum) {
                this.prGroupnum = prGroupnum;
            }

            public int getScoreTime() {
                return scoreTime;
            }

            public void setScoreTime(int scoreTime) {
                this.scoreTime = scoreTime;
            }

            public int getIsEnableAll() {
                return isEnableAll;
            }

            public void setIsEnableAll(int isEnableAll) {
                this.isEnableAll = isEnableAll;
            }

            public String getScoreType() {
                return scoreType;
            }

            public void setScoreType(String scoreType) {
                this.scoreType = scoreType;
            }

            public Object getPromotionGoodsList() {
                return promotionGoodsList;
            }

            public void setPromotionGoodsList(Object promotionGoodsList) {
                this.promotionGoodsList = promotionGoodsList;
            }

            public long getSysCreateTime() {
                return sysCreateTime;
            }

            public void setSysCreateTime(long sysCreateTime) {
                this.sysCreateTime = sysCreateTime;
            }

            public long getSysEditTime() {
                return sysEditTime;
            }

            public void setSysEditTime(long sysEditTime) {
                this.sysEditTime = sysEditTime;
            }

            public String getSysCreateUser() {
                return sysCreateUser;
            }

            public void setSysCreateUser(String sysCreateUser) {
                this.sysCreateUser = sysCreateUser;
            }

            public String getSysEditUser() {
                return sysEditUser;
            }

            public void setSysEditUser(String sysEditUser) {
                this.sysEditUser = sysEditUser;
            }

            public String getSysSpaceNo() {
                return sysSpaceNo;
            }

            public void setSysSpaceNo(String sysSpaceNo) {
                this.sysSpaceNo = sysSpaceNo;
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

            public int getSysIdString() {
                return sysIdString;
            }

            public void setSysIdString(int sysIdString) {
                this.sysIdString = sysIdString;
            }

            public int getSysDataFrom() {
                return sysDataFrom;
            }

            public void setSysDataFrom(int sysDataFrom) {
                this.sysDataFrom = sysDataFrom;
            }

            public String getAttr1() {
                return attr1;
            }

            public void setAttr1(String attr1) {
                this.attr1 = attr1;
            }

            public String getAttr2() {
                return attr2;
            }

            public void setAttr2(String attr2) {
                this.attr2 = attr2;
            }

            public String getAttr3() {
                return attr3;
            }

            public void setAttr3(String attr3) {
                this.attr3 = attr3;
            }

            public String getAttr4() {
                return attr4;
            }

            public void setAttr4(String attr4) {
                this.attr4 = attr4;
            }

            public String getAttr5() {
                return attr5;
            }

            public void setAttr5(String attr5) {
                this.attr5 = attr5;
            }

            public List<PromotionTopicListBean> getPromotionTopicList() {
                return promotionTopicList;
            }

            public void setPromotionTopicList(List<PromotionTopicListBean> promotionTopicList) {
                this.promotionTopicList = promotionTopicList;
            }

            public static class PromotionTopicListBean {

                private int toId;
                private String toName;
                private int toPrId;
                private int pageNum;
                private int pageCount;
                private long sysCreateTime;
                private long sysEditTime;
                private String sysCreateUser;
                private String sysEditUser;
                private String sysSpaceNo;
                private int sysVersionNo;
                private int sysIsEnable;
                private int sysIdString;
                private int sysDataFrom;
                private String attr1;
                private String attr2;
                private String attr3;
                private String attr4;
                private String attr5;
                private List<PromotionGoodsListBeanXX> promotionGoodsList;

                public int getToId() {
                    return toId;
                }

                public void setToId(int toId) {
                    this.toId = toId;
                }

                public String getToName() {
                    return toName;
                }

                public void setToName(String toName) {
                    this.toName = toName;
                }

                public int getToPrId() {
                    return toPrId;
                }

                public void setToPrId(int toPrId) {
                    this.toPrId = toPrId;
                }

                public int getPageNum() {
                    return pageNum;
                }

                public void setPageNum(int pageNum) {
                    this.pageNum = pageNum;
                }

                public int getPageCount() {
                    return pageCount;
                }

                public void setPageCount(int pageCount) {
                    this.pageCount = pageCount;
                }

                public long getSysCreateTime() {
                    return sysCreateTime;
                }

                public void setSysCreateTime(long sysCreateTime) {
                    this.sysCreateTime = sysCreateTime;
                }

                public long getSysEditTime() {
                    return sysEditTime;
                }

                public void setSysEditTime(long sysEditTime) {
                    this.sysEditTime = sysEditTime;
                }

                public String getSysCreateUser() {
                    return sysCreateUser;
                }

                public void setSysCreateUser(String sysCreateUser) {
                    this.sysCreateUser = sysCreateUser;
                }

                public String getSysEditUser() {
                    return sysEditUser;
                }

                public void setSysEditUser(String sysEditUser) {
                    this.sysEditUser = sysEditUser;
                }

                public String getSysSpaceNo() {
                    return sysSpaceNo;
                }

                public void setSysSpaceNo(String sysSpaceNo) {
                    this.sysSpaceNo = sysSpaceNo;
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

                public int getSysIdString() {
                    return sysIdString;
                }

                public void setSysIdString(int sysIdString) {
                    this.sysIdString = sysIdString;
                }

                public int getSysDataFrom() {
                    return sysDataFrom;
                }

                public void setSysDataFrom(int sysDataFrom) {
                    this.sysDataFrom = sysDataFrom;
                }

                public String getAttr1() {
                    return attr1;
                }

                public void setAttr1(String attr1) {
                    this.attr1 = attr1;
                }

                public String getAttr2() {
                    return attr2;
                }

                public void setAttr2(String attr2) {
                    this.attr2 = attr2;
                }

                public String getAttr3() {
                    return attr3;
                }

                public void setAttr3(String attr3) {
                    this.attr3 = attr3;
                }

                public String getAttr4() {
                    return attr4;
                }

                public void setAttr4(String attr4) {
                    this.attr4 = attr4;
                }

                public String getAttr5() {
                    return attr5;
                }

                public void setAttr5(String attr5) {
                    this.attr5 = attr5;
                }

                public List<PromotionGoodsListBeanXX> getPromotionGoodsList() {
                    return promotionGoodsList;
                }

                public void setPromotionGoodsList(List<PromotionGoodsListBeanXX> promotionGoodsList) {
                    this.promotionGoodsList = promotionGoodsList;
                }

                public static class PromotionGoodsListBeanXX {

                    private int goId;
                    private int goStock;
                    private int goLimit;
                    private double goPrPrice;
                    private int goSalenum;
                    private int goPrId;
                    private int goToId;
                    private int goodsNum;
                    private GoodsBean goods;
                    private long sysCreateTime;
                    private int sysEditTime;
                    private String sysCreateUser;
                    private String sysEditUser;
                    private String sysSpaceNo;
                    private int sysVersionNo;
                    private int sysIsEnable;
                    private int sysIdString;
                    private int sysDataFrom;
                    private String attr1;
                    private String attr2;
                    private String attr3;
                    private String attr4;
                    private String attr5;

                    public int getGoId() {
                        return goId;
                    }

                    public void setGoId(int goId) {
                        this.goId = goId;
                    }

                    public int getGoStock() {
                        return goStock;
                    }

                    public void setGoStock(int goStock) {
                        this.goStock = goStock;
                    }

                    public int getGoLimit() {
                        return goLimit;
                    }

                    public void setGoLimit(int goLimit) {
                        this.goLimit = goLimit;
                    }

                    public double getGoPrPrice() {
                        return goPrPrice;
                    }

                    public void setGoPrPrice(double goPrPrice) {
                        this.goPrPrice = goPrPrice;
                    }

                    public int getGoSalenum() {
                        return goSalenum;
                    }

                    public void setGoSalenum(int goSalenum) {
                        this.goSalenum = goSalenum;
                    }

                    public int getGoPrId() {
                        return goPrId;
                    }

                    public void setGoPrId(int goPrId) {
                        this.goPrId = goPrId;
                    }

                    public int getGoToId() {
                        return goToId;
                    }

                    public void setGoToId(int goToId) {
                        this.goToId = goToId;
                    }

                    public int getGoodsNum() {
                        return goodsNum;
                    }

                    public void setGoodsNum(int goodsNum) {
                        this.goodsNum = goodsNum;
                    }

                    public GoodsBean getGoods() {
                        return goods;
                    }

                    public void setGoods(GoodsBean goods) {
                        this.goods = goods;
                    }

                    public long getSysCreateTime() {
                        return sysCreateTime;
                    }

                    public void setSysCreateTime(long sysCreateTime) {
                        this.sysCreateTime = sysCreateTime;
                    }

                    public int getSysEditTime() {
                        return sysEditTime;
                    }

                    public void setSysEditTime(int sysEditTime) {
                        this.sysEditTime = sysEditTime;
                    }

                    public String getSysCreateUser() {
                        return sysCreateUser;
                    }

                    public void setSysCreateUser(String sysCreateUser) {
                        this.sysCreateUser = sysCreateUser;
                    }

                    public String getSysEditUser() {
                        return sysEditUser;
                    }

                    public void setSysEditUser(String sysEditUser) {
                        this.sysEditUser = sysEditUser;
                    }

                    public String getSysSpaceNo() {
                        return sysSpaceNo;
                    }

                    public void setSysSpaceNo(String sysSpaceNo) {
                        this.sysSpaceNo = sysSpaceNo;
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

                    public int getSysIdString() {
                        return sysIdString;
                    }

                    public void setSysIdString(int sysIdString) {
                        this.sysIdString = sysIdString;
                    }

                    public int getSysDataFrom() {
                        return sysDataFrom;
                    }

                    public void setSysDataFrom(int sysDataFrom) {
                        this.sysDataFrom = sysDataFrom;
                    }

                    public String getAttr1() {
                        return attr1;
                    }

                    public void setAttr1(String attr1) {
                        this.attr1 = attr1;
                    }

                    public String getAttr2() {
                        return attr2;
                    }

                    public void setAttr2(String attr2) {
                        this.attr2 = attr2;
                    }

                    public String getAttr3() {
                        return attr3;
                    }

                    public void setAttr3(String attr3) {
                        this.attr3 = attr3;
                    }

                    public String getAttr4() {
                        return attr4;
                    }

                    public void setAttr4(String attr4) {
                        this.attr4 = attr4;
                    }

                    public String getAttr5() {
                        return attr5;
                    }

                    public void setAttr5(String attr5) {
                        this.attr5 = attr5;
                    }

                }
            }
        }

        public static class TopPostionBean {


            private long spoStarttime;
            private long spoEndtime;
            private int poId;
            private String poName;
            private int poDefault;
            private String poStarttime;
            private String poEndtime;
            private String poPostion;
            private String poDesc;
            private long sysCreateTime;
            private int sysEditTime;
            private String sysCreateUser;
            private String sysEditUser;
            private String sysSpaceNo;
            private int sysVersionNo;
            private int sysIsEnable;
            private int sysIdString;
            private int sysDataFrom;
            private String attr1;
            private String attr2;
            private String attr3;
            private String attr4;
            private String attr5;
            private List<ImgsListBean> imgsList;

            public long getSpoStarttime() {
                return spoStarttime;
            }

            public void setSpoStarttime(long spoStarttime) {
                this.spoStarttime = spoStarttime;
            }

            public long getSpoEndtime() {
                return spoEndtime;
            }

            public void setSpoEndtime(long spoEndtime) {
                this.spoEndtime = spoEndtime;
            }

            public int getPoId() {
                return poId;
            }

            public void setPoId(int poId) {
                this.poId = poId;
            }

            public String getPoName() {
                return poName;
            }

            public void setPoName(String poName) {
                this.poName = poName;
            }

            public int getPoDefault() {
                return poDefault;
            }

            public void setPoDefault(int poDefault) {
                this.poDefault = poDefault;
            }

            public String getPoStarttime() {
                return poStarttime;
            }

            public void setPoStarttime(String poStarttime) {
                this.poStarttime = poStarttime;
            }

            public String getPoEndtime() {
                return poEndtime;
            }

            public void setPoEndtime(String poEndtime) {
                this.poEndtime = poEndtime;
            }

            public String getPoPostion() {
                return poPostion;
            }

            public void setPoPostion(String poPostion) {
                this.poPostion = poPostion;
            }

            public String getPoDesc() {
                return poDesc;
            }

            public void setPoDesc(String poDesc) {
                this.poDesc = poDesc;
            }

            public long getSysCreateTime() {
                return sysCreateTime;
            }

            public void setSysCreateTime(long sysCreateTime) {
                this.sysCreateTime = sysCreateTime;
            }

            public int getSysEditTime() {
                return sysEditTime;
            }

            public void setSysEditTime(int sysEditTime) {
                this.sysEditTime = sysEditTime;
            }

            public String getSysCreateUser() {
                return sysCreateUser;
            }

            public void setSysCreateUser(String sysCreateUser) {
                this.sysCreateUser = sysCreateUser;
            }

            public String getSysEditUser() {
                return sysEditUser;
            }

            public void setSysEditUser(String sysEditUser) {
                this.sysEditUser = sysEditUser;
            }

            public String getSysSpaceNo() {
                return sysSpaceNo;
            }

            public void setSysSpaceNo(String sysSpaceNo) {
                this.sysSpaceNo = sysSpaceNo;
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

            public int getSysIdString() {
                return sysIdString;
            }

            public void setSysIdString(int sysIdString) {
                this.sysIdString = sysIdString;
            }

            public int getSysDataFrom() {
                return sysDataFrom;
            }

            public void setSysDataFrom(int sysDataFrom) {
                this.sysDataFrom = sysDataFrom;
            }

            public String getAttr1() {
                return attr1;
            }

            public void setAttr1(String attr1) {
                this.attr1 = attr1;
            }

            public String getAttr2() {
                return attr2;
            }

            public void setAttr2(String attr2) {
                this.attr2 = attr2;
            }

            public String getAttr3() {
                return attr3;
            }

            public void setAttr3(String attr3) {
                this.attr3 = attr3;
            }

            public String getAttr4() {
                return attr4;
            }

            public void setAttr4(String attr4) {
                this.attr4 = attr4;
            }

            public String getAttr5() {
                return attr5;
            }

            public void setAttr5(String attr5) {
                this.attr5 = attr5;
            }

            public List<ImgsListBean> getImgsList() {
                return imgsList;
            }

            public void setImgsList(List<ImgsListBean> imgsList) {
                this.imgsList = imgsList;
            }

            public static class ImgsListBean {
                /**
                 * imgId : 1
                 * postionId : 9
                 * imgSort : 1
                 * imgPrimary : 0
                 * imgSrc : https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1535630468023&di=fca1100d0ba1b1c023ab570532a6d549&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F3%2F592927a508132.jpg
                 * imgUrlMini :
                 * imgUrlAndroid :
                 * imgUrlIos :
                 * imgUrlM :
                 * imgUrlpc :
                 * imgUrlDefault :
                 * sysCreateTime : 1535455636000
                 * sysEditTime : 0
                 * sysCreateUser :
                 * sysEditUser :
                 * sysSpaceNo :
                 * sysVersionNo : 0
                 * sysIsEnable : 1
                 * sysIdString : 0
                 * sysDataFrom : 0
                 * attr1 :
                 * attr2 :
                 * attr3 :
                 * attr4 :
                 * attr5 :
                 */

                private int imgId;
                private int postionId;
                private int imgSort;
                private int imgPrimary;
                private String imgSrc;
                private String imgUrlMini;
                private String imgUrlAndroid;
                private String imgUrlIos;
                private String imgUrlM;
                private String imgUrlpc;
                private String imgUrlDefault;
                private long sysCreateTime;
                private int sysEditTime;
                private String sysCreateUser;
                private String sysEditUser;
                private String sysSpaceNo;
                private int sysVersionNo;
                private int sysIsEnable;
                private int sysIdString;
                private int sysDataFrom;
                private String attr1;
                private String attr2;
                private String attr3;
                private String attr4;
                private String attr5;

                public int getImgId() {
                    return imgId;
                }

                public void setImgId(int imgId) {
                    this.imgId = imgId;
                }

                public int getPostionId() {
                    return postionId;
                }

                public void setPostionId(int postionId) {
                    this.postionId = postionId;
                }

                public int getImgSort() {
                    return imgSort;
                }

                public void setImgSort(int imgSort) {
                    this.imgSort = imgSort;
                }

                public int getImgPrimary() {
                    return imgPrimary;
                }

                public void setImgPrimary(int imgPrimary) {
                    this.imgPrimary = imgPrimary;
                }

                public String getImgSrc() {
                    return imgSrc;
                }

                public void setImgSrc(String imgSrc) {
                    this.imgSrc = imgSrc;
                }

                public String getImgUrlMini() {
                    return imgUrlMini;
                }

                public void setImgUrlMini(String imgUrlMini) {
                    this.imgUrlMini = imgUrlMini;
                }

                public String getImgUrlAndroid() {
                    return imgUrlAndroid;
                }

                public void setImgUrlAndroid(String imgUrlAndroid) {
                    this.imgUrlAndroid = imgUrlAndroid;
                }

                public String getImgUrlIos() {
                    return imgUrlIos;
                }

                public void setImgUrlIos(String imgUrlIos) {
                    this.imgUrlIos = imgUrlIos;
                }

                public String getImgUrlM() {
                    return imgUrlM;
                }

                public void setImgUrlM(String imgUrlM) {
                    this.imgUrlM = imgUrlM;
                }

                public String getImgUrlpc() {
                    return imgUrlpc;
                }

                public void setImgUrlpc(String imgUrlpc) {
                    this.imgUrlpc = imgUrlpc;
                }

                public String getImgUrlDefault() {
                    return imgUrlDefault;
                }

                public void setImgUrlDefault(String imgUrlDefault) {
                    this.imgUrlDefault = imgUrlDefault;
                }

                public long getSysCreateTime() {
                    return sysCreateTime;
                }

                public void setSysCreateTime(long sysCreateTime) {
                    this.sysCreateTime = sysCreateTime;
                }

                public int getSysEditTime() {
                    return sysEditTime;
                }

                public void setSysEditTime(int sysEditTime) {
                    this.sysEditTime = sysEditTime;
                }

                public String getSysCreateUser() {
                    return sysCreateUser;
                }

                public void setSysCreateUser(String sysCreateUser) {
                    this.sysCreateUser = sysCreateUser;
                }

                public String getSysEditUser() {
                    return sysEditUser;
                }

                public void setSysEditUser(String sysEditUser) {
                    this.sysEditUser = sysEditUser;
                }

                public String getSysSpaceNo() {
                    return sysSpaceNo;
                }

                public void setSysSpaceNo(String sysSpaceNo) {
                    this.sysSpaceNo = sysSpaceNo;
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

                public int getSysIdString() {
                    return sysIdString;
                }

                public void setSysIdString(int sysIdString) {
                    this.sysIdString = sysIdString;
                }

                public int getSysDataFrom() {
                    return sysDataFrom;
                }

                public void setSysDataFrom(int sysDataFrom) {
                    this.sysDataFrom = sysDataFrom;
                }

                public String getAttr1() {
                    return attr1;
                }

                public void setAttr1(String attr1) {
                    this.attr1 = attr1;
                }

                public String getAttr2() {
                    return attr2;
                }

                public void setAttr2(String attr2) {
                    this.attr2 = attr2;
                }

                public String getAttr3() {
                    return attr3;
                }

                public void setAttr3(String attr3) {
                    this.attr3 = attr3;
                }

                public String getAttr4() {
                    return attr4;
                }

                public void setAttr4(String attr4) {
                    this.attr4 = attr4;
                }

                public String getAttr5() {
                    return attr5;
                }

                public void setAttr5(String attr5) {
                    this.attr5 = attr5;
                }
            }
        }

        public static class CenterPostionBean {
            /**
             * spoStarttime : -62135596800
             * spoEndtime : -62135596800
             * poId : 1
             * poName : 测试广告位
             * poDefault : 1
             * poStarttime : 0001-01-01 00:00:00
             * poEndtime : 0001-01-01 00:00:00
             * poPostion : center
             * poDesc :
             * imgsList : [{"imgId":2,"postionId":1,"imgSort":2,"imgPrimary":0,"imgSrc":"http://p1.music.126.net/VWvhIXDT0nAO-skBKVLRgg==/109951163523560327.jpg","imgUrlMini":"","imgUrlAndroid":"","imgUrlIos":"","imgUrlM":"","imgUrlpc":"","imgUrlDefault":"","sysCreateTime":0,"sysEditTime":0,"sysCreateUser":"","sysEditUser":"","sysSpaceNo":"","sysVersionNo":0,"sysIsEnable":1,"sysIdString":0,"sysDataFrom":0,"attr1":"","attr2":"","attr3":"","attr4":"","attr5":""},{"imgId":3,"postionId":1,"imgSort":3,"imgPrimary":0,"imgSrc":"http://p3.music.126.net/6DW9zp8Z9lhMlzeu638zEw==/109951163391421089.jpg","imgUrlMini":"","imgUrlAndroid":"","imgUrlIos":"","imgUrlM":"","imgUrlpc":"","imgUrlDefault":"","sysCreateTime":0,"sysEditTime":0,"sysCreateUser":"","sysEditUser":"","sysSpaceNo":"","sysVersionNo":0,"sysIsEnable":1,"sysIdString":0,"sysDataFrom":0,"attr1":"","attr2":"","attr3":"","attr4":"","attr5":""},{"imgId":5,"postionId":1,"imgSort":5,"imgPrimary":0,"imgSrc":"http://c.hiphotos.baidu.com/image/pic/item/8694a4c27d1ed21b3c778fdda06eddc451da3f4f.jpg","imgUrlMini":"","imgUrlAndroid":"","imgUrlIos":"","imgUrlM":"","imgUrlpc":"","imgUrlDefault":"","sysCreateTime":0,"sysEditTime":0,"sysCreateUser":"","sysEditUser":"","sysSpaceNo":"","sysVersionNo":0,"sysIsEnable":1,"sysIdString":0,"sysDataFrom":0,"attr1":"","attr2":"","attr3":"","attr4":"","attr5":""}]
             * sysCreateTime : 1535454817000
             * sysEditTime : 0
             * sysCreateUser :
             * sysEditUser :
             * sysSpaceNo :
             * sysVersionNo : 0
             * sysIsEnable : 1
             * sysIdString : 0
             * sysDataFrom : 0
             * attr1 :
             * attr2 :
             * attr3 :
             * attr4 :
             * attr5 :
             */

            private long spoStarttime;
            private long spoEndtime;
            private int poId;
            private String poName;
            private int poDefault;
            private String poStarttime;
            private String poEndtime;
            private String poPostion;
            private String poDesc;
            private long sysCreateTime;
            private int sysEditTime;
            private String sysCreateUser;
            private String sysEditUser;
            private String sysSpaceNo;
            private int sysVersionNo;
            private int sysIsEnable;
            private int sysIdString;
            private int sysDataFrom;
            private String attr1;
            private String attr2;
            private String attr3;
            private String attr4;
            private String attr5;
            private List<ImgsListBean> imgsList;

            public long getSpoStarttime() {
                return spoStarttime;
            }

            public void setSpoStarttime(long spoStarttime) {
                this.spoStarttime = spoStarttime;
            }

            public long getSpoEndtime() {
                return spoEndtime;
            }

            public void setSpoEndtime(long spoEndtime) {
                this.spoEndtime = spoEndtime;
            }

            public int getPoId() {
                return poId;
            }

            public void setPoId(int poId) {
                this.poId = poId;
            }

            public String getPoName() {
                return poName;
            }

            public void setPoName(String poName) {
                this.poName = poName;
            }

            public int getPoDefault() {
                return poDefault;
            }

            public void setPoDefault(int poDefault) {
                this.poDefault = poDefault;
            }

            public String getPoStarttime() {
                return poStarttime;
            }

            public void setPoStarttime(String poStarttime) {
                this.poStarttime = poStarttime;
            }

            public String getPoEndtime() {
                return poEndtime;
            }

            public void setPoEndtime(String poEndtime) {
                this.poEndtime = poEndtime;
            }

            public String getPoPostion() {
                return poPostion;
            }

            public void setPoPostion(String poPostion) {
                this.poPostion = poPostion;
            }

            public String getPoDesc() {
                return poDesc;
            }

            public void setPoDesc(String poDesc) {
                this.poDesc = poDesc;
            }

            public long getSysCreateTime() {
                return sysCreateTime;
            }

            public void setSysCreateTime(long sysCreateTime) {
                this.sysCreateTime = sysCreateTime;
            }

            public int getSysEditTime() {
                return sysEditTime;
            }

            public void setSysEditTime(int sysEditTime) {
                this.sysEditTime = sysEditTime;
            }

            public String getSysCreateUser() {
                return sysCreateUser;
            }

            public void setSysCreateUser(String sysCreateUser) {
                this.sysCreateUser = sysCreateUser;
            }

            public String getSysEditUser() {
                return sysEditUser;
            }

            public void setSysEditUser(String sysEditUser) {
                this.sysEditUser = sysEditUser;
            }

            public String getSysSpaceNo() {
                return sysSpaceNo;
            }

            public void setSysSpaceNo(String sysSpaceNo) {
                this.sysSpaceNo = sysSpaceNo;
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

            public int getSysIdString() {
                return sysIdString;
            }

            public void setSysIdString(int sysIdString) {
                this.sysIdString = sysIdString;
            }

            public int getSysDataFrom() {
                return sysDataFrom;
            }

            public void setSysDataFrom(int sysDataFrom) {
                this.sysDataFrom = sysDataFrom;
            }

            public String getAttr1() {
                return attr1;
            }

            public void setAttr1(String attr1) {
                this.attr1 = attr1;
            }

            public String getAttr2() {
                return attr2;
            }

            public void setAttr2(String attr2) {
                this.attr2 = attr2;
            }

            public String getAttr3() {
                return attr3;
            }

            public void setAttr3(String attr3) {
                this.attr3 = attr3;
            }

            public String getAttr4() {
                return attr4;
            }

            public void setAttr4(String attr4) {
                this.attr4 = attr4;
            }

            public String getAttr5() {
                return attr5;
            }

            public void setAttr5(String attr5) {
                this.attr5 = attr5;
            }

            public List<ImgsListBean> getImgsList() {
                return imgsList;
            }

            public void setImgsList(List<ImgsListBean> imgsList) {
                this.imgsList = imgsList;
            }

            public static class ImgsListBean {
                /**
                 * imgId : 2
                 * postionId : 1
                 * imgSort : 2
                 * imgPrimary : 0
                 * imgSrc : http://p1.music.126.net/VWvhIXDT0nAO-skBKVLRgg==/109951163523560327.jpg
                 * imgUrlMini :
                 * imgUrlAndroid :
                 * imgUrlIos :
                 * imgUrlM :
                 * imgUrlpc :
                 * imgUrlDefault :
                 * sysCreateTime : 0
                 * sysEditTime : 0
                 * sysCreateUser :
                 * sysEditUser :
                 * sysSpaceNo :
                 * sysVersionNo : 0
                 * sysIsEnable : 1
                 * sysIdString : 0
                 * sysDataFrom : 0
                 * attr1 :
                 * attr2 :
                 * attr3 :
                 * attr4 :
                 * attr5 :
                 */

                private int imgId;
                private int postionId;
                private int imgSort;
                private int imgPrimary;
                private String imgSrc;
                private String imgUrlMini;
                private String imgUrlAndroid;
                private String imgUrlIos;
                private String imgUrlM;
                private String imgUrlpc;
                private String imgUrlDefault;
                private long sysCreateTime;
                private long sysEditTime;
                private String sysCreateUser;
                private String sysEditUser;
                private String sysSpaceNo;
                private int sysVersionNo;
                private int sysIsEnable;
                private int sysIdString;
                private int sysDataFrom;
                private String attr1;
                private String attr2;
                private String attr3;
                private String attr4;
                private String attr5;

                public int getImgId() {
                    return imgId;
                }

                public void setImgId(int imgId) {
                    this.imgId = imgId;
                }

                public int getPostionId() {
                    return postionId;
                }

                public void setPostionId(int postionId) {
                    this.postionId = postionId;
                }

                public int getImgSort() {
                    return imgSort;
                }

                public void setImgSort(int imgSort) {
                    this.imgSort = imgSort;
                }

                public int getImgPrimary() {
                    return imgPrimary;
                }

                public void setImgPrimary(int imgPrimary) {
                    this.imgPrimary = imgPrimary;
                }

                public String getImgSrc() {
                    return imgSrc;
                }

                public void setImgSrc(String imgSrc) {
                    this.imgSrc = imgSrc;
                }

                public String getImgUrlMini() {
                    return imgUrlMini;
                }

                public void setImgUrlMini(String imgUrlMini) {
                    this.imgUrlMini = imgUrlMini;
                }

                public String getImgUrlAndroid() {
                    return imgUrlAndroid;
                }

                public void setImgUrlAndroid(String imgUrlAndroid) {
                    this.imgUrlAndroid = imgUrlAndroid;
                }

                public String getImgUrlIos() {
                    return imgUrlIos;
                }

                public void setImgUrlIos(String imgUrlIos) {
                    this.imgUrlIos = imgUrlIos;
                }

                public String getImgUrlM() {
                    return imgUrlM;
                }

                public void setImgUrlM(String imgUrlM) {
                    this.imgUrlM = imgUrlM;
                }

                public String getImgUrlpc() {
                    return imgUrlpc;
                }

                public void setImgUrlpc(String imgUrlpc) {
                    this.imgUrlpc = imgUrlpc;
                }

                public String getImgUrlDefault() {
                    return imgUrlDefault;
                }

                public void setImgUrlDefault(String imgUrlDefault) {
                    this.imgUrlDefault = imgUrlDefault;
                }

                public long getSysCreateTime() {
                    return sysCreateTime;
                }

                public void setSysCreateTime(long sysCreateTime) {
                    this.sysCreateTime = sysCreateTime;
                }

                public long getSysEditTime() {
                    return sysEditTime;
                }

                public void setSysEditTime(long sysEditTime) {
                    this.sysEditTime = sysEditTime;
                }

                public String getSysCreateUser() {
                    return sysCreateUser;
                }

                public void setSysCreateUser(String sysCreateUser) {
                    this.sysCreateUser = sysCreateUser;
                }

                public String getSysEditUser() {
                    return sysEditUser;
                }

                public void setSysEditUser(String sysEditUser) {
                    this.sysEditUser = sysEditUser;
                }

                public String getSysSpaceNo() {
                    return sysSpaceNo;
                }

                public void setSysSpaceNo(String sysSpaceNo) {
                    this.sysSpaceNo = sysSpaceNo;
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

                public int getSysIdString() {
                    return sysIdString;
                }

                public void setSysIdString(int sysIdString) {
                    this.sysIdString = sysIdString;
                }

                public int getSysDataFrom() {
                    return sysDataFrom;
                }

                public void setSysDataFrom(int sysDataFrom) {
                    this.sysDataFrom = sysDataFrom;
                }

                public String getAttr1() {
                    return attr1;
                }

                public void setAttr1(String attr1) {
                    this.attr1 = attr1;
                }

                public String getAttr2() {
                    return attr2;
                }

                public void setAttr2(String attr2) {
                    this.attr2 = attr2;
                }

                public String getAttr3() {
                    return attr3;
                }

                public void setAttr3(String attr3) {
                    this.attr3 = attr3;
                }

                public String getAttr4() {
                    return attr4;
                }

                public void setAttr4(String attr4) {
                    this.attr4 = attr4;
                }

                public String getAttr5() {
                    return attr5;
                }

                public void setAttr5(String attr5) {
                    this.attr5 = attr5;
                }
            }
        }



    }
}
