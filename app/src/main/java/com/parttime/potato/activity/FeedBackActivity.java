package com.parttime.potato.activity;

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

import com.parttime.base.base.BaseActivity;
import com.parttime.base.bean.BaseBean;
import com.parttime.base.constants.Constants;
import com.parttime.base.retrofit.ApiService;
import com.parttime.base.retrofit.RetrofitServiceCreator;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.rx.RxThrowableConsumer;
import com.parttime.base.rx.RxUtils;
import com.parttime.base.util.ToastUtils;
import com.parttime.potato.R;
import com.parttime.potato.utils.StatusBarUtils;
import com.parttime.potato.view.dilaog.FeedBackConfirmDialog;

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
