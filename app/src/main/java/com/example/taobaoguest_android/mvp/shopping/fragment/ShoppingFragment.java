package com.example.taobaoguest_android.mvp.shopping.fragment;

import com.example.taobaoguest_android.R;
import com.example.taobaoguest_android.base.BaseFragment;
import com.example.taobaoguest_android.mvp.hairring.presenter.HairringPresenter;
import com.example.taobaoguest_android.mvp.hairring.view.HairringView;
import com.example.taobaoguest_android.mvp.shopping.presenter.ShoppingPresneter;
import com.example.taobaoguest_android.mvp.shopping.view.ShoppingView;

public class ShoppingFragment extends BaseFragment<ShoppingView, ShoppingPresneter> implements ShoppingView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_shopping;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

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
