package com.example.community;

import com.example.community.adapter.CommunityAdapter;
import com.example.mvp.IView;

public interface CommunityView extends IView {
    void updateVP(CommunityAdapter adapter);
}
