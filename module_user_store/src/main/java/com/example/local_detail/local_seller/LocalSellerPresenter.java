package com.example.local_detail.local_seller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.adapter.MyRecyclerAdapter;
import com.example.local_detail.adapter.SellerImaAdapter;
import com.example.mvp.BasePresenter;
import com.example.user_store.R;
import com.example.utils.PopUtils;

import java.util.ArrayList;
import java.util.List;

public class LocalSellerPresenter extends BasePresenter<LocalSellerView> {
    private List<String> imgList = new ArrayList<>();
    private SellerImaAdapter imgAdapter;

    public LocalSellerPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void loadData() {
        for (int i = 0; i < 5; i++) {
            imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1562825130737&di=c3f5e6fa4c19552e11b6880baa170aac&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201504%2F03%2F20150403H4625_LWeky.jpeg");
        }

        imgAdapter = new SellerImaAdapter(mContext, imgList, R.layout.rv_local_seller_img);
        if (getView() != null) {
            getView().loadImg(imgAdapter);
        }

        imgAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                PopUtils.popAssessBigPic(mContext, imgList, position);
            }
        });
    }
}
