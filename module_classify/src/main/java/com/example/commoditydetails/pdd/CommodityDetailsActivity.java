package com.example.commoditydetails.pdd;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commoditydetails.pdd.bean.CommodityDetailsBean;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.AppManager;
import com.example.utils.ArithUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.MyTimeUtil;
import com.example.utils.QRCode;
import com.example.utils.ViewToBitmap;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.example.utils.ViewToBitmap.GetImageInputStream;

/**
 * 拼多多商品详情
 */
@Route(path = "/module_classify/CommodityDetailsActivity")
public class CommodityDetailsActivity extends BaseActivity<CommodityDetailsView, CommodityDetailsPresenter> implements CommodityDetailsView, NestedScrollView.OnScrollChangeListener {

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
    @BindView(R2.id.commodity_bitmap)
    ImageView commodityBitmap;
    @BindView(R2.id.commodity_share_all)
    LinearLayout commodityShareAll;


    @Autowired(name = "goods_id")
    String goods_id;

    @Autowired(name = "type")
    String type;

    @BindView(R2.id.share_image)
    ImageView shareImage;
    @BindView(R2.id.share_name)
    TextView shareName;
    @BindView(R2.id.share_preferential_price)
    TextView sharePreferentialPrice;
    @BindView(R2.id.share_original_price)
    TextView shareOriginalPrice;
    @BindView(R2.id.share_coupon_price)
    TextView shareCouponPrice;
    @BindView(R2.id.share_number)
    TextView shareNumber;
    @BindView(R2.id.share_qr_code)
    ImageView shareQrCode;

    private List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> detailsBeanList = new ArrayList<>();
    private String imageUrl;
    private int flag = 0;
    private String earnings;
    private double earnings1;
    private double mul1;
    private double mul;
    private double div;
    private double promotionRate;
    private Bitmap bitmap;
    private Bitmap saveBitmap;
    private File file;


    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        AppManager.getInstance().addGoodsActivity(this);
        LogUtil.e("goods_id" + goods_id + type);
        commodityIntoShop.setVisibility(View.INVISIBLE);
        //加载视图
        presenter.initView(goods_id);

        //保存浏览记录
        presenter.historySave(goods_id);
        //收益
        presenter.earnings();
        //推荐recycler
        presenter.setRecommendRec(shopRecommendRec);
        //字体加中划线
        commodityOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
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
                AppManager.getInstance().finishGoodsActivity();
            }
        });
