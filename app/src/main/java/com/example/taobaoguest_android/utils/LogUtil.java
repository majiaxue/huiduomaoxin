package com.example.taobaoguest_android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * Created by Administrator on 2019/3/21.
 */

public class LogUtil {
    private static boolean isDebug;

    public static void e(String message) {
        if (isDebug) {
            Log.e("tag", message);
        }
    }

    public static boolean isDebug(Context context) {
        isDebug = context.getApplicationInfo() != null &&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        context = null;
        return isDebug;
    }
}
