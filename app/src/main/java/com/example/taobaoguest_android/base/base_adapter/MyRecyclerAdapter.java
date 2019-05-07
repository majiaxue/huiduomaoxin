package com.example.taobaoguest_android.base.base_adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public abstract class MyRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    protected Context context;
    private List<T> mList;
    private int mLayoutId;
    private LayoutInflater inflater;
    private OnItemClickListener listener;
    private OnItemLongClickListener longClickListener;
    private RecyclerView recyclerView;

    public MyRecyclerAdapter(Context context, List<T> mList, int mLayoutId) {
        this.context = context;
        this.mList = mList;
        this.mLayoutId = mLayoutId;
        inflater = LayoutInflater.from(context);
    }

    //在RecyclerView提供数据的时候调用
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(mLayoutId, parent, false);
        return RecyclerViewHolder.getInstance(context, view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null && v != null) {
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(v);
                    listener.onItemClick(recyclerView, v, childAdapterPosition);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null && v != null) {
                    int childAdapterPosition = recyclerView.getChildAdapterPosition(v);
                    longClickListener.onItemLongClick(recyclerView, v, childAdapterPosition);
                    return true;
                }
                return false;
            }
        });
        convert(holder, mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public abstract void convert(RecyclerViewHolder holder, T data, int position);

    /**
     * 以下为点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(RecyclerView parent, View view, int position);
    }

    public interface OnItemLongClickListener {
        boolean onItemLongClick(RecyclerView parent, View view, int position);
    }

    public void setOnItemClick(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnItemLongClick(OnItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}
