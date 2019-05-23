package com.example.goodscollection;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

/**
 * 商品收藏
 */
@Route(path = "/module_user_mine/GoodsCollectionActivity")
public class GoodsCollectionActivity extends BaseActivity<GoodsCollectionView, GoodsCollectionPresenter> implements GoodsCollectionView {


    @BindView(R2.id.goods_collection_go)
    TextView goodsCollectionGo;
    @BindView(R2.id.goods_collection_rec)
    RecyclerView goodsCollectionRec;
    @BindView(R2.id.goods_collection_check_all)
    ImageView goodsCollectionCheckAll;
    @BindView(R2.id.goods_collection_delete)
    TextView goodsCollectionDelete;
    @BindView(R2.id.goods_collection_bottom)
    LinearLayout goodsCollectionBottom;
    @BindView(R2.id.goods_collection_back)
    ImageView goodsCollectionBack;
    @BindView(R2.id.goods_collection_state)
    TextView goodsCollectionState;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_collection;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        goodsCollectionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        goodsCollectionGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //去商品页
                Toast.makeText(GoodsCollectionActivity.this, "点击了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public GoodsCollectionView createView() {
        return this;
    }

    @Override
    public GoodsCollectionPresenter createPresenter() {
        return new GoodsCollectionPresenter(this);
    }

}
