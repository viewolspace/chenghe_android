package com.baihe.orange.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.baihe.base.base.BaseRecyclerAdapter;
import com.baihe.orange.R;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/2/28 11:52
 * @describe ：
 */
public class TitleViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{
    @BindView(R.id.tv_title)
    TextView tv_title;

    public TitleViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        if (recyclerItem.data!=null){
            tv_title.setText((String)recyclerItem.data);
        }
    }
}
