package com.example.shop_home;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;

import butterknife.BindView;

@Route(path = "/module_classify/tshop_home")
public class TShopHomeActivity extends BaseActivity<TShopHomeView, TShopHomePresenter> implements TShopHomeView {
    @BindView(R2.id.tshop_home_webview)
    WebView webView;
    @Autowired(name = "url")
    String url;

    private boolean isJump = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tshop_home;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);

        presenter.login();
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        webView.loadUrl("https://hqrdq.m.tmall.com/?spm=a222m.7628550.0.0");
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.e("==========>" + url);
                if (url.contains("id=")) {
                    LogUtil.e("2222222222222");
                    String[] split = url.split("&");
                    String[] split1 = split[0].split("=");
                    long id = Long.valueOf(split1[1]);
                    ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                            .withString("type", "0")
                            .withLong("goods_id", id)
                            .navigation();
                }
                try {
                    if (!url.startsWith("https://")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                } catch (Exception e) {
                    return false;
                }

                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void initClick() {

    }

    @Override
    public TShopHomeView createView() {
        return this;
    }

    @Override
    public TShopHomePresenter createPresenter() {
        return new TShopHomePresenter(this);
    }
}
