package com.example.replace_phone;

import android.content.Context;
import android.widget.Toast;

import com.example.module_mine.R;
import com.example.mvp.BasePresenter;

public class ReplacePhonePresenter extends BasePresenter<ReplacePhoneView> {
    public ReplacePhonePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void getCodeNum() {
        getView().getCodeSuccess();
    }

    public void commit(String oldNum, String newNum, String code) {
        if ("".equals(oldNum) || "".equals(newNum) || "".equals(code)) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.please_input_more_info), Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}
