package com.example.invite_friends;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.example.bean.BannerBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class InviteFriendsPresenter extends BasePresenter<InviteFriendsView> {

    private List<BannerBean.RecordsBean> beanList;

    public InviteFriendsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        //轮播图
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9005).getDataWithout(CommonResource.USERSBANNER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                BannerBean records = JSON.parseObject(result, BannerBean.class);
                beanList = records.getRecords();
                if (getView() != null) {
                    getView().loadBanner(beanList);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void shareLink(int bannerCurrentItem) {
//        UMWeb umWeb = new UMWeb("https://www.baidu.com/");
//        umWeb.setTitle("我的分享");
//        umWeb.setThumb(new UMImage(mContext, R.mipmap.icon_app));
//        umWeb.setDescription("这是一条分享信息");
//        new ShareAction((Activity)mContext).withMedia(umWeb)
//                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
//                .setCallback(shareListener).open();

        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_invite_friends, null);
        new ShareAction((Activity) mContext)
                .withShareBoardDirection(view, Gravity.TOP)
                .withText("自定义view")
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(shareListener).open();
    }

    public void share(int bannerCurrentItem) {
        ShareBoardConfig config = new ShareBoardConfig();
        config.setTitleText("我要分享")
                .setTitleTextColor(Color.parseColor("#ff0000"))
                .setMenuItemTextColor(Color.parseColor("#999999"))
                .setMenuItemIconPressedColor(Color.parseColor("#000000"))
                .setMenuItemBackgroundColor(Color.parseColor("#fd3c15"),Color.parseColor("#008577"))
                .setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_ROUNDED_SQUARE,(int)mContext.getResources().getDimension(R.dimen.dp_20))
                .setCancelButtonText("我不想分享了");


        new ShareAction((Activity) mContext)
                .withMedia(new UMImage(mContext, R.drawable.no_goods))
                .withText("hello")
                .setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE)
                .setCallback(shareListener).open(config);
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

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media) {

        }
    };
}
