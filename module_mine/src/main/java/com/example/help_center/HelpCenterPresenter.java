package com.example.help_center;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class HelpCenterPresenter extends BasePresenter<HelpCenterView> {
    public HelpCenterPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
