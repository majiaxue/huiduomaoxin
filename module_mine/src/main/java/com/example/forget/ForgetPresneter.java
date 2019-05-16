package com.example.forget;

import android.content.Context;
import android.widget.Toast;

import com.example.mvp.BasePresenter;

public class ForgetPresneter extends BasePresenter<ForgetView> {
    public ForgetPresneter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void commit(String phone, String code, String password, String confirmPassword) {
        if ("".equals(phone) || "".equals(code) || "".equals(password) || "".equals(confirmPassword)) {
            Toast.makeText(mContext, mContext.getResources().getString(com.example.module_base.R.string.please_input_more_info), Toast.LENGTH_SHORT).show();
        } else if (password != confirmPassword) {
            Toast.makeText(mContext, mContext.getResources().getString(com.example.module_base.R.string.password_no_same), Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}
