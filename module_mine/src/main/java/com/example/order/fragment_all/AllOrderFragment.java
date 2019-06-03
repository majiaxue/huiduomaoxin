package com.example.order.fragment_all;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.order.adapter.RvListAdapter;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class AllOrderFragment extends BaseFragment<AllOrderView, AllOrderPresenter> implements AllOrderView {
    @BindView(R2.id.order_list_rv)
    RecyclerView orderListRv;
    @BindView(R2.id.order_list_refresh)
    SmartRefreshLayout orderListRefresh;

    private static AllOrderFragment fragment;
    private static String allFlag;
    private int page = 1;

    public static AllOrderFragment getInstance(String type) {
        allFlag = type;
        if (fragment == null) {
            synchronized (AllOrderFragment.class) {
                if (fragment == null) {
                    fragment = new AllOrderFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderListRv.setLayoutManager(layoutManager);
        presenter.loadData(allFlag);

        //设置 Header 为 官方主题 样式
        orderListRefresh.setRefreshHeader(new MaterialHeader(getActivity()));
        //设置 Footer 为 默认 样式
        orderListRefresh.setRefreshFooter(new ClassicsFooter(getActivity()));

        //********************设置上拉刷新下拉加载
        orderListRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                orderListRefresh.finishRefresh();
            }
        });
        orderListRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;

                orderListRefresh.finishLoadMore();
            }
        });
    }

    @Override
    public void initClick() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            page = 1;
        }
    }

    @Override
    public void loadMineRv(RvListAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public void loadFansRv(FansOrderRvAdapter adapter) {
        orderListRv.setAdapter(adapter);
    }

    @Override
    public AllOrderView createView() {
        return this;
    }

    @Override
    public AllOrderPresenter createPresenter() {
        return new AllOrderPresenter(getContext());
    }
}
