package com.shunmai.zryp.network.service;

import com.shunmai.zryp.bean.GoodsSecKillBean;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.bean.addrbean.RegionBean;
import com.shunmai.zryp.bean.goods.CategoryBean;
import com.shunmai.zryp.bean.goods.GoodsBean;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.bean.goods.GoodsListBean;
import com.shunmai.zryp.bean.goods.GoodsOrderListBean;
import com.shunmai.zryp.bean.goods.GoodsPromotionBean;
import com.shunmai.zryp.bean.goods.OderInfoBean;
import com.shunmai.zryp.bean.goods.PromotionGoodsBean;
import com.shunmai.zryp.bean.home.CheckVersionBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.bean.underling.UnderlingBean;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.bean.userinfo.CollectBean;
import com.shunmai.zryp.bean.userinfo.FootprintBean;
import com.shunmai.zryp.bean.userinfo.Response_Wechat;
import com.shunmai.zryp.bean.userinfo.Response_WechatUserInfo;
import com.ysy.commonlib.base.TResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 *
 */

public interface HttpService {

//    @Headers({"APP-Space:B0CD0050CF0BF01B",
//            "App-Type:2", "App-Uid:0"})
//    @GET("http://47.94.145.183:8780/Home")
//    Observable<HomePageBean> HomePageInfo();

    /**
     * 获取首页数据
     *
     * @return
     */
    @GET("home/index")
    Observable<TResponse<HomePageBean>> HomePageInfo();

    /**
     * 获取商品列表(首页活动商品)
     *
     * @param page
     * @param sysid
     * @param sort
     * @return
     */
    @GET("goods/QueryGoodsEntityByCatlaog")
    Observable<GoodsListBean> GoodsList(@Query("page") int page, @Query("catlaogMobileId") long sysid, @Query("sortRule") int sort);

    /**
     * 分类
     *
     * @return
     */
    @GET("Category")
    Observable<CategoryBean> CategoryList();

    /**
     * 获取商品列表(分类页进入)
     *
     * @param page
     * @param sort
     * @param prType
     * @return
     */
    @GET("goods/QueryGoodsEntityByCatlaog")
    Observable<GoodsListBean> GoodsList(@Query("page") int page, @Query("sortRule") int sort, @Query("prType") int prType);

    /**
     * 获取搜索页关键词
     *
     * @return
     */
    @GET("goods/GetHotSearchGoods")
    Observable<GoodsHotWordBean> GetSearchKeyWord();

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    @GET("goods")
    Observable<GoodsDetailBean> GetGoodsDetail(@Query("id") long id);

    /**
     * 获取地址列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @param isOutAddress 地址所属平台(1：自营或楚楚街用的地址；2：京东收货地址)
     * @return
     */
    @GET("address")
    Observable<AddressListBean> GetAddressList(@Query("userid") int userId, @Query("page") int page, @Query("pageSize") int pageSize, @Query("isOutAddress") int isOutAddress);

    /**
     * 删除地址
     *
     * @param id
     * @return
     */
    @GET("address/delete")
    Observable<TResponse<String>> DeleteAddress(@Query("id") int id);

    /**
     * 修改默认地址
     *
     * @param map
     * @return
     */
    @POST("address/default")
    Observable<TResponse<String>> ChangeDefaultAddress(@Body Map<String, Integer> map);

    /**
     * 添加地址
     *
     * @param data
     * @return
     */
    @PUT("address/put")
    Observable<TResponse<String>> AddAddress(@Body Map<String, Object> data);

    /**
     * 修改地址
     *
     * @param data
     * @return
     */
    @POST("address/p")
    Observable<TResponse<String>> ChangeAddress(@Body Map<String, Object> data);

    /**
     * 获取个人中心数据
     *
     * @return
     */
    @GET("user/index")
    Observable<TResponse<UserInfoBean>> GetUserInfo();

