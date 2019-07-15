package com.example.local_shop;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
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
import com.example.utils.ProcessDialogUtil;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
    @BindView(R2.id.local_shop_refresh)
    SmartRefreshLayout mRefresh;

    private int page = 1;
    private int index = 0;
    private boolean isFirst = true;
    public static final String ASC = "ASC";     //从小到大
    public static final String DESC = "DESC";   //从大到小
    public static final String STAR = "star";   //评分排序
    public static final String DISTANCE = "distance";   //距离排序

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

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        mRefresh.setRefreshHeader(customHeader);
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

        localShopSynthesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                presenter.changeSort(index);
            }
        });

        localShopDistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                presenter.changeSort(index);
            }
        });

        localShopScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 2;
                presenter.changeSort(index);
            }
        });

        //设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(index, page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(index, page);
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && isFirst) {
            ProcessDialogUtil.showProcessDialog(getContext());
            presenter.initNavbar();
            presenter.initSeller("", "", page);
            presenter.getXBanner();
            isFirst = false;
        }
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

        localShopOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder();
            }
        });
    }

    @Override
    public void loadNavbar(LocalNavbarAdapter adapter) {
        localShopNavbar.setAdapter(adapter);
        presenter.initClick();
    }

    @Override
    public void changed(boolean isDistanceJin, boolean isStarMore) {
        localShopText1.setTextColor(Color.parseColor(index == 0 ? "#fd3c15" : "#333333"));
        localShopSynthesizeBottom.setImageResource(index == 0 ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk);
        localShopText2.setTextColor(Color.parseColor(index == 1 ? "#fd3c15" : "#333333"));
        localShopDistanceBottom.setImageResource(index == 1 ? isDistanceJin ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk : R.drawable.khjkjhgjk);
        localShopDistanceTop.setImageResource(index == 1 ? isDistanceJin ? R.drawable.ghfgh : R.drawable.gvhgh : R.drawable.ghfgh);
        localShopText3.setTextColor(Color.parseColor(index == 2 ? "#fd3c15" : "#333333"));
        localShopScoreBottom.setImageResource(index == 2 ? isStarMore ? R.drawable.khjkjhgjk : R.drawable.cgbhdfg : R.drawable.khjkjhgjk);
        localShopScoreTop.setImageResource(index == 2 ? isStarMore ? R.drawable.gvhgh : R.drawable.ghfgh : R.drawable.ghfgh);
    }

    @Override
    public void loadFinish() {
        mRefresh.finishLoadMore();
        mRefresh.finishRefresh();
    }

    @Override
    public void noData() {
        page--;
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
