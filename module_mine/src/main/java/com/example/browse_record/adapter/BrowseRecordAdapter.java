package com.example.browse_record.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.MyCollectBean;
import com.example.entity.BaseRecBean;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;

import java.util.List;

public class BrowseRecordAdapter extends MyRecyclerAdapter<MyCollectBean> {
    public BrowseRecordAdapter(Context context, List<MyCollectBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyCollectBean data, int position) {
        holder.setText(R.id.base_name, data.getGoodsName())
                .setText(R.id.base_reduce_price, "领券减" + ArithUtil.exact(ArithUtil.sub(data.getNormalPrice() * 0.01, data.getGroupPrice() * 0.01), 0) + "元")
                .setText(R.id.base_preferential_price, "￥" + ArithUtil.exact(data.getGroupPrice() * 0.01, 1))
                .setText(R.id.base_original_price, ArithUtil.exact(data.getNormalPrice() * 0.01, 1) + "")
                .setText(R.id.base_number, "已抢" + data.getQuantity() + "件")
                .setTextLine(R.id.base_original_price)
                .setImageFresco(R.id.base_image, data.getImage());
    }
}
