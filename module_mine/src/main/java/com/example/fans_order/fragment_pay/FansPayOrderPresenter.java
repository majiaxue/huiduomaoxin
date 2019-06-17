package com.example.fans_order.fragment_pay;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
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

public class FansPayOrderPresenter extends BasePresenter<FansPayOrderView> {
    private List<FansOrderBean> pddList = new ArrayList();
    private FansOrderRvAdapter pddAdapter;

    public FansPayOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page, int index) {
        if (index == 0) {
            scOrder(page, index);
        } else if (index == 1) {
            tbOrder(page, index);
        } else if (index == 2) {
            jdOrder(page, index);
        } else if (index == 3) {
            pddOrder(page, index);
        }
    }

    private void pddOrder(final int page, int index) {
        Map map = MapUtil.getInstance().addParms("currentPage", page).addParms("status", 2).addParms("pageSize", "10").addParms("type", index).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_FANS_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("粉丝订单fukuan：" + result);
                List<FansOrderBean> baseEntity = JSON.parseArray(result, FansOrderBean.class);
                if (page == 1) {
                    pddList.clear();
                }
                pddList.addAll(baseEntity);
                if (pddAdapter == null) {
                    pddAdapter = new FansOrderRvAdapter(mContext, pddList, R.layout.rv_fans_order_list);
                    if (getView() != null) {
                        getView().loadFansRv(pddAdapter);
                    }
                } else {
                    pddAdapter.notifyDataSetChanged();
                    if (getView() != null) {
                        getView().loadSuccess();
                    }
                }
                pddAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").withString("goods_id", pddList.get(position).getGoodsId() + "").navigation();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    private void jdOrder(int page, int index) {
        Map map = MapUtil.getInstance().addParms("currentPage", page).addParms("status", 2).addParms("pageSize", "10").addParms("type", index).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_FANS_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("粉丝订单fukuan：" + result);

            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    private void tbOrder(int page, int index) {
        Map map = MapUtil.getInstance().addParms("currentPage", page).addParms("status", 2).addParms("pageSize", "10").addParms("type", index).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_FANS_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("粉丝订单fukuan：" + result);

            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    private void scOrder(int page, int index) {
        Map map = MapUtil.getInstance().addParms("currentPage", page).addParms("status", 2).addParms("pageSize", "10").addParms("type", index).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_FANS_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("粉丝订单fukuan：" + result);

            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
