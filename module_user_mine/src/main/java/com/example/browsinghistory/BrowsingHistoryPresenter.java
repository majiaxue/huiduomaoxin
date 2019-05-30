package com.example.browsinghistory;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.MyRecyclerAdapter;
import com.example.browsinghistory.adapter.BrowsingHistoryParentAdapter;
import com.example.browsinghistory.bean.BrowsingHistoryParentBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/27
 * Describe:
 */
public class BrowsingHistoryPresenter extends BasePresenter<BrowsingHistoryView> {

    private boolean isCompile = false;
    private BrowsingHistoryParentAdapter browsingHistoryParentAdapter;
    private List<BrowsingHistoryParentBean> parentBeanList;
    private boolean flag = true;
    private boolean isAllParentChecked;

    public BrowsingHistoryPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void browsingHistoryRec(RecyclerView browsingHistoryRec) {
        parentBeanList = new ArrayList<>();
        parentBeanList.add(new BrowsingHistoryParentBean(false, "5月27日"));
        parentBeanList.add(new BrowsingHistoryParentBean(false, "5月20日"));
        parentBeanList.add(new BrowsingHistoryParentBean(false, "5月17日"));
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
            browsingHistoryParentAdapter.checkAll(false);
        } else {
            parentBeanList.get(index).setCheck(true);
            browsingHistoryParentAdapter.checkAll(true);
        }

        browsingHistoryParentAdapter.notifyDataSetChanged();

        for (int i = 0; i < parentBeanList.size(); i++) {
            if (!parentBeanList.get(i).isCheck()) {
                isAllParentChecked = false;
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
        this.isAllParentChecked = isCheckAllParent;
        if (isCheckAllParent) {
            for (int i = 0; i < parentBeanList.size(); i++) {
                parentBeanList.get(i).setCheck(false);
            }
        } else {
            for (int i = 0; i < parentBeanList.size(); i++) {
                parentBeanList.get(i).setCheck(true);
            }
        }

        browsingHistoryParentAdapter.notifyDataSetChanged();

    }

    //删除
    public void deleteList(){
        for (int i = parentBeanList.size() - 1; i >= 0; i--) {
            if (parentBeanList.get(i).isCheck()) {
                parentBeanList.remove(i);
            }
        }
        browsingHistoryParentAdapter.notifyDataSetChanged();
    }
}
