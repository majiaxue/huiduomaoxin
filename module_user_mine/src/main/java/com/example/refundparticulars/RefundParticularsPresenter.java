package com.example.refundparticulars;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.alteration.bean.AlterationBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class RefundParticularsPresenter extends BasePresenter<RefundParticularsView> {

    public RefundParticularsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(String returnId){
        Map map = MapUtil.getInstance().addParms("returnId", returnId).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.URL_4_4001).postData(CommonResource.RETURNTABLE, map);
        RetrofitUtil.getInstance().toSubscribe(observable,new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("RefundParticularsResult------------->"+result);
                List<AlterationBean> list = JSON.parseArray(result, AlterationBean.class);
                if (getView()!=null){
                    getView().initView(list);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("RefundParticularsErrorMsg------------->"+errorMsg);
            }
        }));

    }
}
