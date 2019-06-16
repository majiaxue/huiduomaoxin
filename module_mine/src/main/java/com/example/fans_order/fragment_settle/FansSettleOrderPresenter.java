package com.example.fans_order.fragment_settle;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.bean.FansOrderBean;
import com.example.common.CommonResource;
import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class FansSettleOrderPresenter extends BasePresenter<FansSettleOrderView> {
    private List<FansOrderBean> fansList = new ArrayList();
    private FansOrderRvAdapter fansAdapter;

    public FansSettleOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page) {
        Map map = MapUtil.getInstance().addParms("currentPage", page).addParms("status", 2).addParms("pageSize", "10").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_FANS_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("粉丝订单jiesuan：" + result);
                List<FansOrderBean> baseEntity = JSON.parseArray(result, FansOrderBean.class);
                if (page == 1) {
                    fansList.clear();
                }
                fansList.addAll(baseEntity);
                if (fansAdapter == null) {
                    fansAdapter = new FansOrderRvAdapter(mContext, fansList, R.layout.rv_fans_order_list);
                    if (getView() != null) {
                        getView().loadFansRv(fansAdapter);
                    }
                } else {
                    fansAdapter.notifyDataSetChanged();
                    if (getView() != null) {
                        getView().loadSuccess();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
