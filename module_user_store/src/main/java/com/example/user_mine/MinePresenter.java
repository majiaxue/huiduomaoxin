package com.example.user_mine;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class MinePresenter extends BasePresenter<MineView> {

    public MinePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
