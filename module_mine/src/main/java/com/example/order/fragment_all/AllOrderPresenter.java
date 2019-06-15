package com.example.order.fragment_all;

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

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.URL_27_4001).getHeadWithout(CommonResource.QUERY_PDD_ORDER, SPUtil.getToken());
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
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }
}
