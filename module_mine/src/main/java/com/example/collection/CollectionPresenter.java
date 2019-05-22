package com.example.collection;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class CollectionPresenter extends BasePresenter<CollectionView> {
    private boolean isEdit = false;

    public CollectionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {

    }

    public void edit() {
        if (isEdit) {
            isEdit = false;
            getView().toFinish();
        } else {
            isEdit = true;
            getView().toEdit();
        }
    }
}
