package com.example.new_guide;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class NewGuidePresenter extends BasePresenter<NewGuideView> {
    public NewGuidePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
