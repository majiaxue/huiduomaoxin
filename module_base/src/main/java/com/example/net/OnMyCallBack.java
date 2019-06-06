package com.example.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bean.BaseEntity;
import com.example.common.CommonResource;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;


public class OnMyCallBack extends DisposableObserver<ResponseBody> {
    private OnDataListener listener;


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
//            BaseEntity baseEntity = JSON.parseObject(string, new TypeReference<BaseEntity>() {
//            }.getType());
//            if (CommonResource.CODE_SUCCESS.equals(baseEntity.getCode())) {
//                listener.onSuccess(JSON.toJSONString(baseEntity.getData()), baseEntity.getMsg());
//            } else {
//                listener.onError(baseEntity.getCode(), baseEntity.getMsg());
//            }
            JSONObject jsonObject = new JSONObject(string);

            String code = jsonObject.optString("code");
            String data = jsonObject.optString("data");
            String msg = jsonObject.optString("msg");
            if (CommonResource.CODE_SUCCESS.equals(code)) {
                listener.onSuccess(data, msg);
            } else if (CommonResource.TOKEN_EXPIRE.equals(code)) {
                SPUtil.addParm(CommonResource.TOKEN, "");
            } else {
                listener.onError(code, msg);
            }
        } catch (Exception e) {
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
                listener.onError(CommonResource.ERROR, "网络连接超时");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                listener.onError(CommonResource.ERROR, "安全证书异常");
            } else {
                listener.onError(CommonResource.ERROR, "error");
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
