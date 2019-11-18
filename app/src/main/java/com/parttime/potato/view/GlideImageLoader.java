package com.parttime.potato.view;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.parttime.potato.R;
import com.youth.banner.loader.ImageLoader;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/23 16:58
 * @describe ：
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        Glide.with(context).load(path).into(imageView);
    }
}
