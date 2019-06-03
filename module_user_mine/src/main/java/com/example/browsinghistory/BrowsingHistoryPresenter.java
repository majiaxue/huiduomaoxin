package com.example.browsinghistory;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.browsinghistory.adapter.BrowsingHistoryParentAdapter;
import com.example.browsinghistory.bean.BrowsingHistoryBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import io.reactivex.internal.operators.flowable.FlowableLastMaybe;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryPresenter extends BasePresenter<BrowsingHistoryView> {

    private boolean isCompile = false;
    private BrowsingHistoryParentAdapter browsingHistoryParentAdapter;
    private List<BrowsingHistoryBean.ParentBean> parentBeanList;
    private boolean flag = true;
    public BrowsingHistoryPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void browsingHistoryRec(RecyclerView browsingHistoryRec) {

        List<BrowsingHistoryBean.ParentBean.ChildBean> childBeanList1 = new ArrayList<>();
        childBeanList1.add(new BrowsingHistoryBean.ParentBean.ChildBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖男T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店", false));
        childBeanList1.add(new BrowsingHistoryBean.ParentBean.ChildBean(R.drawable.img_109, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "10000人付款", "95%好评", "靓仔靓女旗舰店", false));

        List<BrowsingHistoryBean.ParentBean.ChildBean> childBeanList2 = new ArrayList<>();
        childBeanList2.add(new BrowsingHistoryBean.ParentBean.ChildBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖男T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店", false));
        childBeanList2.add(new BrowsingHistoryBean.ParentBean.ChildBean(R.drawable.img_109, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "10000人付款", "95%好评", "靓仔靓女旗舰店", false));

        List<BrowsingHistoryBean.ParentBean.ChildBean> childBeanList3 = new ArrayList<>();
        childBeanList3.add(new BrowsingHistoryBean.ParentBean.ChildBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖男T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店", false));
        childBeanList3.add(new BrowsingHistoryBean.ParentBean.ChildBean(R.drawable.img_109, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "10000人付款", "95%好评", "靓仔靓女旗舰店", false));


        parentBeanList = new ArrayList<>();
        parentBeanList.add(new BrowsingHistoryBean.ParentBean(false, "5月27日", childBeanList1));
        parentBeanList.add(new BrowsingHistoryBean.ParentBean(false, "5月20日", childBeanList2));
        parentBeanList.add(new BrowsingHistoryBean.ParentBean(false, "5月17日", childBeanList3));


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        browsingHistoryRec.setLayoutManager(linearLayoutManager);
        browsingHistoryParentAdapter = new BrowsingHistoryParentAdapter(mContext, parentBeanList, R.layout.item_browsing_history_parent, false);
        browsingHistoryRec.setAdapter(browsingHistoryParentAdapter);
        //点击选中全部子布局的check
        browsingHistoryParentAdapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(final View view, final int index) {

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        flag = true;
                        checkAll(index);
                    }
                });

            }
        });

    }

    //判断每个点击的parentCheckbox如果全部选中就去选中全选按钮
    private void checkAll(int index) {
        if (parentBeanList.get(index).isCheck()) {
            parentBeanList.get(index).setCheck(false);
            browsingHistoryParentAdapter.checkAll(index, true);
        } else {
            parentBeanList.get(index).setCheck(true);
            browsingHistoryParentAdapter.checkAll(index, false);
        }

        browsingHistoryParentAdapter.notifyDataSetChanged();

        for (int i = 0; i < parentBeanList.size(); i++) {
            if (!parentBeanList.get(i).isCheck()) {
                flag = false;
            }
        }

        if (getView() != null) {
            getView().isCheckAll(flag);
        }

    }

    //编辑状态
    public void browsingHistoryState() {
        if (isCompile) {
            isCompile = false;
            getView().isCompile(isCompile);
        } else {
            isCompile = true;
            getView().isCompile(isCompile);
        }
        browsingHistoryParentAdapter.setCompile(isCompile);
    }

    //选中parent全部的checkbox
    public void checkAllParent(boolean isCheckAllParent) {
        if (isCheckAllParent) {
            for (int i = 0; i < parentBeanList.size(); i++) {
                parentBeanList.get(i).setCheck(false);
                browsingHistoryParentAdapter.checkAll(i, true);
            }
        } else {
            for (int i = 0; i < parentBeanList.size(); i++) {
                parentBeanList.get(i).setCheck(true);
                browsingHistoryParentAdapter.checkAll(i, false);
            }
        }

        browsingHistoryParentAdapter.notifyDataSetChanged();

    }

    //删除
    public void deleteList() {
//        for (int i = parentBeanList.size() - 1; i >= 0; i--) {
//            if (parentBeanList.get(i).isCheck()) {
//                parentBeanList.remove(i);
//            }
//        }



        if (parentBeanList.size() == 0) {
            getView().isCompile(false);
            getView().isCheckAll(false);
        }

        browsingHistoryParentAdapter.notifyDataSetChanged();
    }
}
