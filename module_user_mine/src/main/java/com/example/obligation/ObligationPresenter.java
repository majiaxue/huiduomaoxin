package com.example.obligation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.BaseRecStaggeredAdapter;
import com.example.adapter.MyRecyclerAdapter;
import com.example.entity.BaseStaggeredRecBean;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.utils.DisplayUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.SpaceItemDecorationLeftAndRight;
import com.example.utils.TxtUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihaohao on 2019/5/28
 * Describe:
 */
public class ObligationPresenter extends BasePresenter<ObligationView> {

    public ObligationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void obligationRec(RecyclerView obligationRec) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        //添加间距
        SpaceItemDecorationLeftAndRight spaceItemDecorationLeftAndRight = new SpaceItemDecorationLeftAndRight(DisplayUtil.dip2px(mContext, 15), DisplayUtil.dip2px(mContext, 15));
        if (obligationRec.getItemDecorationCount() == 0) {
            obligationRec.addItemDecoration(spaceItemDecorationLeftAndRight);
        }
        obligationRec.setLayoutManager(staggeredGridLayoutManager);
        List<BaseStaggeredRecBean> baseStaggeredRecList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_108, "2019夏季新款纯棉白色短袖女T恤个性字母简约......", "￥39.90", "12345人付款", "97%好评", "班迪卡旗舰店"));
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_109, "星座毛巾纯棉洗脸家用吸水男女洗澡全棉柔软情侣......", "￥18.80", "12345人付款", "97%好评", "班迪卡旗舰店"));
            baseStaggeredRecList.add(new BaseStaggeredRecBean(R.drawable.img_110, "ins超火纯棉短袖T恤女夏装2019新款港风潮宽松学......", "￥15.88", "12345人付款", "97%好评", "班迪卡旗舰店"));

        }
        BaseRecStaggeredAdapter baseRecStaggeredAdapter = new BaseRecStaggeredAdapter(mContext, baseStaggeredRecList, R.layout.item_base_rec_staggered_grid);
        obligationRec.setAdapter(baseRecStaggeredAdapter);

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

    public void popupCancellationOrder(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_cancellation_order, null);
        TextView text = view.findViewById(R.id.popup_cancellation_order_text);
        TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
        final ImageView imageClose = view.findViewById(R.id.popup_cancellation_order_close);
        final RadioGroup popupRefundRadio = view.findViewById(R.id.popup_cancellation_order_radio);
        final RadioButton but1 = view.findViewById(R.id.popup_cancellation_order_but1);
        final RadioButton but2 = view.findViewById(R.id.popup_cancellation_order_but2);
        final RadioButton but3 = view.findViewById(R.id.popup_cancellation_order_but3);
        final RadioButton but4 = view.findViewById(R.id.popup_cancellation_order_but4);
        final RadioButton but5 = view.findViewById(R.id.popup_cancellation_order_but5);
        final TextView submit = view.findViewById(R.id.popup_cancellation_order_submit);
        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mContext, 375), new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (but1.isChecked()){
                            Toast.makeText(mContext, but1.getText().toString(), Toast.LENGTH_SHORT).show();
                            pop.dismiss();
                        }else if (but2.isChecked()){
                            Toast.makeText(mContext, but2.getText().toString(), Toast.LENGTH_SHORT).show();
                            pop.dismiss();
                        }else if (but3.isChecked()){
                            Toast.makeText(mContext, but3.getText().toString(), Toast.LENGTH_SHORT).show();
                            pop.dismiss();
                        }else if (but4.isChecked()){
                            Toast.makeText(mContext, but4.getText().toString(), Toast.LENGTH_SHORT).show();
                            pop.dismiss();
                        }else if (but5.isChecked()){
                            Toast.makeText(mContext, but5.getText().toString(), Toast.LENGTH_SHORT).show();
                            pop.dismiss();
                        }
                    }
                });


            }
        });
        PopUtils.setTransparency(mContext, 0.3f);
    }
}
