package com.example.logisticsinformation.adapter;

import android.content.Context;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.logisticsinformation.bean.LogisticsInforMationBean;
import com.example.module_user_mine.R;

import java.sql.RowId;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/29
 * Describe:
 */
public class LogisticsInforMationAdapter extends MyRecyclerAdapter<LogisticsInforMationBean> {

    private List<LogisticsInforMationBean> list;

    public LogisticsInforMationAdapter(Context context, List<LogisticsInforMationBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
        this.list = mList;
    }

    @Override
    public void convert(RecyclerViewHolder holder, LogisticsInforMationBean data, int position) {
        holder.setText(R.id.accept_station_tv, data.getName());

        if (position == 0){
            holder.getView(R.id.time_top_view).setVisibility(View.INVISIBLE);
            holder.setImageResource(R.id.logistics_information_rec_image,R.drawable.icon_yushu);
        }else if (position == list.size()-1){
            holder.setImageResource(R.id.logistics_information_rec_image,R.drawable.icon_shou);
            holder.getView(R.id.time_line_view).setVisibility(View.INVISIBLE);
        }else {
            holder.setImageResource(R.id.logistics_information_rec_image,R.drawable.icon_shou);
        }

    }
}
