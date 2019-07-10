package com.example.community.goods_commend;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.TitleBean;
import com.example.community.adapter.CommendTitleAdapter;
import com.example.community.adapter.GoodsCommendAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;
import java.util.List;

public class GoodsCommendPresenter extends BasePresenter<GoodsCommendView> {

    private GoodsCommendAdapter commendAdapter;

    public GoodsCommendPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTitle() {
        final List<TitleBean> titleList = new ArrayList<>();
        titleList.add(new TitleBean("全部", true));
        titleList.add(new TitleBean("淘宝", false));
        titleList.add(new TitleBean("京东", false));
        titleList.add(new TitleBean("拼多多", false));

        final CommendTitleAdapter titleAdapter = new CommendTitleAdapter(mContext, titleList, R.layout.rv_commend_title);

        titleAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                for (int i = 0; i < titleList.size(); i++) {
                    titleList.get(i).setCheck(i == position);
                }
                titleAdapter.notifyDataSetChanged();

            }
        });

        if (getView() != null) {
            getView().loadTitle(titleAdapter);
        }
    }

    public void initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("a");
        }
        commendAdapter = new GoodsCommendAdapter(mContext, list, R.layout.rv_goods_commend);

        commendAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        share();
                    }
                });
            }
        });

        if (getView() != null) {
            getView().loadContent(commendAdapter);
        }
    }

    public void share() {
        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, R.drawable.img_temp))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {

                    }
                }).open();
    }
}
