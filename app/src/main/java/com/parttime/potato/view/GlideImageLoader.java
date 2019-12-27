package com.parttime.potato.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.parttime.potato.R;
import com.parttime.potato.utils.PhoneUtils;
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
        Glide.with(context).load(path)
                .apply(new RequestOptions().transform(new RoundedCorners(25)))
                .into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(PhoneUtils.dipToPixels(330),RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = PhoneUtils.dipToPixels(15);
        layoutParams.rightMargin = PhoneUtils.dipToPixels(15);
        imageView.setLayoutParams(layoutParams);
        return super.createImageView(context);
    }
}
