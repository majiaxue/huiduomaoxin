package com.example.predict.pdd;

import android.view.View;
import android.widget.TextView;

import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseFragment;

import butterknife.BindView;
import butterknife.Unbinder;

public class PddFragment extends BaseFragment<PddView, PddPresenter> implements PddView {
    @BindView(R2.id.predict_total_money)
    TextView predictTotalMoney;
    @BindView(R2.id.predict_ben_jiesuan)
    TextView predictBenJiesuan;
    @BindView(R2.id.predict_shang_jiesuan)
    TextView predictShangJiesuan;
    @BindView(R2.id.predict_temp)
    View predictTemp;
    @BindView(R2.id.predict_ben_fukuan)
    TextView predictBenFukuan;
    @BindView(R2.id.predict_shang_fukuan)
    TextView predictShangFukuan;
    @BindView(R2.id.predict_temp2)
    View predictTemp2;
    @BindView(R2.id.predict_fukuanshu_jin)
    TextView predictFukuanshuJin;
    @BindView(R2.id.predict_yongjin_jin)
    TextView predictYongjinJin;
    @BindView(R2.id.predict_temp3)
    View predictTemp3;
    @BindView(R2.id.predict_fukuanshu_zuo)
    TextView predictFukuanshuZuo;
    @BindView(R2.id.predict_yongjin_zuo)
    TextView predictYongjinZuo;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_predict;
    }

    @Override
    public void initData() {
        predictTotalMoney.setText("1000");
    }

    @Override
    public void initClick() {

    }

    @Override
    public PddView createView() {
        return null;
    }

    @Override
    public PddPresenter createPresenter() {
        return null;
    }
}
