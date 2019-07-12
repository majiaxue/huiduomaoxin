package com.example.shakestock.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;

import java.util.List;

import cn.jzvd.JzvdStd;

public class VideoRecAdapter extends MyRecyclerAdapter<String> {

    public VideoRecAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

        JzvdStd jzvdStd = holder.getView(R.id.shake_stock_rec_video);
//        shakeStockRecVideo.setVideoPath(data);
//        jzvdStd.setVideoURI(Uri.parse(data));
        jzvdStd.setUp(data,"");
        if (position==0){
            jzvdStd.startVideo();
        }
        Glide.with(context).load(data).into(jzvdStd.thumbImageView);
    }
}
