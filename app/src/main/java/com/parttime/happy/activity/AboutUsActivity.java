package com.parttime.happy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parttime.base.base.BaseActivity;
import com.parttime.base.constants.Constants;
import com.parttime.base.retrofit.ApiService;
import com.parttime.base.retrofit.RetrofitServiceCreator;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.rx.RxThrowableConsumer;
import com.parttime.base.rx.RxUtils;
import com.parttime.happy.R;
import com.parttime.happy.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutUsActivity extends BaseActivity {
    @BindView(R.id.tv_qq)
    TextView tvQq;

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutUsActivity.class);
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
        RetrofitServiceCreator.createService(this, ApiService.class).getAppQQ(Constants.APP)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle()).subscribe(contactBean -> tvQq.setText(contactBean.getShowMsg()),new RxThrowableConsumer());
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

}
