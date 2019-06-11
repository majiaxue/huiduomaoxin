package com.example.secondarydetails;

import android.content.Context;
import android.graphics.drawable.PaintDrawable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.BaseRecAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.common.CommonResource;
import com.example.secondarydetails.adapter.SecondaryPddRecAdapter;
import com.example.secondarydetails.bean.PddGoodsSearchVo;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnPddCallBack;
import com.example.net.RetrofitUtil;
import com.example.secondarydetails.bean.SecondaryPddRecBean;
import com.example.secondarydetails.bean.SecondaryTabBean;
import com.example.utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by cuihaohao on 2019/5/31
 * Describe:
 */
public class SecondaryDetailsPresenter extends BasePresenter<SecondaryDetailsView> {

    private List<SecondaryTabBean.GoodsCatsGetResponseBean.GoodsCatsListBean> catsListBeans = new ArrayList<>();
    private List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> baseRecBeanList = new ArrayList<>();
    private int page = 1;

    public SecondaryDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(final TabLayout secondaryDetailsTab, final RecyclerView secondaryDetailsRec, final SmartRefreshLayout secondaryDetailsSmartRefresh, final String type) {
        if (type.equals("1")) {
            Observable data = RetrofitUtil.getInstance().getApi2(mContext).getDataWithout(CommonResource.GOODSCATS);
            RetrofitUtil.getInstance().toSubscribe(data, new OnPddCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("SecondaryDetailsTabResult------------------>" + result);
                    SecondaryTabBean secondaryTabBean = JSON.parseObject(result, new TypeReference<SecondaryTabBean>() {
                    }.getType());

                    catsListBeans.clear();
                    catsListBeans.addAll(secondaryTabBean.getGoods_cats_get_response().getGoods_cats_list());

                    for (int i = 0; i < catsListBeans.size(); i++) {
                        secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(catsListBeans.get(i).getCat_name()));
                    }

                    initTabIndicator(secondaryDetailsTab);

                    secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            long cat_id = catsListBeans.get(tab.getPosition()).getCat_id();
                            Toast.makeText(mContext, "cat_id:" + cat_id, Toast.LENGTH_SHORT).show();
                            PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
                            pddGoodsSearchVo.setPage(page);
                            pddGoodsSearchVo.setCatId(cat_id);
                            String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
                            LogUtil.e("SecondaryDetailsJson----------->" + pddGoodsSearchVoStr);
                            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
                            Observable pddGoods = RetrofitUtil.getInstance().getApi2(mContext).postDataWithBody(CommonResource.PDDGOODS, body);
                            RetrofitUtil.getInstance().toSubscribe(pddGoods, new OnPddCallBack(new OnDataListener() {
                                @Override
                                public void onSuccess(String result, String msg) {
                                    LogUtil.e("SecondaryDetailsResult----------->" + result);
                                    SecondaryPddRecBean secondaryPddRecBean = JSON.parseObject(result, new TypeReference<SecondaryPddRecBean>() {
                                    }.getType());

                                    if (!secondaryPddRecBean.getGoods_search_response().getTotal_count().equals("0")) {
                                        getView().noGoods(false);
                                        baseRecBeanList.clear();
                                        baseRecBeanList.addAll(secondaryPddRecBean.getGoods_search_response().getGoods_list());

                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                                        secondaryDetailsRec.setLayoutManager(linearLayoutManager);
                                        SecondaryPddRecAdapter baseRecAdapter = new SecondaryPddRecAdapter(mContext, baseRecBeanList, R.layout.item_base_rec, type);

                                        if (getView() != null) {
                                            getView().lodeRec(baseRecAdapter);
                                        }

                                        baseRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(RecyclerView parent, View view, int position) {
                                                long goods_id = baseRecBeanList.get(position).getGoods_id();
                                                ARouter.getInstance()
                                                        .build("/module_classify/CommodityDetailsActivity")
                                                        .withLong("goods_id", goods_id)
                                                        .withString("type",type).navigation();
                                            }
                                        });

                                        baseRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                                            @Override
                                            public void ViewOnClick(View view, final int index) {
                                                view.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        long goods_id = baseRecBeanList.get(index).getGoods_id();
                                                        ARouter.getInstance()
                                                                .build("/module_classify/CommodityDetailsActivity")
                                                                .withLong("goods_id", goods_id)
                                                                .withString("type",type)
                                                                .navigation();
                                                    }
                                                });
                                            }
                                        });

                                    } else {
                                        getView().noGoods(true);
                                        LogUtil.e("尚无数据");
                                    }
                                }

                                @Override
                                public void onError(String errorCode, String errorMsg) {
                                    LogUtil.e("SecondaryDetailsError----------->" + errorMsg);
                                }

                            }));

                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });

                    initList(catsListBeans, secondaryDetailsRec, type, page);



                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsTabErrorMsg------------------>" + errorMsg);
                }
            }));

            secondaryDetailsSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    page = 1;
                    initList(catsListBeans, secondaryDetailsRec, type, page);
