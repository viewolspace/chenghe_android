package com.ch.parttime.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ch.base.base.BaseActivity;
import com.ch.base.bean.BaseBean;
import com.ch.base.constants.Constants;
import com.ch.base.retrofit.ApiService;
import com.ch.base.retrofit.RetrofitServiceCreator;
import com.ch.base.rx.RxEvent;
import com.ch.base.rx.RxThrowableConsumer;
import com.ch.base.rx.RxUtils;
import com.ch.base.util.ToastUtils;
import com.ch.parttime.R;
import com.ch.parttime.utils.StatusBarUtils;
import com.ch.parttime.view.dilaog.FeedBackConfirmDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class FeedBackActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.view_fill)
    View viewFill;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.et_feedback)
    EditText etFeedback;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    public static void start(Context context) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_feed_back;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @SuppressLint("CheckResult")
    private void commit(String feedBack) {
        if (TextUtils.isEmpty(feedBack)){
            ToastUtils.showShortToast("意见反馈不能为空");
        }
        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
        apiService.feedback(feedBack, Constants.APP)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle()).subscribe(new Consumer<BaseBean>() {
            @Override
            public void accept(BaseBean baseBean) throws Exception {
                FeedBackConfirmDialog feedBackConfirmDialog = new FeedBackConfirmDialog(FeedBackActivity.this);
                feedBackConfirmDialog.setClickListener(new FeedBackConfirmDialog.ClickListener() {
                    @Override
                    public void onClick() {
                        finish();
                    }
                });
                feedBackConfirmDialog.show();
            }
        },new RxThrowableConsumer());
    }

    @Override
    protected void initData() {
    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }
    @OnClick(R.id.tv_confirm)
    public void onViewClicked() {
        commit(etFeedback.getText().toString());
    }
}
