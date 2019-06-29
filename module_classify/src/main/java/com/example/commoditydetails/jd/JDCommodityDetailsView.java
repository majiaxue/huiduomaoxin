package com.example.commoditydetails.jd;

import android.graphics.Bitmap;

import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/6/15
 * Describe:
 */
public interface JDCommodityDetailsView extends IView {
    void earnings(String earnings);

    void isNoGoods(boolean isNoGoods);

    void qrImage(String url);
}
