package com.example.user_shopping_cart;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class ShoppingCartPresenter extends BasePresenter<ShoppingCartView> {

    public ShoppingCartPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
