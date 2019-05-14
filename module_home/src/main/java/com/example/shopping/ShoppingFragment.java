package com.example.shopping;

import com.example.module_home.R;
import com.example.mvp.BaseFragment;

public class ShoppingFragment extends BaseFragment<ShoppingView, ShoppingPresneter> implements ShoppingView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public ShoppingView createView() {
        return this;
    }

    @Override
    public ShoppingPresneter createPresenter() {
        return new ShoppingPresneter(getContext());
    }
}
