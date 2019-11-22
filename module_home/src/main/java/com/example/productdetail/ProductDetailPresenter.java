package com.example.productdetail;

import android.content.Context;

import com.example.bean.ProductCenterBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.productdetail.adapter.ProductAccountAdapter;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class ProductDetailPresenter extends BasePresenter<ProductDetailView> {
    public ProductDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(ProductCenterBean data) {
        String testName = data.getTestName();
        String testAddress = data.getTestAddress();
        String testAccount = data.getTestAccount();
        String testPassword = data.getTestPassword();

        List<ProductCenterBean> list = new ArrayList<>();
        for (int i = 0; i < testName.split(",").length; i++) {
            ProductCenterBean productCenterBean = new ProductCenterBean();
            productCenterBean.setTestName(testName.split(",")[i]);
            productCenterBean.setTestAddress(testAddress.split(",")[i]);
            productCenterBean.setTestAccount(testAccount.split(",")[i]);
            productCenterBean.setTestPassword(testPassword.split(",")[i]);
            list.add(productCenterBean);
        }
        ProductAccountAdapter productAccountAdapter = new ProductAccountAdapter(mContext, list, R.layout.rv_product_detail);
        if (getView() != null) {
            getView().loadRv(productAccountAdapter);
        }
    }

    public void loadPhone() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHeadWithout(CommonResource.PRODUCT_GETPHONE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("咨询电话：" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "---------------" + errorMsg);
            }
        }));
    }

    public void liuYanPop() {

    }
}
