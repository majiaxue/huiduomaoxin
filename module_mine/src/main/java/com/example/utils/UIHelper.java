package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bean.OperatorBean;
import com.example.common.CommonResource;
import com.example.module_mine.R;
import com.example.upgrade.adapter.PopQuanyiAdapter;
import com.example.upgrade.adapter.PopUpAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static void seeBigBitmap(final Context context, final Bitmap bitmap) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pop_full_image, null);
        ImageView img = inflate.findViewById(R.id.pop_full_img);
        final TextView btn = inflate.findViewById(R.id.pop_full_btn);
        Glide.with(context).load(bitmap).into(img);
        btn.setVisibility(View.VISIBLE);

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
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn.setEnabled(false);
                Date date = new Date();
                long time = date.getTime();
                File file = new File(Environment.getExternalStorageDirectory() + "/fltk/image");
                if (file.exists()) {
                    file.mkdirs();
                }
                File file1 = new File(Environment.getExternalStorageDirectory() + "/fltk/image/" + time + ".jpg");
                try {
                    //文件输出流
                    FileOutputStream fileOutputStream = new FileOutputStream(file1);
                    //压缩图片，如果要保存png，就用Bitmap.CompressFormat.PNG，要保存jpg就用Bitmap.CompressFormat.JPEG,质量是100%，表示不压缩
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    //写入，这里会卡顿，因为图片较大
                    fileOutputStream.flush();
                    //记得要关闭写入流
                    fileOutputStream.close();
                    //成功的提示，写入成功后，请在对应目录中找保存的图片
                    Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show();
                    btn.setVisibility(View.GONE);

                    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri uri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.lxy.taobaoke.provider", file1);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    } else {
                        uri = Uri.fromFile(file1);
                    }
                    intent.setData(uri);
                    context.sendBroadcast(intent);

                    MediaStore.Images.Media.insertImage(context.getContentResolver(),
                            bitmap, Environment.getExternalStorageDirectory() + "/fltk/image/" + time + ".jpg", null);
                    context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file1.getAbsolutePath())));
                } catch (Exception e) {
                    e.printStackTrace();
                    //失败的提示
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public static void popQuanYi(final Context mContext, final List<OperatorBean> beanList, final int position, OnClearCacheListener listener) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_quanyi, null);
        TextView title = view.findViewById(R.id.pop_quanyi_level);
        RecyclerView rv = view.findViewById(R.id.pop_quanyi_rv);
        TextView btn = view.findViewById(R.id.pop_quanyi_btn);
        ImageView cancel = view.findViewById(R.id.pop_quanyi_cancel);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(com.example.module_base.R.style.animScale);
        popupWindow.showAtLocation(new View(mContext), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(mContext, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(mContext, 1f);
            }
        });

        AssetManager assets = mContext.getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "fonts/MyFont.TTF");
        title.setTypeface(typeface);

        TxtUtil.txtJianbian(title, "#a26335", "#c97d3b");
        title.setText("升级为" + beanList.get(position).getName());
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);
        rv.setLayoutManager(layoutManager);

        int temp = -1;
        for (int i = 0; i < beanList.size(); i++) {
            if (beanList.get(i).getId().equals(SPUtil.getStringValue(CommonResource.LEVELID))) {
                temp = i;
                break;
            }
        }

        String perCashs = beanList.get(position).getPerCashs();
        List<String> list = new ArrayList<>();
        list.add("自买返佣" + beanList.get(position).getSharePercent() + "%");
        list.add("分享返佣" + beanList.get(position).getSharePercent() + "%");
        if (perCashs == null) {
            list.add("直接会员出单奖励" + "0%");
            list.add("平级奖励" + "0%");
            if (temp == 0) {
                list.add("团队奖励" + beanList.get(position).getSharePercent() + "%");
            } else {
                list.add("团队奖励" + ArithUtil.sub(Double.valueOf(beanList.get(position).getSharePercent()), Double.valueOf(beanList.get(temp).getSharePercent())) + "%");
            }
            list.add("间接会员出单奖励" + "0%");
        } else {
            String[] split = perCashs.split(",");
            list.add("直接会员出单奖励" + split[0] + "%");
            list.add("平级奖励" + "0%");
            if (temp == 0) {
                list.add("团队奖励" + beanList.get(position).getSharePercent() + "%");
            } else {
                list.add("团队奖励" + ArithUtil.sub(Double.valueOf(beanList.get(position).getSharePercent()), Double.valueOf(beanList.get(temp).getSharePercent())) + "%");
            }
            if (split.length == 1) {
                list.add("间接会员出单奖励" + "0%");
            } else {
                list.add("间接会员出单奖励" + split[1] + "%");
            }
        }
        PopQuanyiAdapter quanyiAdapter = new PopQuanyiAdapter(mContext, list, R.layout.rv_pop_quanyi);
        rv.setAdapter(quanyiAdapter);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        listener.setOnClearCache(popupWindow, btn);
    }

    public static void popUpSuccess(final Context context, OperatorBean bean, OnClearCacheListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_up_success, null);
        TextView level = view.findViewById(R.id.pop_up_level);
        TextView btn = view.findViewById(R.id.pop_up_btn);
        RecyclerView rv = view.findViewById(R.id.pop_up_rv);
        ImageView cancel = view.findViewById(R.id.pop_up_cancel);
        TextView txt = view.findViewById(R.id.pop_up_txt);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(com.example.module_base.R.style.animScale);
        popupWindow.showAtLocation(new View(context), Gravity.CENTER, 0, 0);
        PopUtils.setTransparency(context, 0.3f);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopUtils.setTransparency(context, 1f);
            }
        });

        AssetManager assets = context.getAssets();
        Typeface typeface = Typeface.createFromAsset(assets, "fonts/MyFont.TTF");
        level.setTypeface(typeface);
        txt.setTypeface(typeface);

        TxtUtil.txtJianbian(level, "#a26335", "#c97d3b");
        level.setText("升级为" + bean.getName());
        GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        rv.setLayoutManager(layoutManager);

        String perCashs = bean.getPerCashs();
        List<String> list = new ArrayList<>();
        list.add("自买返佣" + bean.getSharePercent() + "%");
        list.add("分享返佣" + bean.getSharePercent() + "%");
        if (perCashs == null) {
            list.add("直接会员出单奖励" + "0%");
        } else {
            String[] split = perCashs.split(",");
            list.add("直接会员出单奖励" + split[0] + "%");
        }

        PopUpAdapter adapter = new PopUpAdapter(context, list, R.layout.rv_pop_quanyi);
        rv.setAdapter(adapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        listener.setOnClearCache(popupWindow, btn);
    }
}
