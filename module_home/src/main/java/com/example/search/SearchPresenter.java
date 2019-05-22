package com.example.search;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class SearchPresenter extends BasePresenter<SearchView> {

    public SearchPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
