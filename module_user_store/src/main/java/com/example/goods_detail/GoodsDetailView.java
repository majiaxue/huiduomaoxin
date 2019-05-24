package com.example.goods_detail;

import com.example.entity.TopBannerBean;
import com.example.goods_detail.adapter.GoodsAssessAdapter;
import com.example.goods_detail.adapter.GoodsCouponAdapter;
import com.example.goods_detail.adapter.GoodsImageAdapter;
import com.example.mvp.IView;
import com.example.user_home.adapter.CommendAdapter;

import java.util.List;

public interface GoodsDetailView extends IView {
    void loadCoupon(GoodsCouponAdapter adapter);

    void loadImage(GoodsImageAdapter adapter);

    void loadAssess(GoodsAssessAdapter adapter);

    void loadCommend(CommendAdapter adapter);

    void loadBanner(List<TopBannerBean> list);

    void attention();

    void cancelAttention();
}
