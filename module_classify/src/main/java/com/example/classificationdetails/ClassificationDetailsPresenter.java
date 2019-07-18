package com.example.classificationdetails;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.BaseRecAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.bean.JDGoodsRecBean;
import com.example.bean.PddGoodsSearchVo;
import com.example.bean.SecondaryPddRecBean;
import com.example.bean.TBGoodsRecBean;
import com.example.classificationdetails.adapter.ClassificationRecAdapter;
import com.example.classificationdetails.adapter.JdWaterfallAdapter;
import com.example.classificationdetails.adapter.PddWaterAdapter;
import com.example.common.CommonResource;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/20
 * Describe:
 */
public class ClassificationDetailsPresenter extends BasePresenter<ClassificationDetailsView> {

    private List<TBGoodsRecBean.DataBean> tbList = new ArrayList<>();
    private List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> pddList = new ArrayList<>();
    private List<JDGoodsRecBean.DataBean.ListsBean> jdList = new ArrayList<>();
    private String[] titleArr = {"淘宝", "拼多多", "京东"};
    private BaseRecAdapter lstAdapter;
    private boolean isWaterfall = false;
    private ClassificationRecAdapter waterfallAdapter;
    private SecondaryPddRecAdapter pddLstAdapter;
    private PddWaterAdapter pddWaterAdapter;
    private SecondaryJDRecAdapter jdLstAdapter;
    private JdWaterfallAdapter jdWaterfallAdapter;

    private int goodsType = 1;  //商品来源  1：淘宝  2：拼多多   3：京东
    private String content = "";     //搜索内容

    private int sort_type = 1;  //拼多多排序字段

    private boolean isPositiveSalesVolume = false;  //是否销量排行
    private boolean isSalesVolumeReduce = false;     //是否销量从高到低
    private boolean saleVolumTemp = false;

    private boolean isPositivePrice = false;        //是否价格排行
    private boolean isPriceReduce = true;          //是否价格从高到低
    private boolean priceTemp = false;

    private boolean isPositiveCredit = false;       //是否信用排行
    private boolean isCreditReduce = false;          //是否信用从高到低
    private boolean creditTemp = false;

    private int tbNum = 0;
    private int jdNum = 0;
    private int pddNum = 0;


    public ClassificationDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setContent(String content) {
        if (content == null || "".equals(content)) {
            this.content = "衣";
        } else {
            this.content = content;
        }
    }

    public void initTabLayout(final TabLayout classificationTab) {
        for (String title : titleArr) {
            classificationTab.addTab(classificationTab.newTab().setText(title));
        }

        classificationTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) classificationTab.getChildAt(0);

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

