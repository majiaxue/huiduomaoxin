package com.example.user_shopping_cart;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.BaseStaggeredRecBean;
import com.example.mvp.BasePresenter;
import com.example.user_shopping_cart.adapter.CartParentRecAdapter;
import com.example.user_shopping_cart.bean.CartChildBean;
import com.example.user_shopping_cart.bean.CartParentBean;
import com.example.user_store.R;
import com.example.utils.DisplayUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.example.view.CustomerExpandableListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class ShoppingCartPresenter extends BasePresenter<ShoppingCartView> {

    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;

    public ShoppingCartPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setShoppingCartRecommendRec(RecyclerView shoppingCartRecommendRec) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //添加间距
        spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
        if (shoppingCartRecommendRec.getItemDecorationCount() == 0) {
            shoppingCartRecommendRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        shoppingCartRecommendRec.setLayoutManager(staggeredGridLayoutManager);
        List<BaseStaggeredRecBean> baseStaggeredRecList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));

        }
        BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, baseStaggeredRecList, R.layout.item_base_rec_staggered_grid);
        shoppingCartRecommendRec.setAdapter(baseRecStaggeredAdapter);

        baseRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "点击:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        baseRecStaggeredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + index, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void setShoppingCartRec(RecyclerView shoppingCartRec){
        List<CartParentBean> plist = new ArrayList<>();
        plist.add(new CartParentBean(false, "private简约男装"));
        plist.add(new CartParentBean(false, "班迪卡旗舰店"));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        shoppingCartRec.setLayoutManager(linearLayoutManager);
        CartParentRecAdapter cartParentRecAdapter = new CartParentRecAdapter(mContext, plist, R.layout.item_cart_parent);
        shoppingCartRec.setAdapter(cartParentRecAdapter);

    }


}
