package com.example.order.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.order.fragment.OrderListFragment;

import java.util.List;

public class OrderVPAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<OrderListFragment> fragmentList;
    private String[] dataList;

    public OrderVPAdapter(FragmentManager fm, Context context, List<OrderListFragment> fragmentList, String[] dataList) {
        super(fm);
        this.context = context;
        this.fragmentList = fragmentList;
        this.dataList = dataList;
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return dataList[position];
    }
}