    /**
     * 获取下属列表
     *
     * @param userType
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GET("user/GetSubUserList")
    Observable<UnderlingBean> GetUnderlingList(@Query("userType") int userType, @Query("userId") int userId, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize);

    /**
     * 保存我的足迹
     *
     * @param sysIdString
     * @return
     */
    @POST("user/SaveMyFootprint")
    Observable<TResponse<String>> SaveMyFootprint(@Body() HashMap sysIdString);

    /**
     * 获取我的足迹列表
     *
     * @return
     */
    @GET("user/GetMyFootprintList")
    Observable<FootprintBean> GetMyFootprintList();

    /**
     * 获取微信登录token
     *
     * @param params
     * @return
     */
    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Observable<Response_Wechat> WeChatToken(@QueryMap Map<String, String> params);

    /**
     * 获取微信登录userInfo
     *
     * @param params
     * @return
     */
    @GET("https://api.weixin.qq.com/sns/userinfo")
    Observable<Response_WechatUserInfo> WeChatUserInfo(@QueryMap Map<String, String> params);

    /**
     * 微信登录
     *
     * @param map
     * @return
     */
    @POST("user/WeChat")
    Observable<TResponse<UserInfoBean>> WeChatLogin(@Body() HashMap<String, String> map);

    /**
     * 获取验证码
     *
     * @param type
     * @param phoneNum
     * @return
     */
    @GET("sms/send")
    Observable<TResponse<String>> GetSmsCode(@Query("type") int type, @Query("phone") String phoneNum);

    /**
     * 微信登录
     *
     * @param map
     * @return
     */
    @PUT("user/Binding")
    Observable<TResponse<UserInfoBean>> bindPhone(@Body() HashMap<String, Object> map, @Query("VerifyCode") String code);

    /**
     * 用户身份验证
     *
     * @param map
     * @param code
     * @return
     */
    @POST("user/Authentication")
    Observable<TResponse<String>> ApproveUser(@Body() HashMap<String, String> map, @Query("VerifyCode") String code);

    /**
     * 设置密码
     *
     * @param map
     * @return
     */
    @POST("user/password")
    Observable<TResponse<UserInfoBean>> SetNewPassword(@Body() HashMap<String, Object> map);

    /**
     * 用户注册
     *
     * @param map
     * @return
     */
    @PUT("user")
    Observable<TResponse<UserInfoBean>> Register(@Body() HashMap<String, String> map, @Query("VerifyCode") String code);

    /**
     * 用户登录
     *
     * @param map
     * @return
     */
    @POST("user/login")
    Observable<TResponse<UserInfoBean>> Login(@Body() HashMap<String, String> map);

    /**
     * 搜索商品
     *
     * @param page
     * @param keywords
     * @param goodsName
     * @param sortRule
     * @return
     */
    @GET("goods/SearchGoods")
    Observable<GoodsListBean> SearchGoods(@Query("page") int page, @Query("keywords") String keywords, @Query("goodsName") String goodsName, @Query("sortRule") int sortRule);

    /**
     * 获取商品邮费
     *
     * @param goods_id
     * @param sku_id
     * @param geshu
     * @return
     */
    @Headers({"Domain-Name: miniProgram"})
    @GET("NewOrders/GetPostCost")
    Observable<TResponse<Double>> GetPostCost(@Query("goods_id") long goods_id, @Query("sku_id") int sku_id, @Query("geshu") int geshu);

    /**
     * 获取用户默认地址
     *
     * @return
     */
    @GET("address/default")
    Observable<TResponse<AddressListBean.DataBean>> GetDefaultAddress(@Query("isOutAddress") int isOutAddress);

    /**
     * 提交订单
     *
     * @param map
     * @param addrid 地址id
     * @param num    购买数量
     * @param type   购买类型0：单独购买 1：拼团
     * @return
     */
    @Headers({"Domain-Name: miniProgram"})
    @POST("/NewOrders/submit")
    Observable<TResponse<OderInfoBean>> GetOderInfo(@Body() HashMap<String, Object> map, @Query("addrid") int addrid, @Query("num") int num, @Query("type") int type);

