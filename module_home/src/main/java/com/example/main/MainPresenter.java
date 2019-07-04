package com.example.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alibaba.baichuan.android.trade.adapter.login.AlibcLogin;
import com.alibaba.baichuan.android.trade.callback.AlibcLoginCallback;
import com.alibaba.fastjson.JSON;
import com.example.bean.CheckUpBean;
import com.example.common.CommonResource;
import com.example.hairring.HairRingFragment;
import com.example.home.HomeFragment;
import com.example.mine.MineFragment;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.classify.ClassifyFragment;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.superbrand.SuperBrandFragment;
import com.example.utils.AppManager;
import com.example.utils.LogUtil;
import com.example.utils.PopUtils;
import com.example.utils.SPUtil;
import com.example.view.SelfDialog;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Callback;

public class MainPresenter extends BasePresenter<MainView> {
    //触碰标识
    private long exitTime = 0;

    private FragmentManager fragmentManager;
    private HairRingFragment hairRingFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private ClassifyFragment classifyFragment;
    private SuperBrandFragment superBrandFragment;
    private ProgressBar mProgress;
    private AlertDialog alertDialog;
    private String clientVersion;
    private static final String savePath = "/sdcard/fltk/apk"; // apk保存到SD卡的路径
    private static final String saveFileName = savePath + "/fltk.apk"; // 完整路径名

    private static final String patchPath = "/sdcard/fltk/patch"; // apk保存到SD卡的路径
    private static final String patchFileName = patchPath + "/patch_signed_7zip.apk"; // 完整路径名
    private static final int DOWNLOADING = 1; // 表示正在下载
    private static final int DOWNLOADED = 2; // 下载完毕
    private static final int DOWNLOAD_FAILED = 3; // 下载失败
    private static final int PATCHEND = 4;

    private int progress; // 下载进度
    private boolean cancelFlag = false; // 取消下载标志位
    private CheckUpBean checkUpBean;


    public MainPresenter(Context context) {
        super(context);
    }

    /**
     * 更新UI的handler
     */
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOADED:
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    installAPK();
                    break;
                case DOWNLOAD_FAILED:
                    Toast.makeText(mContext, "下载失败", Toast.LENGTH_LONG).show();
                    break;
                case PATCHEND:
                    if (alertDialog != null)
                        alertDialog.dismiss();
                    Beta.canAutoPatch = true;
                    Beta.applyDownloadedPatch();
                    break;
                default:
                    break;
            }
        }
    };

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
        Beta.applyTinkerPatch(mContext.getApplicationContext(), patchFileName);
        //自动检查更新
//        Beta.checkUpgrade();
        //是否允许自动下载
//        Beta.canAutoDownloadPatch = true;
        //是否允许自动合成补丁
        Beta.canAutoPatch = true;
        //是否显示弹窗提示用户重启
//        Beta.canNotifyUserRestart = true;


        //用户主动下载补丁文件
//        Beta.downloadPatch();
//        //用户主动合成补丁
        Beta.applyDownloadedPatch();

        /**
         * true表示初始化时自动检查升级;
         * false表示不会自动检查升级,需要手动调用Beta.checkUpgrade()方法;
         */
//        Beta.autoCheckUpgrade = true;
        /**
         * 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
         */
//        Beta.upgradeCheckPeriod = 60 * 1000;
        /**
         * 点击过确认的弹窗在APP下次启动自动检查更新时会再次显示;
         */
