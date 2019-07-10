package com.example.punchsign;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/module_home/PunchSignActivity")
public class PunchSignActivity extends BaseActivity<PunchSignView, PunchSignPresenter> implements PunchSignView {

    @BindView(R2.id.punch_sign_image)
    ImageView punchSignImage;
    @BindView(R2.id.punch_sign_gui_ze)
    TextView punchSignGuiZe;

    @Override
    public int getLayoutId() {
        return R.layout.activity_punch_sign;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initClick() {
        punchSignImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        punchSignGuiZe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击弹出规则

            }
        });
    }

    @Override
    public PunchSignView createView() {
        return this;
    }

    @Override
    public PunchSignPresenter createPresenter() {
        return new PunchSignPresenter(this);
    }

}
