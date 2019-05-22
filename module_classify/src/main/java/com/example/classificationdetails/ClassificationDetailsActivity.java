package com.example.classificationdetails;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

/**
 * 分类商品
 */
@Route(path = "/module_classify/ClassificationDetailsActivity")
public class ClassificationDetailsActivity extends BaseActivity<ClassificationDetailsView, ClassificationDetailsPresenter> implements ClassificationDetailsView, View.OnClickListener {


    @BindView(R2.id.classification_back)
    ImageView classificationBack;
    @BindView(R2.id.classification_message)
    LinearLayout classificationMessage;
    @BindView(R2.id.classification_search)
    LinearLayout classificationSearch;
    @BindView(R2.id.synthesize_bottom)
    ImageView synthesizeBottom;
    @BindView(R2.id.sales_volume_top)
    ImageView salesVolumeTop;
    @BindView(R2.id.sales_volume_bottom)
    ImageView salesVolumeBottom;
    @BindView(R2.id.classification_sales_volume)
    RelativeLayout classificationSalesVolume;
    @BindView(R2.id.price_top)
    ImageView priceTop;
    @BindView(R2.id.price_bottom)
    ImageView priceBottom;
    @BindView(R2.id.classification_price)
    RelativeLayout classificationPrice;
    @BindView(R2.id.credit_top)
    ImageView creditTop;
    @BindView(R2.id.credit_bottom)
    ImageView creditBottom;
    @BindView(R2.id.classification_credit)
    RelativeLayout classificationCredit;
    @BindView(R2.id.classification_synthesize)
    RelativeLayout classificationSynthesize;
    @BindView(R2.id.classification_switchover)
    ImageView classificationSwitchover;
    @BindView(R2.id.classification_rec)
    RecyclerView classificationRec;

    private boolean state = true;
    private boolean salesvolume = true;
    private boolean price = true;
    private boolean credit = true;


    @Override
    public int getLayoutId() {
        return R.layout.activity_classification_details;
    }

    @Override
    public void initData() {

        presenter.setClassifyRec(classificationRec, classificationSwitchover);

    }

    @Override
    public void initClick() {
        classificationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        classificationSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(ClassificationDetailsActivity.this, "我被点击了", Toast.LENGTH_SHORT).show();
                //到搜索页面
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });

        classificationSwitchover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state) {
                    presenter.setClassifyGridRec(classificationRec, classificationSwitchover);
                    state = false;
                } else {
                    //切换布局条形
                    presenter.setClassifyRec(classificationRec, classificationSwitchover);
                    state = true;
                }
            }
        });

        classificationSalesVolume.setOnClickListener(this);
        classificationPrice.setOnClickListener(this);
        classificationCredit.setOnClickListener(this);
    }

    @Override
    public ClassificationDetailsView createView() {
        return this;
    }

    @Override
    public ClassificationDetailsPresenter createPresenter() {
        return new ClassificationDetailsPresenter(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.classification_sales_volume) {
            //销量
            if (salesvolume) {

                salesVolumeTop.setImageResource(R.drawable.gvhgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                salesvolume = false;
            } else {
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.cgbhdfg);
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                salesvolume = true;
            }
        } else if (v.getId() == R.id.classification_price) {
            //价格
            if (price) {
                priceTop.setImageResource(R.drawable.gvhgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                price = false;
            } else {
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.cgbhdfg);
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                price = true;
            }

        } else if (v.getId() == R.id.classification_credit) {
            //信用
            if (credit) {
                creditTop.setImageResource(R.drawable.gvhgh);
                creditBottom.setImageResource(R.drawable.khjkjhgjk);
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                credit = false;
            } else {
                creditTop.setImageResource(R.drawable.ghfgh);
                creditBottom.setImageResource(R.drawable.cgbhdfg);
                salesVolumeTop.setImageResource(R.drawable.ghfgh);
                salesVolumeBottom.setImageResource(R.drawable.khjkjhgjk);
                priceTop.setImageResource(R.drawable.ghfgh);
                priceBottom.setImageResource(R.drawable.khjkjhgjk);
                credit = true;
            }
        }
    }
}
