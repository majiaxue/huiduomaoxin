package com.example.group_fans;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.example.bean.UserInfoBean;
import com.example.common.CommonResource;
import com.example.entity.GroupFansBean;
import com.example.group_fans.adapter.GroupFansRvAdapter;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

public class GroupFansPresenter extends BasePresenter<GroupFansView> {
    private List<UserInfoBean> dataList = new ArrayList<>();
    private GroupFansRvAdapter adapter;


    public GroupFansPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page) {
        Map map = MapUtil.getInstance().addParms("level", "0").addParms("current", page).addParms("size", 2).build();
        LogUtil.e("token:" + SPUtil.getToken());
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi4(mContext).getHead(CommonResource.GROUP_FANS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                List<UserInfoBean> userInfoBeans = JSON.parseArray(result, UserInfoBean.class);
                LogUtil.e("团队粉丝：" + result);
                if (page == 1) {
                    dataList.clear();
                }
                dataList.addAll(userInfoBeans);
                if (adapter == null) {
                    adapter = new GroupFansRvAdapter(mContext, dataList, R.layout.rv_group_fans);
                    if (getView() != null) {
                        getView().loadUI(adapter);
                    }
                } else {
                    adapter.notifyDataSetChanged();
                    getView().loadFinish();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e(errorCode + "-------" + errorMsg);
            }
        }));


    }

    public boolean isShouldHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right
                    && ev.getY() > top && ev.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
