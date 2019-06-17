package com.example.operator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.operator.adapter.YysFactorAdapter;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

@Route(path = "/mine/operator")
public class OperatorActivity extends BaseActivity<OperatorView, OperatorPresenter> implements OperatorView, NestedScrollView.OnScrollChangeListener {
    @BindView(R2.id.operator_back)
    ImageView operatorBack;
    @BindView(R2.id.operator_rv)
    RecyclerView mRv;
    @BindView(R2.id.operator_invite_fans)
    TextView operatorInviteFans;
    @BindView(R2.id.operator_shuoming)
    TextView operatorShuoming;
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
        SpaceItemDecoration itemDecoration = new SpaceItemDecoration(0, 0, 0, (int) getResources().getDimension(R.dimen.dp_12));
        mRv.setLayoutManager(layoutManager);
        mRv.addItemDecoration(itemDecoration);
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

        //邀请粉丝
        operatorInviteFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/module_classify/tshop_home").navigation();
//                presenter.login();
//                presenter.test();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void loadFactor(YysFactorAdapter adapter) {
        mRv.setAdapter(adapter);
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
