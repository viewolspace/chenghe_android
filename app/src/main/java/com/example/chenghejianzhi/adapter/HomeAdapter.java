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

import java.util.ArrayList;
import java.util.List;

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


        public TopViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(RecyclerItem recyclerItem) {

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
    public class RecommendViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{


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
