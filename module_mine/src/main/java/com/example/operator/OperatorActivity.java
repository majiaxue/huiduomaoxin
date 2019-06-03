package com.example.operator;

import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.operator.adapter.YysFactorAdapter;
import com.example.operator.adapter.YysQuanyiAdapter;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/mine/operator")
public class OperatorActivity extends BaseActivity<OperatorView, OperatorPresenter> implements OperatorView, NestedScrollView.OnScrollChangeListener {
    @BindView(R2.id.operator_back)
    ImageView operatorBack;
    @BindView(R2.id.operator_junior)
    TextView operatorJunior;
    @BindView(R2.id.operator_senior)
    TextView operatorSenior;
    @BindView(R2.id.operator_factor1_junior)
    TextView operatorFactor1Junior;
    @BindView(R2.id.operator_factor2_rv_junior)
    RecyclerView operatorFactor2RvJunior;
    @BindView(R2.id.operator_rela_junior)
    RelativeLayout operatorRelaJunior;
    @BindView(R2.id.operator_factor1_senior)
    TextView operatorFactor1Senior;
    @BindView(R2.id.operator_factor2_senior)
    TextView operatorFactor2Senior;
    @BindView(R2.id.operator_pay_senior)
    TextView operatorPaySenior;
    @BindView(R2.id.operator_yugu_senior)
    TextView operatorYuguSenior;
    @BindView(R2.id.operator_rela_senior)
    RelativeLayout operatorRelaSenior;
    @BindView(R2.id.operator_quanyi_rv)
    RecyclerView operatorQuanyiRv;
    @BindView(R2.id.operator_invite_fans)
    TextView operatorInviteFans;
    @BindView(R2.id.operator_shuoming)
    TextView operatorShuoming;
    @BindView(R2.id.operator_bg_change)
    RelativeLayout changeBg;
    @BindView(R2.id.operator_scroll)
    NestedScrollView mScorll;
    @BindView(R2.id.operator_top)
    RelativeLayout mTop;
    @BindView(R2.id.operator_title)
    TextView mTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_operator;
    }

    @Override
    public void initData() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_14));
        operatorFactor2RvJunior.setLayoutManager(layoutManager);
        operatorFactor2RvJunior.addItemDecoration(itemDecoration);

        SpaceItemDecoration itemDecoration1 = new SpaceItemDecoration(0, (int) getResources().getDimension(R.dimen.dp_10), 0, (int) getResources().getDimension(R.dimen.dp_8));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        operatorQuanyiRv.setLayoutManager(gridLayoutManager);
        operatorQuanyiRv.addItemDecoration(itemDecoration1);
        presenter.loadData();
        mScorll.setOnScrollChangeListener(this);
    }

    @Override
    public void initClick() {
        operatorBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //初级运营商权益
        operatorJunior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBg.setBackgroundResource(R.drawable.ghfgdf);
                operatorRelaJunior.setVisibility(View.VISIBLE);
                operatorRelaSenior.setVisibility(View.INVISIBLE);
                operatorQuanyiRv.clearFocus();
            }
        });
        //高级运营商权益
        operatorSenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBg.setBackgroundResource(R.drawable.gaojiyys);
                operatorRelaJunior.setVisibility(View.INVISIBLE);
                operatorRelaSenior.setVisibility(View.VISIBLE);
                operatorSenior.setFocusableInTouchMode(true);
                operatorSenior.setFocusable(true);
                operatorQuanyiRv.clearFocus();
            }
        });
        //高级运营商支付
        operatorPaySenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //邀请粉丝
        operatorInviteFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        if (scrollY < 60) {
            mTop.setBackgroundColor(Color.TRANSPARENT);
            operatorBack.setImageResource(R.drawable.icon_fanhui_bai);
            mTitle.setTextColor(Color.parseColor("#ffffff"));
        } else {
            mTop.setBackgroundColor(Color.WHITE);
            operatorBack.setImageResource(R.drawable.icon_fanhui);
            mTitle.setTextColor(Color.parseColor("#333333"));
        }
    }

    @Override
    public void loadFactor(YysFactorAdapter adapter) {

        operatorFactor2RvJunior.setAdapter(adapter);
    }

    @Override
    public void loadQuanyi(YysQuanyiAdapter adapter) {
        operatorQuanyiRv.setAdapter(adapter);
    }

    @Override
    public OperatorView createView() {
        return this;
    }

    @Override
    public OperatorPresenter createPresenter() {
        return new OperatorPresenter(this);
    }
}
