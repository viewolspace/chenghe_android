package com.parttime.songshu.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.parttime.base.base.BaseActivity;
import com.parttime.base.base.BaseRecyclerAdapter;
import com.parttime.base.bean.RecommendBean;
import com.parttime.base.retrofit.ApiService;
import com.parttime.base.retrofit.RetrofitServiceCreator;
import com.parttime.base.rx.RxEvent;
import com.parttime.base.rx.RxThrowableConsumer;
import com.parttime.base.rx.RxUtils;
import com.parttime.songshu.R;
import com.parttime.songshu.adapter.CommonAdapter;
import com.parttime.songshu.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class JoinPartTimeActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;
    @BindView(R.id.iv_back)
    ImageView iv_back;

    private int pageIndex = 1;
    private int pageSize = 20;
    private CommonAdapter recommendAdapter;
    public static void start(Context context){
        Intent intent = new Intent(context,JoinPartTimeActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_join_part_time;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        recycler.setLayoutManager(new LinearLayoutManager(JoinPartTimeActivity.this));
        recommendAdapter = new CommonAdapter();
        recycler.setAdapter(recommendAdapter);
        smart_refresh.setOnLoadMoreListener(this);
        smart_refresh.setOnRefreshListener(this);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @SuppressLint("CheckResult")
    private void getData( boolean refresh){
        if (refresh){
            pageIndex=1;
        }
        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
        apiService.queryMyJoinPartTime(pageIndex,pageSize)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle()).subscribe(new Consumer<RecommendBean>() {
            @Override
            public void accept(RecommendBean recommendBean) throws Exception {
                if (refresh){
                    smart_refresh.setEnableLoadMore(true);
                    smart_refresh.finishRefresh();
                }else {
                    smart_refresh.finishLoadMore();
                }
                List<BaseRecyclerAdapter.RecyclerItem> recyclerItems = new ArrayList<>();
                if (recommendBean!=null&&recommendBean.getResult()!=null&&recommendBean.getResult().size()>0){
                    for (RecommendBean.ResultBean resultBean:recommendBean.getResult()){
                        recyclerItems.add(new BaseRecyclerAdapter.RecyclerItem(CommonAdapter.RM_RECOMMEND,resultBean));
                    }
                }else if (recommendBean ==null||recommendBean.getResult()==null||recommendBean.getResult().size()<pageSize){
                    smart_refresh.setEnableLoadMore(false);
                }
                refreshList(recyclerItems,refresh);
                pageIndex++;
            }
        },new RxThrowableConsumer(){
            @Override
            public void accept(Throwable throwable) throws Exception {
                super.accept(throwable);
                if (refresh){
                    smart_refresh.setEnableLoadMore(true);
                    smart_refresh.finishRefresh();
                }else {
                    smart_refresh.finishLoadMore();
                }
            }
        });
    }

    private void refreshList(List<BaseRecyclerAdapter.RecyclerItem> itemList, boolean refresh){
        if (refresh){
            recommendAdapter.replace(itemList);
        }else {
            recommendAdapter.add(itemList);
        }
    }
    @Override
    protected void initData() {
        getData(true);
    }

    @Override
    public void handleDefaultEvent(RxEvent event) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        getData(false);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getData(true);
    }
}
