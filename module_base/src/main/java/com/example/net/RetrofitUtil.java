package com.example.net;

import android.content.Context;

import com.example.common.CommonResource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * retrofit网络链接工具类
 */
public class RetrofitUtil {
    private static RetrofitUtil mInstance;

    /**
     * 请求失败重连次数
     */
    private int RETRY_COUNT = 5;

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

    public ApiService getApi(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
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
