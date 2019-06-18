package com.example.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;

import com.example.module_home.R;
import com.example.utils.SPUtil;
import com.example.utils.StatusBarUtils;

/**
 * 启动页
 */
public class WelcomeActivity extends Activity {
    private boolean isFirstIn;
    private static final int TIME = 3000;
    private static final int GO_HOME = 1000;
    private static final int GO_GUIDE = 1001;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GO_HOME:
                    goHome();
                    break;
                case GO_GUIDE:
                    goGuide();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        changeStatus();
        init();
    }

    private void changeStatus() {
        StatusBarUtils.setStatusTheme(this, true, true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(StatusBarUtils.STATUS_COLOR);
        }
    }

    private void init() {
        SPUtil.getInstance(WelcomeActivity.this);
        isFirstIn = SPUtil.isFirstIn();
        if (isFirstIn) {
            handler.sendEmptyMessageDelayed(GO_HOME, TIME);
        } else {
            handler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
            SPUtil.addParm("isFirstIn", true);
        }
    }

    private void goHome() {
        Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goGuide() {
        Intent i = new Intent(WelcomeActivity.this, GuideActivity.class);
        startActivity(i);
        finish();
    }
}
