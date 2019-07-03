package com.example.confirm_order;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.ConfirmOrderBean;
import com.example.bean.ConfirmOrderInsideBean;
import com.example.bean.PostageBean;
import com.example.bean.ShippingAddressBean;
import com.example.bean.SubmitOrderBean;
import com.example.common.CommonResource;
import com.example.confirm_order.adapter.ConfirmOrderAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.bean.CartBean;
import com.example.user_store.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class ConfirmOrderPresenter extends BasePresenter<ConfirmOrderView> {
    private ConfirmOrderAdapter orderAdapter;
    public ShippingAddressBean addressBean;
    private List<CartBean.RecordsBean> dataList = new ArrayList<>();

    public ConfirmOrderPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(List<CartBean.RecordsBean> beanList) {
        dataList.addAll(beanList);
        orderAdapter = new ConfirmOrderAdapter(mContext, dataList, R.layout.rv_confirm_order);

        if (getView() != null) {
            getView().loadRv(orderAdapter);
        }

        orderAdapter.setOnTwoViewClickListener(new MyRecyclerAdapter.OnTwoViewClickListener() {
            @Override
            public void twoViewClick(View minus, View add, final int outside, final int inside) {
                minus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (dataList.get(outside).getItems().get(inside).getQuantity() <= 1) {
                            dataList.get(outside).getItems().get(inside).setQuantity(1);
                        } else {
                            dataList.get(outside).getItems().get(inside).setQuantity(dataList.get(outside).getItems().get(inside).getQuantity() - 1);
                            getPostage(addressBean.getAddressProvince());
                        }
                    }
                });

                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataList.get(outside).getItems().get(inside).setQuantity(dataList.get(outside).getItems().get(inside).getQuantity() + 1);
                        getPostage(addressBean.getAddressProvince());
                    }
                });

            }
        });
    }

    public void jumpToPayment() {
        List<ConfirmOrderInsideBean> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            ConfirmOrderInsideBean insideBean = new ConfirmOrderInsideBean();
            insideBean.setSellerId((long) dataList.get(i).getSellerId());
            insideBean.setFreightAmount(dataList.get(i).getTotalFeight());
            insideBean.setCouponAmount(0l);
            list.add(insideBean);
        }
        ConfirmOrderBean orderBean = new ConfirmOrderBean();

        orderBean.setReceiverName(addressBean.getAddressName());
        orderBean.setReceiverPhone(addressBean.getAddressPhone());
        orderBean.setReceiverProvince(addressBean.getAddressProvince());
        orderBean.setReceiverCity(addressBean.getAddressCity());
        orderBean.setReceiverRegion(addressBean.getAddressArea());
        orderBean.setOrderAddress(addressBean.getAddressDetail());
        orderBean.setUserId(SPUtil.getUserCode());
        orderBean.setOrderRequestItems(list);


        String jsonString = JSON.toJSONString(orderBean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.CART_SUBMIT_ORDER, requestBody, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("购物车下单：" + result);
                SubmitOrderBean submitOrderBean = JSON.parseObject(result, SubmitOrderBean.class);
                submitOrderBean.setProductCategoryId(dataList.get(0).getItems().get(0).getProductCategoryId() + "");
                submitOrderBean.setProductName("cart");
                ARouter.getInstance().build("/module_user_store/PaymentActivity")
                        .withSerializable("submitOrderBean", submitOrderBean)
                        .navigation();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void getAddress() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.MOREN_ADDRESS, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("默认地址：" + result);
                addressBean = JSON.parseObject(result, ShippingAddressBean.class);
                if (getView() != null) {
                    getView().loadAddress(addressBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("dizhi:" + errorCode + "-------" + errorMsg);
                if (getView() != null) {
                    getView().noAddress();
                }
            }
        }));

    }

    public void getPostage(String province) {
        List<Map> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            for (int j = 0; j < dataList.get(i).getItems().size(); j++) {
                list.add(MapUtil.getInstance().addParms("provinceName", province)
                        .addParms("quantity", dataList.get(i).getItems().get(j).getQuantity())
                        .addParms("skuId", dataList.get(i).getItems().get(j).getProductSkuId())
                        .addParms("productId", dataList.get(i).getItems().get(j).getProductId())
                        .build());
            }
        }

        String jsonString = JSON.toJSONString(list);
        LogUtil.e("运费1111---------->"+jsonString);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHeadWithBody(CommonResource.GET_YUNGEI, requestBody, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("运费：" + result);
                List<PostageBean> postageBean = JSON.parseArray(result, PostageBean.class);


                for (int j = 0; j < dataList.size(); j++) {
                    double feight = 0;
                    double totalPrice = 0;
                    for (int i = 0; i < postageBean.size(); i++) {
                        if (postageBean.get(i).getSellerId() == dataList.get(j).getSellerId()) {
                            feight += postageBean.get(i).getFeight();
                            totalPrice += postageBean.get(i).getTotal();
                        }
                    }
                    dataList.get(j).setTotalFeight(ArithUtil.exact(feight, 2));
                    dataList.get(j).setTotalPrice(ArithUtil.exact(totalPrice, 2));
                }
                orderAdapter.notifyDataSetChanged();
                jisuan(postageBean);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("yunfei:" + errorCode + "---------" + errorMsg);
            }
        }));
    }

    private void jisuan(List<PostageBean> postageBean) {
        double totalFeight = 0;
        double totalPrice = 0;
        for (int i = 0; i < postageBean.size(); i++) {
            totalFeight += postageBean.get(i).getFeight();
            totalPrice += postageBean.get(i).getTotal();
        }
        if (getView() != null) {
            getView().loadPostage(ArithUtil.exact(totalFeight, 2), ArithUtil.exact(totalPrice, 2), postageBean.size());
        }
    }

    public void jumpToShippingAddress() {
        ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").navigation((Activity) mContext, 456);
    }
}
