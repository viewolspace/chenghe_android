package com.example.chenghejianzhi.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;

import com.example.base.base.BaseFragment;
import com.example.base.rx.RxEvent;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.utils.FileProvider7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class MineFragment extends BaseFragment {
    private static final int REQUEST_CODE_LOCAL = 3001;

    private static final int REQUEST_CODE_CAMERA = 3003;
    private String cameraPicPath = "";
    public static String IMAGE_SAVE_PATH = Environment.getExternalStorageDirectory() + "/chenghejianzhi/photo/";
    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initWidget(View root) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }

    /**
     * 从相机获取图片
     */
    private void cameraAction() {
        String cameraSavePath = IMAGE_SAVE_PATH;
        File savedir = new File(cameraSavePath);
        if (!savedir.exists()) {
            savedir.mkdirs();
        }
        String fileName = "headpic_temp.jpg";
        File out = new File(cameraSavePath, fileName);
        cameraPicPath = out.getAbsolutePath();
        Uri uri = FileProvider7.getUriForFileWithPermission(getActivity(), out, MediaStore.ACTION_IMAGE_CAPTURE);//android 8新特性适配
        if (uri != null) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            this.startActivityForResult(intent,
                    REQUEST_CODE_CAMERA);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用本地图片
     */
    private void galleryAction() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        this.startActivityForResult(Intent.createChooser(intent, "选择图片"),
                REQUEST_CODE_LOCAL);
    }
    public void headPicReslut(final int requestCode, final Intent data) {


//                if (requestCode == REQUEST_CODE_CAMERA) {
//
//                }

                // 本地图片信息返回
                if (requestCode == REQUEST_CODE_LOCAL) {
                    if (data == null) {
                        return;
                    }
                    Uri thisUri = data.getData();
                    BitmapFactory.Options options = null;
//                    try {
//                        options = getBitmapFactoryOptions(activity, thisUri, 540, 200);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }

//                    InputStream is = null;
//                    try {
//                        is = activity.getContentResolver().openInputStream(thisUri);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }

//                    Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
//                    sendPic(bitmap,imagRspId);
                } else if (requestCode == REQUEST_CODE_CAMERA) { // 相机信息返回
//                    if (TextUtils.isEmpty(cameraPicPath)) {
//                        return;
//                    }
//                    Bitmap bitmap = compressRGB565(cameraPicPath);
//                    sendPic(bitmap,imagRspId);
                }
    }
}
