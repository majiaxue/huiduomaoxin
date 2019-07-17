package com.example.local_shop;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.BannerBean;
import com.example.bean.LocalNavbarBean;
import com.example.bean.LocalShopBean;
import com.example.common.CommonResource;
import com.example.local_detail.LocalDetailActivity;
import com.example.local_mingxi.LocalMingxiActivity;
import com.example.local_shop.adapter.LocalNavbarAdapter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.search.UserSearchActivity;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.MyLocationListener;
import com.example.utils.ProcessDialogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class LocalShopPresenter extends BasePresenter<LocalShopView> {
    private List<BannerBean.RecordsBean> beanList = new ArrayList<>();
    private LocalNavbarAdapter navbarAdapter;
    private LocalSellerAdapter sellerAdapter;
    private List<LocalNavbarBean> navbarList;
    private List<LocalShopBean> shopBeans = new ArrayList<>();

    private boolean isZh = true;

    private boolean isDistance = false;
    private boolean isDistanceJin = true;
    private boolean distanceFlag = false;   //默认false,点击评分后先判断是否为true，如果为true则改变评分正倒序，否则不改变，然后改为true，点击其他排序改为false

    private boolean isStar = false;
    private boolean isStarMore = true;
    private boolean starFlag = false;


    public LocalShopPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getXBanner() {
        //轮播图
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9005).getDataWithout(CommonResource.LOCAL_BANNER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("本地轮播图：" + result);
                if (result != null) {
                    BannerBean records = JSON.parseObject(result, BannerBean.class);
                    beanList = records.getRecords();
                    if (getView() != null) {
                        getView().loadBanner(beanList);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void initNavbar() {

        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getDataWithout(CommonResource.LOCAL_NAVBAR + "?type=1");
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("本地导航：" + result);
                if (result != null) {
                    navbarList = JSON.parseArray(result, LocalNavbarBean.class);
                    navbarAdapter = new LocalNavbarAdapter(mContext, navbarList, R.layout.rv_local_navbar);
                    if (getView() != null) {
                        getView().loadNavbar(navbarAdapter);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void initSeller(String sorttype, String sort, final int page) {
        Map map = MapUtil.getInstance().addParms("sort", sort + " " + sorttype).addParms("page", page).addParms("lon", MyLocationListener.longitude).addParms("lat", MyLocationListener.latitude).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.LOCALSHOPLIST, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("本地商城：" + result);
                if (getView() != null) {
                    getView().loadFinish();
                }
                try {
                    if (page == 1) {
                        shopBeans.clear();
                    }
                    shopBeans.addAll(JSON.parseArray(result, LocalShopBean.class));
                    if (shopBeans.size() > 0) {
                        if (sellerAdapter == null) {
                            sellerAdapter = new LocalSellerAdapter(mContext, shopBeans, R.layout.rv_local_seller);
                            if (getView() != null) {
                                getView().loadSeller(sellerAdapter);
                            }
                        } else {
                            sellerAdapter.notifyDataSetChanged();
                        }

                        sellerAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                Intent intent = new Intent(mContext, LocalDetailActivity.class);
                                intent.putExtra("bean", shopBeans.get(position));
                                mContext.startActivity(intent);
                            }
                        });
                    } else {
                        if (getView() != null) {
                            getView().noData();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-----------" + errorMsg);
                if (getView() != null) {
                    getView().loadFinish();
                }
            }
        }));

    }

    public void initClick() {
        navbarAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                ARouter.getInstance().build("/user/localList").withString("type", navbarList.get(position).getSellerCategoryCode()).navigation();
//                Intent intent = new Intent(mContext, LocalListActivity.class);
//                intent.putExtra("type", navbarList.get(position).getSellerCategoryName());
//                mContext.startActivity(intent);
            }
        });
    }

    public void jumpToOrder() {
        Intent intent = new Intent(mContext, LocalMingxiActivity.class);
        mContext.startActivity(intent);
    }

    public void changeSort(int index) {
        if (index == 0) {
            isDistance = false;
            isStar = false;
            distanceFlag = false;
            starFlag = false;
            if (!isZh) {
                isZh = true;
                ProcessDialogUtil.showProcessDialog(mContext);
                initSeller("", "", 1);
            }
        } else if (index == 1) {
            isZh = false;
            isStar = false;
            isDistance = true;
            starFlag = false;
            if (distanceFlag) {
                isDistanceJin = !isDistanceJin;
            }
            distanceFlag = true;
            ProcessDialogUtil.showProcessDialog(mContext);
            initSeller(isDistanceJin ? LocalShopFragment.ASC : LocalShopFragment.DESC, LocalShopFragment.DISTANCE, 1);
        } else if (index == 2) {
            isZh = false;
            isDistance = false;
            isStar = true;
            distanceFlag = false;
            if (starFlag) {
                isStarMore = !isStarMore;
            }
            starFlag = true;
            ProcessDialogUtil.showProcessDialog(mContext);
            initSeller(isStarMore ? LocalShopFragment.DESC : LocalShopFragment.ASC, LocalShopFragment.STAR, 1);
        }

        getView().changed(isDistanceJin, isStarMore);
    }

    public void loadData(int index, int page) {
        if (index == 0) {
            initSeller("", "", page);
        } else if (index == 1) {
            initSeller(isDistanceJin ? LocalShopFragment.ASC : LocalShopFragment.DESC, LocalShopFragment.DISTANCE, page);
        } else if (index == 2) {
            initSeller(isStarMore ? LocalShopFragment.DESC : LocalShopFragment.ASC, LocalShopFragment.STAR, page);
        }
    }

    public void jumpToSearch() {
        Intent intent = new Intent(mContext, UserSearchActivity.class);
        intent.putExtra("from", CommonResource.HISTORY_LOCAL);
        mContext.startActivity(intent);
    }

    public void isOpenLocation() {
        LocationManager locManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // 未打开位置开关，可能导致定位失败或定位不准，提示用户或做相应处理
        }
    }
}
