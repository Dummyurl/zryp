package com.shunmai.zryp.network.service;

import com.shunmai.zryp.bean.TResponse;
import com.shunmai.zryp.bean.goods.CategoryBean;
import com.shunmai.zryp.bean.goods.GoodsDetailBean;
import com.shunmai.zryp.bean.goods.GoodsHotWordBean;
import com.shunmai.zryp.bean.goods.GoodsListBean;
import com.shunmai.zryp.bean.home.HomePageBean;
import com.shunmai.zryp.bean.userinfo.AddressListBean;

import java.util.Map;
import java.util.Observer;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @GET("/home")
    Observable<HomePageBean> HomePageInfo();


    @GET("/goods/QueryGoodsEntityByCatlaog")
    Observable<GoodsListBean> GoodsList(@Query("page") int page, @Query("catlaogMobileId") long sysid, @Query("sortRule") int sort);


    @GET("/Category")
    Observable<CategoryBean> CategoryList();

    @GET("/goods/QueryGoodsEntityByCatlaog")
    Observable<GoodsListBean> GoodsList(@Query("page") int page, @Query("sortRule") int sort, @Query("prType") int prType);

    @GET("/goods/GetHotSearchGoods")
    Observable<GoodsHotWordBean> GetSearchKeyWord();

    @GET("/goods")
    Observable<GoodsDetailBean> GetGoodsDetail(@Query("id") long id);

    @GET("/address")
    Observable<AddressListBean> GetAddressList(@Query("userid") int userId, @Query("page") int page, @Query("pageSize") int pageSize);

    @HTTP(method = "DELETE", path = "/address", hasBody = true)
    Observable<TResponse<String>> DeleteAddress(@Query("id") int id);

    @POST("/address/default")
    Observable<TResponse<String>> ChangeDefaultAddress(@Body Map<String, Integer> map);

    @Headers({"Content-Type: application/json"})
    @PUT("/address")
    Observable<TResponse<String>> AddAddress(@Body Map<String, Object> data);
}