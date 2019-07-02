package com.example.commoditydetails.taobao;

import com.example.bean.TBBean;
import com.example.bean.TBLedSecuritiesBean;
import com.example.mvp.IView;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public interface TBCommodityDetailsView extends IView {
    void tbBeanList(TBBean tbBeanList);

    void ledSecurities(TBLedSecuritiesBean tbLedSecuritiesBean);

    void earnings(String earnings);

    void tBDetails();

    void noCoupon(boolean noCoupon);
}
