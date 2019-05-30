package com.example.user_classify;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.LeftGroupBean;
import com.example.entity.RightRecBean;
import com.example.entity.TopBannerBean;
import com.example.mvp.BasePresenter;
import com.example.user_classify.adapter.UserLeftRvAdapter;
import com.example.user_classify.adapter.UserRightRecAdapter;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class ClassifyPresenter extends BasePresenter<ClassifyView> {
    private List<LeftGroupBean> leftList;
    private List<TopBannerBean> images;
    private UserLeftRvAdapter leftRvAdapter;


    public ClassifyPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setXBanner(XBanner homeXbanner) {
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

        //监听广告 item 的单击事件
        homeXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRightRec(RecyclerView classifyRecommendRec) {
        List<RightRecBean> list = new ArrayList<>();

        List<RightRecBean.ListBean> a_childList = new ArrayList<>();
        a_childList.add(new RightRecBean.ListBean(R.drawable.tcl, "tcl"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.sony, "索尼"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.feilipu, "飞利浦"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.xiapu, "夏普"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.haier, "海尔"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.chuangwei, "创维"));


        List<RightRecBean.ListBean> b_childList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            b_childList.add(new RightRecBean.ListBean(R.drawable.e76696ec1eea0f7e8f0208ba61583434, "电视"));
            b_childList.add(new RightRecBean.ListBean(R.drawable.bingxiang, "冰箱"));
            b_childList.add(new RightRecBean.ListBean(R.drawable.fgfd, "洗衣机"));
        }

        list.add(new RightRecBean("专场推荐", a_childList));
        list.add(new RightRecBean("热门分类", b_childList));

        UserRightRecAdapter myRightRecAdapter = new UserRightRecAdapter(mContext, list, R.layout.item_rec_group);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        classifyRecommendRec.setLayoutManager(linearLayoutManager);
        classifyRecommendRec.setAdapter(myRightRecAdapter);

    }

    public void setLeftRv() {
        leftList = new ArrayList<>();
        leftList.add(new LeftGroupBean("推荐", true));
        leftList.add(new LeftGroupBean("服装", false));
        leftList.add(new LeftGroupBean("数码", false));
        leftList.add(new LeftGroupBean("配饰", false));
        leftList.add(new LeftGroupBean("洗护", false));
        leftList.add(new LeftGroupBean("美妆", false));
        leftList.add(new LeftGroupBean("家电", false));
        leftList.add(new LeftGroupBean("母婴", false));
        leftList.add(new LeftGroupBean("车品", false));
        leftList.add(new LeftGroupBean("家居", false));
        leftList.add(new LeftGroupBean("箱包", false));
        leftRvAdapter = new UserLeftRvAdapter(mContext, leftList, R.layout.rv_left_classify);
        if (getView() != null) {
            getView().loadLeftRv(leftRvAdapter);
        }

        leftRvAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                for (int i = 0; i < leftList.size(); i++) {
                    leftList.get(i).setSelected(i == position ? true : false);
                }
                leftRvAdapter.notifyDataSetChanged();
            }
        });
    }

    public void formHomeNavbar(int position) {
        for (int i = 0; i < leftList.size(); i++) {
            leftList.get(i).setSelected(i == position ? true : false);
        }
        leftRvAdapter.notifyDataSetChanged();
    }
}
