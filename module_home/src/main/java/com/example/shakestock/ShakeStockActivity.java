package com.example.shakestock;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * 抖劵购买
 */
@Route(path = "/module_home/ShakeStockActivity")
public class ShakeStockActivity extends BaseActivity<ShakeStockView, ShakeStockPresenter> implements ShakeStockView {


    @BindView(R2.id.shake_stock_image_back)
    ImageView shakeStockImageBack;
    @BindView(R2.id.shake_stock_rec)
    RecyclerView shakeStockRec;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shake_stock;
    }

    @Override
    public void initData() {
        StatusBarUtils.setStatusBar(this, getResources().getColor(R.color.black));

        presenter.recyclerVideo(shakeStockRec);

//        String url = "http://video.haodanku.com/ff02cd42e310c04251892b53d13e8d63?attname=1562752503.mp4";
//        shakeStockVideo.setVideoPath(url);
//        shakeStockVideo.seekTo(0);
//        shakeStockVideo.requestFocus();
//        shakeStockVideo.start();
//        shakeStockVideo.stopPlayback();
    }

    @Override
    public void initClick() {
        shakeStockImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//
//        shakeStockVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (shakeStockVideo.isPlaying()){
//                    shakeStockVideo.pause();
//                    shakeStockVideoImage.setVisibility(View.VISIBLE);
//                }else{
//                    shakeStockVideo.start();
//                    shakeStockVideoImage.setVisibility(View.GONE);
//                }
//            }
//        });
//
//        shakeStockVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.start();
//                mp.setLooping(true);
//            }
//        });


    }

    @Override
    public void onPause() {
        super.onPause();
        JzvdStd.resetAllVideos();

    }

    @Override
    public ShakeStockView createView() {
        return this;
    }

    @Override
    public ShakeStockPresenter createPresenter() {
        return new ShakeStockPresenter(this);
    }

}
