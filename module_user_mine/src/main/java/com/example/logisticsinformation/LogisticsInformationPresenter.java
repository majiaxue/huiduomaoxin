package com.example.logisticsinformation;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.BaseStaggeredRecBean;
import com.example.logisticsinformation.adapter.LogisticsInforMationAdapter;
import com.example.logisticsinformation.bean.LogisticsInforMationBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.utils.DisplayUtil;
import com.example.utils.SpaceItemDecorationLeftAndRight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public class LogisticsInformationPresenter extends BasePresenter<LogisticsInformationView> {

    private SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight;

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
    public void logisticsInformationRec(RecyclerView logisticsInformationRec){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //添加间距
        spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
        if (logisticsInformationRec.getItemDecorationCount() == 0) {
            logisticsInformationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        logisticsInformationRec.setLayoutManager(staggeredGridLayoutManager);
        List<BaseStaggeredRecBean> baseStaggeredRecList = new ArrayList<>();
        baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
        baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
        baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));
        BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, baseStaggeredRecList, R.layout.item_base_rec_staggered_grid);
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
}
