package com.example.home;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
