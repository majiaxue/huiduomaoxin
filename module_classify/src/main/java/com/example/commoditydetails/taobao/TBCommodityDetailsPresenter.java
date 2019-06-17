package com.example.commoditydetails.taobao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.model.TradeResult;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.baichuan.android.trade.page.AlibcShopPage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.taobao.adapter.TBRecommendAdapter;
import com.example.commoditydetails.taobao.bean.TBBean;
import com.example.commoditydetails.taobao.bean.TBGoodChoiceBean;
import com.example.commoditydetails.taobao.bean.TBLedSecuritiesBean;
import com.example.commoditydetails.webview.WebViewActivity;
import com.example.common.CommonResource;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:
 */
public class TBCommodityDetailsPresenter extends BasePresenter<TBCommodityDetailsView> {

    private String type;
    private List<TBBean.DataBean> tbBeanList = new ArrayList<>();
    private List<TBLedSecuritiesBean.DataBean> dataBeanList = new ArrayList<>();
    private List<TBGoodChoiceBean.DataBean> tbRecommendList = new ArrayList<>();

    public TBCommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    //初始化视图
    public void initView(String para, String shopType) {
        if ("0" .equals(shopType)) {
            type = "C";
        } else {
            type = "B";
        }
        Map map = MapUtil.getInstance().addParms("moreinfo", "1").addParms("para", para).addParms("shoptype", type).build();
        LogUtil.e("赋值" + 1 + "    " + para + "    " + type);
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSITEMDETAIL, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult---------------->" + result);
                TBBean tbBean = JSON.parseObject(result, new TypeReference<TBBean>() {
                }.getType());
                tbBeanList.clear();
                tbBeanList.add(tbBean.getData());

                Map build = MapUtil.getInstance().addParms("userId", SPUtil.getUserCode()).build();
                Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.ESTIMATEEARN, build);
                RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
                    @Override
                    public void onSuccess(String result, String msg) {
                        LogUtil.e("收益-------->" + result);
                        if (!result.equals("")) {
                            if (getView() != null) {
                                getView().tbBeanList(tbBeanList, result);
                            }
                        }
                    }

                    @Override
                    public void onError(String errorCode, String errorMsg) {

                    }
                }));


            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg---------------->" + errorMsg);
            }
        }));
    }


    //商品轮播图
    public void setXBanner(XBanner commodityXbanner, final List<String> images) {

        commodityXbanner.setData(images, null);
//        commodityXbanner.setBannerData(R.layout.image_fresco, images);
        commodityXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
//                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
                Glide.with(mContext).load("https:" + images.get(position)).apply(RequestOptions.centerInsideTransform()).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        commodityXbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        commodityXbanner.setPageChangeDuration(1000);

        //监听广告 item 的单击事件
        commodityXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //商品详情图
    public void setShopParticulars(RecyclerView shopParticulars, List<String> itemDetail) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
//        {
//            @Override
//            public boolean canScrollVertically() {
//                return false && super.canScrollVertically();
//            }
//        };
        CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, itemDetail, R.layout.itme_commodity_details_rec);
        shopParticulars.setLayoutManager(linearLayoutManager);
//        shopParticulars.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        shopParticulars.setAdapter(commodityDetailsRecAdapter);
    }

    //是否收藏
    public void isCollect(final ImageView commodityCollectImage, String id) {
        LogUtil.e("id------------->" + id);
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.FAVORITESTATUS + "/" + id, SPUtil.getToken());

        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult收藏------->" + result);
                if (result.equals("true")) {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                } else {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg收藏------------>" + errorMsg);
            }
        }));
    }

    //收藏商品
    public void goodsCollect(final ImageView commodityCollectImage, String id) {
        Map map = MapUtil.getInstance().addParms("productId", id).addParms("type", 4).build();
        Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult点击收藏----->" + result);
                if (result.equals("true")) {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                } else {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg点击收藏----->" + errorMsg);

            }
        }));
    }

    //领劵
    public void ledSecurities(String para) {
        Map map = MapUtil.getInstance().addParms("para", para).build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSGETGYURLBYALL, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult领劵--------->" + result);
                TBLedSecuritiesBean tbLedSecuritiesBean = JSON.parseObject(result, new TypeReference<TBLedSecuritiesBean>() {
                }.getType());
                jumpToTB(tbLedSecuritiesBean.getData().getCoupon_click_url());
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg领劵--------->" + errorMsg);
            }
        }));


    }

    private void jumpToTB(String url) {
        //提供给三方传递配置参数
        Map<String, String> exParams = new HashMap<>();
        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");

        //打开指定页面
        AlibcPage alibcPage = new AlibcPage(url);

        //设置页面打开方式
        AlibcShowParams showParams = new AlibcShowParams(OpenType.Native, false);

        //使用百川sdk提供默认的Activity打开detail
        AlibcTrade.show((Activity) mContext, alibcPage, showParams, null, exParams,
                new AlibcTradeCallback() {
                    @Override
                    public void onTradeSuccess(TradeResult tradeResult) {
                        //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
                        LogUtil.e(tradeResult.toString());
                    }

                    @Override
                    public void onFailure(int code, String msg) {
                        //打开电商组件，用户操作中错误信息回调。code：错误码；msg：错误信息
                    }
                });
    }

    public void setRecommendRec(final RecyclerView shopRecommendRec) {
        Map map = MapUtil.getInstance().addParms("page", 1).addParms("pagesize", 20).build();
        Observable data1 = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSPRODUCTS, map);
        RetrofitUtil.getInstance().toSubscribe(data1, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult推荐------------>" + result);
                TBGoodChoiceBean tbGoodChoiceBean = JSON.parseObject(result, new TypeReference<TBGoodChoiceBean>() {
                }.getType());

                if (tbGoodChoiceBean.getData() != null && tbGoodChoiceBean != null) {
                    tbRecommendList.clear();
                    tbRecommendList.addAll(tbGoodChoiceBean.getData());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    shopRecommendRec.setLayoutManager(linearLayoutManager);
                    TBRecommendAdapter goodsRecommendAdapter = new TBRecommendAdapter(mContext, tbRecommendList, R.layout.item_base_rec);
                    shopRecommendRec.setAdapter(goodsRecommendAdapter);

                    goodsRecommendAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                    .withString("para", tbRecommendList.get(position).getItem_id())
                                    .withString("shoptype", tbRecommendList.get(position).getUser_type()).navigation();
                        }
                    });

                    goodsRecommendAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                            .withString("para", tbRecommendList.get(index).getItem_id())
                                            .withString("shoptype", tbRecommendList.get(index).getUser_type()).navigation();
                                }
                            });
                        }
                    });
                } else {
                    LogUtil.e("数据为空");
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg推荐------------>" + errorMsg);
            }
        }));
    }

}
