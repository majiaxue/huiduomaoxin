package com.example.assess_detail;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assess_detail.adapter.InsideAssessAdapter;
import com.example.assess_detail.adapter.InsideImageAdapter;
import com.example.mvp.BaseActivity;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.example.view.RatingBarView;

import butterknife.BindView;

public class AssessDetailActivity extends BaseActivity<AssessDetailView, AssessDetailPresenter> implements AssessDetailView, NestedScrollView.OnScrollChangeListener {
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
    @BindView(R2.id.assess_detail_input_assess)
    LinearLayout mAssessInput;
    @BindView(R2.id.assess_detail_edit)
    EditText mEdit;
    @BindView(R2.id.assess_detail_btn)
    TextView mBtn;
    @BindView(R2.id.assess_detail_scroll)
    NestedScrollView mScroll;
    @BindView(R2.id.assess_detail_header)
    ImageView assessDetailHeader;
    @BindView(R2.id.assess_detail_name)
    TextView assessDetailName;
    @BindView(R2.id.assess_detail_ratingbar)
    RatingBarView assessDetailRatingbar;
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
    @BindView(R2.id.assess_detail_rv_inside_assess)
    RecyclerView assessDetailRvInsideAssess;

    @Override
    public int getLayoutId() {
        return R.layout.activity_assess_detail;
    }

    @Override
    public void initData() {
        includeTitle.setText("评价详情");
        includeRight.setImageResource(R.drawable.icon_fenxiang11);
        assessDetailRatingbar.setStar(4, false);
        assessDetailRatingbar.setClickable(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        assessDetailRvImg.setLayoutManager(gridLayoutManager);
        assessDetailRvImg.addItemDecoration(new SpaceItemDecorationLeftAndRight((int) getResources().getDimension(R.dimen.dp_15), (int) getResources().getDimension(R.dimen.dp_15)));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        assessDetailRvInsideAssess.setLayoutManager(linearLayoutManager);
        mScroll.setOnScrollChangeListener(this);
        presenter.loadData();
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        asssessDetailDianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.dianZan();
            }
        });

        assessDetailPinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assessDetailPinglun.setVisibility(View.GONE);
                mAssessInput.setVisibility(View.VISIBLE);
                mEdit.setFocusable(true);
                mEdit.setFocusableInTouchMode(true);
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mEdit.getText().toString().length() < 2) {
                    Toast.makeText(AssessDetailActivity.this, "请至少输入2个字符", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AssessDetailActivity.this, "评价了。。。", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (mAssessInput.getVisibility() == View.VISIBLE) {
            if (scrollY - oldScrollY > 42 || scrollY - oldScrollY < -42) {
                assessDetailPinglun.setVisibility(View.VISIBLE);
                mAssessInput.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void loadImg(InsideImageAdapter adapter) {
        assessDetailRvImg.setAdapter(adapter);
    }

    @Override
    public void loadInsideAssess(InsideAssessAdapter adapter) {
        assessDetailRvInsideAssess.setAdapter(adapter);
    }

    @Override
    public void zan() {
        assessDetailZanImg.setImageResource(R.drawable.icon_dianzan);
    }

    @Override
    public void cancelZan() {
        assessDetailZanImg.setImageResource(R.drawable.icon_dianzan1);
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
