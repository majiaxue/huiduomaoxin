package com.example.refund;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.example.bean.OrderDetailBean;
import com.example.common.CommonResource;
import com.example.bean.MineOrderBean;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.bean.RefundApplyVo;
import com.example.utils.ImageUtil;
import com.example.utils.LogUtil;
import com.example.utils.SPUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 退款申请
 */
@Route(path = "/module_user_mine/RefundActivity")
public class RefundActivity extends BaseActivity<RefundView, RefundPresenter> implements RefundView {

    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.refund_image)
    SimpleDraweeView refundImage;
    @BindView(R2.id.refund_goods_name)
    TextView refundGoodsName;
    @BindView(R2.id.refund_colour)
    TextView refundColour;
    @BindView(R2.id.refund_size)
    TextView refundSize;
    @BindView(R2.id.refund_cause_text)
    TextView refundCauseText;
    @BindView(R2.id.refund_cause)
    LinearLayout refundCause;
    @BindView(R2.id.refund_type_text)
    TextView refundTypeText;
    @BindView(R2.id.refund_type)
    LinearLayout refundType;
    @BindView(R2.id.refund_sum_text)
    TextView refundSumText;
    @BindView(R2.id.refund_explain_edit)
    EditText refundExplainEdit;
    @BindView(R2.id.refund_add_photo)
    SimpleDraweeView refundAddPhoto;
    @BindView(R2.id.refund_submit)
    TextView refundSubmit;
    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;
    @Autowired(name = "bean")
    public OrderDetailBean bean;

    @Autowired(name = "orderDetailBean")
    OrderDetailBean orderDetailBean;

    @Autowired(name = "mineOrderBean")
    MineOrderBean mineOrderBean1;

    @Autowired(name = "position")
    int position;

    @Autowired(name = "type")
    String type;


    private String Base64;
    private List<MineOrderBean> list = new ArrayList<>();
    private List<String> images = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_refund;
    }

    @Override
    public void initData() {
        includeTitle.setText("退款申请");
        ARouter.getInstance().inject(this);
        LogUtil.e("beanList退款申请" + mineOrderBean1);
        list.add(mineOrderBean1);
        if ("1".equals(type)) {
            refundSumText.setText("" + list.get(0).getOrderList().get(position).getTotalAmount());
            refundImage.setImageURI(list.get(0).getOrderList().get(position).getOrderItems().get(0).getProductPic());
            refundGoodsName.setText(list.get(0).getOrderList().get(position).getOrderItems().get(0).getProductName());
            refundColour.setText(list.get(0).getOrderList().get(position).getOrderItems().get(0).getSp1());
            refundSize.setText(list.get(0).getOrderList().get(position).getOrderItems().get(0).getSp2());
        } else {
            refundSumText.setText("" + orderDetailBean.getPayAmount());
            refundImage.setImageURI(orderDetailBean.getItems().get(0).getProductPic());
            refundGoodsName.setText(orderDetailBean.getItems().get(0).getProductName());
            refundColour.setText(orderDetailBean.getItems().get(0).getSp1());
            refundSize.setText(orderDetailBean.getItems().get(0).getSp2());
        }

    }

    @Override
    public void initClick() {
        //返回
        includeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //退款原因
        refundCause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupCause(refundCauseText);
            }
        });
        //退款类型
        refundType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupType(refundTypeText);
            }
        });
        //添加照片
        refundAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
            }
        });
        //提交
        refundSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("1".equals(type)) {
                    RefundApplyVo refundApplyVo = new RefundApplyVo();
                    refundApplyVo.setOrderId(list.get(0).getOrderList().get(position).getOrderId() + "");
                    refundApplyVo.setProductId(list.get(0).getOrderList().get(position).getOrderItems().get(0).getProductId() + "");
                    refundApplyVo.setProductName(list.get(0).getOrderList().get(position).getOrderItems().get(0).getProductName());
                    refundApplyVo.setOrderSn(list.get(0).getOrderList().get(position).getOrderItems().get(0).getOrderSn());
                    refundApplyVo.setProofPics(images);
                    refundApplyVo.setReason(refundCauseText.getText().toString());
                    refundApplyVo.setMemberUsername(SPUtil.getStringValue(CommonResource.USER_NAME));
                    refundApplyVo.setProductPrice(list.get(0).getOrderList().get(position).getOrderItems().get(0).getProductPrice());
                    refundApplyVo.setProductRealPrice(Double.valueOf(refundSumText.getText().toString()));
                    refundApplyVo.setSellerId("");
                    String jsonString = JSON.toJSONString(refundApplyVo);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
                    Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.REFUNDAPPLY, requestBody, SPUtil.getToken());
                    RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("退款申请--------->" + result);
                            if ("true".equals(result)) {
                                ARouter.getInstance().build("/module_user_mine/RefundSucceedActivity").navigation();
                            }
                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
                            LogUtil.e("退款申请errorMsg--------->" + errorMsg);
                        }
                    }));

                } else {
                    RefundApplyVo refundApplyVo = new RefundApplyVo();
                    refundApplyVo.setOrderId(orderDetailBean.getItems().get(0).getOrderId() + "");
                    refundApplyVo.setProductId(orderDetailBean.getItems().get(0).getProductId() + "");
                    refundApplyVo.setProductName(orderDetailBean.getItems().get(0).getProductName());
                    refundApplyVo.setOrderSn(orderDetailBean.getItems().get(0).getOrderSn());
                    refundApplyVo.setProofPics(images);
                    refundApplyVo.setReason(refundCauseText.getText().toString());
                    refundApplyVo.setMemberUsername(SPUtil.getStringValue(CommonResource.USER_NAME));
                    refundApplyVo.setProductPrice(orderDetailBean.getPayAmount());
                    refundApplyVo.setProductRealPrice(Double.valueOf(refundSumText.getText().toString()));
                    refundApplyVo.setSellerId("");
                    String jsonString = JSON.toJSONString(refundApplyVo);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
                    Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9004).postHeadWithBody(CommonResource.REFUNDAPPLY, requestBody, SPUtil.getToken());
                    RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("退款申请--------->" + result);
                            if ("true".equals(result)) {
                                ARouter.getInstance().build("/module_user_mine/RefundSucceedActivity").navigation();
                            }
                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
                            LogUtil.e("退款申请errorMsg--------->" + errorMsg);
                        }
                    }));
                }
            }
        });
    }

    @Override
    public RefundView createView() {
        return this;
    }

    @Override
    public RefundPresenter createPresenter() {
        return new RefundPresenter(this);
    }

    @Override
    public void takePhoto(Intent intent) {
        startActivityForResult(intent, TAKE_PHOTO_CODE);
    }

    @Override
    public void photoAlbum(Intent intent) {
        startActivityForResult(intent, PHOTO_ALBUM_CODE);
    }

    @Override
    public void selectPhoto(Uri uri) {
        refundAddPhoto.setImageURI(uri);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            String base64 = ImageUtil.bitmapToBase64(bitmap);

            LogUtil.e("base64" + uri + base64);
            images.add(base64);
        } catch (Exception e) {
        }
    }

    @Override
    public void showHeader(String base64) {
        LogUtil.e("base64" + base64);
        images.add(base64);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.uploadPhoto();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
            case CROP_CODE:
                break;
        }
    }
}
