package com.example.invite_friends;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.bean.BannerBean;
import com.example.bean.InviteBean;
import com.example.common.CommonResource;
import com.example.module_base.ModuleBaseApplication;
import com.example.module_mine.R;
import com.example.module_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.UIHelper;
import com.example.utils.ViewToBitmap;
import com.stx.xhb.xbanner.XBanner;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.List;

import butterknife.BindView;

@Route(path = "/mine/invite_friends")
public class InviteFriendsActivity extends BaseActivity<InviteFriendsView, InviteFriendsPresenter> implements InviteFriendsView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.invite_friends_banner)
    XBanner inviteFriendsBanner;
    @BindView(R2.id.invite_friends_link)
    TextView inviteFriendsLink;
    @BindView(R2.id.invite_friends_bill)
    TextView inviteFriendsBill;
    @BindView(R2.id.invite_friends_webview1)
    WebView webView1;
    @BindView(R2.id.invite_friends_webview2)
    WebView webView2;
    @BindView(R2.id.invite_friends_webview3)
    WebView webView3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_friends;
    }

    @Override
    public void initData() {
        includeTitle.setText("邀请好友");
        ProcessDialogUtil.showProcessDialog(this);

        ModuleBaseApplication.initShare();

        WebSettings settings1 = webView1.getSettings();
        settings1.setJavaScriptEnabled(true);
        webView1.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadData(webView1);
                    }
                }, 2000);
            }
        });
        webView1.loadUrl(CommonResource.BASEURL_4001 + "/rest/share/invite?id=1&inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE));

        WebSettings settings2 = webView2.getSettings();
        settings2.setJavaScriptEnabled(true);
        webView2.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadData(webView2);
                    }
                }, 2000);
            }
        });
        webView2.loadUrl(CommonResource.BASEURL_4001 + "/rest/share/invite?id=2&inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE));

        WebSettings settings3 = webView3.getSettings();
        settings3.setJavaScriptEnabled(true);
        webView3.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                webView2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        presenter.loadData(webView3);
                    }
                }, 2000);
            }
        });
        webView3.loadUrl(CommonResource.BASEURL_4001 + "/rest/share/invite?id=3&inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE));

    }

    @Override
    public void initClick() {
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        inviteFriendsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.shareLink();
            }
        });

        inviteFriendsBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inviteFriendsBanner.getBannerCurrentItem() == 0) {
                    presenter.share(webView1);
                } else if (inviteFriendsBanner.getBannerCurrentItem() == 1) {
                    presenter.share(webView2);
                } else if (inviteFriendsBanner.getBannerCurrentItem() == 2) {
                    presenter.share(webView3);
                }
            }
        });

        inviteFriendsBanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                UIHelper.seeBigBitmap(InviteFriendsActivity.this, ((InviteBean) model).getXBannerUrl());
            }
        });
    }

    @Override
    public void loadBanner(List<InviteBean> beanList) {
        inviteFriendsBanner.setBannerData(beanList);

        inviteFriendsBanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(InviteFriendsActivity.this).load(((InviteBean) model).getXBannerUrl()).into((ImageView) view);
            }
        });
    }

    @Override
    public InviteFriendsView createView() {
        return this;
    }

    @Override
    public InviteFriendsPresenter createPresenter() {
        return new InviteFriendsPresenter(this);
    }
}
