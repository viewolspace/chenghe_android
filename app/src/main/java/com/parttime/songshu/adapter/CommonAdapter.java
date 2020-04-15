package com.parttime.songshu.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.songshu.R;
import com.parttime.songshu.adapter.holder.BannerHolder;
import com.parttime.songshu.adapter.holder.JXRecommendViewHolder;
import com.parttime.songshu.adapter.holder.JXTopViewHolder;
import com.parttime.songshu.adapter.holder.JXTopViewHolder2;
import com.parttime.songshu.adapter.holder.RMRecommendViewHolder;
import com.parttime.songshu.adapter.holder.SYHotViewHolder;
import com.parttime.songshu.adapter.holder.SYRecommendViewHolder;
import com.parttime.songshu.adapter.holder.TJRecommendViewHolder;
import com.parttime.songshu.adapter.holder.TitleViewHolder;
import com.parttime.songshu.adapter.holder.TopGridViewHolder;
import com.parttime.songshu.adapter.holder.TopViewHolder;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/2/28 11:22
 * @describe ：
 */
public class CommonAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.RecyclerItem>{
    public final static int SY_TOP = 0;
    public final static int SY_TOP2 = 3;
    public final static int SY_BANNER = 1;
    public final static int SY_RECOMMEND = 2;
    public final static int TJ_TOP = 10;
    public final static int TJ_BANNER = 11;
    public final static int TJ_RECOMMEND = 12;
    public final static int RM_TOP = 20;
    public final static int RM_BANNER = 21;
    public final static int RM_RECOMMEND = 22;
    public final static int JX_TOP = 30;
    public final static int JX_TOP2 = 33;
    public final static int JX_BANNER = 31;
    public final static int JX_RECOMMEND = 32;
    public final static int SEARCH = 101;
    public final static int TITLE = 102;
    public final static int BANNER = 103;
    @Override
    protected int getItemViewType(int position, RecyclerItem recyclerItem) {
        switch (recyclerItem.type){
            case SY_TOP:
                return R.layout.layout_sy_top;
            case SY_TOP2:
                return R.layout.layout_sy_top_grid;
            case SY_BANNER:
                return R.layout.item_banner_margin;
            case SY_RECOMMEND:
                return R.layout.item_home_hot_recommend;
            case TJ_TOP:
                return R.layout.layout_tj_top;
            case TJ_BANNER:
                return R.layout.item_banner_margin;
            case TJ_RECOMMEND:
                return R.layout.item_tj_recommend;
            case RM_TOP:
                return R.layout.layout_all_top;
            case RM_BANNER:
                return R.layout.item_banner_margin;
            case RM_RECOMMEND:
                return R.layout.item_home_hot_recommend;
            case SEARCH:
               // return R.layout.item_search_result;
                return R.layout.item_home_hot_recommend;
            //精选部分
            case JX_TOP:
                return R.layout.layout_jx_top;
            case JX_TOP2:
                return R.layout.layout_jx_top2;
            case JX_BANNER:
                return R.layout.item_banner_margin;
            case JX_RECOMMEND:
                return R.layout.item_jx_content;
            //通用
            case BANNER:
                return R.layout.item_banner_normal;
        }

        return R.layout.item_jx_content;
    }

    @Override
    protected ViewHolder<RecyclerItem> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        switch (viewType){
            //首页部分
            case  R.layout.layout_sy_top:
                return new TopViewHolder(root);
            case  R.layout.layout_sy_top_grid:
                return new TopGridViewHolder(root);
            case R.layout.layout_home_hot_activity:
                return new SYHotViewHolder(root);
            case R.layout.item_home_hot_recommend:
                return new SYRecommendViewHolder(root);
            case R.layout.item_home_title:
                return new TitleViewHolder(root);
            case  R.layout.item_banner_margin:
            //推荐部分
            case  R.layout.item_banner_normal:
                return new BannerHolder(root);
            case  R.layout.item_tj_recommend:
                return new TJRecommendViewHolder(root);
            case  R.layout.layout_tj_top:
                return new TopViewHolder(root);
            case R.layout.item_recommend_today:
                return new TJRecommendViewHolder(root);
            case  R.layout.layout_home_top2:
                return new TopViewHolder(root);
            case R.layout.item_recommend_all:
                return new RMRecommendViewHolder(root);
            case R.layout.item_search_result:
                return new RMRecommendViewHolder(root);
            case R.layout.layout_all_top:
                return new TopViewHolder(root);
                //精选部分
            case R.layout.item_jx_content:
                return new JXRecommendViewHolder(root);
            case R.layout.layout_jx_top:
                return new JXTopViewHolder(root);
            case R.layout.layout_jx_top2:
                return new JXTopViewHolder2(root);
        }
        return new SYRecommendViewHolder(root);
    }

}
