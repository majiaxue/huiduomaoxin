package com.example.mineorder.stayappraise;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.common.CommonResource;
import com.example.mineorder.bean.MineOrderBean;
import com.example.mineorder.stayappraise.adapter.StayAppraiseParentAdapter;
import com.example.module_user_mine.R;
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

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class StayAppraisePresenter extends BasePresenter<StayAppraiseView> {

    private List<MineOrderBean.OrderListBean> listBeans = new ArrayList<>();

    public StayAppraisePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void stayAppraiseRec(final RecyclerView stayAppraiseRec) {
        Map map = MapUtil.getInstance().addParms("status", 3).build();
        Observable<ResponseBody> headWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHead(CommonResource.ORDERSTATUS, map, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(headWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("stayAppraiseResult------->" + result);
                MineOrderBean MineOrderBean = JSON.parseObject(result, new TypeReference<MineOrderBean>() {
                }.getType());
                if (MineOrderBean != null) {
                    listBeans.clear();
                    listBeans.addAll(MineOrderBean.getOrderList());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    stayAppraiseRec.setLayoutManager(linearLayoutManager);
                    StayAppraiseParentAdapter stayAppraiseParentAdapter = new StayAppraiseParentAdapter(mContext, listBeans, R.layout.item_stay_appraise_parent);
                    stayAppraiseRec.setAdapter(stayAppraiseParentAdapter);

                    stayAppraiseParentAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, int index) {
                            Toast.makeText(mContext, "去店铺" + index, Toast.LENGTH_SHORT).show();
                        }
                    });

                    stayAppraiseParentAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(RecyclerView parent, View view, int position) {

                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("stayAppraiseErrorMsg------->" + errorMsg);
            }
        }));
    }
}
