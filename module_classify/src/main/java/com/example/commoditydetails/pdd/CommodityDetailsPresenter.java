package com.example.commoditydetails.pdd;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsPddRecAdapter;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.pdd.bean.CommodityDetailsBean;
import com.example.commoditydetails.pdd.bean.CommodityDetailsPddRecBean;
import com.example.commoditydetails.pdd.bean.LedSecuritiesBean;
import com.example.commoditydetails.webview.WebViewActivity;
import com.example.common.CommonResource;
import com.example.entity.BaseRecBean;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class CommodityDetailsPresenter extends BasePresenter<CommodityDetailsView> {

    private List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList = new ArrayList<>();
    private List<CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean> topGoodsList = new ArrayList<>();
    private String earnings;

    public CommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(String goods_id) {
        Map map = MapUtil.getInstance().addParms("userId", SPUtil.getUserCode()).build();
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.PDDGOODSDETAIL + "/" + goods_id, map);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult详情------------>" + result);
                CommodityDetailsBean commodityDetailsBean = JSON.parseObject(result, new TypeReference<CommodityDetailsBean>() {
                }.getType());
                beanList.clear();
                beanList.addAll(commodityDetailsBean.getGoods_detail_response().getGoods_details());

                Map build = MapUtil.getInstance().addParms("userId", SPUtil.getUserCode()).build();
                Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.ESTIMATEEARN, build);
                RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
                    @Override
                    public void onSuccess(String result, String msg) {
                        LogUtil.e("收益-------->" + result);
                        if (!result.equals("")) {
                            if (getView() != null) {
                                getView().CommodityDetailsList(beanList, result);
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
                LogUtil.e("CommodityDetailsErrorMsg详情------------>" + errorMsg);
            }
        }));
    }

    //商品轮播图
    public void setXBanner(XBanner commodityXbanner, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        final List<String> images = beanList.get(0).getGoods_gallery_urls();

        commodityXbanner.setData(images, null);
//        commodityXbanner.setBannerData(R.layout.image_fresco, images);
        commodityXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
//                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
                Glide.with(mContext).load(images.get(position)).apply(RequestOptions.centerCropTransform()).into((ImageView) view);
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
    public void setShopParticulars(RecyclerView shopParticulars, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        final List<String> images = beanList.get(0).getGoods_gallery_urls();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, images, R.layout.itme_commodity_details_rec);
        shopParticulars.setLayoutManager(linearLayoutManager);
        shopParticulars.setNestedScrollingEnabled(false);
        shopParticulars.setHasFixedSize(true);
        shopParticulars.setAdapter(commodityDetailsRecAdapter);
    }

    public void goodsCollect(final ImageView commodityCollectImage, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            Map map = MapUtil.getInstance().addParms("productId", beanList.get(0).getGoods_id()).addParms("type", 2).build();
            Observable head = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT, map, SPUtil.getToken());
            RetrofitUtil.getInstance().toSubscribe(head, new OnMyCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("CommodityDetailsResult点击收藏----->" + result);
                    if (result.equals("true")) {
                        commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                    } else {
                        commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("CommodityDetailsErrorMsg点击收藏----->" + errorMsg);

                }
            }));
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //领劵
    public void ledSecurities(List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.GOODSCOUPON + "/" + SPUtil.getUserCode() + "/" + beanList.get(0).getGoods_id());
            RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    LogUtil.e("CommodityDetailsResult领劵------------>" + result);
                    LedSecuritiesBean ledSecuritiesBean = JSON.parseObject(result, new TypeReference<LedSecuritiesBean>() {
                    }.getType());

//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                Uri content_url = Uri.parse(ledList.get(0).getMobile_url());
//                intent.setData(content_url);
//                mContext.startActivity(intent);
                    if (ledSecuritiesBean != null && ledSecuritiesBean.getGoods_promotion_url_generate_response() != null && ledSecuritiesBean.getGoods_promotion_url_generate_response().getGoods_promotion_url_list().size() != 0) {
                        Intent intent = new Intent(mContext, WebViewActivity.class);
                        intent.putExtra("url", ledSecuritiesBean.getGoods_promotion_url_generate_response().getGoods_promotion_url_list().get(0).getWe_app_web_view_url());
                        mContext.startActivity(intent);
                    }

                }

                @Override
                public void onError(String errorCode, String errorMsg) {
                    LogUtil.e("CommodityDetailsErrorMsg领劵------------>" + errorMsg);
                }
            }));
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //是否收藏
    public void isCollect(final ImageView commodityCollectImage, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        LogUtil.e("id------------->" + beanList.get(0).getGoods_id());
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.FAVORITESTATUS + "/" + beanList.get(0).getGoods_id(), SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsIsCollectResult是否收藏------->" + result);
                if (result.equals("true")) {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang2);
                } else {
                    commodityCollectImage.setImageResource(R.drawable.icon_shoucang1);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsIsCollectErrorMsg收藏------------>" + errorMsg);
            }
        }));
    }

    //推荐
    public void setRecommendRec(final RecyclerView shopRecommendRec) {

        Map map = MapUtil.getInstance().addParms("limit", 20).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TOPGOODS, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult推荐----->" + result);
//                CommodityDetailsPddRecBean commodityDetailsPddRecBean = JSON.parseObject(result, new TypeReference<CommodityDetailsPddRecBean>() {
//                }.getType());
                CommodityDetailsPddRecBean commodityDetailsPddRecBean = new Gson().fromJson(result, CommodityDetailsPddRecBean.class);
                LogUtil.e("CommodityDetailsResult推荐Bean" + commodityDetailsPddRecBean);
                if (commodityDetailsPddRecBean != null && commodityDetailsPddRecBean.getTop_goods_list_get_response() != null) {
                    topGoodsList.clear();
                    topGoodsList.addAll(commodityDetailsPddRecBean.getTop_goods_list_get_response().getList());
                    for (int i = topGoodsList.size() - 1; i >= 0; i--) {
                        if (topGoodsList.get(i).getCoupon_discount() == 0) {
                            topGoodsList.remove(i);
                        }
                    }
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    shopRecommendRec.setLayoutManager(linearLayoutManager);
                    CommodityDetailsPddRecAdapter pddRecAdapter = new CommodityDetailsPddRecAdapter(mContext, topGoodsList, R.layout.item_base_rec);
                    shopRecommendRec.setAdapter(pddRecAdapter);

                    pddRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance()
                                    .build("/module_classify/CommodityDetailsActivity")
                                    .withLong("goods_id", topGoodsList.get(position).getGoods_id())
                                    .withString("type", "1")
                                    .navigation();
                        }
                    });

                    pddRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int index) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ARouter.getInstance()
                                            .build("/module_classify/CommodityDetailsActivity")
                                            .withLong("goods_id", topGoodsList.get(index).getGoods_id())
                                            .withString("type", "1")
                                            .navigation();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsErrorMsg推荐----->" + errorMsg);
            }
        }));


    }


}
