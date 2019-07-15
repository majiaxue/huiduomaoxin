package com.example.location;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.view.wavesidebar.SearchEditText;
import com.example.view.wavesidebar.WaveSideBarView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 定位
 */
@Route(path = "/module_user_store/LocationActivity")
public class LocationActivity extends BaseActivity<LocationView, LocationPresenter> implements LocationView {

    @BindView(R2.id.location_image_back)
    ImageView locationImageBack;
    @BindView(R2.id.location_search)
    SearchEditText locationSearch;
    @BindView(R2.id.location_recycler)
    RecyclerView locationRecycler;
    @BindView(R2.id.location_side_bar)
    WaveSideBarView locationSideBar;
    @BindView(R2.id.location_select_recycler)
    RecyclerView locationSelectRecycler;
    @BindView(R2.id.location_relative)
    RelativeLayout locationRelative;
//    @BindView(R2.id.location_now_address)
//    TextView locationNowAddress;
//    @BindView(R2.id.location_recently_used_rec)
//    RecyclerView locationRecentlyUsedRec;
//    @BindView(R2.id.location_hot_city_rec)
//    RecyclerView locationHotCityRec;

    @Override
    public int getLayoutId() {
        return R.layout.activity_location;
    }

    @Override
    public void initData() {
        presenter.initView(locationSearch, locationRecycler, locationSelectRecycler, locationSideBar);

        //最近使用
//         presenter.locationRecentlyUsedRec(locationRecentlyUsedRec);

        //热门城市
//        presenter.locationHotCityRec(locationHotCityRec);


    }

    @Override
    public void initClick() {
        locationImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public LocationView createView() {
        return this;
    }

    @Override
    public LocationPresenter createPresenter() {
        return new LocationPresenter(this);
    }

    @Override
    public void whetherToHide(boolean isHide) {
        if (isHide) {
            locationRelative.setVisibility(View.GONE);
            locationSelectRecycler.setVisibility(View.VISIBLE);
        } else {
            locationRelative.setVisibility(View.VISIBLE);
            locationSelectRecycler.setVisibility(View.GONE);
        }
    }

}
