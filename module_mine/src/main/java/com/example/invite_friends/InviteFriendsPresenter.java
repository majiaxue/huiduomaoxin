package com.example.invite_friends;

import android.content.Context;

import com.example.mvp.BasePresenter;

public class InviteFriendsPresenter extends BasePresenter<InviteFriendsView> {
    public InviteFriendsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
}
