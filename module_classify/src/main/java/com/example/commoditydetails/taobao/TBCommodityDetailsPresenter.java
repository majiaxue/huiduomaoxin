package com.example.commoditydetails.taobao;

import android.app.Activity;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.model.TradeResult;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.commoditydetails.pdd.adapter.CommodityDetailsRecAdapter;
import com.example.commoditydetails.taobao.adapter.TBRecommendAdapter;
import com.example.bean.TBBean;
import com.example.bean.TBGoodChoiceBean;
import com.example.bean.TBLedSecuritiesBean;
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
import com.example.utils.QRCode;
import com.example.utils.SPUtil;
import com.example.utils.ViewToBitmap;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.ShareBoardConfig;

import java.io.File;
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
    private List<TBGoodChoiceBean.DataBean> tbRecommendList = new ArrayList<>();
    private TBLedSecuritiesBean tbLedSecuritiesBean;
    private String num_iid;
    private int flag = 0;
    private int number = 0;
    private Bitmap bitmap;
    private TBBean tbBean;

    public TBCommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void login() {

        final AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.showLogin((Activity) mContext, new AlibcLoginCallback() {

            @Override
            public void onSuccess() {
                number++;
                if (number == 2) {
                    shouQuan();
                }
                LogUtil.e("获取淘宝用户信息: " + AlibcLogin.getInstance().getSession());
            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(mContext, "登录失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void shouQuan() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getHeadWithout(CommonResource.SHOUQUAN, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("授权：" + result);
                SPUtil.addParm("link", result);
                ARouter.getInstance().build("/module_classify/shouquan").withString("url", result.replace("web", "wap")).navigation();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("授权：" + errorMsg);
            }
        }));
    }

    //初始化视图
    public void initView(String para, String shopType) {
        if ("0".equals(shopType)) {
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
                tbBean = JSON.parseObject(result, new TypeReference<TBBean>() {
                }.getType());
                if (tbBean != null && tbBean.getData() != null) {
                    if (getView() != null) {
                        getView().tbBeanList(tbBean);
                        getView().tBDetails();
                        LogUtil.e("tBDetails" + "3");
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg---------------->" + errorMsg);
            }
        }));
    }

    public void historySave(String goodsId) {
        Map map = MapUtil.getInstance().addParms("productId", goodsId).addParms("userCode", SPUtil.getUserCode()).addParms("type", 3).build();
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

    //用户收益
    public void earnings() {
        Map build = MapUtil.getInstance().addParms("userId", SPUtil.getUserCode()).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getData(CommonResource.ESTIMATEEARN, build);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("收益-------->" + result);
                if (!TextUtils.isEmpty(result)) {
                    if (getView() != null) {
                        getView().earnings(result);
                        getView().tBDetails();
                        LogUtil.e("tBDetails" + "2");
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

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
                Glide.with(mContext).load("https:" + images.get(position)).apply(RequestOptions.centerCropTransform()).into((ImageView) view);
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

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        final CommodityDetailsRecAdapter commodityDetailsRecAdapter = new CommodityDetailsRecAdapter(mContext, itemDetail, R.layout.itme_commodity_details_rec);
        shopParticulars.setLayoutManager(linearLayoutManager);
        shopParticulars.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        shopParticulars.setHasFixedSize(true);
        shopParticulars.setAdapter(commodityDetailsRecAdapter);
//        shopParticulars.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    Fresco.getImagePipeline().resume();
//                }else {
//                    Fresco.getImagePipeline().pause();
//                }
//
//            }
//        });

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
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
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
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
    }

    //领劵
    public void ledSecurities(String para) {
        LogUtil.e("----------------------->" + para);
        Map map = MapUtil.getInstance().addParms("para", para).build();
        final Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).postHead(CommonResource.TBKGOODSGETGYURLBYALL, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("TBCommodityDetailsResult领劵--------->" + result);

                if (result.startsWith("{\"code\":3")) {
                    number++;
                    if (number == 2) {
                        shouQuan();
                    }
                }else if (result.startsWith("{\"error\":\"15\"")) {
                    Map errorMap = new Gson().fromJson(result, Map.class);
                    LogUtil.e("errorMap---->"+errorMap.toString());
                    num_iid = (String) errorMap.get("num_iid");
                    flag = 1;
                    if (getView() != null) {
                        getView().noCoupon(true);
                    }
                } else {
                    tbLedSecuritiesBean = JSON.parseObject(result, new TypeReference<TBLedSecuritiesBean>() {
                    }.getType());
                    flag = 2;
                    if (tbLedSecuritiesBean != null) {
                        if (getView() != null) {
                            LogUtil.e("成功");
                            getView().ledSecurities(tbLedSecuritiesBean);
                            getView().tBDetails();
                            LogUtil.e("tBDetails" + "1");
                        }

                    }

                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("TBCommodityDetailsErrorMsg领劵--------->" + errorMsg);
            }
        }));
    }

    public void jumpToTB() {
        if (!TextUtils.isEmpty(SPUtil.getToken())) {

            if (flag == 2) {
                //提供给三方传递配置参数
                Map<String, String> exParams = new HashMap<>();
                exParams.put(AlibcConstants.ISV_CODE, "appisvcode");

                //打开指定页面
                AlibcPage alibcPage = new AlibcPage(tbLedSecuritiesBean.getCoupon_click_url());
                LogUtil.e("GotoTB" + flag + "        " + tbLedSecuritiesBean.getCoupon_click_url());
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
                                LogUtil.e("阿里百川" + code + "         " + msg);
                            }
                        });
            } else if (flag == 1) {
                //提供给三方传递配置参数
                Map<String, String> exParams = new HashMap<>();
                exParams.put(AlibcConstants.ISV_CODE, "appisvcode");

                //打开指定页面
                AlibcBasePage detailPage = new AlibcDetailPage(num_iid);
                LogUtil.e("GotoTB" + flag + "        " + num_iid);
                //设置页面打开方式
                AlibcShowParams showParams = new AlibcShowParams(OpenType.Native, false);

                //使用百川sdk提供默认的Activity打开detail
                AlibcTrade.show((Activity) mContext, detailPage, showParams, null, exParams,
                        new AlibcTradeCallback() {
                            @Override
                            public void onTradeSuccess(TradeResult tradeResult) {
                                //打开电商组件，用户操作中成功信息回调。tradeResult：成功信息（结果类型：加购，支付；支付结果）
                                LogUtil.e(tradeResult.toString());
                            }

                            @Override
                            public void onFailure(int code, String msg) {
                                //打开电商组件，用户操作中错误信息回调。code：错误码；msg：错误信息
                                LogUtil.e("阿里百川" + code + "         " + msg);
                            }
                        });
            }
        } else {
            ARouter.getInstance().build("/mine/login").navigation();
        }
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

    //加载生成图片布局
    public void viewToImage(String qRImage, String path) {
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
        String zkFinalPrice = tbBean.getData().getZk_final_price();
        if (!TextUtils.isEmpty(zkFinalPrice)) {
            if (zkFinalPrice.contains("-")) {
                String[] split = zkFinalPrice.split("-");
                String couponInfo = tbLedSecuritiesBean.getCoupon_info();
                if (!TextUtils.isEmpty(couponInfo)) {
                    String substring = couponInfo.substring(couponInfo.indexOf("减"));
                    String price = substring.substring(1, substring.indexOf("元"));
                    double sub = ArithUtil.sub(Double.valueOf(split[0]), Double.valueOf(price));
                    preferentialPrice.setText("￥" + sub);//优惠价
                    originalPrice.setText("原价：￥" + split[0]);//原价
                    couponPrice.setText("￥" + ArithUtil.sub(Double.valueOf(split[0]), sub));

                }

            } else {
                String couponInfo = tbLedSecuritiesBean.getCoupon_info();
                if (!TextUtils.isEmpty(couponInfo)) {
                    String substring = couponInfo.substring(couponInfo.indexOf("减"));
                    String price = substring.substring(1, substring.indexOf("元"));
                    double sub = ArithUtil.sub(Double.valueOf(zkFinalPrice), Double.valueOf(price));
                    preferentialPrice.setText("￥" + sub);//优惠价
                    originalPrice.setText("原价：￥" + zkFinalPrice);//原价
                    couponPrice.setText("￥" + ArithUtil.sub(Double.valueOf(zkFinalPrice), sub));

                }


            }
            LogUtil.e("url主图---------->" + tbBean.getData().getImages().get(0));
            image.setImageURI(Uri.fromFile(new File(path)));
            name.setText(tbBean.getData().getTitle());
            number.setText("已售" + tbBean.getData().getSellCount() + "件");//已售
            Bitmap qr = QRCode.createQRImage(qRImage, DisplayUtil.dip2px(mContext, 150), DisplayUtil.dip2px(mContext, 150));
            qRCode.setImageBitmap(qr);
            LogUtil.e("url2二维码---------->" + qRImage);

            this.bitmap = ViewToBitmap.createBitmap3(view, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
        }
    }

    //分享
    public void share() {
        ShareBoardConfig config = new ShareBoardConfig();
        config.setTitleText("分享到")
                .setTitleTextColor(Color.parseColor("#222222"))
                .setMenuItemTextColor(Color.parseColor("#666666"))
                .setMenuItemIconPressedColor(Color.parseColor("#000000"))
//                .setMenuItemBackgroundColor(Color.parseColor("#fd3c15"),Color.parseColor("#008577"))
                .setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_ROUNDED_SQUARE, (int) mContext.getResources().getDimension(R.dimen.dp_20));
//                .setCancelButtonText("您取消了分享");


        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, bitmap))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(shareListener).open(config);
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LogUtil.e("start:" + share_media.toString());
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            LogUtil.e("result:" + share_media.toString());
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
}