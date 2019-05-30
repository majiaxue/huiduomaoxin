package com.example.assess.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.entity.AssessBean;
import com.example.user_store.R;
import com.example.utils.SpaceItemDecoration;
import com.example.view.RatingBarView;

import java.util.List;

public class AssessAdapter extends MyRecyclerAdapter<AssessBean> {
    private AssessImageAdapter imageAdapter;

    public AssessAdapter(Context context, List<AssessBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(final RecyclerViewHolder holder, AssessBean data, final int position) {
        holder.setImageUrlCircular(R.id.rv_assess_header, data.getImgUrl())
                .setText(R.id.rv_assess_name, data.getName())
                .setText(R.id.rv_assess_content, data.getContent())
                .setText(R.id.rv_assess_time, data.getTime() + "  尺码：" + data.getSize() + "  颜色：" + data.getColor())
                .setText(R.id.rv_assess_count_zan, data.getZanCount() + "")
                .setText(R.id.rv_assess_count_assess, data.getAssessCount() + "");
        RatingBarView ratingBar = holder.getView(R.id.rv_assess_ratingbar);
        ratingBar.setStar(data.getXingji(), false);
        ratingBar.setClickable(false);

        RecyclerView rv = holder.getView(R.id.rv_assess_pic);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(gridLayoutManager);
        rv.addItemDecoration(new SpaceItemDecoration(0, 5, 0, 5));
        imageAdapter = new AssessImageAdapter(context, data.getImgList(), R.layout.rv_assess_rv_img);
        rv.setAdapter(imageAdapter);
        imageAdapter.setViewOnClickListener(new ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                if (fiveViewClickListener != null) {
                    fiveViewClickListener.fiveViewClick((TextView) holder.getView(R.id.rv_assess_count_zan), (ImageView) holder.getView(R.id.rv_assess_zan), (TextView) holder.getView(R.id.rv_assess_count_assess), (ImageView) holder.getView(R.id.rv_assess_toassess), position, (ImageView) view, index);
                }
            }
        });
    }
}
