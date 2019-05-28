package com.example.refundparticulars;

import android.content.Context;

import com.example.mvp.BasePresenter;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class RefundParticularsPresenter extends BasePresenter<RefundParticularsView> {

    public RefundParticularsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
