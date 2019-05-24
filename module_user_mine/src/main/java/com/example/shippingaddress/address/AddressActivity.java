package com.example.shippingaddress.address;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.mvp.BaseActivity;

/**
 * 新建收货地址
 */
@Route(path = "/module_user_mine/AddressActivity")
public class AddressActivity extends BaseActivity<AddressView,AddressPresenter> implements AddressView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public AddressView createView() {
        return this;
    }

    @Override
    public AddressPresenter createPresenter() {
        return new AddressPresenter(this);
    }
}
