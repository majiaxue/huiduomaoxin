package com.example.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private Context context;

    private RecyclerViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        mViews = new SparseArray<>();
    }

    public static RecyclerViewHolder getInstance(Context context, View itemView) {
        return new RecyclerViewHolder(itemView, context);
    }

    public <T extends View> T getView(int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return (T) view;
    }

    public RecyclerViewHolder setText(int resId, String text) {
        TextView txt = getView(resId);
        txt.setText(text);
        return this;
    }

    public RecyclerViewHolder setTextFormHtml(int resId, String html) {
        TextView txt = getView(resId);
        txt.setText(Html.fromHtml(html));
        return this;
    }

    public RecyclerViewHolder setButtonText(int resId, String text) {
        Button button = getView(resId);
        button.setText(text);
        return this;
    }

    public RecyclerViewHolder setTextColor(int resId, int colorId) {
        TextView txt = getView(resId);
        txt.setTextColor(colorId);
        return this;
    }

    public RecyclerViewHolder setButtonTextColor(int resId, int colorId) {
        Button button = getView(resId);
        button.setTextColor(colorId);
        return this;
    }

    public RecyclerViewHolder setImageResource(int resId, int drawableId) {
        ImageView imageView = getView(resId);
        imageView.setImageResource(drawableId);
        return this;
    }

    public RecyclerViewHolder setImageBitmap(int resId, Bitmap bitmap) {
        ImageView imageView = getView(resId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    public RecyclerViewHolder setImageUrl(int resId, String url) {
        ImageView imageView = getView(resId);
        Glide.with(context).load(url).into(imageView);
        return this;
    }

    public RecyclerViewHolder setBackground(int resId, int colorId) {
        View view = getView(resId);
        view.setBackgroundColor(colorId);
        return this;
    }
}
