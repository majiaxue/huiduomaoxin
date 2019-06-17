package com.example.home;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.BannerBean;
import com.example.common.CommonResource;
import com.example.entity.BaseRecBean;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.entity.TopBannerBean;
import com.example.home.adapter.GoodChoiceRecAdapter;
import com.example.home.adapter.GoodsRecommendAdapter;
import com.example.home.adapter.HomeTopRecAdapter;
import com.example.home.bean.GoodChoiceBean;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class HomePresenter extends BasePresenter<HomeView> {


    private List<String> data;
    private List<View> views = new ArrayList<>();
    private List<BaseRecImageAndTextBean> strings;
    private List<GoodChoiceBean.DataBean> goodList = new ArrayList<>();
    private List<BannerBean.RecordsBean> beanList;

    public HomePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setViewSingleLine() {
        data = new ArrayList<>();
        data.add("git常用命令");
        data.add("Git配置SSH访问GitHub(window)");
        data.add("关于java的抽象和接口");
        data.add("阿里HotFix2.0升级详解 畅谈热修复领域那些事");
        views.clear();
        for (int i = 0; i < data.size(); i++) {
            final int position = i;
            //设置滚动的单个布局
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_home_marquee_view, null);
            //初始化布局的控件
            TextView marqueeMessage = moreView.findViewById(R.id.marquee_message);

            //进行对控件赋值
            marqueeMessage.setText(data.get(i));

            //添加到循环滚动数组里面去
            views.add(moreView);
            if (getView() != null) {
                getView().lodeMarquee(views);
            }
        }
    }

    public void setXBanner(final XBanner homeXbanner, final ImageView homeTopBg) {
        //轮播图
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9005).getDataWithout(CommonResource.USERSBANNER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("homePresenterResult---------->" + result);
                BannerBean records = JSON.parseObject(result, BannerBean.class);
                beanList = records.getRecords();

//              homeXbanner.setData(images, null);
//                homeXbanner.setBannerData(R.layout.image_fresco,beanList);
                homeXbanner.setBannerData(beanList);
                Glide.with(mContext).load(beanList.get(0).getPicBackUrl()).into(homeTopBg);
                homeXbanner.loadImage(new XBanner.XBannerAdapter() {
                    @Override
                    public void loadBanner(XBanner banner, Object model, View view, int position) {
//                        SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
//                        bannerImage.setImageURI(((BannerBean)model).getXBannerUrl());
                        RequestOptions requestOptions = RequestOptions.centerCropTransform();
                        Glide.with(mContext).load(((BannerBean.RecordsBean) model).getXBannerUrl())
                                .apply(requestOptions)
                                .transform(new RoundedCorners((int) mContext.getResources().getDimension(R.dimen.dp_10)))
                                .into((ImageView) view);

                    }
                });
                // 设置XBanner的页面切换特效
                homeXbanner.setPageTransformer(Transformer.Default);
                // 设置XBanner页面切换的时间，即动画时长
                homeXbanner.setPageChangeDuration(1000);

                //banner切换image也切换
                homeXbanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {

                    }

                    @Override
                    public void onPageSelected(int i) {
//                        homeTopBg.setImageURI(Uri.parse(beanList.get(i).getPicBackUrl()));
                        Glide.with(mContext).load(beanList.get(i).getPicBackUrl()).into(homeTopBg);

                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {

                    }
                });
                //监听广告 item 的单击事件
                homeXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                    @Override
                    public void onItemClick(XBanner banner, Object model, View view, int position) {
                        Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("homePresenterErrorMsg---------->" + errorMsg);
            }
        }));


    }

    //店铺
    public void setRec(RecyclerView homeTopRec) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1, LinearLayoutManager.HORIZONTAL, false);
        homeTopRec.setLayoutManager(gridLayoutManager);

        strings = new ArrayList<>();
        strings.add(new BaseRecImageAndTextBean("淘宝", R.drawable.tb));
        strings.add(new BaseRecImageAndTextBean("拼多多", R.drawable.pdd));
        strings.add(new BaseRecImageAndTextBean("京东", R.drawable.jd));
        strings.add(new BaseRecImageAndTextBean("天猫", R.drawable.tm));


        HomeTopRecAdapter homeTopRecAdapter = new HomeTopRecAdapter(mContext, strings, R.layout.item_home_top_rec);
        homeTopRec.setAdapter(homeTopRecAdapter);

        homeTopRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
//                Intent intent = new Intent(mContext, SecondaryDetailsActivity.class);
//                intent.putExtra("type", "" + position);
//                mContext.startActivity(intent);
                ARouter.getInstance().build("/module_home/SecondaryDetailsActivity")
                        .withString("type", position + "")
                        .navigation();
            }
        });


    }

    //优选
    public void setGoodChoiceRec(final RecyclerView homeGoodChoiceRec) {

        Map map = MapUtil.getInstance().addParms("page", 1).addParms("pagesize", 10).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSPRODUCTS, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("homePresenterResult---------->" + result);
                GoodChoiceBean GoodChoiceBean = JSON.parseObject(result, new TypeReference<GoodChoiceBean>() {
                }.getType());
                goodList.clear();
                if (GoodChoiceBean.getData() == null) {
                    LogUtil.e("数据为空");
                } else {
                    goodList.addAll(GoodChoiceBean.getData());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 1, LinearLayoutManager.HORIZONTAL, false);
                    homeGoodChoiceRec.setLayoutManager(gridLayoutManager);
                    GoodChoiceRecAdapter goodChoiceRecAdapter = new GoodChoiceRecAdapter(mContext, goodList, R.layout.item_home_good_choice_rec);
                    homeGoodChoiceRec.setAdapter(goodChoiceRecAdapter);
                    goodChoiceRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                    .withString("para", goodList.get(position).getItem_id())
                                    .withString("shoptype", goodList.get(position).getUser_type()).navigation();
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("homePresenterErrorMsg---------->" + errorMsg);
            }
        }));


    }

    //推荐
    public void setBottomRec(final RecyclerView homeBottomRec) {
        Map map = MapUtil.getInstance().addParms("page", 5).addParms("pagesize", 20).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSPRODUCTS, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("homePresenterResult---------->" + result);
                GoodChoiceBean GoodChoiceBean = JSON.parseObject(result, new TypeReference<GoodChoiceBean>() {
                }.getType());
                goodList.clear();
                goodList.addAll(GoodChoiceBean.getData());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                homeBottomRec.setLayoutManager(linearLayoutManager);
                GoodsRecommendAdapter goodsRecommendAdapter = new GoodsRecommendAdapter(mContext, goodList, R.layout.item_base_rec);
                homeBottomRec.setAdapter(goodsRecommendAdapter);

                goodsRecommendAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                .withString("para", goodList.get(position).getItem_id())
                                .withString("shoptype", goodList.get(position).getUser_type()).navigation();
                    }
                });

                goodsRecommendAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                        .withString("para", goodList.get(index).getItem_id())
                                        .withString("shoptype", goodList.get(index).getUser_type()).navigation();
                            }
                        });
                    }
                });
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("homePresenterErrorMsg---------->" + errorMsg);
            }
        }));

    }

}
