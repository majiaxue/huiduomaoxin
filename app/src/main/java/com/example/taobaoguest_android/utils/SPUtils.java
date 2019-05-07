package com.example.taobaoguest_android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


/**
 * Created by HXY on 2018/10/10.
 * Be used for : SharedPreferences工具类,主要用于用户登录状态的判断
 */

public class SPUtils {
    private Context context;
    private SharedPreferences.Editor userSPEditor;
    private SharedPreferences userSP;

//    //存入用户已登录状态
//    userSPEditor.putBoolean("isLogin",true);
//    //存入用户id
//    userSPEditor.putInt("uid",uid);
//    //存入用户Token
//    userSPEditor.putString("userToken",userToken);
//    //存入用户手机号
//    userSPEditor.putString("userPhone",userPhone);
//    //存入用户名
//    userSPEditor.putString("userName",userName);
//    //存入用户性别
//    userSPEditor.putString("userSex",userSex);
//    //存入用户年龄
//    userSPEditor.putString("userAge",userAge);
//    //是否实名认证 1已识别 2 未识别
//    userSPEditor.putString("authStatus",authStatus);
//    //常驻地
//    userSPEditor.putString("permanent",permanent);
//    //用户类型 1普通用户2商家
//    userSPEditor.putString("identity_type",identity_type);
//    //存入用户账号
//    userSPEditor.putString("account",account);

    public SPUtils(Context context) {
        this.context = context;
        //获取用户登录状态sharedPreferences对象
        Log.d("SPUtils", "SPUtils被创建时的上下文 : " + context);
        userSP = this.context.getSharedPreferences("userLoginStatus", Context.MODE_PRIVATE);
        //获取editor对象
        //获取编辑器
        userSPEditor = userSP.edit();
    }

    /**
     * 获取用户登录状态
     * @return
     */
    public boolean getUserLoginStatus(){
        boolean loginStatus = userSP.getBoolean("isLogin", false);
        return loginStatus;
    }

    /**
     * 清除用户数据,退出登录
     * @return
     */
    public void loginOut(){
        userSPEditor.clear();
        userSPEditor.commit();
    }

    /**
     * 获取用户信息数据库
     * @return
     */
    public SharedPreferences getSP(){
        return userSP;
    }

    /**
     * 获取用户信息编辑器
     * @return
     */
    public SharedPreferences.Editor getSPED(){
        return userSPEditor;
    }

}