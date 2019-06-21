package com.example.commoditydetails.taobao;

import com.example.commoditydetails.taobao.bean.TBBean;
import com.example.commoditydetails.taobao.bean.TBLedSecuritiesBean;
import com.example.mvp.IView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public interface TBCommodityDetailsView extends IView {
    void tbBeanList(TBBean tbBeanList);

    void ledSecurities(TBLedSecuritiesBean tbLedSecuritiesBean);

    void earnings(String earnings);

    void tBDetails();
}
