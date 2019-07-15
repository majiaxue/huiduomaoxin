package com.example.local_coupon;

import android.content.Context;

import com.example.bean.UserCouponBean;
import com.example.local_coupon.adapter.LocalCouponAdapter;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;

import java.util.ArrayList;
import java.util.List;

public class LocalCouponPresenter extends BasePresenter<LocalCouponView> {
    private List<UserCouponBean> couponList = new ArrayList<>();
    private LocalCouponAdapter couponAdapter;

    public LocalCouponPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        for (int i = 0; i < 10; i++) {
            UserCouponBean bean = new UserCouponBean();
            bean.setAmount(10);
            bean.setMinPoint(50);
            bean.setEndTime("2019-12-12");
            couponList.add(bean);
        }

        couponAdapter = new LocalCouponAdapter(mContext, couponList, R.layout.rv_local_my_coupon);
        if (getView() != null) {
            getView().loadCoupon(couponAdapter);
        }
    }
}
