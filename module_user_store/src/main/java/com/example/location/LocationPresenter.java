package com.example.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.CityListBean;
import com.example.bean.RegionBean;
import com.example.location.adapter.LocationSelectAdapter;
import com.example.location.adapter.RegionAdapter;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.view.wavesidebar.SearchEditText;
import com.example.view.wavesidebar.WaveSideBarView;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationPresenter extends BasePresenter<LocationView> {

    private List<RegionBean.CityBean> mList = new ArrayList<>();
    private List<String> selectList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private List<CityListBean> listBeans = new ArrayList<>();
    private static int RESULTCODE = 0;

    public LocationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(SearchEditText locationSearch, final RecyclerView locationRecycler, final RecyclerView locationSelectRecycler, WaveSideBarView locationSideBar, String cityName) {
        mList.add(new RegionBean.CityBean("您正在看:", Arrays.asList(cityName)));
        mList.add(new RegionBean.CityBean("定位/最近使用", Arrays.asList("郑州", "北京", "上海")));
        mList.add(new RegionBean.CityBean("热门城市", Arrays.asList("北京", "上海", "广州", "深圳", "杭州", "郑州")));

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(mContext.getAssets().open("city.json"), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            JSONArray jsonArray = jsonObject.getJSONArray("city");
            for (int i = 0; i < jsonArray.length(); i++) {
//                LogUtil.e("数组长度" + jsonArray.length());
//                LogUtil.e("数组内容" + jsonArray.get(i).toString());
                CityListBean cityListBean = new Gson().fromJson(jsonArray.get(i).toString(), CityListBean.class);
                mList.add(new RegionBean.CityBean(cityListBean.getLabel(),cityListBean.getRegion()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        linearLayoutManager = new LinearLayoutManager(mContext);
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
                    for (int i = 3; i < mList.size(); i++) {
                        List<String> regionList = mList.get(i).getRegion();
                        for (int j = 0; j < regionList.size(); j++) {
                            if (regionList.get(j).contains(s.toString())) {
                                selectList.add(regionList.get(j));
                            }
                        }
                    }
                    LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    LocationSelectAdapter locationSelectAdapter = new LocationSelectAdapter(mContext, selectList, R.layout.item_location_select_rec);
                    locationSelectRecycler.setLayoutManager(linearLayoutManager1);
                    locationSelectRecycler.setAdapter(locationSelectAdapter);
                    locationSelectAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            TextView name = view.findViewById(R.id.item_text);
                            Intent intent = new Intent();
                            intent.putExtra("cityName", name.getText().toString());
                            ((Activity) mContext).setResult(RESULTCODE, intent);
                            ((Activity) mContext).finish();
                        }
                    });

                }
            }
        });

        locationSideBar.setOnSelectIndexItemListener(new WaveSideBarView.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String letter) {
                for (int i = 0; i < mList.size(); i++) {
                    if (mList.get(i).getLabel().equals(letter)) {
                        linearLayoutManager.scrollToPositionWithOffset(i, 0);
                    }
                    if ("#".equals(letter)) {
                        linearLayoutManager.scrollToPositionWithOffset(0, 0);
                    }
                }
            }
        });
    }

}
