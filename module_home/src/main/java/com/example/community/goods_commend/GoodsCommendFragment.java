package com.example.community.goods_commend;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.community.adapter.CommendTitleAdapter;
import com.example.community.adapter.GoodsCommendAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class GoodsCommendFragment extends BaseFragment<GoodsCommendView, GoodsCommendPresenter> implements GoodsCommendView {
    @BindView(R2.id.goods_commend_title)
    RecyclerView goodsCommendTitle;
    @BindView(R2.id.goods_commend_rv)
    RecyclerView goodsCommendRv;
    private LinearLayoutManager verManager;
    private GridLayoutManager gridLayoutManager;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_goods_commend;
    }

    @Override
    public void initData() {
        gridLayoutManager = new GridLayoutManager(getContext(), 4);
        goodsCommendTitle.setLayoutManager(gridLayoutManager);
        goodsCommendTitle.addItemDecoration(new SpaceItemDecoration((int) getContext().getResources().getDimension(R.dimen.dp_11), (int) getContext().getResources().getDimension(R.dimen.dp_11), 0, 0));

        verManager = new LinearLayoutManager(getContext());
        verManager.setOrientation(LinearLayoutManager.VERTICAL);
        goodsCommendRv.setLayoutManager(verManager);

        presenter.initTitle();
        presenter.initData();
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadTitle(CommendTitleAdapter adapter) {
        goodsCommendTitle.setAdapter(adapter);
    }

    @Override
    public void loadContent(GoodsCommendAdapter adapter) {
        goodsCommendRv.setAdapter(adapter);
    }

    @Override
    public GoodsCommendView createView() {
        return this;
    }

    @Override
    public GoodsCommendPresenter createPresenter() {
        return new GoodsCommendPresenter(getContext());
    }
}
