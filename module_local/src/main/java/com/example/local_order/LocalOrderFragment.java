package com.example.local_order;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.local_order.adapter.LocalOrderAdapter;
import com.example.local_order.adapter.LocalOrderNavbarAdapter;
import com.example.module_local.R;
import com.example.module_local.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SpaceItemDecoration;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class LocalOrderFragment extends BaseFragment<LocalOrderView, LocalOrderPresenter> implements LocalOrderView {
    @BindView(R2.id.local_order_rv_navbar)
    RecyclerView localOrderRvNavbar;
    @BindView(R2.id.local_order_rv_list)
    RecyclerView localOrderRvList;
    @BindView(R2.id.local_order_refresh)
    SmartRefreshLayout mRefresh;

    private int page = 1;
    private String status = "";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_local_order;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        localOrderRvNavbar.setLayoutManager(layoutManager);
        localOrderRvNavbar.addItemDecoration(new SpaceItemDecoration((int) getContext().getResources().getDimension(R.dimen.dp_10), (int) getContext().getResources().getDimension(R.dimen.dp_10), 0, 0));

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        localOrderRvList.setLayoutManager(layoutManager1);
        localOrderRvList.addItemDecoration(new SpaceItemDecoration(0, 0, (int) getContext().getResources().getDimension(R.dimen.dp_8), (int) getContext().getResources().getDimension(R.dimen.dp_8)));

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(com.example.user_store.R.color.colorTransparency));
        mRefresh.setRefreshHeader(customHeader);

        presenter.initNavbar();
        presenter.loadData(status, page);
    }

    @Override
    public void initClick() {
        //设置上拉刷新下拉加载
        mRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(status, page);
            }
        });
        mRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(status, page);
            }
        });
    }

    @Override
    public void loadNavbar(LocalOrderNavbarAdapter adapter) {
        localOrderRvNavbar.setAdapter(adapter);
    }

    @Override
    public void changeType(int position) {
        if (position == 0) {
            status = "";
        } else if (position == 4) {
            status = "4";
        } else if (position == 5) {
            status = "5";
        } else {
            status = position - 1 + "";
        }
        page = 1;
        presenter.loadData(status, page);
    }

    @Override
    public void loadFinish() {
        mRefresh.finishRefresh();
        mRefresh.finishLoadMore();
    }

    @Override
    public void loadRv(LocalOrderAdapter adapter) {
        localOrderRvList.setAdapter(adapter);
    }

    @Override
    public LocalOrderView createView() {
        return this;
    }

    @Override
    public LocalOrderPresenter createPresenter() {
        return new LocalOrderPresenter(getContext());
    }
}
