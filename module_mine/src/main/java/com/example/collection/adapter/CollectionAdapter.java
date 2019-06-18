package com.example.collection.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.MyCollectBean;
import com.example.entity.BaseRecBean;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;

import java.util.List;

public class CollectionAdapter extends MyRecyclerAdapter<MyCollectBean> {
    private boolean isEdit;

    public CollectionAdapter(Context context, List<MyCollectBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public CollectionAdapter(Context context, List<MyCollectBean> mList, int mLayoutId, boolean isEdit) {
        super(context, mList, mLayoutId);
        this.isEdit = isEdit;
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyCollectBean data, int position) {
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
        holder.setText(R.id.rv_collection_name, data.getGoodsName())
                .setText(R.id.rv_collection_reduce_price, "领券减" + ArithUtil.exact(ArithUtil.sub(data.getNormalPrice() * 0.01, data.getGroupPrice() == null ? 0 : data.getGroupPrice() * 0.01), 0) + "元")
                .setText(R.id.rv_collection_preferential_price, "￥" + ArithUtil.exact(data.getGroupPrice() == null ? 0 : data.getGroupPrice() * 0.01, 1))
                .setText(R.id.rv_collection_original_price, ArithUtil.exact(data.getNormalPrice() * 0.01, 1) + "")
                .setText(R.id.rv_collection_number, "已抢" + data.getQuantity() + "件")
                .setTextLine(R.id.rv_collection_original_price)
                .setImageFresco(R.id.rv_collection_image, data.getImage());
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }
}
