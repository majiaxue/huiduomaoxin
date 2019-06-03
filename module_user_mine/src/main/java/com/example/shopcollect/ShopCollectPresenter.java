package com.example.shopcollect;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.BaseRecImageAndTextBean;
import com.example.entity.BaseStaggeredRecBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.shopcollect.adapter.ShopCollectAdapter;
import com.example.utils.DisplayUtil;
import com.example.utils.PopUtils;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.example.view.SelfDialog;
import com.example.view.SlideRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class ShopCollectPresenter extends BasePresenter<ShopCollectView> {

    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;
    private List<BaseRecImageAndTextBean> list;
    private ShopCollectAdapter shopCollectAdapter;
    private LinearLayoutManager linearLayoutManager;

    public ShopCollectPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void shopCollectRec(final SlideRecyclerView shopCollectRec) {
        list = new ArrayList<>();
        list.add(new BaseRecImageAndTextBean("班迪卡旗舰店", R.drawable.img_104));
        list.add(new BaseRecImageAndTextBean("private简约男装1", R.drawable.img_108));
        list.add(new BaseRecImageAndTextBean("private简约男装2", R.drawable.img_108));
        list.add(new BaseRecImageAndTextBean("private简约男装3", R.drawable.img_108));
        list.add(new BaseRecImageAndTextBean("private简约男装4", R.drawable.img_108));
        list.add(new BaseRecImageAndTextBean("private简约男装5", R.drawable.img_108));
        list.add(new BaseRecImageAndTextBean("private简约男装6", R.drawable.img_108));
        linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        shopCollectRec.setLayoutManager(linearLayoutManager);
        shopCollectAdapter = new ShopCollectAdapter(mContext, list, R.layout.item_shop_collect_rec);
        shopCollectRec.setAdapter(shopCollectAdapter);
        shopCollectAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "进入店铺", Toast.LENGTH_SHORT).show();
            }
        });
        shopCollectAdapter.setViewTwoOnClickListener(new MyRecyclerAdapter.ViewTwoOnClickListener() {
            @Override
            public void ViewTwoOnClick(View view1, View view2, final int position) {
                view1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //取消店铺
                        final SelfDialog selfDialog = new SelfDialog(mContext);
                        selfDialog.setTitle("提示");
                        selfDialog.setMessage("您确定要取消关注此店铺吗？");
                        selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                                list.remove(position);

                                shopCollectAdapter.notifyDataSetChanged();
                                shopCollectRec.closeMenu();
                                selfDialog.dismiss();
                                PopUtils.setTransparency(mContext, 1f);
                            }
                        });
                        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                selfDialog.dismiss();
                                shopCollectRec.closeMenu();
                                PopUtils.setTransparency(mContext, 1f);
                            }
                        });
                        PopUtils.setTransparency(mContext, 0.3f);
                        selfDialog.show();
                    }
                });
            }
        });

    }


    public void shopCollectBottomRec(RecyclerView shopCollectBottomRec) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //添加间距
        spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
        if (shopCollectBottomRec.getItemDecorationCount() == 0) {
            shopCollectBottomRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        shopCollectBottomRec.setLayoutManager(staggeredGridLayoutManager);
        List<BaseStaggeredRecBean> baseStaggeredRecList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));

        }
        BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, baseStaggeredRecList, R.layout.item_base_rec_staggered_grid);
        shopCollectBottomRec.setAdapter(baseRecStaggeredAdapter);

        baseRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(mContext, "查看商品", Toast.LENGTH_SHORT).show();
            }
        });
        baseRecStaggeredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int position) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "进店", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
