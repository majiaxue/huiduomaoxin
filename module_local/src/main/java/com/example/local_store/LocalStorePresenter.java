package com.example.local_store;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.bean.LocalStoreBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LocalStorePresenter extends BasePresenter<LocalStoreView> {


    public LocalStorePresenter(Context context) {
        super(context);

    }

    @Override
    protected void onViewDestroy() {
        SPUtil.addParm(CommonResource.SELLERID, "");
    }


    public void loadData(String id) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_SHOP + id);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("店铺：" + result);
                List<LocalStoreBean> localStoreBeans = JSON.parseArray(result, LocalStoreBean.class);
                if (getView() != null) {
                    getView().loadData(localStoreBeans);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "------------" + errorMsg);
            }
        }));
    }


    public void submitOrder() {

    }
}
