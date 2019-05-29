package com.example.user_classify;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mvp.BaseFragment;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;
import com.stx.xhb.xbanner.XBanner;

import butterknife.BindView;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:商城分类
 */
public class ClassifyFragment extends BaseFragment<ClassifyView, ClassifyPresenter> implements ClassifyView {
    @BindView(R2.id.user_classify_search)
    LinearLayout userClassifySearch;
    @BindView(R2.id.user_classify_msg_img)
    ImageView userClassifyMsgImg;
    @BindView(R2.id.user_classify_message)
    LinearLayout userClassifyMessage;
    @BindView(R2.id.user_classify_expand)
    ExpandableListView userClassifyExpand;
    @BindView(R2.id.user_classify_x_banner)
    XBanner userClassifyXBanner;
    @BindView(R2.id.user_classify_rec)
    RecyclerView userClassifyRec;

    public static int position = -1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_user_classify;
    }

    @Override
    public void initData() {
        //二级列表
        presenter.setExpand(userClassifyExpand, userClassifyXBanner);
        //xBanner
        presenter.setXBanner(userClassifyXBanner);

        //商品二级列表
        presenter.setRightRec(userClassifyRec);
    }

    @Override
    public void initClick() {
        userClassifySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到搜索页面
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });

        userClassifyMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userClassifyExpand.setSelectedGroup(3);
                presenter.renovate();
            }
        });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && position != -1) {
            userClassifyExpand.setSelectedGroup(position);
            userClassifyExpand.setSelectedChild(position, 1, false);
            presenter.renovate();
            position = -1;
        }
    }

    @Override
    public ClassifyView createView() {
        return this;
    }

    @Override
    public ClassifyPresenter createPresenter() {
        return new ClassifyPresenter(getContext());
    }
}
