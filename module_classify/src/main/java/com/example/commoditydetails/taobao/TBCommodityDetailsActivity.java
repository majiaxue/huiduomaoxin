package com.example.commoditydetails.taobao;

import android.net.Uri;
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
import com.example.commoditydetails.taobao.bean.TBBean;
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

    @Autowired(name = "para")
    String para;
    @Autowired(name = "shoptype")
    String shopType;
    private List<TBBean.DataBean> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        AppManager.getInstance().addGoodsActivity(this);
        LogUtil.e("123456              "+para+"        "+shopType);
        //初始化
        presenter.initView(para, shopType);

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
                Toast.makeText(TBCommodityDetailsActivity.this, "暂时不能分享", Toast.LENGTH_SHORT).show();
            }
        });
        //领劵
        commodityLedSecurities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(CommodityDetailsActivity.this, "点击了领劵", Toast.LENGTH_SHORT).show();
                presenter.ledSecurities(para);
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
    public void tbBeanList(List<TBBean.DataBean> tbBeanList, String earnings) {
        this.list = tbBeanList;
        double earnings1 = ArithUtil.div(Double.valueOf(earnings), 100, 2);

        commodityName.setText(tbBeanList.get(0).getTitle());//名字
        commodityPreferentialPrice.setText("￥" + tbBeanList.get(0).getZk_final_price());//优惠价
        commodityOriginalPrice.setText("原价：￥" + tbBeanList.get(0).getReserve_price());//原价
//        commodityEarnings.setText("预估收益：￥" + earnings);//收益
        commodityNumberSold.setText("已售" + tbBeanList.get(0).getSellCount() + "件");//已售

        commodityShopName.setText(tbBeanList.get(0).getSeller().getShopName());//商家名
        commodityShopImage.setImageURI(Uri.parse("https:" + tbBeanList.get(0).getSeller().getShopIcon()));//商家icon
//        LogUtil.e("icon---->" + tbBeanList.get(0).getSeller().getShopIcon());
        shopDescribeScore.setText("" + tbBeanList.get(0).getSeller().getEvaluates().get(0).getScore());
        shopServiceScore.setText("" + tbBeanList.get(0).getSeller().getEvaluates().get(1).getScore());
        shopLogisticsScore.setText("" + tbBeanList.get(0).getSeller().getEvaluates().get(2).getScore());

        //详情轮播图
        List<String> images = tbBeanList.get(0).getImages();
        presenter.setXBanner(commodityXbanner, images);

        //商品详情图片
        List<String> itemDetail = tbBeanList.get(0).getItem_detail();
        presenter.setShopParticulars(shopParticulars, itemDetail);

        //收藏状态
        presenter.isCollect(commodityCollectImage, para);

    }

}
