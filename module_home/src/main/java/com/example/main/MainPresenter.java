package com.example.main;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.example.hairring.HairRingFragment;
import com.example.home.HomeFragment;
import com.example.mine.MineFragment;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.classify.ClassifyFragment;
import com.example.superbrand.SuperBrandFragment;
import com.example.utils.AppManager;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

import java.util.Locale;

public class MainPresenter extends BasePresenter<MainView> {
    //触碰标识
    private long exitTime = 0;

    private FragmentManager fragmentManager;
    private HairRingFragment hairRingFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private ClassifyFragment classifyFragment;
    private SuperBrandFragment superBrandFragment;

    public MainPresenter(Context context) {
        super(context);
    }

    public void loadData(FragmentManager fragmentManager, int resId) {
        this.fragmentManager = fragmentManager;
        hairRingFragment = new HairRingFragment();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        classifyFragment = new ClassifyFragment();
        superBrandFragment = new SuperBrandFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(resId, hairRingFragment)
                .add(resId, homeFragment)
                .add(resId, mineFragment)
                .add(resId, classifyFragment)
                .add(resId, superBrandFragment);
        transaction.show(homeFragment)
                .hide(hairRingFragment)
                .hide(mineFragment)
                .hide(classifyFragment)
                .hide(superBrandFragment)
                .commit();

    }

    public void click(int resId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int position = 0;
        if (resId == R.id.main_home) {
            transaction.show(homeFragment)
                    .hide(hairRingFragment)
                    .hide(classifyFragment)
                    .hide(mineFragment)
                    .hide(superBrandFragment)
                    .commit();
            position = 0;
        } else if (resId == R.id.main_classify) {
            transaction.show(superBrandFragment)
                    .hide(classifyFragment)
                    .hide(hairRingFragment)
                    .hide(homeFragment)
                    .hide(mineFragment)
                    .commit();
            position = 1;
        } else if (resId == R.id.main_mine) {
            transaction.show(mineFragment)
                    .hide(hairRingFragment)
                    .hide(superBrandFragment)
                    .hide(classifyFragment)
                    .hide(homeFragment)
                    .commit();
            position = 4;
        }
        if (getView() != null) {
            getView().clickBottom(position);
        }
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(mContext, "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getInstance().AppExit();
            System.exit(0);
        }
    }

    @Override
    protected void onViewDestroy() {

    }

    public void initTinker() {
        Beta.applyTinkerPatch(mContext.getApplicationContext(), Environment.getExternalStorageDirectory().getAbsolutePath() + "/fltk/patch/patch_signed_7zip.apk");
        //自动检查更新
        Beta.checkUpgrade();
        //是否允许自动下载
        Beta.canAutoDownloadPatch = false;
        //是否允许自动合成补丁
        Beta.canAutoPatch = true;
        //是否显示弹窗提示用户重启
        Beta.canNotifyUserRestart = true;


        //用户主动下载补丁文件
        Beta.downloadPatch();
//        //用户主动合成补丁
//        Beta.applyDownloadedPatch();

        /**
         * true表示初始化时自动检查升级;
         * false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
        Beta.autoCheckUpgrade = true;
        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
        Beta.upgradeCheckPeriod = 60 * 1000;
        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
        Beta.showInterruptedStrategy = true;

        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                LogUtil.e("---------->补丁下载地址：" + patchFile);
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                LogUtil.e("---------->" + String.format(Locale.getDefault(), "%s %d%%",
                        Beta.strNotificationDownloading,
                        (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)));
            }

            @Override
            public void onDownloadSuccess(String msg) {
                LogUtil.e("---------->补丁下载成功");
            }

            @Override
            public void onDownloadFailure(String msg) {
                LogUtil.e("---------->补丁下载失败");
            }

            @Override
            public void onApplySuccess(String msg) {
                LogUtil.e("---------->补丁应用成功");
            }

            @Override
            public void onApplyFailure(String msg) {
                LogUtil.e("----------->补丁应用失败");
            }

            @Override
            public void onPatchRollback() {
                LogUtil.e("----------->补丁回滚");
            }
        };

    }

}
