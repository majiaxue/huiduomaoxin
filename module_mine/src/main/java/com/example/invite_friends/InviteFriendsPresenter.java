package com.example.invite_friends;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.bean.InviteBean;
import com.example.common.CommonResource;
import com.example.invite_friends.adapter.InviteVpAdapter;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.utils.LogUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.QRCode;
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
    private List<View> list = new ArrayList<>();


    public InviteFriendsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 0x111) {
                ProcessDialogUtil.dismissDialog();
                getView().loadBanner(beanList);
            }
        }

    };

    public void loadData() {
        Bitmap qrImage1 = QRCode.createQRImage(CommonResource.BASEURL_4001 + CommonResource.INVITE_ERWEIMA + "?inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE), (int) mContext.getResources().getDimension(R.dimen.dp_193), (int) mContext.getResources().getDimension(R.dimen.dp_193));
        View view1 = LayoutInflater.from(mContext).inflate(R.layout.vp_invite_view1, null);
        ImageView img1 = view1.findViewById(R.id.invite_friends_erweima1);
        Glide.with(mContext).load(qrImage1).into(img1);
        View view2 = LayoutInflater.from(mContext).inflate(R.layout.vp_invite_view2, null);
        ImageView img2 = view2.findViewById(R.id.invite_friends_erweima2);
        Glide.with(mContext).load(qrImage1).into(img2);
        View view3 = LayoutInflater.from(mContext).inflate(R.layout.vp_invite_view3, null);
        ImageView img3 = view3.findViewById(R.id.invite_friends_erweima3);
        Glide.with(mContext).load(qrImage1).into(img3);
        View view4 = LayoutInflater.from(mContext).inflate(R.layout.vp_invite_view4, null);
        ImageView img4 = view4.findViewById(R.id.invite_friends_erweima4);
        Glide.with(mContext).load(qrImage1).into(img4);

        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);
        InviteVpAdapter inviteVpAdapter = new InviteVpAdapter(list);
        if (getView() != null) {
            getView().loadVP(inviteVpAdapter, list.size());
        }
    }

    public void shareLink() {
        UMWeb umWeb = new UMWeb(CommonResource.BASEURL_4001 + "/rest/share/register?inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE));
        umWeb.setTitle("您有一个邀请信息");
        umWeb.setThumb(new UMImage(mContext, R.drawable.icon_app));
        umWeb.setDescription("赶紧加入领取高佣吧！！！");
        new ShareAction((Activity) mContext).withMedia(umWeb)
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(shareListener).open();

    }

    public void share(int position) {
        Bitmap bitmap = ViewToBitmap.createBitmap3(list.get(position % list.size()), list.get(position % list.size()).getWidth(), list.get(position % list.size()).getHeight());
        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, bitmap))
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
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

    public void initPic(ImageView inviteFriendsErweima1, ImageView inviteFriendsErweima2, ImageView inviteFriendsErweima3, ImageView inviteFriendsErweima4) {
        Bitmap qrImage1 = QRCode.createQRImage(CommonResource.BASEURL_4001 + CommonResource.INVITE_ERWEIMA + "?inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE), (int) mContext.getResources().getDimension(R.dimen.dp_193), (int) mContext.getResources().getDimension(R.dimen.dp_193));
        Glide.with(mContext).load(qrImage1).into(inviteFriendsErweima1);
        Bitmap qrImage2 = QRCode.createQRImage(CommonResource.BASEURL_4001 + CommonResource.INVITE_ERWEIMA + "?inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE), (int) mContext.getResources().getDimension(R.dimen.dp_185), (int) mContext.getResources().getDimension(R.dimen.dp_185));
        Glide.with(mContext).load(qrImage2).into(inviteFriendsErweima2);
        Bitmap qrImage3 = QRCode.createQRImage(CommonResource.BASEURL_4001 + CommonResource.INVITE_ERWEIMA + "?inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE), (int) mContext.getResources().getDimension(R.dimen.dp_190), (int) mContext.getResources().getDimension(R.dimen.dp_190));
        Glide.with(mContext).load(qrImage3).into(inviteFriendsErweima3);
        Bitmap qrImage4 = QRCode.createQRImage(CommonResource.BASEURL_4001 + CommonResource.INVITE_ERWEIMA + "?inviteCode=" + SPUtil.getStringValue(CommonResource.USER_INVITE), (int) mContext.getResources().getDimension(R.dimen.dp_185), (int) mContext.getResources().getDimension(R.dimen.dp_185));
        Glide.with(mContext).load(qrImage4).into(inviteFriendsErweima4);
        if (getView() != null) {
            getView().loadQRCode();
        }
    }

    public void createList(final RelativeLayout rela1, final RelativeLayout rela2, final RelativeLayout rela3, final RelativeLayout rela4) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    Bitmap bitmap1 = ViewToBitmap.createBitmap3(rela1, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
                    Bitmap bitmap2 = ViewToBitmap.createBitmap3(rela2, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
                    Bitmap bitmap3 = ViewToBitmap.createBitmap3(rela3, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
                    Bitmap bitmap4 = ViewToBitmap.createBitmap3(rela4, ViewToBitmap.getScreenWidth(mContext), ViewToBitmap.getScreenHeight(mContext));
                    beanList.add(new InviteBean(bitmap1));
                    beanList.add(new InviteBean(bitmap2));
                    beanList.add(new InviteBean(bitmap3));
                    beanList.add(new InviteBean(bitmap4));
                    mHandler.sendEmptyMessage(0x111);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
