package com.example.community.good_goods;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.community.adapter.CommendTitleAdapter;
import com.example.community.adapter.GoodsCommendAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SpaceItemDecoration;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class GoodGoodsFragment extends BaseFragment<GoodGoodsView, GoodGoodsPresneter> implements GoodGoodsView {
    @BindView(R2.id.goods_commend_title)
    RecyclerView goodsCommendTitle;
    @BindView(R2.id.goods_commend_rv)
    RecyclerView goodsCommendRv;
    @BindView(R2.id.goods_commend_refresh)
    SmartRefreshLayout mRefresh;

    private LinearLayoutManager verManager;
    private GridLayoutManager gridLayoutManager;

    private int page = 1;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods_commend;
    }

    @Override
    public void initData() {
        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        goodsCommendTitle.setLayoutManager(gridLayoutManager);
        goodsCommendTitle.addItemDecoration(new SpaceItemDecoration((int) getContext().getResources().getDimension(R.dimen.dp_11), (int) getContext().getResources().getDimension(R.dimen.dp_11), 0, 0));

        verManager = new LinearLayoutManager(getContext());
        verManager.setOrientation(LinearLayoutManager.VERTICAL);
        goodsCommendRv.setLayoutManager(verManager);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        mRefresh.setRefreshHeader(customHeader);

        presenter.initTitle();
        presenter.loadData(page);
    }

    @Override
    public void initClick() {
        //设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(page);
            }
        });
    }

    @Override
    public void loadTitle(CommendTitleAdapter adapter) {
        goodsCommendTitle.setAdapter(adapter);
    }

    @Override
    public void loadContent(GoodsCommendAdapter adapter) {
        goodsCommendRv.setAdapter(adapter);
    }

    @Override
    public void loadFinish() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Override
    public GoodGoodsView createView() {
        return this;
    }

    @Override
    public GoodGoodsPresneter createPresenter() {
        return new GoodGoodsPresneter(getContext());
    }
}
