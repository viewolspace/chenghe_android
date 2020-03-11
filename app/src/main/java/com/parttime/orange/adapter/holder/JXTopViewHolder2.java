package com.parttime.orange.adapter.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.CommonAdBean;
import com.parttime.orange.R;
import com.parttime.orange.utils.WebLinkToNativePageUtil;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/2/28 16:28
 * @describe ：
 */
public class JXTopViewHolder2 extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem> {
    @BindView(R.id.iv_tab_6)
    ImageView iv_tab_6;
    @BindView(R.id.iv_tab_7)
    ImageView iv_tab_7;

    public JXTopViewHolder2(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
        if (commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
            if (commonAdBean.getResult().size()>0){
                if(!TextUtils.isEmpty(commonAdBean.getResult().get(0).getImageUrl())){
                    Glide.with(itemView.getContext()).
                            load(commonAdBean.getResult().get(0).getImageUrl()).into(iv_tab_6);
                }

                iv_tab_6.setOnClickListener(new View.OnClickListener() {
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
                            load(commonAdBean.getResult().get(1).getImageUrl()).into(iv_tab_7);
                }
                iv_tab_7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                commonAdBean.getResult().get(1).getUrl(),commonAdBean.getResult().get(1).getId());
                    }
                });
            }
        }
    }
}
