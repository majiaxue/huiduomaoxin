package com.example.predict;

import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragmentActivity;

import butterknife.BindView;

@Route(path = "/mine/predict")
public class PredictActivity extends BaseFragmentActivity<PredictView, PredictPresenter> implements PredictView {
    @BindView(R2.id.predict_back)
    ImageView mBack;
    //    @BindView(R2.id.predict_radiogroup)
//    RadioGroup predictRadiogroup;
    @BindView(R2.id.predict_tb)
    TextView predictTb;
    @BindView(R2.id.predict_pdd)
    TextView predictPdd;
    @BindView(R2.id.predict_jd)
    TextView predictJd;
    @BindView(R2.id.predict_sc)
    TextView predictSc;

    @Override
    public int getLayoutId() {
        return R.layout.activity_predict;
    }

    @Override
    public void initData() {
        FragmentManager manager = getSupportFragmentManager();
        presenter.loadData(manager, R.id.predict_frame);
    }

    @Override
    public void initClick() {
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        predictTb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.change(0);
            }
        });

        predictPdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.change(1);
            }
        });

        predictJd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.change(2);
            }
        });

        predictSc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.change(3);
            }
        });
    }

    @Override
    public void changeType(int type) {
        predictJd.setTextColor(Color.parseColor(type == 2 ? "#ffffff" : "#222222"));
        predictTb.setTextColor(Color.parseColor(type == 0 ? "#ffffff" : "#222222"));
        predictPdd.setTextColor(Color.parseColor(type == 1 ? "#ffffff" : "#222222"));
        predictSc.setTextColor(Color.parseColor(type == 3 ? "#ffffff" : "#222222"));

        predictTb.setBackgroundResource(type == 0 ? R.drawable.predict_xuan_left : 0);
        predictPdd.setBackgroundResource(type == 1 ? R.drawable.predict_xuan : 0);
        predictJd.setBackgroundResource(type == 2 ? R.drawable.predict_xuan : 0);
        predictSc.setBackgroundResource(type == 3 ? R.drawable.predict_xuan_right : 0);
    }

    @Override
    public PredictView createView() {
        return this;
    }

    @Override
    public PredictPresenter createPresenter() {
        return new PredictPresenter(this);
    }
}
