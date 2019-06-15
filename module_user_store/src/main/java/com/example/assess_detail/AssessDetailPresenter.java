package com.example.assess_detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.assess_detail.adapter.InsideAssessAdapter;
import com.example.assess_detail.adapter.InsideImageAdapter;
import com.example.bean.AssessBean;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;
import com.example.utils.PopUtil;

import java.util.ArrayList;
import java.util.List;

public class AssessDetailPresenter extends BasePresenter<AssessDetailView> {
    private boolean dianzan = false;

    public AssessDetailPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
//        final List<AssessBean.AssessInsideAssess> insideAssessList = new ArrayList<>();
//        insideAssessList.add(new AssessBean.AssessInsideAssess("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558787958399&di=a3a500512493cb2f450d521248dd379b&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201904%2F04%2F222944joyex4t9xy44j4r1.jpeg", "首发式地方", "嘎温哥华瘫痪让她和让他和如果而然后", "一天前", 10, false));
//        insideAssessList.add(new AssessBean.AssessInsideAssess("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558787958399&di=a3a500512493cb2f450d521248dd379b&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201904%2F04%2F222944joyex4t9xy44j4r1.jpeg", "首发式地方", "嘎温哥华瘫痪让她和让他和如果而然后", "一天前", 10, false));
//        insideAssessList.add(new AssessBean.AssessInsideAssess("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558787958399&di=a3a500512493cb2f450d521248dd379b&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201904%2F04%2F222944joyex4t9xy44j4r1.jpeg", "首发式地方", "嘎温哥华瘫痪让她和让他和如果而然后", "一天前", 10, false));
//        insideAssessList.add(new AssessBean.AssessInsideAssess("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558787958399&di=a3a500512493cb2f450d521248dd379b&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201904%2F04%2F222944joyex4t9xy44j4r1.jpeg", "首发式地方", "嘎温哥华瘫痪让她和让他和如果而然后", "一天前", 10, false));
//
//        final List<String> imgList = new ArrayList<>();
//        imgList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1937445174,2133528823&fm=26&gp=0.jpg");
//        imgList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1937445174,2133528823&fm=26&gp=0.jpg");
//        imgList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1937445174,2133528823&fm=26&gp=0.jpg");
//        imgList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1937445174,2133528823&fm=26&gp=0.jpg");
//
//        InsideImageAdapter imageAdapter = new InsideImageAdapter(mContext, imgList, R.layout.rv_inside_img);
//        final InsideAssessAdapter insideAssessAdapter = new InsideAssessAdapter(mContext, insideAssessList, R.layout.rv_inside_assess);
//        if (getView() != null) {
//            getView().loadImg(imageAdapter);
//            getView().loadInsideAssess(insideAssessAdapter);
//        }
//
//        imageAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(RecyclerView parent, View view, int position) {
//                PopUtil.popAssessBigPic(mContext, imgList, position);
//            }
//        });
//
//        insideAssessAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
//            @Override
//            public void ViewOnClick(View view, final int index) {
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (insideAssessList.get(index).isInsideIsZan()) {
//                            insideAssessList.get(index).setInsideIsZan(false);
//                            insideAssessList.get(index).setInsideZanCount(insideAssessList.get(index).getInsideZanCount() - 1);
//                        } else {
//                            insideAssessList.get(index).setInsideIsZan(true);
//                            insideAssessList.get(index).setInsideZanCount(insideAssessList.get(index).getInsideZanCount() + 1);
//                        }
//                        insideAssessAdapter.notifyDataSetChanged();
//                    }
//                });
//            }
//        });
    }

    public void dianZan() {
        if (dianzan) {
            getView().cancelZan();
            dianzan = false;
        } else {
            getView().zan();
            dianzan = true;
        }
    }
}
