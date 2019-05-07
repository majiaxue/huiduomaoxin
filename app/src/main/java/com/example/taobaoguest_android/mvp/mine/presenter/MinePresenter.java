package com.example.taobaoguest_android.mvp.mine.presenter;

import android.content.Context;

import com.example.taobaoguest_android.base.BasePresenter;
import com.example.taobaoguest_android.mvp.mine.view.MineView;

public class MinePresenter extends BasePresenter<MineView> {

    public MinePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
