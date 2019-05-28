package com.example.shop_home.treasure;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvp.BaseFragment;
import com.example.shop_home.adapter.TreasureLstAdapter;
import com.example.shop_home.adapter.TreasureWaterfallAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import butterknife.BindView;

public class ShopTreasureFragment extends BaseFragment<ShopTreasureView, ShopTreasurePresenter> implements ShopTreasureView {
    @BindView(R2.id.shop_treasure_synthesize)
    RelativeLayout mSynthesize;
    @BindView(R2.id.shop_treasure_sales_volume)
    RelativeLayout mSalesVolume;
    @BindView(R2.id.shop_treasure_price)
    RelativeLayout mPrice;
    @BindView(R2.id.shop_treasure_credit)
    RelativeLayout mCredit;
    @BindView(R2.id.shop_treasure_switchover)
    ImageView mChange;
    @BindView(R2.id.shop_treasure_rv)
    RecyclerView mRv;
    @BindView(R2.id.shop_treasure_text1)
    TextView shopTreasureText1;
    @BindView(R2.id.shop_treasure_synthesize_bottom)
    ImageView shopTreasureSynthesizeBottom;
    @BindView(R2.id.shop_treasure_text2)
    TextView shopTreasureText2;
    @BindView(R2.id.shop_treasure_sales_volume_top)
    ImageView shopTreasureSalesVolumeTop;
    @BindView(R2.id.shop_treasure_sales_volume_bottom)
    ImageView shopTreasureSalesVolumeBottom;
    @BindView(R2.id.shop_treasure_text3)
    TextView shopTreasureText3;
    @BindView(R2.id.shop_treasure_price_top)
    ImageView shopTreasurePriceTop;
    @BindView(R2.id.shop_treasure_price_bottom)
    ImageView shopTreasurePriceBottom;
    @BindView(R2.id.shop_treasure_text4)
    TextView shopTreasureText4;
    @BindView(R2.id.shop_treasure_credit_top)
    ImageView shopTreasureCreditTop;
    @BindView(R2.id.shop_treasure_credit_bottom)
    ImageView shopTreasureCreditBottom;

    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private SpaceItemDecorationLeftAndRight itemDecoration;
    private int index = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_treasure;
    }

    @Override
    public void initData() {
        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        itemDecoration = new SpaceItemDecorationLeftAndRight((int) getContext().getResources().getDimension(R.dimen.dp_15), (int) getContext().getResources().getDimension(R.dimen.dp_15));
        presenter.loadData();
    }

    @Override
    public void initClick() {
        mChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ChangeShow();
            }
        });

        mSynthesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 0;
                presenter.changeTyep(index);
            }
        });

        mSalesVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                presenter.changeTyep(index);
            }
        });

        mPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 2;
                presenter.changeTyep(index);
            }
        });

        mCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 3;
                presenter.changeTyep(index);
            }
        });
    }

    @Override
    public void updateTitle(boolean salesVolume, boolean price, boolean credit) {
        shopTreasureText1.setTextColor(Color.parseColor(index == 0 ? "#ffffff" : "#333333"));
        shopTreasureSynthesizeBottom.setImageResource(index == 0 ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk);

        shopTreasureText2.setTextColor(Color.parseColor(index == 1 ? "#ffffff" : "#333333"));
        shopTreasureSalesVolumeTop.setImageResource(index == 1 ? salesVolume ? R.drawable.gvhgh : R.drawable.ghfgh : R.drawable.ghfgh);
        shopTreasureSalesVolumeBottom.setImageResource(index == 1 ? salesVolume ? R.drawable.khjkjhgjk : R.drawable.cgbhdfg : R.drawable.khjkjhgjk);

        shopTreasureText3.setTextColor(Color.parseColor(index == 2 ? "#ffffff" : "#333333"));
        shopTreasurePriceTop.setImageResource(index == 2 ? price ? R.drawable.ghfgh : R.drawable.gvhgh : R.drawable.ghfgh);
        shopTreasurePriceBottom.setImageResource(index == 2 ? price ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk : R.drawable.khjkjhgjk);

        shopTreasureText4.setTextColor(Color.parseColor(index == 3 ? "#ffffff" : "#333333"));
        shopTreasureCreditTop.setImageResource(index == 3 ? credit ? R.drawable.gvhgh : R.drawable.ghfgh : R.drawable.ghfgh);
        shopTreasureCreditBottom.setImageResource(index == 3 ? credit ? R.drawable.khjkjhgjk : R.drawable.cgbhdfg : R.drawable.khjkjhgjk);
    }

    @Override
    public void loadLstRv(TreasureLstAdapter adapter) {
        mChange.setImageResource(R.drawable.xfxfgvx);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.removeItemDecoration(itemDecoration);
        mRv.setAdapter(adapter);
    }

    @Override
    public void loadWaterfallRv(TreasureWaterfallAdapter adapter) {
        mChange.setImageResource(R.drawable.fghfghfg);
        mRv.setLayoutManager(staggeredGridLayoutManager);
        mRv.addItemDecoration(itemDecoration);
        mRv.setAdapter(adapter);
    }

    @Override
    public ShopTreasureView createView() {
        return this;
    }

    @Override
    public ShopTreasurePresenter createPresenter() {
        return new ShopTreasurePresenter(getContext());
    }
}
