package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.module_mine.R;

public class UIHelper {
    private static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    public static void clearCache(final Context context, String totalCache, OnClearCacheListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_clear_cache, null);
        TextView cancel = view.findViewById(R.id.pop_cache_cancel);
        TextView confirm = view.findViewById(R.id.pop_cache_confirm);
        TextView content = view.findViewById(R.id.pop_cache_content);
        content.setText("确定要清除(" + totalCache + ")缓存吗？");

        final PopupWindow popupWindow = new PopupWindow(view, (int) context.getResources().getDimension(R.dimen.dp_259), (int) context.getResources().getDimension(R.dimen.dp_177), true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        listener.setOnClearCache(popupWindow, confirm);
    }

    public static void seeBigImg(final Context context, int imgId) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_full_image, null);
        ImageView img = inflate.findViewById(R.id.pop_full_img);
        img.setImageResource(imgId);

        final PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }
}
