package com.example.logisticsinformation;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.HotSaleBean;
import com.example.common.CommonResource;
import com.example.logisticsinformation.adapter.LogisticsInforMationAdapter;
import com.example.logisticsinformation.bean.LogisticsInforMationBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.LogUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public class LogisticsInformationPresenter extends BasePresenter<LogisticsInformationView> {

    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;
    private List<HotSaleBean.DataBean> commendList = new ArrayList<>();
    public LogisticsInformationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }
    //物流信息
    public void logisticsInformationMessageRec(RecyclerView logisticsInformationMessageRec){
        List<LogisticsInforMationBean> list = new ArrayList<>();
        list.add(new LogisticsInforMationBean("[收货地址]河南省郑州市金水区金成国际广场3号楼204室"));
        list.add(new LogisticsInforMationBean("【温州市】快件已从温州市中转部发出；发往郑州中转"));
        list.add(new LogisticsInforMationBean("【温州市】快件已到达温州中转"));
        list.add(new LogisticsInforMationBean("【温州市】快件已从平阳发出"));
        list.add(new LogisticsInforMationBean("【温州市】贵州平阳公司 已揽收"));
        list.add(new LogisticsInforMationBean("包裹正在等待揽收"));
        list.add(new LogisticsInforMationBean("您的包裹已出库"));
        list.add(new LogisticsInforMationBean("您的订单处理中"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        logisticsInformationMessageRec.setLayoutManager(linearLayoutManager);
        LogisticsInforMationAdapter logisticsInforMationAdapter = new LogisticsInforMationAdapter(mContext, list, R.layout.item_logistics_information_rec);
        logisticsInformationMessageRec.setAdapter(logisticsInforMationAdapter);



    }
    //推荐
    public void logisticsInformationRec(final RecyclerView logisticsInformationRec){
//        Map map = MapUtil.getInstance().addParms("searchInfo", "俩件套").build();
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.HOTNEWSEARCH);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                HotSaleBean hotSaleBean = JSON.parseObject(result, new TypeReference<HotSaleBean>() {
                }.getType());
                commendList.clear();
                commendList.addAll(hotSaleBean.getData());
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                //添加间距
                spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
                if (logisticsInformationRec.getItemDecorationCount() == 0) {
                    logisticsInformationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
                }
                logisticsInformationRec.setLayoutManager(staggeredGridLayoutManager);
                BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, commendList, R.layout.item_base_rec_staggered_grid);
                logisticsInformationRec.setAdapter(baseRecStaggeredAdapter);

                baseRecStaggeredAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                baseRecStaggeredAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
                    @Override
                    public void ViewOnClick(View view, final int index) {
                        view.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(mContext, "position:" + index, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("errorMsg------->"+errorMsg);
            }
        }));

    }
}
