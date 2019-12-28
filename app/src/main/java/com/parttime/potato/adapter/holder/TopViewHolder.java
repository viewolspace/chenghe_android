package com.parttime.potato.adapter.holder;

/**
 * Created by zdy On 2019/11/16.
 */

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.CommonAdBean;
import com.parttime.potato.R;
import com.parttime.potato.utils.WebLinkToNativePageUtil;

import butterknife.BindView;

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
    @BindView(R.id.iv_tab_4)
    ImageView iv_tab_4;
    @BindView(R.id.iv_tab_5)
    ImageView iv_tab_5;

    @BindView(R.id.ll_tab_1)
    LinearLayout ll_tab_1;
    @BindView(R.id.ll_tab_2)
    LinearLayout ll_tab_2;
    @BindView(R.id.ll_tab_3)
    LinearLayout ll_tab_3;
    @BindView(R.id.ll_tab_4)
    LinearLayout ll_tab_4;
    @BindView(R.id.ll_tab_5)
    LinearLayout ll_tab_5;
    @BindView(R.id.tv_tab1)
    TextView tv_tab1;
    @BindView(R.id.tv_tab2)
    TextView tv_tab2;
    @BindView(R.id.tv_tab3)
    TextView tv_tab3;
    @BindView(R.id.tv_tab4)
    TextView tv_tab4;
    @BindView(R.id.tv_tab5)
    TextView tv_tab5;
    public TopViewHolder(View itemView) {
        super(itemView);
    }


    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
        if (commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
            if (commonAdBean.getResult().size()>0){
                tv_tab1.setText(commonAdBean.getResult().get(0).getTitle());
                Glide.with(itemView.getContext()).
                        load(commonAdBean.getResult().get(0).getImageUrl()).into(iv_tab_1);
                ll_tab_1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                commonAdBean.getResult().get(0).getUrl(),commonAdBean.getResult().get(0).getId());
                    }
                });
            }
            if (commonAdBean.getResult().size()>1){
                tv_tab2.setText(commonAdBean.getResult().get(1).getTitle());
                Glide.with(itemView.getContext()).
                        load(commonAdBean.getResult().get(1).getImageUrl()).into(iv_tab_2);
                ll_tab_2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                commonAdBean.getResult().get(1).getUrl(),commonAdBean.getResult().get(1).getId());
                    }
                });
            }
            if (commonAdBean.getResult().size()>2){
                tv_tab3.setText(commonAdBean.getResult().get(2).getTitle());
                Glide.with(itemView.getContext()).
                        load(commonAdBean.getResult().get(2).getImageUrl()).into(iv_tab_3);
                ll_tab_3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                commonAdBean.getResult().get(2).getUrl(),commonAdBean.getResult().get(2).getId());
                    }
                });
            }
            if (commonAdBean.getResult().size()>3){
                tv_tab4.setText(commonAdBean.getResult().get(3).getTitle());
                Glide.with(itemView.getContext()).
                        load(commonAdBean.getResult().get(3).getImageUrl()).into(iv_tab_4);
                ll_tab_4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                commonAdBean.getResult().get(3).getUrl(),commonAdBean.getResult().get(2).getId());
                    }
                });
                if (commonAdBean.getResult().size()>4){
                    tv_tab5.setText(commonAdBean.getResult().get(4).getTitle());
                    Glide.with(itemView.getContext()).
                            load(commonAdBean.getResult().get(4).getImageUrl()).into(iv_tab_5);
                    ll_tab_5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                                    commonAdBean.getResult().get(4).getUrl(),commonAdBean.getResult().get(2).getId());
                        }
                    });
                }
            }
        }
    }
}

