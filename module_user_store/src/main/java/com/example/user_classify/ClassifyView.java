package com.example.user_classify;

import com.example.mvp.IView;
import com.example.user_classify.adapter.UserLeftRvAdapter;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public interface ClassifyView extends IView {

    void loadLeftRv(UserLeftRvAdapter adapter);
}
