package com.example.user_mine;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.common.CommonResource;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.user_mine.bean.ApplicationBean;
import com.example.user_mine.bean.BrowsingBean;
import com.example.user_mine.bean.GoodsCollectCountBean;
import com.example.user_mine.bean.ShopCollectCountBean;
import com.example.utils.LogUtil;
import com.example.utils.MapUtil;
import com.example.utils.SPUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/16
 * Describe:
 */
public class MinePresenter extends BasePresenter<MineView> {

    private List<ShopCollectCountBean.RecordsBean> shopCollectList = new ArrayList<>();
    private List<GoodsCollectCountBean.RecordsBean> goodsCountList = new ArrayList<>();

    public MinePresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void goodsCollectionCount() {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.GOODSCOLLECTION, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("setGoodsCollectionRec----->" + result);

                GoodsCollectCountBean goodsCollectionRecBean = JSON.parseObject(result, new TypeReference<GoodsCollectCountBean>() {
                }.getType());

                if (goodsCollectionRecBean != null) {
                    goodsCountList.clear();
                    goodsCountList.addAll(goodsCollectionRecBean.getRecords());

                    if (goodsCountList.size() != 0 || goodsCountList != null) {
                        if (getView() != null) {
                            getView().goodsCollectionCount(goodsCountList.size());
                        }
                    } else {
                        if (getView() != null) {
                            getView().goodsCollectionCount(0);
                        }
                    }

                } else {
                    if (getView() != null) {
                        getView().goodsCollectionCount(0);
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("setGoodsCollectionRecError------->" + errorCode);
            }
        }));


    }

    public void shopCollectCount() {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.SELLERPAGE, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(final String result, String msg) {
                LogUtil.e("result--------->" + result);
                ShopCollectCountBean shopCollectCountBean = JSON.parseObject(result, new TypeReference<ShopCollectCountBean>() {
                }.getType());
                LogUtil.e("shopCollectList--------->" + shopCollectCountBean.getRecords().size());
                if (shopCollectCountBean != null) {
                    shopCollectList.clear();
                    shopCollectList.addAll(shopCollectCountBean.getRecords());

                    if (getView() != null) {
                        getView().shopCollectCount(shopCollectList.size());
                    }
                } else {
                    getView().shopCollectCount(0);
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("errorMSG----->" + errorMsg);
            }
        }));
    }

    public void browsingHistoryCount() {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getHeadWithout(CommonResource.HISTORYALL, SPUtil.getToken());
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("browsingHistoryRecResult------------->" + result);
                LogUtil.e("browsingHistoryRecMsg------------->" + msg);
                BrowsingBean browsingBean = JSON.parseObject(result, new TypeReference<BrowsingBean>() {
                }.getType());
                if (browsingBean != null) {
                    if (getView() != null) {
                        for (int i = 0; i < browsingBean.getRecords().size(); i++) {
                            int size = browsingBean.getRecords().get(i).getItem().size();
                            getView().browsingHistoryCount(size);
                        }
                    }
                } else {
                    getView().browsingHistoryCount(0);
                }

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("browsingHistoryRecErrorMsg------------->" + errorMsg);
            }
        }));

    }


    //查询商家申请
    public void businessApplication() {
        Map build = MapUtil.getInstance().addParms("userCode", SPUtil.getUserCode()).build();
        Observable<ResponseBody> data = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).getData(CommonResource.SELLERSTATE, build);
        RetrofitUtil.getInstance().toSubscribe(data, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("mineFragmentResult--------------->" + result);
                ApplicationBean applicationBean = JSON.parseObject(result, new TypeReference<ApplicationBean>() {
                }.getType());
                if (applicationBean != null) {
                    String data1 = applicationBean.getData();
                    LogUtil.e("mineFragment" + data1);
                    if (data1.equals("2") || data1.equals("3")) {
                        ARouter.getInstance().build("/module_user_mine/BusinessApplicationActivity").navigation();
                    } else {
                        Toast.makeText(mContext, "您已经是商家了无需申请!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("mineFragmentErrorMsg--------------->" + errorMsg);
            }
        }));
    }
}
