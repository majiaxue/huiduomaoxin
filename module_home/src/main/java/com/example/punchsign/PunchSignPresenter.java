package com.example.punchsign;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class PunchSignPresenter extends BasePresenter<PunchSignView> {

    public PunchSignPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
