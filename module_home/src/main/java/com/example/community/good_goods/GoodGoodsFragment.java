package com.example.community.good_goods;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.community.adapter.CommendTitleAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.SpaceItemDecoration;

import butterknife.BindView;

public class GoodGoodsFragment extends BaseFragment<GoodGoodsView, GoodGoodsPresneter> implements GoodGoodsView {
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
    }

    @Override
    public void initClick() {

    }

    @Override
    public void loadTitle(CommendTitleAdapter adapter) {
        goodsCommendTitle.setAdapter(adapter);
    }

    @Override
    public GoodGoodsView createView() {
        return this;
    }

    @Override
    public GoodGoodsPresneter createPresenter() {
        return new GoodGoodsPresneter(getContext());
    }
}
