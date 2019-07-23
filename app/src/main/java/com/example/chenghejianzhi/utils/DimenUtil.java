package com.example.chenghejianzhi.utils;

import android.content.Context;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/23 09:36
 * @describe ：
 */
public class DimenUtil {

    // dip转像素
    public static int dipToPixels(Context context ,final float dip) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        return (int) (dip * SCALE + 0.5f);
    }

    // 像素转dip
    public static float pixelsToDip(Context context,final int pixels) {
        final float SCALE = context.getResources().getDisplayMetrics().density;
        return pixels / SCALE;
    }
}
