package com.example.local_store;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.example.bean.LocalCartBean;
import com.example.bean.LocalStoreBean;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean;
import com.example.local_store.adapter.LocalStoreCommendAdapter;
import com.example.local_store.adapter.ShoppingRightAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LocalStorePresenter extends BasePresenter<LocalStoreView> {
    private int flag = 0;
    private List<LocalCartBean.InsideCart> localCartBeans;
    private List<LocalStoreBean> localStoreBeans;
    private List<LocalStoreBean.ListBean> recommendList = new ArrayList<>();

    private int temp1 = 0;
    private int temp2 = 0;
    private LocalStoreCommendAdapter commendAdapter;


    public LocalStorePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(this);
        SPUtil.addParm(CommonResource.SELLERID, "");
        SPUtil.addParm(CommonResource.SELLERNAME, "");
    }

    public void loadData(final String id) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_SHOP + id);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("店铺：" + result);
                flag++;
                localStoreBeans = JSON.parseArray(result, LocalStoreBean.class);

                if (flag == 2) {
                    ShoppingRightAdapter.setCartBeanList(localCartBeans);
                    relevance();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (temp1 < 3) {
                    loadData(id);
                    temp1++;
                }
                LogUtil.e(errorCode + "------------" + errorMsg);
            }
        }));
    }

    public void loadCart(final String id) {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).getDataWithout(CommonResource.LOCAL_GET_CART + id + "/" + SPUtil.getUserCode());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("购物车：" + result);
                LocalCartBean localCartBean = JSON.parseObject(result, LocalCartBean.class);
                EventBus.getDefault().post(new EventBusBean(CommonResource.UPCART, JSON.toJSONString(localCartBean.getLocalShopcarList())));
                LocalCartBean cartBean = JSON.parseObject(result, LocalCartBean.class);
                localCartBeans = cartBean.getLocalShopcarList();
                flag++;
                if (flag == 2) {
                    ShoppingRightAdapter.setCartBeanList(localCartBeans);
                    relevance();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (temp2 < 3) {
                    loadCart(id);
                    temp2++;
                }
                LogUtil.e(errorCode + "--------------" + errorMsg);
            }
        }));
    }

    private void relevance() {
        for (int i = 0; i < localStoreBeans.size(); i++) {
            for (int j = 0; j < localStoreBeans.get(i).getList().size(); j++) {
                for (int k = 0; k < localCartBeans.size(); k++) {
                    if (localStoreBeans.get(i).getList().get(j).getId().equals(localCartBeans.get(k).getLocalGoodsId())) {
                        localStoreBeans.get(i).getList().get(j).setCount(Integer.valueOf(localCartBeans.get(k).getNum()));
                    }
                }
            }
        }

        if (getView() != null) {
            getView().loadData(localStoreBeans);
        }
    }

    public void upCart(String msg) {

        List<LocalCartBean.InsideCart> cartBeanList = JSON.parseArray(msg, LocalCartBean.InsideCart.class);
        double money = 0.0;
        for (int i = 0; i < cartBeanList.size(); i++) {
            money += (cartBeanList.get(i).getPrice() * cartBeanList.get(i).getNum());
        }
        getView().upMoney(ArithUtil.exact(money, 2), cartBeanList.size());
    }
}
