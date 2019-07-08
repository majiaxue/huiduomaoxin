package com.example.commoditydetails.taobao;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.bean.TBBean;
import com.example.bean.TBLedSecuritiesBean;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.ArithUtil;
import com.example.utils.CustomDialog;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import butterknife.BindView;

/**
 * Created by cuihaohao on 2019/6/14
 * Describe:淘宝商品详情
 */
@Route(path = "/module_classify/TBCommodityDetailsActivity")
public class TBCommodityDetailsActivity extends BaseActivity<TBCommodityDetailsView, TBCommodityDetailsPresenter> implements TBCommodityDetailsView, NestedScrollView.OnScrollChangeListener {

    @BindView(R2.id.commodity_image_back)
    ImageView commodityImageBack;
    @BindView(R2.id.commodity_xbanner)
    XBanner commodityXbanner;
    @BindView(R2.id.commodity_name)
    TextView commodityName;
    @BindView(R2.id.commodity_text)
    TextView commodityText;
    @BindView(R2.id.commodity_preferential_price)
    TextView commodityPreferentialPrice;
    @BindView(R2.id.commodity_earnings)
    TextView commodityEarnings;
    @BindView(R2.id.commodity_original_price)
    TextView commodityOriginalPrice;
    @BindView(R2.id.commodity_number_sold)
    TextView commodityNumberSold;
    @BindView(R2.id.commodity_shop_image)
    SimpleDraweeView commodityShopImage;
    @BindView(R2.id.commodity_shop_name)
    TextView commodityShopName;
    @BindView(R2.id.shop_describe_score)
    TextView shopDescribeScore;
    @BindView(R2.id.shop_service_score)
    TextView shopServiceScore;
    @BindView(R2.id.shop_logistics_score)
    TextView shopLogisticsScore;
    @BindView(R2.id.shop_particulars)
    RecyclerView shopParticulars;
    @BindView(R2.id.shop_recommend_rec)
    RecyclerView shopRecommendRec;
    @BindView(R2.id.commodity_nested_scroll)
    NestedScrollView commodityNestedScroll;
    @BindView(R2.id.commodity_stick)
    ImageView commodityStick;
    @BindView(R2.id.commodity_go_home)
    LinearLayout commodityGoHome;
    @BindView(R2.id.commodity_collect_image)
    ImageView commodityCollectImage;
    @BindView(R2.id.commodity_collect)
    LinearLayout commodityCollect;
    @BindView(R2.id.commodity_share)
    LinearLayout commodityShare;
    @BindView(R2.id.commodity_led_securities)
    LinearLayout commodityLedSecurities;
    @BindView(R2.id.commodity_linear)
    LinearLayout commodityLinear;
    @BindView(R2.id.commodity_into_shop)
    TextView commodityIntoShop;
    @BindView(R2.id.commodity_coupon_price)
    TextView commodityCouponPrice;
    @BindView(R2.id.commodity_time)
    TextView commodityTime;
    @BindView(R2.id.commodity_immediately_receive)
    TextView commodityImmediatelyReceive;
    @BindView(R2.id.commodity_led_securities_text)
    TextView commodityLedSecuritiesText;
    @BindView(R2.id.commodity_details_no_coupon)
    LinearLayout commodityDetailsNoCoupon;

    @Autowired(name = "para")
    String para;
    @Autowired(name = "shoptype")
    String shopType;
    private TBBean tbBeanList;
    private TBLedSecuritiesBean tbLedSecuritiesBean;

    private String earnings;
    private int status = 0;
    private String imageUrl;
    private CustomDialog customDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        LogUtil.e("123456              " + para + "        " + shopType);

        customDialog = new CustomDialog(this);
        customDialog.show();
        presenter.login();
        //优惠券
        presenter.ledSecurities(para);

