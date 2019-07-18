package com.example.utils;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtUtil {
    public static void txtJianbian(TextView txt, String startColor, String endColor) {
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, txt.getPaint().getTextSize(), Color.parseColor(startColor), Color.parseColor(endColor), Shader.TileMode.CLAMP);
        txt.getPaint().setShader(linearGradient);
        txt.invalidate();
    }

    public static String parse(long value) {
        if (value >= 10000) {
            double v = value * 1.0 / 10000;
            return new BigDecimal(v).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue() + "万人";
        }
        return value + "人";
    }
}
