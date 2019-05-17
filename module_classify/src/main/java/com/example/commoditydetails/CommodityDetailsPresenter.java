package com.example.commoditydetails;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.CommodityRecommendRecAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.CommodityRecommendBean;
import com.example.bean.CommodityXBannerBean;
import com.example.module_shoppingmall.R;
import com.example.mvp.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class CommodityDetailsPresenter extends BasePresenter<CommodityDetailsView> {

    private List<CommodityXBannerBean> images;
    private List<CommodityRecommendBean> commodityRecommendBeanList;


    public CommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }


    public void setXBanner(XBanner commodityXbanner) {
        images = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            images.add(new CommodityXBannerBean(R.drawable.image_7));
        }

//        homeXbanner.setData(images, null);
        commodityXbanner.setBannerData(R.layout.image_fresco, images);
        commodityXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
            }
        });
        // 设置XBanner的页面切换特效
        commodityXbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        commodityXbanner.setPageChangeDuration(1000);

        //监听广告 item 的单击事件
        commodityXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //推荐
    public void setRecommendRec(RecyclerView shopRecommendRec) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        if (shopRecommendRec.getItemDecorationCount() == 0) {
            shopRecommendRec.addItemDecoration(new DividerItemDecoration(mContext, LinearLayout.VERTICAL));
        }
        shopRecommendRec.setLayoutManager(linearLayoutManager);
        commodityRecommendBeanList = new ArrayList<>();
        commodityRecommendBeanList.add(new CommodityRecommendBean(R.drawable.reco1, "稙优泉化妆品买...", "领券减50元", "95.50", "123", "已抢64120件"));
        commodityRecommendBeanList.add(new CommodityRecommendBean(R.drawable.reco2, "有机护肤化妆品...", "领券减50元", "26.50", "123", "已抢64120件"));
        commodityRecommendBeanList.add(new CommodityRecommendBean(R.drawable.reco3, "美容美妆教学...", "领券减50元", "42.80", "123", "已抢64120件"));
        commodityRecommendBeanList.add(new CommodityRecommendBean(R.drawable.reco4, "美容美妆教学...", "领券减50元", "42.80", "123", "已抢64120件"));
        CommodityRecommendRecAdapter recommendRecAdapter = new CommodityRecommendRecAdapter(mContext, commodityRecommendBeanList, R.layout.item_commodity_recommend_rec);
        shopRecommendRec.setAdapter(recommendRecAdapter);

        recommendRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/module_shoppingmall/CommodityDetailsActivity").navigation();
            }
        });
        recommendRecAdapter.setOnGetViewClickListener(new CommodityRecommendRecAdapter.OnGetViewClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/module_shoppingmall/CommodityDetailsActivity").navigation();
            }
        });
    }

}
