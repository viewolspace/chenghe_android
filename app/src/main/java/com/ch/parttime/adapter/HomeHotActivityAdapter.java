package com.ch.parttime.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.ch.base.base.BaseRecyclerAdapter;
import com.ch.base.bean.CommonAdBean;
import com.ch.parttime.R;
import com.ch.parttime.utils.WebLinkToNativePageUtil;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/23 12:13
 * @describe ：
 */
public class HomeHotActivityAdapter extends BaseRecyclerAdapter<CommonAdBean.ResultBean> {


    @Override
    protected int getItemViewType(int position, CommonAdBean.ResultBean resultBean) {
        return R.layout.item_home_hot_activity;
    }

    @Override
    protected ViewHolder<CommonAdBean.ResultBean> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        return new HotActivityViewHolder(root);
    }
    public class HotActivityViewHolder extends BaseRecyclerAdapter.ViewHolder<CommonAdBean.ResultBean>{
        @BindView(R.id.iv_ad)
        ImageView iv_ad;
        public HotActivityViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(CommonAdBean.ResultBean resultBean) {
            iv_ad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                            resultBean.getUrl(),resultBean.getId());
                }
            });
            Glide.with(itemView.getContext()).load(resultBean.getImageUrl())
                    .apply(new RequestOptions().transform(new RoundedCorners(25)))
                    .into(iv_ad);
        }

    }
}