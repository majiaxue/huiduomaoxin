package com.example.utils;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.MyLocationData;

public class MyLocationListener extends BDAbstractLocationListener {
    /**
     * 纬度
     */
    public static double latitude;
    /**
     * 经度
     */
    public static double longitude;
    public static String city;
    public static MyLocationData locData;

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        int adCode = bdLocation.getLocType();
        latitude = bdLocation.getLatitude();    //获取纬度信息
        longitude = bdLocation.getLongitude();    //获取经度信息
        city = bdLocation.getCity(); //获取城市名字


        // 此处设置开发者获取到的方向信息，顺时针0-360
        locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(bdLocation.getDirection()).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        LogUtil.e("--------》纬度：" + latitude + "=====经度：" + longitude + "=====城市：" + city + "-----------getLocType:" + adCode);
    }
}
