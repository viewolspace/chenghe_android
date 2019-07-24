package com.example.chenghejianzhi.activity;

import com.example.base.base.BaseActivity;
import com.example.base.rx.RxEvent;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.utils.StatusBarUtils;

public class PersonalResumeActivity extends BaseActivity {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_personal_resume;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }
}
