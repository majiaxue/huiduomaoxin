package com.example.points;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class PointsPresenter extends BasePresenter<PointsView> {
    public PointsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