//                            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                    refreshlayout.finishRefresh();
                }
            });
            secondaryDetailsSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
                @Override
                public void onLoadMore(RefreshLayout refreshlayout) {
                    page++;
                    initList(catsListBeans, secondaryDetailsRec, type, page);
//                            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                    refreshlayout.finishLoadMore();
                }
            });
        }
    }

    private void initList(List<SecondaryTabBean.GoodsCatsGetResponseBean.GoodsCatsListBean> catsListBeans, final RecyclerView secondaryDetailsRec, final String type, int page) {
        PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
        pddGoodsSearchVo.setPage(page);
        if (catsListBeans.size() != 0) {
            pddGoodsSearchVo.setCatId((long) catsListBeans.get(0).getCat_id());
        }
        String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
        LogUtil.e("SecondaryDetailsJson----------->" + pddGoodsSearchVoStr);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable pddGoods = RetrofitUtil.getInstance().getApi2(mContext).postDataWithBody(CommonResource.PDDGOODS, body);
        RetrofitUtil.getInstance().toSubscribe(pddGoods, new OnPddCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("SecondaryDetailsResult----------->" + result);
                SecondaryPddRecBean secondaryPddRecBean = JSON.parseObject(result, new TypeReference<SecondaryPddRecBean>() {
                }.getType());
                if (!secondaryPddRecBean.getGoods_search_response().getTotal_count().equals("0")) {
                    getView().noGoods(false);
//                    baseRecBeanList.clear();
                    baseRecBeanList.addAll(secondaryPddRecBean.getGoods_search_response().getGoods_list());

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    secondaryDetailsRec.setLayoutManager(linearLayoutManager);
                    SecondaryPddRecAdapter baseRecAdapter = new SecondaryPddRecAdapter(mContext, baseRecBeanList, R.layout.item_base_rec, type);

                    if (getView() != null) {
                        getView().lodeRec(baseRecAdapter);
                    }

                    baseRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            long goods_id = baseRecBeanList.get(position).getGoods_id();
                            ARouter.getInstance()
                                    .build("/module_classify/CommodityDetailsActivity")
                                    .withLong("goods_id", goods_id)
                                    .withString("type",type)
                                    .navigation();

                        }
                    });

                    baseRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    long goods_id = baseRecBeanList.get(index).getGoods_id();
                                    ARouter.getInstance()
                                            .build("/module_classify/CommodityDetailsActivity")
                                            .withLong("goods_id", goods_id)
                                            .withString("type",type)
                                            .navigation();
                                }
                            });
                        }
                    });
                } else {
                    getView().noGoods(true);
                    LogUtil.e("尚无数据");
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("SecondaryDetailsError----------->" + errorMsg);
            }

        }));
    }
//rec
//    public void secondaryDetailsRec(final RecyclerView secondaryDetailsRec, final String type) {
//        if (type.equals("1")) {
//            //拼多多
//            PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
//            pddGoodsSearchVo.setPage(1);
//            pddGoodsSearchVo.setCatId((long) 239);
//            String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
//            LogUtil.e("SecondaryDetailsJson----------->" + pddGoodsSearchVoStr);
//            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
//            Observable pddGoods = RetrofitUtil.getInstance().getApi2(mContext).postDataWithBody(CommonResource.PDDGOODS, body);
//            RetrofitUtil.getInstance().toSubscribe(pddGoods, new OnPddCallBack(new OnDataListener() {
//                @Override
//                public void onSuccess(String result, String msg) {
//                    LogUtil.e("SecondaryDetailsResult----------->" + result);
//                    SecondaryPddRecBean secondaryPddRecBean = JSON.parseObject(result, new TypeReference<SecondaryPddRecBean>() {
//                    }.getType());
//
//                    baseRecBeanList.clear();
//                    baseRecBeanList.addAll(secondaryPddRecBean.getGoods_search_response().getGoods_list());
//
//                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//                    secondaryDetailsRec.setLayoutManager(linearLayoutManager);
//                    SecondaryPddRecAdapter baseRecAdapter = new SecondaryPddRecAdapter(mContext, baseRecBeanList, R.layout.item_base_rec, type);
//
//                    if (getView() != null) {
//                        getView().lodeRec(baseRecAdapter);
//                    }
//
//                    baseRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
//                        @Override
//                        public void onItemClick(RecyclerView parent, View view, int position) {
//                            ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
//                        }
//                    });
//
//                    baseRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
//                        @Override
//                        public void ViewOnClick(View view, final int index) {
//                            view.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
//                                }
//                            });
//                        }
//                    });
//                }
//
//                @Override
//                public void onError(String errorCode, String errorMsg) {
//                    LogUtil.e("SecondaryDetailsError----------->" + errorMsg);
//                }
//
//            }));
//        }
//
//
//    }

    //设置tablayout指示器与文字一样宽
    private void initTabIndicator(final TabLayout secondaryDetailsTab) {
        secondaryDetailsTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) secondaryDetailsTab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });
    }


}
