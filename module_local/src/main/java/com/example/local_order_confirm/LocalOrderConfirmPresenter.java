package com.example.local_order_confirm;

import android.app.Activity;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvp.BasePresenter;

public class LocalOrderConfirmPresenter extends BasePresenter<LocalOrderConfirmView> {
    public LocalOrderConfirmPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void chooseAddress() {
        ARouter.getInstance().build("/module_user_mine/ShippingAddressActivity").withString("from", "order").navigation((Activity) mContext, 456);
    }
}
