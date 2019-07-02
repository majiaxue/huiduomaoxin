package com.example.main;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.entity.EventBusBean;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragmentActivity;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * 主页面
 */
@Route(path = "/home/main")
public class MainActivity extends BaseFragmentActivity<MainView, MainPresenter> implements MainView {

    private final String[] perms = {Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    private final int REQUEST_CODE = 0xa123;
    @BindView(R2.id.main_home)
    RadioButton mainHome;
    @BindView(R2.id.main_classify)
    RadioButton mainClassify;
    @BindView(R2.id.main_user_mall)
    LinearLayout mainUserMall;
    @BindView(R2.id.main_hairring)
    RadioButton mainHairring;
    @BindView(R2.id.main_mine)
    RadioButton mainMine;
    @BindView(R2.id.main_group)
    RadioGroup mainGroup;
    @Autowired(name = "type")
    String type;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        getWindow().setFormat(PixelFormat.TRANSPARENT);
        initPermission();
        presenter.initTinker();
        presenter.checkUp();
        presenter.loadData(getSupportFragmentManager(), R.id.main_frame);
        if ("login".equals(type)) {
            presenter.click(R.id.main_mine);
        }
    }

    @Override
    public void initClick() {
        mainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                presenter.click(checkedId);
            }
        });

        mainUserMall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SPUtil.getToken() == null || "".equals(SPUtil.getToken())) {
                    Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                } else {
                    ARouter.getInstance().build("/module_user_store/UserActivity").navigation();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().post(new EventBusBean("login"));
    }

    @Override
    public void clickBottom(int position) {

    }

    private void initPermission() {
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, perms, REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE) {
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_GRANTED) {

                } else {
                    finish();
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            presenter.exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public MainView createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter(this);
    }
}
