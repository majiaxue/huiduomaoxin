package com.example.businessapplication;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.bean.CityBean;
import com.example.common.CommonResource;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.net.OnDataListener;
import com.example.net.OnMyCallBack;
import com.example.net.RetrofitUtil;
import com.example.utils.DisplayUtil;
import com.example.utils.ImageUtil;
import com.example.utils.LogUtil;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.example.utils.TxtUtil;
import com.example.view.addressselect.AddressSelector;
import com.example.view.addressselect.CityInterface;
import com.example.view.addressselect.OnItemClickListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * Created by cuihaohao on 2019/5/25
 * Describe:
 */
public class BusinessApplicationPresenter extends BasePresenter<BusinessApplicationView> {


    private Uri fileUri;//相册
    private Uri imagePathUri;//相机
    private String filePath = Environment.getExternalStorageDirectory() + "/fltk/image";
    private List<CityBean> cities1 = new ArrayList<>();
    private List<CityBean> cities2 = new ArrayList<>();
    private List<CityBean> cities3 = new ArrayList<>();
    private String cityName1 = "";
    private String cityName2 = "";
    private String cityName3 = "";

    public BusinessApplicationPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void popupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_bottom, null);
        final TextView popHeaderCancel = view.findViewById(R.id.pop_header_cancel);
        final TextView popHeaderCamera = view.findViewById(R.id.pop_header_camera);
        final TextView popHeaderXiangce = view.findViewById(R.id.pop_header_xiangce);
        PopUtils.setTransparency(mContext, 0.3f);
        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mContext, 146), new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                popHeaderCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                popHeaderCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openCamera();
                        pop.dismiss();
                    }
                });
                popHeaderXiangce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openPhotoAlbum();
                        pop.dismiss();
                    }
                });
            }
        });
    }

    public void popupAddressWhere(final TextView addressProvince, final TextView addressCity, final TextView addressArea) {

        addressProvince.setText("点击选择");
        addressCity.setText("");
        addressArea.setText("");

        final View view = LayoutInflater.from(mContext).inflate(R.layout.popup_address_select, null, false);
        final ImageView close = view.findViewById(R.id.address_select_close);
        final AddressSelector addressSelector = view.findViewById(R.id.address_selector);


        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {

                addressSelector.setTabAmount(3);
                city1(addressSelector, 1);

                addressSelector.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition) {
                        switch (tabPosition) {
                            case 0:
                                Toast.makeText(mContext, "tabPosition ：" + tabPosition + " " + city.getCityName() + city.getCityId(), Toast.LENGTH_SHORT).show();
                                city2(addressSelector, city.getCityId());
                                cityName1 = city.getCityName();
                                addressProvince.setText(cityName1);
//                                addressSelector.setCities(cities2);
                                break;
                            case 1:
                                Toast.makeText(mContext, "tabPosition ：" + tabPosition + " " + city.getCityName() + city.getCityId(), Toast.LENGTH_SHORT).show();
                                city3(addressSelector, city.getCityId());
                                cityName2 = city.getCityName();
                                addressCity.setText(cityName2);
//                                addressSelector.setCities(cities3);
                                break;
                            case 2:
                                Toast.makeText(mContext, "tabPosition ：" + tabPosition + " " + city.getCityName() + city.getCityId(), Toast.LENGTH_SHORT).show();
                                cityName3 = city.getCityName();
                                //关闭赋值
                                addressArea.setText(cityName3);
                                pop.dismiss();
                                break;
                        }
                    }
                });
                addressSelector.setOnTabSelectedListener(new AddressSelector.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
                        switch (tab.getIndex()) {
                            case 0:
                                addressSelector.setCities(cities1);
                                break;
                            case 1:
                                addressSelector.setCities(cities2);
                                break;
                            case 2:
                                addressSelector.setCities(cities3);
                                break;
                        }
                    }

                    @Override
                    public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {

                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopUtils.setTransparency(mContext, 1f);
                        pop.dismiss();
                    }
                });

            }
        });

        PopUtils.setTransparency(mContext, 0.3f);


    }

    private void city1(final AddressSelector addressSelector, int cityId) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.ADDRESSSELECT + "/" + cityId);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                LogUtil.e("addressResult-------------->" + result);
                List<CityBean> cityBeans = JSON.parseArray(result, CityBean.class);
                cities1.addAll(cityBeans);
                addressSelector.setCities(cities1);

            }

            @Override
            public void onError(String errorCode, String errorMsg) {
                LogUtil.e("addressErrorMsg-------------->" + errorMsg);
            }
        }));

    }

    private void city2(final AddressSelector addressSelector, int cityId) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.ADDRESSSELECT + "/" + cityId);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                List<CityBean> cityBeans = JSON.parseArray(result, CityBean.class);
                cities2.clear();
                cities2.addAll(cityBeans);
                addressSelector.setCities(cities2);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    private void city3(final AddressSelector addressSelector, int cityId) {
        Observable<ResponseBody> dataWithout = RetrofitUtil.getInstance().getApi(CommonResource.BASEURL_4001).getDataWithout(CommonResource.ADDRESSSELECT + "/" + cityId);
        RetrofitUtil.getInstance().toSubscribe(dataWithout, new OnMyCallBack(new OnDataListener() {
            @Override
            public void onSuccess(String result, String msg) {
                List<CityBean> cityBeans = JSON.parseArray(result, CityBean.class);
                cities3.clear();
                cities3.addAll(cityBeans);
                addressSelector.setCities(cities3);
            }

            @Override
            public void onError(String errorCode, String errorMsg) {

            }
        }));
    }

    public void popupGoodsClassify(final TextView businessApplicationShopClassifyText) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_select_goods_classify, null);
        TextView text = view.findViewById(R.id.popup_select_goods_classify_text);
        TxtUtil.txtJianbian(text, "#feb60e", "#fb4419");
        final ImageView imageClose = view.findViewById(R.id.popup_select_goods_classify_close);
        final RadioGroup popupRefundRadio = view.findViewById(R.id.popup_select_goods_classify_radio);
        final RadioButton but1 = view.findViewById(R.id.popup_select_goods_classify_but1);
        final RadioButton but2 = view.findViewById(R.id.popup_select_goods_classify_but2);
        final RadioButton but3 = view.findViewById(R.id.popup_select_goods_classify_but3);
        final RadioButton but4 = view.findViewById(R.id.popup_select_goods_classify_but4);
        final RadioButton but5 = view.findViewById(R.id.popup_select_goods_classify_but5);
        String textCause = businessApplicationShopClassifyText.getText().toString();
        if (textCause.equals(but1.getText().toString())) {
            but1.setChecked(true);
        } else if (textCause.equals(but2.getText().toString())) {
            but2.setChecked(true);
        } else if (textCause.equals(but3.getText().toString())) {
            but3.setChecked(true);
        } else if (textCause.equals(but4.getText().toString())) {
            but4.setChecked(true);
        } else if (textCause.equals(but5.getText().toString())) {
            but5.setChecked(true);
        } else {
            but1.setChecked(false);
            but2.setChecked(false);
            but3.setChecked(false);
            but4.setChecked(false);
            but5.setChecked(false);
        }
        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(mContext, 352), new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                imageClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop.dismiss();
                    }
                });
                popupRefundRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        if (checkedId == R.id.popup_select_goods_classify_but1) {
                            businessApplicationShopClassifyText.setText(but1.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_select_goods_classify_but2) {
                            businessApplicationShopClassifyText.setText(but2.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_select_goods_classify_but3) {
                            businessApplicationShopClassifyText.setText(but3.getText().toString());
                            pop.dismiss();
                        } else if (checkedId == R.id.popup_select_goods_classify_but4) {
                            businessApplicationShopClassifyText.setText(but4.getText().toString());
                            pop.dismiss();
                        } else {
                            businessApplicationShopClassifyText.setText(but5.getText().toString());
                            pop.dismiss();
                        }
                    }
                });
            }
        });
        PopUtils.setTransparency(mContext, 0.3f);
    }

    private void openCamera() {
//        imagePathUri = createImagePathUri(mContext);
//        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imagePathUri);
//        try {
//            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(imagePathUri));
//            String base64 = ImageUtil.bitmapToBase64(bitmap);
//            getView().showHeader(base64);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        getView().selectPhoto(imagePathUri);
//        getView().takePhoto(captureIntent);
        File file0 = new File(filePath);
        if (!file0.exists()) {
            file0.mkdirs();
        }
        File file = new File(filePath, System.currentTimeMillis() + ".jpg");

        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            fileUri = FileProvider.getUriForFile(mContext.getApplicationContext(), "com.lxy.taobaoke.provider", file);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        } else {
            fileUri = Uri.fromFile(file);
        }
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        getView().selectPhoto(fileUri);
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(fileUri));
            String base64 = ImageUtil.bitmapToBase64(bitmap);
            getView().showHeader(base64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getView().takePhoto(captureIntent);

    }

    public void openPhotoAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        getView().photoAlbum(intent);
    }

    public void parseUri(Intent intent) {
        fileUri = intent.getData();
        String type = intent.getType();
        if (fileUri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = fileUri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = mContext.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{MediaStore.Images.ImageColumns._ID}, buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    // set _id value
                    index = cur.getInt(index);
                }
                if (index == 0) {
                    // do nothing
                } else {
                    Uri uri_temp = Uri.parse("content://media/external/images/media/" + index);
                    if (uri_temp != null) {
                        fileUri = uri_temp;
                    }
                }
            }
        }
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(mContext.getContentResolver().openInputStream(fileUri));
            String base64 = ImageUtil.bitmapToBase64(bitmap);
            getView().showHeader(base64);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getView().selectPhoto(fileUri);
    }

