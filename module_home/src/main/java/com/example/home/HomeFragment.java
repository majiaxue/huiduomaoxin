package com.example.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.view.CustomHeader;
import com.example.view.CustomeRecyclerView;
import com.example.view.MarqueeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, NestedScrollView.OnScrollChangeListener {

    @BindView(R2.id.home_top_bg)
    ImageView homeTopBg;
    @BindView(R2.id.home_search)
    LinearLayout homeSearch;
    @BindView(R2.id.home_message)
    LinearLayout homeMessage;
    @BindView(R2.id.home_xbanner)
    XBanner homeXbanner;
    @BindView(R2.id.home_marquee)
    MarqueeView homeMarquee;
    @BindView(R2.id.home_see_more_top)
    TextView homeSeeMoreTop;
    @BindView(R2.id.home_top_rec)
    CustomeRecyclerView homeTopRec;
    @BindView(R2.id.taobaoke)
    ImageView taobaoke;
    @BindView(R2.id.home_see_more_bottom)
    TextView homeSeeMoreBottom;
    @BindView(R2.id.home_good_choice_rec)
    RecyclerView homeGoodChoiceRec;
    @BindView(R2.id.home_bottom_rec)
    RecyclerView homeBottomRec;
    @BindView(R2.id.home_smart_refresh)
    SmartRefreshLayout homeSmartRefresh;
    @BindView(R2.id.home_gotop)
    ImageView mGoTop;
    @BindView(R2.id.home_nested_scroll)
    NestedScrollView homeNestedScroll;
    @BindView(R2.id.home_slide_indicator_point)
    SeekBar homeSlideIndicatorPoint;

    private int nextPage = 1;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        //跑马灯
        presenter.setViewSingleLine();
        //xBanner
        presenter.setXBanner(homeXbanner, homeTopBg);
        //topRec
        presenter.setRec(homeTopRec,homeSlideIndicatorPoint);

        //优选recycler
        presenter.setGoodChoiceRec(homeGoodChoiceRec);
        //推荐recycler
        presenter.setBottomRec(nextPage, homeBottomRec);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        homeSmartRefresh.setRefreshHeader(customHeader);
        homeSmartRefresh.setRefreshFooter(new ClassicsFooter(getContext()));


    }

    @Override
    public void initClick() {
        homeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });

        homeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/messagecenter").navigation();
            }
        });

        homeSeeMoreTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "我被点击了", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/mine/messagecenter").navigation();
            }
        });

        homeSeeMoreBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getActivity(), "我被点击了", Toast.LENGTH_SHORT).show();
                ARouter.getInstance().build("/module_classify/ClassificationDetailsActivity").navigation();
            }
        });

        //设置上拉刷新下拉加载
        homeSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                nextPage = 1;
                presenter.setBottomRec(nextPage, homeBottomRec);
            }
        });
        homeSmartRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                nextPage++;
                presenter.setBottomRec(nextPage, homeBottomRec);
            }
        });

        homeNestedScroll.setOnScrollChangeListener(this);

        mGoTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeNestedScroll.fullScroll(NestedScrollView.FOCUS_UP);
            }
        });
    }

    @Override
    public HomeView createView() {
        return this;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getContext());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            //不可见
            Log.d("HomeFragment", "hidden:" + hidden);
            homeMarquee.stopFlipping();
            homeXbanner.stopAutoPlay();
        } else {
            //可见
            Log.d("HomeFragment", "hidden:" + hidden);
            homeMarquee.startFlipping();
            homeXbanner.startAutoPlay();
//            //跑马灯
//            presenter.setViewSingleLine();
            //xBanner
//            presenter.setXBanner(homeXbanner, homeTopBg);
//            //topRec
//            presenter.setRec(homeTopRec);
            //优选recycler
            presenter.setGoodChoiceRec(homeGoodChoiceRec);
            //推荐recycler
            presenter.setBottomRec(nextPage, homeBottomRec);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("HomeFragment", "不可见");
        homeMarquee.stopFlipping();
        homeXbanner.stopAutoPlay();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("HomeFragment", "可见");
        homeMarquee.startFlipping();
        homeXbanner.startAutoPlay();
    }

    @Override
    public void lodeMarquee(List<View> views) {
        homeMarquee.setViews(views);
    }

    @Override
    public void refreshSuccess() {
        homeSmartRefresh.finishLoadMore();
        homeSmartRefresh.finishRefresh();
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        int[] ints = new int[2];
        homeBottomRec.getLocationOnScreen(ints);
        int y = ints[1];
        if (y <= 0) {
            mGoTop.setVisibility(View.VISIBLE);
        } else {
            mGoTop.setVisibility(View.GONE);
        }
    }
}
