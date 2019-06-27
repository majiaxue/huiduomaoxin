package com.example.commoditydetails.pdd;

import android.graphics.Bitmap;

import com.example.commoditydetails.pdd.bean.CommodityDetailsBean;
import com.example.mvp.IView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public interface CommodityDetailsView extends IView {
    void CommodityDetailsList(List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList);

    void imageUri(String url);

    void imageBitmap(Bitmap bitmap);

    void flag();

    void earnings(String ear);
}
