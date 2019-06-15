package com.example.order_detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotSaleBean;
import com.example.bean.OrderDetailBean;
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
import com.example.goods_detail.GoodsDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_home.adapter.SaleHotAdapter;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class OrderDetailPresenter extends BasePresenter<OrderDetailView> {
    public OrderDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadOrder(String masterNo) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi5(mContext).getHeadWithout(CommonResource.ORDER_DETAIL + "/" + masterNo, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("订单详情：" + result);
                OrderDetailBean orderDetailBean = JSON.parseObject(result, OrderDetailBean.class);
                if (getView() != null) {
                    getView().loadData(orderDetailBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void loadCommend(String productCategoryId) {
        Map map = MapUtil.getInstance().addParms("pageNum", "1").addParms("categoryId", productCategoryId).build();
        Observable observable = RetrofitUtil.getInstance().getApi1(mContext).getData(CommonResource.HOTNEWSEARCH, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("推荐：" + result);
                final HotSaleBean hotSaleBean = JSON.parseObject(result, HotSaleBean.class);
                SaleHotAdapter saleHotAdapter = new SaleHotAdapter(mContext, hotSaleBean.getData(), R.layout.rv_hot);
                saleHotAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("id", hotSaleBean.getData().get(position).getId()+"");
                        intent.putExtra("commendId", hotSaleBean.getData().get(position).getProductCategoryId()+"");
                        intent.putExtra("sellerId", hotSaleBean.getData().get(position).getSellerId());
                        mContext.startActivity(intent);
                    }
                });
                if (getView() != null) {
                    getView().loadCommend(saleHotAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void jumpToTuikuan(SubmitOrderBean bean) {
        ARouter.getInstance().build("/module_user_mine/RefundActivity").withSerializable("bean", bean).navigation();
    }
}
