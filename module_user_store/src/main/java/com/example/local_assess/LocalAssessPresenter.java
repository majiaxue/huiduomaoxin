package com.example.local_assess;

import android.content.Context;
import android.widget.Toast;

import com.example.mvp.BasePresenter;

public class LocalAssessPresenter extends BasePresenter<LocalAssessView> {
    public LocalAssessPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void commit(int totalStarCount, int serveStarCount, int descriptionStarCount, String edit) {
        if (totalStarCount == 0 || serveStarCount == 0 || descriptionStarCount == 0) {
            Toast.makeText(mContext, "请先打分", Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}
