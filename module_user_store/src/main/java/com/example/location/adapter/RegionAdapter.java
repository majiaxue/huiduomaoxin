package com.example.location.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.RegionBean;
import com.example.user_store.R;

import org.w3c.dom.Text;

import java.util.List;

public class RegionAdapter extends MyRecyclerAdapter<RegionBean.CityBean> {

    private static int RESULTCODE = 0;

    public RegionAdapter(Context context, List<RegionBean.CityBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RegionBean.CityBean data, int position) {

        holder.setText(R.id.item_letter, data.getLabel());
        RecyclerView itemRegionList = holder.getView(R.id.item_regionList);

        if (position < 3) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
            LocationHotCityAdapter locationHotCityAdapter = new LocationHotCityAdapter(context, data.getRegion(), R.layout.item_location_hot_city_rec);
            itemRegionList.setLayoutManager(gridLayoutManager);
            itemRegionList.setAdapter(locationHotCityAdapter);
            locationHotCityAdapter.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView parent, View view, int position) {
                    TextView hotCityName = view.findViewById(R.id.location_hot_city_name);
                    Intent intent = new Intent();
                    intent.putExtra("cityName", hotCityName.getText().toString());
                    ((Activity) context).setResult(RESULTCODE, intent);
                    ((Activity) context).finish();
//                    Toast.makeText(context, "" + hotCityName.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            LocationSelectAdapter locationSelectAdapter = new LocationSelectAdapter(context, data.getRegion(), R.layout.item_location_select_rec);
            itemRegionList.setLayoutManager(linearLayoutManager);
            itemRegionList.setAdapter(locationSelectAdapter);
            locationSelectAdapter.setOnItemClick(new OnItemClickListener() {
                @Override
                public void onItemClick(RecyclerView parent, View view, int position) {
                    TextView itemText = view.findViewById(R.id.item_text);
                    Intent intent = new Intent();
                    intent.putExtra("cityName", itemText.getText().toString());
                    ((Activity) context).setResult(RESULTCODE, intent);
                    ((Activity) context).finish();
//                    Toast.makeText(context, ""+itemText.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }


}
