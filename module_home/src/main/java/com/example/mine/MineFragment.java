package com.example.mine;

import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bean.HomePredictBean;
import com.example.bean.UserInfoBean;
import com.example.entity.EventBusBean;
import com.example.mine.adapter.MyToolAdapter;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseFragment;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.example.utils.SpaceItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 个人中心
 */
public class MineFragment extends BaseFragment<MineView, MinePresenter> implements MineView {

    @BindView(R2.id.mine_setting)
    ImageView mineSetting;
    @BindView(R2.id.mine_header)
    ImageView mineHeader;
    @BindView(R2.id.mine_lv)
    ImageView mineLv;
    @BindView(R2.id.mine_name)
    TextView mineName;
    @BindView(R2.id.mine_temp)
    LinearLayout mineTemp;
    @BindView(R2.id.mine_code)
    TextView mineCode;
    @BindView(R2.id.mine_copy)
    TextView mineCopy;
    @BindView(R2.id.mine_all_order)
    LinearLayout mineAllOrder;
    @BindView(R2.id.mine_yifukuan)
    LinearLayout mineYifukuan;
    @BindView(R2.id.mine_yijiesuan)
    LinearLayout mineYijiesuan;
    @BindView(R2.id.mine_yishixiao)
    LinearLayout mineYishixiao;
    @BindView(R2.id.mine_advice)
    ImageView mineAdvice;
    @BindView(R2.id.mine_income_form)
    LinearLayout mineIncomeForm;
    @BindView(R2.id.mine_fans_order)
    LinearLayout mineFansOrder;
    @BindView(R2.id.mine_group_fans)
    LinearLayout mineGroupFans;
    @BindView(R2.id.mine_up_yys)
    ImageView mineUpYys;
    @BindView(R2.id.mine_rec)
    RecyclerView mineRec;
    @BindView(R2.id.mine_parent)
    NestedScrollView mineParent;
    @BindView(R2.id.mine_rela)
    RelativeLayout mineRela;
    @BindView(R2.id.mine_benri)
    TextView mBenri;
    @BindView(R2.id.mine_benyue)
    TextView mBenyue;
    @BindView(R2.id.mine_shangyue)
    TextView mShangyue;

    private UserInfoBean userInfo;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData() {
        EventBus.getDefault().register(this);
        presenter.loadRec();
    }

    @Override
    public void initClick() {
        mineRela.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mineHeader.dispatchTouchEvent(event);
            }
        });

        mineHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToLogin();
            }
        });

        mineName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToLogin();
            }
        });

        mineSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToSetting();
            }
        });

        mineAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToUpgrade();
            }
        });

        mineAllOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder(0);
            }
        });

        mineYifukuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder(1);
            }
        });

        mineYijiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder(2);
            }
        });

        mineYishixiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToOrder(3);
            }
        });

        mineFansOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToFansOrder();
            }
        });

        mineGroupFans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToGroupFans();
            }
        });

        mineUpYys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToupYYS();
            }
        });

        mineIncomeForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.jumpToPredict();
            }
        });

        mineCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setClipboard(userInfo.getInviteCode());
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventBusBean eventBusBean) {
        if ("login".equals(eventBusBean.getMsg())) {
            presenter.loadData();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && !"".equals(SPUtil.getToken())) {
            presenter.getPredict();
        }
    }

    @Override
    public void loadPredict(HomePredictBean homePredictBean) {
        mBenri.setText(homePredictBean.getWaitCurrentMonth());
        mBenyue.setText(homePredictBean.getSettleCurrentMonth());
        mShangyue.setText(homePredictBean.getSettleLastMonth());
    }

    @Override
    public void loginSuccess(UserInfoBean userInfo) {
        this.userInfo = userInfo;
        mineName.setText(userInfo.getNickname());
        Glide.with(getContext()).load(userInfo.getIcon()).placeholder(R.drawable.vhjfg).apply(RequestOptions.circleCropTransform()).into(mineHeader);
        mineCode.setText(userInfo.getInviteCode());
        mineTemp.setVisibility(View.VISIBLE);
        if (userInfo.getLevel() != null) {
            mineLv.setVisibility(View.VISIBLE);
            if ("铜牌".equals(userInfo.getLevel())) {
                mineLv.setImageResource(R.drawable.lv_tong);
            } else if ("银牌".equals(userInfo.getLevel())) {
                mineLv.setImageResource(R.drawable.lv_yin);
            } else if ("金牌".equals(userInfo.getLevel())) {
                mineLv.setImageResource(R.drawable.lv_jin);
            } else {
                mineLv.setVisibility(View.GONE);
            }
        } else {
            mineLv.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError() {
        mineName.setText("请注册/登录");
        mineHeader.setImageResource(R.drawable.vhjfg);
        mineTemp.setVisibility(View.GONE);
    }

    @Override
    public void loadMyTool(MyToolAdapter adapter) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
        mineRec.setLayoutManager(layoutManager);
        mineRec.addItemDecoration(new SpaceItemDecoration(10, 10, 10, 10));
        mineRec.setAdapter(adapter);
    }

    @Override
    public MineView createView() {
        return this;
    }

    @Override
    public MinePresenter createPresenter() {
        return new MinePresenter(getContext());
    }
}
