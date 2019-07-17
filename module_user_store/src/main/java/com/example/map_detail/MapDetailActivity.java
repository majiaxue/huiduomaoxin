package com.example.map_detail;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;
import com.example.utils.MyLocationListener;

import butterknife.BindView;

public class MapDetailActivity extends BaseActivity<MapDetailView, MapDetailPresenter> implements MapDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.map_detail_mapview)
    MapView mapDetailMapview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_map_detail;
    }

    @Override
    public void initData() {
        includeTitle.setText("位置");
        Intent intent = getIntent();
        String lat = intent.getStringExtra("lat");
        String lon = intent.getStringExtra("lon");
        LogUtil.e("weidu:" + lat + "-----------jingdu:" + lon);
//        LatLng latLng = new LatLng(Double.valueOf(lat), Double.valueOf(lon));
        LatLng latLng = new LatLng(MyLocationListener.latitude, MyLocationListener.longitude);
        MapStatusUpdate statusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
        mapDetailMapview.getMap().setMapStatus(statusUpdate);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapDetailMapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapDetailMapview.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapDetailMapview.onDestroy();
    }

    @Override
    public MapDetailView createView() {
        return this;
    }

    @Override
    public MapDetailPresenter createPresenter() {
        return new MapDetailPresenter(this);
    }

}
