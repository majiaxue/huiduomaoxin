package com.example.user_home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.BannerBean;
import com.example.bean.HotSaleBean;
import com.example.bean.NavBarBean;
import com.example.bean.Records;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean2;
import com.example.goods_detail.GoodsDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.search.UserSearchActivity;
import com.example.shop_home.ShopHomeActivity;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_home.adapter.NavBarAdapter;
import com.example.user_home.adapter.SaleHotAdapter;
import com.example.user_store.R;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class HomePresenter extends BasePresenter<HomeView> {
    private List<BannerBean> beanList = new ArrayList<>();
    private List<NavBarBean.RecordsBean> navbarList = new ArrayList<>();
    private List<HotSaleBean.DataBean> saleHotList = new ArrayList<>();
    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();
    private CommendAdapter commendAdapter;


    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(int hotSaleIndex) {
        //热销
        Map map = MapUtil.getInstance().addParms("pageNum", hotSaleIndex + "").addParms("saleDesc", "1").build();
        Observable observable = RetrofitUtil.getInstance().getApi1(mContext).getData(CommonResource.HOTNEWSEARCH, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                if (hotSaleBean != null) {
                    saleHotList.addAll(hotSaleBean.getData());
                    SaleHotAdapter saleHotAdapter = new SaleHotAdapter(mContext, saleHotList, R.layout.rv_hot);
                    saleHotAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                            intent.putExtra("id", saleHotList.get(position).getId() + "");
                            intent.putExtra("commendId", saleHotList.get(position).getProductCategoryId() + "");
                            intent.putExtra("sellerId", saleHotList.get(position).getSellerId());
                            mContext.startActivity(intent);
                        }
                    });
                    if (getView() != null) {
                        getView().loadSaleHot(saleHotAdapter);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));

        //导航栏
        Observable<ResponseBody> observable1 = RetrofitUtil.getInstance().getApi1(mContext).getDataWithout(CommonResource.TYPENAVBAR);
        RetrofitUtil.getInstance().toSubscribe(observable1, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                NavBarBean navBarBean = JSON.parseObject(result, new TypeReference<NavBarBean>() {
                }.getType());
                if (navBarBean != null) {
                    navbarList.addAll(navBarBean.getRecords());
                    NavBarAdapter navBarAdapter = new NavBarAdapter(mContext, navbarList, R.layout.rv_navbar);
                    navBarAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            EventBus.getDefault().post(new EventBusBean2(CommonResource.JUMP_CLASSIFY, position));
                        }
                    });
                    if (getView() != null) {
                        getView().loadNavBar(navBarAdapter);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    private void jumpToShop(int position) {
        mContext.startActivity(new Intent(mContext, ShopHomeActivity.class));
    }

    public void jumpToGoodsDetail(int position) {
        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
        intent.putExtra("id", commendList.get(position).getId() + "");
        intent.putExtra("commendId", commendList.get(position).getProductCategoryId() + "");
        intent.putExtra("sellerId", commendList.get(position).getSellerId());
        mContext.startActivity(intent);
    }

    public void setXBanner() {
        //轮播图
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi2(mContext).getDataWithout(CommonResource.USERSBANNER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e(result);
                Records<BannerBean> records = JSON.parseObject(result, new TypeReference<Records<BannerBean>>() {
                }.getType());
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

    public void jumpToSearch() {
        mContext.startActivity(new Intent(mContext, UserSearchActivity.class));
    }

    public void getNewRecommend(final int newGoodsIndex) {
        //新品推荐
        Map map = MapUtil.getInstance().addParms("pageNum", newGoodsIndex + "").addParms("saleDesc", "1").build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi1(mContext).getData(CommonResource.HOTNEWSEARCH, map);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("新品推荐：" + result);
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                if (hotSaleBean != null) {
                    if (newGoodsIndex == 1) {
                        commendList.clear();
                    }
                    commendList.addAll(hotSaleBean.getData());
                    if (commendAdapter == null) {
                        commendAdapter = new CommendAdapter(mContext, commendList, R.layout.rv_commend);
                        if (getView() != null) {
                            getView().loadCommend(commendAdapter);
                        }
                    } else {
                        commendAdapter.notifyDataSetChanged();
                        getView().refreshSuccess();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (getView() != null) {
                    getView().refreshSuccess();
                }
            }
        }));
    }

    public void commendClick() {
        commendAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, final int position) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jumpToGoodsDetail(position);
                    }
                });

                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        jumpToShop(position);
                    }
                });
            }
        });
    }
}
