package com.parttime.songshu.adapter.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.RecommendBean;
import com.parttime.songshu.R;
import com.parttime.songshu.activity.JobDetailActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
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
    @BindView(R.id.tv_job_unit)
    TextView tv_job_unit;
    @BindView(R.id.iv_verify)
    ImageView iv_verify;
    @BindView(R.id.fl_job_desc)
    TagFlowLayout fl_job_desc;

    public TJRecommendViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {


        RecommendBean.ResultBean recommendBean = (RecommendBean.ResultBean) recyclerItem.data;
        tv_job_title.setText(recommendBean.getTitle());
        tv_job_money.setText(String.valueOf(recommendBean.getSalary()));
//            if (recommendBean.getLable().contains(",")){
//                tv_job_desc.setText(recommendBean.getLable().replaceAll(","," | "));
//            }else {
//                tv_job_desc.setText(recommendBean.getLable().replaceAll("，"," | "));
//            }
        String[] lable;
        if (recommendBean.getLable().contains("，")) {
            lable = recommendBean.getLable().split("，");
        } else {
            lable = recommendBean.getLable().split(",");
        }

        fl_job_desc.setAdapter(new TagAdapter<String>(Arrays.asList(lable)) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.tag_detail4, null);
//                if (position%3==0){
//                    textView.setBackgroundResource(R.drawable.shape_stk1_cn2_fc6d3f);
//                    textView.setTextColor(Color.parseColor("#fc6d3f"));
//                }else if (position%3==1){
//                    textView.setBackgroundResource(R.drawable.shape_stk1_cn2_24ba87);
//                    textView.setTextColor(Color.parseColor("#24ba87"));
//                }else if (position%3==2){
//                    textView.setBackgroundResource(R.drawable.shape_stk1_cn2_3f78ea);
//                    textView.setTextColor(Color.parseColor("#cn2_3f78ea"));
//                }
                textView.setText(s);
                return textView;
            }

        });
        if (recommendBean.getVerify() == 1) {
            iv_verify.setVisibility(View.VISIBLE);
        } else {
            iv_verify.setVisibility(View.GONE);
        }
        String unit = "元/天";
        switch (recommendBean.getCycle()) {
            case 0:
                unit = String.format(Locale.ENGLISH, "元/%s", "小时");
                break;
            case 1:
                unit = String.format(Locale.ENGLISH, "元/%s", "天");
                break;
            case 2:
                unit = String.format(Locale.ENGLISH, "元/%s", "周");
                break;
            case 3:
                unit = String.format(Locale.ENGLISH, "元/%s", "月");
                break;
            case 4:
                unit = String.format(Locale.ENGLISH, "元/%s", "季度");
                break;
        }
        tv_job_unit.setText(unit);
        go_detail_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobDetailActivity.start(itemView.getContext(), recommendBean.getId());
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobDetailActivity.start(itemView.getContext(), recommendBean.getId());
            }
        });
    }
}