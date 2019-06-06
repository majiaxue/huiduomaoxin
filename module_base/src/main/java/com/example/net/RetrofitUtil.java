package com.example.net;

import android.content.Context;

import com.example.common.CommonResource;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit网络链接工具类
 */
public class RetrofitUtil {
    private static RetrofitUtil mInstance;
    private volatile ApiService mApiService;
    private volatile ApiService mApiService2;
    private volatile ApiService mApiService3;
    private volatile ApiService mApiService4;
    /**
     * 请求失败重连次数
     */
    private int RETRY_COUNT = 0;

    private RetrofitUtil() {
    }

    /**
     * 通过单例模式创建对象
     *
     * @return
     */
    public static RetrofitUtil getInstance() {
        if (mInstance == null) {
            synchronized (RetrofitUtil.class) {
                if (mInstance == null) {
                    mInstance = new RetrofitUtil();
                }
            }
        }
        return mInstance;
    }

    public ApiService getApi1(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonResource.BASEURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        if (mApiService == null) {
            synchronized (ApiService.class) {
                if (mApiService == null) {
                    mApiService = retrofit.create(ApiService.class);
                }
            }
        }
        return mApiService;
    }

    public ApiService getApi2(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonResource.BASEURL2)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        if (mApiService2 == null) {
            synchronized (ApiService.class) {
                if (mApiService2 == null) {
                    mApiService2 = retrofit.create(ApiService.class);
                }
            }
        }
        return mApiService2;
    }

    public ApiService getApi4(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CommonResource.BASEURL4)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        if (mApiService4 == null) {
            synchronized (ApiService.class) {
                if (mApiService4 == null) {
                    mApiService4 = retrofit.create(ApiService.class);
                }
            }
        }
        return mApiService4;
    }

    /**
     * 设置订阅 和 所在的线程环境
     */
    public <T> void toSubscribe(Observable<T> o, DisposableObserver<T> s) {

        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)//请求失败重连次数
                .subscribe(s);

    }

//    private OkHttpClient getHttpClient(final Context context) {
//        final String token = SPUtil.getToken();
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request build = null;
//                        try {
//                            build = chain.request().newBuilder()
//                                    .addHeader("Authorization", token)
//                                    .build();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        return chain.proceed(build);
//                    }
//                }).build();
//        return okHttpClient;
//    }
}
