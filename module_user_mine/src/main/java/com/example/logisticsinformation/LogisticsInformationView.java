package com.example.logisticsinformation;

import com.example.logisticsinformation.bean.LogisticsInforMationBean;
import com.example.mvp.IView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public interface LogisticsInformationView extends IView {
    void traces(LogisticsInforMationBean inforMationBeanList,int size);
}
