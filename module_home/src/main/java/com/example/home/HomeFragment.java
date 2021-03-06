package com.example.home;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.trade.biz.AlibcConstants;
import com.alibaba.baichuan.trade.biz.context.AlibcTradeResult;
import com.alibaba.baichuan.trade.biz.core.taoke.AlibcTaokeParams;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.utils.StatusBarUtils;
import com.example.utils.TxtUtil;
import com.example.view.CustomHeader;
import com.example.view.CustomeRecyclerView;
import com.example.view.MarqueeView;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.kongzue.tabbar.TabBarView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R2.id.text131_gradual_change)
    TextView text131GradualChange;
    @BindView(R2.id.text141_gradual_change)
    TextView text141GradualChange;
    @BindView(R2.id.home_zhong_xbanner)
    ViewPager homeZhongXbanner;
    @BindView(R2.id.home_hot_recommend)
    RelativeLayout homeHotRecommend;
    @BindView(R2.id.home_dou_juan_buy)
    RelativeLayout homeDouJuanBuy;
    @BindView(R2.id.home_punch_sign)
    RelativeLayout homePunchSign;
    @BindView(R2.id.home_free_of_charge)
    RelativeLayout homeFreeOfCharge;
    @BindView(R2.id.home_huo_dong)
    ImageView homeHuoDong;
    @BindView(R2.id.home_see_more)
    TextView homeSeeMore;
    @BindView(R2.id.home_time)
    TextView homeTime;
    @BindView(R2.id.home_count_down)
    TextView homeCountDown;
    @BindView(R2.id.home_flash_sale_rec)
    RecyclerView homeFlashSaleRec;
    @BindView(R2.id.home_xianshiqianggou)
    RelativeLayout homeXianShiQiangGou;
    @BindView(R2.id.home_tab_bar1)
    TabBarView homeTabBar1;
    @BindView(R2.id.home_tab_bar2)
    TabBarView homeTabBar2;
    @BindView(R2.id.home_tab_bar3)
    TabBarView homeTabBar3;

    private int nextPage = 1;
    private CountDownTimer countDownTimer;
    private String format1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData() {
        StatusBarUtils.setAndroidNativeLightStatusBar(getActivity(), false);

        initGif();

        presenter.initTabBar(homeTabBar1,homeTabBar2,homeTabBar3);
//        time();
        //跑马灯
        presenter.setViewSingleLine();
        //xBanner
        presenter.setXBanner(homeXbanner, homeTopBg);
        //中间轮播图
        presenter.setZhongXBanner(homeZhongXbanner);
        //topRec
        presenter.setRec(homeTopRec, homeSlideIndicatorPoint);
        //优选recycler
        presenter.setGoodChoiceRec(homeGoodChoiceRec);
        //推荐recycler
        presenter.setBottomRec(nextPage, homeBottomRec);
        //下拉刷新样式
        CustomHeader customHeader = new CustomHeader(getActivity());
        customHeader.setPrimaryColors(getResources().getColor(R.color.colorTransparency));
        homeSmartRefresh.setRefreshHeader(customHeader);
        homeSmartRefresh.setRefreshFooter(new ClassicsFooter(getContext()));


        TxtUtil.txtJianbian(text131GradualChange, "#0c53e4", "#ae3fed");
        TxtUtil.txtJianbian(text141GradualChange, "#fe5d05", "#fdb902");

    }

    private void initGif() {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                //.placeholder(R.mipmap.ic_launcher_round)
                .error(android.R.drawable.stat_notify_error)
                .priority(Priority.HIGH)
                //.skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);

        Glide.with(this)
                .load(R.drawable.huodong)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .apply(options)
                .into(homeHuoDong);
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
                if (TextUtils.isEmpty(SPUtil.getToken())) {
                    PopUtils.isLogin(getContext());
                } else {
                    ARouter.getInstance().build("/mine/messagecenter").navigation();
                }
            }
        });

        homeSeeMoreTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/mine/messagecenter").navigation();
            }
        });

        homeSeeMoreBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 5).withInt("type", 1).navigation();
            }
        });

        //爆款推荐
        homeHotRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    ARouter.getInstance().build("/module_home/UniversalListActivity").withInt("position", 4).withInt("type", 2).navigation();
                } else {
                    //是否登录
                    PopUtils.isLogin(getContext());
                }

            }
        });
        //抖劵购买
        homeDouJuanBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    ARouter.getInstance().build("/module_home/ShakeStockActivity").navigation();
                } else {
                    //是否登录
                    PopUtils.isLogin(getContext());
                }

            }
        });
        //打卡签到
        homePunchSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    ARouter.getInstance().build("/module_home/PunchSignActivity").navigation();
                } else {
                    //是否登录
                    PopUtils.isLogin(getContext());
                }
            }
        });
        //今日免单
        homeFreeOfCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(SPUtil.getToken())) {
                    ARouter.getInstance().build("/module_home/FreeChargeActivity").navigation();
                } else {
                    //是否登录
                    PopUtils.isLogin(getContext());
                }

            }
        });
        //活动
        homeHuoDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                ARouter.getInstance().build("/module_classify/tshop_home").withString("url","https://s.click.taobao.com/t?e=m%3D2%26s%3Dzm6jhQNKreYcQipKwQzePCperVdZeJviLKpWJ%2Bin0XJRAdhuF14FMQ53%2FRat2hkiRitN3%2FurF3y9240E272vT5e1q28sdb9ofviWYg2ijQSnnpCc7iyvkKS2XTLv%2BVcH%2F2NLiseXG8sRye%2FDw5l6PgMXNI5sr7noQBRB1XJCO28RYIZhO1sDRLvex8GPs9uzI%2BVo4ZbOXiTKLJiYzWv6xmKche5LvsSoIez2UrYlUs7IpLlhUuPEixdPxHZbx0DROsIkmqRCprTAyJWI2L%2Fu1zVVOoyAUYPOxgxdTc00KD8%3D&union_lens=lensId%3APUB%401575447181%400b0b4eb0_0d55_16ecff839d0_0b2e%4001").navigation();
                //提供给三方传递配置参数
                Map<String, String> exParams = new HashMap<>();
                exParams.put(AlibcConstants.ISV_CODE, "appisvcode");


                //设置页面打开方式
                AlibcShowParams showParams = new AlibcShowParams();
                showParams.setOpenType(OpenType.Auto);

                AlibcTaokeParams taokeParams = new AlibcTaokeParams("", "", "");
                taokeParams.setPid("mm_112883640_11584347_72287650277");

                AlibcTrade.openByUrl((Activity) getContext(), "", "", null, null, null, showParams, taokeParams, exParams, new AlibcTradeCallback() {
                    @Override
                    public void onTradeSuccess(AlibcTradeResult alibcTradeResult) {

                    }

                    @Override
                    public void onFailure(int i, String s) {

                    }
                });
            }
        });

        //设置上拉刷新下拉加载
        homeSmartRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                nextPage = 1;
                //xBanner
                presenter.setXBanner(homeXbanner, homeTopBg);
                //优选recycler
                presenter.setGoodChoiceRec(homeGoodChoiceRec);
                //推荐recycler
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

        homeSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_home/FlashSaleActivity").navigation();
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
            homeMarquee.stopFlipping();
            homeXbanner.stopAutoPlay();
