package com.example.taobaoguest_android.mvp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taobaoguest_android.R;
import com.example.taobaoguest_android.mvp.hairring.fragment.HairringFragment;
import com.example.taobaoguest_android.mvp.home.fragment.HomeFragment;
import com.example.taobaoguest_android.mvp.mine.fragment.MineFragment;
import com.example.taobaoguest_android.mvp.search.fragment.SearchFragment;
import com.example.taobaoguest_android.mvp.shopping.fragment.ShoppingFragment;
import com.example.taobaoguest_android.utils.NetStatusUtils;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.activity_main_frame)
    FrameLayout activityMainFrame;
    @BindView(R.id.main_home)
    RadioButton mainHome;
    @BindView(R.id.main_shopping)
    RadioButton mainShopping;
    @BindView(R.id.main_search)
    RadioButton mainSearch;
    @BindView(R.id.main_hairring)
    RadioButton mainHairring;
    @BindView(R.id.main_mine)
    RadioButton mainMine;
    @BindView(R.id.main_group)
    RadioGroup mainGroup;
    @BindView(R.id.main_xbanner)
    XBanner mainXbanner;
    //触碰标识
    private long exitTime = 0;

    private int ISFIRSTOPER = 0;
    private FragmentManager fragmentManager;
    private HairringFragment hairringFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
    private SearchFragment searchFragment;
    private ShoppingFragment shoppingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //判断网络状态
        if (ISFIRSTOPER == 0)
            //首次开启APP,设置网络状态
            NetStatusUtils.getInstance().setNetState(this);
        else {
            ISFIRSTOPER = 1;
        }

        initData();
        initListener();

    }

    private void initData() {
        hairringFragment = new HairringFragment();
        homeFragment = new HomeFragment();
        mineFragment = new MineFragment();
        searchFragment = new SearchFragment();
        shoppingFragment = new ShoppingFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.activity_main_frame, hairringFragment)
                .add(R.id.activity_main_frame, homeFragment)
                .add(R.id.activity_main_frame, mineFragment)
                .add(R.id.activity_main_frame, searchFragment)
                .add(R.id.activity_main_frame, shoppingFragment);
        transaction.show(homeFragment)
                .hide(hairringFragment)
                .hide(mineFragment)
                .hide(searchFragment)
                .hide(shoppingFragment)
                .commit();
        final List list = new ArrayList<>();
        list.add("http://b.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg");
        list.add("http://b.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg");
        list.add("http://b.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg");
        list.add("http://b.hiphotos.baidu.com/image/pic/item/32fa828ba61ea8d3fcd2e9ce9e0a304e241f5803.jpg");
        mainXbanner.setData(list, null);
        mainXbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ImageView img = (ImageView) view;
                Glide.with(MainActivity.this).load(list.get(position)).into(img);
            }
        });
    }

    public void initListener() {
        mainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (checkedId) {
                    case R.id.main_home:
                        transaction.show(homeFragment)
                                .hide(hairringFragment)
                                .hide(searchFragment)
                                .hide(shoppingFragment)
                                .hide(mineFragment)
                                .commit();
                        mainHome.setTextColor(Color.parseColor("#ff0000"));
                        mainHairring.setTextColor(Color.parseColor("#000000"));
                        mainMine.setTextColor(Color.parseColor("#000000"));
                        mainSearch.setTextColor(Color.parseColor("#000000"));
                        mainShopping.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.main_hairring:
                        transaction.show(hairringFragment)
                                .hide(homeFragment)
                                .hide(searchFragment)
                                .hide(shoppingFragment)
                                .hide(mineFragment)
                                .commit();
                        mainHairring.setTextColor(Color.parseColor("#ff0000"));
                        mainHome.setTextColor(Color.parseColor("#000000"));
                        mainMine.setTextColor(Color.parseColor("#000000"));
                        mainSearch.setTextColor(Color.parseColor("#000000"));
                        mainShopping.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.main_search:
                        transaction.show(searchFragment)
                                .hide(hairringFragment)
                                .hide(homeFragment)
                                .hide(shoppingFragment)
                                .hide(mineFragment)
                                .commit();
                        mainSearch.setTextColor(Color.parseColor("#ff0000"));
                        mainHairring.setTextColor(Color.parseColor("#000000"));
                        mainMine.setTextColor(Color.parseColor("#000000"));
                        mainHome.setTextColor(Color.parseColor("#000000"));
                        mainShopping.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.main_shopping:
                        transaction.show(shoppingFragment)
                                .hide(hairringFragment)
                                .hide(searchFragment)
                                .hide(homeFragment)
                                .hide(mineFragment)
                                .commit();
                        mainShopping.setTextColor(Color.parseColor("#ff0000"));
                        mainHairring.setTextColor(Color.parseColor("#000000"));
                        mainMine.setTextColor(Color.parseColor("#000000"));
                        mainSearch.setTextColor(Color.parseColor("#000000"));
                        mainHome.setTextColor(Color.parseColor("#000000"));
                        break;
                    case R.id.main_mine:
                        transaction.show(mineFragment)
                                .hide(hairringFragment)
                                .hide(searchFragment)
                                .hide(shoppingFragment)
                                .hide(homeFragment)
                                .commit();
                        mainMine.setTextColor(Color.parseColor("#ff0000"));
                        mainHairring.setTextColor(Color.parseColor("#000000"));
                        mainHome.setTextColor(Color.parseColor("#000000"));
                        mainSearch.setTextColor(Color.parseColor("#000000"));
                        mainShopping.setTextColor(Color.parseColor("#000000"));
                        break;
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }


}
