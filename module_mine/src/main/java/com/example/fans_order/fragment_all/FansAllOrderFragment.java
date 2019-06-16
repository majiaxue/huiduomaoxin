package com.example.fans_order.fragment_all;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.fans_order.adapter.FansOrderRvAdapter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;
import com.example.order.adapter.RvListAdapter;
import com.example.utils.SpaceItemDecoration;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;

public class FansAllOrderFragment extends BaseFragment<FansAllOrderView, FansAllOrderPresenter> implements FansAllOrderView {
    @BindView(R2.id.fans_order_list_rv)
    RecyclerView orderListRv;
    @BindView(R2.id.fans_order_list_refresh)
    SmartRefreshLayout orderListRefresh;

    private static FansAllOrderFragment fragment;
    private int page = 1;

    public static FansAllOrderFragment getInstance() {
        if (fragment == null) {
            synchronized (FansAllOrderFragment.class) {
                if (fragment == null) {
                    fragment = new FansAllOrderFragment();
                }
            }
        }
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_fans_order_list;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        orderListRv.setLayoutManager(layoutManager);
        orderListRv.addItemDecoration(new SpaceItemDecoration(0, 0, 0, (int) getContext().getResources().getDimension(R.dimen.dp_10)));
        presenter.loadData(page);

        //设置 Header 为 官方主题 样式
        orderListRefresh.setRefreshHeader(new MaterialHeader(getActivity()));
        //设置 Footer 为 默认 样式
        orderListRefresh.setRefreshFooter(new ClassicsFooter(getActivity()));

    }

    public void setOrigin(String origin) {

    }

    @Override
    public void initClick() {
        //********************设置上拉刷新下拉加载
        orderListRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                presenter.loadData(page);
            }
        });
        orderListRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                presenter.loadData(page);
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {

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
    public void loadSuccess() {
        orderListRefresh.finishRefresh();
        orderListRefresh.finishLoadMore();
    }

    @Override
    public FansAllOrderView createView() {
        return this;
    }

    @Override
    public FansAllOrderPresenter createPresenter() {
        return new FansAllOrderPresenter(getContext());
    }
}
