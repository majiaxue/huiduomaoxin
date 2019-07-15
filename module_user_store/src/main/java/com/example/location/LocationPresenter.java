package com.example.location;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.bean.RegionBean;
import com.example.location.adapter.LocationHotCityAdapter;
import com.example.location.adapter.LocationSelectAdapter;
import com.example.location.adapter.RegionAdapter;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;
import com.example.view.wavesidebar.SearchEditText;
import com.example.view.wavesidebar.WaveSideBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationPresenter extends BasePresenter<LocationView> {

    private List<RegionBean> mList = new ArrayList<>();
    private List<String> selectList = new ArrayList<>();

    public LocationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(SearchEditText locationSearch, final RecyclerView locationRecycler, final RecyclerView locationSelectRecycler, WaveSideBarView locationSideBar) {

        mList.add(new RegionBean("A", Arrays.asList("鞍山", "安阳")));
        mList.add(new RegionBean("B", Arrays.asList("北京", "白河", "宝鸡")));
        mList.add(new RegionBean("C", Arrays.asList("重庆", "长春", "长沙")));
        mList.add(new RegionBean("D", Arrays.asList("大明湖", "敦煌", "德惠")));
        mList.add(new RegionBean("E", Arrays.asList("二连", "峨眉")));
        mList.add(new RegionBean("F", Arrays.asList("福建", "佛山")));
        mList.add(new RegionBean("G", Arrays.asList("广州", "广汉", "广西")));
        mList.add(new RegionBean("H", Arrays.asList("哈尔滨", "合肥", "杭州")));
        mList.add(new RegionBean("J", Arrays.asList("济南", "吉安", "景德镇")));
        mList.add(new RegionBean("K", Arrays.asList("昆明", "开安", "开封")));
        mList.add(new RegionBean("L", Arrays.asList("拉萨", "兰州", "临川")));
        mList.add(new RegionBean("M", Arrays.asList("牡丹江", "漠河")));
        mList.add(new RegionBean("N", Arrays.asList("南京", "南宁", "嫩河")));
        mList.add(new RegionBean("P", Arrays.asList("平顶山", "攀枝花", "平安")));
        mList.add(new RegionBean("Q", Arrays.asList("青岛", "泉州", "青龙")));
        mList.add(new RegionBean("R", Arrays.asList("融安", "瑞荣")));
        mList.add(new RegionBean("S", Arrays.asList("上海", "沈阳", "石家庄")));
        mList.add(new RegionBean("T", Arrays.asList("天津", "太原", "潼关")));
        mList.add(new RegionBean("W", Arrays.asList("武汉", "武隆", "乌兰浩特")));
        mList.add(new RegionBean("X", Arrays.asList("厦门", "西安", "西宁")));
        mList.add(new RegionBean("Y", Arrays.asList("银川", "盐城", "延安")));
        mList.add(new RegionBean("Z", Arrays.asList("郑州", "周口", "张家界")));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        locationRecycler.setLayoutManager(linearLayoutManager);
        RegionAdapter regionAdapter = new RegionAdapter(mContext, mList, R.layout.item_location_rec);
        locationRecycler.setAdapter(regionAdapter);

        locationSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                selectList.clear();
                if (s.toString().equals("")) {
                    getView().whetherToHide(false);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                    locationRecycler.setLayoutManager(linearLayoutManager);
                    RegionAdapter regionAdapter = new RegionAdapter(mContext, mList, R.layout.item_location_rec);
                    locationRecycler.setAdapter(regionAdapter);
                } else {
                    getView().whetherToHide(true);
                    //向selectList添加数据
                    for (int i = 0; i < mList.size(); i++) {
                        List<String> regionList = mList.get(i).getRegionList();
                        for (int j = 0; j < regionList.size(); j++) {
                            if (regionList.get(j).contains(s.toString())) {
                                selectList.add(regionList.get(j));
                            }
                        }
                    }
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    locationSelectRecycler.setLayoutManager(linearLayoutManager1);
                    LocationSelectAdapter locationSelectAdapter = new LocationSelectAdapter(mContext, selectList, R.layout.item_location_select_rec);
                    locationSelectRecycler.setAdapter(locationSelectAdapter);
                }
            }
        });

        locationSideBar.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getLetter().equals(letter)) {
                        locationRecycler.smoothScrollToPosition(i);
                    }
                }
            }
        });
    }

    public void locationRecentlyUsedRec(RecyclerView locationRecentlyUsedRec) {

    }

    public void locationHotCityRec(RecyclerView locationHotCityRec) {

        List<String> hotCity = new ArrayList<>();
        hotCity.add("北京");
        hotCity.add("上海");
        hotCity.add("广州");
        hotCity.add("深圳");
        hotCity.add("杭州");
        hotCity.add("郑州");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,4, LinearLayoutManager.VERTICAL, false);
        locationHotCityRec.setLayoutManager(gridLayoutManager);
        LocationHotCityAdapter locationHotCityAdapter = new LocationHotCityAdapter(mContext, hotCity, R.layout.item_location_hot_city_rec);
        locationHotCityRec.setAdapter(locationHotCityAdapter);
    }
}
