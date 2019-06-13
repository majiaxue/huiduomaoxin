package com.example.type_detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.entity.BabyRecBean;
import com.example.goods_detail.GoodsDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.search.UserSearchActivity;
import com.example.shop_home.ShopHomeActivity;
import com.example.shop_home.adapter.TreasureLstAdapter;
import com.example.type_detail.adapter.TypeDetailLstAdapter;
import com.example.type_detail.adapter.TypeDetailWaterfallAdapter;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;

public class TypeDetailPresenter extends BasePresenter<TypeDetailView> {

    private List<HotSaleBean.DataBean> dataList = new ArrayList<>();
    private TypeDetailLstAdapter lstAdapter;
    private TypeDetailWaterfallAdapter waterfallAdapter;
    private boolean isWaterfall = false;
    private boolean isPositiveSalesVolume = false;  //是否销量排行
    private boolean isSalesVolumeReduce = false;     //是否销量从高到低
    private boolean saleVolumTemp = false;

    private boolean isPositivePrice = false;        //是否价格排行
    private boolean isPriceReduce = true;          //是否价格从高到低
    private boolean priceTemp = false;

    private boolean isPositiveCredit = false;       //是否信用排行
    private boolean isCreditReduce = false;          //是否信用从高到低
    private boolean creditTemp = false;
    private String searchInfo;

    public TypeDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(String searchString, boolean isHotSale) {
        searchInfo = searchString == null ? "" : searchString;
        Map map;
        if (isHotSale) {
            map = MapUtil.getInstance().addParms("pageNum", 1).addParms("saleDesc", "1").build();
        } else {
            map = MapUtil.getInstance().addParms("searchInfo", searchInfo).addParms("pageNum", 1).build();
        }
        Observable observable = RetrofitUtil.getInstance().getApi1(mContext).getData(CommonResource.HOTNEWSEARCH, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                dataList.addAll(hotSaleBean.getData());
                lstAdapter = new TypeDetailLstAdapter(mContext, dataList, R.layout.rv_type_detail_lst);
                waterfallAdapter = new TypeDetailWaterfallAdapter(mContext, dataList, R.layout.rv_commend);
                if (getView() != null) {
                    getView().loadLstRv(lstAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }


    public void ChangeShow() {
        if (isWaterfall) {
            getView().loadLstRv(lstAdapter);
            isWaterfall = false;
        } else {
            getView().loadWaterfallRv(waterfallAdapter);
            isWaterfall = true;
        }
    }

    public void fromSeeAll() {
        isPositiveSalesVolume = true;
        isSalesVolumeReduce = true;
        saleVolumTemp = true;

        isPositivePrice = false;
        isPriceReduce = true;
        priceTemp = false;

        isPositiveCredit = false;
        isCreditReduce = false;
        creditTemp = false;
        getView().updateTitle(isPositiveSalesVolume, isPositivePrice, isPositiveCredit);
    }

    public void changeTyep(int index) {
        isPositiveSalesVolume = index == 1 ? !isPositiveSalesVolume : false;
        isSalesVolumeReduce = index == 1 ? !isSalesVolumeReduce : false;
        saleVolumTemp = index == 1 ? true : false;

        isPositivePrice = index == 2 ? !isPositivePrice : false;
        isPriceReduce = index == 2 ? !isPriceReduce : true;
        priceTemp = index == 2 ? true : false;

        isPositiveCredit = index == 3 ? !isPositiveCredit : false;
        isCreditReduce = index == 3 ? !isCreditReduce : false;
        creditTemp = index == 3 ? true : false;
        refreshData(1);
        getView().updateTitle(isPositiveSalesVolume, isPositivePrice, isPositiveCredit);
    }

    public void jumpToSearch() {
        mContext.startActivity(new Intent(mContext, UserSearchActivity.class));
    }

    public void click() {
        lstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("id", dataList.get(position).getId() + "");
                intent.putExtra("commendId", dataList.get(position).getProductCategoryId() + "");
                mContext.startActivity(intent);
            }
        }).setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, ShopHomeActivity.class));
                    }
                });
            }
        });

        waterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                intent.putExtra("id", dataList.get(position).getId() + "");
                intent.putExtra("commendId", dataList.get(position).getProductCategoryId() + "");
                mContext.startActivity(intent);
            }
        }).setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, ShopHomeActivity.class));
                    }
                });
            }
        });
    }

    public void refreshData(final int page) {
        Map map;
        if (saleVolumTemp) {
            if (isSalesVolumeReduce) {
                map = MapUtil.getInstance().addParms("searchInfo", searchInfo).addParms("saleDesc", "1").addParms("pageNum", page).build();
            } else {
                map = MapUtil.getInstance().addParms("searchInfo", searchInfo).addParms("saleAsc", "1").addParms("pageNum", page).build();
            }
        } else if (priceTemp) {
            if (isPriceReduce) {
                map = MapUtil.getInstance().addParms("searchInfo", searchInfo).addParms("pageNum", page).addParms("priceDesc", "1").build();
            } else {
                map = MapUtil.getInstance().addParms("searchInfo", searchInfo).addParms("pageNum", page).addParms("priceAsc", "1").build();
            }
        } else if (creditTemp) {
            if (isCreditReduce) {
                map = MapUtil.getInstance().addParms("searchInfo", searchInfo).addParms("pageNum", page).build();
            } else {
                map = MapUtil.getInstance().addParms("searchInfo", searchInfo).addParms("pageNum", page).build();
            }
        } else {
            map = MapUtil.getInstance().addParms("searchInfo", searchInfo).addParms("pageNum", page).build();
        }

        for (Object key : map.keySet()) {
            LogUtil.e("key:" + key);
        }
        for (Object value : map.values()) {
            LogUtil.e("value:" + value);
        }
        Observable observable = RetrofitUtil.getInstance().getApi1(mContext).getData(CommonResource.HOTNEWSEARCH, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("---->" + result);
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                if (page == 1) {
                    dataList.clear();
                }
                dataList.addAll(hotSaleBean.getData());
                if (isWaterfall) {
                    waterfallAdapter.notifyDataSetChanged();
                } else {
                    lstAdapter.notifyDataSetChanged();
                }
                getView().refreshSuccess();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                getView().refreshSuccess();
            }
        }));
    }
}
