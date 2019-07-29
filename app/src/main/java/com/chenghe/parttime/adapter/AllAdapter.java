package com.chenghe.parttime.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chenghe.base.base.BaseRecyclerAdapter;
import com.chenghe.base.bean.CommonAdBean;
import com.chenghe.base.bean.RecommendBean;
import com.chenghe.parttime.R;
import com.chenghe.parttime.activity.JobDetailActivity;
import com.chenghe.parttime.utils.WebLinkToNativePageUtil;
import com.chenghe.parttime.view.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/23 11:36
 * @describe ：
 */
public class AllAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.RecyclerItem> {
    public final static int TOP = 0;
    public final static int BANNER = 1;
    public final static int RECOMMEND = 2;
    public final static int TITLE = 3;
    @Override
    protected int getItemViewType(int position, RecyclerItem recyclerItem) {
        switch (recyclerItem.type){
            case TOP:
                return R.layout.layout_home_top;
            case BANNER:
                return R.layout.item_recommend_banner;
            case RECOMMEND:
                return R.layout.item_recommend_all;
            case TITLE:
                return R.layout.item_home_title;
        }

        return R.layout.item_home_hot_recommend;
    }

    @Override
    protected ViewHolder<RecyclerItem> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        switch (viewType){
            case  R.layout.layout_home_top:
                return new TopViewHolder(root);
            case R.layout.item_recommend_banner:
                return new BannerHolder(root);
            case R.layout.item_recommend_all:
                return new RecommendViewHolder(root);
            case R.layout.item_home_title:
                return new TitleViewHolder(root);
        }
        return new RecommendViewHolder(root);
    }

    /**
     * 头部三个按钮
     */
    public class TopViewHolder extends ViewHolder<RecyclerItem>{
        @BindView(R.id.iv_tab_3)
        ImageView iv_tab_3;
        @BindView(R.id.iv_tab_2)
        ImageView iv_tab_2;
        @BindView(R.id.iv_tab_1)
        ImageView iv_tab_1;
        public TopViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {
            CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
            if (commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                if (commonAdBean.getResult().size()>0){
                    Glide.with(itemView.getContext()).
                            load(commonAdBean.getResult().get(0).getImageUrl()).into(iv_tab_1);
                    iv_tab_1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(0).getUrl());
                        }
                    });
                }
                if (commonAdBean.getResult().size()>1){
                    Glide.with(itemView.getContext()).
                            load(commonAdBean.getResult().get(1).getImageUrl()).into(iv_tab_2);
                    iv_tab_2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(1).getUrl());
                        }
                    });
                }
                if (commonAdBean.getResult().size()>2){
                    Glide.with(itemView.getContext()).
                            load(commonAdBean.getResult().get(2).getImageUrl()).into(iv_tab_3);
                    iv_tab_3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(2).getUrl());
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
            banner.setImageLoader(new GlideImageLoader());
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
                            commonAdBean.getResult().get(position).getUrl());
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
        @BindView(R.id.tv_job_desc)
        TextView tv_job_desc;
        @BindView(R.id.go_detail_button)
        ImageView go_detail_button;

        public RecommendViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {
            RecommendBean.ResultBean recommendBean = (RecommendBean.ResultBean) recyclerItem.data;
            tv_job_title.setText(recommendBean.getTitle());
            tv_job_money.setText(String.valueOf(recommendBean.getSalary()));
            tv_job_desc.setText(recommendBean.getLable().replaceAll(","," | "));
            go_detail_button.setOnClickListener(new View.OnClickListener() {
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
