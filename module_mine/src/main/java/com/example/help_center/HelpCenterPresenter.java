package com.example.help_center;

import android.content.Context;
import android.content.Intent;

import com.example.help_detail.HelpDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.new_guide.NewGuideActivity;

public class HelpCenterPresenter extends BasePresenter<HelpCenterView> {
    public HelpCenterPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void jumpToDetail(int position) {
        mContext.startActivity(new Intent(mContext, HelpDetailActivity.class));
    }

    public void jumpToGuide() {
        mContext.startActivity(new Intent(mContext, NewGuideActivity.class));
    }
}
