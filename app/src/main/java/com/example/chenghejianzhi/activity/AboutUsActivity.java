package com.example.chenghejianzhi.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.base.base.BaseActivity;
import com.example.base.rx.RxEvent;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.utils.StatusBarUtils;

import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {
    public static void start(Context context){
        Intent intent = new Intent(context,AboutUsActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
    @Override
    public void handleDefaultEvent(RxEvent event) {

    }
}
