package com.example.home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.view.CustomHeader;
import com.example.view.MarqueeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

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
    RecyclerView homeTopRec;
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
        presenter.setRec(homeTopRec);
        //优选recycler
        presenter.setGoodChoiceRec(homeGoodChoiceRec);
        //推荐recycler
        presenter.setBottomRec(homeBottomRec);

        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        homeSmartRefresh.setRefreshHeader(customHeader);
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
            presenter.setXBanner(homeXbanner, homeTopBg);
//            //topRec
//            presenter.setRec(homeTopRec);
            //优选recycler
            presenter.setGoodChoiceRec(homeGoodChoiceRec);
            //推荐recycler
            presenter.setBottomRec(homeBottomRec);
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

}
