package com.example.commoditydetails.taobao;

import com.alibaba.fastjson.JSONArray;
import com.example.bean.TBBean;
import com.example.bean.TBGoodsDetailsBean;
import com.example.bean.TBLedSecuritiesBean;
import com.example.mvp.IView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public interface TBCommodityDetailsView extends IView {
    void tbBeanList(TBGoodsDetailsBean tbGoodsDetailsBean, List<String> imageList);

    void ledSecurities(TBLedSecuritiesBean tbLedSecuritiesBean);

    void earnings(String earnings);

    void tBDetails();

    void noCoupon(boolean noCoupon);
}
