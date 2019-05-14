package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {
    private static SharedPreferences userSP;

    public static void getInstance(Context context) {
        //获取用户登录状态sharedPreferences对象
        userSP = context.getSharedPreferences("userLoginStatus", Context.MODE_PRIVATE);
    }

    /**
     * 获取用户登录状态
     *
     * @return
     */
    public static boolean isLogin() {
        boolean loginStatus = userSP.getBoolean("isLogin", false);
        return loginStatus;
    }

    public static void addParm(String key, String value) {
        userSP.edit().putString(key, value).commit();
    }

    public static void addParm(String key, boolean value) {
        userSP.edit().putBoolean(key, value).commit();
    }

    public static void addParm(String key, int value) {
        userSP.edit().putInt(key, value).commit();
    }

    public static String getStringValue(String key) {
        return userSP.getString(key, "");
    }

    public static int getIntValue(String key) {
        return userSP.getInt(key, -1);
    }

    public static boolean getBooleanValue(String key) {
        return userSP.getBoolean(key, false);
    }

    /**
     * 清除用户数据,退出登录
     *
     * @return
     */
    public static void loginOut() {
        userSP.edit().clear().commit();
    }
}
