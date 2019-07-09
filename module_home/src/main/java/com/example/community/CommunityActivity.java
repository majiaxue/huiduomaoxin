package com.example.community;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragmentActivity;

import butterknife.BindView;

public class CommunityActivity extends BaseFragmentActivity<CommunityView, CommunityPresenter> implements CommunityView {
    @BindView(R2.id.community_tab)
    TabLayout communityTab;
    @BindView(R2.id.community_vp)
    ViewPager communityVp;

    @Override
    public int getLayoutId() {
        return R.layout.activity_community;
    }

    @Override
    public void initData() {
        communityTab.setupWithViewPager(communityVp);
        presenter.setTab(communityTab);
    }

    @Override
    public void initClick() {

    }

    @Override
    public CommunityView createView() {
        return this;
    }

    @Override
    public CommunityPresenter createPresenter() {
        return new CommunityPresenter(this);
    }

}
