package com.example.collection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.MyCollectBean;
import com.example.collection.adapter.CollectionAdapter;
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
import okhttp3.ResponseBody;

public class CollectionPresenter extends BasePresenter<CollectionView> {
    private List<MyCollectBean.GoodsSearchResponseBean.GoodsListBean> dataList = new ArrayList<>();
    private boolean isEdit = false;
    private boolean isAllCheck = false;
    private CollectionAdapter collectionAdapter;

    public CollectionPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData(final int page) {
        Map map = MapUtil.getInstance().addParms("type", "2").addParms("currentPage", page).build();
        Observable observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.COLLECT_LIST, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("收藏列表：" + result);
                if (result != null) {
                    MyCollectBean list = JSON.parseObject(result, MyCollectBean.class);
                    if (page == 1) {
                        dataList.clear();
                    }
                    dataList.addAll(list.getGoods_search_response().getGoods_list());

                    if (collectionAdapter == null) {
                        collectionAdapter = new CollectionAdapter(mContext, dataList, R.layout.rv_collection);
                        if (getView() != null) {
                            getView().loadUI(collectionAdapter);
                        }
                    } else {
                        collectionAdapter.notifyDataSetChanged();
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

    private void check(int position) {
        if (dataList.get(position).isCheck()) {
            dataList.get(position).setCheck(false);
        } else {
            dataList.get(position).setCheck(true);
        }
        collectionAdapter.notifyDataSetChanged();

        for (int i = 0; i < dataList.size(); i++) {
            if (!dataList.get(i).isCheck()) {
                if (isAllCheck) {
                    getView().notAllCheck();
                }
                isAllCheck = false;
                return;
            }
        }
        isAllCheck = true;
        getView().allCheck();
    }

    public void deleteList() {
        List<Integer> deleteList = new ArrayList<>();
        for (int i = dataList.size() - 1; i >= 0; i--) {
            if (dataList.get(i).isCheck()) {
                deleteList.add(Integer.valueOf(dataList.get(i).getGoods_id()));
            }
        }
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).postDelete(CommonResource.FAVORITEDELETE, deleteList, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                loadData(1);
                isEdit = true;
                edit();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                isEdit = true;
                edit();
                Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
            }
        }));
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

    public void allCheck() {
        if (isAllCheck) {
            for (MyCollectBean.GoodsSearchResponseBean.GoodsListBean data : dataList) {
                data.setCheck(false);
            }
            collectionAdapter.notifyDataSetChanged();
            isAllCheck = false;
            getView().notAllCheck();
        } else {
            for (MyCollectBean.GoodsSearchResponseBean.GoodsListBean data : dataList) {
                data.setCheck(true);
            }
            collectionAdapter.notifyDataSetChanged();
            isAllCheck = true;
            getView().allCheck();
        }
    }

    public void rvClick() {
        collectionAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                if (isEdit) {
                    check(position);
                } else {
                    ARouter.getInstance().build("/module_classify/CommodityDetailsActivity").withString("goods_id", dataList.get(position).getGoods_id()).withString("type", "1").navigation();
                }
            }
        });
    }
}
