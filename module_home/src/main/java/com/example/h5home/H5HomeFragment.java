package com.example.h5home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.webkit.WebView;

import com.example.main.MainActivity;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;
import com.example.utils.StatusBarUtils;

import butterknife.BindView;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * h5首页
 */
public class H5HomeFragment extends BaseFragment<H5HomeView, H5HomePresenter> implements H5HomeView {

    @BindView(R2.id.home_h5_web)
    WebView homeH5Web;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_h5_home;
    }

    @Override
    public void initData() {
//        StatusBarUtils.setAndroidNativeLightStatusBar(getActivity(), false);
//        StatusBarUtils.setStatusTheme(getActivity(), true, true);
        presenter.check();
        presenter.initWebView(homeH5Web);
    }

    @Override
    public void initClick() {

    }

    @Override
    public H5HomeView createView() {
        return this;
    }

    @Override
    public H5HomePresenter createPresenter() {
        return new H5HomePresenter(getContext());
    }


}