//        Beta.showInterruptedStrategy = true;

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

    public void checkUp() {
        getVersionInfo();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9005).getDataWithout(CommonResource.CHECKUP);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("检查更新：" + result);
                checkUpBean = JSON.parseObject(result, CheckUpBean.class);
                String[] split = clientVersion.split("\\.");
                String version = checkUpBean.getVersion();
                if (version != null) {
                    String[] split1 = version.split("\\.");
                    if (Integer.valueOf(split[0]) < Integer.valueOf(split1[0])) {
                        final SelfDialog selfDialog = new SelfDialog(mContext);
                        selfDialog.setTitle("更新提示");
                        selfDialog.setMessage(checkUpBean.getContent());
                        selfDialog.setYesOnclickListener("立即升级", new SelfDialog.onYesOnclickListener() {
                            @Override
                            public void onYesClick() {
                                writeToDisk(checkUpBean.getUrl());
                                showDialog();
                                selfDialog.dismiss();
                            }
                        });

                        selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                            @Override
                            public void onNoClick() {
                                if ("0".equals(checkUpBean.getIsForce())) {
                                    selfDialog.dismiss();
                                } else {
                                    selfDialog.dismiss();
                                    AppManager.getInstance().AppExit();
                                }
                            }
                        });

                        selfDialog.show();
                        selfDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                PopUtils.setTransparency(mContext, 1f);
                            }
                        });

                        PopUtils.setTransparency(mContext, 0.3f);
                    } else {
                        if (Double.valueOf(clientVersion) < Double.valueOf(checkUpBean.getList().get(0).getVersion())) {
                            final SelfDialog selfDialog = new SelfDialog(mContext);
                            selfDialog.setTitle("更新提示");
                            selfDialog.setMessage(checkUpBean.getContent());
                            selfDialog.setYesOnclickListener("立即升级", new SelfDialog.onYesOnclickListener() {
                                @Override
                                public void onYesClick() {
                                    downLoadPatch(checkUpBean.getList().get(0).getUrl());
                                    showDialog();
                                    selfDialog.cancel();
                                }
                            });

                            selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                                @Override
                                public void onNoClick() {
                                    selfDialog.cancel();
                                }
                            });

                            selfDialog.show();
                        }
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("更新：" + errorCode + "------------" + errorMsg);
            }
        }));
    }

    private void getVersionInfo() {

        PackageManager pm = mContext.getPackageManager();

        try {
            // 0代表拿所有的信息 packageInfo 是一个bean对象 是对整个清单文件的封装
            // ApplicationInfo是PackageInfo的子集
            PackageInfo packageInfo = pm.getPackageInfo(mContext.getPackageName(), 0);
            clientVersion = packageInfo.versionName;
            LogUtil.e("当前版本：" + clientVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("正在更新");
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.dialog_update, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        builder.setView(v);
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    public void writeToDisk(final String apkUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(apkUrl);
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();

                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String apkFile = saveFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);

                    int count = 0;
                    byte buf[] = new byte[64];

                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if (numread <= 0) {
                            // 下载完成通知安装
                            mHandler.sendEmptyMessage(DOWNLOADED);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelFlag); // 点击取消就停止下载.

                    fos.close();
                    is.close();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 下载完成后自动安装apk
     */
    public void installAPK() {
        File apkFile = new File(saveFileName);
        if (!apkFile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri contentUri = FileProvider.getUriForFile(mContext, "com.lxy.taobaoke.provider", apkFile);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        }
        mContext.startActivity(intent);
    }

    private void downLoadPatch(final String patchUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(patchUrl);
                    HttpURLConnection conn = (HttpURLConnection) url
                            .openConnection();
                    conn.connect();

                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();

                    File file = new File(patchPath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String apkFile = patchFileName;
                    File ApkFile = new File(apkFile);
                    FileOutputStream fos = new FileOutputStream(ApkFile);

                    int count = 0;
                    byte buf[] = new byte[64];

                    do {
                        int numread = is.read(buf);
                        count += numread;
                        progress = (int) (((float) count / length) * 100);
                        // 更新进度
                        mHandler.sendEmptyMessage(DOWNLOADING);
                        if (numread <= 0) {
                            // 下载完成通知安装
                            mHandler.sendEmptyMessage(PATCHEND);
                            break;
                        }
                        fos.write(buf, 0, numread);
                    } while (!cancelFlag); // 点击取消就停止下载.

                    fos.close();
                    is.close();
                } catch (Exception e) {
                    mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
