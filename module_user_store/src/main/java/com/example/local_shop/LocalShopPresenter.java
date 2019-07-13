package com.example.local_shop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.BannerBean;
import com.example.common.CommonResource;
import com.example.local_list.LocalListActivity;
import com.example.local_shop.adapter.LocalNavbarAdapter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_store.R;
import com.example.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LocalShopPresenter extends BasePresenter<LocalShopView> {
    private List<BannerBean.RecordsBean> beanList = new ArrayList<>();
    private LocalNavbarAdapter navbarAdapter;
    private LocalSellerAdapter sellerAdapter;

    public LocalShopPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getXBanner() {
        //轮播图
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9005).getDataWithout(CommonResource.USERSBANNER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("轮播图：" + result);
                BannerBean records = JSON.parseObject(result, BannerBean.class);
                LogUtil.e("解析后：" + records);
                beanList = records.getRecords();
                if (getView() != null) {
                    getView().loadBanner(beanList);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void initNavbar() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            list.add("");
        }
        navbarAdapter = new LocalNavbarAdapter(mContext, list, R.layout.rv_local_navbar);
        if (getView() != null) {
            getView().loadNavbar(navbarAdapter);
        }
    }

    public void initSeller() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add("汤小调美食");
            list.add("飞洋健身");
            list.add("世纪联华");
        }
        sellerAdapter = new LocalSellerAdapter(mContext, list, R.layout.rv_local_seller);
        if (getView() != null) {
            getView().loadSeller(sellerAdapter);
        }
    }

    public void initClick() {
        navbarAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                mContext.startActivity(new Intent(mContext, LocalListActivity.class));
            }
        });
    }
}
