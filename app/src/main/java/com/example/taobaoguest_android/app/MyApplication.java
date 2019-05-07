package com.example.taobaoguest_android.app;

import android.app.Application;
import android.content.Context;

import com.example.taobaoguest_android.utils.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.isDebug(this);
        Fresco.initialize(this);
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
