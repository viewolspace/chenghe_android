package com.example.chenghejianzhi.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.base.base.BaseActivity;
import com.example.base.bean.LoginBean;
import com.example.base.retrofit.ApiService;
import com.example.base.retrofit.RetrofitServiceCreator;
import com.example.base.rx.RxEvent;
import com.example.base.util.ToastUtils;
import com.example.base.util.UserInfoUtil;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.utils.BitmapUtil;
import com.example.chenghejianzhi.utils.FileProvider7;
import com.example.chenghejianzhi.utils.StatusBarUtils;
import com.example.chenghejianzhi.view.dilaog.ChooseBirthDayDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalResumeActivity extends BaseActivity {
    @BindView(R.id.iv_avatar)
    ImageView iv_avatar;
    @BindView(R.id.tv_birthday)
    TextView tv_birthday;
    @BindView(R.id.et_tv_work_exp)
    EditText et_tv_work_exp;
    @BindView(R.id.et_self_intro)
    EditText et_self_intro;
    @BindView(R.id.rg_menu)
    RadioGroup rg_menu;
    @BindView(R.id.et_nick_name)
    TextView et_nick_name;
    private static final int REQUEST_CODE_LOCAL = 3001;

    private static final int REQUEST_CODE_CAMERA = 3003;
    private String cameraPicPath = "";
    public static String IMAGE_SAVE_PATH = Environment.getExternalStorageDirectory() + "/chenghejianzhi/photo/";
    private ChooseBirthDayDialog chooseBirthDayDialog;
    private String headPic64 = "";
    int sex = 1;

    public static void start(Context context){
        Intent intent = new Intent(context,PersonalResumeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_personal_resume;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        LoginBean.UserInfo userInfo = UserInfoUtil.getInstance().getUserInfo();
        if (userInfo.getRealName()!=null&&!userInfo.getRealName().trim().isEmpty()){
            et_nick_name.setText(userInfo.getRealName());
        }
        if (userInfo.getBirthday()!=null&&!userInfo.getBirthday().trim().isEmpty()){
            tv_birthday.setText(userInfo.getBirthday());
        }
        et_tv_work_exp.setText(userInfo.getExp());
        et_self_intro.setText(userInfo.getDes());
        rg_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_man:
                        sex = 1;
                        break;
                    case R.id.rb_woman:
                        sex = 2;
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {
        if (event.getEventType() == RxEvent.EventType.USERINFO_UPDATE){
            finish();
        }

    }

    @OnClick({R.id.iv_avatar,R.id.tv_commit,R.id.tv_birthday,R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_avatar:
                galleryAction();
                break;
            case R.id.tv_commit:
                if (tv_birthday.getText()==null||
                        tv_birthday.getText().
                                toString().trim().isEmpty()
                ||"选择生日".equals(tv_birthday.getText().toString())){
                    ToastUtils.showShortToast("请选择生日");
                    return;
                }
                if (et_tv_work_exp.getText()==null||et_tv_work_exp.getText().toString().trim().isEmpty()){
                    ToastUtils.showShortToast("工作经历不能为空");
                    return;
                }
                if (et_self_intro.getText()==null||et_self_intro.getText().toString().trim().isEmpty()){
                    ToastUtils.showShortToast("自我简介不能为空");
                    return;
                }
                if (et_nick_name.getText()==null||et_nick_name.getText().toString().trim().isEmpty()){
                    ToastUtils.showShortToast("请输入真实名字");
                    return;
                }
                UserInfoUtil.getInstance().upDateUser(headPic64,sex,tv_birthday.getText().toString(),
                        et_tv_work_exp.getText().toString()
                        ,et_self_intro.getText().toString(),et_nick_name.getText().toString());
                break;
            case R.id.tv_birthday:
                showBirthDayDialog();

                break;
        }
    }

    private void showBirthDayDialog(){
        if (chooseBirthDayDialog!=null){
            chooseBirthDayDialog.show();
        }else {
            chooseBirthDayDialog = new ChooseBirthDayDialog(PersonalResumeActivity.this);
            chooseBirthDayDialog.getWindow().setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = chooseBirthDayDialog.getWindow().getAttributes(); // 获取对话框当前的参数值
            lp.x = 0; // 新位置X坐标
            lp.y = 0; // 新位置Y坐标
            lp.width = getResources().getDisplayMetrics().widthPixels; // 宽度
            chooseBirthDayDialog.setClickListener(new ChooseBirthDayDialog.ClickListener() {
                @Override
                public void onConfirmClick(String year, String month, String day) {
                    String birthday = String.format(Locale.ENGLISH,"%s.%s.%s",year,month,day);
                    tv_birthday.setText(birthday);
                }

                @Override
                public void onCancelClick() {

                }
            });
            chooseBirthDayDialog.show();
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        headPicResult(requestCode,data);
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
        Uri uri = FileProvider7.getUriForFileWithPermission(PersonalResumeActivity.this, out, MediaStore.ACTION_IMAGE_CAPTURE);//android 8新特性适配
        if (uri != null) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            this.startActivityForResult(intent,
                    REQUEST_CODE_CAMERA);
        }

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
    public void headPicResult(final int requestCode, final Intent data) {


        new Thread() {
            @Override
            public void run() {
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
                    try {
                        options =  BitmapUtil.getBitmapFactoryOptions(PersonalResumeActivity.this, thisUri, 540, 200);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    InputStream is = null;
                    try {
                        is = PersonalResumeActivity.this.getContentResolver().openInputStream(thisUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
                    //sendPic(bitmap,imagRspId);
                    headPic64 =  BitmapUtil.bitmaptoString(bitmap);
                    iv_avatar.post(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(iv_avatar).load(bitmap)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(iv_avatar);
                        }
                    });

                } else if (requestCode == REQUEST_CODE_CAMERA) {
                    if (TextUtils.isEmpty(cameraPicPath)) {
                        return;
                    }
                    Bitmap bitmap =  BitmapUtil.compressRGB565(cameraPicPath);
                    headPic64 = BitmapUtil.bitmaptoString(bitmap);
                    Glide.with(iv_avatar).load(bitmap)
                            .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                            .into(iv_avatar);
                    iv_avatar.post(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(iv_avatar).load(bitmap)
                                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                                    .into(iv_avatar);
                        }
                    });
                    //sendPic(bitmap,imagRspId);
                }
            }
        }.start();
    }

}
