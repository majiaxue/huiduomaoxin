package com.example.invite_friends;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bean.BannerBean;
import com.example.bean.Records;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class InviteFriendsPresenter extends BasePresenter<InviteFriendsView> {

    private List<BannerBean> beanList;

    public InviteFriendsPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        //轮播图
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi2(mContext).getDataWithout(CommonResource.USERSBANNER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                Records<BannerBean> records = JSON.parseObject(result, new TypeReference<Records<BannerBean>>() {
                }.getType());
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
}
