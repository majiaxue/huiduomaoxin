package com.example.browse_record.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.BaseRecBean;
import com.example.module_mine.R;

import java.util.List;

public class BrowseRecordAdapter extends MyRecyclerAdapter<BaseRecBean> {
    public BrowseRecordAdapter(Context context, List<BaseRecBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, BaseRecBean data, int position) {
        holder.setText(R.id.base_name, data.getName())
                .setText(R.id.base_reduce_price, data.getReduce_price())
                .setText(R.id.base_preferential_price, data.getPreferential_price())
                .setText(R.id.base_original_price, data.getOriginal_price())
                .setText(R.id.base_number, "已抢" + data.getNumber() + "件")
                .setTextLine(R.id.base_original_price)
                .setImageFresco(R.id.base_image, data.getImgUrl());
    }
}
