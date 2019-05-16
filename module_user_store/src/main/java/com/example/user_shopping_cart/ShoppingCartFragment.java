package com.example.user_shopping_cart;

import com.example.user_store.R;
import com.example.mvp.BaseFragment;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class ShoppingCartFragment extends BaseFragment<ShoppingCartView, ShoppingCartPresenter> implements ShoppingCartView {
    @Override
    public int getLayoutId() {
        return R.layout.fragment_multi_user_shopping_cart;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {

    }

    @Override
    public ShoppingCartView createView() {
        return this;
    }

    @Override
    public ShoppingCartPresenter createPresenter() {
        return new ShoppingCartPresenter(getContext());
    }
}
