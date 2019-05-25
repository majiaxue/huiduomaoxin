package com.example.classificationdetails;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.BaseRecAdapter;
import com.example.classificationdetails.adapter.ClassificationRecAdapter;
import com.example.entity.BaseRecBean;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.utils.DisplayUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/20
 * Describe:
 */
public class ClassificationDetailsPresenter extends BasePresenter<ClassificationDetailsView> {

    private List<BaseRecBean> classifyRecList;
    private List<BaseRecBean> classifyGridRecList;
    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;

    public ClassificationDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setClassifyRec(RecyclerView classificationRec, ImageView classificationSwitchover){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        classificationRec.removeItemDecoration(spaceItemDecorationLeftAndRight);
        classificationRec.setLayoutManager(linearLayoutManager);
        classifyRecList = new ArrayList<>();
        classifyRecList.add(new BaseRecBean(R.drawable.reco1, "稙优泉化妆品买...", "领券减50元", "95.50", "123", "已抢64120件"));
        classifyRecList.add(new BaseRecBean(R.drawable.reco2, "有机护肤化妆品...", "领券减50元", "26.50", "123", "已抢64120件"));
        classifyRecList.add(new BaseRecBean(R.drawable.reco3, "美容美妆教学...", "领券减50元", "42.80", "123", "已抢64120件"));
        classifyRecList.add(new BaseRecBean(R.drawable.reco4, "美容美妆教学...", "领券减50元", "42.80", "123", "已抢64120件"));
        BaseRecAdapter baseRecAdapter = new BaseRecAdapter(mContext, classifyRecList, R.layout.item_base_rec);
        classificationRec.setAdapter(baseRecAdapter);

        classificationSwitchover.setImageResource(R.drawable.xfxfgvx);

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

    public void setClassifyGridRec(RecyclerView classificationRec, ImageView classificationSwitchover){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false);
        //添加间距
        spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
        if (classificationRec.getItemDecorationCount() == 0) {
            classificationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        classificationRec.setLayoutManager(gridLayoutManager);
        classifyGridRecList = new ArrayList<>();
        classifyGridRecList.add(new BaseRecBean(R.drawable.reco1, "稙优泉化妆品买...", "领券减50元", "95.50", "123", ""));
        classifyGridRecList.add(new BaseRecBean(R.drawable.reco2, "有机护肤化妆品...", "领券减50元", "26.50", "123", ""));
        classifyGridRecList.add(new BaseRecBean(R.drawable.reco3, "美容美妆教学...", "领券减50元", "42.80", "123", ""));
        classifyGridRecList.add(new BaseRecBean(R.drawable.reco4, "美容美妆教学...", "领券减50元", "42.80", "123", ""));
        ClassificationRecAdapter classificationRecAdapter = new ClassificationRecAdapter(mContext, classifyGridRecList, R.layout.item_classification_rec_grid);
        classificationRec.setAdapter(classificationRecAdapter);

        classificationSwitchover.setImageResource(R.drawable.fghfghfg);

        classificationRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").navigation();
            }
        });

        classificationRecAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
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
