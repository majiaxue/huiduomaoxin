package com.example.goods_detail;

import com.example.mvp.BaseActivity;
import com.example.user_store.R;

public class GoodsDetailActivity extends BaseActivity<GoodsDetailView, GoodsDetailPresenter> implements GoodsDetailView {
    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public GoodsDetailView createView() {
        return this;
    }

    @Override
    public GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter(this);
    }
}
