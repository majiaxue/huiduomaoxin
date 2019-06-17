package com.example.goods_detail;

import com.example.bean.BannerBean;
import com.example.bean.UserGoodsDetail;
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

    void loadBanner(List<BannerBean.RecordsBean> list);

    void attention();

    void cancelAttention();

    void yixuanze(String color, String size);

    void weixuanze(String str);

    void loadUI(UserGoodsDetail data);
}
