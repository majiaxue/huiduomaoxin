package com.example.productcenter;

import com.example.module_home.R;
import com.example.mvp.BaseActivity;

public class ProductCenterActivity extends BaseActivity<ProductCenterView,ProductCenterPresenter> implements ProductCenterView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_product_center;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public ProductCenterView createView() {
        return this;
    }

    @Override
    public ProductCenterPresenter createPresenter() {
        return new ProductCenterPresenter(this);
    }
}
