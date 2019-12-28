package com.example.invite_friends;

import com.example.invite_friends.adapter.InviteVpAdapter;
import com.example.mvp.IView;

public interface InviteFriendsView extends IView {

    void loadVP(InviteVpAdapter adapter, int size);
}
