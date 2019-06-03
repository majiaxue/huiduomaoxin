package com.example.classificationdetails;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.BaseRecAdapter;
import com.example.classificationdetails.adapter.ClassificationRecAdapter;
import com.example.entity.BaseRecBean;
import com.example.module_classify.R;
import com.example.mvp.BasePresenter;
import com.example.utils.DisplayUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import java.lang.reflect.Field;
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
    private String[] titleArr = {"淘宝","拼多多","京东"};

    public ClassificationDetailsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }


    public void initTabLayout(final TabLayout classificationTab) {
        for (String title : titleArr) {
            classificationTab.addTab(classificationTab.newTab().setText(title));
        }

        classificationTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) classificationTab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });

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
