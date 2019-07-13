package com.example.shakestock;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.bean.VideoRecBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.shakestock.adapter.VideoRecAdapter;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.jzvd.JzvdStd;
import io.reactivex.Observable;

public class ShakeStockPresenter extends BasePresenter<ShakeStockView> {

    private LinearLayoutManager linearLayoutManager;
    private VideoRecAdapter videoRecAdapter;
    private List<VideoRecBean.DataBean> videoList = new ArrayList<>();

    public ShakeStockPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void recyclerVideo(final RecyclerView shakeStockRec) {
        Map map = MapUtil.getInstance().addParms("min_id", 1).build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSGETTRILLDATA, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("ShakeStockPresenterResult" + result);
                final VideoRecBean videoRecBean = JSON.parseObject(result, new TypeReference<VideoRecBean>() {
                }.getType());
                if (videoRecBean != null && videoRecBean.getData() != null) {
                    videoList.addAll(videoRecBean.getData());

                    final PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
                    pagerSnapHelper.attachToRecyclerView(shakeStockRec);

                    linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    videoRecAdapter = new VideoRecAdapter(mContext, videoList, R.layout.item_shake_stock_rec);
                    shakeStockRec.setLayoutManager(linearLayoutManager);
                    shakeStockRec.setAdapter(videoRecAdapter);

                    videoRecAdapter.setViewFourOnClickListener(new MyRecyclerAdapter.ViewFourOnClickListener() {
                        @Override
                        public void ViewFourOnClick(View view1, View view2, final View view3, View view4, final int position) {
                            view1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    JzvdStd shakeStockVideo = v.findViewById(R.id.shake_stock_rec_video);
//                                    if (shakeStockVideo.isPlaying()) {
//                                        shakeStockVideo.pause();
//                                    } else {
//                                        shakeStockVideo.start();
//                                    }
                                }
                            });
                            //去购买
                            view2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ARouter.getInstance()
                                            .build("/module_classify/TBCommodityDetailsActivity")
                                            .withString("para", videoList.get(position).getItemid())
                                            .withString("shoptype", videoList.get(position).getShoptype())
                                            .navigation();
                                }
                            });
                            //收藏
                            view3.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(final View v) {
                                    final ImageView image = v.findViewById(R.id.shake_stock_rec_collect_image);
                                    Map map = MapUtil.getInstance().addParms("productId", videoList.get(position).getItemid()).addParms("type", 4).build();
                                    Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT, map, SPUtil.getToken());
                                    RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
                                        @Override
                                        public void onSuccess(String result, String msg) {
                                            LogUtil.e("ShakeStockPresenterResult点击收藏----->" + result);
                                            if (result.equals("true")) {
//                                                videoRecBean.getData().get(position).setShouCang(true);
                                                image.setImageResource(R.drawable.icon_shoucang_red);
                                            } else {
//                                                videoRecBean.getData().get(position).setShouCang(false);
                                                image.setImageResource(R.drawable.icon_shoucang);
                                            }
                                        }

                                        @Override
                                        public void onError(String errorCode, String errorMsg) {
                                            LogUtil.e("ShakeStockPresenterErrorMsg点击收藏----->" + errorMsg);

                                        }
                                    }));
                                }
                            });
                            //分享
                            view4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {


                                }
                            });
                        }
                    });

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

                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));


    }


}
