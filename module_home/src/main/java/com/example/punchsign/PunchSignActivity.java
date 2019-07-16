package com.example.punchsign;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.bean.PunchSignBean;
import com.example.module_home.R;
import com.example.module_home.R2;
import com.example.mvp.BaseActivity;
import com.example.utils.LogUtil;

import butterknife.BindView;

/**
 * 打卡签到
 */
@Route(path = "/module_home/PunchSignActivity")
public class PunchSignActivity extends BaseActivity<PunchSignView, PunchSignPresenter> implements PunchSignView {

    @BindView(R2.id.punch_sign_image)
    ImageView punchSignImage;
    @BindView(R2.id.punch_sign_gui_ze)
    TextView punchSignGuiZe;
    @BindView(R2.id.punch_sign_ji_fen)
    TextView punchSignJiFen;
    @BindView(R2.id.punch_sign_bottom_qian_dao)
    ImageView punchSignBottomQianDao;
    @BindView(R2.id.punch_sign_several_days)
    TextView punchSignSeveralDays;
    @BindView(R2.id.punch_sign_ji_fen_zhi)
    TextView punchSignJiFenZhi;
    @BindView(R2.id.punch_sign_text1)
    TextView punchSignText1;
    @BindView(R2.id.punch_sign_text11)
    TextView punchSignText11;
    @BindView(R2.id.punch_sign_text111)
    TextView punchSignText111;
    @BindView(R2.id.punch_sign_text2)
    TextView punchSignText2;
    @BindView(R2.id.punch_sign_text22)
    TextView punchSignText22;
    @BindView(R2.id.punch_sign_text222)
    TextView punchSignText222;
    @BindView(R2.id.punch_sign_text3)
    TextView punchSignText3;
    @BindView(R2.id.punch_sign_text33)
    TextView punchSignText33;
    @BindView(R2.id.punch_sign_text333)
    TextView punchSignText333;
    @BindView(R2.id.punch_sign_text4)
    TextView punchSignText4;
    @BindView(R2.id.punch_sign_text44)
    TextView punchSignText44;
    @BindView(R2.id.punch_sign_text444)
    TextView punchSignText444;
    @BindView(R2.id.punch_sign_text5)
    TextView punchSignText5;
    @BindView(R2.id.punch_sign_text55)
    TextView punchSignText55;
    @BindView(R2.id.punch_sign_text555)
    TextView punchSignText555;
    @BindView(R2.id.punch_sign_text6)
    TextView punchSignText6;
    @BindView(R2.id.punch_sign_text66)
    TextView punchSignText66;
    @BindView(R2.id.punch_sign_text666)
    TextView punchSignText666;
    @BindView(R2.id.punch_sign_text7)
    TextView punchSignText7;
    @BindView(R2.id.punch_sign_text77)
    TextView punchSignText77;
    @BindView(R2.id.punch_sign_text777)
    TextView punchSignText777;
    @BindView(R2.id.punch_sign_text2222)
    TextView punchSignText2222;
    @BindView(R2.id.punch_sign_text3333)
    TextView punchSignText3333;
    @BindView(R2.id.punch_sign_text4444)
    TextView punchSignText4444;
    @BindView(R2.id.punch_sign_text6666)
    TextView punchSignText6666;
    @BindView(R2.id.punch_sign_text7777)
    TextView punchSignText7777;

    private PunchSignBean punchSignBean;
    private int allJiFen;

    @Override
    public int getLayoutId() {
        return R.layout.activity_punch_sign;
    }

    @Override
    public void initData() {
        //查询签到任务完成度
        presenter.signQuery();
    }

