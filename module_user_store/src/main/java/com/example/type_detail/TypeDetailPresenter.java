package com.example.type_detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.BabyRecBean;
import com.example.goods_detail.GoodsDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.shop_home.ShopHomeActivity;
import com.example.shop_home.adapter.TreasureLstAdapter;
import com.example.type_detail.adapter.TypeDetailLstAdapter;
import com.example.type_detail.adapter.TypeDetailWaterfallAdapter;
import com.example.user_store.R;

import java.util.ArrayList;
import java.util.List;

public class TypeDetailPresenter extends BasePresenter<TypeDetailView> {

    private List<BabyRecBean> dataList;
    private TypeDetailLstAdapter lstAdapter;
    private TypeDetailWaterfallAdapter waterfallAdapter;
    private boolean isWaterfall = false;
    private boolean isPositiveSalesVolume = false;
    private boolean isPositivePrice = false;
    private boolean isPositiveCredit = false;

    public TypeDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        dataList = new ArrayList<>();
        dataList.add(new BabyRecBean(R.drawable.img_114, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_115, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_116, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_117, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_114, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_115, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_116, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_117, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_114, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_115, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_116, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345", "97%", "班迪卡旗舰店"));
        dataList.add(new BabyRecBean(R.drawable.img_117, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345", "97%", "班迪卡旗舰店"));

        lstAdapter = new TypeDetailLstAdapter(mContext, dataList, R.layout.rv_type_detail_lst);

        waterfallAdapter = new TypeDetailWaterfallAdapter(mContext, dataList, R.layout.rv_commend);

        lstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
            }
        }).setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, ShopHomeActivity.class));
                    }
                });
            }
        });

        waterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
            }
        }).setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, ShopHomeActivity.class));
                    }
                });
            }
        });

        if (getView() != null) {
            getView().loadLstRv(lstAdapter);
        }
    }

    public void ChangeShow() {
        if (isWaterfall) {
            getView().loadLstRv(lstAdapter);
            isWaterfall = false;
        } else {
            getView().loadWaterfallRv(waterfallAdapter);
            isWaterfall = true;
        }
    }

    public void changeTyep(int index) {
        isPositiveSalesVolume = index == 1 ? !isPositiveSalesVolume : false;
        isPositivePrice = index == 2 ? !isPositivePrice : false;
        isPositiveCredit = index == 3 ? !isPositiveCredit : false;
        getView().updateTitle(isPositiveSalesVolume, isPositivePrice, isPositiveCredit);
    }

    public void jumpToSearch() {
        ARouter.getInstance().build("/module_home/SearchActivity").navigation();
    }
}
