package com.example.module_base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.example.utils.LogUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

public class ModuleBaseApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LogUtil.isDebug(this)) {
            ARouter.openLog();  //开启打印日志
            ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);

        Fresco.initialize(this);
        context = getApplicationContext();

        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                Log.e("tag", "阿里百川初始化成功");
            }

            @Override
            public void onFailure(int i, String s) {
                Log.e("tag", "阿里百川初始化失败。code:" + i + ",msg:" + s);
            }
        });
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
