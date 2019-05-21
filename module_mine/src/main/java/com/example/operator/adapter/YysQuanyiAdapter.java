package com.example.operator.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.YysQuanyiBean;
import com.example.module_mine.R;

import java.util.List;

public class YysQuanyiAdapter extends MyRecyclerAdapter<YysQuanyiBean> {
    public YysQuanyiAdapter(Context context, List<YysQuanyiBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, YysQuanyiBean data, int position) {
        holder.setText(R.id.rv_yys_quanyi_name, data.getName())
                .setText(R.id.rv_yys_quanyi_price, "￥" + data.getPrice());
        Glide.with(context).load(data.getUrl()).apply(RequestOptions.bitmapTransform(new RoundedCorners((int) context.getResources().getDimension(R.dimen.dp_5)))).into((ImageView) holder.getView(R.id.rv_yys_quanyi_img));
    }
}
