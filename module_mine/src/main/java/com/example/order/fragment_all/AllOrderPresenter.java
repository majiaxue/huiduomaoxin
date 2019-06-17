package com.example.order.fragment_all;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.MyOrderBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.order.adapter.RvListAdapter;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class AllOrderPresenter extends BasePresenter<AllOrderView> {
    private List<MyOrderBean> dataList;
    private RvListAdapter adapter;

    public AllOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(int index) {
        if (index == 0) {
            scOrder(index);
        } else if (index == 1) {
            tbOrder(index);
        } else if (index == 2) {
            jdOrder(index);
        } else if (index == 3) {
            pddOrder(index);
        }

    }

    private void pddOrder(int index) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.QUERY_PDD_ORDER, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("pdd订单：" + result);
                dataList = JSON.parseArray(result, MyOrderBean.class);
                if (adapter == null) {
                    adapter = new RvListAdapter(mContext, dataList, R.layout.rv_order_list);
                    if (getView() != null) {
                        getView().loadMineRv(adapter);
                    }
                } else {
                    adapter.notifyDataSetChanged();
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

            }
        }));
    }

    private void jdOrder(int index) {

    }

    private void tbOrder(int index) {

    }

    private void scOrder(int index) {

    }
}
