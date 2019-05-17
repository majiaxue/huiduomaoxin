package com.example.user_home;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