    @Override
    public void initClick() {
        punchSignImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        punchSignGuiZe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击弹出规则
                presenter.popGuiZe(punchSignGuiZe);
            }
        });
        //签到
        punchSignBottomQianDao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.qiandao();
            }
        });
        //每日一件商品签到
        punchSignText111.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.meiRiQianDao(0);
            }
        });
        //多件商品签到
        punchSignText222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.meiRiQianDao(1);
            }
        });
        //每日分享
        punchSignText333.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.shareCount();
            }
        });
        //每日邀请好友注册
        punchSignText444.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.yaoQingHaoYou();
            }
        });
        //首次下单
        punchSignText555.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.firstOrder();
            }
        });
        //有效订单
        punchSignText666.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.order();
            }
        });
        //粉丝数量
        punchSignText777.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.fans();
            }
        });

    }

    @Override
    public PunchSignView createView() {
        return this;
    }

    @Override
    public PunchSignPresenter createPresenter() {
        return new PunchSignPresenter(this);
    }

    @Override
    public void punchSign(PunchSignBean punchSignBean) {
        this.punchSignBean = punchSignBean;
        allJiFen = punchSignBean.getResult().getIntegration();
        LogUtil.e("punchSignBean" + punchSignBean);
        punchSignJiFen.setText("" + allJiFen);
        int continueDay = punchSignBean.getFinish().getContinueDay();
        int daySign = punchSignBean.getSignSetting().getDaySign();
        int continueDay1 = punchSignBean.getSignSetting().getContinueDay();
        int continueSign = punchSignBean.getSignSetting().getContinueSign();
        int jifen = continueDay * daySign + continueDay / continueDay1 * continueSign;
        punchSignSeveralDays.setText("" + continueDay);
        punchSignJiFenZhi.setText("" + jifen);
        punchSignText1.setText("+" + punchSignBean.getSignSetting().getViewGoods());
        if (punchSignBean.getResult().getViewGoods() == 1) {
            punchSignText11.setText("完成1/1");
            punchSignText111.setText("已完成");
        }
        punchSignText2.setText("+" + punchSignBean.getSignSetting().getViewMoreGoods());
        punchSignText2222.setText("每日浏览" + punchSignBean.getSignSetting().getGoodsNum() + "件商品以上");
        punchSignText22.setText("完成" + 0 + "/" + punchSignBean.getSignSetting().getGoodsNum());
        if (punchSignBean.getResult().getViewMoreGoods() == 1) {
            punchSignText22.setText("完成" + punchSignBean.getSignSetting().getGoodsNum() + "/" + punchSignBean.getSignSetting().getGoodsNum());
            punchSignText222.setText("已完成");
        }
        punchSignText3.setText("+" + punchSignBean.getSignSetting().getShareGoods());
        punchSignText3333.setText("每日分享一件商品，上限" + punchSignBean.getSignSetting().getShareNum() + "次");
        punchSignText33.setText("完成" + 0 + "/" + punchSignBean.getSignSetting().getShareNum());
        if (punchSignBean.getResult().getShareGoods() == 3) {
            punchSignText33.setText("完成" + punchSignBean.getSignSetting().getShareNum() + "/" + punchSignBean.getSignSetting().getShareNum());
            punchSignText333.setText("已完成");
        }
        punchSignText4.setText("+" + punchSignBean.getSignSetting().getInviteUser());
        punchSignText4444.setText("每日邀请" + punchSignBean.getSignSetting().getInviteNum() + "个好友注册");
        punchSignText44.setText("完成" + 0 + "/" + punchSignBean.getSignSetting().getInviteNum());
        if (punchSignBean.getResult().getInviteUser() == 1) {
            punchSignText44.setText("完成" + punchSignBean.getSignSetting().getInviteNum() + "/" + punchSignBean.getSignSetting().getInviteNum());
            punchSignText444.setText("已完成");
        }
        punchSignText5.setText("+" + punchSignBean.getNewUserConf().getFirstOrder());
        if (punchSignBean.getResult().getFirstOrder() == 1) {
            punchSignText55.setText("完成1/1");
            punchSignText555.setText("已完成");
        }
        punchSignText6.setText("+" + punchSignBean.getNewUserConf().getOrderIntegration());
        punchSignText6666.setText(punchSignBean.getNewUserConf().getOrderNum() + "有效订单(自购或分享)");
        punchSignText66.setText("完成" + 0 + "/" + punchSignBean.getNewUserConf().getOrderNum());
        if (punchSignBean.getResult().getOrderIntegration() == 3) {
            punchSignText66.setText("完成" + punchSignBean.getNewUserConf().getOrderNum() + "/" + punchSignBean.getNewUserConf().getOrderNum());
            punchSignText666.setText("已完成");
        }
        punchSignText7.setText("+" + punchSignBean.getNewUserConf().getFansIntegration());
        punchSignText7777.setText("邀请粉丝" + punchSignBean.getNewUserConf().getFansNum() + "人以上");
        punchSignText77.setText("完成" + 0 + "/" + punchSignBean.getNewUserConf().getFansNum());
        if (punchSignBean.getResult().getFansIntegration() == 1) {
            punchSignText77.setText("完成" + punchSignBean.getNewUserConf().getFansNum() + "/" + punchSignBean.getNewUserConf().getFansNum());
            punchSignText777.setText("已完成");
        }
    }

    @Override
    public void qianDao() {
        Toast.makeText(this, "签到成功", Toast.LENGTH_SHORT).show();
        int continueDay = punchSignBean.getFinish().getContinueDay();
        int daySign = punchSignBean.getSignSetting().getDaySign();
        int continueDay1 = punchSignBean.getSignSetting().getContinueDay();
        int continueSign = punchSignBean.getSignSetting().getContinueSign();
        int jiFen = continueDay * daySign + continueDay / continueDay1 * continueSign;
        int newDay = continueDay + 1;
        int newJiFen = jiFen + daySign;
        allJiFen = allJiFen + daySign;

        punchSignJiFen.setText("" + allJiFen);
        punchSignSeveralDays.setText("" + newDay);
        punchSignJiFenZhi.setText("" + newJiFen);
    }

    @Override
    public void meiRiQianDao(int type) {
        Toast.makeText(this, "任务已完成", Toast.LENGTH_SHORT).show();
        if (type == 0) {
            int viewGoods = punchSignBean.getSignSetting().getViewGoods();
            allJiFen = viewGoods + allJiFen;
            punchSignJiFen.setText("" + allJiFen);
            punchSignText11.setText("完成1/1");
            punchSignText111.setText("已完成");
        } else {
            int viewMoreGoods = punchSignBean.getSignSetting().getViewMoreGoods();
            allJiFen = viewMoreGoods + allJiFen;
            punchSignJiFen.setText("" + allJiFen);
            punchSignText22.setText("完成" + punchSignBean.getSignSetting().getGoodsNum() + "/" + punchSignBean.getSignSetting().getGoodsNum());
            punchSignText222.setText("已完成");
        }
    }

    @Override
    public void shareCount() {
        Toast.makeText(this, "任务已完成", Toast.LENGTH_SHORT).show();
        int shareGoods = punchSignBean.getSignSetting().getShareGoods();
        allJiFen = shareGoods + allJiFen;
        punchSignJiFen.setText("" + allJiFen);
        punchSignText33.setText("完成" + punchSignBean.getSignSetting().getShareNum() + "/" + punchSignBean.getSignSetting().getShareNum());
        punchSignText333.setText("已完成");
    }

    @Override
    public void yaoQingHaoYou() {
        Toast.makeText(this, "任务已完成", Toast.LENGTH_SHORT).show();
        int inviteUser = punchSignBean.getSignSetting().getInviteUser();
        allJiFen = inviteUser + allJiFen;
        punchSignJiFen.setText("" + allJiFen);
        punchSignText44.setText("完成" + punchSignBean.getSignSetting().getInviteNum() + "/" + punchSignBean.getSignSetting().getInviteNum());
        punchSignText444.setText("已完成");
    }

    @Override
    public void firstOrder() {
        int firstOrder = punchSignBean.getNewUserConf().getFirstOrder();
        allJiFen = firstOrder + allJiFen;
        punchSignJiFen.setText("" + allJiFen);
        punchSignText55.setText("完成1/1");
        punchSignText555.setText("已完成");
    }

    @Override
    public void order() {
        int orderIntegration = punchSignBean.getNewUserConf().getOrderIntegration();
        allJiFen = orderIntegration + allJiFen;
        punchSignJiFen.setText("" + allJiFen);
        punchSignText77.setText("完成" + punchSignBean.getNewUserConf().getOrderNum() + "/" + punchSignBean.getNewUserConf().getOrderNum());
        punchSignText777.setText("已完成");
    }

    @Override
    public void fans() {
        int fansIntegration = punchSignBean.getNewUserConf().getFansIntegration();
        allJiFen = fansIntegration + allJiFen;
        punchSignJiFen.setText("" + allJiFen);
        punchSignText77.setText("完成" + punchSignBean.getNewUserConf().getFansNum() + "/" + punchSignBean.getNewUserConf().getFansNum());
        punchSignText777.setText("已完成");
    }

}
