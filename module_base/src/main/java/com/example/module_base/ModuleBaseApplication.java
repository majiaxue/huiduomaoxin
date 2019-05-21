package com.example.module_base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.utils.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class ModuleBaseApplication extends MultiDexApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        if (LogUtil.isDebug(this)) {
            ARouter.openLog();  //开启打印日志
            ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);

        Fresco.initialize(this);
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}
