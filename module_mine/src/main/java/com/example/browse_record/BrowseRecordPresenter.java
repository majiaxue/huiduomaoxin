package com.example.browse_record;

import android.content.Context;

import com.example.browse_record.adapter.BrowseRecordAdapter;
import com.example.entity.BaseRecBean;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class BrowseRecordPresenter extends BasePresenter<BrowseRecordView> {

    private List<BaseRecBean> dataList;

    public BrowseRecordPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        dataList = new ArrayList<>();
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000"));
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000"));
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000"));
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000"));
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000"));
        BrowseRecordAdapter recordAdapter = new BrowseRecordAdapter(mContext, dataList, R.layout.item_base_rec);
        if (getView() != null) {
            getView().loadUI(recordAdapter);
        }
    }
}
