package com.example.shop_home.treasure;

import com.example.mvp.IView;
import com.example.shop_home.adapter.TreasureLstAdapter;
import com.example.shop_home.adapter.TreasureWaterfallAdapter;

public interface ShopTreasureView extends IView {

    void loadLstRv(TreasureLstAdapter adapter);

    void loadWaterfallRv(TreasureWaterfallAdapter adapter);

    void updateTitle(boolean salesVolume, boolean price, boolean credit);
}
