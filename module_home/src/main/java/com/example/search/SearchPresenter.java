package com.example.search;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class SearchPresenter extends BasePresenter<SearchView> {

    public SearchPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
