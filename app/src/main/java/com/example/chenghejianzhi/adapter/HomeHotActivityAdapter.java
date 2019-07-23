package com.example.chenghejianzhi.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/23 12:13
 * @describe ：
 */
public class HomeHotActivityAdapter extends BaseRecyclerAdapter<String> {
    @Override
    protected int getItemViewType(int position, String s) {
        return R.layout.item_home_hot_activity;
    }

    @Override
    protected ViewHolder<String> onCreateViewHolder(View root, ViewGroup parent, int viewType) {
        return new HotActivityViewHolder(root);
    }
    public class HotActivityViewHolder extends BaseRecyclerAdapter.ViewHolder<String>{

        public HotActivityViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(String s) {

        }
    }
}
