package com.example.commoditydetails.pdd;

import com.example.commoditydetails.pdd.bean.CommodityDetailsBean;
import com.example.mvp.IView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public interface CommodityDetailsView extends IView {
    void CommodityDetailsList(List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList,String earnings);

}