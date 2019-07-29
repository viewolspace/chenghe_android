package com.chenghe.parttime.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chenghe.base.base.BaseActivity;
import com.chenghe.base.rx.RxEvent;
import com.chenghe.parttime.R;
import com.chenghe.parttime.utils.StatusBarUtils;

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
