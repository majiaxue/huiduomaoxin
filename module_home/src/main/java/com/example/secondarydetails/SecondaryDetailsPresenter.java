package com.example.secondarydetails;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.JDGoodsRecBean;
import com.example.bean.TBGoodsSearchBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.secondarydetails.adapter.SecondaryTBRecAdapter;
import com.example.secondarydetails.bean.JDTabBean;
import com.example.bean.PddGoodsSearchVo;
import com.example.bean.SecondaryPddRecBean;
import com.example.secondarydetails.bean.SecondaryTabBean;
import com.example.bean.TBGoodsRecBean;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/31
 * Describe:
 */
public class SecondaryDetailsPresenter extends BasePresenter<SecondaryDetailsView> {

    private int page = 1;
    private List<SecondaryTabBean.GoodsCatsGetResponseBean.GoodsCatsListBean> catsListBeans = new ArrayList<>();
    private List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> baseRecBeanList = new ArrayList<>();
    private List<TBGoodsSearchBean> TBGoodsSearchBeans = new ArrayList<>();
    private List<TBGoodsRecBean.DataBean> tbGoodsList = new ArrayList<>();
    private List<JDTabBean.DataBean> jdTabList = new ArrayList<>();
    private List<JDGoodsRecBean.DataBean.ListsBean> listsBeanList = new ArrayList<>();


    public SecondaryDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(final TabLayout secondaryDetailsTab, final RecyclerView secondaryDetailsRec, final SmartRefreshLayout secondaryDetailsSmartRefresh, final String type) {
        if (type.equals("1")) {
            //拼多多
            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.GOODSCATS);
            RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
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
//                            Toast.makeText(mContext, "cat_id:" + cat_id, Toast.LENGTH_SHORT).show();
                            PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
                            pddGoodsSearchVo.setPage(page);
                            pddGoodsSearchVo.setCatId(cat_id);
                            String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
                            LogUtil.e("SecondaryDetailsJson----------->" + pddGoodsSearchVoStr);
                            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
                            Observable pddGoods = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithBody(CommonResource.PDDGOODS, body);
                            RetrofitUtil.getInstance().toSubscribe(pddGoods, new OnTripartiteCallBack(new OnDataListener() {
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
                                                ARouter.getInstance()
                                                        .build("/module_classify/CommodityDetailsActivity")
                                                        .withString("goods_id", baseRecBeanList.get(position).getGoods_id() + "")
                                                        .withString("type", type)
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
                                                                .withString("goods_id", goods_id + "")
                                                                .withString("type", type)
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

                    initList(catsListBeans, secondaryDetailsRec, type, page, TBGoodsSearchBeans, jdTabList);


                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsTabErrorMsg------------------>" + errorMsg);
                }
            }));
        } else if (type.equals("0") || type.equals("3")) {
            //淘宝
            Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.TBKGOODSTBCATEGOTY);
            RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("SecondaryDetailsResult淘宝--------------->" + result);
                    TBGoodsSearchBeans = JSON.parseArray(result, TBGoodsSearchBean.class);

                    for (int i = TBGoodsSearchBeans.size() - 1; i >= 0; i--) {
                        if (TBGoodsSearchBeans.get(i).getCat_name().equals("数码家电")) {
                            TBGoodsSearchBeans.remove(i);
                        }
                        if (TBGoodsSearchBeans.get(i).getCat_name().equals("箱包")) {
                            TBGoodsSearchBeans.remove(i);
                        }
                    }


                    for (int i = 0; i < TBGoodsSearchBeans.size(); i++) {
                        secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(TBGoodsSearchBeans.get(i).getCat_name()));
                    }

                    initTabIndicator(secondaryDetailsTab);

                    secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            String cat_name = TBGoodsSearchBeans.get(tab.getPosition()).getCat_name();
