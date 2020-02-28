package com.parttime.rainbow.adapter.holder;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.CommonAdBean;
import com.parttime.rainbow.R;
import com.parttime.rainbow.adapter.HomeHotActivityAdapter;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email :
 * @time : 2020/2/28 11:50
 * @describe ：
 */
public class SYHotViewHolder extends BaseRecyclerAdapter.ViewHolder<BaseRecyclerAdapter.RecyclerItem>{
    @BindView(R.id.recycler_hot_activity)
    RecyclerView recyclerView;
    public SYHotViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    protected void onBind(BaseRecyclerAdapter.RecyclerItem recyclerItem) {
        CommonAdBean commonAdBean = (CommonAdBean) recyclerItem.data;
        recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(),LinearLayoutManager.HORIZONTAL,false));
//            recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
//                @Override
//                public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
//                    outRect.right = DimenUtil.dipToPixels(itemView.getContext(),8);
//                }
//            });
        HomeHotActivityAdapter homeHotActivityAdapter = new HomeHotActivityAdapter();
        recyclerView.setAdapter(homeHotActivityAdapter);
        if (commonAdBean.getResult()!=null&&commonAdBean.getResult().size()>0){
            homeHotActivityAdapter.replace(commonAdBean.getResult());
        }


    }
}
