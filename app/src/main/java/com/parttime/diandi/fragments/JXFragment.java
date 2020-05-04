package com.parttime.diandi.fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.parttime.base.base.BaseMvpFragment;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.diandi.R;
import com.parttime.diandi.activity.SearchActivity;
import com.parttime.diandi.adapter.CommonAdapter;
import com.parttime.diandi.contract.JXContract;
import com.parttime.diandi.presenter.JXPresenter;
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
public class JXFragment extends BaseMvpFragment<JXContract.Presenter>
        implements JXContract.View , OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;
    @BindView(R.id.rl_search)
    LinearLayout rl_search;
    private CommonAdapter recommendAdapter;

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initWidget(View root) {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendAdapter = new CommonAdapter();
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
    protected JXContract.Presenter initPresenter() {
        return new JXPresenter(this);
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
