package com.example.goodscollection;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class GoodsCollectionPresenter extends BasePresenter<GoodsCollectionView> {

    public GoodsCollectionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
