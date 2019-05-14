package com.example.mine;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class MinePresenter extends BasePresenter<MineView> {


    public MinePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
