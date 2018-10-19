package com.shunmai.zryp.network.service;

import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.UserInfoBean;
import com.shunmai.zryp.bean.goods.CategoryBean;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.bean.goods.GoodsListBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.bean.underling.UnderlingBean;
import com.shunmai.zryp.bean.userinfo.AddressListBean;
import com.shunmai.zryp.bean.userinfo.FootprintBean;
import com.shunmai.zryp.bean.userinfo.Response_Wechat;
import com.shunmai.zryp.bean.userinfo.Response_WechatUserInfo;
import com.shunmai.zryp.bean.userinfo.WechatLoginBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by xianglanzuo on 2018/1/2.
 */

public interface HttpService {

//    @Headers({"APP-Space:B0CD0050CF0BF01B",
//            "App-Type:2", "App-Uid:0"})
//    @GET("http://47.94.145.183:8780/Home")
//    Observable<HomePageBean> HomePageInfo();

    /**
     * 获取首页数据
     * @return
     */
    @GET("/home")
    Observable<HomePageBean> HomePageInfo();

    /**
     * 获取商品列表(首页活动商品)
     *
     * @param page
     * @param sysid
     * @param sort
     * @return
     */
    @GET("/goods/QueryGoodsEntityByCatlaog")
    Observable<GoodsListBean> GoodsList(@Query("page") int page, @Query("catlaogMobileId") long sysid, @Query("sortRule") int sort);

    /**
     * 分类
     *
     * @return
     */
    @GET("/Category")
    Observable<CategoryBean> CategoryList();

    /**
     * 获取商品列表(分类页进入)
     *
     * @param page
     * @param sort
     * @param prType
     * @return
     */
    @GET("/goods/QueryGoodsEntityByCatlaog")
    Observable<GoodsListBean> GoodsList(@Query("page") int page, @Query("sortRule") int sort, @Query("prType") int prType);

    /**
     * 获取搜索页关键词
     * @return
     */
    @GET("/goods/GetHotSearchGoods")
    Observable<GoodsHotWordBean> GetSearchKeyWord();

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    @GET("/goods")
    Observable<GoodsDetailBean> GetGoodsDetail(@Query("id") long id);

    /**
     * 获取地址列表
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GET("/address")
    Observable<AddressListBean> GetAddressList(@Query("userid") int userId, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 删除地址
     * @param id
     * @return
     */
    @HTTP(method = "DELETE", path = "/address", hasBody = true)
    Observable<TResponse<String>> DeleteAddress(@Query("id") int id);

    /**
     * 修改默认地址
     * @param map
     * @return
     */
    @POST("/address/default")
    Observable<TResponse<String>> ChangeDefaultAddress(@Body Map<String, Integer> map);

    /**
     * 添加地址
     * @param data
     * @return
     */
    @Headers({"Content-Type: application/json"})
    @PUT("/address")
    Observable<TResponse<String>> AddAddress(@Body Map<String, Object> data);

    /**
     * 获取个人中心数据
     * @return
     */
    @GET("/user/index")
    Observable<TResponse<UserInfoBean>> GetUserInfo();

    /**
     * 获取下属列表
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
     * @param sysIdString
     * @return
     */
    @POST("user/SaveMyFootprint")
    Observable<TResponse<String>> SaveMyFootprint(@Body() HashMap sysIdString);

    /**
     * 获取我的足迹列表
     * @return
     */
    @GET("user/GetMyFootprintList")
    Observable<FootprintBean> GetMyFootprintList();

    /**
     * 获取微信登录token
     * @param params
     * @return
     */
    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Observable<Response_Wechat> WeChatToken(@QueryMap Map<String, String> params);

    /**
     * 获取微信登录userInfo
     * @param params
     * @return
     */
    @GET("https://api.weixin.qq.com/sns/userinfo")
    Observable<Response_WechatUserInfo> WeChatUserInfo(@QueryMap Map<String, String> params);

    /**
     * 微信登录
     * @param map
     * @return
     */
    @POST("/user/WeChat")
    Observable<TResponse<UserInfoBean>> WeChatLogin(@Body()HashMap<String,String> map);

    /**
     * 获取验证码
     * @param type
     * @param phoneNum
     * @return
     */
    @GET("/sms/send")
    Observable<TResponse<String>> GetSmsCode(@Query("type")int type,@Query("phone")String phoneNum);

    /**
     * 微信登录
     * @param map
     * @return
     */
    @PUT("/user/Binding")
    Observable<TResponse<UserInfoBean>> bindPhone(@Body()HashMap<String,String> map,@Query("VerifyCode")String code);

    /**
     * 用户身份验证
     * @param map
     * @param code
     * @return
     */
    @POST("/user/Authentication")
    Observable<TResponse<String>> ApproveUser(@Body()HashMap<String,String> map,@Query("VerifyCode")String code);

    /**
     * 用户身份验证
     * @param map
     * @return
     */
    @POST("/user/password")
    Observable<TResponse<UserInfoBean>> SetNewPassword(@Body()HashMap<String,String> map);

    /**
     * 用户注册
     * @param map
     * @return
     */
    @PUT("/user")
    Observable<TResponse<UserInfoBean>> Register(@Body()HashMap<String,String> map,@Query("VerifyCode")String code);

    /**
     * 用户登录
     * @param map
     * @return
     */
    @POST("/user/login")
    Observable<TResponse<UserInfoBean>> Login(@Body()HashMap<String,String> map);

}