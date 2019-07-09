package com.example.community.goods_commend;

import com.example.mvp.BaseFragment;

public class GoodsCommendFragment extends BaseFragment<GoodsCommendView, GoodsCommendPresenter> implements GoodsCommendView {
    @Override
    public int getLayoutId() {
        return 0;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public GoodsCommendView createView() {
        return this;
    }

    @Override
    public GoodsCommendPresenter createPresenter() {
        return new GoodsCommendPresenter(getContext());
    }
}
