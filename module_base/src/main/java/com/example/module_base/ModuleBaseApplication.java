package com.example.module_base;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.example.common.CommonResource;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class ModuleBaseApplication extends MultiDexApplication {
    private static Context context;
    public static IWXAPI wxapi;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        if (LogUtil.isDebug(this)) {
            ARouter.openLog();  //开启打印日志
            ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        SPUtil.getInstance(this);
        FlowManager.init(this);

        wxapi = WXAPIFactory.createWXAPI(this, CommonResource.WXAPPID, false);
        wxapi.registerApp(CommonResource.WXAPPID);

        Fresco.initialize(this);
        context = getApplicationContext();

//        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
//            @Override
//            public void onSuccess() {
//                LogUtil.e("阿里百川初始化成功");
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                LogUtil.e("阿里百川：" + code + "-------" + msg);
//            }
//        });
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
