package com.example.order_confirm;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.bean.AddressBean;
import com.example.bean.OrderConfirmBean;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.payment.PaymentActivity;
import com.example.shop_home.ShopHomeActivity;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class OrderConfirmPresneter extends BasePresenter<OrderConfirmView> {

    private AddressBean addressBean;
    private OrderConfirmBean order;

    public OrderConfirmPresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void jumpToShop(String sellerId) {
        Intent intent = new Intent(mContext, ShopHomeActivity.class);
        intent.putExtra("sellId", sellerId);
        mContext.startActivity(intent);
    }

    public void loadData(OrderConfirmBean order) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi4(mContext).getHeadWithout(CommonResource.MOREN_ADDRESS, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("默认地址：" + result);
                addressBean = JSON.parseObject(result, AddressBean.class);
                if (getView() != null) {
                    getView().loadAddress(addressBean);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("dizhi:" + errorCode + "------" + errorMsg);
                if ("1".equals(errorCode) && getView() != null) {
                    getView().noAddress();
                }
            }
        }));

    }

    public void getPostage(OrderConfirmBean order) {
        this.order = order;
        Map map = MapUtil.getInstance().addParms("feightTemplateId", order.getFeightTemplateId())
                .addParms("ProvinceName", addressBean.getAddressProvince())
                .addParms("quantity", order.getQuantity())
                .addParms("skuId", order.getProductSkuId())
                .build();

    }

    public void jumpToShippingAddress() {
        ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").navigation();
    }

    public void minusCount() {
        if (order.getQuantity() <= 1) {

        } else {
            order.setQuantity(order.getQuantity() - 1);
            getView().reviseCount(order);
        }
    }

    public void addCount() {
        if (order.getQuantity() >= order.getStock()) {

        } else {
            order.setQuantity(order.getQuantity() + 1);
            getView().reviseCount(order);
        }
    }

    public void submit(String content) {
        Intent intent = new Intent(mContext, PaymentActivity.class);
        mContext.startActivity(intent);
    }
}
