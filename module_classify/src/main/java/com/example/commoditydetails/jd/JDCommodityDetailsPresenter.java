package com.example.commoditydetails.jd;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.JDGoodsRecBean;
import com.example.bean.JDLedSecuritiesBean;
import com.example.commoditydetails.jd.adapter.JDRecAdapter;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.webview.WebViewActivity;
import com.example.common.CommonResource;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.ArithUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.QRCode;
import com.example.utils.SPUtil;
import com.example.utils.ViewToBitmap;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.io.File;
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
    private Bitmap bitmap;

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

    public void historySave(String goodsId) {
        Map map = MapUtil.getInstance().addParms("productId", goodsId).addParms("userCode", SPUtil.getUserCode()).addParms("type", 2).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.HISTORYSAVE, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("添加浏览记录" + result);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("添加浏览记录errorMsg" + errorMsg);
            }
        }));

    }

    //商品轮播图
    public void setXBanner(XBanner commodityXbanner, final JDGoodsRecBean.DataBean.ListsBean listsBeanList) {
        List<JDGoodsRecBean.DataBean.ListsBean.ImageInfoBean.ImageListBean> imageList = listsBeanList.getImageInfo().getImageList();
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
    public void setShopParticulars(RecyclerView shopParticulars, final JDGoodsRecBean.DataBean.ListsBean listsBeanList) {
        List<JDGoodsRecBean.DataBean.ListsBean.ImageInfoBean.ImageListBean> imageList = listsBeanList.getImageInfo().getImageList();
        for (int i = 0; i < imageList.size(); i++) {
            images.add(imageList.get(i).getUrl());
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, images, R.layout.itme_commodity_details_rec);
        shopParticulars.setLayoutManager(linearLayoutManager);
        shopParticulars.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        shopParticulars.setHasFixedSize(true);
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
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
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
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //领劵
    public void ledSecurities(String url, String couponUrl) {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            Map map = MapUtil.getInstance().addParms("materialId", url).addParms("userCode", SPUtil.getUserCode()).addParms("couponUrl", couponUrl).build();
            Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postData(CommonResource.JDGETGOODSMARKETLINK, map);
            RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
                @Override
                public void onSuccess(String result, String msg) {
                    JDLedSecuritiesBean jdLedSecuritiesBean = JSON.parseObject(result, new TypeReference<JDLedSecuritiesBean>() {
                    }.getType());
                    if (jdLedSecuritiesBean != null && jdLedSecuritiesBean.getData() != null) {
                        String clickURL = jdLedSecuritiesBean.getData().getClickURL();
                        LogUtil.e("url---------->" + clickURL);
                        if (getView() != null) {
                            getView().qrImage(clickURL);
                        }

                    }
                }

                @Override
                public void onError(String errorCode, String errorMsg) {

                }
            }));
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //点击领劵
    public void clickLedSecurities(String clickURL) {
        Intent intent = new Intent(mContext, WebViewActivity.class);
        intent.putExtra("url", clickURL);
        mContext.startActivity(intent);
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
                if (jDGoodsRecBean != null) {
                    if (jDGoodsRecBean.getData() != null && jDGoodsRecBean.getData().getLists() != null && jDGoodsRecBean.getData().getLists().size() != 0) {
                        if (getView() != null) {
                            getView().isNoGoods(false);
                        }
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
                    } else {
                        if (getView() != null) {
                            getView().isNoGoods(true);
                        }
                    }

                } else {
                    LogUtil.e("空");
                }
            }


            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg京东推荐商品--------------->" + errorMsg);
            }
        }));
    }

    //分享
