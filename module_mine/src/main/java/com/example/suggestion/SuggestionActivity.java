package com.example.suggestion;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/mine/suggestion")
public class SuggestionActivity extends BaseActivity<SuggestionView, SuggestionPresenter> implements SuggestionView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.suggestion_history)
    TextView suggestionHistory;
    @BindView(R2.id.suggestion_edit)
    EditText suggestionEdit;
    @BindView(R2.id.suggestion_nums)
    TextView suggestionNums;
    @BindView(R2.id.suggestion_btn)
    TextView suggestionBtn;

    @Override
    public int getLayoutId() {
        return R.layout.activity_suggestion;
    }

    @Override
    public void initData() {
        includeTitle.setText("意见反馈");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        suggestionHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToHistory();
            }
        });

        suggestionEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 300) {
                    s.delete(300, s.length());
                }
                suggestionNums.setText(s.length() + "/300");
            }
        });
    }

    @Override
    public SuggestionView createView() {
        return this;
    }

    @Override
    public SuggestionPresenter createPresenter() {
        return new SuggestionPresenter(this);
    }
}
