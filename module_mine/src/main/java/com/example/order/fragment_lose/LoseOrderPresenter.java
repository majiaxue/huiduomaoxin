package com.example.order.fragment_lose;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.JDGoodsRecBean;
import com.example.bean.JDOrderBean;
import com.example.bean.MyOrderBean;
import com.example.bean.TBOrderBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.order.OrderActivity;
import com.example.order.adapter.JDAdapter;
import com.example.order.adapter.RvListAdapter;
import com.example.order.adapter.TBAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class LoseOrderPresenter extends BasePresenter<LoseOrderView> {
    private List<MyOrderBean> dataList;
    private RvListAdapter adapter;

    public LoseOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        if (OrderActivity.index == 0) {
            scOrder();
        } else if (OrderActivity.index == 1) {
            tbOrder();
        } else if (OrderActivity.index == 2) {
            jdOrder();
        } else if (OrderActivity.index == 3) {
            pddOrder();
        }

    }

    private void scOrder() {

    }

    private void tbOrder() {
        Map map = MapUtil.getInstance().addParms("status", 2).addParms("type", 0).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_PDD_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("淘宝已失效：" + result);
                final List<TBOrderBean> orderBeans = JSON.parseArray(result, TBOrderBean.class);
                TBAdapter tbAdapter = new TBAdapter(mContext, orderBeans, R.layout.rv_order_list);
                if (getView() != null) {
                    getView().loadTB(tbAdapter);
                }

                tbAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                .withString("para", orderBeans.get(position).getNumIid())
                                .withString("shoptype", "淘宝".equals(orderBeans.get(position).getOrderType()) ? "1" : "0")
                                .navigation();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("已失效error" + errorCode + "----------" + errorMsg);
                List<TBOrderBean> orderBeans = new ArrayList<>();
                TBAdapter tbAdapter = new TBAdapter(mContext, orderBeans, R.layout.rv_order_list);
                if (getView() != null) {
                    getView().loadTB(tbAdapter);
                }
            }
        }));
    }

    private void jdOrder() {
        Map map = MapUtil.getInstance().addParms("status", 2).addParms("type", 1).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_PDD_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("已失效：" + result);
                List<JDOrderBean> jdOrderBeans = JSON.parseArray(result, JDOrderBean.class);
                for (int i = 0; i < jdOrderBeans.size(); i++) {
                    String image = jdOrderBeans.get(i).getImage();
                    String[] split = image.split(" imgUrl=");
                    String[] split1 = split[1].split(",");
                    jdOrderBeans.get(i).setImage(split1[0]);
                }

                JDAdapter jdAdapter = new JDAdapter(mContext, jdOrderBeans, R.layout.rv_order_list);
                getView().loadJD(jdAdapter);

                jdAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        JDGoodsRecBean.DataBean.ListsBean bean = new JDGoodsRecBean.DataBean.ListsBean();

//                        ARouter.getInstance().build("/module_classify/JDCommodityDetailsActivity")
//                                .withString("")
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("已失效error" + errorCode + "----------" + errorMsg);
                List<JDOrderBean> jdOrderBeans = new ArrayList<>();
                JDAdapter jdAdapter = new JDAdapter(mContext, jdOrderBeans, R.layout.rv_order_list);
                getView().loadJD(jdAdapter);

            }
        }));
    }

    private void pddOrder() {
        Map map = MapUtil.getInstance().addParms("status", 2).addParms("type", 2).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.QUERY_PDD_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("已失效：" + result);
                dataList = JSON.parseArray(result, MyOrderBean.class);

                adapter = new RvListAdapter(mContext, dataList, R.layout.rv_order_list);
                if (getView() != null) {
                    getView().loadMineRv(adapter);
                }

                adapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                                .withString("goods_id", dataList.get(position).getGoodsId() + "")
                                .navigation();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                List<MyOrderBean> dataList = new ArrayList<>();
                RvListAdapter adapter = new RvListAdapter(mContext, dataList, R.layout.rv_order_list);
                if (getView() != null) {
                    getView().loadMineRv(adapter);
                }
            }
        }));
    }
}
