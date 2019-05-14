package com.example.home;

import android.app.Activity;
import android.content.Context;

import com.ali.auth.third.login.callback.LogoutCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.example.mvp.BasePresenter;
import com.example.utils.LogUtil;

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
