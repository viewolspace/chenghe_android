package com.baihe.orange.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baihe.base.base.BaseActivity;
import com.baihe.base.base.BaseRecyclerAdapter;
import com.baihe.base.bean.RecommendBean;
import com.baihe.base.constants.Constants;
import com.baihe.base.retrofit.ApiService;
import com.baihe.base.retrofit.RetrofitServiceCreator;
import com.baihe.base.rx.RxEvent;
import com.baihe.base.rx.RxThrowableConsumer;
import com.baihe.base.rx.RxUtils;
import com.baihe.orange.R;
import com.baihe.orange.adapter.CommonAdapter;
import com.baihe.orange.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class CategoryPartTimeActivity extends BaseActivity implements OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.smart_refresh)
    SmartRefreshLayout smart_refresh;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    private int pageIndex = 1;
    private int pageSize = 20;
    private CommonAdapter recommendAdapter;
    private String category;
    public static void start(Context context,String categoryId,String categoryName){
        Intent intent = new Intent(context, CategoryPartTimeActivity.class);
        intent.putExtra(Constants.CATEGORY_ID,categoryId);
        intent.putExtra(Constants.CATEGORY_NAME,categoryName);
        context.startActivity(intent);
    }
    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_join_part_time;
    }

    @Override
    protected void initWidget() {
        StatusBarUtils.statusbar(this);
        recycler.setLayoutManager(new LinearLayoutManager(CategoryPartTimeActivity.this));
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
    @Override
    protected void initData() {
        category = getIntent().getStringExtra(Constants.CATEGORY_ID);
        String categoryName = getIntent().getStringExtra(Constants.CATEGORY_NAME);
        tv_title.setText(categoryName);
        getData(true,category);
    }

    @SuppressLint("CheckResult")
    private void getData( boolean refresh,String category){
        if (refresh){
            pageIndex=1;
        }
        ApiService apiService = RetrofitServiceCreator.createService(ApiService.class);
        apiService.queryCategory(category,pageIndex,pageSize)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mProvider.bindToLifecycle()).subscribe(new Consumer<RecommendBean>() {
            @Override
            public void accept(RecommendBean recommendBean) throws Exception {
                if (refresh){
                    refreshCategory(recommendBean.getCategory());
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


    public void refreshCategory(RecommendBean.CategoryBean categoryBean){
        if (categoryBean==null){
            return;
        }
        tv_title.setText(categoryBean.getName());
    }
    @Override
    public void handleDefaultEvent(RxEvent event) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        getData(false,category);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        getData(true,category);
    }
}
