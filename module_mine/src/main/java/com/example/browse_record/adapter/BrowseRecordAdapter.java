package com.example.browse_record.adapter;

import android.content.Context;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.MyCollectBean;
import com.example.entity.BaseRecBean;
import com.example.module_mine.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;

import java.util.List;

public class BrowseRecordAdapter extends MyRecyclerAdapter<MyCollectBean> {
    public BrowseRecordAdapter(Context context, List<MyCollectBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, MyCollectBean data, int position) {
        if (data.getNormalPrice() != null && data.getNormalPrice().contains("-")) {
            String[] split = data.getNormalPrice().split("-");
            data.setNormalPrice(split[0]);
        }

        if (data.getNormalPrice() == null) {
            data.setNormalPrice("0");
        }
        if (data.getGroupPrice() == null) {
            data.setGroupPrice(0.0);
        }

        holder.setText(R.id.base_name, data.getGoodsName())
                .setText(R.id.base_reduce_price, "领券减" + ArithUtil.exact(ArithUtil.sub(Double.valueOf(data.getNormalPrice()), data.getGroupPrice() * 0.01), 0) + "元")
                .setText(R.id.base_preferential_price, "￥" + ArithUtil.exact(data.getGroupPrice() * 0.01, 1))
                .setText(R.id.base_original_price, ArithUtil.exact(Double.valueOf(data.getNormalPrice()), 1) + "")
                .setText(R.id.base_number, "已抢" + data.getQuantity() + "件")
                .setTextLine(R.id.base_original_price)
                .setImageFresco(R.id.base_image, data.getImage());
        if (data.getType() == 0) {
            holder.setImageResource(R.id.base_type, R.drawable.taobao);
        } else if (data.getType() == 1) {
            holder.setImageResource(R.id.base_type, R.drawable.jingdong);
        } else if (data.getType() == 2) {
            holder.setImageResource(R.id.base_type, R.drawable.pinduoduo);
        }
    }
}
