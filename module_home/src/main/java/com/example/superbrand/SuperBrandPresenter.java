package com.example.superbrand;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.superbrand.adapter.SuperBrandRecAdapter;
import com.example.superbrand.bean.SuperBrandBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/6/5
 * Describe:
 */
public class SuperBrandPresenter extends BasePresenter<SuperBrandView> {

    private String[] titles = {"精选", "美妆", "男装", "女装", "食品", "居家", "海鲜", "电器", "美妆"};


    public SuperBrandPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTabLayout(final TabLayout superBrandTab) {
        for (String title : titles) {
            superBrandTab.addTab(superBrandTab.newTab().setText(title));
        }

        superBrandTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) superBrandTab.getChildAt(0);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField =
                                tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding
                        // 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params =
                                (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (Exception e) {

                }
            }
        });

    }

    public void setSuperBrandRec(RecyclerView superBrandRec){
        List<SuperBrandBean> superBrandBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            superBrandBeanList.add(new SuperBrandBean(R.drawable.img_lankou,"兰蔻","平均返佣10%"));
            superBrandBeanList.add(new SuperBrandBean(R.drawable.img_yiyezi,"一叶子","平均返佣10%"));
            superBrandBeanList.add(new SuperBrandBean(R.drawable.img_adaofu,"阿道夫","平均返佣10%"));
            superBrandBeanList.add(new SuperBrandBean(R.drawable.img_oulanya,"欧莱雅","平均返佣10%"));
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext,4, LinearLayoutManager.VERTICAL, false);
        superBrandRec.setLayoutManager(gridLayoutManager);
        SuperBrandRecAdapter superBrandRecAdapter = new SuperBrandRecAdapter(mContext, superBrandBeanList, R.layout.item_super_brand_rec);
        superBrandRec.setAdapter(superBrandRecAdapter);

        superBrandRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
              Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
