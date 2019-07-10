package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.module_base.R;
import com.example.utils.adapter.VPBigPicAdapter;

import java.util.ArrayList;
import java.util.List;

public class PopUtils {
    public static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    public static void createPop(final Context context, View view, int width, int height, OnPopListener listener) {
        PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);
        setTransparency(context, 0.3f);
        listener.setOnPop(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
    }

    public static void createPopCenter(final Context context, View view, int width, int height, OnPopListener listener) {
        PopupWindow popupWindow = new PopupWindow(view, width, height, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.animScale);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);
        listener.setOnPop(popupWindow);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });
    }

    public static void changeHeader(final Context mContext, OnChangeHeaderListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_change_header, null);
        TextView takePhoto = view.findViewById(R.id.pop_change_header_camera);
        TextView photoAlbum = view.findViewById(R.id.pop_change_header_xiangce);
        TextView cancel = view.findViewById(R.id.pop_change_header_cancel);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(mContext), Gravity.BOTTOM, 0, 0);
        setTransparency(mContext, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(mContext, 1f);
            }
        });

        listener.setOnChangeHeader(popupWindow, takePhoto, photoAlbum);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void seeBigImg(final Context context, Uri uri) {
        View inflate = LayoutInflater.from(context).inflate(com.example.module_base.R.layout.pop_full_image, null);
        ImageView img = inflate.findViewById(com.example.module_base.R.id.pop_full_img);
        Glide.with(context).load(uri).into(img);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void popAssessBigPic(final Context context, List<String> list, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_vp_big_pic, null);
        ViewPager vp = inflate.findViewById(R.id.pop_vp_big_pic_vp);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.animScale);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);

        List<View> viewList = new ArrayList<>();
        for (String url : list) {
            ImageView imageView = new ImageView(context);
            Glide.with(context).load(url).into(imageView);
            viewList.add(imageView);
        }
        vp.setOffscreenPageLimit(list.size() - 1);
        VPBigPicAdapter adapter = new VPBigPicAdapter(viewList, new OnViewListener() {
            @Override
            public void setOnViewListener(View view) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        vp.setAdapter(adapter);
        vp.setCurrentItem(position);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
