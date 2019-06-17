package com.example.classificationdetails;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
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


    public ClassificationDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

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

    public void searchTB(String content, final int page, final int index) {
        Map map = MapUtil.getInstance().addParms("keyword", content).addParms("pageno", page).addParms("pagesize", "10").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHead(CommonResource.SEARCHTBGOODS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("淘宝搜索：" + result);
                TBGoodsRecBean tbGoodsRecBean = JSON.parseObject(result, TBGoodsRecBean.class);
                if (page == 1) {
                    tbList.clear();
                }
                tbList.addAll(tbGoodsRecBean.getData());
                lstAdapter = new BaseRecAdapter(mContext, tbList, R.layout.item_base_rec, "0");
                waterfallAdapter = new ClassificationRecAdapter(mContext, tbList, R.layout.item_classification_rec_grid);
                if (getView() != null) {
                    if (isWaterfall) {
                        getView().loadTBWaterfallRv(waterfallAdapter);
                        getView().loadFinish();
                    } else {
                        getView().loadTBLstRv(lstAdapter);
                        getView().loadFinish();
                    }
                }
                lstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                .withString("para", tbList.get(position).getItem_id())
                                .withString("shoptype", index + "")
                                .navigation();
                    }
                });

                waterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                .withString("para", tbList.get(position).getItem_id())
                                .withString("shoptype", index + "")
                                .navigation();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void searchJD(String searchContent, int page) {
        Map map = MapUtil.getInstance().addParms("keyword", searchContent).addParms("pageIndex", page).addParms("pageSize", "10").addParms("isCoupon", "1").build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHead(CommonResource.SEARCHJDGOODS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new DisposableObserver<ResponseBody>() {
            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    String string = responseBody.string();
                    final JDGoodsRecBean jdSearchBean = JSON.parseObject(string, JDGoodsRecBean.class);
                    jdList = jdSearchBean.getData().getLists();
                    jdLstAdapter = new SecondaryJDRecAdapter(mContext, jdList, R.layout.item_base_rec);
                    jdWaterfallAdapter = new JdWaterfallAdapter(mContext, jdList, R.layout.item_classification_rec_grid);
                    if (isWaterfall) {
                        if (getView() != null) {
                            getView().loadJDWaterfallRv(jdWaterfallAdapter);
                            getView().loadFinish();
                        }
                    } else {
                        if (getView() != null) {
                            getView().loadJDLstRv(jdLstAdapter);
                            getView().loadFinish();
                        }
                    }

                    jdLstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_classify/JDCommodityDetailsActivity")
                                    .withString("skuid", jdList.get(position).getSkuId())
                                    .withSerializable("jDGoodsRecBean", jdSearchBean)
                                    .withInt("position", position)
                                    .navigation();
                        }
                    });

                    jdWaterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_classify/JDCommodityDetailsActivity")
                                    .withString("skuid", jdList.get(position).getSkuId())
                                    .withSerializable("jDGoodsRecBean", jdSearchBean)
                                    .withInt("position", position)
                                    .navigation();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void searchPDD(String searchContent, final int page) {
        PddGoodsSearchVo pddGoodsSearchVo = new PddGoodsSearchVo();
        pddGoodsSearchVo.setPage(page);
        pddGoodsSearchVo.setKeyword(searchContent);
        pddGoodsSearchVo.setPageSize(10);

        String pddGoodsSearchVoStr = JSON.toJSONString(pddGoodsSearchVo);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), pddGoodsSearchVoStr);
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postDataWithBody(CommonResource.SEARCHPDDGOODS, body);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("拼多多搜索：" + result);
                SecondaryPddRecBean secondaryPddRecBean = JSON.parseObject(result, new TypeReference<SecondaryPddRecBean>() {
                }.getType());
                if (page == 1) {
                    pddList.clear();
                }
                pddList.addAll(secondaryPddRecBean.getGoods_search_response().getGoods_list());
                pddLstAdapter = new SecondaryPddRecAdapter(mContext, pddList, R.layout.item_base_rec, "1");
                pddWaterAdapter = new PddWaterAdapter(mContext, pddList, R.layout.item_classification_rec_grid);
                if (getView() != null) {
                    if (isWaterfall) {
                        getView().loadPDDWaterfallRv(pddWaterAdapter);
                        getView().loadFinish();
                    } else {
                        getView().loadPDDLstRv(pddLstAdapter);
                        getView().loadFinish();
                    }
                }
                pddLstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                                .withString("goods_id", pddList.get(position).getGoods_id() + "")
                                .withString("type", "1")
                                .navigation();
                    }
                });

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

            @Override
            public void onError(String errorCode, String errorMsg) {

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
                    getView().loadJDWaterfallRv(jdWaterfallAdapter);
                    isWaterfall = false;
                } else {
                    getView().loadJDLstRv(jdLstAdapter);
                    isWaterfall = true;
                }
                break;
        }

    }
}
