package com.example.superbrand;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.SuperBrandBean;
import com.example.common.CommonResource;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.superbrand.adapter.SuperBrandRecAdapter;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.lang.reflect.Field;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public class SuperBrandPresenter extends BasePresenter<SuperBrandView> {


    private String[] strArray = new String[]{"女装", "男装", "食品", "居家", "鞋品", "母婴", "数码","美妆","百货"};


    public SuperBrandPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(final TabLayout superBrandTab, final RecyclerView superBrandRec) {
        for (String title : strArray) {
            superBrandTab.addTab(superBrandTab.newTab().setText(title));
        }

        initIndicator(superBrandTab);

        superBrandTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.SUPERGRAND + "/" + position);
                RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
                    @Override
                    public void onSuccess(String result, String msg) {
                        LogUtil.e("超级品牌------>" + result);
                        final List<SuperBrandBean> superBrandBeans = JSON.parseArray(result, SuperBrandBean.class);
                        LogUtil.e("superBrandBeans-------------->" + superBrandBeans);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4, LinearLayoutManager.VERTICAL, false);
                        superBrandRec.setLayoutManager(gridLayoutManager);
                        SuperBrandRecAdapter superBrandRecAdapter = new SuperBrandRecAdapter(mContext, superBrandBeans, R.layout.item_super_brand_rec);
                        superBrandRec.setAdapter(superBrandRecAdapter);

                        superBrandRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(RecyclerView parent, View view, int position) {
                                if (SPUtil.getToken() != null && !"".equals(SPUtil.getToken())) {
                                    String shop_url = superBrandBeans.get(position).getAddress();
                                    LogUtil.e("shop_url---------->" + shop_url);
                                    ARouter.getInstance()
                                            .build("/module_classify/tshop_home")
                                            .withString("url", shop_url)
                                            .navigation();
                                } else {
                                    ARouter.getInstance().build("/mine/login").navigation();
                                }
                            }
                        });

                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {

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
//        //淘宝
//        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.TBKGOODSTBCATEGOTY);
//        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
//            @Override
//            public void onSuccess(String result, String msg) {
//                LogUtil.e("SecondaryDetailsResult淘宝--------------->" + result);
//                tbGoodsSearchBeans = JSON.parseArray(result, TBGoodsSearchBean.class);
//
//                if (tbGoodsSearchBeans != null) {
//                    if (tbGoodsSearchBeans.size() != 0) {
//                        for (int i = 0; i < tbGoodsSearchBeans.size(); i++) {
//                            superBrandTab.addTab(superBrandTab.newTab().setText(tbGoodsSearchBeans.get(i).getCat_name()));
//                        }
//
//                        initIndicator(superBrandTab);
//
//                        superBrandTab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//                            @Override
//                            public void onTabSelected(TabLayout.Tab tab) {
//                                String cat_name = tbGoodsSearchBeans.get(tab.getPosition()).getCat_name();
////                        Toast.makeText(mContext, "cat_name:" + cat_name, Toast.LENGTH_SHORT).show();
//                                Map map = MapUtil.getInstance().addParms("keyword", cat_name).addParms("pageno", 1).build();
//                                Observable<ResponseBody> dataWithout1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSGETTBKSHOP, map);
//                                RetrofitUtil.getInstance().toSubscribe(dataWithout1, new OnTripartiteCallBack(new OnDataListener() {
//                                    @Override
//                                    public void onSuccess(String result, String msg) {
//                                        LogUtil.e("SecondaryDetailsResult淘宝商品--------------->" + result);
//                                        SuperBrandBean SuperBrandBean = JSON.parseObject(result, new TypeReference<SuperBrandBean>() {
//                                        }.getType());
//                                        if (SuperBrandBean.getCode() != -1) {
//                                            if (getView() != null) {
//                                                getView().noBrand(false);
//                                            }
//                                            listsBeans.clear();
//                                            listsBeans.addAll(SuperBrandBean.getData().getLists());
//                                            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4, LinearLayoutManager.VERTICAL, false);
//                                            superBrandRec.setLayoutManager(gridLayoutManager);
//                                            SuperBrandRecAdapter superBrandRecAdapter = new SuperBrandRecAdapter(mContext, listsBeans, R.layout.item_super_brand_rec);
//                                            superBrandRec.setAdapter(superBrandRecAdapter);
//
//                                            superBrandRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
//                                                @Override
//                                                public void onItemClick(RecyclerView parent, View view, int position) {
////                                                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
//                                                    String shop_url = listsBeans.get(position).getShop_url();
//                                                    LogUtil.e("shop_url---------->" + shop_url);
//                                                    ARouter.getInstance()
//                                                            .build("/module_classify/tshop_home")
//                                                            .withString("url", shop_url)
//                                                            .navigation();
//                                                }
//                                            });
//                                        } else {
//                                            if (getView() != null) {
//                                                getView().noBrand(true);
//                                            }
//                                        }
//
//                                    }
//
//                                    @Override
//                                    public void onError(String errorCode, String errorMsg) {
//                                        LogUtil.e("SecondaryDetailsErrorMsg淘宝商品--------------->" + errorMsg);
//                                    }
//                                }));
//
//                            }
//
//                            @Override
//                            public void onTabUnselected(TabLayout.Tab tab) {
//
//                            }
//
//                            @Override
//                            public void onTabReselected(TabLayout.Tab tab) {
//
//                            }
//                        });
//
//                        initList(tbGoodsSearchBeans, superBrandRec);
//                    } else {
//                        LogUtil.e("无数据");
//                    }
//
//                } else {
//                    LogUtil.e("无数据");
//                }
//            }
//
//            @Override
//            public void onError(String errorCode, String errorMsg) {
//                LogUtil.e("SecondaryDetailsErrorMsg淘宝--------------->" + errorMsg);
//            }
//        }));

        initList(superBrandRec);

    }

    private void initList(final RecyclerView superBrandRec) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.SUPERGRAND + "/" + 0);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("超级品牌------>" + result);
                final List<SuperBrandBean> superBrandBeans = JSON.parseArray(result, SuperBrandBean.class);
                LogUtil.e("superBrandBeans-------------->" + superBrandBeans);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4, LinearLayoutManager.VERTICAL, false);
                superBrandRec.setLayoutManager(gridLayoutManager);
                SuperBrandRecAdapter superBrandRecAdapter = new SuperBrandRecAdapter(mContext, superBrandBeans, R.layout.item_super_brand_rec);
                superBrandRec.setAdapter(superBrandRecAdapter);

                superBrandRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        if (SPUtil.getToken() != null && !"".equals(SPUtil.getToken())) {
                            String shop_url = superBrandBeans.get(position).getAddress();
                            LogUtil.e("shop_url---------->" + shop_url);
                            ARouter.getInstance()
                                    .build("/module_classify/tshop_home")
                                    .withString("url", shop_url)
                                    .navigation();
                        } else {
                            ARouter.getInstance().build("/mine/login").navigation();
                        }
                    }
                });

            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }


    private void initIndicator(final TabLayout superBrandTab) {
        superBrandTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) superBrandTab.getChildAt(0);

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
