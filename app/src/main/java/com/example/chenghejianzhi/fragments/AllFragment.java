package com.example.chenghejianzhi.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.base.BaseRecyclerAdapter;
import com.example.chenghejianzhi.R;
import com.example.chenghejianzhi.activity.SearchActivity;
import com.example.chenghejianzhi.adapter.AllAdapter;
import com.example.chenghejianzhi.adapter.RecommendAdapter;
import com.example.chenghejianzhi.contract.AllContract;
import com.example.chenghejianzhi.presenter.AllPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class AllFragment extends BaseMvpFragment<AllContract.Presenter>
        implements AllContract.View , OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;
    @BindView(R.id.rl_search)
    LinearLayout rl_search;
    private AllAdapter recommendAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_all;
    }

    @Override
    protected void initWidget(View root) {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendAdapter = new AllAdapter();
        recycler.setAdapter(recommendAdapter);
        smart_refresh.setOnRefreshListener(this);
        smart_refresh.setOnLoadMoreListener(this);
        rl_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.start(getContext());
            }
        });
    }

    @Override
    protected void initData() {
        presenter.getData(true);
    }

    @Override
    protected AllContract.Presenter initPresenter() {
        return new AllPresenter(this);
    }


    @Override
    public void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList, boolean refresh) {
        if (refresh){
            recommendAdapter.replace(itemList);
        }else {
            recommendAdapter.add(itemList);
        }
    }

    @Override
    public void loadFinish(boolean refresh) {
        if (refresh){
            smart_refresh.finishRefresh();
        }else {
            smart_refresh.finishLoadMore();
        }
    }

    @Override
    public void loadMoreEnable(boolean enable) {
        smart_refresh.setEnableLoadMore(enable);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        presenter.getData(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        presenter.getData(true);    }
}
