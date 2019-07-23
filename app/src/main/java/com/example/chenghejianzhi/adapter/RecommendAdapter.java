package com.example.chenghejianzhi.adapter;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.utils.DimenUtil;
import com.example.chenghejianzhi.view.GlideImageLoader;
import com.youth.banner.Banner;

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

        return R.layout.item_home_hot_recommend;
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
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            List<Integer> images = new ArrayList<>();
            for (int i = 0;i<3;i++){
             images.add(R.drawable.banner_test);
            }
            banner.setImages(images);
            //banner设置方法全部调用完毕时最后调用
            banner.start();
        }
    }

    /**
     * 热门活动
     */
    public class HotViewHolder extends ViewHolder<RecyclerItem>{
        @BindView(R.id.recycler_hot_activity)
        RecyclerView recyclerView;

        public HotViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {
            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                    outRect.right = DimenUtil.dipToPixels(itemView.getContext(),8);
                }
            });
            HomeHotActivityAdapter homeHotActivityAdapter = new HomeHotActivityAdapter();
            recyclerView.setAdapter(homeHotActivityAdapter);
            List<String> hotList = new ArrayList<>();
            for (int i = 0;i<10;i++){
                hotList.add("");
            }
            homeHotActivityAdapter.replace(hotList);
        }
    }
    /**
     * 热门推荐
     */
    public class RecommendViewHolder extends ViewHolder<RecyclerItem>{


        public RecommendViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {

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
