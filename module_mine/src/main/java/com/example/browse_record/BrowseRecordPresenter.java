package com.example.browse_record;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.MyCollectBean;
import com.example.browse_record.adapter.BrowseRecordAdapter;
import com.example.common.CommonResource;
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

public class BrowseRecordPresenter extends BasePresenter<BrowseRecordView> {
    private List<MyCollectBean> dataList = new ArrayList<>();
    private BrowseRecordAdapter recordAdapter;

    public BrowseRecordPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page) {
        Map map = MapUtil.getInstance().addParms("type", "1").addParms("current", page).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.BROWSE_LIST, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("浏览记录：" + result);
                if (result==null){
                    if (getView()!=null){
                        getView().loadFinish();
                    }
                }
                if (result != null) {
                    if (page == 1) {
                        dataList.clear();
                    }
                    dataList.addAll(JSON.parseArray(result, MyCollectBean.class));
                    LogUtil.e("changdu:"+dataList.size());
                    if (recordAdapter == null) {
                        recordAdapter = new BrowseRecordAdapter(mContext, dataList, R.layout.item_base_rec);
                        if (getView() != null) {
                            getView().loadUI(recordAdapter);
                        }
                    } else {
                        recordAdapter.notifyDataSetChanged();
                    }
                }

                if (getView() != null) {
                    getView().loadFinish();
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                if (getView() != null) {
                    getView().loadFinish();
                }
            }
        }));
    }

    public void click() {
        recordAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                ARouter.getInstance().build("/module_classify/CommodityDetailsActivity")
                        .withString("goods_id", dataList.get(position).getGoodsId()+"")
                        .navigation();
            }
        });
    }
}
