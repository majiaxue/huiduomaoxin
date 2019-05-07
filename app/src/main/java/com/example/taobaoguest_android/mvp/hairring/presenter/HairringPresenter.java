package com.example.taobaoguest_android.mvp.hairring.presenter;

import android.content.Context;

import com.example.taobaoguest_android.base.BasePresenter;
import com.example.taobaoguest_android.mvp.hairring.view.HairringView;

public class HairringPresenter extends BasePresenter<HairringView> {

    public HairringPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
