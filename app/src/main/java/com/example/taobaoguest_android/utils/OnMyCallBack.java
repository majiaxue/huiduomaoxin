package com.example.taobaoguest_android.utils;

import com.example.taobaoguest_android.base.BaseEntity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class OnMyCallBack extends DisposableObserver<ResponseBody> {
    private OnDataListener listener;
    private static final String SUCCESS = "";
    private static final String ERROR = "";

    /**
     * @param listener 回调监听
     */
    public OnMyCallBack(OnDataListener listener) {
        this.listener = listener;
    }

    /**
     * code为0000时，调用onSuccess方法返回数据和message
     * 否则调用onError方法返回code和message
     *
     * @param responseBody
     */
    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            BaseEntity baseEntity = new Gson().fromJson(string, new TypeToken<BaseEntity>() {
            }.getType());
            if (SUCCESS.equals(baseEntity.getCode())) {
//                listener.onSuccess(AESUtil.decrypt((String) baseEntity.getData()), baseEntity.getMsg());
                listener.onSuccess(baseEntity.getData(), baseEntity.getMsg());
            } else {
                listener.onError(baseEntity.getCode(), baseEntity.getMsg());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对错误进行统一处理
     */
    @Override
    public void onError(Throwable e) {
        try {

            if (e instanceof SocketTimeoutException) {//请求超时
            } else if (e instanceof ConnectException) {//网络连接超时
                listener.onError(ERROR, "网络连接超时");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                listener.onError(ERROR, "安全证书异常");
            } else {
                listener.onError(ERROR, "error:" + e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        } finally {
            LogUtil.e("onError---->" + e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }
}
