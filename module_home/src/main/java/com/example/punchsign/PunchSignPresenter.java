package com.example.punchsign;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;

public class PunchSignPresenter extends BasePresenter<PunchSignView> {

    public PunchSignPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void popGuiZe(TextView punchSignGuiZe){
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.pop_guize, null);
        PopUtils.viewPopBottom(punchSignGuiZe,mContext, inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
            @Override
            public void setOnPop(PopupWindow pop) {

            }
        });
    }
}
