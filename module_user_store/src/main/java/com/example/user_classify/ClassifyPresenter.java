package com.example.user_classify;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class ClassifyPresenter extends BasePresenter<ClassifyView> {

    public ClassifyPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
