package com.example.secondarydetails;

import com.example.mvp.IView;
import com.example.adapter.SecondaryPddRecAdapter;

/**
 * Created by cuihaohao on 2019/5/31
 * Describe:
 */
public interface SecondaryDetailsView extends IView {
    void lodeRec(SecondaryPddRecAdapter baseRecAdapter);

    void noGoods(boolean isNoGoods);
}
