package com.example.local_store.ShoppingRight;


import com.example.bean.LocalStoreBean;
import com.example.common.CommonResource;
import com.example.net.RetrofitUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * author pangchao
 * created on 2017/5/12
 * email fat_chao@163.com.
 */

public class SortDetailPresenter extends BasePresenter {
    @Override
    protected void getData() {

    }

    public void submitOrder(List<LocalStoreBean.ListBean> data) {
        List<LocalStoreBean.ListBean> list = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getCount() > 0) {
                list.add(data.get(i));
            }
        }
        LocalStoreBean localStoreBean = new LocalStoreBean();
        localStoreBean.setSellerId(SPUtil.getStringValue(CommonResource.SELLERID));

//        RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9010).postDataWithBody();
    }
}
