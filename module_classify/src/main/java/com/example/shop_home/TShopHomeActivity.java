package com.example.shop_home;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.baichuan.android.trade.AlibcTrade;
import com.alibaba.baichuan.android.trade.callback.AlibcTradeCallback;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alibaba.baichuan.android.trade.model.AlibcShowParams;
import com.alibaba.baichuan.android.trade.model.AlibcTaokeParams;
import com.alibaba.baichuan.android.trade.model.OpenType;
import com.alibaba.baichuan.android.trade.model.TradeResult;
import com.alibaba.baichuan.android.trade.page.AlibcBasePage;
import com.alibaba.baichuan.android.trade.page.AlibcDetailPage;
import com.alibaba.baichuan.android.trade.page.AlibcPage;
import com.alibaba.baichuan.android.trade.page.AlibcShopPage;
import com.example.module_classify.R;
import com.example.module_classify.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

@Route(path = "/module_classify/tshop_home")
public class TShopHomeActivity extends BaseActivity<TShopHomeView, TShopHomePresenter> implements TShopHomeView {
    @BindView(R2.id.tshop_home_webview)
    com.ali.auth.third.ui.webview.TaeWebView webView;
    @Autowired(name = "shopId")
    String shopId;

    private String goodsId = "";
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

//        webView.loadUrl("https://hqrdq.m.tmall.com/?spm=a222m.7628550.0.0");

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.e("==========>" + url);
                String id = "";
                if (url.contains("?id=")) {
                    String[] split = url.split("&");
                    String[] split1 = split[0].split("=");
                    id = split1[1];
                    if (goodsId.equals(id)) {
                        return false;
                    }
                    goodsId = id;
                } else if (url.contains("&id=")) {
                    String[] split = url.split("&id=");
                    if (split[1].contains("&")) {
                        String[] split1 = split[1].split("&");
                        id = split1[0];
                    } else {
                        id = split[1];
                    }
                    if (goodsId.equals(id)) {
                        return false;
                    }
                    goodsId = id;
                }
                ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                        .withString("shoptype", "0")
                        .withString("para", id)
                        .navigation();
                try {
                    if (!url.startsWith("https://")) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }

                view.loadUrl(url);
                return false;
            }
        };

        WebChromeClient webChromeClient = new WebChromeClient();

        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);

        AlibcTaokeParams alibcTaokeParams = new AlibcTaokeParams("57328044", "mm_26632322_6858406_23810104", "mm_26632322_6858406_23810104");

        //提供给三方传递配置参数
        Map<String, String> exParams = new HashMap<>();
        exParams.put(AlibcConstants.ISV_CODE, "appisvcode");

        //实例化店铺打开page
        AlibcBasePage shopPage = new AlibcShopPage(shopId);

        //设置页面打开方式
        AlibcShowParams showParams = new AlibcShowParams(OpenType.H5, false);

        //使用百川sdk提供默认的Activity打开detail
        AlibcTrade.show(this, webView, webViewClient, webChromeClient, shopPage, showParams, alibcTaokeParams, exParams, new AlibcTradeCallback() {
            @Override
            public void onTradeSuccess(TradeResult tradeResult) {
                LogUtil.e("------>" + tradeResult.toString());
            }

            @Override
            public void onFailure(int i, String s) {

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
