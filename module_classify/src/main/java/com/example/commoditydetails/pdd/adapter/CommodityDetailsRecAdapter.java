package com.example.commoditydetails.pdd.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.adapter.RecyclerViewHolder;
import com.example.module_classify.R;
import com.example.utils.FrescoUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by cuihaohao on 2019/6/11
 * Describe:
 */
public class CommodityDetailsRecAdapter extends MyRecyclerAdapter<String> {

    public CommodityDetailsRecAdapter(Context context, List<String> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, String data, int position) {
//        holder.setImageUrl(R.id.commodity_details_rec_image, data);
        SimpleDraweeView image = holder.getView(R.id.commodity_details_rec_image);
        FrescoUtils.setControllerListener(image,data,FrescoUtils.getScreenWidth(context));


//        ImageView image = holder.getView(R.id.commodity_details_rec_image);
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        DisplayMetrics dm = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(dm);
//        // 屏幕宽度算法:屏幕宽度（像素）/屏幕密度
//        int width = dm.widthPixels;         // 屏幕宽度（像素）
//        float density = dm.density;         // 屏幕密度（0.75 / 1.0 / 1.5）
//        int screenWidth = (int) (width / density);  // 屏幕宽度(dp)
//        //设置图片的位置
//        ViewGroup.MarginLayoutParams margin9 = new ViewGroup.MarginLayoutParams(image.getLayoutParams());
//        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(margin9);
//        layoutParams9.height = screenWidth;//设置图片的高度
//        image.setLayoutParams(layoutParams9);
//        image.setMaxHeight(screenWidth);
//        image.setImageURI(Uri.parse(data));
//        Glide.with(context).load(data).into(image);
    }
}
