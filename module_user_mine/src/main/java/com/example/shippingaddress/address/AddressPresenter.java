package com.example.shippingaddress.address;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.adapter.MyRecyclerAdapter;
import com.example.module_user_mine.R;
import com.example.mvp.BasePresenter;
import com.example.shippingaddress.adapter.PopupRecAdapter;
import com.example.utils.OnPopListener;
import com.example.utils.PopUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cuihaohao on 2019/5/24
 * Describe:
 */
public class AddressPresenter extends BasePresenter<AddressView>{

    String data = "{\n" +
            "  \"provinceList\": [\n" +
            "    {\n" +
            "      \"name\": \"山东省\",\n" +
            "      \"id\": \"name\",\n" +
            "      \"cityList\": [\n" +
            "        {\n" +
            "          \"countyList\": [\n" +
            "            {\n" +
            "              \"name\": \"莱城区\",\n" +
            "              \"id\": \"371202\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"市辖区\",\n" +
            "              \"id\": \"371201\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"钢城区\",\n" +
            "              \"id\": \"371203\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"name\": \"莱芜市\",\n" +
            "          \"id\": \"name\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"countyList\": [\n" +
            "            {\n" +
            "              \"name\": \"市辖区\",\n" +
            "              \"id\": \"370101\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"历城区\",\n" +
            "              \"id\": \"370112\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"历下区\",\n" +
            "              \"id\": \"370102\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"长清区\",\n" +
            "              \"id\": \"370113\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"平阴县\",\n" +
            "              \"id\": \"370124\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"章丘市\",\n" +
            "              \"id\": \"370181\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"市中区\",\n" +
            "              \"id\": \"370103\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"济阳县\",\n" +
            "              \"id\": \"370125\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"槐荫区\",\n" +
            "              \"id\": \"370104\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"商河县\",\n" +
            "              \"id\": \"370126\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"天桥区\",\n" +
            "              \"id\": \"370105\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"name\": \"济南市\",\n" +
            "          \"id\": \"name\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"countyList\": [\n" +
            "            {\n" +
            "              \"name\": \"市辖区\",\n" +
            "              \"id\": \"371101\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"莒县\",\n" +
            "              \"id\": \"371122\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"岚山区\",\n" +
            "              \"id\": \"371103\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"东港区\",\n" +
            "              \"id\": \"371102\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"五莲县\",\n" +
            "              \"id\": \"371121\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"name\": \"日照市\",\n" +
            "          \"id\": \"name\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"countyList\": [\n" +
            "            {\n" +
            "              \"name\": \"西乡塘区\",\n" +
            "              \"id\": \"450107\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"邕宁区\",\n" +
            "              \"id\": \"450109\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"良庆区\",\n" +
            "              \"id\": \"450108\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"青秀区\",\n" +
            "              \"id\": \"450103\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"上林县\",\n" +
            "              \"id\": \"450125\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"兴宁区\",\n" +
            "              \"id\": \"450102\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"马山县\",\n" +
            "              \"id\": \"450124\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"江南区\",\n" +
            "              \"id\": \"450105\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"横县\",\n" +
            "              \"id\": \"450127\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"宾阳县\",\n" +
            "              \"id\": \"450126\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"武鸣区\",\n" +
            "              \"id\": \"450110\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"市辖区\",\n" +
            "              \"id\": \"450101\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"隆安县\",\n" +
            "              \"id\": \"450123\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"name\": \"南宁市\",\n" +
            "          \"id\": \"name\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"countyList\": [\n" +
            "            {\n" +
            "              \"name\": \"大化瑶族自治县\",\n" +
            "              \"id\": \"451229\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"都安瑶族自治县\",\n" +
            "              \"id\": \"451228\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"宜州市\",\n" +
            "              \"id\": \"451281\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"罗城仫佬族自治县\",\n" +
            "              \"id\": \"451225\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"金城江区\",\n" +
            "              \"id\": \"451202\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"东兰县\",\n" +
            "              \"id\": \"451224\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"巴马瑶族自治县\",\n" +
            "              \"id\": \"451227\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"环江毛南族自治县\",\n" +
            "              \"id\": \"451226\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"南丹县\",\n" +
            "              \"id\": \"451221\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"市辖区\",\n" +
            "              \"id\": \"451201\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"凤山县\",\n" +
            "              \"id\": \"451223\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"天峨县\",\n" +
            "              \"id\": \"451222\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"name\": \"河池市\",\n" +
            "          \"id\": \"name\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"errCode\": 0,\n" +
            "  \"errMsg\": \"获取成功\"\n" +
            "}";

