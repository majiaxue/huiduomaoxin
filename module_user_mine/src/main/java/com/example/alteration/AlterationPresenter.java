package com.example.alteration;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.alteration.adapter.AlterationAdapter;
import com.example.alteration.bean.AlterationBean;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class AlterationPresenter extends BasePresenter<AlterationView> {

    private List<AlterationBean.RBean> rBeanList = new ArrayList<>();

    public AlterationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void alterationRec(final RecyclerView alterationRec) {
        Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithout(CommonResource.RETURNTABLE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {

            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("AlterationResult------->" + result);
//                final List<AlterationBean> dataList = JSON.parseArray(result, AlterationBean.class);
//                Gson gson = new Gson();
////                List<AlterationBean> dataList = gson.fromJson(result, new TypeToken<List<AlterationBean>>() {
////                }.getType());
                AlterationBean alterationBean = JSON.parseObject(result, new TypeReference<AlterationBean>() {
                }.getType());
                if (alterationBean != null && alterationBean.getR().size() != 0) {
                    rBeanList.clear();
                    rBeanList.addAll(alterationBean.getR());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                    alterationRec.setLayoutManager(linearLayoutManager);
                    AlterationAdapter alterationAdapter = new AlterationAdapter(mContext, rBeanList, R.layout.item_alteration_rec);
                    alterationRec.setAdapter(alterationAdapter);
                    alterationAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                        @Override
                        public void ViewOnClick(View view, final int position) {
                            view.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    //查看详情
                                    ARouter.getInstance()
                                            .build("/module_user_mine/RefundParticularsActivity")
                                            .withString("orderSn", rBeanList.get(position).getOrderSn())
                                            .withInt("position",position)
                                            .navigation();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("AlterationErrorMsg------->" + errorMsg);
            }
        }));

    }
}
