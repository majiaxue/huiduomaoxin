package com.example.h5home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.utils.AndroidJs;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import static android.content.Context.TELEPHONY_SERVICE;

public class H5HomePresenter extends BasePresenter<H5HomeView> {

    private static String imei;

    public H5HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

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
        webSettings.setAppCacheEnabled(false);
        webSettings.setAllowFileAccessFromFileURLs(false);
        //不加的话有些网页加载不出来，是空白
        webSettings.setDomStorageEnabled(true);
        //设置数据库
        webSettings.setDatabaseEnabled(true);

        //其他细节操作
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //没有网络时加载缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

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
        });
        //加载url地址
        homeH5Web.loadUrl(" http://47.99.93.123:8085/?tenantId=" + CommonResource.TENANT_ID + "&" + "preProfit=" + SPUtil.getFloatValue(CommonResource.BACKBL) + "&" + "imei=" + imei);
//        LogUtil.e("getIMEi===" + getIMEINew(mContext));
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
