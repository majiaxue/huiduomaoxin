package com.example.user_home;

import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.entity.EventBusBean;
import com.example.entity.EventBusBean2;
import com.example.mvp.BaseFragment;
import com.example.user_home.adapter.CommendAdapter;
import com.example.user_home.adapter.NavBarAdapter;
import com.example.user_home.adapter.SaleHotAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.RvItemDecoration;
import com.example.view.CustomHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:商城首页
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {
    @BindView(R2.id.user_home_back)
    ImageView userHomeBack;
    @BindView(R2.id.user_home_top_img)
    ImageView userHomeTopImg;
    @BindView(R2.id.user_home_search)
    TextView userHomeSearch;
    @BindView(R2.id.user_home_msg_img)
    ImageView userHomeMsgImg;
    @BindView(R2.id.user_home_msg)
    LinearLayout userHomeMsg;
    @BindView(R2.id.user_home_xbanner)
    XBanner userHomeXbanner;
    @BindView(R2.id.user_home_rv_navbar)
    RecyclerView userHomeRvNavbar;
    @BindView(R2.id.user_home_more)
    LinearLayout userHomeMore;
    @BindView(R2.id.user_home_rv_hot)
    RecyclerView userHomeRvHot;
    @BindView(R2.id.user_home_rv_goods)
    RecyclerView userHomeRvGoods;
    @BindView(R2.id.user_home_refresh)
    SmartRefreshLayout userHomeRefresh;
    @BindView(R2.id.user_home_nescroll)
    NestedScrollView userHomeNescroll;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_home;
    }

    @Override
    public void initData() {
        //导航栏
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 5);
        userHomeRvNavbar.setLayoutManager(gridLayoutManager);
        //热销产品
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        userHomeRvHot.setLayoutManager(linearLayoutManager);
        //新品推荐
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        userHomeRvGoods.setLayoutManager(staggeredGridLayoutManager);
        userHomeRvGoods.addItemDecoration(new RvItemDecoration((int) getContext().getResources().getDimension(R.dimen.dp_12), (int) getContext().getResources().getDimension(R.dimen.dp_12)));
        presenter.loadData();
        presenter.setXBanner(userHomeXbanner, userHomeTopImg);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        userHomeRefresh.setRefreshHeader(customHeader);
        //设置上拉刷新下拉加载
        userHomeRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                userHomeRefresh.finishRefresh();
            }
        });
        userHomeRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                userHomeRefresh.finishLoadMore();
            }
        });
    }

    @Override
    public void initClick() {
        userHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean2("user_back", 0));
            }
        });
    }

    @Override
    public void loadNavBar(NavBarAdapter adapter) {
        userHomeRvNavbar.setAdapter(adapter);
    }

    @Override
    public void loadSaleHot(SaleHotAdapter adapter) {
        userHomeRvHot.setAdapter(adapter);
    }

    @Override
    public void loadCommend(CommendAdapter adapter) {
        userHomeRvGoods.setAdapter(adapter);
    }

    @Override
    public HomeView createView() {
        return this;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getContext());
    }
}
