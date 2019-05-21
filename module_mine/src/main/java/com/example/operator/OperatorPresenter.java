package com.example.operator;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.YysFactorBean;
import com.example.entity.YysQuanyiBean;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.operator.adapter.YysFactorAdapter;
import com.example.operator.adapter.YysQuanyiAdapter;

import java.util.ArrayList;
import java.util.List;

public class OperatorPresenter extends BasePresenter<OperatorView> {

    private YysFactorAdapter factorAdapter;

    public OperatorPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        List<YysFactorBean> factorList = new ArrayList<>();
        factorList.add(new YysFactorBean("月版", "10元"));
        factorList.add(new YysFactorBean("年版", "100元"));
        factorList.add(new YysFactorBean("永久", "188元"));
        factorAdapter = new YysFactorAdapter(mContext, factorList, R.layout.rv_yys_factor);
        if (getView() != null) {
            getView().loadFactor(factorAdapter);
        }

        List<YysQuanyiBean> quanyiList = new ArrayList<>();
        quanyiList.add(new YysQuanyiBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿", "200"));
        quanyiList.add(new YysQuanyiBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿手动阀手动阀手动阀阿斯蒂芬", "200"));
        quanyiList.add(new YysQuanyiBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿", "200"));
        quanyiList.add(new YysQuanyiBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿啊士大夫撒旦飞洒地方撒旦发射点", "200"));
        quanyiList.add(new YysQuanyiBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿", "200"));
        YysQuanyiAdapter quanyiAdapter = new YysQuanyiAdapter(mContext, quanyiList, R.layout.rv_yys_quanyi);
        if (getView() != null) {
            getView().loadQuanyi(quanyiAdapter);
        }

        factorAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, int position) {

            }
        });
    }
}
