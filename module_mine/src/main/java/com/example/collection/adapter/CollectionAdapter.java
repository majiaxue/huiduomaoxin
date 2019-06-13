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

public class CollectionAdapter extends MyRecyclerAdapter<MyCollectBean.GoodsSearchResponseBean.GoodsListBean> {
    private boolean isEdit;

    public CollectionAdapter(Context context, List<MyCollectBean.GoodsSearchResponseBean.GoodsListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    public CollectionAdapter(Context context, List<MyCollectBean.GoodsSearchResponseBean.GoodsListBean> mList, int mLayoutId, boolean isEdit) {
        super(context, mList, mLayoutId);
        this.isEdit = isEdit;
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyCollectBean.GoodsSearchResponseBean.GoodsListBean data, int position) {
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
        holder.setText(R.id.rv_collection_name, data.getGoods_name())
                .setText(R.id.rv_collection_reduce_price, "领券减" + ArithUtil.exact(ArithUtil.sub(data.getMin_normal_price() * 0.01, data.getMin_group_price() * 0.01), 0) + "元")
                .setText(R.id.rv_collection_preferential_price, "￥" + ArithUtil.exact(data.getMin_group_price() * 0.01, 1))
                .setText(R.id.rv_collection_original_price, ArithUtil.exact(data.getMin_normal_price() * 0.01, 1) + "")
                .setText(R.id.rv_collection_number, "已抢" + data.getSold_quantity() + "件")
                .setTextLine(R.id.rv_collection_original_price)
                .setImageFresco(R.id.rv_collection_image, data.getGoods_image_url());
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
        notifyDataSetChanged();
    }
}
