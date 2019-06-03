package com.example.invite_friends;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.stx.xhb.xbanner.XBanner;

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
    public InviteFriendsView createView() {
        return this;
    }

    @Override
    public InviteFriendsPresenter createPresenter() {
        return new InviteFriendsPresenter(this);
    }
}
