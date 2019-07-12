package com.example.shakestock;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.shakestock.adapter.VideoRecAdapter;
import com.example.utils.ProcessDialogUtil;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JzvdStd;

public class ShakeStockPresenter extends BasePresenter<ShakeStockView> {

    private List<String> urlList;
    private LinearLayoutManager linearLayoutManager;
    private VideoRecAdapter videoRecAdapter;

    public ShakeStockPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void recyclerVideo(RecyclerView shakeStockRec){
        urlList = new ArrayList<>();
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201811/26/09/5bfb4c55633c9.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201805/100651/201805181532123423.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803151735198462.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150923220770.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150922255785.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803150920130302.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803141625005241.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803141624378522.mp4");
        urlList.add("http://chuangfen.oss-cn-hangzhou.aliyuncs.com/public/attachment/201803/100651/201803131546119319.mp4");

        final PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(shakeStockRec);

        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        videoRecAdapter = new VideoRecAdapter(mContext, urlList, R.layout.item_shake_stock_rec);
        shakeStockRec.setLayoutManager(linearLayoutManager);
        shakeStockRec.setAdapter(videoRecAdapter);

        shakeStockRec.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private JzvdStd videoView;
            private RecyclerView.ViewHolder viewHolder;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = pagerSnapHelper.findSnapView(linearLayoutManager);
                        JzvdStd.resetAllVideos();
                        viewHolder = recyclerView.getChildViewHolder(view);
                        if (viewHolder != null && viewHolder instanceof RecyclerViewHolder) {
                            videoView = ((RecyclerViewHolder) viewHolder).getView(R.id.shake_stock_rec_video);
                            videoView.startVideo();
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }
        });
        videoRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                VideoView shakeStockVideo = view.findViewById(R.id.shake_stock_rec_video);
                ImageView shakeStockVideoImage = view.findViewById(R.id.shake_stock_rec_video_image);

                if (shakeStockVideo.isPlaying()){
                    shakeStockVideo.pause();
                    shakeStockVideoImage.setVisibility(View.VISIBLE);
                }else{
                    shakeStockVideo.start();
                    shakeStockVideoImage.setVisibility(View.GONE);
                }
            }
        });

    }


}
