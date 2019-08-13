package com.example.commoditydetails.taobao;

import android.content.Intent;
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
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.bean.BannerImageBean;
import com.example.bean.TBBean;
import com.example.bean.TBGoodsDetailsBean;
import com.example.bean.TBLedSecuritiesBean;
import com.example.common.CommonResource;
import com.example.dbflow.ShareBean;
import com.example.dbflow.ShareUtil;
import com.example.module_base.ModuleBaseApplication;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.AppManager;
import com.example.utils.ArithUtil;
import com.example.utils.CustomDialog;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.umeng.socialize.UMShareAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    @BindView(R2.id.shop_xinxi)
    LinearLayout shopXinxi;

    @Autowired(name = "para")
    String para;
    @Autowired(name = "shoptype")
    String shopType;
    @Autowired(name = "youhuiquan")
    double youhuiquan;
    @Autowired(name = "coupon_start_time")
    String coupon_start_time;
    @Autowired(name = "coupon_end_time")
    String coupon_end_time;
    @Autowired(name = "commission_rate")
    String commission_rate;
    @Autowired(name = "type")
    int type;

    private int status = 0;
    private CustomDialog customDialog;
    private List<BannerImageBean> bannerImageBeans = new ArrayList<>();
    //触碰标识
    private long exitTime = 0;
    private double div;
    private List<String> images;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
