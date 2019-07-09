package com.example.points;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

@Route(path = "/mine/points")
public class PointsActivity extends BaseActivity<PointsView, PointsPresenter> implements PointsView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.include_right_btn)
    TextView includeRightBtn;
    @BindView(R2.id.points_total_points)
    TextView pointsTotalPoints;
    @BindView(R2.id.points_cashing_points)
    TextView pointsCashingPoints;
    @BindView(R2.id.points_zfb_account)
    TextView pointsZfbAccount;
    @BindView(R2.id.points_choose_zfb)
    RelativeLayout pointsChooseZfb;
    @BindView(R2.id.points_edit)
    EditText pointsEdit;
    @BindView(R2.id.points_my_points)
    TextView pointsMyPoints;
    @BindView(R2.id.points_toall)
    TextView pointsToall;
    @BindView(R2.id.points_rules)
    TextView pointsRules;
    @BindView(R2.id.points_btn)
    TextView pointsBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_points;
    }

    @Override
    public void initData() {
        includeTitle.setText("积分提现");
        includeRightBtn.setText("明细");
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
    public PointsView createView() {
        return this;
    }

    @Override
    public PointsPresenter createPresenter() {
        return new PointsPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
