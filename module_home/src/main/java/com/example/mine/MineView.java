package com.example.mine;

import com.example.mine.adapter.MyToolAdapter;
import com.example.mvp.IView;

public interface MineView extends IView {
    void loadMyTool(MyToolAdapter adapter);
}
