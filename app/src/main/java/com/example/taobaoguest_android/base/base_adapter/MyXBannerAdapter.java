package com.example.taobaoguest_android.base.base_adapter;

import android.content.Context;
import android.view.View;

import com.stx.xhb.xbanner.XBanner;

public class MyXBannerAdapter implements XBanner.XBannerAdapter {
    private Context context;

    public MyXBannerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {

    }
}
