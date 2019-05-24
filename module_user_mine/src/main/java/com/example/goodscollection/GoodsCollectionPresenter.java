package com.example.goodscollection;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.BaseStaggeredRecBean;
import com.example.goodscollection.adapter.GoodsCollectionRecAdapter;
import com.example.goodscollection.bean.GoodsCollectionRecBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.utils.DisplayUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/23
 * Describe:
 */
public class GoodsCollectionPresenter extends BasePresenter<GoodsCollectionView> {

    private List<GoodsCollectionRecBean> recBeanList;
    private boolean isCompile = false;
    private boolean isCheckAll = false;
    private boolean flag = true;
    private GoodsCollectionRecAdapter goodsCollectionRecAdapter;
    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;

    public GoodsCollectionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setGoodsCollectionRec(RecyclerView goodsCollectionRec) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        goodsCollectionRec.setLayoutManager(linearLayoutManager);
        recBeanList = new ArrayList<>();
        recBeanList.add(new GoodsCollectionRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店", false));
        recBeanList.add(new GoodsCollectionRecBean(R.drawable.img_109, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥18.90", "12345人付款", "97%好评", "古田兔旗舰店", false));
        recBeanList.add(new GoodsCollectionRecBean(R.drawable.img_110, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥15.90", "12345人付款", "97%好评", "星辰时尚坊", false));

        goodsCollectionRecAdapter = new GoodsCollectionRecAdapter(mContext, recBeanList, R.layout.item_goods_collection_rec);
        if (getView() != null) {
            getView().loadUI(goodsCollectionRecAdapter);
        }
        goodsCollectionRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        goodsCollectionRecAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, final int position) {
                //点击选中
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = true;
                        check(position);
                    }
                });
                //进入店铺
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    public void setGoodsCollectionBottomRec(RecyclerView goodsCollectionBottomRec) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //添加间距
        spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
        if (goodsCollectionBottomRec.getItemDecorationCount() == 0) {
            goodsCollectionBottomRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        goodsCollectionBottomRec.setLayoutManager(staggeredGridLayoutManager);
        List<BaseStaggeredRecBean> baseStaggeredRecList = new ArrayList<>();
        baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
        baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
        baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));
        BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, baseStaggeredRecList, R.layout.item_base_rec_staggered_grid);
        goodsCollectionBottomRec.setAdapter(baseRecStaggeredAdapter);

        baseRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
        baseRecStaggeredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int position) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //选中recycler的item
    private void check(int position) {

        if (recBeanList.get(position).isCheck()) {
            recBeanList.get(position).setCheck(false);
        } else {
            recBeanList.get(position).setCheck(true);
        }

        goodsCollectionRecAdapter.notifyDataSetChanged();

        for (int i = 0; i < recBeanList.size(); i++) {
            if (!recBeanList.get(i).isCheck()) {
                isCheckAll = false;
                flag = false;
            }

        }

        if (getView() != null) {
            getView().isCheckAll(flag);
        }

    }

    //编辑
    public void compile() {
        if (isCompile) {
            isCompile = false;
            getView().isCompile(isCompile);
        } else {
            isCompile = true;
            getView().isCompile(isCompile);
        }
        goodsCollectionRecAdapter.setCompile(isCompile);
    }

    //点击选中全部
    public void checkAll(ImageView goodsCollectionCheckAll) {
        if (isCheckAll) {
            for (int i = 0; i < recBeanList.size(); i++) {
                recBeanList.get(i).setCheck(false);
            }
            goodsCollectionCheckAll.setImageResource(R.drawable.vghfgdg);
            isCheckAll = false;
        } else {
            for (int i = 0; i < recBeanList.size(); i++) {
                recBeanList.get(i).setCheck(true);
            }
            goodsCollectionCheckAll.setImageResource(R.drawable.ghftyf);
            isCheckAll = true;
        }
        goodsCollectionRecAdapter.notifyDataSetChanged();
    }

    //删除
    public void deleteList() {
        for (int i = recBeanList.size() - 1; i >= 0; i--) {
            if (recBeanList.get(i).isCheck()) {
                recBeanList.remove(i);
            }
        }
        goodsCollectionRecAdapter.notifyDataSetChanged();
    }
}
