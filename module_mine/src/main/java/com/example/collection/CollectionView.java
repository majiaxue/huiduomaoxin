package com.example.collection;

import com.example.mvp.IView;

public interface CollectionView extends IView {

    void toEdit();

    void toFinish();
}