//                            Toast.makeText(mContext, "cat_name:" + cat_name, Toast.LENGTH_SHORT).show();
                            Map map = MapUtil.getInstance().addParms("keyword", cat_name).addParms("pageno", 1).build();
                            Observable<ResponseBody> dataWithout1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSSELLERTBKLIST, map);
                            RetrofitUtil.getInstance().toSubscribe(dataWithout1, new OnTripartiteCallBack(new OnDataListener() {
                                @Override
                                public void onSuccess(String result, String msg) {
                                    LogUtil.e("SecondaryDetailsResult淘宝商品--------------->" + result);
                                    TBGoodsRecBean tbGoodsRecBean = JSON.parseObject(result, new TypeReference<TBGoodsRecBean>() {
                                    }.getType());
                                    tbGoodsList.clear();
                                    tbGoodsList.addAll(tbGoodsRecBean.getData());

                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                                    SecondaryTBRecAdapter secondaryTBRecAdapter = new SecondaryTBRecAdapter(mContext, tbGoodsList, R.layout.item_base_rec);
                                    secondaryDetailsRec.setLayoutManager(linearLayoutManager);
                                    secondaryDetailsRec.setAdapter(secondaryTBRecAdapter);

                                    secondaryTBRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(RecyclerView parent, View view, int position) {
                                            ARouter.getInstance()
                                                    .build("/module_classify/TBCommodityDetailsActivity")
                                                    .withString("para", tbGoodsList.get(position).getItem_id())
                                                    .withString("shoptype", tbGoodsList.get(position).getUser_type()).navigation();
                                        }
                                    });

                                    secondaryTBRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                                        @Override
                                        public void ViewOnClick(View view, final int index) {
                                            view.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {
                                                    ARouter.getInstance()
                                                            .build("/module_classify/TBCommodityDetailsActivity")
                                                            .withString("para", tbGoodsList.get(index).getItem_id())
                                                            .withString("shoptype", tbGoodsList.get(index).getUser_type())
                                                            .navigation();
                                                }
                                            });
                                        }
                                    });

                                }

                                @Override
                                public void onError(String errorCode, String errorMsg) {
                                    LogUtil.e("SecondaryDetailsErrorMsg淘宝商品--------------->" + errorMsg);
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

                    initList(catsListBeans, secondaryDetailsRec, type, page, TBGoodsSearchBeans, jdTabList);

                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsErrorMsg淘宝--------------->" + errorMsg);
                }
            }));

        } else {
            //京东
            final Map map = MapUtil.getInstance().addParms("grade", 0).addParms("parentId", 0).build();
            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGETCATEGORY, map);
            RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("SecondaryDetailsResult京东--------------->" + result);
                    JDTabBean jdTabBeans = JSON.parseObject(result, new TypeReference<JDTabBean>() {
                    }.getType());
                    jdTabList.clear();
                    jdTabList.addAll(jdTabBeans.getData());
                    for (int i = 0; i < jdTabList.size(); i++) {
                        secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(jdTabList.get(i).getName()));
                    }

                    initTabIndicator(secondaryDetailsTab);

                    secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            String name = jdTabList.get(tab.getPosition()).getName();
                            Map build = MapUtil.getInstance().addParms("isCoupon", 1).addParms("pageIndex", 1).addParms("pageSize", 20).addParms("keyword", name).build();
                            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGOODSLIST, build);
                            RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                                @Override
                                public void onSuccess(String result, String msg) {
                                    LogUtil.e("SecondaryDetailsResult京东商品--------------->" + result);
                                    final JDGoodsRecBean jDGoodsRecBean = JSON.parseObject(result, new TypeReference<JDGoodsRecBean>() {
                                    }.getType());

                                    if (jDGoodsRecBean.getCode().equals("-1")) {
                                        getView().noGoods(true);
                                    } else {
                                        getView().noGoods(false);

                                        listsBeanList.clear();
                                        listsBeanList.addAll(jDGoodsRecBean.getData().getLists());
                                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                                        SecondaryJDRecAdapter secondaryJDRecAdapter = new SecondaryJDRecAdapter(mContext, listsBeanList, R.layout.item_base_rec);
                                        secondaryDetailsRec.setLayoutManager(linearLayoutManager);
                                        secondaryDetailsRec.setAdapter(secondaryJDRecAdapter);

                                        secondaryJDRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(RecyclerView parent, View view, int position) {
                                                ARouter.getInstance()
                                                        .build("/module_classify/JDCommodityDetailsActivity")
                                                        .withString("skuid", listsBeanList.get(position).getSkuId())
                                                        .withSerializable("jDGoodsRecBean", jDGoodsRecBean)
                                                        .withInt("position", position)
                                                        .navigation();
                                            }
                                        });

                                        secondaryJDRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                                            @Override
                                            public void ViewOnClick(View view, final int index) {
                                                view.setOnClickListener(new View.OnClickListener() {
                                                    @Override
                                                    public void onClick(View v) {
                                                        ARouter.getInstance()
                                                                .build("/module_classify/JDCommodityDetailsActivity")
                                                                .withString("skuid", listsBeanList.get(index).getSkuId())
                                                                .withSerializable("jDGoodsRecBean", jDGoodsRecBean)
                                                                .withInt("position", index)
                                                                .navigation();
                                                    }
                                                });
                                            }
                                        });
                                    }


                                }

                                @Override
                                public void onError(String errorCode, String errorMsg) {
                                    LogUtil.e("SecondaryDetailsErrorMsg京东商品--------------->" + errorMsg);
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

                    initList(catsListBeans, secondaryDetailsRec, type, page, TBGoodsSearchBeans, jdTabList);

                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsErrorMsg京东--------------->" + errorMsg);
                }
            }));
        }

        secondaryDetailsSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                initList(catsListBeans, secondaryDetailsRec, type, page, TBGoodsSearchBeans, jdTabList);
//                            refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                refreshlayout.finishRefresh();
            }
        });
        secondaryDetailsSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                initList(catsListBeans, secondaryDetailsRec, type, page, TBGoodsSearchBeans, jdTabList);
