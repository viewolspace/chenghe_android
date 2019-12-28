package com.parttime.potato.adapter.holder;

import android.view.View;

import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.CommonAdBean;
import com.parttime.potato.R;
import com.parttime.potato.utils.PhoneUtils;
import com.parttime.potato.utils.WebLinkToNativePageUtil;
import com.parttime.potato.view.GlideRoundImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * banner
 */
public class BannerHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem> {
    @BindView(R.id.banner)
    Banner banner;


    public BannerHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
        //设置图片加载器
        banner.setImageLoader(new GlideRoundImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片集合
        if (commonAdBean.getResult()==null){
            return;
        }
        List<String> images = new ArrayList<>();
        for (CommonAdBean.ResultBean resultBean : commonAdBean.getResult()) {
            images.add(resultBean.getImageUrl());
        }
        banner.getViewPager().setPageMargin(PhoneUtils.dipToPixels(15));
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.isAutoPlay(true);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                        commonAdBean.getResult().get(position).getUrl(), commonAdBean.getResult().get(position).getId());
            }
        });
    }
}