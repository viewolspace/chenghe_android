package com.ch.parttime.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ch.base.constants.RoutMap;
import com.ch.parttime.R;
@Route(path = RoutMap.ACTIVITY_SPLASH)
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ARouter.getInstance().build(RoutMap.ACTIVITY_MAIN).navigation();
        finish();
    }
}
