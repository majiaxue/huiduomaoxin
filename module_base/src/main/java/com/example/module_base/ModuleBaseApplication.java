package com.example.module_base;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTradeSDK;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeInitCallback;
import com.example.common.CommonResource;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

public class ModuleBaseApplication extends Application {
    private static Context context;
    public static IWXAPI wxapi;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);


        // 安装tinker
        Beta.installTinker();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LogUtil.isDebug(this)) {
            ARouter.openLog();  //开启打印日志
            ARouter.openDebug();// 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        SPUtil.getInstance(this);
        //初始化DBFLOW
        FlowManager.init(this);
        initFresco();

        wxapi = WXAPIFactory.createWXAPI(this, CommonResource.WXAPPID, false);
        wxapi.registerApp(CommonResource.WXAPPID);

        //友盟
        UMConfigure.init(this, CommonResource.U_APPKEY, "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
        initShare();
        UMConfigure.setLogEnabled(true);


        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(this, "47037498aa", false);

        Fresco.initialize(this);
        context = getApplicationContext();

        AlibcTradeSDK.asyncInit(this, new AlibcTradeInitCallback() {
            @Override
            public void onSuccess() {
                LogUtil.e("阿里百川初始化成功");
            }

            @Override
            public void onFailure(int code, String msg) {
                LogUtil.e("阿里百川：" + code + "-------" + msg);
            }
        });
    }

    private void initFresco() {
        //对ImagePipelineConfig进行一些配置
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(getApplicationContext())
                .setDownsampleEnabled(true)              // 对图片进行自动缩放
                .setResizeAndRotateEnabledForNetwork(true) // 对网络图片进行resize处理，减少内存消耗
                .setBitmapsConfig(Bitmap.Config.RGB_565) //图片设置RGB_565，减小内存开销 fresco默认情况下是RGB_8888
                .build();
        Fresco.initialize(this, config);
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }

    private void initShare() {
        PlatformConfig.setWeixin("wxf08fd2965ac9ac30", "2d54eace93a3bda15d041ee594b7eeef");
    }

}
