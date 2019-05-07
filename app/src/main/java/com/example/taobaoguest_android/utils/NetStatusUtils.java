package com.example.taobaoguest_android.utils;

/**
 * Created by chh on 2019/4/30.
 * Be used for : 工具类之获取网络状态
 */

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class NetStatusUtils {

    private NetStatusUtils() {
    }

    private static NetStatusUtils netUtils = null;

    //静态工厂方法
    public static NetStatusUtils getInstance() {
        if (netUtils == null) {
            synchronized (NetStatusUtils.class) {
                if (netUtils == null) {
                    netUtils = new NetStatusUtils();
                }
            }
        }
        return netUtils;
    }

    /**
     * 设置网络状态
     */
    public void setNetState(final Context context) {
        int netType = NetStatusUtils.getAPNType(context);

        if (netType == 0) {
            //无网络连接，打开网络设置页面
            showSetNetworkUI(context);
        } else if (netType == 1) {
            //WIFI连接
            Toast.makeText(context, "当前为WIFI连接...", Toast.LENGTH_SHORT).show();
        } else {
            //数据连接
            new AlertDialog.Builder(context)
                    .setTitle("确认网络")
                    .setMessage("当前为蜂窝网数据连接，是否切换到WIFI网络？")
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            dialog.dismiss();
                        }
                    })
                    .setPositiveButton("前往设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            // TODO Auto-generated method stub
                            Intent intent = null;
                            // 判断手机系统的版本 即API大于10 就是3.0或以上版本
                            if (Build.VERSION.SDK_INT > 10) {
                                intent = new Intent(
                                        Settings.ACTION_WIFI_SETTINGS);
                            } else {
                                intent = new Intent();
                                ComponentName component = new ComponentName(
                                        "com.android.settings",
                                        "com.android.settings.WirelessSettings");
                                intent.setComponent(component);
                                intent.setAction("android.intent.action.VIEW");
                            }
                            dialog.dismiss();
                            context.startActivity(intent);
                        }
                    }).show();
        }
    }

    /**
     * 打开设置网络界面
     */
    private void showSetNetworkUI(final Context context) {
        // 提示对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("网络设置提示")
                .setMessage("网络连接不可用,是否进行设置?")
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Intent intent = null;
                        // 判断手机系统的版本 即API大于10 就是3.0或以上版本
                        if (Build.VERSION.SDK_INT > 10) {
                            intent = new Intent(
                                    Settings.ACTION_WIFI_SETTINGS);
                        } else {
                            intent = new Intent();
                            ComponentName component = new ComponentName(
                                    "com.android.settings",
                                    "com.android.settings.WirelessSettings");
                            intent.setComponent(component);
                            intent.setAction("android.intent.action.VIEW");
                        }
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                }).show();
    }


    private static boolean isNetworkAvailable(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return false;
        }

        return true;
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @param context
     * @return
     */
    private static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (mWiFiNetworkInfo != null) {
                return mWiFiNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     */
    private static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    /**
     * 获取当前网络连接的类型信息
     *
     * @param context
     * @return
     */
    private static int getConnectedType(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }


    /**
     * 获取当前的网络状态 ：没有网络0：WIFI网络1：3G网络2：2G网络3
     *
     * @param context
     * @return
     */
    private static int getAPNType(Context context) {
        int netType = 0;
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1;// wifi
        } else if (nType == ConnectivityManager.TYPE_MOBILE) {
            int nSubType = networkInfo.getSubtype();
            TelephonyManager mTelephony = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);
            if (nSubType == TelephonyManager.NETWORK_TYPE_UMTS
                    && !mTelephony.isNetworkRoaming()) {
                netType = 2;// 3G
            } else {
                netType = 3;// 2G
            }
        }
        return netType;
    }
}
