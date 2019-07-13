package com.example.local_shop;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.BannerBean;
import com.example.local_list.LocalListActivity;
import com.example.local_shop.adapter.LocalNavbarAdapter;
import com.example.local_shop.adapter.LocalSellerAdapter;
import com.example.mvp.BaseFragment;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;

public class LocalShopFragment extends BaseFragment<LocalShopView, LocalShopPresenter> implements LocalShopView {
    @BindView(R2.id.local_shop_city)
    TextView localShopCity;
    @BindView(R2.id.local_shop_choose_city)
    LinearLayout localShopChooseCity;
    @BindView(R2.id.local_shop_search)
    TextView localShopSearch;
    @BindView(R2.id.local_shop_order)
    ImageView localShopOrder;
    @BindView(R2.id.local_shop_xbanner)
    XBanner localShopXbanner;
    @BindView(R2.id.local_shop_navbar)
    RecyclerView localShopNavbar;
    @BindView(R2.id.local_shop_pinzhi)
    ImageView localShopPinzhi;
    @BindView(R2.id.local_shop_xinxuan)
    ImageView localShopXinxuan;
    @BindView(R2.id.local_shop_text1)
    TextView localShopText1;
    @BindView(R2.id.local_shop_synthesize_bottom)
    ImageView localShopSynthesizeBottom;
    @BindView(R2.id.local_shop_synthesize)
    RelativeLayout localShopSynthesize;
    @BindView(R2.id.local_shop_text2)
    TextView localShopText2;
    @BindView(R2.id.local_shop_distance_top)
    ImageView localShopDistanceTop;
    @BindView(R2.id.local_shop_distance_bottom)
    ImageView localShopDistanceBottom;
    @BindView(R2.id.local_shop_distance)
    RelativeLayout localShopDistance;
    @BindView(R2.id.local_shop_text3)
    TextView localShopText3;
    @BindView(R2.id.local_shop_score_top)
    ImageView localShopScoreTop;
    @BindView(R2.id.local_shop_score_bottom)
    ImageView localShopScoreBottom;
    @BindView(R2.id.local_shop_score)
    RelativeLayout localShopScore;
    @BindView(R2.id.local_shop_rv_shop)
    RecyclerView localShopRvShop;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_shop;
    }

    @Override
    public void initData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        localShopNavbar.setLayoutManager(layoutManager);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        localShopRvShop.setLayoutManager(linearLayoutManager);

        presenter.initNavbar();
        presenter.initSeller();
        presenter.getXBanner();
    }

    @Override
    public void initClick() {
        localShopPinzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LocalListActivity.class));
            }
        });

        localShopXinxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), LocalListActivity.class));
            }
        });
    }

    @Override
    public void loadBanner(List<BannerBean.RecordsBean> beanList) {
        localShopXbanner.setBannerData(beanList);

        localShopXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                RequestOptions requestOptions = RequestOptions.centerCropTransform();
                Glide.with(getContext()).load(((BannerBean.RecordsBean) model).getXBannerUrl()).apply(requestOptions).transform(new RoundedCorners((int) getContext().getResources().getDimension(R.dimen.dp_10))).into((ImageView) view);
            }
        });
    }

    @Override
    public void loadNavbar(LocalNavbarAdapter adapter) {
        localShopNavbar.setAdapter(adapter);
        presenter.initClick();
    }

    @Override
    public void loadSeller(LocalSellerAdapter adapter) {
        localShopRvShop.setAdapter(adapter);
    }

    @Override
    public LocalShopView createView() {
        return this;
    }

    @Override
    public LocalShopPresenter createPresenter() {
        return new LocalShopPresenter(getContext());
    }
}
