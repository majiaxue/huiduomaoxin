package com.example.commoditydetails.jd;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.JDGoodsRecBean;
import com.example.commoditydetails.jd.adapter.JDRecAdapter;
import com.example.commoditydetails.jd.bean.JDLedSecuritiesBean;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.taobao.adapter.TBRecommendAdapter;
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
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/6/15
 * Describe:
 */
public class JDCommodityDetailsPresenter extends BasePresenter<JDCommodityDetailsView> {

    private List<String> url = new ArrayList<>();
    private List<String> images = new ArrayList<>();
    private List<JDGoodsRecBean.DataBean.ListsBean> listsBeanList = new ArrayList<>();

    public JDCommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    //收益
    public void earnings() {

        Map build = MapUtil.getInstance().addParms("userId", SPUtil.getUserCode()).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.ESTIMATEEARN, build);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("收益-------->" + result);
                if (!result.equals("")) {
                    if (getView() != null) {
                        getView().earnings(result);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    //商品轮播图
    public void setXBanner(XBanner commodityXbanner, final List<JDGoodsRecBean.DataBean.ListsBean> listsBeanList, int position) {
        List<JDGoodsRecBean.DataBean.ListsBean.ImageInfoBean.ImageListBean> imageList = listsBeanList.get(position).getImageInfo().getImageList();
        for (int i = 0; i < imageList.size(); i++) {
            url.add(imageList.get(i).getUrl());
        }

        commodityXbanner.setData(url, null);
//        commodityXbanner.setBannerData(R.layout.image_fresco, images);
        commodityXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
//                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
                Glide.with(mContext).load(url.get(position)).apply(RequestOptions.centerCropTransform()).into((ImageView) view);
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
    public void setShopParticulars(RecyclerView shopParticulars, final List<JDGoodsRecBean.DataBean.ListsBean> listsBeanList, int position) {
        List<JDGoodsRecBean.DataBean.ListsBean.ImageInfoBean.ImageListBean> imageList = listsBeanList.get(position).getImageInfo().getImageList();
        for (int i = 0; i < imageList.size(); i++) {
            images.add(imageList.get(i).getUrl());
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, images, R.layout.itme_commodity_details_rec);
        shopParticulars.setLayoutManager(linearLayoutManager);
        shopParticulars.setAdapter(commodityDetailsRecAdapter);
    }


    //是否收藏
    public void isCollect(final ImageView commodityCollectImage, String skuid) {
        LogUtil.e("id------------->" + skuid);
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.FAVORITESTATUS + "/" + skuid, SPUtil.getToken());

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
    public void goodsCollect(final ImageView commodityCollectImage, String skuid) {
        Map map = MapUtil.getInstance().addParms("productId", skuid).addParms("type", 3).build();
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
    public void ledSecurities(String url, String couponUrl) {
        Map map = MapUtil.getInstance().addParms("materialId", url).addParms("userCode", SPUtil.getUserCode()).addParms("couponUrl", couponUrl).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postData(CommonResource.JDGETGOODSMARKETLINK, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                JDLedSecuritiesBean jdLedSecuritiesBean = JSON.parseObject(result, new TypeReference<JDLedSecuritiesBean>() {
                }.getType());
                String clickURL = jdLedSecuritiesBean.getData().getClickURL();
                LogUtil.e("url---------->" + clickURL);

                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", clickURL);
                mContext.startActivity(intent);

            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }


    public void setRecommendRec(final RecyclerView shopRecommendRec, String name) {
        Map build = MapUtil.getInstance().addParms("isCoupon", 1).addParms("pageIndex", 1).addParms("pageSize", 20).addParms("isHot", 1).addParms("keyword", name).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.JDGOODSLIST, build);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult京东推荐商品--------------->" + result);
                final JDGoodsRecBean jDGoodsRecBean = JSON.parseObject(result, new TypeReference<JDGoodsRecBean>() {
                }.getType());


                listsBeanList.clear();
                listsBeanList.addAll(jDGoodsRecBean.getData().getLists());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                JDRecAdapter jdRecAdapter = new JDRecAdapter(mContext, listsBeanList, R.layout.item_base_rec);
                shopRecommendRec.setLayoutManager(linearLayoutManager);
                shopRecommendRec.setAdapter(jdRecAdapter);

                jdRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
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

                jdRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
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


            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg京东推荐商品--------------->" + errorMsg);
            }
        }));
    }

}
