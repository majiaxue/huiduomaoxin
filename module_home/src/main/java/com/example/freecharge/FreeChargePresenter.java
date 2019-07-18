package com.example.freecharge;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.FreeChargeBean;
import com.example.common.CommonResource;
import com.example.freecharge.adapter.FreeChargeAdapter;
import com.example.freecharge.adapter.FreeChargeLookAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class FreeChargePresenter extends BasePresenter<FreeChargeView> {

    private FreeChargeAdapter freeChargeAdapter;
    private FreeChargeLookAdapter freeChargeLookAdapter;

    public FreeChargePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void freeChargeActivity(final int activityType, final RecyclerView freeChargeRec) {
        Map map = MapUtil.getInstance().addParms("activityType", activityType).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.GOODSACTIVITY, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("FreeChargePresenterResult" + result);
                List<FreeChargeBean> freeChargeBeans = JSON.parseArray(result, FreeChargeBean.class);
                if (freeChargeBeans.size() != 0) {
                    if (getView() != null) {
                        getView().noGoods(false);
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    freeChargeRec.setLayoutManager(linearLayoutManager);

                    if (activityType == 0) {
                        if (freeChargeAdapter == null) {
                            freeChargeAdapter = new FreeChargeAdapter(mContext, freeChargeBeans, R.layout.item_free_charge_activity_rec);
                        }
                        freeChargeRec.setAdapter(freeChargeAdapter);
                    } else {
                        if (freeChargeLookAdapter == null) {
                            freeChargeLookAdapter = new FreeChargeLookAdapter(mContext, freeChargeBeans, R.layout.item_free_charge_look_back_rec);
                        }
                        freeChargeRec.setAdapter(freeChargeLookAdapter);
                        freeChargeLookAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                Toast.makeText(mContext, "活动已过期", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    if (getView() != null) {
                        getView().noGoods(true);
                    }
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
