package com.chenghe.parttime.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
public class RecommendAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.RecyclerItem> {
    public final static int TOP_BANNER = 0;
    public final static int RECOMMEND = 1;
    public final static int TITLE = 2;
    @Override
    protected int getItemViewType(int position, RecyclerItem recyclerItem) {
        switch (recyclerItem.type){
            case TOP_BANNER:
                return R.layout.item_recommend_banner;
            case RECOMMEND:
                return R.layout.item_recommend_today;
            case TITLE:
                return R.layout.item_home_title;
        }

        return R.layout.item_recommend_today;
    }

    @Override
    protected ViewHolder<RecyclerItem> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        switch (viewType){
            case  R.layout.item_recommend_banner:
                return new BannerHolder(root);
            case R.layout.item_home_title:
                return new TitleViewHolder(root);
            case R.layout.item_recommend_today:
                return new RecommendViewHolder(root);
        }
        return new RecommendViewHolder(root);
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

//    /**
//     * 热门活动
//     */
//    public class HotViewHolder extends ViewHolder<RecyclerItem>{
//        @BindView(R.id.recycler_hot_activity)
//        RecyclerView recyclerView;
//
//        public HotViewHolder(View itemView) {
//            super(itemView);
//        }
//
//        @Override
//        protected void onBind(RecyclerItem recyclerItem) {
//            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
//            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//                @Override
//                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                    outRect.right = DimenUtil.dipToPixels(itemView.getContext(),8);
//                }
//            });
//            HomeHotActivityAdapter homeHotActivityAdapter = new HomeHotActivityAdapter();
//            recyclerView.setAdapter(homeHotActivityAdapter);
////            List<String> hotList = new ArrayList<>();
////            for (int i = 0;i<10;i++){
////                hotList.add("");
////            }
////            homeHotActivityAdapter.replace(hotList);
//        }
//    }
    /**
     * 热门推荐
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
        @BindView(R.id.iv_job_icon)
        ImageView iv_job_icon;

        public RecommendViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {
            RecommendBean.ResultBean recommendBean = (RecommendBean.ResultBean) recyclerItem.data;
            tv_job_title.setText(recommendBean.getTitle());
            tv_job_money.setText(String.valueOf(recommendBean.getSalary()));
            tv_job_desc.setText(recommendBean.getLable().replaceAll(","," | "));
            if (recommendBean.getPic()==null||recommendBean.getPic().trim().isEmpty()){
                iv_job_icon.setVisibility(View.GONE);
            }else {
                iv_job_icon.setVisibility(View.VISIBLE);
                Glide.with(itemView.getContext()).load(recommendBean.getPic()).into(iv_job_icon);
            }
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
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tv_title.getLayoutParams();
                layoutParams.bottomMargin = 0;
            }
        }
    }
}
