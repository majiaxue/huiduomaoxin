package com.example.shippingaddress.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_user_mine.R;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/29
 * Describe:
 */
public class PopupRecAdapter extends MyRecyclerAdapter {

    private List<String> list;

    public PopupRecAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
        this.list = mList;
    }

    @Override
    public void convert(RecyclerViewHolder holder, Object data, int position) {
        holder.setText(R.id.place_name, list.get(position));
    }

}
