package com.parttime.rainbow.adapter.holder;

import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.RecommendBean;
import com.parttime.rainbow.R;
import com.parttime.rainbow.activity.JobDetailActivity;
import com.parttime.rainbow.utils.StringUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Arrays;
import java.util.Locale;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/2/28 16:31
 * @describe ：
 */
public class JXRecommendViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem> {
    @BindView(R.id.tv_job_title)
    TextView tv_job_title;
    @BindView(R.id.tv_job_money)
    TextView tv_job_money;
    @BindView(R.id.fl_job_desc)
    TagFlowLayout fl_job_desc;
    @BindView(R.id.tv_job_unit)
    TextView tv_job_unit;
    @BindView(R.id.iv_verify)
    ImageView iv_verify;
    public JXRecommendViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        RecommendBean.ResultBean recommendBean = (RecommendBean.ResultBean) recyclerItem.data;
//        if (recommendBean.getTitle()!=null&&recommendBean.getTitle().length()>0){
//            if (recommendBean.getVerify()==1){
//                SpannableString spannableString = new SpannableString("   "+recommendBean.getTitle());
//                StringUtil.addImageSpan(spannableString,0,1,R.drawable.jx_verify_item,itemView.getContext());
//                tv_job_title.setText(spannableString);
//            }else {
//                tv_job_title.setText(recommendBean.getTitle());
//            }
//        }
        if (recommendBean.getVerify() == 1) {
            iv_verify.setVisibility(View.VISIBLE);
        } else {
            iv_verify.setVisibility(View.GONE);
        }
        tv_job_money.setText(String.valueOf(recommendBean.getSalary()));
        String unit = "元/天";
        String[] lable;
        if (recommendBean.getLable().contains("，")){
            lable = recommendBean.getLable().split("，");
        }else {
            lable = recommendBean.getLable().split(",");

        }

        fl_job_desc.setAdapter(new TagAdapter<String>(Arrays.asList(lable)) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = (TextView) LayoutInflater
                        .from(parent.getContext()).inflate(R.layout.tag_detail3, null);
                textView.setText(s);
                return textView;
            }

        });
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
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JobDetailActivity.start(itemView.getContext(),recommendBean.getId());
            }
        });
    }
}
