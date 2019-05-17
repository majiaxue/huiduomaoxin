package com.example.classify;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class ClassifyPresenter extends BasePresenter<ClassifyView> {
    public ClassifyPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
