package com.example.new_guide;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

public class NewGuideActivity extends BaseActivity<NewGuideView, NewGuidePresenter> implements NewGuideView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_guide;
    }

    @Override
    public void initData() {
        includeTitle.setText("新人指导");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public NewGuideView createView() {
        return this;
    }

    @Override
    public NewGuidePresenter createPresenter() {
        return new NewGuidePresenter(this);
    }
}
