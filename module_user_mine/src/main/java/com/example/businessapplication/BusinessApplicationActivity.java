package com.example.businessapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.bean.BusinessApplicationBean;
import com.example.bean.SellerVo;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.module_user_mine.R2;
import com.example.mvp.BaseActivity;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.LogUtil;
import com.example.utils.PhoneNumUtil;
import com.example.utils.ProcessDialogUtil;
import com.example.utils.SPUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * 商家申请
 */
@Route(path = "/module_user_mine/BusinessApplicationActivity")
public class BusinessApplicationActivity extends BaseActivity<BusinessApplicationView, BusinessApplicationPresenter> implements BusinessApplicationView {
    @BindView(R2.id.include_back)
    ImageView includeBack;
    @BindView(R2.id.include_title)
    TextView includeTitle;
    @BindView(R2.id.business_application_icon)
    SimpleDraweeView businessApplicationIcon;
    @BindView(R2.id.business_application_shop_name)
    EditText businessApplicationShopName;
    @BindView(R2.id.business_application_shop_classify_text)
    TextView businessApplicationShopClassifyText;
    @BindView(R2.id.business_application_shop_classify)
    LinearLayout businessApplicationShopClassify;
    @BindView(R2.id.business_application_name)
    EditText businessApplicationName;
    @BindView(R2.id.business_application_phone)
    EditText businessApplicationPhone;
    @BindView(R2.id.business_application_shop_address)
    LinearLayout businessApplicationShopAddress;
    @BindView(R2.id.business_application_detail_address)
    EditText businessApplicationDetailAddress;
    @BindView(R2.id.business_application_front_photo)
    SimpleDraweeView businessApplicationFrontPhoto;
    @BindView(R2.id.business_application_verso_photo)
    SimpleDraweeView businessApplicationVersoPhoto;
    @BindView(R2.id.business_application_business_license)
    SimpleDraweeView businessApplicationBusinessLicense;
    @BindView(R2.id.business_application_food_safety_permit)
    SimpleDraweeView businessApplicationFoodSafetyPermit;
    @BindView(R2.id.business_application_submit)
    TextView businessApplicationSubmit;
    @BindView(R2.id.business_application_agreement)
    TextView businessApplicationAgreement;
    @BindView(R2.id.business_application_address_province)
    TextView businessApplicationAddressProvince;
    @BindView(R2.id.business_application_address_city)
    TextView businessApplicationAddressCity;
    @BindView(R2.id.business_application_address_area)
    TextView businessApplicationAddressArea;
    @BindView(R2.id.business_application_shop_type_text)
    TextView businessApplicationShopTypeText;
    @BindView(R2.id.business_application_shop_type)
    LinearLayout businessApplicationShopType;

    @Autowired(name = "from")
    String from;

    private final int TAKE_PHOTO_CODE = 0x111;
    private final int PHOTO_ALBUM_CODE = 0x222;
    private final int CROP_CODE = 0x333;


