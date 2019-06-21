package com.example.commoditydetails.pdd;


import android.graphics.Paint;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.commoditydetails.pdd.bean.CommodityDetailsBean;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.AppManager;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.MyTimeUtil;
import com.example.view.RVNestedScrollView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    RVNestedScrollView commodityNestedScroll;
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


    @Autowired(name = "goods_id")
    String goods_id;

    @Autowired(name = "type")
    String type;

    private List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> detailsBeanList = new ArrayList<>();


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
        commodityShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CommodityDetailsActivity.this, "暂时不能分享", Toast.LENGTH_SHORT).show();
            }
        });
        //领劵
        commodityLedSecurities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommodityDetailsActivity.this, "点击了领劵", Toast.LENGTH_SHORT).show();
                presenter.ledSecurities(detailsBeanList);
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
    public void CommodityDetailsList(List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList, String earnings) {
        this.detailsBeanList = beanList;
        LogUtil.e("收益1-------->" + earnings);
        double div = ArithUtil.div(beanList.get(0).getMin_group_price() - beanList.get(0).getCoupon_discount(), 100, 1);//到手价
        double promotionRate = ArithUtil.div(beanList.get(0).getPromotion_rate(), 1000, 1);//佣金比例
        double mul = ArithUtil.mul(div, promotionRate);//到手价乘佣金
        double earnings1 = ArithUtil.div(Double.valueOf(earnings), 100, 1);//用户佣金比例
        double mul1 = ArithUtil.mul(mul, earnings1);//收益

        commodityName.setText(beanList.get(0).getGoods_name());//名字
        commodityPreferentialPrice.setText("￥" + div);//优惠价
        commodityOriginalPrice.setText("原价：￥" + ArithUtil.div(beanList.get(0).getMin_group_price(), 100, 1));//原价
        commodityEarnings.setText("预估收益：￥" + mul1);//收益
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
    }

}
