package com.example.local_pay;

import android.content.Context;
import android.content.Intent;

import com.example.local_assess.LocalAssessActivity;
import com.example.mvp.BasePresenter;

public class LocalPayPresenter extends BasePresenter<LocalPayView> {
    public LocalPayPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void commit(String edit, int payType) {
        Intent intent = new Intent(mContext, LocalAssessActivity.class);
        mContext.startActivity(intent);
    }
}
