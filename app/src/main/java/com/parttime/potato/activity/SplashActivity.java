package com.parttime.potato.activity;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.parttime.base.constants.RoutMap;
import com.parttime.base.util.PermissionUtil;
import com.parttime.potato.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

@Route(path = RoutMap.ACTIVITY_SPLASH)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        RxPermissions rxPermissions = new RxPermissions(this);
        boolean isgranted = rxPermissions.isGranted( Manifest.permission.READ_PHONE_STATE);
        if (isgranted){
            UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE, null);
            MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
            // 支持在子进程中统计自定义事件
            UMConfigure.setProcessEvent(true);
            ARouter.getInstance().build(RoutMap.ACTIVITY_MAIN).navigation();
            finish();
            return;
        }

        PermissionUtil.requestPermissionCombined(this, new PermissionUtil.CombinedPermissionListenerImp() {
            @Override
            public void onCombinedGranted() {
                UMConfigure.init(SplashActivity.this,UMConfigure.DEVICE_TYPE_PHONE, null);
                MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
                // 支持在子进程中统计自定义事件
                UMConfigure.setProcessEvent(true);
                ARouter.getInstance().build(RoutMap.ACTIVITY_MAIN).navigation();
                finish();
            }
        }, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }
}
