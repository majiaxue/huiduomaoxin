package com.example.order_confirm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.bean.OrderConfirmBean;
import com.example.bean.PostageBean;
import com.example.bean.ShippingAddressBean;
import com.example.bean.SubmitOrderBean;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class OrderConfirmPresneter extends BasePresenter<OrderConfirmView> {

    private ShippingAddressBean addressBean;

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

    public void getPostage(OrderConfirmBean order) {
        Map map = MapUtil.getInstance().addParms("feightTemplateId", order.getFeightTemplateId())
                .addParms("provinceName", addressBean.getAddressProvince())
                .addParms("quantity", order.getQuantity())
                .addParms("skuId", order.getProductSkuId())
                .addParms("productId", order.getProductId())
                .build();
        List<Map> list = new ArrayList<>();
        list.add(map);
        String jsonString = JSON.toJSONString(list);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHeadWithBody(CommonResource.GET_YUNGEI, requestBody, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("运费：" + result);
                List<PostageBean> postageBean = JSON.parseArray(result, PostageBean.class);
                getView().loadPostage(postageBean.get(0));
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("yunfei:" + errorCode + "---------" + errorMsg);
            }
        }));
    }

    public void jumpToShippingAddress() {
        ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").navigation((Activity) mContext, 123);
    }

    public void submit(final OrderConfirmBean bean) {
        String jsonString = JSON.toJSONString(bean);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.COMMIT_ORDER, requestBody, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("提交订单：" + result);
                SubmitOrderBean submitOrderBean = JSON.parseObject(result, SubmitOrderBean.class);
                submitOrderBean.setProductName("goods");
                submitOrderBean.setProductCategoryId(bean.getProductCategoryId());
//                Intent intent = new Intent(mContext, PaymentActivity.class);
//                intent.putExtra("bean", submitOrderBean);
//                mContext.startActivity(intent);
                ARouter.getInstance().build("/module_user_store/PaymentActivity")
                        .withSerializable("submitOrderBean",submitOrderBean)
                        .navigation();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "--------" + errorMsg);
            }
        }));

    }
}
