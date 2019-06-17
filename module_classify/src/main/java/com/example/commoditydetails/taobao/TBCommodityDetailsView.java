package com.example.commoditydetails.taobao;

import com.example.commoditydetails.taobao.bean.TBBean;
import com.example.mvp.IView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public interface TBCommodityDetailsView extends IView {
    void tbBeanList(List<TBBean.DataBean> tbBeanList,String earnings);
}
