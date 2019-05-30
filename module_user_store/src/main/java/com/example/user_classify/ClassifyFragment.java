package com.example.user_classify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.common.CommonResource;
import com.example.entity.EventBusBean2;
import com.example.mvp.BaseFragment;
import com.example.user_classify.adapter.UserLeftRvAdapter;
import com.example.user_store.R;
import com.example.user_store.R2;
import com.example.utils.LogUtil;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:商城分类
 */
public class ClassifyFragment extends BaseFragment<ClassifyView, ClassifyPresenter> implements ClassifyView {
    @BindView(R2.id.user_classify_back)
    ImageView mBack;
    @BindView(R2.id.user_classify_search)
    TextView userClassifySearch;
    @BindView(R2.id.user_classify_msg_img)
    ImageView userClassifyMsgImg;
    @BindView(R2.id.user_classify_message)
    LinearLayout userClassifyMessage;
    @BindView(R2.id.user_classify_rv_left)
    RecyclerView leftRv;
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        leftRv.setLayoutManager(layoutManager);
        //二级列表
        presenter.setLeftRv();
        //xBanner
        presenter.setXBanner(userClassifyXBanner);

        //商品二级列表
        presenter.setRightRec(userClassifyRec);
    }

    @Override
    public void initClick() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusBean2(CommonResource.USER_BACK, 0));
            }
        });

        userClassifySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //到搜索页面
                ARouter.getInstance().build("/module_home/SearchActivity").navigation();
            }
        });

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && position != -1) {
            presenter.formHomeNavbar(position);
            position = -1;
        }
    }

    @Override
    public void loadLeftRv(UserLeftRvAdapter adapter) {
        leftRv.setAdapter(adapter);
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