//    /**
//     * 创建一条图片地址uri,用于保存拍照后的照片
//     *
//     * @param context
//     * @return 图片的uri
//     */
//    private static Uri createImagePathUri(final Context context) {
//        final Uri[] imageFilePath = {null};
//        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
//            imageFilePath[0] = Uri.parse("");
//        } else {
//            //拍照前保存一条uri的地址
//            String status = Environment.getExternalStorageState();
//            SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
//            long time = System.currentTimeMillis();
//            String imageName = timeFormatter.format(new Date(time));
//            String fileName;
//            String parentPath;
//
//            if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
//                parentPath = Environment.getExternalStorageDirectory().getPath();
//            } else {
//                parentPath = context.getExternalCacheDir().getPath();
//            }
//
//            fileName = parentPath + File.separator + imageName + ".webp";// + ".webp"
////            imageFilePath[0] = Uri.fromFile(new File(fileName));
//            imageFilePath[0] = getUriForFile(context, new File(fileName));
//        }
//
//        Log.d("tag", "生成的照片输出路径：" + imageFilePath[0].toString());
//        return imageFilePath[0];
//    }
//
//    //解决android版本大于7的问题
//    private static Uri getUriForFile(Context context, File file) {
//        if (context == null || file == null) {
//            throw new NullPointerException();
//        }
//        Uri uri;
//        //判断是否是AndroidN以及更高的版本
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//如果SDK版本>=24，即：Build.VERSION.SDK_INT >= 24
//            uri = FileProvider.getUriForFile(context.getApplicationContext(), "com.lxy.taobaoke.provider", file);
//        } else {
//            uri = Uri.fromFile(file);
//        }
//        return uri;
//    }

}
