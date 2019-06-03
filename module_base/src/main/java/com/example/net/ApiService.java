package com.example.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ApiService {

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postData(@Url String url, @FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postDataWithout(@Url String url);


    @GET
    Observable<ResponseBody> getData(@Url String url, @QueryMap Map<String,String> map);

    @GET
    Observable<ResponseBody> getDataWithout(@Url String url);

    //多用户商城---新品推荐
    @GET
    Observable<ResponseBody> newCommend(@Url String url, @Query("current") int current, @Query("saleDesc") int saleDesc);

    //多用户商城---热销商品
    @GET
    Observable<ResponseBody> hotSale(@Url String url, @Query("current") int current, @Query("saleDesc") int newStatus);

    //把微信code传给后台---获取商品详情
    @GET
    Observable<ResponseBody> sendCode(@Url String url, @Query("code") String code);

    //获取商品详情
    @GET("/rest/goods/{id}")
    Observable<ResponseBody> goodsDetail(@Path("id") String id);

    //多用户商城---搜索
    @GET
    Observable<ResponseBody> search(@Url String url, @Query("current") int current, @Query("searchInfo") String search);
}
