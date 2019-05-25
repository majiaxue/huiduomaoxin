package com.example.collection;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.collection.adapter.CollectionAdapter;
import com.example.entity.BaseRecBean;
import com.example.module_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

public class CollectionPresenter extends BasePresenter<CollectionView> {
    private List<BaseRecBean> dataList;
    private boolean isEdit = false;
    private CollectionAdapter collectionAdapter;

    public CollectionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        dataList = new ArrayList<>();
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000", false));
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000", false));
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000", false));
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000", false));
        dataList.add(new BaseRecBean("http://e.hiphotos.baidu.com/image/pic/item/4610b912c8fcc3cef70d70409845d688d53f20f7.jpg", "【3瓶装】小紫瓶保湿乳液", "领券减50元", "39", "119", "6543", "10000", false));
        collectionAdapter = new CollectionAdapter(mContext, dataList, R.layout.rv_collection);
        if (getView() != null) {
            getView().loadUI(collectionAdapter);
        }

        collectionAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        check(index);
                    }
                });
            }
        });
    }

    private void check(int position) {
        if (dataList.get(position).isCheck()) {
            dataList.get(position).setCheck(false);
        } else {
            dataList.get(position).setCheck(true);
        }
        collectionAdapter.notifyDataSetChanged();
    }

    public void deleteList() {
        for (int i = dataList.size() - 1; i >= 0; i--) {
            if (dataList.get(i).isCheck()) {
                dataList.remove(i);
            }
        }
        collectionAdapter.notifyDataSetChanged();
    }

    public void edit() {
        if (isEdit) {
            isEdit = false;
            getView().toFinish();
        } else {
            isEdit = true;
            getView().toEdit();
        }
        collectionAdapter.setEdit(isEdit);
    }
}
