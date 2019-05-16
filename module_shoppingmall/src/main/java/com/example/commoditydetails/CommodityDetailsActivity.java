package com.example.commoditydetails;


import com.example.module_shoppingmall.R;
import com.example.mvp.BaseActivity;

public class CommodityDetailsActivity extends BaseActivity<CommodityDetailsView, CommodityDetailsPresenter> implements CommodityDetailsView {

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public CommodityDetailsView createView() {
        return this;
    }

    @Override
    public CommodityDetailsPresenter createPresenter() {
        return new CommodityDetailsPresenter(this);
    }
}
