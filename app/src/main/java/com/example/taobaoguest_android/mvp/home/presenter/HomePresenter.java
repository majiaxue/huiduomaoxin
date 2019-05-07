package com.example.taobaoguest_android.mvp.home.presenter;

import android.content.Context;

import com.example.taobaoguest_android.base.BasePresenter;
import com.example.taobaoguest_android.mvp.home.view.HomeView;

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
