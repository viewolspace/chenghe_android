package com.parttime.sunshine.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.CommonAdBean;
import com.parttime.sunshine.R;
import com.parttime.sunshine.utils.WebLinkToNativePageUtil;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/2/28 16:28
 * @describe ：
 */
public class JXTopViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem> {
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
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
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
