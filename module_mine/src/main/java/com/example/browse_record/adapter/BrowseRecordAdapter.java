package com.example.browse_record.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.MyCollectBean;
import com.example.entity.BaseRecBean;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;

import java.util.List;

public class BrowseRecordAdapter extends MyRecyclerAdapter<MyCollectBean.GoodsSearchResponseBean.GoodsListBean> {
    public BrowseRecordAdapter(Context context, List<MyCollectBean.GoodsSearchResponseBean.GoodsListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyCollectBean.GoodsSearchResponseBean.GoodsListBean data, int position) {
        holder.setText(R.id.base_name, data.getGoods_name())
                .setText(R.id.base_reduce_price, "领券减" + ArithUtil.exact(ArithUtil.sub(data.getMin_normal_price() * 0.01, data.getMin_group_price() * 0.01), 0) + "元")
                .setText(R.id.base_preferential_price,"￥" + ArithUtil.exact(data.getMin_group_price() * 0.01, 1))
                .setText(R.id.base_original_price, ArithUtil.exact(data.getMin_normal_price() * 0.01, 1) + "")
                .setText(R.id.base_number, "已抢" + data.getSold_quantity() + "件")
                .setTextLine(R.id.base_original_price)
                .setImageFresco(R.id.base_image, data.getGoods_image_url());
    }
}
