package com.example.operator;

import com.example.mvp.IView;
import com.example.operator.adapter.YysFactorAdapter;
import com.example.operator.adapter.YysQuanyiAdapter;

public interface OperatorView extends IView {
    void loadFactor(YysFactorAdapter adapter);
}
