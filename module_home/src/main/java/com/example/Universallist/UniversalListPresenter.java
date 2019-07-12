package com.example.Universallist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.example.mvp.BasePresenter;

public class UniversalListPresenter extends BasePresenter<UniversalListView> {

    public UniversalListPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void universalList(RecyclerView universalListRec, int position) {
        if (position == 1) {

        } else if (position == 2) {

        } else if (position == 3) {

        } else {

        }
    }
}