    public void searchTB(final int page, String sort) {
        goodsType = 1;
        if (page == 1) {
            ProcessDialogUtil.showProcessDialog(mContext);
        }
        Map map = MapUtil.getInstance().addParms("keyword", content).addParms("pageno", page).addParms("pagesize", "10").build();
        if (sort != null) {
            map.put("sort", sort);
        }
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHead(CommonResource.SEARCHTBGOODS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                ProcessDialogUtil.dismissDialog();
                LogUtil.e("淘宝搜索：" + result);
                if (getView() != null) {
                    getView().loadFinish();
                }

                try {

                    if (result != null) {
                        if (page == 1) {
                            tbList.clear();
                            tbNum = 0;
                        }
                        JSONObject jsonObject = JSON.parseObject(result);
                        if ("200".equals(jsonObject.getString("code"))) {
                            JSONArray data = jsonObject.getJSONArray("data");
                            for (int i = 0; i < data.size(); i++) {
                                TBGoodsRecBean.DataBean dataBean = new TBGoodsRecBean.DataBean();
                                JSONObject jsonObject1 = data.getJSONObject(i);
                                dataBean.setItem_id(jsonObject1.getString("item_id"));
                                dataBean.setPict_url(jsonObject1.getString("pict_url"));
                                dataBean.setTitle(jsonObject1.getString("title"));
                                dataBean.setCommission_rate(jsonObject1.getString("commission_rate"));
                                dataBean.setVolume(jsonObject1.getString("volume"));
                                dataBean.setCoupon_amount(jsonObject1.getString("coupon_amount"));
                                dataBean.setZk_final_price(jsonObject1.getString("zk_final_price"));
                                dataBean.setReserve_price(jsonObject1.getString("reserve_price"));
                                dataBean.setTk_total_sales(jsonObject1.getString("tk_total_sales"));
                                tbList.add(dataBean);
                            }

                            waterfallAdapter = new ClassificationRecAdapter(mContext, tbList, R.layout.item_classification_rec_grid);
                            lstAdapter = new BaseRecAdapter(mContext, tbList, R.layout.item_base_rec, "0");
                            if (isWaterfall) {
                                if (getView() != null) {
                                    getView().loadTBWaterfallRv(waterfallAdapter);
                                    getView().moveTo(tbNum, isWaterfall);
                                }

                            } else {
                                if (getView() != null) {
                                    getView().loadTBLstRv(lstAdapter);
                                    getView().moveTo(tbNum, isWaterfall);
                                }

                            }

                            if (lstAdapter != null) {
                                lstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(RecyclerView parent, View view, int position) {
                                        ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                                .withString("para", tbList.get(position).getItem_id())
                                                .withString("shoptype", "1")
                                                .navigation();
                                    }
                                });
                            }

                            if (waterfallAdapter != null) {
                                waterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(RecyclerView parent, View view, int position) {
                                        ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                                .withString("para", tbList.get(position).getItem_id())
                                                .withString("shoptype", "1")
                                                .navigation();
                                    }
                                });
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
            }
        }));
    }

    public void searchJD(final int page, String sort, String sortName) {
        goodsType = 3;
        if (page == 1) {
            ProcessDialogUtil.showProcessDialog(mContext);
        }

        Map map = MapUtil.getInstance().addParms("keyword", content).addParms("pageIndex", page).addParms("pageSize", "10").addParms("isCoupon", "1").build();
        if (sort != null) {
            map.put("sort", sort);
            map.put("sortName", sortName);
        }
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHead(CommonResource.SEARCHJDGOODS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new DisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
                try {
                    String string = responseBody.string();
                    LogUtil.e("京东搜索:" + string);
                    final JDGoodsRecBean jdSearchBean = JSON.parseObject(string, JDGoodsRecBean.class);
                    if (page == 1) {
                        jdList.clear();
                    }
                    jdList.addAll(jdSearchBean.getData().getLists());
                    jdWaterfallAdapter = new JdWaterfallAdapter(mContext, jdList, R.layout.item_classification_rec_grid);
                    jdLstAdapter = new SecondaryJDRecAdapter(mContext, jdList, R.layout.item_base_rec);
                    if (isWaterfall) {

                        if (getView() != null) {
                            getView().loadJDWaterfallRv(jdWaterfallAdapter);
                            getView().moveTo(tbNum, isWaterfall);
                        }

                    } else {

                        if (getView() != null) {
                            getView().loadJDLstRv(jdLstAdapter);
                            getView().moveTo(tbNum, isWaterfall);
                        }

                    }

                    if (jdLstAdapter != null) {
                        jdLstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance()
                                        .build("/module_classify/JDCommodityDetailsActivity")
                                        .withString("skuid", jdList.get(position).getSkuId())
                                        .withSerializable("jDGoodsRecBean", jdSearchBean.getData().getLists().get(position))
                                        .withInt("position", position)
                                        .navigation();
                            }
                        });
                    }


                    if (jdWaterfallAdapter != null) {
                        jdWaterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                ARouter.getInstance()
                                        .build("/module_classify/JDCommodityDetailsActivity")
                                        .withString("skuid", jdList.get(position).getSkuId())
                                        .withSerializable("jDGoodsRecBean", jdSearchBean.getData().getLists().get(position))
                                        .withInt("position", position)
                                        .navigation();
                            }
                        });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {
                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void searchPDD(final int page) {
        goodsType = 2;
        if (page == 1) {
            ProcessDialogUtil.showProcessDialog(mContext);
        }

        PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
        pddGoodsSearchVo.setPage(page);
        pddGoodsSearchVo.setKeyword(content);
        pddGoodsSearchVo.setPageSize(10);
        pddGoodsSearchVo.setSortType(sort_type);

        String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithBody(CommonResource.SEARCHPDDGOODS, body);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }

                try {

                    if (result != null) {
                        LogUtil.e("拼多多搜索：" + result);
                        SecondaryPddRecBean secondaryPddRecBean = JSON.parseObject(result, new TypeReference<SecondaryPddRecBean>() {
                        }.getType());
                        if (page == 1) {
                            pddList.clear();
                        }
                        pddList.addAll(secondaryPddRecBean.getGoods_search_response().getGoods_list());
                        pddWaterAdapter = new PddWaterAdapter(mContext, pddList, R.layout.item_classification_rec_grid);
                        pddLstAdapter = new SecondaryPddRecAdapter(mContext, pddList, R.layout.item_base_rec);

                        if (isWaterfall) {

                            if (getView() != null) {
                                getView().loadPDDWaterfallRv(pddWaterAdapter);
                                getView().moveTo(tbNum, isWaterfall);
                            }

                        } else {

                            if (getView() != null) {
                                getView().loadPDDLstRv(pddLstAdapter);
                                getView().moveTo(tbNum, isWaterfall);
                            }

                        }

                        if (pddLstAdapter != null) {
                            pddLstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                                            .withString("goods_id", pddList.get(position).getGoods_id() + "")
                                            .withString("type", "1")
                                            .navigation();
                                }
                            });
                        }

                        if (pddWaterAdapter != null) {
                            pddWaterAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(RecyclerView parent, View view, int position) {
                                    ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                                            .withString("goods_id", pddList.get(position).getGoods_id() + "")
                                            .withString("type", "1")
                                            .navigation();
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                ProcessDialogUtil.dismissDialog();
                if (getView() != null) {
                    getView().loadFinish();
                }
            }
        }));
    }

    public void ChangeShow(int position) {
        switch (position) {
            case 0:
                if (isWaterfall) {
                    getView().loadTBLstRv(lstAdapter);
                    isWaterfall = false;
                } else {
                    getView().loadTBWaterfallRv(waterfallAdapter);
                    isWaterfall = true;
                }
                break;
            case 1:
                if (isWaterfall) {
                    getView().loadPDDLstRv(pddLstAdapter);
                    isWaterfall = false;
                } else {
                    getView().loadPDDWaterfallRv(pddWaterAdapter);
                    isWaterfall = true;
                }
                break;
            case 2:
                if (isWaterfall) {
                    getView().loadJDLstRv(jdLstAdapter);
                    isWaterfall = false;
                } else {
                    getView().loadJDWaterfallRv(jdWaterfallAdapter);
                    isWaterfall = true;
                }
                break;
        }

    }

    public void changeType(int index) {
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

    private void refreshData(int page) {
        if (saleVolumTemp) {
            if (isSalesVolumeReduce) {
                if (goodsType == 1) {
                    searchTB(page, "total_sales_des");
                } else if (goodsType == 2) {
                    sort_type = 6;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, "desc", "inOrderCount30Days");
                }
            } else {
                if (goodsType == 1) {
                    searchTB(page, "total_sales_asc");
                } else if (goodsType == 2) {
                    sort_type = 5;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, "asc", "inOrderCount30Days");
                }
            }
        } else if (priceTemp) {
            if (isPriceReduce) {
                if (goodsType == 1) {
                    searchTB(page, "price_des");
                } else if (goodsType == 2) {
                    sort_type = 4;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, "desc", "price");
                }
            } else {
                if (goodsType == 1) {
                    searchTB(page, "price_asc");
                } else if (goodsType == 2) {
                    sort_type = 3;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, "asc", "price");
                }
            }
        } else if (creditTemp) {
            if (isCreditReduce) {
                if (goodsType == 1) {
                    searchTB(page, null);
                } else if (goodsType == 2) {
                    sort_type = 0;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, null, null);
                }
            } else {
                if (goodsType == 1) {
                    searchTB(page, null);
                } else if (goodsType == 2) {
                    sort_type = 0;
                    searchPDD(page);
                } else if (goodsType == 3) {
                    searchJD(page, null, null);
                }
            }
        } else {
            if (goodsType == 1) {
                searchTB(page, null);
            } else if (goodsType == 2) {
                sort_type = 0;
                searchPDD(page);
            } else if (goodsType == 3) {
                searchJD(page, null, null);
            }
        }
    }
}
