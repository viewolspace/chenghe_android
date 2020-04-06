package com.parttime.songshu.activity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.parttime.base.constants.RoutMap;
import com.parttime.base.util.SpUtil;
import com.parttime.songshu.R;
import com.parttime.songshu.utils.StatusBarUtils;
import com.parttime.songshu.view.dilaog.AgreementDialog;

@Route(path = RoutMap.ACTIVITY_SPLASH)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        StatusBarUtils.statusbar(this);
        Log.e("SplashActivity",getAppMetaData(this, "UMENG_CHANNEL"));
        boolean agreeMent = SpUtil.getBoolean(this,"isAgreementPrivacy",false);
        if (agreeMent){
            ARouter.getInstance().build(RoutMap.ACTIVITY_MAIN).navigation();
            finish();
        }else {
            AgreementDialog agreementDialog = new AgreementDialog(this);
            agreementDialog.setClickListener(new AgreementDialog.ClickListener() {
                @Override
                public void onConfirmClick() {
                    SpUtil.putBoolean(SplashActivity.this,"isAgreementPrivacy",true);
                    ARouter.getInstance().build(RoutMap.ACTIVITY_MAIN).navigation();
                    finish();
                }

                @Override
                public void onCancelClick() {
                    finish();
                }
            });
            agreementDialog.show();
        }

    }
    /**
     * 获取app当前的渠道号或application中指定的meta-data
     *
     * @return 如果没有获取成功(没有对应值，或者异常)，则返回值为空
     */
    public static String getAppMetaData(Context context, String key) {
        if (context == null || TextUtils.isEmpty(key)) {
            return null;
        }
        String channelNumber = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                if (applicationInfo != null) {
                    if (applicationInfo.metaData != null) {
                        channelNumber = applicationInfo.metaData.getString(key);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return channelNumber;
    }
}
