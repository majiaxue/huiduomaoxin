package com.example.order.fragment_settle;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.bean.MyOrderBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.order.adapter.RvListAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class SettleOrderPresenter extends BasePresenter<SettleOrderView> {
    private List<MyOrderBean> dataList;
    private RvListAdapter adapter;

    public SettleOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Map map = MapUtil.getInstance().addParms("status", 1).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.URL_27_4001).getHead(CommonResource.QUERY_PDD_ORDER, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("已结算：" + result);
                dataList = JSON.parseArray(result, MyOrderBean.class);
                if (adapter == null) {
                    adapter = new RvListAdapter(mContext, dataList, R.layout.rv_order_list);
                    if (getView() != null) {
                        getView().loadMineRv(adapter);
                    }
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
