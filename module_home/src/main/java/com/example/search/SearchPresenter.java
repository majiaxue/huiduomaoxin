package com.example.search;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.view.FlowLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/22
 * Describe:
 */
public class SearchPresenter extends BasePresenter<SearchView> {

    //    private String[] mVals = new String[]{"渔夫帽", "渔夫帽蓝色", "渔夫帽湖蓝色 ", "渔夫帽蓝色",
//            "渔夫帽湖蓝色", "渔夫帽湖蓝色"};
    private List<String> mVals = new ArrayList<>();
    private TextView searchTextView;
    private String[] titleArr = {"淘宝","拼多多","京东"};

    public SearchPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTabLayout(final TabLayout searchTab) {
        for (String title : titleArr) {
            searchTab.addTab(searchTab.newTab().setText(title));
        }

        searchTab.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //了解源码得知 线的宽度是根据 tabView的宽度来设置的
                    LinearLayout mTabStrip = (LinearLayout) searchTab.getChildAt(0);

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

    public void searchFlowLayout(FlowLayout searchFlowLayout) {
        mVals.add("渔夫帽");
        mVals.add("渔夫帽蓝色");
        mVals.add("V20");
        mVals.add("华为mate20");
        mVals.add("儿童大礼包");

        for (int i = 0; i < mVals.size(); i++) {
            searchTextView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.search_text_view, searchFlowLayout, false);
            searchTextView.setText(mVals.get(i));
            final int finalI = i;
            searchTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "i:" + mVals.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });

            searchFlowLayout.addView(searchTextView);
        }
    }

    public void searchEdit(EditText searchEdit, FlowLayout searchFlowLayout) {
        String s = searchEdit.getText().toString();
        searchTextView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.search_text_view, searchFlowLayout, false);
        mVals.add(s);
        searchTextView.setText(s);
        if (s != null && !s.equals("")) {
            searchFlowLayout.addView(searchTextView);
        } else {
            Toast.makeText(mContext, "请输入商品信息", Toast.LENGTH_SHORT).show();
        }
    }
}
