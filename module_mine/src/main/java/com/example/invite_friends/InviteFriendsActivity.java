package com.example.invite_friends;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.bean.BannerBean;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

import butterknife.BindView;

@Route(path = "/mine/invite_friends")
public class InviteFriendsActivity extends BaseActivity<InviteFriendsView, InviteFriendsPresenter> implements InviteFriendsView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.invite_friends_banner)
    XBanner inviteFriendsBanner;
    @BindView(R2.id.invite_friends_link)
    TextView inviteFriendsLink;
    @BindView(R2.id.invite_friends_bill)
    TextView inviteFriendsBill;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_friends;
    }

    @Override
    public void initData() {
        includeTitle.setText("邀请好友");
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

    }

    @Override
    public void loadBanner(List<BannerBean.RecordsBean> beanList) {
        inviteFriendsBanner.setBannerData(beanList);
        inviteFriendsBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(InviteFriendsActivity.this).load(((BannerBean.RecordsBean) model).getXBannerUrl()).into((ImageView) view);
            }
        });

        inviteFriendsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.shareLink();
            }
        });

        inviteFriendsBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.share(inviteFriendsBanner.getBannerCurrentItem());
            }
        });
    }

    @Override
    public InviteFriendsView createView() {
        return this;
    }

    @Override
    public InviteFriendsPresenter createPresenter() {
        return new InviteFriendsPresenter(this);
    }
}
