package com.example.freecharge;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.TxtUtil;

import butterknife.BindView;

/**
 * 今日免单
 */
@Route(path = "/module_home/FreeChargeActivity")
public class FreeChargeActivity extends BaseActivity<FreeChargeView, FreeChargePresenter> implements FreeChargeView {


    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.free_charge_activity_text)
    TextView freeChargeActivityText;
    @BindView(R2.id.free_charge_activity_view)
    View freeChargeActivityView;
    @BindView(R2.id.free_charge_activity)
    LinearLayout freeChargeActivity;
    @BindView(R2.id.free_charge_look_back_text)
    TextView freeChargeLookBackText;
    @BindView(R2.id.free_charge_look_back_view)
    View freeChargeLookBackView;
    @BindView(R2.id.free_charge_look_back)
    LinearLayout freeChargeLookBack;
    @BindView(R2.id.free_charge_rec)
    RecyclerView freeChargeRec;

    @Override
    public int getLayoutId() {
        return R.layout.activity_free_charge;
    }

    @Override
    public void initData() {
        includeTitle.setText("0元抢购免单");
        TxtUtil.txtJianbian(freeChargeActivityText, "#febc0d", "#fb3912");
    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //免单活动
        freeChargeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TxtUtil.txtJianbian(freeChargeActivityText, "#febc0d", "#fb3912");
                TxtUtil.txtJianbian(freeChargeLookBackText, "#999999", "#999999");
                freeChargeActivityView.setVisibility(View.VISIBLE);
                freeChargeLookBackView.setVisibility(View.INVISIBLE);
            }
        });
        //往期回顾
        freeChargeLookBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TxtUtil.txtJianbian(freeChargeLookBackText, "#febc0d", "#fb3912");
                TxtUtil.txtJianbian(freeChargeActivityText, "#999999", "#999999");
                freeChargeLookBackView.setVisibility(View.VISIBLE);
                freeChargeActivityView.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public FreeChargeView createView() {
        return this;
    }

    @Override
    public FreeChargePresenter createPresenter() {
        return new FreeChargePresenter(this);
    }

}
