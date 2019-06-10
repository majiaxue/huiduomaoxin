package com.example.group_fans;

import com.example.group_fans.adapter.GroupFansRvAdapter;
import com.example.mvp.IView;

public interface GroupFansView extends IView {
    void loadUI(GroupFansRvAdapter adapter);

    void loadFinish();
}