    String selectStatus = "one";

    public AddressPresenter(Context context) {
        super(context);
    }

    @Override
    protected void onViewDestroy() {

    }

    public void popupAddressWhere(final TextView addressWhereText){

        View view = LayoutInflater.from(mContext).inflate(R.layout.popup_address_select, null, false);
        final ImageView close = view.findViewById(R.id.address_select_close);
        final ImageView one_status = view.findViewById(R.id.one_status);
        final ImageView two_status = view.findViewById(R.id.two_status);
        final ImageView three_status = view.findViewById(R.id.three_status);
        final TextView one_name = view.findViewById(R.id.one_name);
        final TextView two_name = view.findViewById(R.id.two_name);
        final TextView three_name = view.findViewById(R.id.three_name);
        final TextView line_1 = view.findViewById(R.id.line_1);
        final TextView line_2 = view.findViewById(R.id.line_2);
        final LinearLayout two_layout = view.findViewById(R.id.two_layout);
        final LinearLayout three_layout = view.findViewById(R.id.three_layout);
        RecyclerView data_rec = view.findViewById(R.id.data_rec);

        Gson gson = new Gson();
        Map mapData = gson.fromJson(data, Map.class);
        List<String> dataList = new ArrayList<>();
        List<Map> provinceList = (List) mapData.get("provinceList");
        for (int i = 0; i < provinceList.size(); i++) {
            String name = (String) provinceList.get(i).get("name");
            Log.d("MainActivity--------", "provinceList 里的 name --> "+name);
            dataList.add(name);
        }

        Log.d("MainActivity", "dataList.size() --> " + dataList.size());

        data_rec.setLayoutManager(new LinearLayoutManager(mContext));
        final PopupRecAdapter recAdapter = new PopupRecAdapter(mContext,dataList,R.layout.item_data_rec);
        data_rec.setAdapter(recAdapter);



        PopUtils.createPop(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, new OnPopListener() {
            @Override
            public void setOnPop(final PopupWindow pop) {
                recAdapter.setOnItemClick(new MyRecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(RecyclerView parent, View view, int position) {
                        TextView name = view.findViewById(R.id.place_name);

                        if (selectStatus.equals("one")){
                            one_name.setText(name.getText().toString());
                            one_name.setTextColor(Color.parseColor("#666666"));
                            one_status.setImageResource(R.mipmap.selected);
                            selectStatus = "two";
                            line_1.setVisibility(View.VISIBLE);
                            two_layout.setVisibility(View.VISIBLE);
                            recAdapter.notifyDataSetChanged();


                        }else if (selectStatus.equals("two")){
                            two_name.setText(name.getText().toString());
                            two_name.setTextColor(Color.parseColor("#666666"));
                            two_status.setImageResource(R.mipmap.selected);
                            selectStatus = "three";
                            line_2.setVisibility(View.VISIBLE);
                            three_layout.setVisibility(View.VISIBLE);
                            recAdapter.notifyDataSetChanged();
                        }else if (selectStatus.equals("three")){
                            three_name.setText(name.getText().toString());
                            three_name.setTextColor(Color.parseColor("#666666"));
                            three_status.setImageResource(R.mipmap.selected);
                            recAdapter.notifyDataSetChanged();
                        }
                    }
                });

                one_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectStatus = "one";
                        one_name.setText("请选择省份");
                        one_name.setTextColor(Color.parseColor("#eb6100"));
                        two_status.setImageResource(R.mipmap.unselect);
                        one_status.setImageResource(R.mipmap.unselect);
                        three_status.setImageResource(R.mipmap.unselect);
                        line_1.setVisibility(View.GONE);
                        two_layout.setVisibility(View.GONE);
                        line_2.setVisibility(View.GONE);
                        three_layout.setVisibility(View.GONE);
                    }
                });

                two_name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectStatus = "two";
                        two_name.setText("请选择市");
                        two_name.setTextColor(Color.parseColor("#eb6100"));
                        line_2.setVisibility(View.GONE);
                        two_status.setImageResource(R.mipmap.unselect);
                        three_layout.setVisibility(View.GONE);
                        three_status.setImageResource(R.mipmap.unselect);

                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PopUtils.setTransparency(mContext,1f);
                        pop.dismiss();
                    }
                });

            }
        });
        PopUtils.setTransparency(mContext,0.3f);

    }
}
