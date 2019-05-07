package com.example.taobaoguest_android.mvp.search.presenter;

import android.content.Context;

import com.example.taobaoguest_android.base.BasePresenter;
import com.example.taobaoguest_android.mvp.search.view.SearchView;

public class SearchPresenter extends BasePresenter<SearchView> {

    public SearchPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
