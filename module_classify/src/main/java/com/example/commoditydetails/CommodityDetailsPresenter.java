package com.example.commoditydetails;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.commoditydetails.adapter.CommodityDetailsPddRecAdapter;
import com.example.commoditydetails.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.bean.CommodityDetailsBean;
import com.example.commoditydetails.bean.CommodityDetailsPddRecBean;
import com.example.commoditydetails.bean.LedSecuritiesBean;
import com.example.common.CommonResource;
import com.example.entity.BaseRecBean;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnPddCallBack;
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

    private List<BaseRecBean> baseRecBeanList;
    private List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList = new ArrayList<>();
    private List<CommodityDetailsPddRecBean.TopGoodsListGetResponseBean.ListBean> topGoodsList = new ArrayList<>();
    private List<LedSecuritiesBean.GoodsPromotionUrlGenerateResponseBean.GoodsPromotionUrlListBean> ledList = new ArrayList<>();

    public CommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(long goods_id) {
        Map map = MapUtil.getInstance().addParms("userId", SPUtil.getUserCode() + "").build();
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.PDDGOODSDETAIL + "/" + goods_id, map);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnPddCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult详情------------>" + result);
                CommodityDetailsBean commodityDetailsBean = JSON.parseObject(result, new TypeReference<CommodityDetailsBean>() {
                }.getType());
                beanList.clear();
                beanList.addAll(commodityDetailsBean.getGoods_detail_response().getGoods_details());
                if (getView() != null) {
                    getView().CommodityDetailsList(beanList);
                }
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
                Glide.with(mContext).load(images.get(position)).apply(RequestOptions.centerInsideTransform()).into((ImageView) view);
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, images, R.layout.itme_commodity_details_rec);
        shopParticulars.setLayoutManager(linearLayoutManager);
//        shopParticulars.setNestedScrollingEnabled(false);
        shopParticulars.setAdapter(commodityDetailsRecAdapter);
    }

    public void goodsCollect(final ImageView commodityCollectImage, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
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
    }

    //领劵
    public void ledSecurities(List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.GOODSCOUPON + "/" + SPUtil.getUserCode() + "/" + beanList.get(0).getGoods_id());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnPddCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult领劵------------>" + result);
                LedSecuritiesBean ledSecuritiesBean = JSON.parseObject(result, new TypeReference<LedSecuritiesBean>() {
                }.getType());
                ledList.clear();
                ledList.addAll(ledSecuritiesBean.getGoods_promotion_url_generate_response().getGoods_promotion_url_list());

                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(ledList.get(0).getMobile_url());
                intent.setData(content_url);
                mContext.startActivity(intent);

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsErrorMsg领劵------------>" + errorMsg);
            }
        }));
    }

    //是否收藏
    public void isCollect(final ImageView commodityCollectImage, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        LogUtil.e("id------------->"+beanList.get(0).getGoods_id());
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.FAVORITESTATUS + "/" + beanList.get(0).getGoods_id(), SPUtil.getToken());
        LogUtil.e("path------------>"+"http://192.168.1.27:4001"+CommonResource.FAVORITESTATUS+"/"+beanList.get(0).getGoods_id());

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
        RetrofitUtil.getInstance().toSubscribe(data, new OnPddCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult推荐----->" + result);
//                CommodityDetailsPddRecBean commodityDetailsPddRecBean = JSON.parseObject(result, new TypeReference<CommodityDetailsPddRecBean>() {
//                }.getType());
                CommodityDetailsPddRecBean commodityDetailsPddRecBean = new Gson().fromJson(result, CommodityDetailsPddRecBean.class);
                LogUtil.e("CommodityDetailsResult推荐Bean" + commodityDetailsPddRecBean);
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
//                ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
                    }
                });

                pddRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                        ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsErrorMsg推荐----->" + errorMsg);
            }
        }));


    }

}
