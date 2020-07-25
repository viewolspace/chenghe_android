package com.baihe.orange.adapter.holder;

import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.baihe.base.base.BaseRecyclerAdapter;
import com.baihe.base.bean.RecommendBean;
import com.baihe.orange.R;
import com.baihe.orange.activity.JobDetailActivity;
import com.baihe.orange.utils.StringUtil;

import java.util.Locale;

import butterknife.BindView;

/**
 * 热门推荐
 */
public class TJRecommendViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{
    @BindView(R.id.tv_job_title)
    TextView tv_job_title;
    @BindView(R.id.tv_job_money)
    TextView tv_job_money;
    @BindView(R.id.tv_job_desc)
    TextView tv_job_desc;
    @BindView(R.id.go_detail_button)
    ImageView go_detail_button;
    @BindView(R.id.iv_job_icon)
    ImageView iv_job_icon;
    @BindView(R.id.tv_job_unit)
    TextView tv_job_unit;
    public TJRecommendViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        RecommendBean.ResultBean recommendBean = (RecommendBean.ResultBean) recyclerItem.data;
        if (recommendBean.getTitle()!=null&&recommendBean.getTitle().length()>0){
            if (recommendBean.getVerify()==1){
                SpannableString spannableString = new SpannableString("   "+recommendBean.getTitle());
                StringUtil.addImageSpan(spannableString,0,1,R.drawable.verify_head,itemView.getContext());
                tv_job_title.setText(spannableString);
            }else {
                tv_job_title.setText(recommendBean.getTitle());
            }
        }
        tv_job_money.setText(String.valueOf(recommendBean.getSalary()));
        if (recommendBean.getLable().contains(",")){
            tv_job_desc.setText(recommendBean.getLable().replaceAll(","," | "));
        }else {
            tv_job_desc.setText(recommendBean.getLable().replaceAll("，"," | "));
        }
        if (recommendBean.getPic()==null||recommendBean.getPic().trim().isEmpty()){
            iv_job_icon.setVisibility(View.GONE);
        }else {
            iv_job_icon.setVisibility(View.VISIBLE);
            Glide.with(itemView.getContext()).load(recommendBean.getPic()).into(iv_job_icon);
        }
        String unit = "元/天";
        switch (recommendBean.getCycle()){
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
        tv_job_unit.setText(unit);
        go_detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobDetailActivity.start(itemView.getContext(),recommendBean.getId());
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobDetailActivity.start(itemView.getContext(),recommendBean.getId());
            }
        });
    }
}