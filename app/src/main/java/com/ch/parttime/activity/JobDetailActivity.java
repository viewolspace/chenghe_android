package com.ch.parttime.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.ch.base.base.BaseMvpActivity;
import com.ch.base.bean.JobDetailBean;
import com.ch.base.bean.JoinPartTimeBean;
import com.ch.base.constants.Constants;
import com.ch.base.constants.RoutMap;
import com.ch.base.util.ToastUtils;
import com.ch.base.util.UserInfoUtil;
import com.ch.parttime.R;
import com.ch.parttime.contract.JobDetailContract;
import com.ch.parttime.presenter.JobDetailPresenter;
import com.ch.parttime.utils.DateUtil;
import com.ch.parttime.utils.LinearLayoutUtil;
import com.ch.parttime.utils.MobEventHelper;
import com.ch.parttime.utils.StatusBarUtils;
import com.ch.parttime.utils.StringUtil;
import com.ch.parttime.view.dilaog.CopyContactDialog;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class JobDetailActivity extends BaseMvpActivity<JobDetailContract.Presenter> implements JobDetailContract.View {


    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.tv_job_title)
    TextView tvJobTitle;
    @BindView(R.id.tv_update_time)
    TextView tvUpdateTime;
    @BindView(R.id.tv_job_money)
    TextView tvJobMoney;
    @BindView(R.id.tv_job_unit)
    TextView tvJobUnit;
    @BindView(R.id.flowLayout)
    TagFlowLayout flowLayout;
    @BindView(R.id.tv_work_time)
    TextView tvWorkTime;
    @BindView(R.id.tv_work_location)
    TextView tvWorkLocation;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.rl_detail_1)
    RelativeLayout rlDetail1;
    @BindView(R.id.tv_work_content_title)
    TextView tvWorkContentTitle;
    @BindView(R.id.tv_work_content)
    TextView tvWorkContent;
    @BindView(R.id.rl_detail_2)
    RelativeLayout rlDetail2;
    @BindView(R.id.tv_company_title)
    TextView tvCompanyTitle;
    @BindView(R.id.iv_company_logo)
    ImageView ivCompanyLogo;
    @BindView(R.id.tv_company_name)
    TextView tvCompanyName;
    @BindView(R.id.tv_star)
    TextView tvStar;
    @BindView(R.id.ll_star)
    LinearLayout llStar;
    @BindView(R.id.rl_detail_3)
    RelativeLayout rlDetail3;
    @BindView(R.id.tv_apply)
    TextView tvApply;
    @BindView(R.id.iv_verify)
    ImageView iv_verify;
    private int id;
    private JobDetailBean jobDetailBean;
    public static void start(Context context, int id) {
        Intent intent = new Intent(context, JobDetailActivity.class);
        intent.putExtra("id", id);
        MobEventHelper.statistics(context,"1","查看职位");
        context.startActivity(intent);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_job_detail;
    }

    @Override
    protected boolean leftToRightBack() {
        return true;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
    }


    @Override
    protected void initData() {
        id = getIntent().getIntExtra("id", 0);
        presenter.getData(id);
    }

    @Override
    protected JobDetailContract.Presenter initPresenter() {
        return new JobDetailPresenter(this);
    }


    @OnClick({R.id.tv_apply, R.id.tv_copy,R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_apply:
                if ( UserInfoUtil.getInstance().isLogin()){
                    MobEventHelper.statistics(JobDetailActivity.this,"3","职位报名");
                    presenter.apply(id);
                }else {
                    LoginActivity.start(JobDetailActivity.this);
                }


                break;
            case R.id.tv_copy:
                if ( UserInfoUtil.getInstance().isLogin()){
                    MobEventHelper.statistics(JobDetailActivity.this,"2","复制联系方式");
                    presenter.copyRecord(id);
                    if (jobDetailBean!=null&&jobDetailBean.getResult()!=null){
                        CopyContactDialog copyContactDialog = new CopyContactDialog(JobDetailActivity.this,jobDetailBean.getResult().getContactType(),
                                jobDetailBean.getResult().getContact(),1);
                        copyContactDialog.show();
                    }
                }else {
                    LoginActivity.start(JobDetailActivity.this);
                }


                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void refreshList(JobDetailBean jobDetailBean) {
        if (jobDetailBean == null) {
            return;
        }
        this.jobDetailBean = jobDetailBean;
        if ("1".equals(jobDetailBean.getIsJoin())) {
            tvApply.setText("已报名");
            tvApply.setBackgroundColor(getResources().getColor(R.color.color_B2B2B2));
            tvApply.setEnabled(false);
        } else {
            tvApply.setText("报名参加");
            tvApply.setBackgroundResource(R.drawable.shape_apply_bg);
        }
        JobDetailBean.ResultBean resultBean = jobDetailBean.getResult();
        if (resultBean == null) {
            return;
        }
        if (resultBean.getContactType() == Constants.CONTACT_QQ){
            tvCopy.setText("点击复制联系方式 QQ："+resultBean.getContact());
        }else if (resultBean.getContactType() == Constants.CONTACT_WECHAT){
            tvCopy.setText("点击复制联系方式 微信："+resultBean.getContact());
        }else if (resultBean.getContactType() == Constants.CONTACT_PHONE){
            tvCopy.setText("点击复制联系方式 电话："+resultBean.getContact());
        }
        if (resultBean.getContact()==null||resultBean.getContact().trim().isEmpty()){
            tvCopy.setVisibility(View.GONE);
        }else {
            tvCopy.setVisibility(View.VISIBLE);
        }
        if (resultBean.getVerify()==1){
            iv_verify.setVisibility(View.VISIBLE);
        }else {
            iv_verify.setVisibility(View.GONE);
        }
        tvJobTitle.setText(resultBean.getTitle());
        String time = String.format(Locale.ENGLISH,"更新时间：%s"
                ,DateUtil.getDateToString(resultBean.getCTime(),"yyyy-MM-dd"));
        tvUpdateTime.setText(time);
        String[] lable = resultBean.getLable().split(",");
        flowLayout.setAdapter(new TagAdapter<String>(Arrays.asList(lable)) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.tag_detail, null);
                textView.setText(s);
                return textView;
            }

        });

        SpannableString workTime =
                new SpannableString(String.format(Locale.ENGLISH, "工作时间：%s", resultBean.getWorkTime()));
        StringUtil.
                setForegroundColorSpan(workTime, 0,
                        "工作时间：".length()
                        , getResources().getColor(R.color.color_656565));
        tvWorkTime.setText(workTime);
        SpannableString workLocation =
                new SpannableString(String.format(Locale.ENGLISH, "工作地点：%s", resultBean.getWorkAddress()));
        StringUtil.
                setForegroundColorSpan(workLocation, 0,
                        "工作地点：".length()
                        , getResources().getColor(R.color.color_656565));
        tvWorkLocation.setText(workLocation);

        tvWorkContent.setText(Html.fromHtml(resultBean.getContent()));

        tvJobMoney.setText(String.valueOf(resultBean.getSalary()));
        String unit = "元/天";
        switch (resultBean.getCycle()){
            case 0:
                unit = String.format(Locale.ENGLISH,"元/%s","小时");
                break;
            case 1:
                unit = String.format(Locale.ENGLISH,"元/%s","天");
                break;
            case 2:
                unit = String.format(Locale.ENGLISH,"元/%s","周");
                break;
            case 3:
                unit = String.format(Locale.ENGLISH,"元/%s","月");
                break;
            case 4:
                unit = String.format(Locale.ENGLISH,"元/%s","季度");
                break;
        }
        tvJobUnit.setText(unit);
        JobDetailBean.CompanyBean companyBean = jobDetailBean.getCompany();
        if (companyBean == null){
            return;
        }
        if (companyBean.getLogo() != null && !companyBean.getLogo().isEmpty()) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.transform(new CircleCrop());
            requestOptions.placeholder(R.drawable.company_default);
            Glide.with(JobDetailActivity.this)
                    .load(resultBean.getPic())
                    .apply(requestOptions)
                    .into(ivCompanyLogo);
        }else {
            Glide.with(JobDetailActivity.this)
                    .load(R.drawable.company_default)
                    .into(ivCompanyLogo);
        }
        tvCompanyName.setText(companyBean.getName());
        LinearLayoutUtil.setCompanyLevel(llStar,companyBean.getStar());
    }

    @Override
    public void refreshApply(JoinPartTimeBean joinPartTimeBean) {
        if ("0000".equals(joinPartTimeBean.getStatus())) {
            tvApply.setText("已报名");
            tvApply.setBackgroundColor(getResources().getColor(R.color.color_B2B2B2));
            tvApply.setEnabled(false);
            if (jobDetailBean!=null&&jobDetailBean.getResult()!=null){
                if (jobDetailBean.getResult().getContact()==null||jobDetailBean.getResult().getContact().trim().isEmpty()){
                    if (joinPartTimeBean.getFlag()==1){
                        CopyContactDialog copyContactDialog = new CopyContactDialog(JobDetailActivity.this,jobDetailBean.getResult().getContactType(),
                                jobDetailBean.getResult().getContact(),joinPartTimeBean.getFlag());
                        copyContactDialog.show();
                    }else {
                        ToastUtils.showShortToast("报名成功");
                    }
                }else {
                    CopyContactDialog copyContactDialog = new CopyContactDialog(JobDetailActivity.this,jobDetailBean.getResult().getContactType(),
                            jobDetailBean.getResult().getContact(),joinPartTimeBean.getFlag());
                    copyContactDialog.show();
                }

            }
        } else {
            tvApply.setText("报名参加");
            tvApply.setBackgroundResource(R.drawable.shape_apply_bg);
        }
    }

}
