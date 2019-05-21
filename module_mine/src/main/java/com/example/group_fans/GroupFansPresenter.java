package com.example.group_fans;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.entity.GroupFansBean;
import com.example.group_fans.adapter.GroupFansRvAdapter;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class GroupFansPresenter extends BasePresenter<GroupFansView> {
    private List<GroupFansBean> dataList = new ArrayList<>();

    public GroupFansPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        dataList.add(new GroupFansBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿", "时间：2018-1-1"));
        dataList.add(new GroupFansBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿", "时间：2018-1-1"));
        dataList.add(new GroupFansBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿", "时间：2018-1-1"));
        dataList.add(new GroupFansBean("http://e.hiphotos.baidu.com/image/pic/item/b8014a90f603738d6d8d0d65bd1bb051f919ecb6.jpg", "阿斯顿", "时间：2018-1-1"));
        GroupFansRvAdapter adapter = new GroupFansRvAdapter(mContext, dataList, R.layout.rv_group_fans);
        if (getView() != null) {
            getView().loadUI(adapter);
        }
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
