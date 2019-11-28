package com.example.web_detail;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;

import butterknife.BindView;

@Route(path = "/module_home/WebDetailActivity")
public class WebDetailActivity extends BaseActivity<WebDetailView, WebDetailPresenter> implements WebDetailView {
    @BindView(R2.id.web_detail_webview)
    WebView webView;

    @Autowired(name = "url")
    String url;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_detail;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webView.loadUrl(url);

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        };

        WebChromeClient webChromeClient = new WebChromeClient();

        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
    }

    @Override
    public void initClick() {

    }

    @Override
    public WebDetailView createView() {
        return this;
    }

    @Override
    public WebDetailPresenter createPresenter() {
        return new WebDetailPresenter(this);
    }
}
