package com.example.mine;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mine.adapter.MyToolAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView, NestedScrollView.OnScrollChangeListener {
    @BindView(R2.id.mine_rec)
    RecyclerView mMyTool;
    @BindView(R2.id.mine_advice)
    ImageView mAdvice;
    @BindView(R2.id.mine_tab_top)
    ImageView mTabTop;
    @BindView(R2.id.mine_parent)
    NestedScrollView mParent;
    @BindView(R2.id.mine_login)
    TextView mLogin;
    @BindView(R2.id.mine_header)
    ImageView mHeader;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        presenter.loadRec();
    }

    @Override
    public void initClick() {
        mParent.setOnScrollChangeListener(this);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToLogin();
            }
        });

        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToSetting();
            }
        });
    }

    @Override
    public void loadMyTool(MyToolAdapter adapter) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mMyTool.setLayoutManager(layoutManager);
        mMyTool.addItemDecoration(new SpaceItemDecoration(10, 10));
        mMyTool.setAdapter(adapter);
    }

    @Override
    public MineView createView() {
        return this;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(getContext());
    }

    @Override
    public void onScrollChange(NestedScrollView nestedScrollView, int i, int i1, int i2, int i3) {
        int[] location = new int[2];
        mAdvice.getLocationOnScreen(location);
        int y = location[1];
        LogUtil.e("Y轴：" + y);
        if (y <= mTabTop.getHeight()) {
            mTabTop.setVisibility(View.VISIBLE);
            mAdvice.setVisibility(View.GONE);
        } else {
            mTabTop.setVisibility(View.GONE);
            mAdvice.setVisibility(View.VISIBLE);
        }
    }
}
