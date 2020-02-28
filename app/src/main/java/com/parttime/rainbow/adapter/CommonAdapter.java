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
import com.parttime.rainbow.adapter.holder.BannerHolder;
import com.parttime.rainbow.adapter.holder.RMRecommendViewHolder;
import com.parttime.rainbow.adapter.holder.SYHotViewHolder;
import com.parttime.rainbow.adapter.holder.SYRecommendViewHolder;
import com.parttime.rainbow.adapter.holder.TJRecommendViewHolder;
import com.parttime.rainbow.adapter.holder.TitleViewHolder;
import com.parttime.rainbow.adapter.holder.TopViewHolder;
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
 * @email :
 * @time : 2020/2/28 11:22
 * @describe ：
 */
public class CommonAdapter extends BaseRecyclerAdapter<BaseRecyclerAdapter.RecyclerItem>{
    public final static int SY_TOP = 0;
    public final static int SY_BANNER = 1;
    public final static int SY_RECOMMEND = 2;
    public final static int TJ_TOP = 10;
    public final static int TJ_BANNER = 11;
    public final static int TJ_RECOMMEND = 12;
    public final static int RM_TOP = 20;
    public final static int RM_BANNER = 21;
    public final static int RM_RECOMMEND = 22;
    public final static int SEARCH = 101;
    public final static int TITLE = 102;

    @Override
    protected int getItemViewType(int position, RecyclerItem recyclerItem) {
        switch (recyclerItem.type){
            case SY_TOP:
                return R.layout.layout_sy_top;
            case SY_BANNER:
                return R.layout.item_banner_normal;
            case SY_RECOMMEND:
                return R.layout.item_home_hot_recommend;
            case TITLE:
                return R.layout.item_home_title;
            case TJ_BANNER:
                return R.layout.item_banner_normal;
            case TJ_RECOMMEND:
                return R.layout.item_home_hot_recommend;
            case RM_TOP:
                return R.layout.layout_all_top;
            case RM_BANNER:
                return R.layout.item_recommend_banner;
            case RM_RECOMMEND:
                return R.layout.item_home_hot_recommend;
            case SEARCH:
                return R.layout.item_search_result;
        }

        return R.layout.item_jx_content;
    }

    @Override
    protected ViewHolder<RecyclerItem> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        switch (viewType){
            //首页部分
            case  R.layout.layout_sy_top:
                return new TopViewHolder(root);
            case R.layout.layout_home_hot_activity:
                return new SYHotViewHolder(root);
            case R.layout.item_home_hot_recommend:
                return new SYRecommendViewHolder(root);
            case R.layout.item_home_title:
                return new TitleViewHolder(root);
            case  R.layout.item_recommend_banner:
            //推荐部分
            case  R.layout.item_banner_normal:
                return new BannerHolder(root);
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

        }
        return new SYRecommendViewHolder(root);
    }

}
