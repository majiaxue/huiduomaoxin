package com.example.local_order;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.LocalOrderBean;
import com.example.bean.TxtAndChooseBean;
import com.example.common.CommonResource;
import com.example.local_order.adapter.LocalOrderAdapter;
import com.example.local_order.adapter.LocalOrderNavbarAdapter;
import com.example.module_local.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LocalOrderPresenter extends BasePresenter<LocalOrderView> {

    private List<TxtAndChooseBean> navbarList;
    private LocalOrderNavbarAdapter navbarAdapter;
    private LocalOrderAdapter orderAdapter;
    private List<LocalOrderBean> localOrderBeans = new ArrayList<>();

    public LocalOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initNavbar() {
        navbarList = new ArrayList<>();
        navbarList.add(new TxtAndChooseBean("全部订单", true));
        navbarList.add(new TxtAndChooseBean("待付款", false));
        navbarList.add(new TxtAndChooseBean("待取货", false));
        navbarList.add(new TxtAndChooseBean("配送中", false));
        navbarList.add(new TxtAndChooseBean("已完成", false));
        navbarList.add(new TxtAndChooseBean("退货", false));
        navbarAdapter = new LocalOrderNavbarAdapter(mContext, navbarList, R.layout.rv_local_order_navbar);
        if (getView() != null) {
            getView().loadNavbar(navbarAdapter);
        }

        navbarAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                for (int i = 0; i < navbarList.size(); i++) {
                    if (i == position) {
                        navbarList.get(i).setChoose(true);
                    } else {
                        navbarList.get(i).setChoose(false);
                    }
                }

                navbarAdapter.notifyDataSetChanged();
                if (getView() != null) {
                    getView().changeType(position);
                }
            }
        });
    }

    public void loadData(String status, final int page) {
        ProcessDialogUtil.showProcessDialog(mContext);
        Map map = MapUtil.getInstance().addParms("status", status).addParms("page", page).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getData(CommonResource.LOCAL_GET_ORDER, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("附近小店订单：" + result);
                if (page == 1) {
                    localOrderBeans.clear();
                }
                localOrderBeans.addAll(JSON.parseArray(result, LocalOrderBean.class));
                if (orderAdapter == null) {
                    orderAdapter = new LocalOrderAdapter(mContext, localOrderBeans, R.layout.rv_local_order_list);
                    if (getView() != null) {
                        getView().loadRv(orderAdapter);
                    }
                } else {
                    orderAdapter.notifyDataSetChanged();
                }

                orderAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_local/OrderInfoActivity").withSerializable("bean", localOrderBeans.get(position)).navigation();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "----------" + errorMsg);
            }
        }));
    }
}
