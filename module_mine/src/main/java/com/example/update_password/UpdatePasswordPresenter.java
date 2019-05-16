package com.example.update_password;

import android.content.Context;
import android.widget.Toast;

import com.example.module_mine.R;
import com.example.mvp.BasePresenter;

public class UpdatePasswordPresenter extends BasePresenter<UpdatePasswordView> {
    public UpdatePasswordPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void commit(String first, String second) {
        if ("".equals(first) || "".equals(second)) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.password_cannot_empty), Toast.LENGTH_SHORT).show();
        } else if (!first.equals(second)) {
            Toast.makeText(mContext, mContext.getResources().getString(R.string.password_no_same), Toast.LENGTH_SHORT).show();
        } else {

        }
    }
}
