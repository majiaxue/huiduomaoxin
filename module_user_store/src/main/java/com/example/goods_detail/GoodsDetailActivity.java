package com.example.goods_detail;

import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.entity.TopBannerBean;
import com.example.goods_detail.adapter.GoodsAssessAdapter;
import com.example.goods_detail.adapter.GoodsCouponAdapter;
import com.example.goods_detail.adapter.GoodsImageAdapter;
import com.example.mvp.BaseActivity;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.RvItemDecoration;
import com.example.utils.SpaceItemDecoration;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;

public class GoodsDetailActivity extends BaseActivity<GoodsDetailView, GoodsDetailPresenter> implements GoodsDetailView, NestedScrollView.OnScrollChangeListener {
    @BindView(R2.id.goods_detail_xbanner)
    XBanner goodsDetailXbanner;
    @BindView(R2.id.goods_detail_name)
    TextView goodsDetailName;
    @BindView(R2.id.goods_detail_price)
    TextView goodsDetailPrice;
    @BindView(R2.id.goods_detail_attention_img)
    ImageView goodsDetailAttentionImg;
    @BindView(R2.id.goods_detail_attention)
    LinearLayout goodsDetailAttention;
    @BindView(R2.id.goods_detail_rv_coupon)
    RecyclerView goodsDetailRvCoupon;
    @BindView(R2.id.goods_detail_lingquan)
    RelativeLayout goodsDetailLingquan;
    @BindView(R2.id.goods_detail_ensure)
    LinearLayout goodsDetailEnsure;
    @BindView(R2.id.goods_detail_choose_goods)
    RelativeLayout goodsDetailChooseGoods;
    @BindView(R2.id.goods_detail_parms)
    RelativeLayout goodsDetailParms;
    @BindView(R2.id.goods_detail_title_assess)
    TextView goodsDetailTitleAssess;
    @BindView(R2.id.goods_detail_assess_count)
    TextView goodsDetailAssessCount;
    @BindView(R2.id.goods_detail_see_all)
    LinearLayout goodsDetailSeeAll;
    @BindView(R2.id.goods_detail_rv_assess)
    RecyclerView goodsDetailRvAssess;
    @BindView(R2.id.goods_detail_shop_img)
    ImageView goodsDetailShopImg;
    @BindView(R2.id.goods_detail_shop_name)
    TextView goodsDetailShopName;
    @BindView(R2.id.goods_detail_shop_call)
    TextView goodsDetailShopCall;
    @BindView(R2.id.goods_detail_shop_attention)
    TextView goodsDetailShopAttention;
    @BindView(R2.id.goods_detail_shop_goin)
    TextView goodsDetailShopGoin;
    @BindView(R2.id.goods_detail_title_detail)
    TextView goodsDetailTitleDetail;
    @BindView(R2.id.goods_detail_title_commend)
    TextView goodsDetailTitleCommend;
    @BindView(R2.id.goods_detail_rv_commend)
    RecyclerView goodsDetailRvCommend;
    @BindView(R2.id.goods_detail_back)
    ImageView goodsDetailBack;
    @BindView(R2.id.goods_detail_share)
    ImageView goodsDetailShare;
    @BindView(R2.id.goods_detail_touming)
    RelativeLayout goodsDetailTouming;
    @BindView(R2.id.goods_detail_back2)
    ImageView goodsDetailBack2;
    @BindView(R2.id.goods_detail_navbar_baby)
    TextView goodsDetailNavbarBaby;
    @BindView(R2.id.goods_detail_navbar_assess)
    TextView goodsDetailNavbarAssess;
    @BindView(R2.id.goods_detail_navbar_detail)
    TextView goodsDetailNavbarDetail;
    @BindView(R2.id.goods_detail_navbar_commend)
    TextView goodsDetailNavbarCommend;
    @BindView(R2.id.goods_detail_navbar)
    RelativeLayout goodsDetailNavbar;
    @BindView(R2.id.goods_detail_bottom_shop)
    LinearLayout goodsDetailBottomShop;
    @BindView(R2.id.goods_detail_bottom_serve)
    LinearLayout goodsDetailBottomServe;
    @BindView(R2.id.goods_detail_bottom_cart)
    LinearLayout goodsDetailBottomCart;
    @BindView(R2.id.goods_detail_add_cart)
    TextView goodsDetailAddCart;
    @BindView(R2.id.goods_detail_buy)
    TextView goodsDetailBuy;
    @BindView(R2.id.goods_detail_rv_img)
    RecyclerView goodsDetailRvImg;
    @BindView(R2.id.goods_detail_scroll)
    NestedScrollView goodsDetailScroll;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public void initData() {
        //优惠券
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        goodsDetailRvCoupon.addItemDecoration(new SpaceItemDecoration((int) getResources().getDimension(R.dimen.dp_12), 0, 0, 0));
        goodsDetailRvCoupon.setLayoutManager(linearLayoutManager);
        //图片
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        goodsDetailRvImg.addItemDecoration(new SpaceItemDecoration(0, (int) getResources().getDimension(R.dimen.dp_15), 0, 0));
        goodsDetailRvImg.setLayoutManager(linearLayoutManager1);
        //评论
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        goodsDetailRvAssess.setLayoutManager(linearLayoutManager2);
        //推荐
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        goodsDetailRvCommend.setLayoutManager(staggeredGridLayoutManager);
        goodsDetailRvCommend.addItemDecoration(new RvItemDecoration((int) getResources().getDimension(R.dimen.dp_12), (int) getResources().getDimension(R.dimen.dp_12)));
        //轮播图
        goodsDetailXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(GoodsDetailActivity.this).load(((TopBannerBean) model).getImgUrl()).into((ImageView) view);
            }
        });

        goodsDetailScroll.setOnScrollChangeListener(this);
        presenter.loadData();
    }

    @Override
    public void initClick() {
        goodsDetailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        goodsDetailBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //关注商品
        goodsDetailAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.toAttention();
            }
        });
        //领优惠券
        goodsDetailLingquan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.lingquan();
            }
        });

        //正品保障
        goodsDetailEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ensure();
            }
        });

        goodsDetailParms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.detailParms();
            }
        });

        goodsDetailChooseGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.chooseGoods();
            }
        });

        goodsDetailSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToAssess();
            }
        });

        goodsDetailShopGoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShop();
            }
        });

        goodsDetailBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder();
            }
        });

        goodsDetailBottomShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToShop();
            }
        });

        goodsDetailBottomCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToCart();
            }
        });
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY <= 300) {
            if (goodsDetailTouming.getVisibility() != View.VISIBLE) {
                goodsDetailTouming.setVisibility(View.VISIBLE);
            }
            goodsDetailTouming.setAlpha(((300 - scrollY) * 1.0f / 300));
        } else if (scrollY == 301) {
            goodsDetailTouming.setVisibility(View.GONE);
        }

        int[] int_baby = new int[2];
        goodsDetailName.getLocationOnScreen(int_baby);
        int y_baby = int_baby[1];
        if (y_baby - 400 <= goodsDetailNavbar.getHeight() && y_baby - goodsDetailNavbar.getHeight() >= 0) {
            if (goodsDetailNavbar.getVisibility() != View.VISIBLE) {
                goodsDetailNavbar.setVisibility(View.VISIBLE);
            }
            goodsDetailNavbar.setAlpha(-(y_baby - goodsDetailNavbar.getHeight() - 400) * 1.0f / 400);
        } else if (y_baby - 400 >= goodsDetailNavbar.getHeight()) {
            goodsDetailNavbar.setVisibility(View.GONE);
        }
        int[] int_assess = new int[2];
        goodsDetailTitleAssess.getLocationOnScreen(int_assess);
        int y_assess = int_assess[1];

        int[] int_detail = new int[2];
        goodsDetailTitleDetail.getLocationOnScreen(int_detail);
        int y_detail = int_detail[1];

        int[] int_commend = new int[2];
        goodsDetailTitleCommend.getLocationOnScreen(int_commend);
        int y_commend = int_commend[1];

        if (y_baby - 400 - goodsDetailNavbar.getHeight() <= 0 && y_assess - goodsDetailNavbar.getHeight() > 0) {
            changeType(0);
        } else if (y_assess - goodsDetailNavbar.getHeight() <= 0 && y_detail - goodsDetailNavbar.getHeight() > 0) {
            changeType(1);
        } else if (y_detail - goodsDetailNavbar.getHeight() <= 0 && y_commend - goodsDetailNavbar.getHeight() > 0) {
            changeType(2);
        } else if (y_commend - goodsDetailNavbar.getHeight() <= 0) {
            changeType(3);
        }
    }

    private void changeType(int position) {
        goodsDetailNavbarBaby.setTextColor(Color.parseColor(position == 0 ? "#fd3c15" : "#333333"));
        goodsDetailNavbarAssess.setTextColor(Color.parseColor(position == 1 ? "#fd3c15" : "#333333"));
        goodsDetailNavbarDetail.setTextColor(Color.parseColor(position == 2 ? "#fd3c15" : "#333333"));
        goodsDetailNavbarCommend.setTextColor(Color.parseColor(position == 3 ? "#fd3c15" : "#333333"));
    }

    @Override
    public void loadCoupon(GoodsCouponAdapter adapter) {
        goodsDetailRvCoupon.setAdapter(adapter);
    }

    @Override
    public void loadImage(GoodsImageAdapter adapter) {
        goodsDetailRvImg.setAdapter(adapter);
    }

    @Override
    public void loadAssess(GoodsAssessAdapter adapter) {
        goodsDetailRvAssess.setAdapter(adapter);
    }

    @Override
    public void loadCommend(CommendAdapter adapter) {
        goodsDetailRvCommend.setAdapter(adapter);
    }

    @Override
    public void loadBanner(List<TopBannerBean> list) {
        goodsDetailXbanner.setBannerData(list);
    }

    @Override
    public void attention() {
        goodsDetailAttentionImg.setImageResource(R.drawable.icon_shoucang2);
    }

    @Override
    public void cancelAttention() {
        goodsDetailAttentionImg.setImageResource(R.drawable.icon_shoucang1);
    }

    @Override
    public GoodsDetailView createView() {
        return this;
    }

    @Override
    public GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter(this);
    }
}
