package com.example.collection.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.BaseRecBean;
import com.example.module_mine.R;

import java.util.List;

public class CollectionAdapter extends MyRecyclerAdapter<BaseRecBean> {
    private boolean isEdit;

    public CollectionAdapter(Context context, List<BaseRecBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public CollectionAdapter(Context context, List<BaseRecBean> mList, int mLayoutId, boolean isEdit) {
        super(context, mList, mLayoutId);
        this.isEdit = isEdit;
    }

    @Override
    public void convert(RecyclerViewHolder holder, BaseRecBean data, int position) {
        if (isEdit) {
            holder.getView(R.id.rv_collection_check).setVisibility(View.VISIBLE);
            holder.getView(R.id.rv_collection_immediately_grab).setVisibility(View.GONE);
            holder.getView(R.id.rv_collection_original_price).setVisibility(View.GONE);
        } else {
            holder.getView(R.id.rv_collection_check).setVisibility(View.GONE);
            holder.getView(R.id.rv_collection_immediately_grab).setVisibility(View.VISIBLE);
            holder.getView(R.id.rv_collection_original_price).setVisibility(View.VISIBLE);
        }

        if (data.isCheck()) {
            holder.setImageResource(R.id.rv_collection_check, R.drawable.icon_xuanzhong);
        } else {
            holder.setImageResource(R.id.rv_collection_check, R.drawable.vghfgdg);
        }
        holder.setText(R.id.rv_collection_name, data.getName())
                .setText(R.id.rv_collection_reduce_price, data.getReduce_price())
                .setText(R.id.rv_collection_preferential_price, "￥" + data.getPreferential_price())
                .setText(R.id.rv_collection_original_price, data.getOriginal_price())
                .setText(R.id.rv_collection_number, "已抢" + data.getNumber() + "件")
                .setTextLine(R.id.rv_collection_original_price)
                .setProgressBar(R.id.rv_collection_progress, (int) (100.0 * Integer.valueOf(data.getNumber()) / Integer.valueOf(data.getTotalCount())))
                .setImageFresco(R.id.rv_collection_image, data.getImgUrl());
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }
}
