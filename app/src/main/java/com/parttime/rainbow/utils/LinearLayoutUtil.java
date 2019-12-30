package com.parttime.rainbow.utils;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.parttime.rainbow.R;

/**
 * Created by zdy On 2019/7/27.
 */
public class LinearLayoutUtil {

    public static void setCompanyLevel(LinearLayout linearLayout,int level){
        if (level>5){
            level = 5;
        }
        linearLayout.removeAllViews();
        for (int i = 0;i<level;i++){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.rightMargin = DimenUtil.dipToPixels(linearLayout.getContext(),8);
            ImageView imageView1 = new ImageView(linearLayout.getContext());
            imageView1.setLayoutParams(lp);
            imageView1.setImageResource(R.drawable.xingxing_light);
            linearLayout.addView(imageView1);
        }
        for (int i = 0;i<5-level;i++){
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            ImageView imageView1 = new ImageView(linearLayout.getContext());
            imageView1.setLayoutParams(lp);
            imageView1.setImageResource(R.drawable.xingxing_unlignt);
            linearLayout.addView(imageView1);
        }


    }
}
