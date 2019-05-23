package com.example.collection;

import com.example.collection.adapter.CollectionAdapter;
import com.example.mvp.IView;

public interface CollectionView extends IView {

    void toEdit();

    void toFinish();

    void loadUI(CollectionAdapter adapter);
}