//                            refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                refreshlayout.finishLoadMore();
            }
        });
    }
    //京东

    private void initList(List<SecondaryTabBean.GoodsCatsGetResponseBean.GoodsCatsListBean> catsListBeans, final RecyclerView secondaryDetailsRec, final String type, int page, List<TBGoodsSearchBean> TBGoodsSearchBeans, List<JDTabBean.DataBean> jdTabList) {
        //0淘宝 1 拼多多  2京东 3天猫
        if (type.equals("1")) {
            PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
            pddGoodsSearchVo.setPage(page);
            if (catsListBeans.size() != 0) {
                pddGoodsSearchVo.setCatId((long) catsListBeans.get(0).getCat_id());
            }
            String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
            LogUtil.e("SecondaryDetailsJson----------->" + pddGoodsSearchVoStr);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
            Observable pddGoods = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithBody(CommonResource.PDDGOODS, body);
            RetrofitUtil.getInstance().toSubscribe(pddGoods, new OnTripartiteCallBack(new OnDataListener() {
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
                                        .withString("goods_id", goods_id+"")
                                        .withString("type", type)
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
                                                .withString("goods_id", goods_id+"")
                                                .withString("type", type)
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
        } else if (type.equals("0") || type.equals("3")) {
            String cat_name = TBGoodsSearchBeans.get(0).getCat_name();
//            Toast.makeText(mContext, "cat_name:" + cat_name, Toast.LENGTH_SHORT).show();
            Map map = MapUtil.getInstance().addParms("keyword", cat_name).addParms("pageno", 1).build();
            Observable<ResponseBody> dataWithout1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSSELLERTBKLIST, map);
            RetrofitUtil.getInstance().toSubscribe(dataWithout1, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("SecondaryDetailsResult淘宝商品--------------->" + result);
                    TBGoodsRecBean tbGoodsRecBean = JSON.parseObject(result, new TypeReference<TBGoodsRecBean>() {
                    }.getType());
//                    tbGoodsList.clear();
                    tbGoodsList.addAll(tbGoodsRecBean.getData());

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    SecondaryTBRecAdapter secondaryTBRecAdapter = new SecondaryTBRecAdapter(mContext, tbGoodsList, R.layout.item_base_rec);
                    secondaryDetailsRec.setLayoutManager(linearLayoutManager);
                    secondaryDetailsRec.setAdapter(secondaryTBRecAdapter);

                    secondaryTBRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_classify/TBCommodityDetailsActivity")
                                    .withString("para", tbGoodsList.get(position).getItem_id())
                                    .withString("shoptype", tbGoodsList.get(position).getUser_type()).navigation();
                        }
                    });

                    secondaryTBRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ARouter.getInstance()
                                            .build("/module_classify/TBCommodityDetailsActivity")
                                            .withString("para", tbGoodsList.get(index).getItem_id())
                                            .withString("shoptype", tbGoodsList.get(index).getUser_type())
                                            .navigation();
                                }
                            });
                        }
                    });
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsErrorMsg淘宝商品--------------->" + errorMsg);
                }
            }));
        } else {
            String name = jdTabList.get(0).getName();
            Map build = MapUtil.getInstance().addParms("isCoupon", 1).addParms("pageIndex", page).addParms("pageSize", 20).addParms("keyword", name).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGOODSLIST, build);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("SecondaryDetailsResult京东商品--------------->" + result);
                    final JDGoodsRecBean jDGoodsRecBean = JSON.parseObject(result, new TypeReference<JDGoodsRecBean>() {
                    }.getType());

                    if (jDGoodsRecBean.getCode().equals("-1")) {
                        getView().noGoods(true);
                    } else {
                        getView().noGoods(false);
                        listsBeanList.addAll(jDGoodsRecBean.getData().getLists());
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                        SecondaryJDRecAdapter secondaryJDRecAdapter = new SecondaryJDRecAdapter(mContext, listsBeanList, R.layout.item_base_rec);
                        secondaryDetailsRec.setLayoutManager(linearLayoutManager);
                        secondaryDetailsRec.setAdapter(secondaryJDRecAdapter);

                        secondaryJDRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance()
                                        .build("/module_classify/JDCommodityDetailsActivity")
                                        .withString("skuid", listsBeanList.get(position).getSkuId())
                                        .withSerializable("jDGoodsRecBean", jDGoodsRecBean)
                                        .withInt("position", position)
                                        .navigation();
                            }
                        });

                        secondaryJDRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                            @Override
                            public void ViewOnClick(View view, final int index) {
                                view.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ARouter.getInstance()
                                                .build("/module_classify/JDCommodityDetailsActivity")
                                                .withString("skuid", listsBeanList.get(index).getSkuId())
                                                .withSerializable("jDGoodsRecBean", jDGoodsRecBean)
                                                .withInt("position", index)
                                                .navigation();
                                    }
                                });
                            }
                        });
                    }


                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsErrorMsg京东商品--------------->" + errorMsg);
                }
            }));
        }

    }


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
