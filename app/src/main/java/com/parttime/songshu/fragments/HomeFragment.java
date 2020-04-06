package com.parttime.songshu.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.parttime.base.base.BaseMvpFragment;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.songshu.R;
import com.parttime.songshu.activity.SearchActivity;
import com.parttime.songshu.adapter.CommonAdapter;
import com.parttime.songshu.contract.HomeContract;
import com.parttime.songshu.presenter.HomePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;

/**
 * @author : sklyand
 * @email : zhengdengyao@51yryc.com
 * @time : 2019/7/22 14:02
 * @describe ：
 */
public class HomeFragment extends BaseMvpFragment<HomeContract.Presenter>
        implements HomeContract.View , OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;
    @BindView(R.id.rl_search)
    LinearLayout rl_search;

    private CommonAdapter homeAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initWidget(View root) {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        homeAdapter = new CommonAdapter();
        recycler.setAdapter(homeAdapter);
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
    protected HomeContract.Presenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        presenter.getData(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        presenter.getData(true);
    }

    @Override
    public void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList, boolean refresh) {
        if (refresh){
            homeAdapter.replace(itemList);
        }else {
            homeAdapter.add(itemList);
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

}
