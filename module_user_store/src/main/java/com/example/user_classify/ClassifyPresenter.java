package com.example.user_classify;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.adapter.MyRecyclerAdapter;
import com.example.bean.BannerBean;
import com.example.bean.ClassifyBean;
import com.example.bean.Records;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_classify.adapter.UserLeftRvAdapter;
import com.example.user_classify.adapter.UserRightRecAdapter;
import com.example.user_store.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class ClassifyPresenter extends BasePresenter<ClassifyView> {
    private List<ClassifyBean.Records> leftList = new ArrayList<>();
    private List<ClassifyBean.Records.RecordsSecond> rightList = new ArrayList<>();
    private UserLeftRvAdapter leftRvAdapter;
    private UserRightRecAdapter rightAdapter;
    private List<BannerBean> bannerBeanList;


    public ClassifyPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9001).getDataWithout(CommonResource.ALLCATEGORT);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                ClassifyBean classifyBean = new Gson().fromJson(result, new TypeToken<ClassifyBean>() {
                }.getType());

                leftList = classifyBean.getRecords();
                leftList.get(0).setSelect(true);
                rightList.addAll(leftList.get(0).getChildren());

                leftRvAdapter = new UserLeftRvAdapter(mContext, leftList, R.layout.rv_left_classify);
                rightAdapter = new UserRightRecAdapter(mContext, rightList, R.layout.item_rec_group);
                if (getView() != null) {
                    getView().loadRv(leftRvAdapter, rightAdapter);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void setLeftRvCLick() {
        leftRvAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                for (int i = 0; i < leftList.size(); i++) {
                    leftList.get(i).setSelect(i == position ? true : false);
                }
                if (position == 0) {
                    getView().showBanner();
                } else {
                    getView().hideBanner();
                }
                rightList.clear();
                rightList.addAll(leftList.get(position).getChildren());
                leftRvAdapter.notifyDataSetChanged();
                rightAdapter.notifyDataSetChanged();
            }
        });
    }

    public void setXBanner() {
        Observable<ResponseBody> observable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9005).getDataWithout(CommonResource.USERSBANNER);
        RetrofitUtil.getInstance().toSubscribe(observable, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                Records<BannerBean> records = JSON.parseObject(result, new TypeReference<Records<BannerBean>>() {
                }.getType());
                bannerBeanList = records.getRecords();
//                String data = result;
//                BaseEntityList<BannerBean> baseEntity = new Gson().fromJson(data, new TypeToken<BaseEntityList<BannerBean>>() {
//                }.getType());
//                bannerBeanList = baseEntity.getData();
                if (getView() != null) {
                    getView().loadBanner(bannerBeanList);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void formHomeNavbar(int position) {
        for (int i = 0; i < leftList.size(); i++) {
            leftList.get(i).setSelect(i == position ? true : false);
        }
        rightList.clear();
        if (leftList.get(position).getChildren() != null && leftList.get(position).getChildren().size() > 0) {
            rightList.addAll(leftList.get(position).getChildren());
            rightAdapter.notifyDataSetChanged();
        }
        leftRvAdapter.notifyDataSetChanged();
    }
}
