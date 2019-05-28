package com.example.classify;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.classify.adapter.MyExpandableAdapter;
import com.example.classify.adapter.MyRightRecAdapter;
import com.example.entity.LeftChildBean;
import com.example.entity.LeftGroupBean;
import com.example.entity.RightRecBean;
import com.example.entity.TopBannerBean;
import com.example.module_home.R;
import com.example.mvp.BasePresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import java.util.ArrayList;
import java.util.List;

public class ClassifyPresenter extends BasePresenter<ClassifyView> {

    private List<LeftGroupBean> glist;
    private List<List<LeftChildBean>> clist;
    private int FLAG = 0;
    private List<TopBannerBean> images;

    public ClassifyPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void setExpand(final ExpandableListView classifyExpand, final XBanner classifyXBanner) {
        //去掉系统给的指示器
        classifyExpand.setGroupIndicator(null);
        //去掉下划线
        classifyExpand.setDivider(null);

        //定义第一级的数据集合
        glist = new ArrayList<>();
        glist.add(new LeftGroupBean("推荐", true));
        glist.add(new LeftGroupBean("服装", false));
        glist.add(new LeftGroupBean("数码", false));
        glist.add(new LeftGroupBean("配饰", false));
        glist.add(new LeftGroupBean("洗护", false));
        glist.add(new LeftGroupBean("美妆", false));
        glist.add(new LeftGroupBean("家电", false));
        glist.add(new LeftGroupBean("数码", false));
        glist.add(new LeftGroupBean("数码", false));
        glist.add(new LeftGroupBean("数码", false));
        glist.add(new LeftGroupBean("数码", false));
        glist.add(new LeftGroupBean("数码", false));
        glist.add(new LeftGroupBean("数码", false));
        glist.add(new LeftGroupBean("数码", false));
        glist.add(new LeftGroupBean("数码", false));

        //定义第二级的数据
        clist = new ArrayList<>();

        //第一组数据
        List<LeftChildBean> o_list = new ArrayList<>();
        o_list.add(new LeftChildBean("男装", false));
        o_list.add(new LeftChildBean("女装", false));
        o_list.add(new LeftChildBean("裤子", false));
        o_list.add(new LeftChildBean("内衣", false));
        o_list.add(new LeftChildBean("鞋子", false));

//        //第二组数据
//        List<LeftChildBean> t_list = new ArrayList<>();
//        t_list.add(new LeftChildBean("电脑",false));
//        t_list.add(new LeftChildBean("电视",false));
//        t_list.add(new LeftChildBean("手机",false));
//        t_list.add(new LeftChildBean("二级",false));
//        t_list.add(new LeftChildBean("音响",false));

        //放入大集合
        for (int i = 0; i < glist.size(); i++) {
            if (i == 0) {
                List<LeftChildBean> t_list = new ArrayList<>();
                t_list.add(new LeftChildBean());
                clist.add(t_list);
            } else {
                clist.add(o_list);
            }

        }

//        clist.add(t_list);

        final MyExpandableAdapter myExpandableAdapter = new MyExpandableAdapter(mContext, glist, clist);
        classifyExpand.setAdapter(myExpandableAdapter);

        classifyExpand.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                for (int i = 0, count = classifyExpand.getExpandableListAdapter().getGroupCount(); i < count; i++) {
                    if (i > 0) {
                        if (groupPosition != i) {// 关闭其他分组
                            classifyExpand.collapseGroup(i);
                        }
                    } else {
                        classifyExpand.collapseGroup(0);
                    }
                }

            }
        });

        classifyExpand.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(mContext, "groupPosition:" + groupPosition, Toast.LENGTH_SHORT).show();
                //判断父布局的位置
                if (FLAG != groupPosition) {
                    //设置点击后的状态
                    if (glist.size() > 0 && groupPosition < glist.size()) {
                        for (int i = 0; i < glist.size(); i++) {
                            glist.get(i).setSelected(false);
                        }
                        glist.get(groupPosition).setSelected(true);

                        //判断父布局下标大于1而又点击后
                        if (groupPosition > 0) {
                            for (int i = 0; i < clist.get(groupPosition).size(); i++) {
                                if (i == 0) {
                                    clist.get(groupPosition).get(0).setSelected(true);
                                } else {
                                    clist.get(groupPosition).get(i).setSelected(false);
                                }
                            }
                        } else {
                            //关闭推荐
                            classifyExpand.collapseGroup(0);
                        }
                        //判断推荐item是否选中选中显示没选中隐藏
                        boolean selected = glist.get(0).isSelected();
                        if (selected) {
                            classifyXBanner.startAutoPlay();
                            classifyXBanner.setVisibility(View.VISIBLE);
                        } else {
                            classifyXBanner.stopAutoPlay();
                            classifyXBanner.setVisibility(View.GONE);
                        }

                        myExpandableAdapter.notifyDataSetChanged();
                    }
                    FLAG = groupPosition;
                    return false;
                } else {
                    //如果父布局下标等于0不让展开子布局
                    return true;
                }


            }
        });

        classifyExpand.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext, "groupPosition:" + groupPosition + "++++++++++++++" + childPosition, Toast.LENGTH_SHORT).show();
                if (clist.get(groupPosition).size() > 0 && groupPosition < clist.size()) {
                    for (int i = 0; i < clist.get(groupPosition).size(); i++) {
                        clist.get(groupPosition).get(i).setSelected(false);
                    }
                    clist.get(groupPosition).get(childPosition).setSelected(true);
                    myExpandableAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

    }

    public void setXBanner(XBanner homeXbanner) {
        images = new ArrayList<>();
        images.add(new TopBannerBean(R.drawable.banner1));
        images.add(new TopBannerBean(R.drawable.banner2));
        images.add(new TopBannerBean(R.drawable.banner3));
        images.add(new TopBannerBean(R.drawable.banner4));
//        homeXbanner.setData(images, null);
        homeXbanner.setBannerData(R.layout.image_fresco, images);
        homeXbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                SimpleDraweeView bannerImage = view.findViewById(R.id.banner_image);
                bannerImage.setImageResource((int) images.get(position).getXBannerUrl());
            }
        });
        // 设置XBanner的页面切换特效
        homeXbanner.setPageTransformer(Transformer.Default);
        // 设置XBanner页面切换的时间，即动画时长
        homeXbanner.setPageChangeDuration(1000);

        //监听广告 item 的单击事件
        homeXbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Toast.makeText(mContext, "点击了第" + position + "图片", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setRightRec(RecyclerView classifyRecommendRec) {
        List<RightRecBean> list = new ArrayList<>();

        List<RightRecBean.ListBean> a_childList = new ArrayList<>();
        a_childList.add(new RightRecBean.ListBean(R.drawable.tcl, "tcl"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.sony, "索尼"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.feilipu, "飞利浦"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.xiapu, "夏普"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.haier, "海尔"));
        a_childList.add(new RightRecBean.ListBean(R.drawable.chuangwei, "创维"));


        List<RightRecBean.ListBean> b_childList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                b_childList.add(new RightRecBean.ListBean(R.drawable.e76696ec1eea0f7e8f0208ba61583434, "电视"));
                b_childList.add(new RightRecBean.ListBean(R.drawable.bingxiang, "冰箱"));
                b_childList.add(new RightRecBean.ListBean(R.drawable.fgfd, "洗衣机"));
            }
            list.add(new RightRecBean("专场推荐", a_childList));
            list.add(new RightRecBean("热门分类", b_childList));
        }


        MyRightRecAdapter myRightRecAdapter = new MyRightRecAdapter(mContext, list, R.layout.item_rec_group);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        classifyRecommendRec.setLayoutManager(linearLayoutManager);
        classifyRecommendRec.setAdapter(myRightRecAdapter);

    }

}
