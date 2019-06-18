package com.example.operator;

import com.example.mvp.IView;
import com.example.operator.adapter.YysFactorAdapter;

public interface OperatorView extends IView {
    void loadFactor(YysFactorAdapter adapter);
}
