package com.example.assess;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.assess.adapter.AssessAdapter;
import com.example.assess.adapter.AssessTitleAdapter;
import com.example.entity.AssessBean;
import com.example.entity.AssessTitleBean;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;
import com.example.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class AssessPresenter extends BasePresenter<AssessView> {
    public AssessPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        final List<AssessTitleBean> titleList = new ArrayList<>();
        titleList.add(new AssessTitleBean("全部", true));
        titleList.add(new AssessTitleBean("最新", false));
        titleList.add(new AssessTitleBean("有图", false));
        titleList.add(new AssessTitleBean("追评", false));
        final AssessTitleAdapter titleAdapter = new AssessTitleAdapter(mContext, titleList, R.layout.rv_assess_title);
        if (getView() != null) {
            getView().loadTitle(titleAdapter);
        }
        titleAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int index) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < titleList.size(); i++) {
                            if (i == index) {
                                titleList.get(i).setCheck(true);
                            } else {
                                titleList.get(i).setCheck(false);
                            }
                        }
                        titleAdapter.notifyDataSetChanged();
                    }
                });
            }
        });

        List<String> urlList = new ArrayList<>();
        urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1937445174,2133528823&fm=26&gp=0.jpg");
        urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1937445174,2133528823&fm=26&gp=0.jpg");
        urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1937445174,2133528823&fm=26&gp=0.jpg");
        final List<AssessBean> assessList = new ArrayList<>();
        assessList.add(new AssessBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558787958399&di=a3a500512493cb2f450d521248dd379b&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201904%2F04%2F222944joyex4t9xy44j4r1.jpeg", "山豆根山", "啊手动阀撒根深蒂固的师傅给对手犯规得分", 4, "2018年10月12日", "XXL", "白色", 100, 100, false, urlList));
        assessList.add(new AssessBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558787958399&di=a3a500512493cb2f450d521248dd379b&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201904%2F04%2F222944joyex4t9xy44j4r1.jpeg", "山豆根山", "啊手动阀撒根深蒂固的师傅给对手犯规得分", 4, "2018年10月12日", "XXL", "白色", 100, 110, false, urlList));
        assessList.add(new AssessBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558787958399&di=a3a500512493cb2f450d521248dd379b&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201904%2F04%2F222944joyex4t9xy44j4r1.jpeg", "山豆根山", "啊手动阀撒根深蒂固的师傅给对手犯规得分", 4, "2018年10月12日", "XXL", "白色", 10, 20, false, urlList));
        assessList.add(new AssessBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1558787958399&di=a3a500512493cb2f450d521248dd379b&imgtype=0&src=http%3A%2F%2Fatt.bbs.duowan.com%2Fforum%2F201904%2F04%2F222944joyex4t9xy44j4r1.jpeg", "山豆根山", "啊手动阀撒根深蒂固的师傅给对手犯规得分", 4, "2018年10月12日", "XXL", "白色", 24, 500, false, urlList));
        AssessAdapter assessAdapter = new AssessAdapter(mContext, assessList, R.layout.rv_assess_content);
        if (getView() != null) {
            getView().loadAssess(assessAdapter);
        }
        assessAdapter.setOnFiveViewClickListener(new MyRecyclerAdapter.OnFiveViewClickListener() {
            @Override
            public void FiveViewClick(final TextView zanCount, final ImageView zanImg, TextView assessCount, ImageView assessImg, final int groupPosition, ImageView img, final int position) {
                zanImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (assessList.get(groupPosition).isZan()) {
                            assessList.get(groupPosition).setZan(false);
                            assessList.get(groupPosition).setZanCount(assessList.get(groupPosition).getZanCount() + 1);
                            zanCount.setText(assessList.get(groupPosition).getZanCount() + "");
                            zanImg.setImageResource(R.drawable.icon_dianzan);
                        } else {
                            assessList.get(groupPosition).setZan(true);
                            assessList.get(groupPosition).setZanCount(assessList.get(groupPosition).getZanCount() - 1);
                            zanCount.setText(assessList.get(groupPosition).getZanCount() + "");
                            zanImg.setImageResource(R.drawable.icon_dianzan1);
                        }
                    }
                });

                assessImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.e("点击了第" + groupPosition + "条的第" + position + "张图片");
                    }
                });

            }
        });
    }
}
