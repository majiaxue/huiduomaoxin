package com.example.assess_detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;

import butterknife.BindView;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class AssessDetailActivity extends BaseActivity<AssessDetailView, AssessDetailPresenter> implements AssessDetailView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right)
    ImageView includeRight;
    @BindView(R2.id.assess_detail_zan_img)
    ImageView assessDetailZanImg;
    @BindView(R2.id.assess_detail_count_zan2)
    TextView assessDetailCountZan2;
    @BindView(R2.id.asssess_detail_dianzan)
    LinearLayout asssessDetailDianzan;
    @BindView(R2.id.assess_detail_count_assess2)
    TextView assessDetailCountAssess2;
    @BindView(R2.id.assess_detail_pinglun)
    LinearLayout assessDetailPinglun;
    @BindView(R2.id.assess_detail_bootom)
    LinearLayout assessDetailBootom;
    @BindView(R2.id.assess_detail_header)
    ImageView assessDetailHeader;
    @BindView(R2.id.assess_detail_name)
    TextView assessDetailName;
    @BindView(R2.id.assess_detail_ratingbar)
    MaterialRatingBar assessDetailRatingbar;
    @BindView(R2.id.assess_detail_time)
    TextView assessDetailTime;
    @BindView(R2.id.assess_detail_content)
    TextView assessDetailContent;
    @BindView(R2.id.assess_detail_rv_img)
    RecyclerView assessDetailRvImg;
    @BindView(R2.id.assess_detail_count_see)
    TextView assessDetailCountSee;
    @BindView(R2.id.assess_detail_count_zan)
    TextView assessDetailCountZan;
    @BindView(R2.id.assess_detail_count_assess)
    TextView assessDetailCountAssess;

    @Override
    public int getLayoutId() {
        return R.layout.activity_assess_detail;
    }

    @Override
    public void initData() {
        includeTitle.setText("评价详情");
        includeRight.setImageResource(R.drawable.icon_fenxiang11);
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public AssessDetailView createView() {
        return this;
    }

    @Override
    public AssessDetailPresenter createPresenter() {
        return new AssessDetailPresenter(this);
    }
}
