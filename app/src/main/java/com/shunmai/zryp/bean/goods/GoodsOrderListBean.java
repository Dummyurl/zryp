package com.shunmai.zryp.bean.goods;

/**
 * Created by yushengyang.
 * Date: 2018/11/5.
 */

public class GoodsOrderListBean {
    /**
     id	int	订单自动编号
     bid int	goodsId
     successtime string	拼团成功时间
     title	string	商品名称
     mainpic string	商品主图
     geshu int	购买数量
     oid string	订单号
     statename	string	订单状态
     state	int	订单状态码 state 0 未支付  1 拼团中  2 拼团成功 3 已退款 4 打单中 5 已发送 6 已签收 7 未中奖订单(等待退款处理)
     cost	decimal	价格
     postcost	decimal	快递费用
     addrid	int	收货地址Id
     totalcost	decimal	总花费
     **/
    /**
     * id : 14590
     * bid : 33383
     * successtime : 2018-11-05 10:13:00
     * title : 3个装 满天星蝴蝶结布艺遥控器套 电视机空调遥控器保护套防尘套
     * mainpic : https://image-shop.chuchujie.com/culiu.cdn/image/201808/14/52fbb55a8b42255b2abbc32cfa724da6.jpeg
     * geshu : 5
     * oid : 323036779944
     * type : 0
     * show : true
     * statename : 未付款
     * state : 0
     * cost : 9.9
     * postcost : 0
     * ordertype : 2
     * brandtype : 2
     * addrid : 10858
     * totalcost : 49.5
     */

    private int id;
    private long bid;
    private String successtime;
    private String title;
    private String mainpic;
    private int geshu;
    private long oid;
    private int type;
    private boolean show;
    private String statename;
    private int state;
    private double cost;
    private double postcost;
    private int ordertype;
    private int brandtype;
    private int addrid;
    private double totalcost;
    private int skuid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBid() {
        return bid;
    }

    public void setBid(long bid) {
        this.bid = bid;
    }

    public String getSuccesstime() {
        return successtime;
    }

    public void setSuccesstime(String successtime) {
        this.successtime = successtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMainpic() {
        return mainpic;
    }

    public void setMainpic(String mainpic) {
        this.mainpic = mainpic;
    }

    public int getGeshu() {
        return geshu;
    }

    public void setGeshu(int geshu) {
        this.geshu = geshu;
    }

    public long getOid() {
        return oid;
    }

    public void setOid(long oid) {
        this.oid = oid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPostcost() {
        return postcost;
    }

    public void setPostcost(double postcost) {
        this.postcost = postcost;
    }

    public int getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(int ordertype) {
        this.ordertype = ordertype;
    }

    public int getBrandtype() {
        return brandtype;
    }

    public void setBrandtype(int brandtype) {
        this.brandtype = brandtype;
    }

    public int getAddrid() {
        return addrid;
    }

    public void setAddrid(int addrid) {
        this.addrid = addrid;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }

    public int getSkuid() {
        return skuid;
    }

    public void setSkuid(int skuid) {
        this.skuid = skuid;
    }
}