    /**
     * 获取订单列表
     *
     * @param tab  0 全部  1 待付款  2 待发货  3 已发货  4 已完成
     * @param page
     * @return
     */
    @Headers({"Domain-Name: miniProgram"})
    @GET("/Orders/MyAppOrders")
    Observable<TResponse<List<GoodsOrderListBean>>> GetOrderList(@Query("tab") int tab, @Query("page") int page);

    /**
     * 确认收货
     *
     * @param oid
     * @return
     */
    @Headers({"Domain-Name: miniProgram"})
    @GET("/Orders/Delivery")
    Observable<TResponse<String>> OrdersDelivery(@Query("oid") long oid);

    /**
     * 根据adrId查询地址
     *
     * @param adrId
     * @return
     */
    @GET("Address/GetSingle")
    Observable<TResponse<AddressListBean.DataBean>> GetAddressById(@Query("addressId") int adrId);

    /**
     * 获取版本信息
     *
     * @param vDevice
     * @return
     */
    @POST("Version/getlatestversion")
    Observable<TResponse<CheckVersionBean>> CheckVersion(@Query("vDevice") String vDevice);

    /**
     * 根据上级区域ID获取子级区域集合
     *
     * @param map 1.parentId
     *            上级区域ID，获取省份集合时，传入的值为100
     *            2.regionType
     *            行政区划所属平台类型，1：自营和楚楚街使用；2：京东使用
     * @return
     */
    @POST("address/GetRegionList")
    Observable<TResponse<List<RegionBean>>> GetRegionList(@Body() HashMap<String, String> map);

    /**
     * 获取猜你喜欢商品
     *
     * @param start
     * @param limit
     * @return
     */
    @GET("home/GuessYouLike")
    Observable<TResponse<List<GoodsBean>>> GuessYouLike(@Query("start") int start, @Query("limit") int limit);

    /**
     * 获取用户收藏列表
     *
     * @param page
     * @param pageSize
     * @param userId
     * @return
     */
    @GET("GoodsCollection/GetGoodsList")
    Observable<TResponse<List<CollectBean>>> GetCollectGoods(@Query("pageNum") int page, @Query("pageSize") int pageSize, @Query("userId") int userId);

    /**
     * 用户取消收藏
     *
     * @param collectId
     * @return
     */
    @GET("GoodsCollection/GetGoodsCancel")
    Observable<TResponse<String>> DeleteCollectItem(@Query("collectId") int collectId);

    /**
     * 用户收藏商品
     *
     * @param goodsId
     * @param skuId
     * @param userId
     * @return
     */
    @GET("GoodsCollection/GetGoodsStart")
    Observable<TResponse<String>> CollectGoodsItem(@Query("goodsId") int goodsId, @Query("skuId") int skuId, @Query("userId") int userId);

    /**
     * 获取爆赚积分活动详情
     *
     * @param prId 活动id
     * @return
     */
    @GET("promotion/detail/nonsecondkill")
    Observable<TResponse<GoodsPromotionBean>> GetScorePromotion(@Query("prId") int prId);

    /**
     * 获取商品秒杀信息
     *
     * @return
     */
    @GET("goods/seckill")
    Observable<TResponse<List<GoodsSecKillBean>>> GetSeckill();

    /**
     * 根据活动Id获取商品
     *
     * @param prId
     * @param isPriceType
     * @param pageNum
     * @param pageSize
     * @param dataStatus
     * @return
     */
    @GET("goods/getactivitygoods")
    Observable<TResponse<List<PromotionGoodsBean>>> GetActivityGoods(@Query("prId") int prId, @Query("isPriceType") int isPriceType, @Query("pageNum") int pageNum, @Query("pageSize") int pageSize, @Query("dataStatus") int dataStatus);

    /**
     * 获取智融优品活动页面
     *
     * @param prId
     * @return
     */
    @GET("goods/preference")
    Observable<TResponse<GoodsPromotionBean>> GetPrePro(@Query("prId") int prId);

    /**
     * 获取顶部轮播图
     *
     * @return
     */
    @Headers({"Domain-Name: miniProgram"})
    @GET("/index/main")
    Observable<TResponse<String>> GetMainAd();

}