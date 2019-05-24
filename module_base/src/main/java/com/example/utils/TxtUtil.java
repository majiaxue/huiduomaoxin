package com.example.utils;

import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.widget.TextView;

public class TxtUtil {
    public static void txtJianbian(TextView txt, String startColor, String endColor) {
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, txt.getPaint().getTextSize(), Color.parseColor(startColor), Color.parseColor(endColor), Shader.TileMode.CLAMP);
        txt.getPaint().setShader(linearGradient);
        txt.invalidate();
    }
}