//        //进入店铺
//        commodityIntoShop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance().build("/module_classify/IntoShopActivity").navigation();
//            }
//        });
//        //进入店铺
//        commodityShopImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance().build("/module_classify/IntoShopActivity").navigation();
//            }
//        });
//        //进入店铺
//        commodityShopName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ARouter.getInstance().build("/module_classify/IntoShopActivity").navigation();
//            }
//        });
        //分享
        //分享
        commodityShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommodityDetailsActivity.this, "暂时不能分享", Toast.LENGTH_SHORT).show();
                presenter.share();
            }
        });
        //立即领取
        commodityImmediatelyReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToWeb(imageUrl);
            }
        });
        //领劵
        commodityLedSecurities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommodityDetailsActivity.this, "点击了领劵", Toast.LENGTH_SHORT).show();
                presenter.jumpToWeb(imageUrl);
            }
        });
        //收藏
        commodityCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommodityDetailsActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                presenter.goodsCollect(commodityCollectImage, detailsBeanList);
            }
        });
    }

    @Override
    public CommodityDetailsView createView() {
        return this;
    }

    @Override
    public CommodityDetailsPresenter createPresenter() {
        return new CommodityDetailsPresenter(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("ShopHomeFragment", "不可见");
        commodityXbanner.stopAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ShopHomeFragment", "可见");
        commodityXbanner.startAutoPlay();
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
    public void CommodityDetailsList(List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        this.detailsBeanList = beanList;
//        LogUtil.e("收益1-------->" + earnings);
        new Task().execute(detailsBeanList.get(0).getGoods_gallery_urls().get(0));
        //到手价
        div = ArithUtil.div(beanList.get(0).getMin_group_price() - beanList.get(0).getCoupon_discount(), 100, 1);
        //佣金比例
        promotionRate = ArithUtil.div(beanList.get(0).getPromotion_rate(), 1000, 1);


        commodityName.setText(beanList.get(0).getGoods_name());//名字
        commodityPreferentialPrice.setText("￥" + div);//优惠价
        commodityOriginalPrice.setText("原价：￥" + ArithUtil.div(beanList.get(0).getMin_group_price(), 100, 1));//原价
        commodityNumberSold.setText("已售" + beanList.get(0).getSold_quantity() + "件");//已售
        commodityCouponPrice.setText(ArithUtil.sub(ArithUtil.div(beanList.get(0).getMin_group_price(), 100, 1), div) + "元优惠劵");

        String startTime = MyTimeUtil.date2String(beanList.get(0).getCoupon_start_time() * 1000 + "");
        String endTime = MyTimeUtil.date2String(beanList.get(0).getCoupon_end_time() * 1000 + "");
        commodityTime.setText("有效期限：" + startTime + "~" + endTime);
        commodityShopName.setText(beanList.get(0).getMall_name());
        shopDescribeScore.setText("" + ArithUtil.div(beanList.get(0).getAvg_desc(), 100, 1));
        shopServiceScore.setText("" + ArithUtil.div(beanList.get(0).getAvg_serv(), 100, 1));
        shopLogisticsScore.setText("" + ArithUtil.div(beanList.get(0).getAvg_lgst(), 100, 1));
        //详情轮播图
        presenter.setXBanner(commodityXbanner, beanList);
        //商品详情图片
        presenter.setShopParticulars(shopParticulars, beanList);
        //收藏状态
        presenter.isCollect(commodityCollectImage, beanList);
        //领劵
        presenter.ledSecurities(detailsBeanList.get(0).getGoods_id());
    }

    //加载生成图片布局
    private void viewToImage(String qRImage) {
        //字体加中划线
        shareOriginalPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); // 设置中划线并加清晰
        double div = ArithUtil.div(detailsBeanList.get(0).getMin_group_price() - detailsBeanList.get(0).getCoupon_discount(), 100, 1);//到手价
        LogUtil.e("url主图---------->" + detailsBeanList.get(0).getGoods_gallery_urls().get(0));
        String s = detailsBeanList.get(0).getGoods_gallery_urls().get(0);
        /*try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(Uri.parse(s)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/
        //商品图片
        LogUtil.e("url1轮播图---------->" + s);

        shareName.setText(detailsBeanList.get(0).getGoods_name());
        //商品优惠价
        sharePreferentialPrice.setText("￥" + div);
        //商品原价
        shareOriginalPrice.setText("￥" + ArithUtil.div(detailsBeanList.get(0).getMin_group_price(), 100, 1));
        //商品优惠劵
        shareCouponPrice.setText("￥" + ArithUtil.sub(ArithUtil.div(detailsBeanList.get(0).getMin_group_price(), 100, 1), div) + "元");
        //商品已售件数
        shareNumber.setText("已售" + detailsBeanList.get(0).getSold_quantity() + "件");//已售
        //得到链接生成二维码
        Bitmap qr = QRCode.createQRImage(qRImage, DisplayUtil.dip2px(this, 150), DisplayUtil.dip2px(this, 150));
        shareQrCode.setImageBitmap(qr);
        LogUtil.e("url2二维码---------->" + qRImage);

        //获得view生成一张bitmap
        this.bitmap = ViewToBitmap.createBitmap3(commodityShareAll, ViewToBitmap.getScreenWidth(this), ViewToBitmap.getScreenHeight(this));

//        commodityShareAll.setVisibility(View.GONE);
    }

    @Override
    public void imageUri(String url) {
        this.imageUrl = url;
        //viewToImage
//        presenter.viewToImage(url);
        //viewToImage(url);
    }

    @Override
    public void imageBitmap(Bitmap bitmap) {
//        commodityBitmap.setImageBitmap(bitmap);
    }

    @Override
    public void flag() {
        flag++;
        if (flag == 2) {
            //到手价乘佣金
            mul = ArithUtil.mul(div, promotionRate);
            //用户佣金比例
            earnings1 = ArithUtil.div(Double.valueOf(earnings), 100, 1);
            //收益
            mul1 = ArithUtil.mul(mul, earnings1);
            commodityEarnings.setText("预估收益：￥" + mul1);//收益
        }
    }

    @Override
    public void earnings(String ear) {
        this.earnings = ear;
    }

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
        file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //shareImage.setImageURI(Uri.fromFile(new File(file.getPath())));
        presenter.viewToImage(imageUrl,file.getPath());
        LogUtil.e("图片路径"+ file.getPath());
    }


    Handler handler=new Handler(){
        public void handleMessage(android.os.Message msg) {
            if(msg.what==0x123){
                saveImageToPhotos(saveBitmap);
            }
        };
    };


    /**
     * 异步线程下载图片
     *
     */
    class Task extends AsyncTask<String, Integer, Void> {

        protected Void doInBackground(String... params) {
            saveBitmap = ViewToBitmap.GetImageInputStream((String)params[0]);
            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Message message=new Message();
            message.what=0x123;
            handler.sendMessage(message);
        }

    }

}
