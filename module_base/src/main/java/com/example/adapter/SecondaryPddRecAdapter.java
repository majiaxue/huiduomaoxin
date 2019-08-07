package com.example.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.TextView;

import com.example.bean.SecondaryPddRecBean;
import com.example.common.CommonResource;
import com.example.module_base.R;
import com.example.utils.ArithUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;

import java.util.List;


/**
 * Created by cuihaohao on 2019/6/6
 * Describe:
 */
public class SecondaryPddRecAdapter extends MyRecyclerAdapter<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> {

    public SecondaryPddRecAdapter(Context context, List<SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean> mList, int mLayoutId) {
        super(context, mList, mLayoutId);
    }

    @Override
    public void convert(RecyclerViewHolder holder, SecondaryPddRecBean.GoodsSearchResponseBean.GoodsListBean data, int position) {

        //拼多多
        holder.setImageResource(com.example.module_base.R.id.base_type, com.example.module_base.R.drawable.pinduoduo);

        double div = ArithUtil.div(Double.valueOf(data.getMin_group_price()) - Double.valueOf(data.getCoupon_discount()), 100, 1);
        double mul = ArithUtil.mul(div, ArithUtil.div(Double.valueOf(data.getPromotion_rate()), 1000, 1));

        holder.setImageFresco(com.example.module_base.R.id.base_image, data.getGoods_thumbnail_url());
        holder.setText(R.id.base_name, data.getGoods_name());
        holder.setText(R.id.base_reduce_price, "领劵减" + ArithUtil.div(Double.valueOf(data.getCoupon_discount()), 100, 0) + "元");
        holder.setText(R.id.base_preferential_price, "￥" + div);
        holder.setText(R.id.base_original_price, "" + ArithUtil.div(Double.valueOf(data.getMin_group_price()), 100, 1));
        if (!TextUtils.isEmpty(data.getSold_quantity())){
            holder.setText(R.id.base_number, "已抢" + data.getSold_quantity());
        }else{
            holder.setText(R.id.base_number, "已抢" + 0+"件");
        }
        // 中间加横线 ， 添加Paint.ANTI_ALIAS_FLAG是线会变得清晰去掉锯齿
        holder.setTextLine(R.id.base_original_price);
        if (!TextUtils.isEmpty(SPUtil.getToken())) {
            holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mul(mul, SPUtil.getFloatValue(CommonResource.BACKBL)));
            LogUtil.e("预估收益: 到手价" + div + "佣金比例" + mul + "个人佣金" + SPUtil.getFloatValue(CommonResource.BACKBL));
        } else {
            holder.setText(com.example.module_base.R.id.base_estimate, "预估赚" + ArithUtil.mul(mul, 0.3));
        }
        holder.setText(R.id.base_upgrade, "升级赚"+ArithUtil.mul(mul,0.8));

//        TextView immediatelyGrab = holder.getView(R.id.base_immediately_grab);
//        if (viewOnClickListener != null) {
//            viewOnClickListener.ViewOnClick(immediatelyGrab, position);
//        }
    }
}
