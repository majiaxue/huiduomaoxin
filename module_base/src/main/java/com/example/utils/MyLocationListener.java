package com.example.utils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

public class MyLocationListener extends BDAbstractLocationListener {
    /**
     * 纬度
     */
    public static double latitude;
    /**
     * 经度
     */
    public static double longitude;

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        int adCode = bdLocation.getLocType();
        latitude = bdLocation.getLatitude();    //获取纬度信息
        longitude = bdLocation.getLongitude();    //获取经度信息
        LogUtil.e("--------》纬度：" + latitude + "=====经度：" + longitude + "-----------getLocType:" + adCode);
    }
}
