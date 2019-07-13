package com.example.location.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.RegionBean;
import com.example.user_store.R;

import java.util.List;

public class RegionAdapter extends MyRecyclerAdapter<RegionBean> {

    public RegionAdapter(Context context, List<RegionBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, RegionBean data, int position) {


        holder.setText(R.id.item_letter, data.getLetter());
        RecyclerView itemRegionList = holder.getView(R.id.item_regionList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        LocationSelectAdapter locationSelectAdapter = new LocationSelectAdapter(context, data.getRegionList(), R.layout.item_location_select_rec);
        itemRegionList.setLayoutManager(linearLayoutManager);
        itemRegionList.setAdapter(locationSelectAdapter);

    }


}
