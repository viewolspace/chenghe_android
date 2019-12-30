package com.parttime.rainbow.adapter;

import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.CommonAdBean;
import com.parttime.base.bean.RecommendBean;
import com.parttime.rainbow.R;
import com.parttime.rainbow.activity.JobDetailActivity;
import com.parttime.rainbow.utils.StringUtil;
import com.parttime.rainbow.utils.WebLinkToNativePageUtil;
import com.parttime.rainbow.view.GlideRoundImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/23 11:36
 * @describe ：
 */
public class JXAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.RecyclerItem> {
    public final static int TOP = 0;
    public final static int TOP2 = 5;
    public final static int BANNER = 1;
    public final static int RECOMMEND = 2;
    public final static int TITLE = 3;
    public final static int SEARCH = 4;
    @Override
    protected int getItemViewType(int position, RecyclerItem recyclerItem) {
        switch (recyclerItem.type){
            case TOP:
                return R.layout.layout_jx_top;
            case TOP2:
                return R.layout.layout_jx_top2;
            case BANNER:
                return R.layout.item_recommend_banner;
            case RECOMMEND:
                return R.layout.item_jx_content;
            case TITLE:
                return R.layout.item_home_title;
            case SEARCH:
                return R.layout.item_search_result;
        }

        return R.layout.item_jx_content;
    }

    @Override
    protected ViewHolder<RecyclerItem> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        switch (viewType){
            case R.layout.item_recommend_banner:
                return new BannerHolder(root);
            case R.layout.item_jx_content:
                return new RecommendViewHolder(root);
            case R.layout.item_home_title:
                return new TitleViewHolder(root);
            case R.layout.layout_jx_top:
                return new JXTopViewHolder(root);
            case R.layout.layout_jx_top2:
                return new JXTopViewHolder2(root);
        }
        return new RecommendViewHolder(root);
    }

    /**
     * 头部
     */
    public class JXTopViewHolder extends ViewHolder<RecyclerItem>{
        @BindView(R.id.iv_tab_3)
        ImageView iv_tab_3;
        @BindView(R.id.iv_tab_2)
        ImageView iv_tab_2;
        @BindView(R.id.iv_tab_1)
        ImageView iv_tab_1;
        @BindView(R.id.iv_tab_4)
        ImageView iv_tab_4;

        public JXTopViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {

            CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
            if (commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                if (commonAdBean.getResult().size()>0){
                    if(!TextUtils.isEmpty(commonAdBean.getResult().get(0).getImageUrl())){
                        Glide.with(itemView.getContext()).
                                load(commonAdBean.getResult().get(0).getImageUrl()).into(iv_tab_1);
                    }

                    iv_tab_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(0).getUrl(),commonAdBean.getResult().get(0).getId());
                        }
                    });
                }
                if (commonAdBean.getResult().size()>1){
                    if(!TextUtils.isEmpty(commonAdBean.getResult().get(1).getImageUrl())){
                        Glide.with(itemView.getContext()).
                                load(commonAdBean.getResult().get(1).getImageUrl()).into(iv_tab_2);
                    }

                    iv_tab_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(1).getUrl(),commonAdBean.getResult().get(1).getId());
                        }
                    });
                }
                if (commonAdBean.getResult().size()>2){
                    if(!TextUtils.isEmpty(commonAdBean.getResult().get(2).getImageUrl())){
                        Glide.with(itemView.getContext()).
                                load(commonAdBean.getResult().get(2).getImageUrl()).into(iv_tab_3);
                    }
                    iv_tab_3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(2).getUrl(),commonAdBean.getResult().get(2).getId());
                        }
                    });
                }
            }
        }
    }

    /**
     * 头部
     */
    public class JXTopViewHolder2 extends ViewHolder<RecyclerItem>{

        @BindView(R.id.iv_tab_6)
        ImageView iv_tab_6;
        @BindView(R.id.iv_tab_7)
        ImageView iv_tab_7;

        public JXTopViewHolder2(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {

            CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
            if (commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                if (commonAdBean.getResult().size()>0){
                    if(!TextUtils.isEmpty(commonAdBean.getResult().get(0).getImageUrl())){
                        Glide.with(itemView.getContext()).
                                load(commonAdBean.getResult().get(0).getImageUrl()).into(iv_tab_6);
                    }

                    iv_tab_6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(0).getUrl(),commonAdBean.getResult().get(0).getId());
                        }
                    });
                }
                if (commonAdBean.getResult().size()>1){
                    if(!TextUtils.isEmpty(commonAdBean.getResult().get(1).getImageUrl())){
                        Glide.with(itemView.getContext()).
                                load(commonAdBean.getResult().get(1).getImageUrl()).into(iv_tab_7);
                    }
                    iv_tab_7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(1).getUrl(),commonAdBean.getResult().get(1).getId());
                        }
                    });
                }
            }
        }
    }
    /**
     * banner
     */
    public class BannerHolder extends ViewHolder<RecyclerItem>{
        @BindView(R.id.banner)
        Banner banner;


        public BannerHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {
            CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
            //设置图片加载器
            banner.setImageLoader(new GlideRoundImageLoader());
            //设置图片集合
            List<String> images = new ArrayList<>();
            for (CommonAdBean.ResultBean resultBean:commonAdBean.getResult()){
                images.add(resultBean.getImageUrl());
            }

            banner.setImages(images);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                            commonAdBean.getResult().get(position).getUrl(),commonAdBean.getResult().get(position).getId());
                }
            });
        }
    }
    /**
     * 兼职列表
     */
    public class RecommendViewHolder extends ViewHolder<RecyclerItem>{
        @BindView(R.id.tv_job_title)
        TextView tv_job_title;
        @BindView(R.id.tv_job_money)
        TextView tv_job_money;
        @BindView(R.id.fl_job_desc)
        TagFlowLayout fl_job_desc;
        @BindView(R.id.tv_job_unit)
        TextView tv_job_unit;

        public RecommendViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {
            RecommendBean.ResultBean recommendBean = (RecommendBean.ResultBean) recyclerItem.data;
            if (recommendBean.getTitle()!=null&&recommendBean.getTitle().length()>0){
                if (recommendBean.getVerify()==1){
                    SpannableString spannableString = new SpannableString("   "+recommendBean.getTitle());
                    StringUtil.addImageSpan(spannableString,0,1,R.drawable.jx_verify_item,itemView.getContext());
                    tv_job_title.setText(spannableString);
                }else {
                    tv_job_title.setText(recommendBean.getTitle());
                }
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
                            .from(parent.getContext()).inflate(R.layout.tag_detail2, null);
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
    /**
     * 标题
     */
    public class TitleViewHolder extends ViewHolder<RecyclerItem>{
        @BindView(R.id.tv_title)
        TextView tv_title;

        public TitleViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {
            if (recyclerItem.data!=null){
                tv_title.setText((String)recyclerItem.data);
            }
        }
    }
}