//        LogUtil.e("123456              " + para + "        " + shopType);
        ModuleBaseApplication.initShare();
        shopXinxi.setVisibility(View.GONE);
        commodityIntoShop.setVisibility(View.GONE);
        customDialog = new CustomDialog(this);
        customDialog.show();

        presenter.login();

        //加载视图
        presenter.initView(para);

        if (!TextUtils.isEmpty(coupon_end_time) && !TextUtils.isEmpty(coupon_start_time)) {
            commodityCouponPrice.setText(youhuiquan + "元优惠劵");
            commodityTime.setText("使用期限：" + coupon_start_time + "~" + coupon_end_time);
        } else {
            commodityDetailsNoCoupon.setVisibility(View.GONE);
        }

        //字体加中划线
        commodityOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        //字体加粗
        commodityCouponPrice.getPaint().setFakeBoldText(true);
        //店铺头像
        commodityShopImage.setImageResource(R.drawable.img_taobao);
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
        //分享
        //立即领取
        commodityImmediatelyReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessDialogUtil.showProcessDialog(TBCommodityDetailsActivity.this);
                presenter.ledSecurities(para);
            }
        });
        //分享
        commodityShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (1 == status) {
                    if ((System.currentTimeMillis() - exitTime) > 3000) {
                        ProcessDialogUtil.showProcessDialog(TBCommodityDetailsActivity.this);
                        presenter.ShareledSecurities(para, youhuiquan);
                        exitTime = System.currentTimeMillis();
                    } else {
                        Toast.makeText(TBCommodityDetailsActivity.this, "图片生成中!请勿重复点击", Toast.LENGTH_SHORT).show();
                    }
//                    presenter.share();
                }
            }
        });
        //领劵
        commodityLedSecurities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessDialogUtil.showProcessDialog(TBCommodityDetailsActivity.this);
                presenter.ledSecurities(para);

            }
        });
        //收藏
        commodityCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    public void tbBeanList(TBGoodsDetailsBean tbGoodsDetailsBean, List<String> imageList) {
        customDialog.dismiss();
        try {
//            this.tbGoodsDetailsBean = tbGoodsDetailsBean;
            //详情轮播图
            if (tbGoodsDetailsBean.getN_tbk_item().getSmall_images() != null) {
                images = tbGoodsDetailsBean.getN_tbk_item().getSmall_images().getString();
                for (int i = 0; i < images.size(); i++) {
                    bannerImageBeans.add(new BannerImageBean(images.get(i)));
                }
            } else {
                bannerImageBeans.add(new BannerImageBean(tbGoodsDetailsBean.getN_tbk_item().getPict_url()));
            }
            presenter.setXBanner(commodityXbanner, bannerImageBeans);


            commodityName.setText(tbGoodsDetailsBean.getN_tbk_item().getTitle());//名字
            commodityNumberSold.setText("已售" + tbGoodsDetailsBean.getN_tbk_item().getVolume() + "件");//已售
            commodityShopName.setText(tbGoodsDetailsBean.getN_tbk_item().getNick() + "");//商家名
            String zkFinalPrice = tbGoodsDetailsBean.getN_tbk_item().getZk_final_price();

            if (!TextUtils.isEmpty(zkFinalPrice)) {
                if (zkFinalPrice.contains("-")) {
                    String[] split = zkFinalPrice.split("-");
                    if (youhuiquan != 0) {
                        double sub = ArithUtil.sub(Double.valueOf(zkFinalPrice) , youhuiquan);//商品价格
                        if (0 == type) {
                            div = Double.valueOf(commission_rate) / 100;
                        } else {
                            //商品佣金率
                            div = Double.valueOf(commission_rate) / 10000;
                        }
                        double mul = div * sub * 0.9;
                        commodityPreferentialPrice.setText("￥" + sub);//优惠价
                        commodityOriginalPrice.setText("原价：￥" + split[0]);//原价
                        commodityEarnings.setText("预估收益：￥" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));//收益
                        LogUtil.e("预估收益：" + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "商品佣金" + div + "商品优惠后" + sub + "最终收益" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
                    } else {
                        if (0 == type) {
                            div = Double.valueOf(commission_rate) / 100;
                        } else {
                            //商品佣金率
                            div = Double.valueOf(commission_rate) / 10000;
                        }
                        double mul = div * Double.valueOf(split[0]) * 0.9;
                        commodityPreferentialPrice.setText("￥" + split[0]);//优惠价
                        commodityOriginalPrice.setText("原价：￥" + split[0]);//原价
                        commodityEarnings.setText("预估收益：￥" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));//收益
                        LogUtil.e("预估收益：" + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "商品佣金" + div + "商品优惠后" + split[0] + "最终收益" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
                    }

                } else {
                    if (youhuiquan != 0) {
                        double sub = ArithUtil.sub(Double.valueOf(zkFinalPrice) , youhuiquan);//商品价格
                        if (0 == type) {
                            div = Double.valueOf(commission_rate) / 100;
                        } else {
                            //商品佣金率
                            div = Double.valueOf(commission_rate) / 10000;
                        }
                        double mul = div * sub * 0.9;
                        commodityPreferentialPrice.setText("￥" + sub);//优惠价
                        commodityOriginalPrice.setText("原价：￥" + zkFinalPrice);//原价
                        commodityEarnings.setText("预估收益：￥" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));//收益
                        LogUtil.e("预估收益：" + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "商品佣金" + div + "商品优惠后" + sub + "最终收益" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
                    } else {
                        if (0 == type) {
                            div = Double.valueOf(commission_rate) / 100;
                        } else {
                            //商品佣金率
                            div = Double.valueOf(commission_rate) / 10000;
                        }
                        double mul = div * Double.valueOf(zkFinalPrice) * 0.9;
                        commodityPreferentialPrice.setText("￥" + zkFinalPrice);//优惠价
                        commodityOriginalPrice.setText("原价：￥" + zkFinalPrice);//原价
                        commodityEarnings.setText("预估收益：￥" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));//收益
                        LogUtil.e("预估收益：" + "个人收益" + SPUtil.getFloatValue(CommonResource.BACKBL) + "商品佣金" + div + "商品优惠后" + zkFinalPrice + "最终收益" + ArithUtil.mulRound(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
                    }
                }
            }


            //商品详情图片
            if (imageList != null && imageList.size() != 0) {
                presenter.setShopParticulars(shopParticulars, imageList);
            } else {
                presenter.setShopParticulars(shopParticulars, images);
            }

            //浏览历史
            presenter.historySave(para);
            //收藏状态
            presenter.isCollect(commodityCollectImage, para);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("异常了");
        }
    }

    @Override
    public void tBDetails() {
        status++;
    }

    @Override
    public void noCoupon(boolean noCoupon) {
        if (noCoupon) {
//            customDialog.dismiss();
            commodityDetailsNoCoupon.setVisibility(View.GONE);
            commodityEarnings.setVisibility(View.GONE);
        }
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        LogUtil.e("HomeFragment" + "可见");
//    }

//    /**
//     * 保存二维码到本地相册
//     */
//    private void saveImageToPhotos(Bitmap bmp) {
//        // 首先保存图片
//        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        String fileName = "wwww" + ".jpg";
//        final File file = new File(appDir, fileName);
//        try {
//            FileOutputStream fos = new FileOutputStream(file);
//            bmp.compress(Bitmap.CompressFormat.JPEG, 30, fos);
//            fos.flush();
//            fos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        presenter.viewToImage(imageUrl, file.getPath(),youhuiquan);
//
//        LogUtil.e("图片路径" + file.getPath());
//    }

//    private void redirectUrl(String url, final OnSuccessListener onSuccessListener) {
//        new AsyncTask<String, Integer, String>() {
//            @Override
//            protected String doInBackground(String... strings) {
//                HttpURLConnection conn = null;
//                try {
//                    conn = (HttpURLConnection) new URL(strings[0]).openConnection();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                conn.setInstanceFollowRedirects(false);
//                conn.setConnectTimeout(5000);
//                String url = conn.getHeaderField("Location");
//                conn.disconnect();
//                return url;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                onSuccessListener.doLogic(s);
//
//            }
//        }.execute(url);
//    }
//
//    private interface OnSuccessListener {
//        void doLogic(String s);
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
