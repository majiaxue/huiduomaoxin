package com.example.secondarydetails;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.secondarydetails.adapter.SecondaryTBRecAdapter;
import com.example.bean.JDTabBean;
import com.example.bean.PddGoodsSearchVo;
import com.example.bean.SecondaryPddRecBean;
import com.example.bean.SecondaryTabBean;
import com.example.bean.TBGoodsRecBean;
import com.example.utils.CustomDialog;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.view.SelfDialog;
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
    private List<TBGoodsSearchBean> tBGoodsSearchBeans = new ArrayList<>();
    private List<TBGoodsRecBean.DataBean> tbGoodsList = new ArrayList<>();
    private List<JDTabBean.DataBean> jdTabList = new ArrayList<>();
    private List<JDGoodsRecBean.DataBean.ListsBean> listsBeanList = new ArrayList<>();

    public SecondaryDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(final TabLayout secondaryDetailsTab, final SmartRefreshLayout secondaryDetailsSmartRefresh, final String type) {
        ProcessDialogUtil.showProcessDialog(mContext);
        if (type.equals("2")) {
            //拼多多
            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.GOODSCATS);
            RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("SecondaryDetailsTabResult------------------>" + result);
                    SecondaryTabBean secondaryTabBean = JSON.parseObject(result, new TypeReference<SecondaryTabBean>() {
                    }.getType());
                    if (secondaryTabBean != null) {
                        if (secondaryTabBean.getGoods_cats_get_response() != null && secondaryTabBean.getGoods_cats_get_response().getGoods_cats_list().size() != 0) {
                            catsListBeans.clear();
                            catsListBeans.addAll(secondaryTabBean.getGoods_cats_get_response().getGoods_cats_list());

                            for (int i = 0; i < catsListBeans.size(); i++) {
                                secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(catsListBeans.get(i).getCat_name()));
                            }

                            initTabIndicator(secondaryDetailsTab);
                            secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    ProcessDialogUtil.showProcessDialog(mContext);
                                    baseRecBeanList.clear();
                                    //拼多多,淘宝,京东,page用来刷新,type用来分辨
                                    page = 1;
                                    initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, tab.getPosition());
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });
                            //拼多多,淘宝,京东,page用来刷新,type用来分辨
                            initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, 0);
                        } else {
                            LogUtil.e("数据为空");
                        }

                    } else {
                        LogUtil.e("数据为空");
                    }


                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsTabErrorMsg------------------>" + errorMsg);

                }
            }));
        } else if (type.equals("0") || type.equals("6")) {
            //淘宝
            Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.TBKGOODSTBCATEGOTY);
            RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsResult淘宝--------------->" + result);
                    tBGoodsSearchBeans = JSON.parseArray(result, TBGoodsSearchBean.class);
                    if (tBGoodsSearchBeans != null) {
                        if (tBGoodsSearchBeans != null && tBGoodsSearchBeans.size() != 0) {
                            for (int i = tBGoodsSearchBeans.size() - 1; i >= 0; i--) {
                                if (tBGoodsSearchBeans.get(i).getCat_name().equals("数码家电")) {
                                    tBGoodsSearchBeans.remove(i);
                                }
                                if (tBGoodsSearchBeans.get(i).getCat_name().equals("箱包")) {
                                    tBGoodsSearchBeans.remove(i);
                                }
                            }
                            for (int i = 0; i < tBGoodsSearchBeans.size(); i++) {
                                secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(tBGoodsSearchBeans.get(i).getCat_name()));
                            }

                            initTabIndicator(secondaryDetailsTab);

                            secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    ProcessDialogUtil.showProcessDialog(mContext);
                                    tbGoodsList.clear();
                                    page = 1;
                                    //拼多多,淘宝,京东,page用来刷新,type用来分辨
                                    initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, tab.getPosition());

                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });
                            //拼多多,淘宝,京东,page用来刷新,type用来分辨
                            initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, 0);

                        } else {
                            LogUtil.e("数据为空");
                        }
                    } else {
                        LogUtil.e("数据为空");
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsErrorMsg淘宝--------------->" + errorMsg);
                }
            }));

        } else if(type.equals("4")) {
            //京东
            final Map map = MapUtil.getInstance().addParms("grade", 0).addParms("parentId", 0).build();
            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGETCATEGORY, map);
            RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsResult京东--------------->" + result);
                    JDTabBean jdTabBeans = JSON.parseObject(result, new TypeReference<JDTabBean>() {
                    }.getType());
                    if (jdTabBeans != null) {
                        if (jdTabBeans.getData() != null && jdTabBeans.getData().size() != 0) {
//                            jdTabList.clear();
                            jdTabList.addAll(jdTabBeans.getData());
                            for (int i = 0; i < jdTabList.size(); i++) {
                                secondaryDetailsTab.addTab(secondaryDetailsTab.newTab().setText(jdTabList.get(i).getName()));
                            }

                            initTabIndicator(secondaryDetailsTab);

                            secondaryDetailsTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    ProcessDialogUtil.showProcessDialog(mContext);
                                    //拼多多,淘宝,京东,page用来刷新,type用来分辨
                                    page = 1;
                                    listsBeanList.clear();
                                    initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, tab.getPosition());

                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });

                            //拼多多,淘宝,京东,page用来刷新,type用来分辨
                            initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, 0);

                        } else {
                            LogUtil.e("空");
                        }

                    } else {
                        LogUtil.e("空");
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsErrorMsg京东--------------->" + errorMsg);
                }
            }));
        }

        secondaryDetailsSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                //拼多多,淘宝,京东,page用来刷新,type用来分辨
                initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, secondaryDetailsTab.getTabAt(secondaryDetailsTab.getSelectedTabPosition()).getPosition());
                refreshlayout.finishRefresh();
            }
        });
        secondaryDetailsSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                page++;
                //拼多多,淘宝,京东,page用来刷新,type用来分辨
                initList(catsListBeans, tBGoodsSearchBeans, jdTabList, page, type, secondaryDetailsTab.getTabAt(secondaryDetailsTab.getSelectedTabPosition()).getPosition());
                refreshlayout.finishLoadMore();
            }
        });
    }
    //京东

    private void initList(List<SecondaryTabBean.GoodsCatsGetResponseBean.GoodsCatsListBean> catsListBeans, List<TBGoodsSearchBean> tBGoodsSearchBeans, List<JDTabBean.DataBean> jdTabList, final int page, final String type, int position) {
        //0淘宝 2 拼多多  4京东 6天猫
        if ("2".equals(type)) {
            PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
            pddGoodsSearchVo.setPage(page);
            pddGoodsSearchVo.setCatId((long) catsListBeans.get(position).getCat_id());
            String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
            LogUtil.e("SecondaryDetailsJson----------->" + pddGoodsSearchVoStr);
            RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
            Observable pddGoods = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithBody(CommonResource.PDDGOODS, body);
            RetrofitUtil.getInstance().toSubscribe(pddGoods, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsResult----------->" + result);
                    SecondaryPddRecBean secondaryPddRecBean = JSON.parseObject(result, new TypeReference<SecondaryPddRecBean>() {
                    }.getType());
                    if (secondaryPddRecBean != null) {
                        if (!"0".equals(secondaryPddRecBean.getGoods_search_response().getTotal_count())) {
                            if (getView() != null) {
                                getView().noGoods(false);
                            }
                            if (page == 1) {
                                baseRecBeanList.clear();
                            }
                            baseRecBeanList.addAll(secondaryPddRecBean.getGoods_search_response().getGoods_list());

                            SecondaryPddRecAdapter baseRecAdapter = new SecondaryPddRecAdapter(mContext, baseRecBeanList, R.layout.item_base_rec);

                            if (getView() != null) {
                                getView().lodeRec(baseRecAdapter);
                            }

                            baseRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    long goods_id = baseRecBeanList.get(position).getGoods_id();
                                    ARouter.getInstance()
                                            .build("/module_classify/CommodityDetailsActivity")
                                            .withString("goods_id", goods_id + "")
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
                                                    .navigation();
                                        }
                                    });
                                }
                            });
                        } else {
                            if (getView() != null) {
                                getView().noGoods(true);
                            }
                            LogUtil.e("尚无数据");
                        }
                    } else {
                        LogUtil.e("尚无数据");
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsError----------->" + errorMsg);
                    ProcessDialogUtil.dismissDialog();
                }

            }));
        } else if ("0".equals(type)) {
            //淘宝
            Map map = MapUtil.getInstance().addParms("keyword", tBGoodsSearchBeans.get(position).getCat_name()).addParms("pageno", page).addParms("istmall", false).build();
            Observable<ResponseBody> dataWithout1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSSELLERTBKLIST, map);
            RetrofitUtil.getInstance().toSubscribe(dataWithout1, new OnTripartiteCallBack(new OnDataListener() {

                private TBGoodsRecBean tbGoodsRecBean;

                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsResult淘宝商品--------------->" + result);
                    try {
                        tbGoodsRecBean = JSON.parseObject(result, new TypeReference<TBGoodsRecBean>() {
                        }.getType());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (tbGoodsRecBean != null) {
                        if (tbGoodsRecBean.getData() != null) {
                            if (getView() != null) {
                                getView().noGoods(false);
                            }
                            if (page == 1) {
                                tbGoodsList.clear();
                            }
                            tbGoodsList.addAll(tbGoodsRecBean.getData());

                            SecondaryTBRecAdapter secondaryTBRecAdapter = new SecondaryTBRecAdapter(mContext, tbGoodsList, R.layout.item_base_rec);
                            if (getView() != null) {
                                getView().lodeTBRec(secondaryTBRecAdapter);
                            }

                            secondaryTBRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    if (!TextUtils.isEmpty(SPUtil.getToken())) {
                                        ARouter.getInstance()
                                                .build("/module_classify/TBCommodityDetailsActivity")
                                                .withString("para", tbGoodsList.get(position).getItem_id())
                                                .withString("shoptype", tbGoodsList.get(position).getUser_type())
                                                .navigation();
                                    } else {
                                        //是否登录
                                        PopUtils.isLogin(mContext);
                                    }

                                }
                            });

                        } else {
                            if (getView() != null) {
                                getView().noGoods(true);
                            }
                        }

                    } else {
                        if (getView() != null) {
                            getView().noGoods(true);
                        }
                        LogUtil.e("数据为空");
                    }

                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsErrorMsg淘宝商品--------------->" + errorMsg);
                }
            }));
        } else if ("6".equals(type)) {
            //天猫
            Map map = MapUtil.getInstance().addParms("keyword", tBGoodsSearchBeans.get(position).getCat_name()).addParms("pageno", page).addParms("istmall", true).build();
            Observable<ResponseBody> dataWithout1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSSELLERTBKLIST, map);
            RetrofitUtil.getInstance().toSubscribe(dataWithout1, new OnTripartiteCallBack(new OnDataListener() {

                private TBGoodsRecBean tbGoodsRecBean;

                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsResult淘宝商品--------------->" + result);
                    try {
                        tbGoodsRecBean = JSON.parseObject(result, new TypeReference<TBGoodsRecBean>() {
                        }.getType());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (tbGoodsRecBean != null) {
                        if (tbGoodsRecBean.getData() != null) {
                            if (getView() != null) {
                                getView().noGoods(false);
                            }
                            if (page == 1) {
                                tbGoodsList.clear();
                            }
                            tbGoodsList.addAll(tbGoodsRecBean.getData());

                            SecondaryTBRecAdapter secondaryTBRecAdapter = new SecondaryTBRecAdapter(mContext, tbGoodsList, R.layout.item_base_rec);
                            if (getView() != null) {
                                getView().lodeTBRec(secondaryTBRecAdapter);
                            }

                            secondaryTBRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    if (!TextUtils.isEmpty(SPUtil.getToken())) {
                                        ARouter.getInstance()
                                                .build("/module_classify/TBCommodityDetailsActivity")
                                                .withString("para", tbGoodsList.get(position).getItem_id())
                                                .withString("shoptype", tbGoodsList.get(position).getUser_type())
                                                .navigation();
                                    } else {
                                        //是否登录
                                        PopUtils.isLogin(mContext);
                                    }
                                }
                            });

                            secondaryTBRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                                @Override
                                public void ViewOnClick(View view, final int index) {
                                    view.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            if (!TextUtils.isEmpty(SPUtil.getToken())) {
                                                ARouter.getInstance()
                                                        .build("/module_classify/TBCommodityDetailsActivity")
                                                        .withString("para", tbGoodsList.get(index).getItem_id())
                                                        .withString("shoptype", tbGoodsList.get(index).getUser_type())
                                                        .navigation();
                                            } else {
                                                //是否登录
                                                PopUtils.isLogin(mContext);
                                            }
                                        }
                                    });
                                }
                            });
                        } else {
                            if (getView() != null) {
                                getView().noGoods(true);
                            }
                        }

                    } else {
                        LogUtil.e("数据为空");
                    }

                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsErrorMsg淘宝商品--------------->" + errorMsg);
                }
            }));
        } else if (type.equals("4")){
            //京东
            Map build = MapUtil.getInstance().addParms("isCoupon", 1).addParms("pageIndex", page).addParms("pageSize", 20).addParms("keyword", jdTabList.get(position).getName()).build();
            Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGOODSLIST, build);
            RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    ProcessDialogUtil.dismissDialog();
                    LogUtil.e("SecondaryDetailsResult京东商品--------------->" + result);
                    final JDGoodsRecBean jDGoodsRecBean = JSON.parseObject(result, new TypeReference<JDGoodsRecBean>() {
                    }.getType());

                    if (jDGoodsRecBean != null) {
                        if (jDGoodsRecBean.getCode().equals("-1")) {
                            if (page == 1) {
                                if (getView() != null) {
                                    getView().noGoods(true);
                                }
                            }
                        } else {
                            if (getView() != null) {
                                getView().noGoods(false);
                            }
                            if (page == 1) {
                                listsBeanList.clear();
                            }
                            listsBeanList.addAll(jDGoodsRecBean.getData().getLists());
                            SecondaryJDRecAdapter secondaryJDRecAdapter = new SecondaryJDRecAdapter(mContext, listsBeanList, R.layout.item_base_rec);
                            if (getView() != null) {
                                getView().lodeJDRec(secondaryJDRecAdapter);
                            }
                            secondaryJDRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    ARouter.getInstance()
                                            .build("/module_classify/JDCommodityDetailsActivity")
                                            .withString("skuid", listsBeanList.get(position).getSkuId())
                                            .withSerializable("jDGoodsRecBean", listsBeanList.get(position))
                                            .navigation();
                                }
                            });
                        }
                    } else {
                        LogUtil.e("数据为空");
                    }


                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("SecondaryDetailsErrorMsg京东商品--------------->" + errorMsg);
                    ProcessDialogUtil.dismissDialog();
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
