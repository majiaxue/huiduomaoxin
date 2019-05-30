package com.example.user_home;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.common.CommonResource;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.entity.CommendBean;
import com.example.entity.EventBusBean2;
import com.example.entity.SaleHotBean;
import com.example.entity.TopBannerBean;
import com.example.goods_detail.GoodsDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.shop_home.ShopHomeActivity;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_home.adapter.NavBarAdapter;
import com.example.user_home.adapter.SaleHotAdapter;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class HomePresenter extends BasePresenter<HomeView> {
    private List<TopBannerBean> images;
    private List<BaseRecImageAndTextBean> navbarList;
    private List<SaleHotBean> saleHotList;
    private List<CommendBean> commendList;

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        navbarList = new ArrayList<>();
        navbarList.add(new BaseRecImageAndTextBean("服装", R.drawable.icon_fuzhuang));
        navbarList.add(new BaseRecImageAndTextBean("数码", R.drawable.icon_shuma));
        navbarList.add(new BaseRecImageAndTextBean("配饰", R.drawable.icon_peishi));
        navbarList.add(new BaseRecImageAndTextBean("洗护", R.drawable.icon_xihu));
        navbarList.add(new BaseRecImageAndTextBean("美妆", R.drawable.icon_meizhuang));
        navbarList.add(new BaseRecImageAndTextBean("家电", R.drawable.icon_jiadian));
        navbarList.add(new BaseRecImageAndTextBean("母婴", R.drawable.icon_muying));
        navbarList.add(new BaseRecImageAndTextBean("车品", R.drawable.icon_chepin));
        navbarList.add(new BaseRecImageAndTextBean("家居", R.drawable.icon_jiaju));
        navbarList.add(new BaseRecImageAndTextBean("箱包", R.drawable.icon_xiangbao));
        NavBarAdapter navBarAdapter = new NavBarAdapter(mContext, navbarList, R.layout.rv_navbar);
        navBarAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                EventBus.getDefault().post(new EventBusBean2(CommonResource.JUMP_CLASSIFY, position));
            }
        });
        if (getView() != null) {
            getView().loadNavBar(navBarAdapter);
        }

        saleHotList = new ArrayList<>();
        saleHotList.add(new SaleHotBean(R.drawable.rec1, "稙优泉化妆品买...", "39.90", "95.50"));
        saleHotList.add(new SaleHotBean(R.drawable.rec2, "有机护肤化妆品...", "12.88", "26.50"));
        saleHotList.add(new SaleHotBean(R.drawable.rec3, "美容美妆教学...", "19.90", "42.80"));
        SaleHotAdapter saleHotAdapter = new SaleHotAdapter(mContext, saleHotList, R.layout.rv_hot);
        saleHotAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
            }
        });
        if (getView() != null) {
            getView().loadSaleHot(saleHotAdapter);
        }

        commendList = new ArrayList<>();
        commendList.add(new CommendBean(R.drawable.img_114, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_115, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_116, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_117, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_114, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        commendList.add(new CommendBean(R.drawable.img_115, "2019夏季新款纯棉白色短袖女T恤个性字母简约受到广大", "39.9", "12345", "班迪卡旗舰店"));
        CommendAdapter commendAdapter = new CommendAdapter(mContext, commendList, R.layout.rv_commend);

        commendAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, final int position) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jumpToGoodsDetail(position);
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jumpToShop(position);
                    }
                });
            }
        });

        if (getView() != null) {
            getView().loadCommend(commendAdapter);
        }
    }

    private void jumpToShop(int position) {
        mContext.startActivity(new Intent(mContext, ShopHomeActivity.class));
    }

    public void jumpToGoodsDetail(int position) {
        mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
    }

    public void setXBanner(XBanner homeXbanner, final ImageView homeTopBg) {
        images = new ArrayList<>();
        images.add(new TopBannerBean(R.drawable.banner1));
        images.add(new TopBannerBean(R.drawable.banner2));
        images.add(new TopBannerBean(R.drawable.banner3));
        images.add(new TopBannerBean(R.drawable.banner4));

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

    public void jumpToSearch() {
        ARouter.getInstance().build("/module_home/SearchActivity").navigation();
    }
}
