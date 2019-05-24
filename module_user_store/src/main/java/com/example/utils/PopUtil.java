package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.ChooseGoodsBean;
import com.example.entity.CouponBean;
import com.example.entity.ParmsBean;
import com.example.goods_detail.adapter.PopLingQuanAdapter;
import com.example.goods_detail.adapter.PopParmsAdapter;
import com.example.user_store.R;
import com.example.view.FlowLayout;

import java.util.List;

public class PopUtil {
    public static void setTransparency(Context context, float value) {
        Activity activity = (Activity) context;
        Window window = activity.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.alpha = value;
        window.setAttributes(params);
    }

    public static void lingquanPop(final Context context, final List<CouponBean> dataList) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_lingquan, null);
        RecyclerView rv = view.findViewById(R.id.pop_lingquan_rv);
        TextView btn = view.findViewById(R.id.pop_lingquan_btn);
        final PopupWindow popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_569), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);


        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        final PopLingQuanAdapter adapter = new PopLingQuanAdapter(context, dataList, R.layout.rv_pop_lingquan);
        rv.setAdapter(adapter);
        adapter.setViewOnClickListener(new MyRecyclerAdapter.ViewOnClickListener() {
            @Override
            public void ViewOnClick(View view, final int position) {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataList.get(position).setHas(true);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    public static void ensurePop(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_ensure, null);
        TextView btn = view.findViewById(R.id.pop_ensure_btn);
        TextView txt = view.findViewById(R.id.pop_ensure_txt);
        TxtUtil.txtJianbian(txt, "#feb60e", "#fb4419");

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_371), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void parmsPop(final Context context, List<ParmsBean> dataList) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_parms, null);
        TextView title = view.findViewById(R.id.pop_parms_title);
        RecyclerView rv = view.findViewById(R.id.pop_parms_rv);
        TextView btn = view.findViewById(R.id.pop_parms_btn);
        TxtUtil.txtJianbian(title, "#feb60e", "#fb4419");

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_371), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        PopParmsAdapter adapter = new PopParmsAdapter(context, dataList, R.layout.rv_pop_parms);
        rv.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    public static void chooseGoodsPop(final Context context, final List<ChooseGoodsBean> dataList) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_choose_goods, null);
        ImageView img = view.findViewById(R.id.pop_choose_goods_img);
        TextView price = view.findViewById(R.id.pop_choose_goods_price);
        ImageView cancel = view.findViewById(R.id.pop_choose_goods_cancel);
        FlowLayout flow1 = view.findViewById(R.id.pop_choose_goods_flow1);
        FlowLayout flow2 = view.findViewById(R.id.pop_choose_goods_flow2);
        TextView minus = view.findViewById(R.id.pop_choose_goods_minus);
        TextView add = view.findViewById(R.id.pop_choose_goods_add);
        final TextView count = view.findViewById(R.id.pop_choose_goods_count);
        TextView shopCart = view.findViewById(R.id.pop_choose_goods_cart);
        TextView buy = view.findViewById(R.id.pop_choose_goods_buy);

        final PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, (int) context.getResources().getDimension(R.dimen.dp_444), true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.pop_bottom_anim);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);

        setTransparency(context, 0.3f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTransparency(context, 1f);
            }
        });

        LinearLayout colorParent;
        TextView colorContent;
        for (int i = 0; i < dataList.size(); i++) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.pop_choose_goods_color, flow1, false);
            colorParent = inflate.findViewById(R.id.flow_goods_color_parent);
            colorContent = inflate.findViewById(R.id.flow_goods_color_content);
            Glide.with(context).load(dataList.get(i).getImg()).into((ImageView) inflate.findViewById(R.id.flow_goods_color_img));
            colorContent.setText(dataList.get(i).getName());
            if (i == 0) {
                colorParent.setBackgroundResource(R.drawable.goods_5_99fd3c15);
                colorContent.setTextColor(Color.WHITE);
            }
            flow1.addView(inflate);
        }

        TextView sizeTxt;
        for (int i = 0; i < dataList.get(0).getSize().size(); i++) {
            sizeTxt = (TextView) LayoutInflater.from(context).inflate(R.layout.pop_choose_goods_size, flow2, false);
            sizeTxt.setText(dataList.get(0).getSize().get(i).getSize());
            if (i == 0) {
                sizeTxt.setBackgroundResource(R.drawable.goods_5_99fd3c15);
            }
            flow2.addView(sizeTxt);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(count.getText().toString()) <= 1) {
                    count.setText("1");
                } else {
                    count.setText(Integer.valueOf(count.getText().toString()) - 1 + "");
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(count.getText().toString()) >= dataList.get(0).getSize().get(0).getCount()) {
                    count.setText(dataList.get(0).getSize().get(0).getCount() + "");
                } else {
                    count.setText(Integer.valueOf(count.getText().toString()) + 1 + "");
                }
            }
        });


    }
}
