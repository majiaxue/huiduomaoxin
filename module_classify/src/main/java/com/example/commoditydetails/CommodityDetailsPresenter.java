package com.example.commoditydetails;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.BaseRecAdapter;
import com.example.bean.BannerBean;
import com.example.commoditydetails.bean.CommodityDetailsBean;
import com.example.commoditydetails.bean.CommodityXBannerBean;
import com.example.common.CommonResource;
import com.example.entity.BaseRecBean;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnPddCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class CommodityDetailsPresenter extends BasePresenter<CommodityDetailsView> {

    private List<BaseRecBean> baseRecBeanList;
    private List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList = new ArrayList<>();

    public CommodityDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initView(long goods_id) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi2(mContext).getDataWithout(CommonResource.PDDGOODSDETAIL + "/" + goods_id);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnPddCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("CommodityDetailsResult------------>" + result);
                CommodityDetailsBean commodityDetailsBean = JSON.parseObject(result, new TypeReference<CommodityDetailsBean>() {
                }.getType());
                beanList.clear();
                beanList.addAll(commodityDetailsBean.getGoods_detail_response().getGoods_details());
                if (getView() != null) {
                    getView().CommodityDetailsList(beanList);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("CommodityDetailsErrorMsg------------>" + errorMsg);
            }
        }));
    }

    public void setXBanner(XBanner commodityXbanner, List<CommodityDetailsBean.GoodsDetailResponseBean.GoodsDetailsBean> beanList) {
        final List<String> images = beanList.get(0).getGoods_gallery_urls();

        commodityXbanner.setData(images, null);
//        commodityXbanner.setBannerData(R.layout.image_fresco, images);
        commodityXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
//                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
                Glide.with(mContext).load(images.get(position)).apply(RequestOptions.centerInsideTransform()).into((ImageView) view);
            }
        });
        // 设置XBanner的页面切换特效
        commodityXbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        commodityXbanner.setPageChangeDuration(1000);

        //监听广告 item 的单击事件
        commodityXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //推荐
    public void setRecommendRec(RecyclerView shopRecommendRec) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        shopRecommendRec.setLayoutManager(linearLayoutManager);
        baseRecBeanList = new ArrayList<>();
        baseRecBeanList.add(new BaseRecBean(R.drawable.reco1, "稙优泉化妆品买...", "领券减50元", "95.50", "123", "已抢64120件"));
        baseRecBeanList.add(new BaseRecBean(R.drawable.reco2, "有机护肤化妆品...", "领券减50元", "26.50", "123", "已抢64120件"));
        baseRecBeanList.add(new BaseRecBean(R.drawable.reco3, "美容美妆教学...", "领券减50元", "42.80", "123", "已抢64120件"));
        baseRecBeanList.add(new BaseRecBean(R.drawable.reco4, "美容美妆教学...", "领券减50元", "42.80", "123", "已抢64120件"));
        BaseRecAdapter baseRecAdapter = new BaseRecAdapter(mContext, baseRecBeanList, R.layout.item_base_rec);
        shopRecommendRec.setAdapter(baseRecAdapter);

        baseRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
            }
        });

        baseRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
                    }
                });
            }
        });


    }

}
