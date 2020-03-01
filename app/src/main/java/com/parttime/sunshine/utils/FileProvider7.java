package com.parttime.sunshine.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.parttime.base.util.ToastUtils;

import java.io.File;

/**
 * @Author zhengdengyao
 * @Created 2019/4/26 0026
 * @Description
 */
public class FileProvider7 {
    public static Uri getUriForFile(Context context, File file) {
        Uri fileUri = null;
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = getUriForFile24(context, file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        return fileUri;
    }
    public static Uri getUriForFileWithPermission(Activity context, File file, String action) {
        if (action!=null&&android.provider.MediaStore.ACTION_IMAGE_CAPTURE.equals(action)){
            int isGranted = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            if (isGranted == PackageManager.PERMISSION_GRANTED) {
                //已授权
                Uri fileUri = null;
                if (Build.VERSION.SDK_INT >= 24) {
                    fileUri = getUriForFile24(context, file);
                } else {
                    fileUri = Uri.fromFile(file);
                }
                return fileUri;
            }else if(isGranted == PackageManager.PERMISSION_DENIED){
                //未授权的
                ActivityCompat.requestPermissions(context,new String[]{Manifest.permission.CAMERA},0);
                ToastUtils.showShortToast("该功能需要相机权限，请授予相机权限");
            }
        }
        return null;

    }
    public static Uri getUriForFile24(Context context, File file) {
        Uri fileUri = android.support.v4.content.FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        return fileUri;
    }

    public static void setIntentDataAndType(Context context, Intent intent, String type, File file, boolean writeAble) {
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setDataAndType(getUriForFile(context, file), type);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (writeAble) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
        } else {
            intent.setDataAndType(Uri.fromFile(file), type);
        }
    }
}
