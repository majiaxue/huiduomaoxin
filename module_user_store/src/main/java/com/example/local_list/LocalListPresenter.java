package com.example.local_list;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class LocalListPresenter extends BasePresenter<LocalListView> {
    public LocalListPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
