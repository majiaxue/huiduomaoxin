package com.example.h5home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.utils.AndroidJs;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.example.utils.net_change_util.NetworkType;
import com.example.utils.net_change_util.NetworkUtil;

import org.greenrobot.eventbus.EventBus;

import static android.content.Context.TELEPHONY_SERVICE;

public class H5HomePresenter extends BasePresenter<H5HomeView> {

    private static String imei;

    public H5HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {
        EventBus.getDefault().unregister(this);
    }

    public void initWebView(WebView homeH5Web) {
        WebSettings webSettings = homeH5Web.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);

        homeH5Web.addJavascriptInterface(new AndroidJs(mContext), "android");

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        // 应用可以有缓存 true false 没有缓存
        // 应用可以有缓存
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = mContext.getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);
        webSettings.setAllowFileAccessFromFileURLs(true);
        //设置可以使用localStorage
        webSettings.setDomStorageEnabled(true);
        // 应用可以有数据库
        webSettings.setDatabaseEnabled(true);
        String dbPath = mContext.getDir("database", Context.MODE_PRIVATE).getPath();
        webSettings.setDatabasePath(dbPath);

        webSettings.setAppCacheMaxSize(8 * 1024 * 1024); //缓存最多可以有8M
        webSettings.setAllowFileAccess(true); // 可以读取文件缓存(manifest生效)

        //其他细节操作
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        NetworkType networkType = NetworkUtil.getNetworkType(mContext);
        LogUtil.e("当前网络：" + networkType);
        if (networkType == NetworkType.NETWORK_NO) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //没有网络时加载缓存
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }

        homeH5Web.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

        });

        homeH5Web.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public void onExceededDatabaseQuota(String url, String databaseIdentifier,
                                                long currentQuota, long estimatedSize, long totalUsedQuota,
                                                WebStorage.QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(estimatedSize * 2);
            }

            // 扩充缓存的容量
            @Override
            public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota,
                                                 WebStorage.QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(spaceNeeded * 2);
            }
        });
        //加载url地址
        loadUrl(homeH5Web);
//        LogUtil.e("getIMEi===" + getIMEINew(mContext));
    }

    public void loadUrl(WebView homeH5Web) {
        homeH5Web.loadUrl(" http://47.99.93.123:8085/?tenantId=" + CommonResource.TENANT_ID + "&" + "preProfit=" + SPUtil.getFloatValue(CommonResource.BACKBL) + "&" + "imei=" + imei);
    }

    /**
     * Pseudo-Unique ID, 这个在任何Android手机中都有效 解决手机中IMEI获取不到情况，兼容所有手机
     */
//    public static String getIMEINew(Context context) {
//        //we make this look like a valid IMEI
//        String m_szDevIDShort = "35" +
//                Build.BOARD.length() % 10 +
//                Build.BRAND.length() % 10 +
//                Build.CPU_ABI.length() % 10 +
//                Build.DEVICE.length() % 10 +
//                Build.DISPLAY.length() % 10 +
//                Build.HOST.length() % 10 +
//                Build.ID.length() % 10 +
//                Build.MANUFACTURER.length() % 10 +
//                Build.MODEL.length() % 10 +
//                Build.PRODUCT.length() % 10 +
//                Build.TAGS.length() % 10 +
//                Build.TYPE.length() % 10 +
//                Build.USER.length() % 10; //13 digits
//        return m_szDevIDShort;
//    }


    /**
     * Author: liuqiang
     * Time: 2017-08-14 15:28
     * Description:
     * <p>
     * IMEI 与你的手机是绑定关系 用于区别移动终端设备
     * IMSI 与你的手机卡是绑定关系 用于区别移动用户的有效信息 IMSI是用户的标识。
     * ICCID ICCID是卡的标识，由20位数字组成
     * ICCID只是用来区别SIM卡，不作接入网络的鉴权认证。而IMSI在接入网络的时候，会到运营商的服务器中进行验证。
     * https://github.com/android/platform_frameworks_base/blob/master/telephony/java/android/telephony/TelephonyManager.java
     */
    @SuppressLint("MissingPermission")
    public void check() {

        TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(TELEPHONY_SERVICE);// 取得相关系统服务

        String simOperatorName = telephonyManager.getSimOperatorName();
        //取出 IMEI
        imei = telephonyManager.getDeviceId();
        String imeiAPI26 = null;    //取出 IMEI 需要 api26以上
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            imeiAPI26 = telephonyManager.getImei();
        }
        String tel = telephonyManager.getLine1Number();   //取出 MSISDN，很可能为空
        String imsi = telephonyManager.getSubscriberId();   //取出 IMSI
        String icc = telephonyManager.getSimSerialNumber(); //取出 ICCID

        LogUtil.e("Q_M 运行商名字--" + simOperatorName);
        LogUtil.e("Q_M IMEI--" + imei);
        LogUtil.e("Q_M IMEI_API26--" + imeiAPI26);
        LogUtil.e("Q_M IMSI--" + imsi);
        LogUtil.e("Q_M ICCID--" + icc);//353515234688244
    }

}