//    public void share(JDGoodsRecBean.DataBean.ListsBean listsBean,String qRImage) {
//        LogUtil.e("qRImage---------->"+qRImage);
//        View inflate = LayoutInflater.from(mContext).inflate(R.layout.pop_share, null, false);
//        final LinearLayout popShareAll = inflate.findViewById(R.id.pop_share_all);
//        SimpleDraweeView popShareImage = inflate.findViewById(R.id.pop_share_image);
//        TextView popShareName = inflate.findViewById(R.id.pop_share_name);
//        TextView popSharePrice = inflate.findViewById(R.id.pop_share_price);
//        ImageView popShareQR = inflate.findViewById(R.id.pop_share_qr);
//        final LinearLayout popShareWeiXin = inflate.findViewById(R.id.pop_share_weixin);
//        LinearLayout popSharePengYouQuan = inflate.findViewById(R.id.pop_share_pengyouquan);
//        LinearLayout popShareQQ = inflate.findViewById(R.id.pop_share_qq);
//        LinearLayout popShareQZone = inflate.findViewById(R.id.pop_share_qzone);
//        final TextView popShareCancel = inflate.findViewById(R.id.pop_share_cancel);
//        popShareImage.setImageURI(Uri.parse(listsBean.getImageInfo().getImageList().get(0).getUrl()));
//        popShareName.setText(listsBean.getSkuName());
//        popSharePrice.setText("￥" + ArithUtil.sub(Double.valueOf(listsBean.getPriceInfo().getPrice()), Double.valueOf(listsBean.getCouponInfo().getCouponList().get(0).getDiscount())));
//        Bitmap qrImage = QRCode.createQRImage(qRImage, DisplayUtil.dip2px(mContext, 58), DisplayUtil.dip2px(mContext, 58));
//        popShareQR.setImageBitmap(qrImage);
//        final Bitmap bitmap3 = ViewToBitmap.createBitmap3(popShareAll, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
//
//        PopUtils.shareBottom(mContext, inflate, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
//            @Override
//            public void setOnPop(final PopupWindow pop) {
//                popShareCancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pop.dismiss();
//                    }
//                });
//
//                popShareWeiXin.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        LogUtil.e("分享");
////                        pop.dismiss();
//                        new ShareAction((Activity) mContext)
//                                .withMedia(new UMImage(mContext, bitmap3))
//                                .setPlatform(SHARE_MEDIA.WEIXIN)//传入平台
//                                .setCallback(shareListener)//回调监听器
//                                .share();
//                    }
//                });
//            }
//        });
//
//    }

    //加载生成图片布局
    public void viewToImage(JDGoodsRecBean.DataBean.ListsBean listsBean,String qRImage, String path) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.sharebg, null, false);
        ImageView image = view.findViewById(R.id.share_image);
        TextView name = view.findViewById(R.id.share_name);
        TextView preferentialPrice = view.findViewById(R.id.share_preferential_price);
        TextView originalPrice = view.findViewById(R.id.share_original_price);
        TextView couponPrice = view.findViewById(R.id.share_coupon_price);
        TextView number = view.findViewById(R.id.share_number);
        ImageView qRCode = view.findViewById(R.id.share_qr_code);
        //字体加中划线
        originalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        double div = ArithUtil.sub(Double.valueOf(listsBean.getPriceInfo().getPrice()), Double.valueOf(listsBean.getCouponInfo().getCouponList().get(0).getDiscount()));//到手价
        LogUtil.e("url主图---------->" + listsBean.getImageInfo().getImageList().get(0).getUrl());
        image.setImageURI(Uri.fromFile(new File(path)));
//        Glide.with(mContext)
//                .load(s)
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .placeholder(R.drawable.icon_logo)
//                .error(R.drawable.icon_chahao)
//                .into(image);

        name.setText(listsBean.getSkuName());
        preferentialPrice.setText("￥" + div);
        originalPrice.setText("￥" + Double.valueOf(listsBean.getPriceInfo().getPrice()));
        couponPrice.setText("￥" + ArithUtil.sub(Double.valueOf(listsBean.getPriceInfo().getPrice()), div) + "元");
        number.setText("已售" + listsBean.getInOrderCount30Days() + "件");//已售
        Bitmap qr = QRCode.createQRImage(qRImage, DisplayUtil.dip2px(mContext, 150), DisplayUtil.dip2px(mContext, 150));
        qRCode.setImageBitmap(qr);
        LogUtil.e("url2二维码---------->" + qRImage);

        this.bitmap = ViewToBitmap.createBitmap3(view, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
    }

    //分享
    public void share() {
        ShareBoardConfig config = new ShareBoardConfig();
        config.setTitleText("分享到")
                .setTitleTextColor(Color.parseColor("#222222"))
                .setMenuItemTextColor(Color.parseColor("#666666"))
                .setMenuItemIconPressedColor(Color.parseColor("#000000"))
                .setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_ROUNDED_SQUARE, (int) mContext.getResources().getDimension(R.dimen.dp_20));
        LogUtil.e("bitmap" + bitmap);

        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, bitmap))
                .withText("hello")
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(shareListener).open(config);

    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Toast.makeText(mContext,"成功了",Toast.LENGTH_LONG).show();
            LogUtil.e("成功了");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            LogUtil.e("失败"+t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(ShareDetailActivity.this,"取消了",Toast.LENGTH_LONG).show();
            LogUtil.e("取消了");
        }
    };

}
