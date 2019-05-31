package com.example.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.view.FlowLayout;

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

    public SearchPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

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
