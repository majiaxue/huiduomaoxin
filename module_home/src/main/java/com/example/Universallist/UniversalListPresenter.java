package com.example.Universallist;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.Universallist.adapter.HotRecommendRecAdapter;
import com.example.Universallist.adapter.UniversalListRecAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.GoodChoiceBean;
import com.example.bean.HotRecommendBean;
import com.example.bean.UniversalListBean;
import com.example.common.CommonResource;
import com.example.home.adapter.GoodChoiceRecAdapter;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnTripartiteCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.PopUtils;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class UniversalListPresenter extends BasePresenter<UniversalListView> {

    private String itemType;
    private List<UniversalListBean.DataBean> dataBeanList = new ArrayList<>();
    private List<HotRecommendBean.DataBean> hotList = new ArrayList<>();

    public UniversalListPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void universalList(final RecyclerView universalListRec, int position, final int page) {
        if (position == 1) {
            //淘抢购
            itemType = "item";
        } else if (position == 2) {
            //9.9包邮
            itemType = "tqg";
        } else if (position == 3) {
            //聚划算
            itemType = "ju";
        }
        Map map = MapUtil.getInstance().addParms("page", page).addParms("itemtype", itemType).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSGETITEMS, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                ProcessDialogUtil.dismissDialog();
                LogUtil.e("UniversalListPresenterResult" + result);
                UniversalListBean universalListBean = JSON.parseObject(result, new TypeReference<UniversalListBean>() {
                }.getType());
                if (universalListBean != null && universalListBean.getData() != null) {
                    if (page == 1) {
                        dataBeanList.clear();
                    }
                    dataBeanList.addAll(universalListBean.getData());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
                    UniversalListRecAdapter universalListRecAdapter = new UniversalListRecAdapter(mContext, dataBeanList, R.layout.item_universal_list_rec);
                    universalListRec.setLayoutManager(gridLayoutManager);
                    universalListRec.setAdapter(universalListRecAdapter);
                    universalListRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                    .withString("para", dataBeanList.get(position).getNum_iid())
                                    .withString("shoptype", "1").navigation();
                        }
                    });
                }
                getView().finishRefresh();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                ProcessDialogUtil.dismissDialog();
                getView().finishRefresh();
            }
        }));
    }

    public void hotRecommend(final RecyclerView universalListRec, final int page,int type){
        Map map = MapUtil.getInstance().addParms("sale_type", type).build();
        Observable data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getData(CommonResource.TBKGOODSSALESLIST, map);
        RetrofitUtil.getInstance().toSubscribe(data, new OnTripartiteCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                ProcessDialogUtil.dismissDialog();
                LogUtil.e("hotRecommend：" + result);
                HotRecommendBean hotRecommendBean = JSON.parseObject(result, new TypeReference<HotRecommendBean>() {
                }.getType());
                if (hotRecommendBean != null && hotRecommendBean.getData().size() != 0) {
                    if (page == 1){
                        hotList.clear();
                    }
                    hotList.addAll(hotRecommendBean.getData());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2, LinearLayoutManager.VERTICAL, false);
                    universalListRec.setLayoutManager(gridLayoutManager);
                    HotRecommendRecAdapter hotRecommendRecAdapter = new HotRecommendRecAdapter(mContext, hotList, R.layout.item_universal_list_rec);
                    universalListRec.setAdapter(hotRecommendRecAdapter);
                    hotRecommendRecAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {
                            if (!TextUtils.isEmpty(SPUtil.getToken())) {
                                ARouter.getInstance().build("/module_classify/TBCommodityDetailsActivity")
                                        .withString("para", hotList.get(position).getItemid())
                                        .withString("shoptype", "1").navigation();
                            } else {
                                //是否登录
                                PopUtils.isLogin(mContext);
                            }

                        }
                    });

                }
                getView().finishRefresh();
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                ProcessDialogUtil.dismissDialog();
                getView().finishRefresh();
            }
        }));
    }
}
