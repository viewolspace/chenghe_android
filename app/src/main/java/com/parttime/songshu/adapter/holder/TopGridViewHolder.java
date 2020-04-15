package com.parttime.songshu.adapter.holder;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.CommonAdBean;
import com.parttime.songshu.R;
import com.parttime.songshu.utils.WebLinkToNativePageUtil;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/4/15 14:54
 * @describe ：
 */
public class TopGridViewHolder  extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{
    @BindView(R.id.rv_menu)
    RecyclerView recyclerView;
    private final TopMenuItemAdapter topMenuItemAdapter;

    public TopGridViewHolder(View itemView) {
        super(itemView);
        recyclerView.setLayoutManager(new GridLayoutManager(itemView.getContext(),3));
        topMenuItemAdapter = new TopMenuItemAdapter();
        recyclerView.setAdapter(topMenuItemAdapter);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
        topMenuItemAdapter.replace(commonAdBean.getResult());
    }


    public class TopMenuItemAdapter  extends BaseRecyclerAdapter<CommonAdBean.ResultBean>{

        @Override
        protected int getItemViewType(int position, CommonAdBean.ResultBean resultBean) {
            return R.layout.item_top_grid_menu;
        }

        @Override
        protected ViewHolder<CommonAdBean.ResultBean> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
            return new TopMenuItemViewHolder(root);
        }
    }

    public class TopMenuItemViewHolder extends BaseRecyclerAdapter.ViewHolder<CommonAdBean.ResultBean>{
        @BindView(R.id.iv_tab)
        ImageView iv_tab;
        @BindView(R.id.tv_tab)
        TextView tv_tab;
        public TopMenuItemViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(CommonAdBean.ResultBean resultBean) {
            tv_tab.setText(resultBean.getTitle());
            Glide.with(itemView.getContext()).
                    load(resultBean.getImageUrl()).into(iv_tab);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WebLinkToNativePageUtil.dealWithUrl(itemView.getContext(),
                            resultBean.getUrl(),resultBean.getId());
                }
            });
        }
    }
}
