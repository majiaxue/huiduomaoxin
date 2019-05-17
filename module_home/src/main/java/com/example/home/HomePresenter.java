package com.example.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.home.adapter.GoodChoiceRecAdapter;
import com.example.home.adapter.HomeTopRecAdapter;
import com.example.home.adapter.RecommendRecAdapter;
import com.example.home.bean.GoodChoiceBean;
import com.example.home.bean.RecommendBean;
import com.example.home.bean.TopBannerBean;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter extends BasePresenter<HomeView> {


    private List<String> data;
    private List<View> views = new ArrayList<>();
    private List<TopBannerBean> images;
    private List<BaseRecImageAndTextBean> strings;
    private List<GoodChoiceBean> goodList;
    private List<RecommendBean> recommendBeanList;

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setViewSingleLine() {
        data = new ArrayList<>();
        data.add("git常用命令");
        data.add("Git配置SSH访问GitHub(window)");
        data.add("关于java的抽象和接口");
        data.add("阿里HotFix2.0升级详解 畅谈热修复领域那些事");
        views.clear();
        for (int i = 0; i < data.size(); i++) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_home_marquee_view, null);
            //初始化布局的控件
            TextView marqueeMessage = moreView.findViewById(R.id.marquee_message);

            //进行对控件赋值
            marqueeMessage.setText(data.get(i));

            //添加到循环滚动数组里面去
            views.add(moreView);
            if (getView() != null) {
                getView().lodeMarquee(views);
            }
        }
    }

    public void setXBanner(XBanner homeXbanner, final ImageView homeTopBg) {
        images = new ArrayList<>();
        images.add(new TopBannerBean(R.drawable.banner1));
        images.add(new TopBannerBean(R.drawable.banner2));
        images.add(new TopBannerBean(R.drawable.banner3));
        images.add(new TopBannerBean(R.drawable.banner4));
//        homeXbanner.setData(images, null);
        homeXbanner.setBannerData(R.layout.image_fresco, images);
        homeXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
            }
        });
        // 设置XBanner的页面切换特效
        homeXbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        homeXbanner.setPageChangeDuration(1000);

        //banner切换image也切换
        homeXbanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 0) {
                    homeTopBg.setImageResource(R.drawable.image1);
                } else if (i == 1) {
                    homeTopBg.setImageResource(R.drawable.image2);
                } else if (i == 2) {
                    homeTopBg.setImageResource(R.drawable.image3);
                } else {
                    homeTopBg.setImageResource(R.drawable.image4);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        //监听广告 item 的单击事件
        homeXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //店铺
    public void setRec(RecyclerView homeTopRec) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1, LinearLayoutManager.HORIZONTAL, false);
        homeTopRec.setLayoutManager(gridLayoutManager);

        strings = new ArrayList<>();
        strings.add(new BaseRecImageAndTextBean("淘宝", R.drawable.tb));
        strings.add(new BaseRecImageAndTextBean("拼多多", R.drawable.pdd));
        strings.add(new BaseRecImageAndTextBean("京东", R.drawable.jd));
        strings.add(new BaseRecImageAndTextBean("天猫", R.drawable.tm));


        HomeTopRecAdapter homeTopRecAdapter = new HomeTopRecAdapter(mContext, strings, R.layout.item_home_top_rec);
        homeTopRec.setAdapter(homeTopRecAdapter);

        homeTopRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    //优选
    public void setGoodChoiceRec(RecyclerView homeGoodChoiceRec) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1, LinearLayoutManager.HORIZONTAL, false);
        homeGoodChoiceRec.setLayoutManager(gridLayoutManager);

        goodList = new ArrayList<>();
        goodList.add(new GoodChoiceBean(R.drawable.rec1, "稙优泉化妆品买...", "￥39.90", "95.50"));
        goodList.add(new GoodChoiceBean(R.drawable.rec2, "有机护肤化妆品...", "￥12.88", "26.50"));
        goodList.add(new GoodChoiceBean(R.drawable.rec3, "美容美妆教学...", "￥19.90", "42.80"));


        GoodChoiceRecAdapter goodChoiceRecAdapter = new GoodChoiceRecAdapter(mContext, goodList, R.layout.item_home_good_choice_rec);
        homeGoodChoiceRec.setAdapter(goodChoiceRecAdapter);

        goodChoiceRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //推荐
    public void setBottomRec(RecyclerView homeBottomRec) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        if (homeBottomRec.getItemDecorationCount() == 0) {
            homeBottomRec.addItemDecoration(new DividerItemDecoration(mContext, LinearLayout.VERTICAL));
        }
        homeBottomRec.setLayoutManager(linearLayoutManager);
        recommendBeanList = new ArrayList<>();
        recommendBeanList.add(new RecommendBean(R.drawable.reco1, "稙优泉化妆品买...", "领券减50元", "95.50", "123", "已抢64120件"));
        recommendBeanList.add(new RecommendBean(R.drawable.reco2, "有机护肤化妆品...", "领券减50元", "26.50", "123", "已抢64120件"));
        recommendBeanList.add(new RecommendBean(R.drawable.reco3, "美容美妆教学...", "领券减50元", "42.80", "123", "已抢64120件"));
        recommendBeanList.add(new RecommendBean(R.drawable.reco4, "美容美妆教学...", "领券减50元", "42.80", "123", "已抢64120件"));
        RecommendRecAdapter recommendRecAdapter = new RecommendRecAdapter(mContext, recommendBeanList, R.layout.item_home_recommend_rec);
        homeBottomRec.setAdapter(recommendRecAdapter);

        recommendRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/module_shoppingmall/CommodityDetailsActivity").navigation();
            }
        });
        recommendRecAdapter.setOnGetViewClickListener(new RecommendRecAdapter.OnGetViewClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/module_shoppingmall/CommodityDetailsActivity").navigation();
            }
        });
    }

}
