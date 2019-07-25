package com.example.chenghejianzhi.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.base.base.BaseActivity;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.base.rx.RxEvent;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.adapter.AllAdapter;
import com.example.chenghejianzhi.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView recycler;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        recycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        AllAdapter recommendAdapter = new AllAdapter();
        recycler.setAdapter(recommendAdapter);
        List<BaseRecyclerAdapter.RecyclerItem> itemList = new ArrayList<>();
        for (int i = 0;i<10;i++){
            itemList.add(new BaseRecyclerAdapter.RecyclerItem(AllAdapter.RECOMMEND,null));
        }
        recommendAdapter.replace(itemList);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }
}