//            countDownTimer.cancel();
//            countDownTimer.onFinish();
        } else {
            //可见
//            time();
            homeMarquee.startFlipping();
            homeXbanner.startAutoPlay();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        homeMarquee.stopFlipping();
        homeXbanner.stopAutoPlay();
//        countDownTimer.cancel();
//        countDownTimer.onFinish();
    }

    @Override
    public void onResume() {
        super.onResume();
        homeMarquee.startFlipping();
        homeXbanner.startAutoPlay();
//        time();
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

    private void time() {
        //获取当前时间
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("HH");// yyyy年MM月dd日 HH:mm:ss
        Date date1 = new Date(System.currentTimeMillis());
        format1 = simpleDateFormat1.format(date1);
        homeTime.setText(format1 + "点场");
        //获取之后的一个小时
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");// yyyy年MM月dd日 HH:mm:ss
        Date date = new Date(System.currentTimeMillis() + 1 * 60 * 60 * 1000);
        String format = simpleDateFormat.format(date);
        long afterTime = getTimeStamp(format + ":00:00", "yyyy-MM-dd HH:mm:ss");
        LogUtil.e("时间" + format + "-----------" + afterTime + "--------------" + (afterTime - System.currentTimeMillis()));
        //第一个参数表示总时间，第二个参数表示间隔时间。
        countDownTimer = new CountDownTimer(afterTime - System.currentTimeMillis(), 1000) {//第一个参数表示总时间，第二个参数表示间隔时间。
            @Override
            public void onTick(long millisUntilFinished) {
                SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
                String dateString = formatter.format(millisUntilFinished);
                homeCountDown.setText("00:" + dateString);
                if (homeCountDown.getText().toString().contains("00:00:00")) {
                    time();
//                    presenter.initGoods(format1);
                }
            }

            @Override
            public void onFinish() {
                LogUtil.e("结束");
            }
        }.start();

    }

    /**
     * 时间转换为时间戳
     *
     * @param timeStr 时间 例如: 2016-03-09
     * @param format  时间对应格式  例如: yyyy-MM-dd
     * @return
     */
    public static long getTimeStamp(String timeStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = simpleDateFormat.parse(timeStr);
            long timeStamp = date.getTime();
            return timeStamp;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