        presenter.historySave(para);
        //用户收益
        presenter.earnings();
        //加载视图
        presenter.initView(para, shopType);
        //字体加中划线
        commodityOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        //字体加粗
        commodityCouponPrice.getPaint().setFakeBoldText(true);
        //推荐商品
        presenter.setRecommendRec(shopRecommendRec);
    }

    @Override
    public void initClick() {
        //返回上个页面
        commodityImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //监听    NestedScrollView
        commodityNestedScroll.setOnScrollChangeListener(this);
        //点击回到顶部
        commodityStick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commodityNestedScroll.fullScroll(NestedScrollView.FOCUS_UP);
            }
        });
        //回到首页
        commodityGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/home/main").navigation();
            }
        });
        //进入店铺
        commodityIntoShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/module_classify/tshop_home")
                        .withString("url", tbBeanList.getData().getSeller().getTaoShopUrl())
                        .navigation();
            }
        });
        //进入店铺
        commodityShopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/module_classify/tshop_home")
                        .withString("url", tbBeanList.getData().getSeller().getTaoShopUrl())
                        .navigation();
            }
        });
        //进入店铺
        commodityShopName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance()
                        .build("/module_classify/tshop_home")
                        .withString("url", tbBeanList.getData().getSeller().getTaoShopUrl())
                        .navigation();
            }
        });
        //分享
        //立即领取
        commodityImmediatelyReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectUrl(new OnSuccessListener() {
                    @Override
                    public void doLogic(String s) {
                        //获取到重定向的url可以操作啦
                        LogUtil.e("doLogic: " + s);
                        presenter.jumpToTB(s);
                    }
                });

            }
        });
        //分享
        commodityShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.share();
            }
        });
        //领劵
        commodityLedSecurities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectUrl(new OnSuccessListener() {
                    @Override
                    public void doLogic(String s) {
                        //获取到重定向的url可以操作啦
                        LogUtil.e("doLogic: " + s);
                        presenter.jumpToTB(s);
                    }
                });
            }
        });
        //收藏
        commodityCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommodityDetailsActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                presenter.goodsCollect(commodityCollectImage, para);
            }
        });
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
        int[] ints = new int[2];
        commodityLinear.getLocationOnScreen(ints);
        int y = ints[1];
        if (y <= commodityImageBack.getHeight()) {
            //显示
            commodityStick.setVisibility(View.VISIBLE);
        } else {
            //隐藏
            commodityStick.setVisibility(View.GONE);
        }
    }

    @Override
    public TBCommodityDetailsView createView() {
        return this;
    }

    @Override
    public TBCommodityDetailsPresenter createPresenter() {
        return new TBCommodityDetailsPresenter(this);
    }

    //详情回调
    @Override
    public void tbBeanList(TBBean tbBeanList) {
        this.tbBeanList = tbBeanList;
        //详情轮播图
        List<String> images = tbBeanList.getData().getImages();
        presenter.setXBanner(commodityXbanner, images);

        //商品详情图片
        List<String> itemDetail = tbBeanList.getData().getItem_detail();
        if (itemDetail.size() != 0) {
            presenter.setShopParticulars(shopParticulars, itemDetail);
        } else {
            presenter.setShopParticulars(shopParticulars, images);
        }

        //收藏状态
        presenter.isCollect(commodityCollectImage, para);
        commodityName.setText(tbBeanList.getData().getTitle());//名字
        commodityNumberSold.setText("已售" + tbBeanList.getData().getSellCount() + "件");//已售
        commodityShopName.setText(tbBeanList.getData().getSeller().getShopName() + "");//商家名
        commodityShopImage.setImageURI(Uri.parse("https:" + tbBeanList.getData().getSeller().getShopIcon()));//商家icon
        shopDescribeScore.setText("" + tbBeanList.getData().getSeller().getEvaluates().get(0).getScore());
        shopServiceScore.setText("" + tbBeanList.getData().getSeller().getEvaluates().get(1).getScore());
        shopLogisticsScore.setText("" + tbBeanList.getData().getSeller().getEvaluates().get(2).getScore());
        String zkFinalPrice = tbBeanList.getData().getZk_final_price();
        if (!TextUtils.isEmpty(zkFinalPrice)) {
            if (zkFinalPrice.contains("-")) {
                String[] split = zkFinalPrice.split("-");
                commodityPreferentialPrice.setText("￥" + split[0]);//优惠价

                commodityOriginalPrice.setText("原价：￥" + split[0]);//原价
            } else {
                commodityPreferentialPrice.setText("￥" + zkFinalPrice);//优惠价
                commodityOriginalPrice.setText("原价：￥" + zkFinalPrice);//原价
            }
        }

    }


    @Override
    public void ledSecurities(TBLedSecuritiesBean tbLedSecuritiesBean) {
        this.tbLedSecuritiesBean = tbLedSecuritiesBean;
        this.imageUrl = tbLedSecuritiesBean.getCoupon_click_url();
    }

    @Override
    public void earnings(String earnings) {
        this.earnings = earnings;
    }

    @Override
    public void tBDetails() {
        status++;
        LogUtil.e("status" + status);
        if (status == 3) {
//            status = 0;
            customDialog.dismiss();
            String zkFinalPrice = tbBeanList.getData().getZk_final_price();
            if (!TextUtils.isEmpty(zkFinalPrice)) {
                if (zkFinalPrice.contains("-")) {
                    String[] split = zkFinalPrice.split("-");
                    String commission_rate = tbLedSecuritiesBean.getCommission_rate();
                    String couponInfo = tbLedSecuritiesBean.getCoupon_info();
                    if (!TextUtils.isEmpty(couponInfo)) {
                        String substring = couponInfo.substring(couponInfo.indexOf("减"));
                        String price = substring.substring(1, substring.indexOf("元"));
                        double earnings1 = ArithUtil.div(Double.valueOf(earnings), 100, 2);//用户个人收益
                        double sub = ArithUtil.sub(Double.valueOf(split[0]), Double.valueOf(price));
                        double mul = ArithUtil.mul(ArithUtil.div(Double.valueOf(commission_rate),100,2), sub);//商品收益乘商品价格

                        commodityPreferentialPrice.setText("￥" + sub);//优惠价

                        commodityOriginalPrice.setText("原价：￥" + split[0]);//原价
                        commodityEarnings.setText("预估收益：￥" + ArithUtil.mul(mul, earnings1));//收益
                        LogUtil.e("商品收益" + Double.valueOf(commission_rate));
                        LogUtil.e("预估收益：" + "个人收益" + earnings1 + "商品收益" + mul + "最终收益" + ArithUtil.mul(mul, earnings1));
                        commodityCouponPrice.setText(price + "元");
                        commodityTime.setText("使用期限：" + tbLedSecuritiesBean.getCoupon_start_time() + "~" + tbLedSecuritiesBean.getCoupon_end_time());
                    }

                } else {
                    String commission_rate = tbLedSecuritiesBean.getCommission_rate();
                    String couponInfo = tbLedSecuritiesBean.getCoupon_info();
                    if (!TextUtils.isEmpty(couponInfo)) {
                        String substring = couponInfo.substring(couponInfo.indexOf("减"));
                        String price = substring.substring(1, substring.indexOf("元"));
                        double earnings1 = ArithUtil.div(Double.valueOf(earnings), 100, 2);
                        double sub = ArithUtil.sub(Double.valueOf(zkFinalPrice), Double.valueOf(price));
                        double mul = ArithUtil.mul(ArithUtil.div(Double.valueOf(commission_rate),100,2), sub);
                        commodityPreferentialPrice.setText("￥" + sub);//优惠价
                        commodityOriginalPrice.setText("原价：￥" + zkFinalPrice);//原价
                        commodityEarnings.setText("预估收益：￥" + ArithUtil.mul(mul, earnings1));//收益
                        LogUtil.e("商品收益" + Double.valueOf(commission_rate));
                        LogUtil.e("预估收益：" + "个人收益" + earnings1 + "商品收益" + mul + "最终收益" + ArithUtil.mul(mul, earnings1));
                        commodityCouponPrice.setText(price + "元");
                        commodityTime.setText("使用期限：" + tbLedSecuritiesBean.getCoupon_start_time() + "~" + tbLedSecuritiesBean.getCoupon_end_time());

                    }


                }
            }

            Glide.with(this)
                    .asBitmap()
                    .load("https:" + tbBeanList.getData().getImages().get(0))
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            saveImageToPhotos(bitmap);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });

        }
    }

    @Override
    public void noCoupon(boolean noCoupon) {
        if (noCoupon) {
            customDialog.dismiss();
            commodityDetailsNoCoupon.setVisibility(View.GONE);
            commodityEarnings.setVisibility(View.GONE);
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        LogUtil.e("HomeFragment" + "可见");
//        //优惠券
//        presenter.ledSecurities(para);
//    }

    /**
     * 保存二维码到本地相册
     */
    private void saveImageToPhotos(Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "wwww" + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 30, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        presenter.viewToImage(imageUrl, file.getPath());
        LogUtil.e("图片路径" + file.getPath());
    }

    private void redirectUrl(final OnSuccessListener onSuccessListener) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                HttpURLConnection conn = null;
                try {
                    conn = (HttpURLConnection) new URL(imageUrl).openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                conn.setInstanceFollowRedirects(false);
                conn.setConnectTimeout(5000);
                String url = conn.getHeaderField("Location");
                conn.disconnect();
                return url;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                onSuccessListener.doLogic(s);

            }
        }.execute();
    }

    private interface OnSuccessListener {
        void doLogic(String s);
    }


}
