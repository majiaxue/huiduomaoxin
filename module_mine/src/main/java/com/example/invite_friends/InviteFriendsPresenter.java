package com.example.invite_friends;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebView;

import com.example.bean.InviteBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.example.utils.ViewToBitmap;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

public class InviteFriendsPresenter extends BasePresenter<InviteFriendsView> {

    private List<InviteBean> beanList = new ArrayList<>();


    public InviteFriendsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(WebView webView) {
        Bitmap bitmap = ViewToBitmap.createBitmap3(webView, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
//        Bitmap bitmap2 = ViewToBitmap.createBitmap3(webView2, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
//        Bitmap bitmap3 = ViewToBitmap.createBitmap3(webView3, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
        beanList.add(new InviteBean(bitmap));
//        beanList.add(new InviteBean(bitmap2));
//        beanList.add(new InviteBean(bitmap3));
        if (getView() != null && beanList.size() == 3) {
            ProcessDialogUtil.dismissDialog();
            getView().loadBanner(beanList);
        }
    }

    public void shareLink() {
        UMWeb umWeb = new UMWeb(CommonResource.BASEURL_4001 + "/rest/share/register?inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE));
        umWeb.setTitle("您有一个邀请信息");
        umWeb.setThumb(new UMImage(mContext, R.mipmap.icon_app));
        umWeb.setDescription("赶紧加入领取高佣吧！！！");
        new ShareAction((Activity) mContext).withMedia(umWeb)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(shareListener).open();

    }

    public void share(WebView webView) {
        Bitmap bitmap = ViewToBitmap.createBitmap3(webView, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, bitmap))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {
            LogUtil.e("start:" + share_media.toString());
        }

        @Override
        public void onResult(SHARE_MEDIA share_media) {
            LogUtil.e("result:" + share_media.toString());
        }

        @Override
        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
            LogUtil.e("onError:" + share_media.toString() + "-----------" + throwable.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };

}
