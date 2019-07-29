package com.chenghe.parttime.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import java.util.Locale;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/23 11:36
 * @describe ：
 */
public class HomeAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.RecyclerItem> {
    public final static int HOME_TOP = 0;
    public final static int HOME_HOT = 1;
    public final static int HOME_RECOMMEND = 2;
    public final static int HOME_TITLE = 3;
    @Override
    protected int getItemViewType(int position, RecyclerItem recyclerItem) {
        switch (recyclerItem.type){
            case HOME_TOP:
                return R.layout.layout_home_top;
            case HOME_HOT:
                return R.layout.layout_home_hot_activity;
            case HOME_RECOMMEND:
                return R.layout.item_home_hot_recommend;
            case HOME_TITLE:
                return R.layout.item_home_title;
        }

        return R.layout.item_home_hot_recommend;
    }

    @Override
    protected ViewHolder<RecyclerItem> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        switch (viewType){
            case  R.layout.layout_home_top:
                return new TopViewHolder(root);
            case R.layout.layout_home_hot_activity:
                return new HotViewHolder(root);
            case R.layout.item_home_hot_recommend:
                return new RecommendViewHolder(root);
            case R.layout.item_home_title:
                return new TitleViewHolder(root);
        }
        return new RecommendViewHolder(root);
    }

    /**
     * 头部三个按钮
     */
    public class TopViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{
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
     * 热门活动
     */
    public class HotViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{
        @BindView(R.id.recycler_hot_activity)
        RecyclerView recyclerView;

        public HotViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {
            CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
//            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//                @Override
//                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                    outRect.right = DimenUtil.dipToPixels(itemView.getContext(),8);
//                }
//            });
            HomeHotActivityAdapter homeHotActivityAdapter = new HomeHotActivityAdapter();
            recyclerView.setAdapter(homeHotActivityAdapter);
            if (commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
                homeHotActivityAdapter.replace(commonAdBean.getResult());
            }


        }
    }
    /**
     * 热门推荐
     */
    public class RecommendViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{
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

        public RecommendViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {

            RecommendBean.ResultBean recommendBean = (RecommendBean.ResultBean) recyclerItem.data;
            tv_job_title.setText(recommendBean.getTitle());
            tv_job_money.setText(String.valueOf(recommendBean.getSalary()));
            tv_job_desc.setText(recommendBean.getLable().replaceAll(","," | "));
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
    /**
     * 标题
     */
    public class TitleViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{
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
