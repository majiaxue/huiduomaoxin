package com.example.net;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


public interface ApiService {

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postData(@Url String url, @FieldMap Map map);

    @FormUrlEncoded
    @POST
    Observable<ResponseBody> postDataWithout(@Url String url);

    @GET
    Observable<ResponseBody> getData(@Url String url, @QueryMap Map map);

    @GET
    Observable<ResponseBody> getDataWithout(@Url String url);
}
