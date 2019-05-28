package com.example.type_detail;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mvp.BaseActivity;
import com.example.type_detail.adapter.TypeDetailLstAdapter;
import com.example.type_detail.adapter.TypeDetailWaterfallAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.RvItemDecoration;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import butterknife.BindView;

public class TypeDetailActivity extends BaseActivity<TypeDetailView, TypeDetailPresenter> implements TypeDetailView {
    @BindView(R2.id.type_detail_synthesize)
    RelativeLayout mSynthesize;
    @BindView(R2.id.type_detail_sales_volume)
    RelativeLayout mSalesVolume;
    @BindView(R2.id.type_detail_price)
    RelativeLayout mPrice;
    @BindView(R2.id.type_detail_credit)
    RelativeLayout mCredit;
    @BindView(R2.id.type_detail_switchover)
    ImageView mChange;
    @BindView(R2.id.type_detail_rv)
    RecyclerView mRv;
    @BindView(R2.id.type_detail_text1)
    TextView typeDetailText1;
    @BindView(R2.id.type_detail_synthesize_bottom)
    ImageView typeDetailSynthesizeBottom;
    @BindView(R2.id.type_detail_text2)
    TextView typeDetailText2;
    @BindView(R2.id.type_detail_sales_volume_top)
    ImageView typeDetailSalesVolumeTop;
    @BindView(R2.id.type_detail_sales_volume_bottom)
    ImageView typeDetailSalesVolumeBottom;
    @BindView(R2.id.type_detail_text3)
    TextView typeDetailText3;
    @BindView(R2.id.type_detail_price_top)
    ImageView typeDetailPriceTop;
    @BindView(R2.id.type_detail_price_bottom)
    ImageView typeDetailPriceBottom;
    @BindView(R2.id.type_detail_text4)
    TextView typeDetailText4;
    @BindView(R2.id.type_detail_credit_top)
    ImageView typeDetailCreditTop;
    @BindView(R2.id.type_detail_credit_bottom)
    ImageView typeDetailCreditBottom;

    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private RvItemDecoration itemDecoration;
    private int index = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_type_detail;
    }

    @Override
    public void initData() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        itemDecoration = new RvItemDecoration((int) getResources().getDimension(R.dimen.dp_13), (int) getResources().getDimension(R.dimen.dp_10));
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
    public void loadLstRv(TypeDetailLstAdapter adapter) {
        mChange.setImageResource(R.drawable.xfxfgvx);
        mRv.setLayoutManager(linearLayoutManager);
        mRv.removeItemDecoration(itemDecoration);
        mRv.setAdapter(adapter);
    }

    @Override
    public void loadWaterfallRv(TypeDetailWaterfallAdapter adapter) {
        mChange.setImageResource(R.drawable.fghfghfg);
        mRv.setLayoutManager(staggeredGridLayoutManager);
        mRv.addItemDecoration(itemDecoration);
        mRv.setAdapter(adapter);
    }

    @Override
    public void updateTitle(boolean salesVolume, boolean price, boolean credit) {
        typeDetailText1.setTextColor(Color.parseColor(index == 0 ? "#fd3c15" : "#333333"));
        typeDetailSynthesizeBottom.setImageResource(index == 0 ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk);

        typeDetailText2.setTextColor(Color.parseColor(index == 1 ? "#fd3c15" : "#333333"));
        typeDetailSalesVolumeTop.setImageResource(index == 1 ? salesVolume ? R.drawable.gvhgh : R.drawable.ghfgh : R.drawable.ghfgh);
        typeDetailSalesVolumeBottom.setImageResource(index == 1 ? salesVolume ? R.drawable.khjkjhgjk : R.drawable.cgbhdfg : R.drawable.khjkjhgjk);

        typeDetailText3.setTextColor(Color.parseColor(index == 2 ? "#fd3c15" : "#333333"));
        typeDetailPriceTop.setImageResource(index == 2 ? price ? R.drawable.ghfgh : R.drawable.gvhgh : R.drawable.ghfgh);
        typeDetailPriceBottom.setImageResource(index == 2 ? price ? R.drawable.cgbhdfg : R.drawable.khjkjhgjk : R.drawable.khjkjhgjk);

        typeDetailText4.setTextColor(Color.parseColor(index == 3 ? "#fd3c15" : "#333333"));
        typeDetailCreditTop.setImageResource(index == 3 ? credit ? R.drawable.gvhgh : R.drawable.ghfgh : R.drawable.ghfgh);
        typeDetailCreditBottom.setImageResource(index == 3 ? credit ? R.drawable.khjkjhgjk : R.drawable.cgbhdfg : R.drawable.khjkjhgjk);
    }

    @Override
    public TypeDetailView createView() {
        return this;
    }

    @Override
    public TypeDetailPresenter createPresenter() {
        return new TypeDetailPresenter(this);
    }
}
