package com.example.taobaoguest_android.utils;


public interface OnDataListener<T> {
    /**
     * 网络请求成功，返回数据
     *
     * @param result
     */
    void onSuccess(T result, String msg);

    /**
     * 网络请求失败
     */
    void onError(String errorCode, String errorMsg);
}
