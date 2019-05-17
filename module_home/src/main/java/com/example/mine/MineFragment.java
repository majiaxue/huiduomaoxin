package com.example.mine;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mine.adapter.MyToolAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;
import butterknife.Unbinder;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {

    @BindView(R2.id.mine_setting)
    ImageView mineSetting;
    @BindView(R2.id.mine_header)
    ImageView mineHeader;
    @BindView(R2.id.mine_name)
    TextView mineName;
    @BindView(R2.id.mine_code)
    TextView mineCode;
    @BindView(R2.id.mine_copy)
    TextView mineCopy;
    @BindView(R2.id.mine_all_order)
    LinearLayout mineAllOrder;
    @BindView(R2.id.mine_yifukuan)
    LinearLayout mineYifukuan;
    @BindView(R2.id.mine_yijiesuan)
    LinearLayout mineYijiesuan;
    @BindView(R2.id.mine_yishixiao)
    LinearLayout mineYishixiao;
    @BindView(R2.id.mine_advice)
    ImageView mineAdvice;
    @BindView(R2.id.mine_income_form)
    LinearLayout mineIncomeForm;
    @BindView(R2.id.mine_fans_order)
    LinearLayout mineFansOrder;
    @BindView(R2.id.mine_group_fans)
    LinearLayout mineGroupFans;
    @BindView(R2.id.mine_up_yys)
    ImageView mineUpYys;
    @BindView(R2.id.mine_rec)
    RecyclerView mineRec;
    @BindView(R2.id.mine_parent)
    NestedScrollView mineParent;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        mineHeader.bringToFront();
        presenter.loadRec();
    }

    @Override
    public void initClick() {
        mineName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToLogin();
            }
        });

        mineHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToSetting();
            }
        });

        mineAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToPredict();
            }
        });
    }

    @Override
    public void loadMyTool(MyToolAdapter adapter) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mineRec.setLayoutManager(layoutManager);
        mineRec.addItemDecoration(new SpaceItemDecoration(10, 10));
        mineRec.setAdapter(adapter);
    }

    @Override
    public MineView createView() {
        return this;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(getContext());
    }
}
