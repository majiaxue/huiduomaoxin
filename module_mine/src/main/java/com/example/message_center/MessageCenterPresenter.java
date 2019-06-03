package com.example.message_center;

import android.content.Context;
import android.content.Intent;

import com.example.entity.MessageCenterBean;
import com.example.message_center.adapter.MessageCenterAdapter;
import com.example.message_detail.MessageDetailActivity;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class MessageCenterPresenter extends BasePresenter<MessageCenterView> {

    private List<MessageCenterBean> dataList;

    public MessageCenterPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        dataList = new ArrayList<>();
        dataList.add(new MessageCenterBean("0", "我的快递", "我的消息", "已付款", "【国朝价】学生气质洋气俏皮小个子法国小众裙俩件套", "申通快递", "123456789", "http://h.hiphotos.baidu.com/image/pic/item/d000baa1cd11728bd0649c9dc2fcc3cec2fd2cc7.jpg"));
        dataList.add(new MessageCenterBean("1", "安全险", "系统消息", "账户安全险免费续保", "投保成功"));
        dataList.add(new MessageCenterBean("0", "我的快递", "我的消息", "已付款", "【国朝价】学生气质洋气俏皮小个子法国小众裙俩件套", "申通快递", "123456789", "http://h.hiphotos.baidu.com/image/pic/item/d000baa1cd11728bd0649c9dc2fcc3cec2fd2cc7.jpg"));
        dataList.add(new MessageCenterBean("1", "安全险", "系统消息", "账户安全险免费续保", "投保成功"));
        dataList.add(new MessageCenterBean("0", "我的快递", "我的消息", "已付款", "【国朝价】学生气质洋气俏皮小个子法国小众裙俩件套", "申通快递", "123456789", "http://h.hiphotos.baidu.com/image/pic/item/d000baa1cd11728bd0649c9dc2fcc3cec2fd2cc7.jpg"));
        MessageCenterAdapter centerAdapter = new MessageCenterAdapter(mContext, dataList);
        if (getView() != null) {
            getView().loadRv(centerAdapter);
        }
    }

    public void jumpToDetail(int position) {
        mContext.startActivity(new Intent(mContext, MessageDetailActivity.class));
    }
}
