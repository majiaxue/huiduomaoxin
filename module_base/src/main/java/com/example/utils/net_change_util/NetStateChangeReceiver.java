package com.example.utils.net_change_util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

public class NetStateChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            NetworkType networkType = NetworkUtil.getNetworkType(context);
            LogUtil.e("当前网络：" + networkType);
            if (networkType == NetworkType.NETWORK_NO) {
                SPUtil.setNetType(false);
            } else {
                SPUtil.setNetType(true);
//                if (ModuleBaseApplication.isDingWei) {
//                    ModuleBaseApplication.mLocationClient.restart();
//                }
            }
        }
    }
}