    private int type;
    private String base64;
    private Map<String, String> map = new HashMap<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_business_application;
    }

    @Override
    public void initData() {
        ARouter.getInstance().inject(this);
        includeTitle.setText("商家申请");

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

        //点击提交
        businessApplicationSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(businessApplicationShopName.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请输入店铺名!", Toast.LENGTH_SHORT).show();
                } else if ("点击选择".equals(businessApplicationShopClassifyText.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请选择商品分类!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(businessApplicationName.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请输入姓名!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(businessApplicationPhone.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请输入手机号!", Toast.LENGTH_SHORT).show();
                } else if (!PhoneNumUtil.isMobileNO(businessApplicationPhone.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请输入正确的手机号!", Toast.LENGTH_SHORT).show();
                } else if ("点击选择".equals(businessApplicationAddressProvince.getText().toString())) {
                    Toast.makeText(BusinessApplicationActivity.this, "请选择地址!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(map.get("0"))) {
                    Toast.makeText(BusinessApplicationActivity.this, "请选择头像", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(map.get("1"))) {
                    Toast.makeText(BusinessApplicationActivity.this, "请上传身份证正面", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(map.get("2"))) {
                    Toast.makeText(BusinessApplicationActivity.this, "请上传身份证背面", Toast.LENGTH_SHORT).show();

                } else if (TextUtils.isEmpty(map.get("3"))) {
                    Toast.makeText(BusinessApplicationActivity.this, "请上传营业执照", Toast.LENGTH_SHORT).show();

                } else {
                    ProcessDialogUtil.showProcessDialog(BusinessApplicationActivity.this);
                    SellerVo sellerVo = new SellerVo();
                    sellerVo.setUserCode(SPUtil.getUserCode());//SPUtil.getUserCode()"297881222686703616"
                    sellerVo.setSellerLogo(map.get("0"));
                    sellerVo.setSellerIdPositiveCardUrl(map.get("1"));
                    sellerVo.setSellerIdBackCardUrl(map.get("2"));
                    sellerVo.setSellerBusinessLicenseUrl(map.get("3"));
                    sellerVo.setSellerFoodSafetyPermitUrl(map.get("4"));

                    if (CommonResource.HISTORY_LOCAL.equals(from)) {
                        sellerVo.setSellerType("1");
                    } else {
                        sellerVo.setSellerType("0");
                    }
                    sellerVo.setSellerShopName(businessApplicationShopName.getText().toString());
                    sellerVo.setSellerCategory(businessApplicationShopClassifyText.getText().toString());
                    sellerVo.setSellerName(businessApplicationName.getText().toString());
                    sellerVo.setSellerPhone(businessApplicationPhone.getText().toString());
                    sellerVo.setSellerAddredd(businessApplicationAddressProvince.getText().toString() + " " + businessApplicationAddressCity.getText().toString() + " " + businessApplicationAddressArea.getText().toString() + " " + businessApplicationDetailAddress.getText().toString());
                    String sellerVoJson = JSON.toJSONString(sellerVo);

                    RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), sellerVoJson);

                    Observable<ResponseBody> responseBodyObservable = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_9003).postHeadWithBody(CommonResource.SELLERINFO, body, SPUtil.getToken());
                    RetrofitUtil.getInstance().toSubscribe(responseBodyObservable, new OnMyCallBack(new OnDataListener() {
                        @Override
                        public void onSuccess(String result, String msg) {
                            LogUtil.e("BusinessApplicationResult----------->" + result);
                            BusinessApplicationBean businessApplicationBean = JSON.parseObject(result, new TypeReference<BusinessApplicationBean>() {
                            }.getType());

                            String msg1 = businessApplicationBean.getMsg();
                            if (msg1.equals("success")) {
                                Toast.makeText(BusinessApplicationActivity.this, "商家申请成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(BusinessApplicationActivity.this, msg1, Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onError(String errorCode, String errorMsg) {
//                            ProcessDialogUtil.dismissDialog();
                            LogUtil.e("BusinessApplicationErrorMsg----------->" + errorMsg);
                        }
                    }));
                }
            }
        });
        //商家logo
        businessApplicationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openPhotoAlbum();
                type = 0;
            }
        });
        //身份证正面
        businessApplicationFrontPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
                type = 1;
            }
        });
        //身份证反面
        businessApplicationVersoPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
                type = 2;
            }
        });
        //营业执照
        businessApplicationBusinessLicense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
                type = 3;
            }
        });
        //食品安全许可证
        businessApplicationFoodSafetyPermit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupWindow();
                type = 4;
            }
        });
        //选择分类
        businessApplicationShopClassify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupGoodsClassify(businessApplicationShopClassifyText, from);
            }
        });
        //选择地址
        businessApplicationShopAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupAddressWhere(businessApplicationAddressProvince, businessApplicationAddressCity, businessApplicationAddressArea);
            }
        });
        //商家类型
        businessApplicationShopType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.popupGoodsType(businessApplicationShopTypeText, from);
            }
        });
    }

    @Override
    public BusinessApplicationView createView() {
        return this;
    }

    @Override
    public BusinessApplicationPresenter createPresenter() {
        return new BusinessApplicationPresenter(this);
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
    public void kedian() {
        businessApplicationShopClassify.setEnabled(true);
    }

    @Override
    public void selectPhoto(Uri uri) {
        if (type == 1) {
            businessApplicationFrontPhoto.setImageURI(uri);
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (type == 2) {
            businessApplicationVersoPhoto.setImageURI(uri);
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (type == 3) {
            businessApplicationBusinessLicense.setImageURI(uri);
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (type == 4) {
            businessApplicationFoodSafetyPermit.setImageURI(uri);
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            businessApplicationIcon.setImageURI(uri);
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(this.getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void showHeader(String bitmap) {
        this.base64 = bitmap;
        if (type == 1) {
            map.put("1", bitmap.replace("\n", ""));
        } else if (type == 2) {
            map.put("2", bitmap.replace("\n", ""));
        } else if (type == 3) {
            map.put("3", bitmap.replace("\n", ""));
        } else if (type == 4) {
            map.put("4", bitmap.replace("\n", ""));
        } else {
            map.put("0", bitmap.replace("\n", ""));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case TAKE_PHOTO_CODE:
                presenter.takePhoto();
                break;
            case PHOTO_ALBUM_CODE:
                presenter.parseUri(data);
                break;
            case CROP_CODE:
//                presenter.uploadPhoto();
                break;
        }
    }

}
