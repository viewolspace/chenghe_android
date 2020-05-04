package com.parttime.diandi.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by zdy On 2019/7/27.
 */
public class BitmapUtil {

    public static String bitmaptoString(Bitmap bitmap) {
        // 将Bitmap转换成字符串
        String string = null;

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, bStream);

        byte[] bytes = bStream.toByteArray();

        string = Base64.encodeToString(bytes, Base64.DEFAULT);

        return string.replaceAll("[\\s*\t\n\r]", "");
    }

    public static BitmapFactory.Options getBitmapFactoryOptions(Context c, Uri uri, int width, int height) throws FileNotFoundException {
        if (width != -1 && height != -1) {
            return getOptimalSizeOptions(c, uri, width, height);
        } else {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            return options;
        }
    }

    public static BitmapFactory.Options getOptimalSizeOptions(
            Context context, Uri bitmapUri,
            int reqWidth, int reqHeight) throws FileNotFoundException {
        InputStream is = context.getContentResolver().openInputStream(bitmapUri);
        BitmapFactory.Options result = new BitmapFactory.Options();
        result.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, result);
        result.inJustDecodeBounds = false;
        result.inPreferredConfig = Bitmap.Config.ARGB_4444;

        result.inSampleSize = calculateInSampleSize(result, reqWidth, reqHeight);
        return result;
    }
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    public static Bitmap compressRGB565(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_4444;
        return BitmapFactory.decodeFile(path, options);
    }
}
