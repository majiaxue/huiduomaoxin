package com.example.commoditydetails.jd;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.bean.JDGoodsRecBean;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.AppManager;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 京东商品详情
 */
@Route(path = "/module_classify/JDCommodityDetailsActivity")
public class JDCommodityDetailsActivity extends BaseActivity<JDCommodityDetailsView, JDCommodityDetailsPresenter> implements JDCommodityDetailsView, NestedScrollView.OnScrollChangeListener {

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
    @BindView(R2.id.shop_text1)
    TextView shopText1;
    @BindView(R2.id.shop_text2)
    TextView shopText2;
    @BindView(R2.id.shop_text3)
    TextView shopText3;
    @BindView(R2.id.shop_no_goods)
    LinearLayout shopNoGoods;

    @Autowired(name = "skuid")
    String skuid;

    @Autowired(name = "position")
    int position;

    @Autowired(name = "jDGoodsRecBean")
    JDGoodsRecBean jDGoodsRecBean;


    private List<JDGoodsRecBean.DataBean.ListsBean> listsBeanList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        AppManager.getInstance().addGoodsActivity(this);
        listsBeanList.addAll(jDGoodsRecBean.getData().getLists());
        LogUtil.e("京东+++++++++++++" + skuid + "             " + jDGoodsRecBean);

        //收益
        presenter.earnings();

        //详情轮播图
        presenter.setXBanner(commodityXbanner, listsBeanList, position);

        //商品详情图片
        presenter.setShopParticulars(shopParticulars, listsBeanList, position);

        commodityName.setText(listsBeanList.get(position).getSkuName());//名字
        commodityPreferentialPrice.setText("￥" + ArithUtil.sub(Double.valueOf(listsBeanList.get(position).getPriceInfo().getPrice()), Double.valueOf(listsBeanList.get(position).getCouponInfo().getCouponList().get(0).getDiscount())));//优惠价
        commodityOriginalPrice.setText("原价：￥" + Double.valueOf(listsBeanList.get(position).getPriceInfo().getPrice()));//原价
        commodityNumberSold.setText("已售" + listsBeanList.get(0).getInOrderCount30Days() + "件");//已售

        commodityShopName.setText(listsBeanList.get(position).getShopInfo().getShopName());//商家名
//        commodityShopImage.setImageURI(Uri.parse("https:" + tbBeanList.get(0).getSeller().getShopIcon()));//商家icon
//        LogUtil.e("icon---->" + tbBeanList.get(0).getSeller().getShopIcon());
        shopDescribeScore.setText("");
        shopServiceScore.setText("");
        shopLogisticsScore.setText("");
        shopText1.setText("");
        shopText2.setText("");
        shopText3.setText("");

        //收藏状态
        presenter.isCollect(commodityCollectImage, skuid);

        //推荐
        presenter.setRecommendRec(shopRecommendRec, listsBeanList.get(position).getCategoryInfo().getCid1Name());

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
                Toast.makeText(JDCommodityDetailsActivity.this, "暂时不能分享", Toast.LENGTH_SHORT).show();
            }
        });
        //领劵
        commodityLedSecurities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommodityDetailsActivity.this, "点击了领劵", Toast.LENGTH_SHORT).show();
                presenter.ledSecurities(listsBeanList.get(position).getMaterialUrl(), listsBeanList.get(position).getCouponInfo().getCouponList().get(0).getLink());
            }
        });
        //收藏
        commodityCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommodityDetailsActivity.this, "点击了收藏", Toast.LENGTH_SHORT).show();
                presenter.goodsCollect(commodityCollectImage, skuid);
            }
        });
    }

    @Override
    public JDCommodityDetailsView createView() {
        return this;
    }

    @Override
    public JDCommodityDetailsPresenter createPresenter() {
        return new JDCommodityDetailsPresenter(this);
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
    public void earnings(String earnings) {
        Double commission = Double.valueOf(listsBeanList.get(position).getCommissionInfo().getCommission());
        Double aDouble = Double.valueOf(earnings);

        double mul = ArithUtil.mul(commission, ArithUtil.div(aDouble, 100, 2));
        commodityEarnings.setText("预估收益：￥" + mul);//收益
    }

    @Override
    public void isNoGoods(boolean isNoGoods) {
        if (isNoGoods){
            shopNoGoods.setVisibility(View.GONE);
        }else{
            shopNoGoods.setVisibility(View.VISIBLE);
        }
    }
}
