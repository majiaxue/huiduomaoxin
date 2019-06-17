package com.example.classificationdetails;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.BaseRecAdapter;
import com.example.adapter.SecondaryJDRecAdapter;
import com.example.adapter.SecondaryPddRecAdapter;
import com.example.classificationdetails.adapter.ClassificationRecAdapter;
import com.example.classificationdetails.adapter.JdWaterfallAdapter;
import com.example.classificationdetails.adapter.PddWaterAdapter;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.DisplayUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

/**
 * 分类商品
 */
@Route(path = "/module_classify/ClassificationDetailsActivity")
public class ClassificationDetailsActivity extends BaseActivity<ClassificationDetailsView, ClassificationDetailsPresenter> implements ClassificationDetailsView, View.OnClickListener {

    @BindView(R2.id.classification_back)
    ImageView classificationBack;
    @BindView(R2.id.classification_message)
    LinearLayout classificationMessage;
    @BindView(R2.id.classification_search)
    LinearLayout classificationSearch;
    @BindView(R2.id.synthesize_bottom)
    ImageView synthesizeBottom;
    @BindView(R2.id.sales_volume_top)
    ImageView salesVolumeTop;
    @BindView(R2.id.sales_volume_bottom)
    ImageView salesVolumeBottom;
    @BindView(R2.id.classification_sales_volume)
    RelativeLayout classificationSalesVolume;
    @BindView(R2.id.price_top)
    ImageView priceTop;
    @BindView(R2.id.price_bottom)
    ImageView priceBottom;
    @BindView(R2.id.classification_price)
    RelativeLayout classificationPrice;
    @BindView(R2.id.credit_top)
    ImageView creditTop;
    @BindView(R2.id.credit_bottom)
    ImageView creditBottom;
    @BindView(R2.id.classification_credit)
    RelativeLayout classificationCredit;
    @BindView(R2.id.classification_synthesize)
    RelativeLayout classificationSynthesize;
    @BindView(R2.id.classification_switchover)
    ImageView classificationSwitchover;
    @BindView(R2.id.classification_rec)
    RecyclerView classificationRec;
    @BindView(R2.id.classification_text)
    TextView classificationText;
    @BindView(R2.id.classification_tab)
    TabLayout classificationTab;
    @BindView(R2.id.classification_refresh)
    SmartRefreshLayout mRefresh;

    private boolean salesvolume = true;
    private boolean price = true;
    private boolean credit = true;
    @Autowired(name = "searchContent")
    String searchContent;
    @Autowired(name = "position")
    int position;

    private int page = 1;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;


    @Override
    public int getLayoutId() {
        return R.layout.activity_classification_details;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        //添加间距
        spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(this, 15), DisplayUtil.dip2px(this, 15));

        //初始化tablayout
        presenter.initTabLayout(classificationTab);
        if (searchContent != null && !"" .equals(searchContent)) {
            classificationText.setText(searchContent);
        }
        classificationTab.getTabAt(position).select();
        if (position == 0) presenter.searchTB(searchContent, page, position);
        else if (position == 1) {
            presenter.searchPDD(searchContent, page);
        } else if (position == 2) {
            presenter.searchJD(searchContent, page);
        }

        //设置 Header 为 官方主题 样式
        mRefresh.setRefreshHeader(new MaterialHeader(this));
        //设置 Footer 为 默认 样式
        mRefresh.setRefreshFooter(new ClassicsFooter(this));
    }

    @Override
    public void initClick() {
        classificationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        classificationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ClassificationDetailsActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
                //到搜索页面
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });

        classificationSwitchover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.ChangeShow(position);
            }
        });

        classificationSalesVolume.setOnClickListener(this);
        classificationPrice.setOnClickListener(this);
        classificationCredit.setOnClickListener(this);

        classificationTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        position = 0;
                        page = 1;
                        presenter.searchTB(searchContent, page, 0);
                        break;
                    case 1:
                        position = 1;
                        page = 1;
                        presenter.searchPDD(searchContent, page);
                        break;
                    case 2:
                        position = 2;
                        page = 1;
                        presenter.searchJD(searchContent, page);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //********************设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                if (position == 0) presenter.searchTB(searchContent, page, position);
                else if (position == 1) {
                    presenter.searchPDD(searchContent, page);
                } else if (position == 2) {
                    presenter.searchJD(searchContent, page);
                }
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                if (position == 0) presenter.searchTB(searchContent, page, position);
                else if (position == 1) {
                    presenter.searchPDD(searchContent, page);
                } else if (position == 2) {
                    presenter.searchJD(searchContent, page);
                }
            }
        });
    }

    @Override
    public ClassificationDetailsView createView() {
        return this;
    }

    @Override
    public ClassificationDetailsPresenter createPresenter() {
        return new ClassificationDetailsPresenter(this);
    }

    @Override
    public void loadFinish() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Override
    public void loadTBLstRv(BaseRecAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.xfxfgvx);
        classificationRec.setLayoutManager(linearLayoutManager);
        classificationRec.removeItemDecoration(spaceItemDecorationLeftAndRight);
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadTBWaterfallRv(ClassificationRecAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.fghfghfg);
        classificationRec.setLayoutManager(gridLayoutManager);
        classificationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadPDDLstRv(SecondaryPddRecAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.xfxfgvx);
        classificationRec.setLayoutManager(linearLayoutManager);
        classificationRec.removeItemDecoration(spaceItemDecorationLeftAndRight);
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadPDDWaterfallRv(PddWaterAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.fghfghfg);
        classificationRec.setLayoutManager(gridLayoutManager);
        classificationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadJDLstRv(SecondaryJDRecAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.xfxfgvx);
        classificationRec.setLayoutManager(linearLayoutManager);
        classificationRec.removeItemDecoration(spaceItemDecorationLeftAndRight);
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void loadJDWaterfallRv(JdWaterfallAdapter adapter) {
        classificationSwitchover.setImageResource(R.drawable.fghfghfg);
        classificationRec.setLayoutManager(gridLayoutManager);
        classificationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        classificationRec.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.classification_sales_volume) {
            //销量
            if (salesvolume) {

                salesVolumeTop.setImageResource(R.drawable.gvhgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                salesvolume = false;
            } else {
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.cgbhdfg);
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                salesvolume = true;
            }
        } else if (v.getId() == R.id.classification_price) {
            //价格
            if (price) {
                priceTop.setImageResource(R.drawable.gvhgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                price = false;
            } else {
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.cgbhdfg);
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                price = true;
            }

        } else if (v.getId() == R.id.classification_credit) {
            //信用
            if (credit) {
                creditTop.setImageResource(R.drawable.gvhgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                credit = false;
            } else {
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.cgbhdfg);
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                credit = true;
            }
        }
    }
}
