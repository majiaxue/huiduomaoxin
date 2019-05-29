package com.example.shop_home.treasure;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.BabyRecBean;
import com.example.goods_detail.GoodsDetailActivity;
import com.example.mvp.BasePresenter;
import com.example.shop_home.adapter.TreasureLstAdapter;
import com.example.shop_home.adapter.TreasureWaterfallAdapter;
import com.example.user_store.R;

import java.util.ArrayList;
import java.util.List;

public class ShopTreasurePresenter extends BasePresenter<ShopTreasureView> {

    private List<BabyRecBean> babyRecBeanList;
    private TreasureLstAdapter lstAdapter;
    private TreasureWaterfallAdapter waterfallAdapter;
    private boolean isWaterfall = false;
    private boolean isPositiveSalesVolume = false;
    private boolean isPositivePrice = false;
    private boolean isPositiveCredit = false;

    public ShopTreasurePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        babyRecBeanList = new ArrayList<>();
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
        babyRecBeanList.add(new BabyRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));
        lstAdapter = new TreasureLstAdapter(mContext, babyRecBeanList, R.layout.item_baby_rec);
        waterfallAdapter = new TreasureWaterfallAdapter(mContext, babyRecBeanList, R.layout.item_baby_rec_staggered_grid);

        lstAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
            }
        });

        waterfallAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                mContext.startActivity(new Intent(mContext, GoodsDetailActivity.class));
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
}
